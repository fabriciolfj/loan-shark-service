package com.github.loanshark.adapters.client.bureau;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;

public interface BureauClient {

    @GetMapping("/customer/{document}")
    CustomerBereauDTO findDetails(@PathParam("document") final String document);
}