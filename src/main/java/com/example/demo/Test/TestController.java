package com.example.demo.Test;

import com.example.demo.core.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/")
public class TestController extends BaseController<TestEntity,String,TestService> {

    public TestController(TestService service) {
        super(service);
    }

    @PostMapping("save")
    public ResponseEntity<?> saveTest(@RequestBody TestEntity testEntity) {
        service.save(testEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getAllTestByTestName/")
    public ResponseEntity<?> getAllTestByTestName(@RequestParam String testName) {
        return new ResponseEntity<>(service.getTestByTestName(testName), HttpStatus.OK);
    }


}
