package pt.ipleiria.estg.dei.app_projeto.models;

public class PlanosTreino {
    private int IDPlanoTreino, repeticoes, tempo, serie, repouso, tempo_total, num_maquina;
    private String nome_exercicio;

    public PlanosTreino (int IDPlanoTreino, String nome_exercicio, int repeticoes, int tempo, int serie, int repouso, int tempo_total, int num_maquina){
        this.IDPlanoTreino = IDPlanoTreino;
        this.nome_exercicio = nome_exercicio;
        this.repeticoes = repeticoes;
        this.tempo = tempo;
        this.serie = serie;
        this.repouso = repouso;
        this.tempo_total = tempo_total;
        this.num_maquina = num_maquina;
    }

    public int getIDPlanoTreino() {
        return IDPlanoTreino;
    }

    public void setIDPlanoTreino(int IDPlanoTreino) {
        this.IDPlanoTreino = IDPlanoTreino;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRepouso() {
        return repouso;
    }

    public void setRepouso(int repouso) {
        this.repouso = repouso;
    }

    public int getTempo_total() {
        return tempo_total;
    }

    public void setTempo_total(int tempo_total) {
        this.tempo_total = tempo_total;
    }

    public int getNum_maquina() {
        return num_maquina;
    }

    public void setNum_maquina(int num_maquina) {
        this.num_maquina = num_maquina;
    }

    public String getNome_exercicio() {
        return nome_exercicio;
    }

    public void setNome_exercicio(String nome_exercicio) {
        this.nome_exercicio = nome_exercicio;
    }
}
