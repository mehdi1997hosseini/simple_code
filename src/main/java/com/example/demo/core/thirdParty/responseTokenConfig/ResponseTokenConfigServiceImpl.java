package com.example.demo.core.thirdParty.responseTokenConfig;

import com.example.demo.core.service.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ResponseTokenConfigServiceImpl extends BasicServiceImpl<ResponseTokenConfigEntity,String,ResponseTokenConfigRepository> implements ResponseTokenConfigService {

    public ResponseTokenConfigServiceImpl(ResponseTokenConfigRepository repository) {
        super(repository);
    }

}
