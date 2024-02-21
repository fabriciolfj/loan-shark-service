package com.github.loanshark.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "score")
@Getter
@Setter
public class ScoreRiskConfigurationProperties {

    private int approved;
}
