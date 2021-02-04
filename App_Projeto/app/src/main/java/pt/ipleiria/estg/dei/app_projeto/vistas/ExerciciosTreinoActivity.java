package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.listeners.ExerciciosListener;
import pt.ipleiria.estg.dei.app_projeto.models.Exercicio;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.utils.ExerciciosJSONParser;

public class ExerciciosTreinoActivity extends AppCompatActivity implements ExerciciosListener {
    public TextView tvNomeEx, tvRepeticoesEx, tvTempoEx, tvNrSeriesEx, tvTempoRepousoEx, tvTempoTotalEx, tvNrMaquinaEx;
    public static  final String ID_PLANOTREINO = "IDPlanoTreino";
    public static  final String NOME_EXERCICIO = "nome";
    public static  final String FEED = "-1";
    private Exercicio exercicio;
    public int idPlanoTreino;
    private FloatingActionButton fabProcura;
    private String urlAPI;
    private String ipURL;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_exerciciostreino);
        final int idplanotreino = getIntent().getIntExtra(ID_PLANOTREINO, -1);
        exercicio = Singleton.getInstance(getApplicationContext()).getExercicio(idplanotreino);

        System.out.println("--> exercicio : " + exercicio);


        idPlanoTreino = getIntent().getIntExtra(ID_PLANOTREINO, 0);
        System.out.println("--> idPlanoTreino: " + idPlanoTreino);
        final String nomeExercicio = getIntent().getStringExtra(NOME_EXERCICIO);
        //fabProcura = findViewById(R.id.fabProcura);

        mQueue = Volley.newRequestQueue(this);
        tvNomeEx = findViewById(R.id.tvNomeEx);
        tvRepeticoesEx = findViewById(R.id.tvRepeticoesEx);
        tvTempoEx = findViewById(R.id.tvTempoEx);
        tvNrSeriesEx = findViewById(R.id.tvNrSeriesEx);
        tvTempoRepousoEx = findViewById(R.id.tvTempoRepousoEx);
        tvTempoTotalEx = findViewById(R.id.tvTempoTotalEx);
        tvNrMaquinaEx = findViewById(R.id.tvNrMaquinaEx);

        Singleton.getInstance(getApplicationContext()).setExerciciosListener(this);
        Singleton.getInstance(getApplicationContext()).getAllExerciciosFromClientAPI(getApplicationContext(), ExerciciosJSONParser.isConnectionInternet(getApplicationContext()));
        System.out.println("--> nome ex: " + exercicio);
        carregardetalhesexercicios();
    }

    private void carregardetalhesexercicios(){
        tvNomeEx.setText(exercicio.getNome());
        tvRepeticoesEx.setText(exercicio.getRepeticoes()+"");
        tvTempoEx.setText(exercicio.getTempo()+"");
        tvNrSeriesEx.setText(exercicio.getSerie()+"");
        tvTempoRepousoEx.setText(exercicio.getRepouso()+"");
        tvTempoTotalEx.setText(exercicio.getTempo_total()+"");
        tvNrMaquinaEx.setText(exercicio.getNum_maquina()+"");
        System.out.println("--> Exercicio Nome" + tvNomeEx);
    }

    @Override
    public void onRefreshExericios(ArrayList<Exercicio> exercicios) {

    }

    @Override
    public void onUpdateExercicios(Exercicio exercicio, int operacao) {

    }
}
