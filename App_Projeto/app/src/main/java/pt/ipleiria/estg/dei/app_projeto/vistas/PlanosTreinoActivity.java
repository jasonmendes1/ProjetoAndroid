package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.R;

public class PlanosTreinoActivity extends AppCompatActivity /*implements PlanosTreinoListener*/ {

    public static final String ID_EXERCICIO = "idExercicio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planos_treino);
    }
}