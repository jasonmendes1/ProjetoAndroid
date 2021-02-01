package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pt.ipleiria.estg.dei.app_projeto.R;

public class PlanosTreinoFragment extends Fragment {
    public PlanosTreinoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planos__treino, container, false);
    }
}