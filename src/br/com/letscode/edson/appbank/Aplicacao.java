package br.com.letscode.edson.appbank;

import java.math.BigDecimal;

public class Aplicacao {
    public static void main(String[] args) {

      var banco = new Banco();
      var contaPJ = banco.criaNovaConta(new ClientePJ("Edy", "Rua Batman, 332", "2222-0505", "116888777999-02"));
      var contaPF = banco.criaNovaConta(new ClientePF("Sueli", "Rua Chispirito, 55", "9999-0509", "332444009-17"));

      contaPF.depositar(BigDecimal.valueOf(800), TipoDeConta.CONTA_CORRENTE);
      contaPJ.sacar(BigDecimal.valueOf(300), TipoDeConta.CONTA_CORRENTE);
      contaPJ.depositar(BigDecimal.valueOf(500), TipoDeConta.CONTA_CORRENTE);
      contaPF.investir(BigDecimal.valueOf(100));
      contaPJ.investir(BigDecimal.valueOf(100));
      contaPJ.transferir(BigDecimal.valueOf(100), contaPF, TipoDeConta.CONTA_CORRENTE, TipoDeConta.CONTA_POUPANCA);



        System.out.println(contaPF.consultarSaldo(TipoDeConta.CONTA_CORRENTE));
        System.out.println(contaPF.consultarSaldo(TipoDeConta.CONTA_INVESTIMENTO));
        System.out.println(contaPF.consultarSaldo(TipoDeConta.CONTA_POUPANCA));
        System.out.println(contaPJ.consultarSaldo(TipoDeConta.CONTA_CORRENTE));
        System.out.println(contaPJ.consultarSaldo(TipoDeConta.CONTA_INVESTIMENTO));









    }

}
