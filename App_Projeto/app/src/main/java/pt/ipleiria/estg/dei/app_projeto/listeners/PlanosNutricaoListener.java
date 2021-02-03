package pt.ipleiria.estg.dei.app_projeto.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;

public interface PlanosNutricaoListener {
    void onRefreshPlanosNutricao(ArrayList<PlanosNutricao> planosNutricaos);
    void onUpdatePlanosNutricao(PlanosNutricao planosNutricao, int operacao);
}
