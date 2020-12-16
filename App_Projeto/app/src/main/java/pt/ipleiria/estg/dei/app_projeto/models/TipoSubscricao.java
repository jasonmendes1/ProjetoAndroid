package pt.ipleiria.estg.dei.app_projeto.models;

public class TipoSubscricao {
    private int IDTipoSubscricao;
    private String tipo;

    public TipoSubscricao(int IDTipoSubscricao, String tipo){
        this.IDTipoSubscricao = IDTipoSubscricao;
        this.tipo = tipo;
    }

    public int getIDTipoSubscricao() {
        return IDTipoSubscricao;
    }

    public void setIDTipoSubscricao(int IDTipoSubscricao) {
        this.IDTipoSubscricao = IDTipoSubscricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
