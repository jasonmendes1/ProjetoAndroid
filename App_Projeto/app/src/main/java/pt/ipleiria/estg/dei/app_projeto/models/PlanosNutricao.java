package pt.ipleiria.estg.dei.app_projeto.models;

public class PlanosNutricao {
    private int IDPlanoNutricao;
    private String segunda, terca, quarta, quinta, sexta, sabado, semana;

    public PlanosNutricao (int IDPlanoNutricao, String segunda, String terca, String quarta, String quinta, String sexta, String sabado, String semana){
        this.IDPlanoNutricao = IDPlanoNutricao;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.semana = semana;
    }

    public int getIDPlanoNutricao() {
        return IDPlanoNutricao;
    }

    public void setIDPlanoNutricao(int IDPlanoNutricao) {
        this.IDPlanoNutricao = IDPlanoNutricao;
    }

    public String getSegunda() {
        return segunda;
    }

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public String getTerca() {
        return terca;
    }

    public void setTerca(String terca) {
        this.terca = terca;
    }

    public String getQuarta() {
        return quarta;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public String getQuinta() {
        return quinta;
    }

    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

    public String getSexta() {
        return sexta;
    }

    public void setSexta(String sexta) {
        this.sexta = sexta;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }
}
