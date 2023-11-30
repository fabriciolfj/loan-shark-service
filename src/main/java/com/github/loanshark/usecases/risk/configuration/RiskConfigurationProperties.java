package com.github.loanshark.usecases.risk.configuration;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(value = "risk")
@Getter
public class RiskConfigurationProperties {

    private Map<String, Integer> values;
}