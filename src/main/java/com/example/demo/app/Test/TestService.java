package com.example.demo.app.Test;

import com.example.demo.core.service.BaseService;

public interface TestService extends BaseService<TestEntity, String> {
    public String getTestByTestName(String testName);
}
