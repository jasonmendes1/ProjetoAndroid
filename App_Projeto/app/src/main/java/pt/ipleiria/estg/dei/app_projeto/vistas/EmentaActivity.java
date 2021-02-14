package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.listeners.EmentaListener;
import pt.ipleiria.estg.dei.app_projeto.models.Ementa;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.utils.EmentaJSONParser;

public class EmentaActivity extends AppCompatActivity implements EmentaListener {
    public TextView tv2PequenoAlmoco, tv2Almoco, tv2Lanche1, tv2Lanche2, tv2Jantar;
    public TextView tv3PequenoAlmoco, tv3Almoco, tv3Lanche1, tv3Lanche2, tv3Jantar;
    public TextView tv4PequenoAlmoco, tv4Almoco, tv4Lanche1, tv4Lanche2, tv4Jantar;
    public TextView tv5PequenoAlmoco, tv5Almoco, tv5Lanche1, tv5Lanche2, tv5Jantar;
    public TextView tv6PequenoAlmoco, tv6Almoco, tv6Lanche1, tv6Lanche2, tv6Jantar;
    public TextView tvSabadoPequenoAlmoco, tvSabadoAlmoco, tvSabadoLanche1, tvSabadoLanche2, tvSabadoJantar;

    public static  final String ID_PLANONUTRICAO = "IDPlanoNutricao";


    private Ementa ementa;
    public int idPlanoNutricao;

    private RequestQueue mQueue;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_ementa);

        int idplanonutricao = getIntent().getIntExtra(ID_PLANONUTRICAO, -1);
        ementa = Singleton.getInstance(getApplicationContext()).getEmenta(idplanonutricao);

        mQueue = Volley.newRequestQueue(this);

        tv2PequenoAlmoco = findViewById(R.id.tv2PequenoAlmoco);
        tv2Almoco = findViewById(R.id.tv2Almoco);
        tv2Lanche1 = findViewById(R.id.tv2Lanche1);
        tv2Lanche2 = findViewById(R.id.tv2Lanche2);
        tv2Jantar = findViewById(R.id.tv2Jantar);

        tv3PequenoAlmoco = findViewById(R.id.tv3PequenoAlmoco);
        tv3Almoco = findViewById(R.id.tv3Almoco);
        tv3Lanche1 = findViewById(R.id.tv3Lanche1);
        tv3Lanche2 = findViewById(R.id.tv3Lanche2);
        tv3Jantar = findViewById(R.id.tv3Jantar);

        tv4PequenoAlmoco = findViewById(R.id.tv4PequenoAlmoco);
        tv4Almoco = findViewById(R.id.tv4Almoco);
        tv4Lanche1 = findViewById(R.id.tv4Lanche1);
        tv4Lanche2 = findViewById(R.id.tv4Lanche2);
        tv4Jantar = findViewById(R.id.tv4Jantar);

        tv5PequenoAlmoco = findViewById(R.id.tv5PequenoAlmoco);
        tv5Almoco = findViewById(R.id.tv5Almoco);
        tv5Lanche1 = findViewById(R.id.tv5Lanche1);
        tv5Lanche2 = findViewById(R.id.tv5Lanche2);
        tv5Jantar = findViewById(R.id.tv5Jantar);

        tv6PequenoAlmoco = findViewById(R.id.tv6PequenoAlmoco);
        tv6Almoco = findViewById(R.id.tv6Almoco);
        tv6Lanche1 = findViewById(R.id.tv6Lanche1);
        tv6Lanche2 = findViewById(R.id.tv6Lanche2);
        tv6Jantar = findViewById(R.id.tv6Jantar);

        tvSabadoPequenoAlmoco = findViewById(R.id.tvSabadoPequenoAlmoco);
        tvSabadoAlmoco = findViewById(R.id.tvSabadoAlmoco);
        tvSabadoLanche1 = findViewById(R.id.tvSabadoLanche1);
        tvSabadoLanche2 = findViewById(R.id.tvSabadoLanche2);
        tvSabadoJantar = findViewById(R.id.tvSabadoJantar);

        idPlanoNutricao = getIntent().getIntExtra(ID_PLANONUTRICAO, 0);
        System.out.println("--> idPlanoTreino: " + idPlanoNutricao);

        System.out.println("--> ementa : " + ementa);

        Singleton.getInstance(getApplicationContext()).setEmentaListener(this);
        Singleton.getInstance(getApplicationContext()).getAllEmentaFromClientAPI(getApplicationContext(), EmentaJSONParser.isConnectionInternet(getApplicationContext()));


        if (ementa != null) {
            carregardetalhesementa();
        }
        else
            System.out.println("--> NADA: ");
    }

    private void carregardetalhesementa(){

        tv2PequenoAlmoco.setText(ementa.getPequenoalmoco());
        tv2Almoco.setText(ementa.getAlmoco());
        tv2Lanche1.setText(ementa.getLanche1());
        tv2Lanche2.setText(ementa.getLanche2());
        tv2Jantar.setText(ementa.getJantar());



        System.out.println("--> Exercicio Nome" + tv2PequenoAlmoco);
    }

    @Override
    public void onRefreshEmenta(ArrayList<Ementa> ementas) {

    }

    @Override
    public void onUpdateEmenta(Ementa ementa, int operacao) {

    }
}