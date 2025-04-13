package com.example.demo.Test;

import com.example.demo.core.service.BaseService;

public interface TestService extends BaseService<TestEntity, String> {
    public String getTestByTestName(String testName);
}
