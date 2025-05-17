package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy;

public enum RequestSendType {
    BODY,          // برای ارسال با body (POST/PUT معمولاً)
    QUERY_PARAM,   // برای ارسال با query string
    PATH_PARAM,    // برای جای‌گذاری در URI
    MULTIPART,     // برای ارسال فایل یا multipart
    HEADER_ONLY;    // برای درخواست بدون body، فقط header

    public BasicExternalServiceRequestStrategy getInstanceStrategyRequest() {
        return switch (this) {
            case BODY -> new BodyRequestStrategy();
            case QUERY_PARAM -> new QueryParamRequestStrategy();
            case PATH_PARAM -> new PathParamRequestStrategy();
            case MULTIPART -> new MultipartRequestStrategy();
            case HEADER_ONLY -> new HeaderOnlyRequestStrategy();
            default -> null;
        };
    }
}
