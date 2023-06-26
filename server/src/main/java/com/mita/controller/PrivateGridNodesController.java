

package com.mita.controller;

import com.mita.dto.PrivateGridNodeDTO;
import com.mita.mapper.PrivateGridNodeMapper;
import com.mita.model.PrivateGridNode;
import com.mita.service.PrivateGridService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/grid_nodes")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class PrivateGridNodesController {
  private final PrivateGridService privateGridService;
  private final PrivateGridNodeMapper mapper;

  @RequestMapping(method = RequestMethod.GET)
  public List<PrivateGridNodeDTO> index() {
    List<PrivateGridNode> nodes = privateGridService.findAll();
    return mapper.mapList(nodes);
  }

}
