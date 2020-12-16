package pt.ipleiria.estg.dei.app_projeto.models;

public class Funcionario {
    private int IDFuncionario, dt_nascimento, num_tele;
    private String primeiroNome, apelido, avatar;

    public Funcionario (int IDFuncionario, String primeiroNome, String apelido, int dt_nascimento, String avatar, int num_tele){
        this.IDFuncionario = IDFuncionario;
        this.primeiroNome = primeiroNome;
        this.apelido = apelido;
        this.dt_nascimento = dt_nascimento;
        this.avatar = avatar;
        this.num_tele = num_tele;
    }

    public int getIDFuncionario() {
        return IDFuncionario;
    }

    public void setIDFuncionario(int IDFuncionario) {
        this.IDFuncionario = IDFuncionario;
    }

    public int getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(int dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public int getNum_tele() {
        return num_tele;
    }

    public void setNum_tele(int num_tele) {
        this.num_tele = num_tele;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
