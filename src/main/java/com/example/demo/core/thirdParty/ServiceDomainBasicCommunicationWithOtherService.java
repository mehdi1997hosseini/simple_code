package com.example.demo.core.thirdParty;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import org.springframework.http.HttpEntity;

public interface ServiceDomainBasicCommunicationWithOtherService {
    /**
     * <p>
     *
     * @author mehdi_hosseini
     * <ul>
     * <li>documents: با استفاده از این متد میتوان درخواست را به سمت سامانه مورد نظر ارسال کرد که این متد ابتدا توکن را بر اساس نام سازمان آن از کش سیستم دریافت کرده و بعد قرار دادن توکن در قسمت امنیتی در خواست را ارسال مینماید </li>
     * <li>description: <ul>
     *     <li>@param : objectRequest : body درخواست برای ارسال به سامانه مورد نظر </li>
     *     <li>@param : uriExternalOrg : ادرس سرویس مورد نظر </li>
     *     <li>@param : orgName : سازمانی که قصد ارسال درخواست برای ان را داریم </li>
     * </ul>
     * </li>
     * </ul>
     * </p>
     */
    <E> HttpEntity<Object> requestExternalOrganizationService(E objectRequest, String uriExternalOrg, ExternalOrganizationName orgName);
    /**
     * <div>
     *     @author mehdi_hosseini
     * <ul>
     * <li>documents: دریافت توکن امنیتی </li>
     * <li>description: urlTokenAddress: ادرس سرویس برای گرفتن توکن امنیتی
     * 				username: نام کاربری برای ورود به سامانه مورد نظر
     * 				password: رمز عبور برای سامانه مورد نظر
     * 				clientId, clientSecret, grantType: header های مورد نیاز برای سامانه
     * </li>
     * </ul>
     * </div>
     * */
//	String getToken(String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType);
//	<E>HttpEntity<Object> initServiceRequestForAnyHttpMethod(E objectRequest, HttpMethod httpMethod, String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType);

}
