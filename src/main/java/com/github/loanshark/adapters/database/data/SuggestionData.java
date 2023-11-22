package com.github.loanshark.adapters.database.data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "suggestion")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal fees;
    private int installments;
    private BigDecimal endValue;
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanData loan;
}
