package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.parser;


/**
 * Interface representing a unified error response from an external server.
 * <p>
 * This interface provides a common contract for classes that represent error responses
 * coming from external target servers. Implementing classes must specify
 * an error code that identifies the type of server-side failure being handled.
 * <p>
 * It acts as a standard structure ensuring that any error received from
 * the external server can be handled uniformly in the system.
 * <p>
 * <h2>Usage example:</h2>
 * <pre>{@code
 * public class BankServerErrorResponse implements ExternalUnifiedServerFailureResponse {
 *     private final String errorCode;
 *
 *     public BankServerErrorResponse(String errorCode) {
 *         this.errorCode = errorCode;
 *     }
 *
 *     @Override
 *     public String getErrorCode() {
 *         return errorCode;
 *     }
 * }
 * }</pre>
 * <p>
 * ------------------------------------------------------------------------------------
 * <p>
 * اینترفیس نمایانگر پاسخ خطای یکپارچه از یک سرور خارجی است.
 * <p>
 * این اینترفیس قراردادی مشترک برای کلاس‌هایی است که نماینده پاسخ‌های خطا
 * دریافت شده از سرورهای خارجی هستند. کلاس‌های پیاده‌ساز باید کد خطا را مشخص کنند
 * که نوع خطای سمت سرور مقصد را نشان می‌دهد.
 * <p>
 * این ساختار استاندارد تضمین می‌کند که هر خطای دریافتی از سرور خارجی
 * به صورت یکنواخت در سیستم مدیریت شود.
 * <p>
 * <h2>نمونه استفاده:</h2>
 * <pre>{@code
 * public class BankServerErrorResponse implements ExternalUnifiedServerFailureResponse {
 *     private final String errorCode;
 *
 *     public BankServerErrorResponse(String errorCode) {
 *         this.errorCode = errorCode;
 *     }
 *
 *     @Override
 *     public String getErrorCode() {
 *         return errorCode;
 *     }
 * }
 * }</pre>
 *
 * @author mehdi.hosseini
 */
public interface ExternalUnifiedServerFailureResponse {
    /**
     * Returns the error code representing the type of server-side failure.
     * <p>
     * This code is used to identify and handle specific error cases
     * returned from the external server.
     *
     * @return the error code string received from the external server
     * <p>
     * <p> دریافت کد خطا (Error Code) که نشان‌دهنده نوع خطا از سمت سرور مقصد است.</p>
     * <p>
     * <p> این مقدار برای شناسایی و هندل کردن دقیق نوع خطا استفاده می‌شود.</p>
     * <p>@return رشته‌ای شامل کد خطا که سرور مقصد ارسال کرده است</p>
     */
    String getErrorCode();
}
