package pt.ipleiria.estg.dei.app_projeto.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.Exercicio;

public interface ExerciciosListener {
    void onRefreshExericios(ArrayList<Exercicio> exercicios);
    void onUpdateExercicios(Exercicio exercicio, int operacao);
}
