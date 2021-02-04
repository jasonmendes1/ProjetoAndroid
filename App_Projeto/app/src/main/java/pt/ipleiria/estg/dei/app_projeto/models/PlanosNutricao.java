package pt.ipleiria.estg.dei.app_projeto.models;

public class PlanosNutricao {
    private int IDPlanoNutricao, segunda, terca, quarta, quinta, sexta, sabado;
    private String semana;

    public PlanosNutricao (int IDPlanoNutricao, int segunda, int terca, int quarta, int quinta, int sexta, int sabado, String semana){
        this.IDPlanoNutricao = IDPlanoNutricao;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.semana = semana;
    }

    @Override
    public String toString() {
        return "PlanosNutricao{" +
                "IDPlanoNutricao=" + IDPlanoNutricao +
                ", segunda=" + segunda +
                ", terca=" + terca +
                ", quarta=" + quarta +
                ", quinta=" + quinta +
                ", sexta=" + sexta +
                ", sabado=" + sabado +
                ", semana='" + semana + '\'' +
                '}';
    }

    public int getIDPlanoNutricao() {
        return IDPlanoNutricao;
    }

    public void setIDPlanoNutricao(int IDPlanoNutricao) {
        this.IDPlanoNutricao = IDPlanoNutricao;
    }

    public int getSegunda() {
        return segunda;
    }

    public void setSegunda(int segunda) {
        this.segunda = segunda;
    }

    public int getTerca() {
        return terca;
    }

    public void setTerca(int terca) {
        this.terca = terca;
    }

    public int getQuarta() {
        return quarta;
    }

    public void setQuarta(int quarta) {
        this.quarta = quarta;
    }

    public int getQuinta() {
        return quinta;
    }

    public void setQuinta(int quinta) {
        this.quinta = quinta;
    }

    public int getSexta() {
        return sexta;
    }

    public void setSexta(int sexta) {
        this.sexta = sexta;
    }

    public int getSabado() {
        return sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }
}
