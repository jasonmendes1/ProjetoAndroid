package pt.ipleiria.estg.dei.app_projeto.models;

public class PlanosTreino {
    private int dia_treino, semana;

    public PlanosTreino (int dia_treino, int semana){
        this.dia_treino = dia_treino;
        this.semana = semana;
    }

    public int getDia_treino() {
        return dia_treino;
    }

    public void setDia_treino(int dia_treino) {
        this.dia_treino = dia_treino;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }
}
