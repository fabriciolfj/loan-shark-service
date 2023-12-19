package com.github.loanshark.adapters.messages.sendloan;

import com.github.loanshark.annotations.Provider;
import com.github.loanshark.usecases.risk.providers.NotifyRiskProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Provider
@RequiredArgsConstructor
public class SendNotifyLoanRiskCompletedRiskProviderImpl implements NotifyRiskProvider {

    private final KafkaTemplate<String, String> producer;

    @Value("${loan.notify}")
    private String topic;

    @Override
    public void process(final String loan) {
        producer.send(topic, loan);
    }
}
