package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class PerfilFragment extends Fragment {

    private TextView tvNome, tvSexo2, tvEmail2, tvTelefone2, tvDataNasc2, tvNif2, tvDataInicio, tvDataExpirar;
    private SharedPreferences sharedPreferences;
    private RequestQueue mQueue;
    private String urlAPI, ipURL;
    private int ID_User;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
        mQueue = Volley.newRequestQueue(getContext());

        tvNome = rootView.findViewById(R.id.textViewNome);
        tvSexo2 = rootView.findViewById(R.id.textViewSexo2);
        tvDataNasc2 = rootView.findViewById(R.id.textViewDataNasc2);
        tvTelefone2 = rootView.findViewById(R.id.textViewTelefone2);
        tvNif2 = rootView.findViewById(R.id.textViewNif2);
        tvDataInicio = rootView.findViewById(R.id.textViewDataInicio);
        tvDataExpirar = rootView.findViewById(R.id.textViewDataExpirar);

        ipURL = Singleton.getInstance(getContext()).getIPInput();
        urlAPI = "http://" + ipURL + "/ProjetoWeb/api/web/v1/user/cliente";

        ID_User = Singleton.getInstance(getContext()).getIdUser();
        System.out.println("--> url ClienteAPI: " + urlAPI + "/" + ID_User);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlAPI + "/" + ID_User, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("-->RESPOSTA ");
                        try {
                            JSONObject cliente = response.getJSONObject(0);
                            String ClienteNome = cliente.getString("ClienteNome");
                            String ClienteSexo = cliente.getString("ClienteSexo");
                            int ClienteDataNasc = cliente.getInt("ClienteDataNasc");
                            int ClienteTelefone = cliente.getInt("ClienteTelefone");
                            int ClienteNif = cliente.getInt("ClienteNif");
                            String SubscricaoDataInicio = cliente.getString("SubscricaoDataInicio");
                            String SubscricaoDataExpirar = cliente.getString("SubscricaoDataExpirar");

                            tvNome.setText(ClienteNome);
                            tvSexo2.setText(ClienteSexo);
                            tvDataNasc2.setText(ClienteDataNasc);
                            tvTelefone2.setText(ClienteTelefone);
                            tvNif2.setText(ClienteNif);
                            tvDataInicio.setText(SubscricaoDataInicio);
                            tvDataExpirar.setText(SubscricaoDataExpirar);

                            System.out.println("--> tvNome: " + tvNome.getText());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
        return rootView;

    }

}
