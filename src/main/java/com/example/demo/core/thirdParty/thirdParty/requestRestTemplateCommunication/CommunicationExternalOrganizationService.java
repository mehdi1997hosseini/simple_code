package com.example.demo.core.thirdParty.thirdParty.requestRestTemplateCommunication;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalUnifiedResponse;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalUnifiedServerFailureResponse;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;

import java.util.List;

/**
 * ==============================================
 * <h3>Communication External Organization Service</h3>
 * <h3>سرویس ارتباط با سازمان‌های خارجی</h3>
 * ==============================================
 * <p>
 *
 * @Description :
 * This interface provides a generic contract for sending HTTP (or other protocol-based)
 * requests to external organizations and receiving typed responses.
 * It abstracts the communication layer and standardizes the way services
 * interact with external APIs.
 * <p>
 * این اینترفیس مسئول ارسال درخواست‌ها به سازمان‌های بیرونی است و پاسخ‌های برگشتی را به
 * صورت تایپ‌شده مدیریت می‌کند. هدف این لایه، جداسازی منطق ارتباط با سیستم‌های بیرونی و
 * ایجاد یک ساختار استاندارد برای فراخوانی سرویس‌های خارجی است.
 * <p>
 * مزایای این طراحی:
 * - پشتیبانی از پاسخ موفق و ناموفق با انواع متفاوت
 * - استفاده عمومی برای همه انواع درخواست‌ها و پاسخ‌ها (Generic)
 * - کاهش پیچیدگی در سطح سرویس‌های کاربردی
 */
public interface CommunicationExternalOrganizationService {

    /**
     * <p>Send a request to an external organization and receive the expected typed response.</p>
     * <p> ارسال درخواست به سازمان بیرونی و دریافت پاسخ مورد انتظار</p>
     * <p>
     *
     * @param requestBody                          The request body to send / بدنه‌ی درخواستی برای ارسال
     * @param externalOrganizationApiServiceEntity The target external API metadata / اطلاعات سرویس خارجی
     * @param objectResponseType                   The class type of the expected response / نوع کلاس پاسخ مورد انتظار
     * @param <T>                                  Type of request body / نوع داده ارسالی
     * @param <R>                                  Type of successful response / نوع پاسخ موفق
     * @return The mapped response object / شیء پاسخ‌شده بازگردانده‌شده
     */
    <T, R> R sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType);

    /**
     * <p>Send a request to an external organization, handling both success and failure response types.</p>
     * <p>ارسال درخواست به سازمان بیرونی با امکان مدیریت پاسخ موفق و ناموفق</p>
     * <p>
     *
     * @param requestBody                          The request body / بدنه‌ی درخواستی
     * @param externalOrganizationApiServiceEntity API metadata for the target service / متادیتای سرویس خارجی
     * @param responseAccept                       The expected class of success response / کلاس پاسخ موفق
     * @param responseFailed                       The expected class of failure response / کلاس پاسخ ناموفق
     * @param <T>                                  Type of request body / نوع داده ورودی
     * @param <R>                                  Type of accepted response / نوع پاسخ موفق
     * @param <F>                                  Type of failed response (must extend ExternalUnifiedResponseFailed) / نوع پاسخ ناموفق
     * @return Either success or failure response / شیء پاسخ موفق یا ناموفق
     */
    <T, R, F extends ExternalUnifiedServerFailureResponse> ExternalUnifiedResponse sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, Class<F> responseFailed);
    /**
     * <p>Send a request to an external organization, handling success and multiple possible failure response types.</p>
     * <p>ارسال درخواست به سازمان بیرونی با امکان مدیریت پاسخ موفق و چندین مدل پاسخ ناموفق</p>
     * <p>
     *
     * @param requestBody                          The request body / بدنه‌ی درخواستی
     * @param externalOrganizationApiServiceEntity API metadata for the target service / متادیتای سرویس خارجی
     * @param responseAccept                       The expected class of success response / کلاس پاسخ موفق
     * @param responseFailed                       A list of possible failure response classes / لیستی از کلاس‌های پاسخ ناموفق ممکن
     * @param <T>                                  Type of request body / نوع داده ورودی
     * @param <R>                                  Type of accepted response / نوع پاسخ موفق
     * @param <F>                                  Type of failed response (must extend ExternalUnifiedResponseFailed) / نوع پاسخ ناموفق
     * @return Either success or failure response / شیء پاسخ موفق یا یکی از پاسخ‌های ناموفق
     */
    <T, R, F extends ExternalUnifiedServerFailureResponse> Object sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, List<Class<F>> responseFailed);

}
