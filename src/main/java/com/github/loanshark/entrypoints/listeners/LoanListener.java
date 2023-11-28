package com.github.loanshark.entrypoints.listeners;

import com.github.loanshark.annotations.Provider;
import com.github.loanshark.util.EventLogUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@Provider
@RequiredArgsConstructor
public class LoanListener {

    private final EventLogUtil log = EventLogUtil.defaults(LoanListener.class);

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
    }
}
