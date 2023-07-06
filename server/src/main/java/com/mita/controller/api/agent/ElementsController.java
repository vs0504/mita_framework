

package com.mita.controller.api.agent;

import com.mita.exception.MitaException;
import com.mita.service.ElementService;
import com.mita.web.request.ElementRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController(value = "agentElementsController")
@RequestMapping(path = "/api/agents/elements")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElementsController {
  private final ElementService elementService;

  @RequestMapping(path = "/{versionId}/{name}", method = RequestMethod.PUT)
  public ResponseEntity<String> update(@PathVariable(value = "name") String name,
                                       @RequestBody ElementRequest elementRequest
  ) throws MitaException, SQLException {
    elementService.updateByName(name, elementRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
