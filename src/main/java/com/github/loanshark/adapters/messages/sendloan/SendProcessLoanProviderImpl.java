package com.github.loanshark.adapters.messages.sendloan;

import com.github.loanshark.annotations.Provider;
import com.github.loanshark.entities.loan.Loan;
import com.github.loanshark.usecases.loan.SendProcessLoanProvider;
import com.github.loanshark.util.ConvertJsonUtil;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import static com.github.loanshark.adapters.messages.sendloan.InitProcessLoanDTO.toDto;


@Provider
@RequiredArgsConstructor
public class SendProcessLoanProviderImpl implements SendProcessLoanProvider {

    private static final EventLogUtil log = EventLogUtil.defaults(SendProcessLoanProviderImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${loan.initprocess}")
    private String topic;

    @Override
    public void process(final Loan loan) {
        var dto = toDto(loan);
        var json = ConvertJsonUtil.toJson(dto);

        kafkaTemplate.send(topic, json);
        log.event().m("process")
                .param("loan", loan.getCode())
                .action("send process loan")
                .info();
    }
}
