package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.adapters.ListaPlanosNutricaoAdapter;
import pt.ipleiria.estg.dei.app_projeto.listeners.PlanosNutricaoListener;
import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.utils.PlanosNutricaoJSONParser;

public class PlanosNutricaoFragment extends Fragment implements PlanosNutricaoListener {

    private ListView lvPlanosNutricao;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isHistorico = false;
    private ListaPlanosNutricaoAdapter listaPlanosNutricaoAdapter;

    public PlanosNutricaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.fragment_planos_nutricao, container, false);


        lvPlanosNutricao = view.findViewById(R.id.lvPlanosNutricao);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);


        lvPlanosNutricao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanosNutricao planosNutricao = (PlanosNutricao) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), PlanosNutricao.class);
                intent.putExtra("DETALHES",planosNutricao.getIDPlanoNutricao());
                startActivity(intent);
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Singleton.getInstance(getContext()).getAllPlanosNutricaoFromClientAPI(getContext(), PlanosNutricaoJSONParser.isConnectionInternet(getContext()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        Singleton.getInstance(getContext()).getAllPlanosNutricaoFromClientAPI(getContext(), PlanosNutricaoJSONParser.isConnectionInternet(getContext()));
        return view;
    }

    @Override
    public void onRefreshPlanosNutricao(ArrayList<PlanosNutricao> planosNutricaos) {
        System.out.println("--> onRefreshPlanosTreino" + planosNutricaos);
        if (!planosNutricaos.isEmpty()) {
            listaPlanosNutricaoAdapter = new ListaPlanosNutricaoAdapter(getContext(), planosNutricaos);
            lvPlanosNutricao.setAdapter(listaPlanosNutricaoAdapter);
            listaPlanosNutricaoAdapter.refresh(planosNutricaos);
        }
    }

    @Override
    public void onUpdatePlanosNutricao(PlanosNutricao planosNutricao, int operacao) {

    }
}