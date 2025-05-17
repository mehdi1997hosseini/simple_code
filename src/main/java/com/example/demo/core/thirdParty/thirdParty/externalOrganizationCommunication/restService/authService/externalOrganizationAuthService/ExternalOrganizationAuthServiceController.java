package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService;

import ir.smarttrustco.pssnote.core.controller.BasicController;
import ir.smarttrustco.pssnote.core.logger.annotation.LoggableRequestResponseApiInDB;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("external-organization-auth-service-config/")
public class ExternalOrganizationAuthServiceController extends BasicController<ExternalOrganizationAuthServiceEntity, String, ExternalOrganizationAuthService> {

    public ExternalOrganizationAuthServiceController(ExternalOrganizationAuthService service) {
        super(service);
    }

    @PostMapping("save")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> save(@RequestBody @NotNull ExternalOrganizationAuthServiceDto externalOrganization) {
        return new ResponseEntity<>(service.saveOrUpdate(externalOrganization), HttpStatus.CREATED);
    }

    @PostMapping("update")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> update(@RequestBody @NotNull ExternalOrganizationAuthServiceDto externalOrganization) {
        return new ResponseEntity<>(service.saveOrUpdate(externalOrganization), HttpStatus.OK);
    }

    @PostMapping("refresh-manually")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> refreshManually(@RequestParam @NotBlank String organizationName) {
        service.refreshManuallyExternalOrganization(organizationName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("shot-down-manually")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> shotDownManually(@RequestParam @NotBlank String organizationName) {
        service.shotDownManuallyExternalOrganizationForGetToken(organizationName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
