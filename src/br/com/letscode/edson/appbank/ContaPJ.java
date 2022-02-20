package br.com.letscode.edson.appbank;

import java.math.BigDecimal;

public class ContaPJ extends Conta{

    private ContaCorrente contaCorrente;
    private ContaInvestimento contaInvestimento;
    private static final BigDecimal taxa = BigDecimal.valueOf(0.005);

    public ContaPJ(ClientePJ clientePJ, String numeroDaConta) {
        super(clientePJ, numeroDaConta);

        this.contaCorrente = new ContaCorrente(BigDecimal.ZERO);
        this.contaInvestimento = new ContaInvestimento(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal sacar(BigDecimal valorSaque, TipoDeConta tipoDeConta) {
        if (valorSaque.signum() != 1){
            throw new ValorNegativoException();
        }

        return switch (tipoDeConta){
            case CONTA_CORRENTE -> contaCorrente.setSaldo(contaCorrente.getSaldo()
                    .subtract(valorSaque.add(valorSaque.multiply(taxa))));
            case CONTA_POUPANCA -> throw new TipoContaNaoHabilitadaException();
            case CONTA_INVESTIMENTO -> contaInvestimento.setSaldo(contaInvestimento.getSaldo()
                    .subtract(valorSaque.add(valorSaque.multiply(taxa))));
        };
    }

    @Override
    public BigDecimal depositar(BigDecimal valorDeposito, TipoDeConta tipoDeConta) {
        if (valorDeposito.signum() != 1){
            throw new ValorNegativoException();
        }

        return switch (tipoDeConta){
            case CONTA_CORRENTE -> contaCorrente.setSaldo(contaCorrente.getSaldo().add(valorDeposito));
            case CONTA_POUPANCA -> throw new TipoContaNaoHabilitadaException();
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
                .add(valorInvestimento.multiply(BigDecimal.valueOf(1.12))));
    }

    @Override
    public BigDecimal consultarSaldo(TipoDeConta tipoDeConta) {
        return switch (tipoDeConta){
            case CONTA_CORRENTE -> contaCorrente.getSaldo();
            case CONTA_POUPANCA -> throw new TipoContaNaoHabilitadaException();
            case CONTA_INVESTIMENTO -> contaInvestimento.getSaldo();
        };
    }
}
