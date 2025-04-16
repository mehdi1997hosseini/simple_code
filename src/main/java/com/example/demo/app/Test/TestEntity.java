package com.example.demo.app.Test;

import com.example.demo.core.entity.BasicEntityAudit;
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
public class TestEntity extends BasicEntityAudit<Long> {

    private String testName;
    private String testDescription;

}
