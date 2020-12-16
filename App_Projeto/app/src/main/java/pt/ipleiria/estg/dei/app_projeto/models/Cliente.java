package pt.ipleiria.estg.dei.app_projeto.models;

public class Cliente{
    private int IDCliente, dta_nascimento, num_tele, nif, altura, peso, massa_muscular, massa_gorda;
    private String primeiroNome, apelido, sexo, avatar;

    public Cliente(int IDCliente, String primeiroNome, String apelido, int dta_nascimento, String sexo, String avatar, int num_tele, int nif, int altura, int peso, int massa_muscular, int massa_gorda){
        this.IDCliente = IDCliente;
        this.primeiroNome = primeiroNome;
        this.apelido = apelido;
        this.dta_nascimento = dta_nascimento;
        this.sexo = sexo;
        this.avatar = avatar;
        this.num_tele = num_tele;
        this.nif = nif;
        this.altura = altura;
        this.peso = peso;
        this.massa_muscular = massa_muscular;
        this.massa_gorda = massa_gorda;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public int getDta_nascimento() {
        return dta_nascimento;
    }

    public void setDta_nascimento(int dta_nascimento) {
        this.dta_nascimento = dta_nascimento;
    }

    public int getNum_tele() {
        return num_tele;
    }

    public void setNum_tele(int num_tele) {
        this.num_tele = num_tele;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getMassa_muscular() {
        return massa_muscular;
    }

    public void setMassa_muscular(int massa_muscular) {
        this.massa_muscular = massa_muscular;
    }

    public int getMassa_gorda() {
        return massa_gorda;
    }

    public void setMassa_gorda(int massa_gorda) {
        this.massa_gorda = massa_gorda;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
