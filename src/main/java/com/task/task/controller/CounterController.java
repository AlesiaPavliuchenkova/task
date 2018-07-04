package com.task.task.controller;

import com.task.task.entity.Body;
import com.task.task.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CounterController {
    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(
            value = "/count",
            method = RequestMethod.POST,
            produces = { APPLICATION_JSON_VALUE },
            consumes = { APPLICATION_JSON_VALUE })
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> count(@Valid @RequestBody Body body){
        try {
            Map<String, Long> words = counterService.countWordsEntries(body.getData());
            return new ResponseEntity<>(words, OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Bad input data", BAD_REQUEST);
        }
    }
}
