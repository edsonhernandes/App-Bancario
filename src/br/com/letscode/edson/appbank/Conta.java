package br.com.letscode.edson.appbank;

import java.math.BigDecimal;

public abstract class Conta {
    private Cliente cliente;
    private String numeroDaConta;

    public Conta(Cliente cliente, String numeroDaConta) {
        this.cliente = cliente;
        this.numeroDaConta = numeroDaConta;
    }


    public abstract BigDecimal sacar(BigDecimal valorSaque, TipoDeConta tipoDeConta);
    public abstract BigDecimal depositar(BigDecimal valorDeposito, TipoDeConta tipoDeConta);
    public abstract BigDecimal transferir(BigDecimal valorTransferencia, Conta conta,
                                          TipoDeConta tipoDeContaDebitada,
                                          TipoDeConta tipoDeContaCreditada);
    public abstract BigDecimal investir(BigDecimal valorInvestimento);
    public abstract BigDecimal consultarSaldo(TipoDeConta tipoDeConta);
}
