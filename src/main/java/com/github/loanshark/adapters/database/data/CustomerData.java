package com.github.loanshark.adapters.database.data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    private BigDecimal salary;
    private LocalDate birthday;
    @OneToMany(mappedBy = "customer")
    private List<LoanData> loans;
}
