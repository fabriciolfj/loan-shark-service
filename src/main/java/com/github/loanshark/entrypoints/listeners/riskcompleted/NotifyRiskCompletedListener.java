package com.github.loanshark.entrypoints.listeners.riskcompleted;

import com.github.loanshark.annotations.Provider;
import com.github.loanshark.usecases.loan.ProcessStatusLoanUseCase;
import com.github.loanshark.util.ConvertJsonUtil;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Objects;

import static com.github.loanshark.util.EventLogUtil.State.FAILURE;

@Provider
@RequiredArgsConstructor
public class NotifyRiskCompletedListener {

    private final EventLogUtil log = EventLogUtil.defaults(NotifyRiskCompletedListener.class);
    private ProcessStatusLoanUseCase processStatusLoanUseCase;

    @KafkaListener(topics = "${loan.notify}", groupId = "${spring.application.name}")
    public void process(final ConsumerRecord<String, String> record, final Acknowledgment ack) {
        var logMethod = log.event().m("NotifyRiskCompletedListener");
        var payload = record.value();

        if (Objects.isNull(payload)) {
            logMethod.state(FAILURE)
                    .error("payload is null");
            return;
        }

        try {
            logMethod.param("message", payload)
                            .info();

            var dto = new ConvertJsonUtil<NotifyRiskDTO>().toObject(payload, NotifyRiskDTO.class);

            processStatusLoanUseCase.execute(dto.getLoan(), dto.getStatus());
            ack.acknowledge();
        } catch (Exception e) {
            logMethod.state(FAILURE)
                    .error("fail process notify", e.getMessage());
        }
    }
}