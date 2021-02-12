package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.adapters.ListaPlanosTreinoAdapter;
import pt.ipleiria.estg.dei.app_projeto.listeners.PlanosTreinoListener;
import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.utils.PlanosTreinoJSONParser;

public class PlanosTreinoFragment extends Fragment implements PlanosTreinoListener {

    private ListView lvPlanosTreino;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isHistorico = false;
    private ListaPlanosTreinoAdapter listaPlanosTreinoAdapter;
    public static final String IDPlanoTreino = "IDPlanoTreino";
    private int ID_User;
    private Button btnPDF;

    public static final int RESULT_CODE_VER = 10;




    public PlanosTreinoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.fragment_planos__treino, container, false);

        //btnPDF = view.findViewById(R.id.buttonPDF);


        lvPlanosTreino = view.findViewById(R.id.lvPlanosTreino);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);


        lvPlanosTreino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanosTreino temp = (PlanosTreino)  parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), ExerciciosTreinoActivity.class);
                intent.putExtra(ExerciciosTreinoActivity.ID_PLANOTREINO, temp.getIDPlanoTreino());
                startActivityForResult(intent, RESULT_CODE_VER);
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    Singleton.getInstance(getContext()).getAllPlanosTreinoFromClientAPI(getContext(), PlanosTreinoJSONParser.isConnectionInternet(getContext()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

/*        btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadFotoActivity.class);
                startActivity(intent);
            }
        });
*/
        Singleton.getInstance(getContext()).setPlanosTreinoListener(this);
        Singleton.getInstance(getContext()).getAllPlanosTreinoFromClientAPI(getContext(), PlanosTreinoJSONParser.isConnectionInternet(getContext()));

        return view;
    }

    @Override
    public void onRefreshPlanosTreino(ArrayList<PlanosTreino> planosTreinos) {
        System.out.println("--> onRefreshPlanosTreino" + planosTreinos.toString());
        if (!planosTreinos.isEmpty()) {
            listaPlanosTreinoAdapter = new ListaPlanosTreinoAdapter(getContext(), planosTreinos);
            lvPlanosTreino.setAdapter(listaPlanosTreinoAdapter);
            listaPlanosTreinoAdapter.refresh(planosTreinos);
        }
    }

    @Override
    public void onUpdatePlanoTreino(PlanosTreino planosTreino, int operacao) {

    }

}