package com.github.loanshark.configuration;

import com.github.loanshark.adapters.client.bureau.BureauClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {

    @Value("${bureau.url}")
    public String bureauUrl;

    @Bean
    public BureauClient bureauClient() {
        final var rest = RestClient.create(bureauUrl);
        final var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter
                .create(rest)).build();

        return factory.createClient(BureauClient.class);
    }
}