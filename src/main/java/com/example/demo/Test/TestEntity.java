package com.example.demo.Test;

import com.example.demo.core.entity.BaseEntityAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tbl_test")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity extends BaseEntityAudit<String> {

    private String testName;
    private String testDescription;

}
