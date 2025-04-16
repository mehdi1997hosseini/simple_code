package com.example.demo.app.Test;

import com.example.demo.core.service.BasicService;

public interface TestService extends BasicService<TestEntity, Long> {
    public String getTestByTestName(String testName);
}
