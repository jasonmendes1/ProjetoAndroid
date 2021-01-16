package pt.ipleiria.estg.dei.app_projeto.models;

import android.app.Application;
import android.content.Context;
import android.util.Base64;

import org.json.JSONArray;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import pt.ipleiria.estg.dei.app_projeto.listeners.LoginListener;

public class Singleton extends Application {

    private ArrayList<Cargo> cargos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Desconto> descontos;
    private ArrayList<Ementa> ementa;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<ListaPlanos> listaPlanos;
    private ArrayList<PlanosNutricao> planosNutricaos;
    private ArrayList<PlanosTreino> planosTreinos;
    private ArrayList<Subscricao> subscricaos;
    private ArrayList<TipoSubscricao> tipoSubscricaos;

    public List<PlanosTreino> planostreinoAPI;
    public List<PlanosNutricao> planosnutricaoAPI;

    private LoginListener loginListener;
    private int IDUser = 4;
    // Resto dos Listeners

    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();

    private static RequestQueue volleyQueue = null;
    private String tokenAPI = "";
    private String urlAPI = "http://" + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null) + "/projetoWeb/api";
    private String ipURL;

    private FitnessLeagueBDHelper fitnessLeagueBDHelper = null;


    private static Singleton INSTANCE = null;

    public static synchronized Singleton getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new Singleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private Singleton(Context context){
        cargos = new ArrayList<>();
        clientes = new ArrayList<>();
        descontos = new ArrayList<>();
        ementa = new ArrayList<>();
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
        urlAPI = "http://" + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null) + "/projetoweb/api";
        //UrlAPI = "http://192.168.1.7/MusicaeWeb/backend/web/v1";
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

    public String getEncrypted(String plainText) {
        if (plainText == null) {
            return null;
        }
        Key salt = getSalt();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encodedValue,Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed to encrypt data");
    }

    static Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }

    public void setLoginListener(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    public void verificaLoginAPI_POST(final String username, final String password){
        System.out.println("--> url:" + UrlAPI + "/user/verificaLogin?username="+ username +"&password_hash="+ password);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, UrlAPI + "/user/verificaLogin?username="+ username +"&password_hash="+ password, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(loginListener!=null){
                    loginListener.onRefreshLogin(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> Erro: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public int getIdUser(){
        return IDUser;
    }

    public void setIdUser(int idUser){
        this.IDUser = IDUser;
    }
}
