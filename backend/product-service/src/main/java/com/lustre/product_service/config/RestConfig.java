package com.lustre.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestConfig {

    @Bean
    public StockClient stockClient() {
        String stockurl = "http://localhost:8082";
        RestClient client = RestClient.builder()
                .baseUrl(stockurl)
                .build();

        var restClientAdapter = RestClientAdapter.create(client);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(StockClient.class);

    }
}
