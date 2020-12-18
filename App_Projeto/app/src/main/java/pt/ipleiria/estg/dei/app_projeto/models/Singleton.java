package pt.ipleiria.estg.dei.app_projeto.models;

import android.app.Application;

import java.util.ArrayList;

public class Singleton extends Application {

    private ArrayList<Cliente> clientes;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<Ementa> ementa;
}
