package pt.ipleiria.estg.dei.app_projeto.listeners;

import android.app.Dialog;
import android.os.Bundle;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.Cliente;

public interface UserListener {
    //void onRefreshListaUser(User user);
    void onRefreshListaCliente(ArrayList<Cliente> listaCliente);
    void onRefreshEditar();
    Dialog onCreateDialog(Bundle savedInstanceState);
}
