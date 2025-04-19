package com.example.demo.app.Test;

import com.example.demo.core.controller.BasicController;
import com.example.demo.core.utility.validation.nin.NIN;
import com.example.demo.core.utility.validation.nin.NIN_TypeVld;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test/")
public class TestController extends BasicController<TestEntity,Long,TestService> {

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

    @GetMapping("testNationalCode/")
    public ResponseEntity<?> testNationalCode(@RequestParam @NIN(NIN_type = NIN_TypeVld.SSN_PERSON) String nationalCode) {
        return ResponseEntity.ok("NationalCode: " + nationalCode);
    }

    @PostMapping("getTokenTest")
    public ResponseEntity<Map<String,Object>> getTokenTest(@RequestBody TokenRequestTest body) {
        Map<String,Object> response = new HashMap<>();
        response.put("access_token","mehdiHosseini");
        response.put("expires_in",2);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
