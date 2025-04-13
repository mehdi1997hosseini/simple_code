package com.example.demo.core.utility.validation.nin;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NINVldImpl implements ConstraintValidator<NIN, String> {
    private NIN_TypeVld nINType;
    private String message;

    @Override
    public void initialize(NIN NIN) {
        this.nINType = NIN.NIN_type();
        this.message = NIN.message();
    }

    @Override
    public boolean isValid(String nin, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (nin == null || nin.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }

        switch (nINType) {
            case SSN_IR:
                isValid = checkSSN_IR(nin);
                break;
            case FIDA:
                isValid = check_FIDA(nin);
                break;
            case PASSPORT_NO:
                isValid = check_PASSPORT_NO(nin);
                break;
        }

        return isValid;
    }

    public boolean checkSSN_IR(String ssn) {
        //check length
        if (ssn.length() != 10)
            return false;

        char[] breakNationalCode = ssn.toCharArray();
        int length = breakNationalCode.length;
        int result = 0;
        int numberIndex = 0;
        for (int start = 0; start < length - 1; start++) {
            numberIndex = length - start;
            result += (Character.getNumericValue(breakNationalCode[start]) * numberIndex);
        }
        int numberTotal = result % 11;
        if (numberTotal > 2) {
            int number = 11 - numberTotal;
            return number == Character.getNumericValue(breakNationalCode[9]);
        } else return numberTotal == Character.getNumericValue(breakNationalCode[9]);
    }

    public boolean check_PASSPORT_NO(String passportNO) {
        return false;
    }

    private boolean check_FIDA(String fidaCode) {
        return false;
    }


}