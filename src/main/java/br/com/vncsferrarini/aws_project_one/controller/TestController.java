package br.com.vncsferrarini.aws_project_one.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Test controller - name: {}", name);
        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/sum/{valueOne}/{valueTwo}")
    public ResponseEntity<?> sum(@PathVariable Double valueOne, @PathVariable Double valueTwo) {
        LOG.info("Adding values: {} + {}", valueOne, valueTwo);
        return ResponseEntity.ok("Result: " + (valueOne + valueTwo));
    }


}
