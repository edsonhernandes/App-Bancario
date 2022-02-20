package br.com.letscode.edson.appbank;

public class ClientePF extends Cliente{
    private String cpf;

    public ClientePF(String nome, String endereco, String telefone, String cpf) {
        super(nome, endereco, telefone);
        this.cpf = cpf;
    }
}
