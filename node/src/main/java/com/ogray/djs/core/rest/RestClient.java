package com.ogray.djs.core.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Slf4j
public class RestClient {

    public static String sendPostRequest(String url, String data) throws Exception {
        log.info("POST '"+data+"'");

        SSLUtil.turnOffSslChecking();
        SSLUtil.disableSslVerification();

        RestTemplate restTemplate = new RestTemplate(); // restTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
 //
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        //headers.setContentType(new MediaType("application", "json", Charset.forName("windows-1251")));
        headers.add("token", "ey");

        HttpEntity<String> request =
                new HttpEntity<String>(data, headers);

        String response =
                restTemplate.postForObject(url, request, String.class);
        //JsonNode root = objectMapper.readTree(personResultAsJsonStr);
        return response;
    }

    public static RestTemplate restTemplate()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
