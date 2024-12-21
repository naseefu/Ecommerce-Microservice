package com.lustre.order.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClients {

    @Bean
    public StockClient stockClient() {

        String stockUrl = "http://localhost:8082";
        RestClient client = RestClient.builder()
                .baseUrl(stockUrl)
                .build();

        var restClientAdapter = RestClientAdapter.create(client);
        var webProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter);

        return webProxyFactory.build().createClient(StockClient.class);

    }

    @Bean
    public ProductClient productClient() {
        String productUrl = "http://localhost:8081";
        RestClient client = RestClient.builder()
                .baseUrl(productUrl).build();

        var restClientAdapter = RestClientAdapter.create(client);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(ProductClient.class);
    }

    @Bean
    public CartClient cartClient() {
        String cartUrl = "http://localhost:8084";
        RestClient client = RestClient.builder()
                .baseUrl(cartUrl).build();
        var restClientAdapter = RestClientAdapter.create(client);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(CartClient.class);
    }

}
