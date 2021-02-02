package pt.ipleiria.estg.dei.app_projeto.models;

public class PlanosTreino {
    private int IDPlanoTreino, id_PT;
    private String dia_treino;
    private String semana;

    public PlanosTreino (int IDPlanoTreino, int id_PT, String dia_treino, String semana){
        this.IDPlanoTreino = IDPlanoTreino;
        this.id_PT = id_PT;
        this.dia_treino = dia_treino;
        this.semana = semana;
    }

    public int getid_PT() {
        return id_PT;
    }

    public void setid_PT(int IDPT) {
        this.id_PT = id_PT;
    }

    public int getIDPlanoTreino() {
        return IDPlanoTreino;
    }

    public void setIDPlanoTreino(int IDPlanoTreino) {
        this.IDPlanoTreino = IDPlanoTreino;
    }

    public String getDia_treino() {
        return dia_treino;
    }

    public void setDia_treino(String dia_treino) {
        this.dia_treino = dia_treino;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }
}
