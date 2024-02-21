package com.github.loanshark.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(value = "risk")
@Getter
@Setter
public class RiskConfigurationProperties {

    private Map<String, Integer> values;
}