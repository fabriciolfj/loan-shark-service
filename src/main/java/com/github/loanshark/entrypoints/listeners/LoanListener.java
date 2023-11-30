package com.github.loanshark.entrypoints.listeners;

import com.github.loanshark.annotations.Provider;
import com.github.loanshark.usecases.risk.RiskAnalysisCoordinatorUseCase;
import com.github.loanshark.util.ConvertJsonUtil;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@Provider
@RequiredArgsConstructor
public class LoanListener {

    private final EventLogUtil log = EventLogUtil.defaults(LoanListener.class);

    private final RiskAnalysisCoordinatorUseCase riskAnalysisCoordinatorUseCase;

    @KafkaListener(topics = "${loan.initprocess}", groupId = "${spring.application.name}")
    public void receive(final ConsumerRecord<String, String> message, final Acknowledgment ack) {
        final var payload = message.value();

        if (payload == null) {
            ack.acknowledge();
            return;
        }

        log.event()
                .m("receive")
                .param("message", payload)
                .info();

        try {
            final var dto = new ConvertJsonUtil<LoanIdDTO>().toObject(payload, LoanIdDTO.class);
            final var risk = RiskMapper.toRisk(dto);

            riskAnalysisCoordinatorUseCase.execute(risk);
            log.event().m("receive")
                    .param("message", "process success risk to loan " + payload)
                    .info();

            ack.acknowledge();
        } catch (Exception e) {
            log.event().m("receive")
                    .param("message", "fail process risk to loan " + payload)
                    .error();
        }
    }
}
