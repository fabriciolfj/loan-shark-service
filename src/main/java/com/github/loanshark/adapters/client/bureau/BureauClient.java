package com.github.loanshark.adapters.client.bureau;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface BureauClient {

    @GetExchange("/customer/{document}")
    CustomerBereauDTO findDetails(@PathVariable("document") final String document);
}