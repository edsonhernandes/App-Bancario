package br.com.letscode.edson.appbank;

public class ClientePJ extends Cliente {
    private String cnpj;

    public ClientePJ(String nome, String endereco, String telefone, String cnpj) {
        super(nome, endereco, telefone);
        this.cnpj = cnpj;
    }
}
