package com.example.demo.core.thirdParty.requestTokenConfig;

import com.example.demo.core.controller.BasicController;
import com.example.demo.core.thirdParty.requestTokenConfig.dto.RequestTokenConfigDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("request-token-config/")
public class RequestTokenConfigController extends BasicController<RequestTokenConfigEntity,String,RequestTokenConfigService> {

    public RequestTokenConfigController(RequestTokenConfigService service) {
        super(service);
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody RequestTokenConfigDto dto) {
        service.save(dto);
        return ResponseEntity.ok().build();
    }

}
