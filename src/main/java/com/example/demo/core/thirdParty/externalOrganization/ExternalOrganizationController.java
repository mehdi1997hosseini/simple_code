package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.controller.BasicController;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("external-organization/")
public class ExternalOrganizationController extends BasicController<ExternalOrganizationEntity, String, ExternalOrganizationService> {
    public ExternalOrganizationController(ExternalOrganizationService service) {
        super(service);
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody ExternalOrganizationDto externalOrganization) {
        return new ResponseEntity<>(service.create(externalOrganization), HttpStatus.CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody ExternalOrganizationDto externalOrganization) {
        return null;
    }
}
