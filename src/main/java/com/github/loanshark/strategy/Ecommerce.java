package com.github.loanshark.strategy;

public class Ecommerce implements Pedido {

    @Override
    public String getTipo() {
        return "ecomm";
    }

    @Override
    public double getTotal() {
        return 283.67;
    }
}
