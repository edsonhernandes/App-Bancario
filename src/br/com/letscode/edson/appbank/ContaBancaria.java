package br.com.letscode.edson.appbank;

import java.math.BigDecimal;

public abstract class ContaBancaria {
    private BigDecimal saldo;


    public ContaBancaria(BigDecimal saldo) {
        this.saldo = saldo;

    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this.saldo;
    }


    @Override
    public String toString() {
        return "ContaBancaria{" +
                "saldo=" + saldo +
                '}';
    }
}
