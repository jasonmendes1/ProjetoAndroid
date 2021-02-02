package pt.ipleiria.estg.dei.app_projeto.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;

public interface PlanosTreinoListener {
    void onRefreshPlanosTreino(ArrayList<PlanosTreino> planosTreinos);
    void onUpdatePlanoTreino(PlanosTreino planosTreino, int operacao);
}
