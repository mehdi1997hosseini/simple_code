package com.example.demo.Test;

import com.example.demo.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends BaseRepository<TestEntity, String> {
    List<TestEntity> findAllByTestName(String testName);
}
