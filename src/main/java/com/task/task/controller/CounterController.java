package com.task.task.controller;

import com.task.task.entity.Body;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CounterController {

    @RequestMapping(
            value = "/count",
            method = RequestMethod.POST,
            produces="application/json")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, Integer>> count(@RequestBody Body body){
        try {
            Map<String, Integer> words = new HashMap<>();
            body.getData().forEach(word -> {
                Integer count = words.get(word);
                words.put(word, (count == null) ? 1 : count + 1);
            });
            return new ResponseEntity<>(words, HttpStatus.OK);
        } catch (Exception ex) {
            //change "Return some error code in case of any issues with a request."
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }
    }
}
