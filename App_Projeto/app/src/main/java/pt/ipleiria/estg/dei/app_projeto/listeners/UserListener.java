package pt.ipleiria.estg.dei.app_projeto.listeners;

import android.app.Dialog;
import android.os.Bundle;

import pt.ipleiria.estg.dei.app_projeto.models.Cliente;

public interface UserListener {
    //void onRefreshListaUser(User user);
    void onRefreshListaCliente(Cliente cliente);

    Dialog onCreateDialog(Bundle savedInstanceState);
}
