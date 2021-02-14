package pt.ipleiria.estg.dei.app_projeto.models;

public class Ementa {
    private int IDEmenta;
    private String nomeementa, pequenoalmoco, almoco, lanche1, lanche2, jantar;


    public Ementa (int IDEmenta, String nomeEmenta, String pequenoalmoco, String almoco, String lanche1, String lanche2, String jantar){
        this.IDEmenta = IDEmenta;
        this.nomeementa = nomeEmenta;
        this.pequenoalmoco = pequenoalmoco;
        this.almoco = almoco;
        this.lanche1 = lanche1;
        this.lanche2 = lanche2;
        this.jantar = jantar;
    }

    public int getIDEmenta() {
        return IDEmenta;
    }

    public void setIDEmenta(int IDEmenta) {
        this.IDEmenta = IDEmenta;
    }

    public String getNomeementa() {
        return nomeementa;
    }

    public void setNomeementa(String nomeementa) {
        this.nomeementa = nomeementa;
    }

    public String getPequenoalmoco() {
        return pequenoalmoco;
    }

    public void setPequenoalmoco(String pequenoalmoco) {
        this.pequenoalmoco = pequenoalmoco;
    }

    public String getAlmoco() {
        return almoco;
    }

    public void setAlmoco(String almoco) {
        this.almoco = almoco;
    }

    public String getLanche1() {
        return lanche1;
    }

    public void setLanche1(String lanche1) {
        this.lanche1 = lanche1;
    }

    public String getLanche2() {
        return lanche2;
    }

    public void setLanche2(String lanche2) {
        this.lanche2 = lanche2;
    }

    public String getJantar() {
        return jantar;
    }

    public void setJantar(String jantar) {
        this.jantar = jantar;
    }
}
