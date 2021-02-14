package pt.ipleiria.estg.dei.app_projeto.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.Ementa;

public interface EmentaListener {
    void onRefreshEmenta(ArrayList<Ementa> ementas);
    void onUpdateEmenta(Ementa ementa, int operacao);
}
