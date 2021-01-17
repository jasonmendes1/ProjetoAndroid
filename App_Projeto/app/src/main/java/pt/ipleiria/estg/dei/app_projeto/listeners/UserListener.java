package pt.ipleiria.estg.dei.app_projeto.listeners;

import android.app.Dialog;
import android.os.Bundle;

import pt.ipleiria.estg.dei.app_projeto.models.Cliente;
import pt.ipleiria.estg.dei.app_projeto.models.User;

public interface UserListener {
    void onRefreshListaUser(User user);
    void onRefrshListaCliente(Cliente cliente);

    Dialog onCreateDialog(Bundle savedInstanceState);
}
