package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.mapper;

import com.example.demo.core.utility.TimeUnitType;
import org.mapstruct.Named;


public class ExternalOrganizationAuthMapHelper {

    @Named("stringToTimeUnitType")
    public static TimeUnitType stringToTimeUnitType(String value) {
        return value != null ? TimeUnitType.fromString(value) : null;
    }

    @Named("timeUnitTypeToString")
    public static String timeUnitTypeToString(TimeUnitType value) {
        return value == null ? null : value.name();
    }


}
