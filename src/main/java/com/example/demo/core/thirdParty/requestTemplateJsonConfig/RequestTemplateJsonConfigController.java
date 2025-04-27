package com.example.demo.core.thirdParty.requestTemplateJsonConfig;

import com.example.demo.core.controller.BasicController;
import com.example.demo.core.thirdParty.requestTemplateJsonConfig.dto.RequestTemplateJsonConfigDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("request-template-json-config/")
public class RequestTemplateJsonConfigController extends BasicController<RequestTemplateJsonConfigEntity, String, RequestTemplateJsonConfigService> {

    public RequestTemplateJsonConfigController(RequestTemplateJsonConfigService service) {
        super(service);
    }

    @PostMapping("add")
    public ResponseEntity<?> save(@RequestBody RequestTemplateJsonConfigDto dto) {
        service.save(dto);
        return ResponseEntity.ok().build();
    }

}
