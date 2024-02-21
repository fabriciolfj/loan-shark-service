package com.github.loanshark.adapters.database.data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Column(name = "value")
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerData customer;
    @Column(name = "request_date")
    private LocalDateTime requestDate;

    public LocalDate getBirthday() {
        return customer.getBirthday();
    }

    public BigDecimal getSalary() {
        return customer.getSalary();
    }

    public String getNameCustomer() {
        return this.customer.getName();
    }

    public String getDocument() {
        return this.customer.getDocument();
    }
}
