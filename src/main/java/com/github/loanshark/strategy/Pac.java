package com.github.loanshark.strategy;

public class Pac implements StrategyEnvio {
    @Override
    public double calcular(Pedido pedido) {
        return pedido.getTotal() * 0.04;
    }
}
