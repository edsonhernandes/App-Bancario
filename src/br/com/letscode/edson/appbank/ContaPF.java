package br.com.letscode.edson.appbank;

import java.math.BigDecimal;

public class ContaPF extends Conta{

    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;
    private ContaInvestimento contaInvestimento;

    public ContaPF(ClientePF cliente, String numeroDaConta) {
        super(cliente, numeroDaConta);
        this.contaCorrente = new ContaCorrente(BigDecimal.ZERO);
        this.contaPoupanca = new ContaPoupanca(BigDecimal.ZERO);
        this.contaInvestimento = new ContaInvestimento(BigDecimal.ZERO);

    }


    @Override
    public BigDecimal sacar(BigDecimal valorSaque, TipoDeConta tipoDeConta) {
        if (valorSaque.signum() != 1){
            throw new ValorNegativoException();
        }
        return switch (tipoDeConta){
            case CONTA_CORRENTE -> contaCorrente.setSaldo(contaCorrente.getSaldo().subtract(valorSaque));
            case CONTA_POUPANCA -> contaPoupanca.setSaldo(contaPoupanca.getSaldo().subtract(valorSaque));
            case CONTA_INVESTIMENTO -> contaInvestimento.setSaldo(contaInvestimento.getSaldo().subtract(valorSaque));
        };
    }

    @Override
    public BigDecimal depositar(BigDecimal valorDeposito, TipoDeConta tipoDeConta) {
        if (valorDeposito.signum() != 1){
            throw new ValorNegativoException();
        }

        return switch (tipoDeConta){
            case CONTA_CORRENTE -> contaCorrente.setSaldo(contaCorrente.getSaldo().add(valorDeposito));
            case CONTA_POUPANCA -> contaPoupanca.setSaldo(contaPoupanca.getSaldo().add(valorDeposito));
            case CONTA_INVESTIMENTO -> contaInvestimento.setSaldo(contaInvestimento.getSaldo().add(valorDeposito));
        };
    }

    @Override
    public BigDecimal transferir(BigDecimal valorTransferencia, Conta conta, TipoDeConta tipoDeContaDebitada,
                                 TipoDeConta tipoDeContaCreditada) {
        if (valorTransferencia.signum() != 1){
            throw new ValorNegativoException();
        }

        conta.depositar(valorTransferencia, tipoDeContaCreditada);
        return sacar(valorTransferencia, tipoDeContaDebitada);


    }

    @Override
    public BigDecimal investir(BigDecimal valorInvestimento) {
        if (valorInvestimento.signum() != 1){
            throw new ValorNegativoException();
        }

        contaCorrente.setSaldo(contaCorrente.getSaldo().subtract(valorInvestimento));
        return contaInvestimento.setSaldo(contaInvestimento.getSaldo()
                .add(valorInvestimento.multiply(BigDecimal.valueOf(1.1))));
    }

    @Override
    public BigDecimal consultarSaldo(TipoDeConta tipoDeConta) {
        return switch (tipoDeConta){
            case CONTA_CORRENTE -> contaCorrente.getSaldo();
            case CONTA_POUPANCA -> contaPoupanca.getSaldo();
            case CONTA_INVESTIMENTO -> contaInvestimento.getSaldo();
        };
    }
}
