package com.github.loanshark.adapters.database.risk;

import com.github.loanshark.adapters.database.data.RiskData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskRepository extends JpaRepository<RiskData, Long> {
}
