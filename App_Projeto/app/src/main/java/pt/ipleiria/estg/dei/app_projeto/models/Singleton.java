package pt.ipleiria.estg.dei.app_projeto.models;

import android.app.Application;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import pt.ipleiria.estg.dei.app_projeto.listeners.LoginListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.UserListener;
import pt.ipleiria.estg.dei.app_projeto.utils.PlanosTreinoJSONParser;
import pt.ipleiria.estg.dei.app_projeto.utils.UserJSONParser;


public class Singleton extends Application {

    private ArrayList<Cargo> cargos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Desconto> descontos;
    private ArrayList<Ementa> ementa;
    private ArrayList<Exercicio> exercicios;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<ListaPlanos> listaPlanos;
    private ArrayList<PlanosNutricao> planosNutricaos;
    private ArrayList<PlanosTreino> planosTreinos;
    private ArrayList<Subscricao> subscricaos;
    private ArrayList<TipoSubscricao> tipoSubscricaos;

    public List<PlanosTreino> planostreinoAPI;
    public List<PlanosNutricao> planosnutricaoAPI;

    private LoginListener loginListener;
    private int User_id = 4;
    private UserListener userListener;

    // Resto dos Listeners

    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();

    private static RequestQueue volleyQueue = null;
    private String tokenAPI = "";
    private String urlAPI = "http://" + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null) + "/projetoWeb/api/web/v1";
    private String ipURL;

    private String UrlAPIusersLogin = "http://localhost/ProjetoWeb/api/web/v1/userregisterandlogin/loginuser";

    private FitnessLeagueBDHelper fitnessLeagueBDHelper = null;


    private static Singleton INSTANCE = null;

    public static synchronized Singleton getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Singleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private Singleton(Context context) {
        cargos = new ArrayList<>();
        clientes = new ArrayList<>();
        descontos = new ArrayList<>();
        ementa = new ArrayList<>();
        exercicios = new ArrayList<>();
        funcionario = new ArrayList<>();
        listaPlanos = new ArrayList<>();
        planosNutricaos = new ArrayList<>();
        planosTreinos = new ArrayList<>();
        subscricaos = new ArrayList<>();
        tipoSubscricaos = new ArrayList<>();

        fitnessLeagueBDHelper = new FitnessLeagueBDHelper(context);
    }

    public void setIp(String ip) {
        SharedPreferencesConfig.write(SharedPreferencesConfig.IP, ip);
        urlAPI = "http://" + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null) + "/projetoWeb/api/web/v1";
    }

    public String getIp() {
        ipURL = SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null);
        return ipURL;
    }

    public ArrayList<Cargo> getCargos() {
        return cargos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Desconto> getDescontos() {
        return descontos;
    }

    public ArrayList<Ementa> getEmenta() {
        return ementa;
    }

    public ArrayList<Funcionario> getFuncionario() {
        return funcionario;
    }

    public ArrayList<ListaPlanos> getListaPlanos() {
        return listaPlanos;
    }

    public ArrayList<PlanosNutricao> getPlanosNutricaos() {
        return planosNutricaos;
    }

    public ArrayList<PlanosTreino> getPlanosTreinos() {
        return planosTreinos;
    }

    public ArrayList<Subscricao> getSubscricaos() {
        return subscricaos;
    }

    public ArrayList<TipoSubscricao> getTipoSubscricaos() {
        return tipoSubscricaos;
    }


    public Cliente getCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getIDCliente() == id) {
                return c;
            }
        }
        return null;
    }

    public String getEncrypted(String plainText) {
        if (plainText == null) {
            return null;
        }
        Key salt = getSalt();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encodedValue, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed to encrypt data");
    }

    static Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void verificaLoginAPI_POST(final String username, final String password, final Context context, final boolean isConnected) {
        if (!isConnected) {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, UrlAPIusersLogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    User user = UserJSONParser.parserJsonUser(response, context);
                    userListener.onRefreshListaUser(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            volleyQueue.add(request);
        }
    }

    public int getIdUser() {
        return User_id;
    }

    public void setIdUser(int idUser) {
        this.User_id = User_id;
    }

    public void adicionarPlanosTreinoBD(ArrayList<PlanosTreino> planostreinos) {
        fitnessLeagueBDHelper.removeAllPlanosTreinoBD();
        for (PlanosTreino planosTreino : planostreinos) {
            fitnessLeagueBDHelper.adicionarPlanoTreino(planosTreino);
        }
    }

    public void adicionarPlanosNutricaoBD(ArrayList<PlanosNutricao> planosnutri) {
        fitnessLeagueBDHelper.removeAllPlanosNutricaoBD();
        for (PlanosNutricao planosNutricao : planosnutri) {
            fitnessLeagueBDHelper.adicionarPlanoNutricao(planosNutricao);
        }
    }

    /*
        public void getAllExerFromPlanosTreino(final Context context, boolean isConnected, ArrayList<PlanosTreino> planosTreinos){
            if(!isConnected){
                planosTreinos = fitnessLeagueBDHelper.getAllPlanosTreino();
                if()
            }else{

            }
        }

    */
    public void planostreinoAPI(final Context context, final boolean isConnected) {
        if (!isConnected) {

        } else {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    planosTreinos = PlanosTreinoJSONParser.parserJsonPlanosTreino(response, context);
                    adicionarPlanosTreinoBD(planosTreinos);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            );
            volleyQueue.add(request);
        }
    }
}
