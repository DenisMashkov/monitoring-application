package ru.example.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class ApplicationConfiguration {

    public class CustomHandler implements ResponseErrorHandler {

        @Override
        public void handleError(ClientHttpResponse response) {

        }

        @Override
        public boolean hasError(ClientHttpResponse response) {
            return false;   // all codes is OK
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();

        httpComponentsClientHttpRequestFactory.setConnectTimeout(10);
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(10);
        httpComponentsClientHttpRequestFactory.setReadTimeout(10);

        return new RestTemplateBuilder()
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .errorHandler(new CustomHandler())
                .build();
    }

}
