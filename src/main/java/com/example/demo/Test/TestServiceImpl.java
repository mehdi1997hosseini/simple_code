package com.example.demo.Test;

import com.example.demo.core.exceptionHandler.ResponseLangType;
import com.example.demo.core.exceptionHandler.PssNoteRunTimeException;
import com.example.demo.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl extends BaseServiceImpl<TestEntity, String, TestRepository> implements TestService {

    public TestServiceImpl(TestRepository repository) {
        super(repository);
    }

    public String getTestByTestName(String testName) {
        List<TestEntity> allByTestName = repository.findAllByTestName(testName);
        if (allByTestName.isEmpty()) {
            throw new PssNoteRunTimeException(TestException.BY_TEST_NAME_NOT_FOUND, ResponseLangType.FA, null ,testName);
        }
        return allByTestName.toString();
    }

}
