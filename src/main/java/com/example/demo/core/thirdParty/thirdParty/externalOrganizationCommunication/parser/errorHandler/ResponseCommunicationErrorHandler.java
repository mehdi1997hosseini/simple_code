package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.parser.errorHandler;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalUnifiedResponseFailed;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.response.ResponseFailedBankOrganization;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class responsible for handling and resolving error responses
 * received from external organizations during API communication.
 *
 * <p>
 * کلاس ابزار (Utility) برای مدیریت و تحلیل خطاهای بازگشتی از سازمان‌های خارجی
 * در زمان ارتباطات (Communication) بین سرویس‌ها.
 *
 * <p>
 * This class registers and maps external error codes (specific to each organization)
 * to a corresponding failure response object implementing {@link ExternalUnifiedResponseFailed}.
 *
 * <p>
 * این کلاس با نگهداری یک نگاشت (Map) از خطاهای سازمان‌ها و مقادیر متناسب آن‌ها (که پیاده‌ساز
 * {@code ExternalUnifiedResponseFailed} هستند)، امکان استخراج دقیق نوع خطا را فراهم می‌کند.
 *
 * <h2>Example - نمونه استفاده</h2>
 *
 * <pre>{@code
 * // Example usage when you know the exact class
 * Optional<ResponseFailedBankOrganization> failure =
 *     ResponseCommunicationErrorHandler.getFailedResponse(
 *         ExternalOrganizationName.BANK,
 *         "0401_BANK",
 *         ResponseFailedBankOrganization.class
 *     );
 * failure.ifPresent(f -> System.out.println("Bank message: " + f.getBankMessage()));
 *
 * // Example usage when type is unknown
 * Optional<ExternalUnifiedResponseFailed> generalFailure =
 *     ResponseCommunicationErrorHandler.getFailedResponse(
 *         ExternalOrganizationName.BANK,
 *         "0401_BANK"
 *     );
 * generalFailure.ifPresent(f -> System.out.println("Error Code: " + f.getErrorCode()));
 * }</pre>
 *
 * <b>Thread-safe</b> – ایمن برای استفاده همزمان در چند Thread به کمک {@code ConcurrentHashMap}.
 */
public final class ResponseCommunicationErrorHandler {

    private ResponseCommunicationErrorHandler() {
    }

    private static final Map<ExternalOrganizationName, Map<String, Object>> errorRegistryMap = new ConcurrentHashMap<>();

    static {

        registerErrorsMap(ExternalOrganizationName.BANK,
                List.of(new ResponseFailedBankOrganization("0401_BANK", "0401_BANK", "0401_BANK", "0401_BANK"))
        );

        registerErrorsMap(ExternalOrganizationName.NCR,
                List.of()
        );

    }

    /**
     * <p>Register a list of error response objects for a specific organization.</p>
     *
     * <p>ثبت لیستی از شیءهای خطا برای یک سازمان مشخص.</p>
     *
     * @param externalOrganizationName name of the external organization
     *                                 نام سازمان خارجی
     * @param fList                    list of objects implementing {@code ExternalUnifiedResponseFailed}
     *                                 لیستی از اشیاء پیاده‌ساز {@code ExternalUnifiedResponseFailed}
     * @param <F>                      type of failure objects
     *                                 نوع عمومی اشیاء خطا
     */
    private static <F extends ExternalUnifiedResponseFailed> void registerErrorsMap(
            ExternalOrganizationName externalOrganizationName,
            List<F> fList
    ) {
        Map<String, Object> errorsMap = new HashMap<>();
        for (F f : fList) {
            errorsMap.put(f.getErrorCode(), f);
        }
        errorRegistryMap.put(externalOrganizationName, new ConcurrentHashMap<>(errorsMap));
    }

    /**
     * Retrieve an error response object with explicit casting to a specific type.
     * <p>
     * دریافت شیء خطا با تعیین نوع خروجی به‌صورت امن.
     *
     * <p>
     * Useful when you want to access type-specific fields or methods of the failure response.
     * <p>
     * زمانی کاربرد دارد که بخواهید به فیلدها یا متدهای اختصاصی نوع خاصی از خطا دسترسی داشته باشید.
     *
     * <h3>Usage Example - مثال استفاده:</h3>
     * <pre>{@code
     * Optional<ResponseFailedBankOrganization> failure =
     *     ResponseCommunicationErrorHandler.getFailedResponse(
     *         ExternalOrganizationName.BANK,
     *         "0401_BANK",
     *         ResponseFailedBankOrganization.class
     *     );
     * failure.ifPresent(f -> System.out.println("Bank Msg: " + f.getBankMessage()));
     * }</pre>
     *
     * @param externalOrganizationName the name of the external organization
     * @param errorCode                the returned error code
     * @param failureClass             the expected failure class
     * @param <F>                      type of expected failure class
     * @return Optional of the expected failure object, if available
     */
    @SuppressWarnings("unchecked")
    public static <F extends ExternalUnifiedResponseFailed> Optional<F> getFailedResponse(
            ExternalOrganizationName externalOrganizationName,
            String errorCode,
            Class<F> failureClass
    ) {
        Object obj = errorRegistryMap
                .getOrDefault(externalOrganizationName, Map.of())
                .get(errorCode);

        if (failureClass.isInstance(obj)) {
            return Optional.of((F) obj);
        }
        return Optional.empty();
    }

    /**
     * Retrieve an error response object without specifying its exact class.
     * <p>
     * دریافت شیء خطا بدون نیاز به تعیین کلاس خروجی. فقط متدهای {@code ExternalUnifiedResponseFailed} قابل دسترسی هستند.
     *
     * <h3>Usage Example - مثال استفاده:</h3>
     * <pre>{@code
     * Optional<ExternalUnifiedResponseFailed> failure =
     *     ResponseCommunicationErrorHandler.getFailedResponse(
     *         ExternalOrganizationName.BANK,
     *         "0401_BANK"
     *     );
     * failure.ifPresent(f -> System.out.println("Error Code: " + f.getErrorCode()));
     * }</pre>
     *
     * @param externalOrganizationName the name of the external organization
     * @param errorCode                the returned error code
     * @return Optional of the general failure object
     */
    public static Optional<ExternalUnifiedResponseFailed> getFailedResponse(
            ExternalOrganizationName externalOrganizationName,
            String errorCode
    ) {
        return Optional.ofNullable(errorRegistryMap
                        .getOrDefault(externalOrganizationName, Map.of())
                        .get(errorCode))
                .filter(ExternalUnifiedResponseFailed.class::isInstance)
                .map(ExternalUnifiedResponseFailed.class::cast);
    }

}
