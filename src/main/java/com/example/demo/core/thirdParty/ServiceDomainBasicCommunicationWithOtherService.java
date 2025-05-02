package com.example.demo.core.thirdParty;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public interface ServiceDomainBasicCommunicationWithOtherService {
	/**
	 * <p>
	 *     @author mehdi_hosseini
	 * <ul>
	 * <li>documents: با استفاده از این متد میتوان درخواست را به سمت سامانه مورد نظر ارسال کرد که این متد ابتدا توکن دریافت کرده و بعد قرار دادن توکن در قسمت امنیتی در خواست را ارسال مینماید </li>
	 * <li>description: bodyGson: مقدار ورودی های مورد نیاز آن سرویس به صورت gson
	 * 				urlTokenAddress: ادرس سرویس برای گرفتن توکن امنیتی
	 * 				username: نام کاربری برای ورود به سامانه مورد نظر
	 * 				password: رمز عبور برای سامانه مورد نظر
	 * 				clientId, clientSecret, grantType: header های مورد نیاز برای سامانه
	 * </li>
	 * </ul>
	 * </p>
	 * */
	<E>HttpEntity<Object> initServiceReqHttpEntity(E objectRequest, String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType);
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
	String getToken(String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType);
//	<E>HttpEntity<Object> initServiceRequestForAnyHttpMethod(E objectRequest, HttpMethod httpMethod, String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType);

}
