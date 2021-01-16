package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

public class EditarPerfilFragment extends Fragment {

    private EditText etNome, etSexo2, etEmail2, etTelefone2, etDataNasc2, etNif2, etDataInicio, etDataExpirar;
    private SharedPreferences sharedPreferences;
    private RequestQueue mQueue;
    private String urlAPI, ipURL;
    private Button buttonEditar;
    private int User_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        mQueue = Volley.newRequestQueue(getContext());

        etNome = rootView.findViewById(R.id.editTextNome);
        etSexo2 = rootView.findViewById(R.id.editTextSexo);
        etEmail2 = rootView.findViewById(R.id.editTextEmail);
        etTelefone2 = rootView.findViewById(R.id.editTextTelefone);
        etDataNasc2 = rootView.findViewById(R.id.editTextDataNasc);


        ipURL = Singleton.getInstance(getContext()).getIp();
        urlAPI = "http://" + ipURL + "/ProjetoWeb/api/web/v1/cliente";

        User_id = Singleton.getInstance(getContext()).getIdUser();
        System.out.println("--> url ClienteAPI: " + urlAPI + "/" + User_id);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlAPI + "/" + User_id, null,
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

                            etNome.setText(ClienteNome);
                            etSexo2.setText(ClienteSexo);
                            etDataNasc2.setText(ClienteDataNasc);
                            etTelefone2.setText(ClienteTelefone);
                            etNif2.setText(ClienteNif);
                            etDataInicio.setText(SubscricaoDataInicio);
                            etDataExpirar.setText(SubscricaoDataExpirar);

                            System.out.println("--> tvNome: " + etNome.getText());

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

        buttonEditar = rootView.findViewById(R.id.buttonEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNome.clearFocus();
                etSexo2.clearFocus();
                etDataNasc2.clearFocus();
                etTelefone2.clearFocus();
                etNif2.clearFocus();
                etDataInicio.clearFocus();
                etDataExpirar.clearFocus();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlAPI + "/edit?User_id=" + User_id
                        + "&clienteprimeiroNome=" + etNome.getText().toString() + "&clienteSexo=" + etSexo2.getText().toString(), null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println("--> Error: " + error.getMessage());
                    }
                });
                mQueue.add(request);
            }
        });
        return rootView;
    }
}