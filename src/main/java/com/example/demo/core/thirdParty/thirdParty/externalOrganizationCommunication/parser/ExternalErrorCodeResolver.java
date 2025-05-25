package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.parser;

/**
 * Interface defining a contract for resolving external server error codes.
 * <p>
 * This interface provides a standard structure for classes responsible for
 * interpreting and resolving error codes received from external servers.
 * Implementing classes determine the appropriate error message to display
 * and the handling strategy based on the parsed error code from the server.
 * <p>
 * It helps unify the error handling mechanism by mapping raw server error codes
 * to meaningful messages and handling logic within the application.
 * <p>
 * <h2>Usage example:</h2>
 * <pre>{@code
 * // Example resolver implementation for an external organization (e.g., Bank)
 * public class BankErrorCodeResolver implements ExternalErrorCodeResolver {
 *     private final String {The name of the key field where the code or value of the ID is located in};
 *      .
 *      .
 *      .
 *
 *     public BankErrorCodeResolver(String {The name of the key field where the code or value of the ID is located in}) {
 *         this.{The name of the key field where the code or value of the ID is located in} = {The name of the key field where the code or value of the ID is located in};
 *         .
 *         .
 *         .
 *     }
 *     .
 *     .
 *     .
 *
 *     @Override
 *     public String errorCode() {
 *         return {The name of the key field where the code or value of the ID is located in};
 *     }
 * }
 *
 * // Example usage inside ResponseCommunicationErrorHandler:
 * public class ResponseCommunicationErrorHandler {
 *
 *     // Register error handlers for external organizations
 *     public void registerErrorsMap() {
 *         registerErrorsMap(ExternalOrganizationName.TEST,
 *             List.of(new ConstantUnifiedServerFailureResponseHandler("400", {"other field or property declared on class "} );
 *     }
 *
 *     // Method to register handlers (simplified)
 *     private void registerErrorsMap(ExternalOrganizationName orgName, List<ExternalErrorCodeResolver> handlers) {
 *         // registration logic here...
 *     }
 * }
 * }</pre>
 *
 * ------------------------------------------------------------------------------------
 *
 * اینترفیس تعریف‌کننده قراردادی برای تشخیص کدهای خطای سرور خارجی است.
 * <p>
 * این اینترفیس ساختار استانداردی را برای کلاس‌هایی فراهم می‌کند که مسئول
 * تفسیر و حل کدهای خطای دریافتی از سرورهای خارجی هستند.
 * کلاس‌های پیاده‌ساز بر اساس کد خطای پارس‌شده، پیغام مناسب جهت نمایش و
 * روش هندل خطا را تعیین می‌کنند.
 * <p>
 * این روش باعث یکپارچه‌سازی مکانیزم مدیریت خطا با نگاشت کدهای خطای خام
 * سرور به پیام‌ها و منطق قابل فهم در داخل اپلیکیشن می‌شود.
 * <p>
 * <h2>نمونه استفاده:</h2>
 * <pre>{@code
 * // نمونه پیاده‌سازی برای یک سازمان خارجی (مثلاً بانک)
 * public class BankErrorCodeResolver implements ExternalErrorCodeResolver {
 *     private final String {نام فیلد کلیدی که کد یا مقدار شناسه خا در ان قرار دارد} ;
 *
 *     public BankErrorCodeResolver(String {نام فیلد کلیدی که کد یا مقدار شناسه خا در ان قرار دارد}) {
 *         this.{نام فیلد کلیدی که کد یا مقدار شناسه خا در ان قرار دارد} = {نام فیلد کلیدی که کد یا مقدار شناسه خا در ان قرار دارد};
 *     }
 *
 *     @Override
 *     public String errorCode() {
 *         return {نام فیلد کلیدی که کد یا مقدار شناسه خا در ان قرار دارد};
 *     }
 * }
 *
 * // نمونه استفاده در کلاس ResponseCommunicationErrorHandler:
 * public class ResponseCommunicationErrorHandler {
 *
 *     // ثبت هندلرهای خطا برای سازمان‌های خارجی
 *     public void registerErrorsMap() {
 *         registerErrorsMap(ExternalOrganizationName.TEST,
 *             List.of(new ConstantUnifiedServerFailureResponseHandler("400", {"field یا property های دیگری که در کلاس تعریف شده اند. "} );
 *     }
 *
 *     // متد ثبت هندلرها (به صورت ساده شده)
 *     private void registerErrorsMap(ExternalOrganizationName orgName, List<ExternalErrorCodeResolver> handlers) {
 *         // منطق ثبت اینجا...
 *     }
 * }
 * }</pre>
 *
 * @author mehdi.hosseini
 */
public interface ExternalErrorCodeResolver {

    String errorCode();
}
