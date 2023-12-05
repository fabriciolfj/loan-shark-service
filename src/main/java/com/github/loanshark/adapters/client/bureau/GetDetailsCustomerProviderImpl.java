package com.github.loanshark.adapters.client.bureau;

import com.github.loanshark.entities.risk.LastLoanVO;
import com.github.loanshark.entities.risk.Risk;
import com.github.loanshark.usecases.risk.providers.GetDetailsCustomerProvider;
import com.github.loanshark.util.EventLogUtil;
import com.github.loanshark.util.StringToLocalDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetDetailsCustomerProviderImpl implements GetDetailsCustomerProvider {

    private static final EventLogUtil log = EventLogUtil.defaults(GetDetailsCustomerProviderImpl.class);
    private final BureauClient bureauClient;

    @Override
    public Risk process(final Risk risk) {
        try {
            final var result = bureauClient.findDetails(risk.getDocumentCustomer());
            final LastLoanVO vo = new LastLoanVO(StringToLocalDateUtil.toLocalDate(result.dateRequest()), result.othersLoan());

            log.event().m("getDetailsCustomerProviderImpl")
                    .param("customer", risk.getDocumentCustomer().substring(3))
                    .param("vo", vo)
                    .info();
            return risk.setLastLoan(vo);
        } catch (Exception e) {
            log.event().m("getDetailsCustomerProviderImpl")
                    .param("error", e.getMessage())
                    .info();

            return risk;
        }
    }
}