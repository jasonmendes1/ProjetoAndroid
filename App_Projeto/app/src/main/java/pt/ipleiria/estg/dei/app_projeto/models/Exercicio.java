package pt.ipleiria.estg.dei.app_projeto.models;

public class Exercicio {
    private int IDExer, repeticoes, tempo, series, repouso, tempo_total, num_maquina;
    private String nome;

    public Exercicio(int IDExer, String nome, int series, int repouso, int tempo_total, int num_maquina){
        this.IDExer = IDExer;
        this.nome = nome;
        this.series = series;
        this.repouso = repouso;
        this.tempo_total = tempo_total;
        this.num_maquina = num_maquina;
    }

    public int getIDExer() {
        return IDExer;
    }

    public void setIDExer(int IDExer) {
        this.IDExer = IDExer;
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

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
