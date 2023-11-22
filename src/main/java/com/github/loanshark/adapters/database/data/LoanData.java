package com.github.loanshark.adapters.database.data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "loan")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanData {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String status;
    @Column(name = "end_value")
    private BigDecimal endValue;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerData customer;
    @Column(name = "request_date")
    private LocalDateTime requestDate;
}
