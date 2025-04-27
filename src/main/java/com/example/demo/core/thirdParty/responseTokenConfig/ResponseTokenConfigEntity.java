package com.example.demo.core.thirdParty.responseTokenConfig;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.utility.TimeUnitType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_RESPONSE_TOKEN_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseTokenConfigEntity extends BasicEntity<String> {
    private String tokenFieldName;
    private String expireTimeFieldName;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIME_UNIT_TYPE", nullable = false)
    private TimeUnitType timeUnitType;

}
