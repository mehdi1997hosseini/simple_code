package com.example.demo.app.Test;

import com.example.demo.core.repository.BasicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends BasicRepository<TestEntity, Long> {
    List<TestEntity> findAllByTestName(String testName);
}
