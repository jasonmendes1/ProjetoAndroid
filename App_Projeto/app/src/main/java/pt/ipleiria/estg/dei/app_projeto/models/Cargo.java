package pt.ipleiria.estg.dei.app_projeto.models;

public class Cargo {
    private int IDCargo;
    private String cargo;

    public Cargo (int IDCargo, String cargo){
        this.IDCargo = IDCargo;
        this.cargo = cargo;
    }

    public int getIDCargo() {
        return IDCargo;
    }

    public void setIDCargo(int IDCargo) {
        this.IDCargo = IDCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
