package pt.ipleiria.estg.dei.app_projeto.models;

public class Desconto {
    private int IDDesconto, quantidade;
    private String nome;

    public Desconto (int IDDesconto, int quantidade, String nome){
        this.IDDesconto = IDDesconto;
        this.quantidade = quantidade;
        this.nome = nome;
    }

    public int getIDDesconto() {
        return IDDesconto;
    }

    public void setIDDesconto(int IDDesconto) {
        this.IDDesconto = IDDesconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
