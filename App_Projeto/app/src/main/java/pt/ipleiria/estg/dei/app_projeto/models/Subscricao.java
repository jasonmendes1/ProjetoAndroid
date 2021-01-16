package pt.ipleiria.estg.dei.app_projeto.models;

import java.util.Date;

public class Subscricao {
    private int IDSubscricao, preco, total;
    private Date data_subscricao, data_expirar;

    public Subscricao (int IDSubscricao, int preco, Date data_subscricao, Date data_expirar, int total){
        this.IDSubscricao = IDSubscricao;
        this.preco = preco;
        this.data_subscricao = data_subscricao;
        this.data_expirar = data_expirar;
        this.total = total;
    }

    public int getIDSubscricao() {
        return IDSubscricao;
    }

    public void setIDSubscricao(int IDSubscricao) {
        this.IDSubscricao = IDSubscricao;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Date getData_subscricao() {
        return data_subscricao;
    }

    public void setData_subscricao(Date data_subscricao) {
        this.data_subscricao = data_subscricao;
    }

    public Date getData_expirar() {
        return data_expirar;
    }

    public void setData_expirar(Date data_expirar) {
        this.data_expirar = data_expirar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
