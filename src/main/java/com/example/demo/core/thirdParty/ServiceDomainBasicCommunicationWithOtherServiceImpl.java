package com.example.demo.core.thirdParty;

import ir.jame.dispatching.DispatchingRemoteExceptionRegistry;
import ir.jame.dispatching.dto.mockdto.ResponseTokenDto;

import java.util.Objects;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class ServiceDomainBasicCommunicationWithOtherServiceImpl implements ServiceDomainBasicCommunicationWithOtherService{

	@Override
	public <E>HttpEntity<Object> initServiceReqHttpEntity(E objectRequest, String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType) {
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		ResponseTokenDto appToken = getTokenFromService(urlTokenAddress,username,password,clientId,clientSecret,grantType);
		httpHeader.set("Authorization", "Bearer "+appToken.getAccess_token());
		return new HttpEntity<>(objectRequest, httpHeader);
	}

	private ResponseTokenDto getTokenFromService(String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType ){
		Gson gson = new Gson();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> requestEntity = initTokenReqHttpEntity(username,password,clientId,clientSecret,grantType);
		ResponseEntity<String> responseEntity = restTemplate.exchange(urlTokenAddress, HttpMethod.POST, requestEntity, String.class);
		return gson.fromJson(responseEntity.getBody(),ResponseTokenDto.class);
	}

	private HttpEntity<Object> initTokenReqHttpEntity(String username, String password , String clientId, String clientSecret , String grantType){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("username", username);
		requestBody.add("password", password);
		requestBody.add("client_id", clientId);
		requestBody.add("client_secret", clientSecret);
		requestBody.add("grant_type", grantType);
		return new HttpEntity<>(requestBody, headers);
	}


	@Override
	public String getToken(String urlTokenAddress , String username, String password , String clientId, String clientSecret , String grantType){
		MultiValueMap<String, String> oauthPayload = new LinkedMultiValueMap<>();
		oauthPayload.add("username", username);
		oauthPayload.add("password", password);
		oauthPayload.add("client_id", clientId);
		oauthPayload.add("client_secret", clientSecret);
		oauthPayload.add("grant_type", grantType);

		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<?> entity = new HttpEntity<>(oauthPayload, headers);
		ResponseEntity<ResponseTokenDto> tokenApiDto = restTemplate.exchange(
				urlTokenAddress, HttpMethod.POST, entity, ResponseTokenDto.class);
		String accessToken;
		if(tokenApiDto.getStatusCodeValue()!=200){
			throw DispatchingRemoteExceptionRegistry.create(DispatchingRemoteExceptionRegistry.ERROR_UNAUTHORIZED, "خطا در دریافت توکن امنیتی");
		}else{
			accessToken = Objects.requireNonNull(tokenApiDto.getBody()).getAccess_token();
			return accessToken;
		}
	}

}
