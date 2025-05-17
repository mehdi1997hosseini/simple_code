package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig;

import ir.smarttrustco.pssnote.core.entity.BasicEntity;
import ir.smarttrustco.pssnote.core.utility.TimeUnitType;
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
    @Column(name = "TOKEN_FIELD_NAME")
    private String tokenFieldName;
    @Column(name = "EXPIRE_TIME_FIELD_NAME")
    private String expireTimeFieldName;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIME_UNIT_TYPE", nullable = false)
    private TimeUnitType timeUnitType;
    @Column(name = "EXPIRES_IN")
    private Integer expiresIn;

}
