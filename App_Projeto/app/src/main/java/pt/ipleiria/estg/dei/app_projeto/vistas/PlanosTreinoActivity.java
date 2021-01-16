package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class PlanosTreinoActivity extends AppCompatActivity {

    private ListView lista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planos_treino);
        Singleton.getInstance(getApplicationContext()).planostreinoAPI();
        lista = findViewById(R.id.lvPlanosTreino);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanosTreino pl = (PlanosTreino) parent.getItemAtPosition(position);
                Singleton.getInstance(getApplicationContext()).
            }
        });
    }



}