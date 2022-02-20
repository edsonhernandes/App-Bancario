package br.com.letscode.edson.appbank;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> listaDeContas;

    public Banco() {
        listaDeContas = new ArrayList<>();
    }

    public Conta criaNovaConta(Cliente cliente){
        if (cliente instanceof ClientePF){
            //casting
            var contaPF = new ContaPF((ClientePF) cliente, String.valueOf(listaDeContas.size()+1));
            listaDeContas.add(contaPF);
            return contaPF;
        }
        else if (cliente instanceof ClientePJ){


            ContaPJ contaPJ = new ContaPJ((ClientePJ) cliente, String.valueOf(listaDeContas.size() + 1));
            listaDeContas.add(contaPJ);
            return contaPJ;

        }
        return null;


    }
}
