package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import pt.ipleiria.estg.dei.app_projeto.R;

public class PerfilFragment extends Fragment {

    private TextView tvNome, tvSexo2, tvEmail2, tvDataNasc2, tvDataInicio, tvDataExpirar;
    private SharedPreferences sharedPreferences;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}
