package pt.ipleiria.estg.dei.app_projeto.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;
import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;

public interface PlanosTreinoListener {
    void onRefreshPlanosNutricao(ArrayList<PlanosTreino> planosNutricaos);
    void onUpdatePlanoNutricao(PlanosNutricao planosNutricao, int operacao);
}
