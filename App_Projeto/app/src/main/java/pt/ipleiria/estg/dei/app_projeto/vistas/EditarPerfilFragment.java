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

    private EditText etNome, etApelido, etSexo2, etEmail2, etTelefone2, etDataNasc2, etNif2, etAltura, etPeso, etMassaMuscular, etMassaGorda;
    private SharedPreferences sharedPreferences;
    private RequestQueue mQueue;
    private String urlAPI, ipURL;
    private Button buttonEditar;
    private int ID_User;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        mQueue = Volley.newRequestQueue(getContext());

        etNome = rootView.findViewById(R.id.editTextNome);
        etApelido = rootView.findViewById(R.id.editTextApelido);
        etDataNasc2 = rootView.findViewById(R.id.editTextDataNasc);
        etTelefone2 = rootView.findViewById(R.id.editTextTelefone);
        etNif2 = rootView.findViewById(R.id.editTextNIF);
        etSexo2 = rootView.findViewById(R.id.editTextSexo);
        etAltura = rootView.findViewById(R.id.etAltura);
        etPeso = rootView.findViewById(R.id.etPeso);
        etMassaMuscular = rootView.findViewById(R.id.etMassaMuscular);
        etMassaGorda = rootView.findViewById(R.id.etMassaGorda);






        ipURL = Singleton.getInstance(getContext()).getIPInput();
        urlAPI = "http://" + ipURL + "/ProjetoWeb/api/web/v1/cliente/get";

        ID_User = Singleton.getInstance(getContext()).getIdUser();
        System.out.println("--> url ClienteEDITARAPI: " + urlAPI + "/" + ID_User);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlAPI + "/" + ID_User, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("-->RESPOSTAEDITARPERFIL ");
                        try {
                            JSONObject cliente = response.getJSONObject(0);
                            String ClienteNome = cliente.getString("ClientePrimeiroNome");
                            String ClienteApelido = cliente.getString("ClienteApelido");
                            String ClienteDataNasc = cliente.getString("ClienteDataNasc");
                            int ClienteNumTele = cliente.getInt("ClienteNumTele");
                            int ClienteNIF = cliente.getInt("ClienteNIF");
                            String ClienteSexo = cliente.getString("ClienteSexo");
                            int ClienteAltura = cliente.getInt("ClienteAltura");
                            int ClientePeso = cliente.getInt("ClientePeso");
                            int ClienteMassaMuscular = cliente.getInt("ClienteMassaMuscular");
                            int ClienteMassaGorda = cliente.getInt("ClienteMassaGorda");

                            etNome.setText(ClienteNome);
                            System.out.println("--> tvNome: " + etNome.getText());

                            etApelido.setText(ClienteApelido);
                            System.out.println("--> tvNome: " + etApelido.getText());

                            etDataNasc2.setText(ClienteDataNasc);
                            System.out.println("--> tvNome: " + etDataNasc2.getText());

                            etTelefone2.setText(String.valueOf(ClienteNumTele));
                            System.out.println("--> tvNome: " + etTelefone2.getText());

                            etNif2.setText(String.valueOf(ClienteNIF));
                            System.out.println("--> tvNome: " + etNif2.getText());

                            etSexo2.setText(ClienteSexo);
                            System.out.println("--> tvNome: " + etSexo2.getText());


                            etAltura.setText(String.valueOf(ClienteAltura));
                            System.out.println("--> tvNome: " + etAltura.getText());

                            etPeso.setText(String.valueOf(ClientePeso));
                            System.out.println("--> tvNome: " + etPeso.getText());

                            etMassaMuscular.setText(String.valueOf(ClienteMassaMuscular));
                            System.out.println("--> tvNome: " + etMassaMuscular.getText());

                            etMassaGorda.setText(String.valueOf(ClienteMassaGorda));
                            System.out.println("--> tvNome: " + etMassaGorda.getText());


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
                etApelido.clearFocus();
                etDataNasc2.clearFocus();
                etTelefone2.clearFocus();
                etNif2.clearFocus();
                etSexo2.clearFocus();
                etAltura.clearFocus();
                etPeso.clearFocus();
                etMassaMuscular.clearFocus();
                etMassaGorda.clearFocus();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlAPI + "/edit?IdUser=" + ID_User
                        + "&ClientePrimeiroNome=" +  etNome.getText().toString() + "&ClienteApelido=" + etApelido.getText().toString() + "&ClienteDataNasc=" + etDataNasc2.getText().toString()
                        + "&ClienteSexo=" + etSexo2.getText().toString() + "&ClienteNumTele" + etTelefone2.getText().toString() + "&ClienteNIF" + etNif2.getText().toString()
                        + "&ClienteAltura" + etAltura.getText().toString() + "&ClientePeso" + etPeso.getText().toString() + "&ClienteMassaMuscular" + etMassaMuscular.getText().toString()
                        + "&ClienteMassaGorda" + etMassaGorda.getText().toString(), null,
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