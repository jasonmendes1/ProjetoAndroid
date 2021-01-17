package pt.ipleiria.estg.dei.app_projeto.listeners;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;
import java.util.ArrayList;

public interface PlanosTreinoListener {
    void onRefreshPlanosNutricao(ArrayList<PlanosNutricao> planosNutricaos);
    void onUpdatePlanoNutricao(PlanosNutricao planosNutricao, int operacao);
}
