package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.smarttrustco.pssnote.core.exceptionHandler.exception.AppRunTimeException;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.errorHandler.ResponseCommunicationErrorHandler;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ResponseType;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.exception.ExternalOrganizationException;
import org.springframework.http.HttpStatus;

/**
 * ==============================================
 * <h3>External Organization Communication Response Parser</h3>
 * <h3> تجزیه پاسخ ارتباطی با سازمان‌های خارجی</h3>
 * ==============================================
 * <p>
 *
 * @Description :
 * This interface provides a common structure to parse and classify responses
 * received from external organization APIs. It supports both successful and
 * failed responses and transforms them into a unified format (`ExternalUnifiedResponse`).
 * <p>
 * این اینترفیس یک ساختار یکپارچه برای پردازش پاسخ‌های دریافتی از سازمان‌های بیرونی فراهم می‌کند.
 * پاسخ‌های موفق و ناموفق را تشخیص داده، تبدیل کرده و در قالبی استاندارد (ExternalUnifiedResponse) ارائه می‌دهد.
 * <p>
 * اهداف:
 * - تجزیه‌ی پاسخ دریافتی به دو بخش موفق و ناموفق
 * - ایجاد خروجی استاندارد برای مدیریت بهتر در لایه‌ی سرویسی
 * - تفکیک منطق تبدیل پاسخ‌ها از منطق ارتباطات
 * @author mehdi.hosseini
 */
public interface ExternalOrganizationCommunicationResponseParser {

    /**
     * Shared ObjectMapper instance for JSON deserialization.
     * <p>
     * نمونه‌ی مشترک برای تبدیل رشته‌های JSON به اشیاء
     */
    ObjectMapper objectMapper = new ObjectMapper();

    ExternalOrganizationName getOrganizationName();

    /**
     * Parses the response string from the external service.
     * Attempts to deserialize it first as a successful response,
     * and if it fails, tries to parse it as a failure response object.
     * In both failure cases, an exception with a unified response is thrown.
     * <p>
     * این متد رشته‌ی پاسخ را از سرویس بیرونی پردازش می‌کند.
     * ابتدا سعی می‌کند آن را به‌عنوان پاسخ موفق بخواند.
     * اگر شکست خورد، آن را به‌عنوان پاسخ خطا تحلیل کرده و در نهایت
     * اگر همچنان شکست بخورد، یک خطای عمومی پرتاب می‌کند.
     * <p>
     *
     * @param responseBody          JSON string response / پاسخ به صورت رشته‌ی JSON
     * @param responseAcceptObject  Class type of successful response / کلاس پاسخ موفق
     * @param responseFailureObject Class type of failure response / کلاس پاسخ ناموفق
     * @param <A>                   نوع داده‌ی موفق
     * @param <F>                   نوع داده‌ی خطا (باید از ExternalUnifiedResponseFailed ارث ببرد)
     * @return ExternalUnifiedResponse پاسخ تحلیل‌شده به صورت یکپارچه
     * @throws AppRunTimeException در صورت بروز خطا در تبدیل یا تحلیل پاسخ
     */
    default <A, F extends ExternalUnifiedResponseFailed> ExternalUnifiedResponse parseResponse(String responseBody, Class<A> responseAcceptObject, Class<F> responseFailureObject) {
        try {
            A responseSuccess = objectMapper.readValue(responseBody, responseAcceptObject);
            return new ExternalUnifiedResponse(true, getOrganizationName().getTitle(), responseSuccess, ResponseType.ACCEPTED);
        } catch (Exception e1) {
            throw new AppRunTimeException(ExternalOrganizationException.EXTERNAL_ORGANIZATION_SERVER_ERROR, handleFailureResponse(responseBody, responseFailureObject).toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private <F extends ExternalUnifiedResponseFailed> ExternalUnifiedResponse handleFailureResponse(String responseBody, Class<F> responseFailureObject) {
        try {
            F failureResponseObject = objectMapper.readValue(responseBody, responseFailureObject);
            return new ExternalUnifiedResponse(false, getOrganizationName().getTitle(), ResponseCommunicationErrorHandler.getFailedResponse(getOrganizationName(), failureResponseObject.getErrorCode()), ResponseType.REJECTED);
        } catch (Exception e2) {
            return new ExternalUnifiedResponse(false, getOrganizationName().getTitle(), "Request error: " + e2.getMessage(), ResponseType.ERROR);
        }
    }

}
