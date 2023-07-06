package com.mita.agent.controllers;

import com.mita.agent.utils.PathUtil;

import com.mita.agent.utils.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin
@RequestMapping(path = "/deleteFile")
public class DeploymentController {

    @GetMapping
    ResponseEntity<Object> deleteFile() {
        String filePath = PathUtil.getInstance().getConfigPath()+ File.separator+"agent.properties";

        Path path = Paths.get(filePath);

        if (Files.exists(path)) {
            try {
                Files.delete(path);
                return new ResponseEntity<>(new ResponseObject(200, "Success", "File Deleted Successfully"), HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(new ResponseObject(500, "Fail", "Unable to delete the file: " + e.getMessage()), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new ResponseObject(401, "Fail", "File does not exist."), HttpStatus.OK);
        }
    }
}