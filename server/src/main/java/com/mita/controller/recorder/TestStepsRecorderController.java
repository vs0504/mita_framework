package com.mita.controller.recorder;

import com.mita.specification.TestStepSpecificationsBuilder;
import com.mita.dto.RestStepResponseDTO;
import com.mita.dto.TestStepDTO;
import com.mita.exception.ResourceNotFoundException;
import com.mita.exception.MitaException;
import com.mita.mapper.TestStepMapper;
import com.mita.mapper.recorder.TestStepRecorderMapper;
import com.mita.mapper.recorder.UiIdentifierMapper;
import com.mita.model.Element;
import com.mita.model.TestStep;
import com.mita.model.TestStepPriority;
import com.mita.model.TestStepType;
import com.mita.model.recorder.TestStepRecorderDTO;
import com.mita.model.recorder.TestStepRecorderRequest;
import com.mita.model.recorder.UiIdentifierRequest;
import com.mita.service.ElementService;
import com.mita.service.TestStepService;
import com.mita.util.HttpClient;
import com.mita.web.request.ElementRequest;
import com.mita.web.request.RestStepRequest;
import com.mita.web.request.TestStepRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(path = "/os_recorder/v2/test_steps", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class TestStepsRecorderController {

    private final HttpClient httpClient;
    private final TestStepService service;
    private final TestStepMapper mapper;
    private final ElementService elementService;
    //private final ElementMapper elementMapper;
    private final UiIdentifierMapper uiIdentifierMapper;
    private final TestStepRecorderMapper testStepRecorderMapper;

    @RequestMapping(path = "/fetch_rest_response", method = RequestMethod.POST)
    public RestStepResponseDTO fetchApiResponse(@RequestBody RestStepRequest restStepRequest) {
        log.debug("GET /os_recorder/v2/test_steps/fetch_rest_response with request" + restStepRequest);
        return this.httpClient.execute(restStepRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<TestStepRecorderDTO> index(TestStepSpecificationsBuilder builder, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("GET /os_recorder/v2/test_steps ");
        Specification<TestStep> spec = builder.build();
        Page<TestStep> testStep = this.service.findAll(spec, pageable);
        List<TestStepDTO> testDataDTOS =
                mapper.mapDTOs(testStep.getContent());
        List<TestStepRecorderDTO> testStepRecorderDTOS = testStepRecorderMapper.mapDTOs(testDataDTOS);
        return new PageImpl<>(testStepRecorderDTOS, pageable, testStep.getTotalElements());
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void destroy(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        log.debug("DELETE /os_recorder/v2/test_steps with id::" + id);
        TestStep testStep = this.service.find(id);
        service.destroy(testStep);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TestStepRecorderDTO update(@PathVariable(value = "id") Long id, @RequestBody TestStepRecorderRequest request) throws MitaException {
        log.debug("PUT /os_recorder/test_steps with id::" + id + " request::" + request);
        TestStep testStep = this.service.find(id);
        TestStepRecorderDTO testStepRecorderDTO;
        TestStepDTO testStepDTO;
        if(request.getType() == TestStepType.NLP_TEXT) {
            request.setType(TestStepType.ACTION_TEXT);
        }
        if(request.getIsStepRecorder() && request.getUiIdentifierRequest() != null){
            log.info("Update Test step from Step recorder");
            UiIdentifierRequest uiIdentifierRequest = request.getUiIdentifierRequest();
            ElementRequest elementRequest = uiIdentifierMapper.mapRequest(uiIdentifierRequest);
            Element element = uiIdentifierMapper.map(elementRequest);
            element = elementService.createUiIdentifierFromRecorder(element);
            request.getDataMap().setUiIdentifier(element.getName());


            TestStepRequest testStepRequest = testStepRecorderMapper.mapRequest(request);
            mapper.merge(testStepRequest, testStep);
            testStep.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            if (testStep.getParentId() != null)
                testStep.setDisabled(this.service.find(testStep.getParentId()).getDisabled());
            testStep = this.service.update(testStep);
            testStepDTO = mapper.mapDTO(testStep);

            testStepRecorderDTO = testStepRecorderMapper.mapDTO(testStepDTO);
            testStepRecorderDTO.setUiIdentifierDTO(uiIdentifierMapper.mapDTO(uiIdentifierMapper.map(element)));
        } else{
            TestStepRequest testStepRequest = testStepRecorderMapper.mapRequest(request);
            testStep = this.service.update(mapper.map(testStepRequest));
            testStepDTO = mapper.mapDTO(testStep);
            testStepRecorderDTO = testStepRecorderMapper.mapDTO(testStepDTO);
        }
        return testStepRecorderDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestStepRecorderDTO create(@RequestBody TestStepRecorderRequest request) throws MitaException {
        log.debug("POST /os_recorder/test_steps with request::" + request);
        TestStepRecorderDTO testStepRecorderDTO;
        TestStepDTO testStepDTO;
        if(request.getType() == TestStepType.NLP_TEXT) {
            request.setType(TestStepType.ACTION_TEXT);
        }
        if(request.getIsStepRecorder() && request.getUiIdentifierRequest() != null){
            log.info("Create Test step from Step recorder");
            UiIdentifierRequest uiIdentifierRequest = request.getUiIdentifierRequest();
            ElementRequest elementRequest = uiIdentifierMapper.mapRequest(uiIdentifierRequest);
            Element element = uiIdentifierMapper.map(elementRequest);
            element = elementService.createUiIdentifierFromRecorder(element);
            request.getDataMap().setUiIdentifier(element.getName());

            TestStepRequest testStepRequest = testStepRecorderMapper.mapRequest(request);
            TestStep testStep = mapper.map(testStepRequest);
            testStep.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            if(testStep.getPosition() == null) {
                Optional<TestStep> lastTestStep = service.findTopByTestCaseIdOrderByPositionDesc(testStep.getTestCaseId());
                int position = lastTestStep.isPresent() ? lastTestStep.get().getPosition()+1 : 0;
                testStep.setPosition(position);
            }
            if (testStep.getParentId() != null)
                testStep.setDisabled(this.service.find(testStep.getParentId()).getDisabled());
            testStep = service.create(testStep);
            testStepDTO = mapper.mapDTO(testStep);

            testStepRecorderDTO = testStepRecorderMapper.mapDTO(testStepDTO);
            testStepRecorderDTO.setUiIdentifierDTO(uiIdentifierMapper.mapDTO(uiIdentifierMapper.map(element)));
        } else{
            TestStepRequest testStepRequest = testStepRecorderMapper.mapRequest(request);
            TestStep testStep = mapper.map(testStepRequest);
            if(testStep.getPosition() == null) {
                Optional<TestStep> lastTestStep = service.findTopByTestCaseIdOrderByPositionDesc(testStep.getTestCaseId());
                int position = lastTestStep.isPresent() ? lastTestStep.get().getPosition()+1 : 0;
                testStep.setPosition(position);
            }
            testStep = service.create(testStep);
            testStepDTO = mapper.mapDTO(testStep);
            testStepRecorderDTO = testStepRecorderMapper.mapDTO(testStepDTO);
        }
        if(testStepRecorderDTO.getUiIdentifierDTO() != null) {
            testStepRecorderDTO.getDataMap().setUiIdentifier(testStepRecorderDTO.getUiIdentifierDTO().getName());
        }
        return testStepRecorderDTO;
    }

    @DeleteMapping(value = "/bulk_delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void bulkDelete(@RequestParam(value = "ids[]") Long[] ids) throws ResourceNotFoundException {
        log.debug("DELETE /os_recorder/test_steps/bulk_update_properties with ids::" + Arrays.toString(ids));
        for (Long id : ids) {
            TestStep step = this.service.find(id);
            this.service.destroy(step);
        }
    }

    @PutMapping(value = "/bulk_update_properties")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void bulkUpdateProperties(@RequestParam(value = "ids[]") Long[] ids,
                                     @RequestParam(value = "waitTime", required = false) Integer waitTime,
                                     @RequestParam(value = "priority", required = false) TestStepPriority testStepPriority,
                                     @RequestParam(value = "disabled", required = false) Boolean disabled,
                                     @RequestParam(value = "ignoreStepResult", required = false) Boolean ignoreStepResult,
                                     @RequestParam(value = "visualEnabled", required = false) Boolean visualEnabled) {

        log.debug("PUT /os_recorder/test_steps/bulk_update_properties with ids::" + Arrays.toString(ids) + " waitTime ::"
                + waitTime + " priority ::" + testStepPriority + " disabled ::" + disabled +" ignoreStepResult ::" +ignoreStepResult);
        this.service.bulkUpdateProperties(ids, testStepPriority, waitTime, disabled, ignoreStepResult,visualEnabled);
    }

    @PutMapping(value = "/bulk_update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TestStepRecorderDTO> bulkUpdate( @RequestBody List<TestStepRecorderRequest> requests) throws MitaException {
        log.debug("PUT /os_recorder/bulk_update");
        List<TestStepRecorderDTO> testStepRecorderDTOS = new ArrayList<>();
        for(TestStepRecorderRequest request : requests) {
            TestStep testStep = this.service.find(request.getId());
            TestStepRecorderDTO testStepRecorderDTO;
            TestStepDTO testStepDTO;
            if (request.getType() == TestStepType.NLP_TEXT) {
                request.setType(TestStepType.ACTION_TEXT);
            }
            if (request.getIsStepRecorder() && request.getUiIdentifierRequest() != null) {
                log.info("Update Test step from Step recorder");
                UiIdentifierRequest uiIdentifierRequest = request.getUiIdentifierRequest();
                ElementRequest elementRequest = uiIdentifierMapper.mapRequest(uiIdentifierRequest);
                Element element = uiIdentifierMapper.map(elementRequest);
                element = elementService.createUiIdentifierFromRecorder(element);
                request.getDataMap().setUiIdentifier(element.getName());


                TestStepRequest testStepRequest = testStepRecorderMapper.mapRequest(request);
                mapper.merge(testStepRequest, testStep);
                testStep.setUpdatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

                if (testStep.getParentId() != null)
                    testStep.setDisabled(this.service.find(testStep.getParentId()).getDisabled());
                testStep = this.service.update(testStep);
                testStepDTO = mapper.mapDTO(testStep);

                testStepRecorderDTO = testStepRecorderMapper.mapDTO(testStepDTO);
                testStepRecorderDTO.setUiIdentifierDTO(uiIdentifierMapper.mapDTO(uiIdentifierMapper.map(element)));
            } else {
                TestStepRequest testStepRequest = testStepRecorderMapper.mapRequest(request);
                testStep = this.service.update(mapper.map(testStepRequest));
                testStepDTO = mapper.mapDTO(testStep);
                testStepRecorderDTO = testStepRecorderMapper.mapDTO(testStepDTO);
            }
            testStepRecorderDTOS.add(testStepRecorderDTO);
        }
        return testStepRecorderDTOS;
    }
}
