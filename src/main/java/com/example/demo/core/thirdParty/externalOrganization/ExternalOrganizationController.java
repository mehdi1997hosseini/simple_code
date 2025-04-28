package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.controller.BasicController;
import com.example.demo.core.logger.annotation.LoggableRequestResponseApiInDB;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("external-organization/")
public class ExternalOrganizationController extends BasicController<ExternalOrganizationEntity, String, ExternalOrganizationService> {

    private final ExternalOrganizationTokenService externalOrganizationTokenService;

    public ExternalOrganizationController(ExternalOrganizationService service, ExternalOrganizationTokenService externalOrganizationTokenService) {
        super(service);
        this.externalOrganizationTokenService = externalOrganizationTokenService;
    }

    @PostMapping("save")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> save(@RequestBody @NotNull ExternalOrganizationDto externalOrganization) {
        return new ResponseEntity<>(service.saveOrUpdate(externalOrganization), HttpStatus.CREATED);
    }

    @PostMapping("update")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> update(@RequestBody @NotNull ExternalOrganizationDto externalOrganization) {
        return new ResponseEntity<>(service.saveOrUpdate(externalOrganization), HttpStatus.OK);
    }

    @PostMapping("refresh-manually")
    @LoggableRequestResponseApiInDB
    public ResponseEntity<?> refreshManually(@RequestParam @NotBlank String organizationName) {
        externalOrganizationTokenService.refreshManuallyExternalOrganization(organizationName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
