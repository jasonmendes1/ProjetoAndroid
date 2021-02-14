package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.listeners.UserListener;
import pt.ipleiria.estg.dei.app_projeto.models.Cliente;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.utils.ClienteJSONParser;

public class EditarPerfilFragment extends Fragment implements UserListener {

    private EditText etNome, etApelido, etSexo2, etEmail2, etTelefone2, etDataNasc2, etNif2, etAltura, etPeso, etMassaMuscular, etMassaGorda;
    private SharedPreferences sharedPreferences;
    private RequestQueue mQueue;
    private String urlAPI, ipURL;
    private Button buttonEditar;
    private int ID_User;
    public static final String ID = "ID";
    private Cliente cliente;
    private String token;
    private ImageView ivAvatar;
    private static final String DEFAULT_IMAGE = "https://cdn4.iconfinder.com/data/icons/famous-character-vol-2-flat/48/Avatar_Famous_Characters-04-512.png";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        mQueue = Volley.newRequestQueue(getContext());

        final int id = getActivity().getIntent().getIntExtra(ID, -1);
        cliente = Singleton.getInstance(getActivity().getApplicationContext()).getCliente(id);

        //SharedPreferences sharedPrefUser = getActivity().getSharedPreferences(MenuMainActivity.USER, Context.MODE_PRIVATE);
        //token = sharedPrefUser.getString(MenuMainActivity.TOKEN, "TOKEN");

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
        ivAvatar = rootView.findViewById(R.id.ivAvatar);

        Singleton.getInstance(getContext()).setUserListener(this);


        ipURL = Singleton.getInstance(getContext()).getIPInput();
        urlAPI = "http://" + ipURL + "/ProjetoWeb/api/web/v1/cliente/get";
        ID_User = Singleton.getInstance(getContext()).getIdUser();
        System.out.println("--> url ClienteEDITARAPI: " + urlAPI + "/" + ID_User);

        buttonEditar = rootView.findViewById(R.id.buttonEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClienteJSONParser.isConnectionInternet(getActivity().getApplicationContext())) {
                    if (cliente != null)
                        if (validarCliente() == true) {
                            cliente.setPrimeiroNome(etNome.getText().toString());
                            cliente.setApelido(etApelido.getText().toString());
                            cliente.setDta_nascimento(Integer.parseInt(etDataNasc2.getText().toString()));
                            cliente.setNum_tele(Integer.parseInt(etTelefone2.getText().toString()));
                            cliente.setNif(Integer.parseInt(etNif2.getText().toString()));
                            cliente.setSexo(etSexo2.getText().toString());
                            cliente.setAltura(Integer.parseInt(etAltura.getText().toString()));
                            cliente.setPeso(Integer.parseInt(etPeso.getText().toString()));
                            cliente.setMassa_muscular(Integer.parseInt(etMassaMuscular.getText().toString()));
                            cliente.setMassa_gorda(Integer.parseInt(etMassaGorda.getText().toString()));

                            Singleton.getInstance(getActivity().getApplicationContext()).editarClienteAPI(cliente, getActivity().getApplicationContext());
                        } else return;
                    //setResult(RESULT_OK);
                    //finish();
                } else
                    Toast.makeText(getActivity(), "Não há Internet", Toast.LENGTH_SHORT).show();
                    System.out.println("--> NADA ");

            }
        });

        if (cliente != null)
            carregarCliente();
        return rootView;
    }

    private boolean validarCliente() {
        String ClientePrimeiroNome = etNome.getText().toString();
        String ClienteApelido = etApelido.getText().toString();
        String ClienteDataNasc = etDataNasc2.getText().toString();
        String ClienteNumTele = etTelefone2.getText().toString();
        String ClienteNIF = etNif2.getText().toString();
        String ClienteSexo = etSexo2.getText().toString();
        String ClienteAltura = etAltura.getText().toString();
        String ClientePeso = etPeso.getText().toString();
        String ClienteMassaMuscular = etMassaMuscular.getText().toString();
        String ClienteMassaGorda = etMassaGorda.getText().toString();

        if (ClientePrimeiroNome.length() < 3) {
            etNome.setError("Nome Inválido");
            return false;
        }

        if (ClienteApelido.length() < 3) {
            etApelido.setError("Apelido Inválido");
            return false;
        }

        if (ClienteDataNasc.length() < 3) {
            etDataNasc2.setError("Data Inválida");
            return false;
        }

        if (ClienteNumTele.length() < 9) {
            etTelefone2.setError("Telefone Inválido");
            return false;
        }

        if (ClienteNIF.length() < 9) {
            etNif2.setError("NIF Inválido");
            return false;
        }

        if (ClienteSexo.length() > 3) {
            etSexo2.setError("Sexo Inválido");
            return false;
        }

        if (ClienteAltura.length() > 3) {
            etAltura.setError("Altura Inválido");
            return false;
        }

        if (ClientePeso.length() > 3) {
            etPeso.setError("Peso Inválido");
            return false;
        }

        if (ClienteMassaMuscular.length() > 3) {
            etMassaMuscular.setError("Massa Muscular Inválido");
            return false;
        }

        if (ClienteMassaGorda.length() > 3) {
            etMassaGorda.setError("Massa Gorda Inválido");
            return false;
        }

        //ctrl + alt + t
        try {
            Integer.parseInt(ClienteDataNasc);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etDataNasc2.setError("Data Inválido");
            return false;
        }

        try {
            Integer.parseInt(ClienteNumTele);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etTelefone2.setError("Telefone Inválido");
            return false;
        }

        try {
            Integer.parseInt(ClienteNIF);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etNif2.setError("NIF Inválido");
            return false;
        }

        try {
            Integer.parseInt(ClienteAltura);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etAltura.setError("Altura Inválida");
            return false;
        }

        try {
            Integer.parseInt(ClientePeso);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etPeso.setError("Peso Inválido");
            return false;
        }

        try {
            Integer.parseInt(ClienteMassaMuscular);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etMassaMuscular.setError("Massa Muscular Inválida");
            return false;
        }

        try {
            Integer.parseInt(ClienteMassaGorda);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            etMassaGorda.setError("Massa Gorda Inválida");
            return false;
        }

        return true;
    }

    private void carregarCliente() {
        etNome.setText(cliente.getPrimeiroNome());
        etApelido.setText(cliente.getApelido());
        etDataNasc2.setText(cliente.getDta_nascimento() + "");
        etSexo2.setText(cliente.getSexo());
        etApelido.setText(cliente.getApelido());
        Glide.with(this)
                .load(cliente.getAvatar())
                .placeholder(R.drawable.avatar_example)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivAvatar);
        etTelefone2.setText(cliente.getNum_tele() + "");
        etNif2.setText(cliente.getNif() + "");
        etAltura.setText(cliente.getAltura() + "");
        etPeso.setText(cliente.getPeso() + "");
        etMassaMuscular.setText(cliente.getMassa_muscular() + "");
        etMassaGorda.setText(cliente.getMassa_gorda() + "");
    }

    @Override
    public void onRefreshListaCliente(Cliente cliente) {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return null;
    }
}