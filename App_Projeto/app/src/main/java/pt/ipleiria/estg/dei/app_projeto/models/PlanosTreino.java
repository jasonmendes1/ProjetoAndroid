package pt.ipleiria.estg.dei.app_projeto.models;

import java.util.Date;

public class PlanosTreino {
    private int IDPlanoTreino, IDPT;
    private Integer dia_treino;
    private String semana;

    public PlanosTreino (int IDPlanoTreino,int IDPT ,  int dia_treino, String semana){
        this.IDPlanoTreino = IDPlanoTreino;
        this.IDPT = IDPT;
        this.dia_treino = dia_treino;
        this.semana = semana;
    }

    public int getIDPT() {
        return IDPT;
    }

    public void setIDPT(int IDPT) {
        this.IDPT = IDPT;
    }

    public int getIDPlanoTreino() {
        return IDPlanoTreino;
    }

    public void setIDPlanoTreino(int IDPlanoTreino) {
        this.IDPlanoTreino = IDPlanoTreino;
    }

    public Integer getDia_treino() {
        return dia_treino;
    }

    public void setDia_treino(Integer dia_treino) {
        this.dia_treino = dia_treino;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }
}
