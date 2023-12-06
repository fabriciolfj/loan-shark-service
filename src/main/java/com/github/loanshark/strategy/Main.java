package com.github.loanshark.strategy;

public class Main {


    public static void main(String[] args) {
        var pedido = new Ecommerce();
        var strategy = new Sedex();
        var pac = new Pac();

        System.out.println(strategy.calcular(pedido));
        System.out.println(pac.calcular(pedido));
    }
}
