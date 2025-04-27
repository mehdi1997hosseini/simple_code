package com.example.demo.core.thirdParty.responseTokenConfig;

import com.example.demo.core.controller.BasicController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("response-token-config/")
public class ResponseTokenConfigController extends BasicController<ResponseTokenConfigEntity,String,ResponseTokenConfigService> {
    public ResponseTokenConfigController(ResponseTokenConfigService service) {
        super(service);
    }
}
