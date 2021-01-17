package pt.ipleiria.estg.dei.app_projeto.listeners;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;
import java.util.ArrayList;

public interface PlanosNutricaoListener {
    void onRefreshPlanosTreino(ArrayList<PlanosTreino> planosTreinos);
    void onUpdatePlanosTreino(PlanosTreino planosTreino, int operacao);
}
