package pt.ipleiria.estg.dei.app_projeto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.Cliente;

public class ClienteJSONParser {

    public static Cliente parserJsonCliente(String response, Context context){
        Cliente auxCliente = null;
        try {
            JSONObject cliente = new JSONObject(response);
            int cliente_ID = cliente.getInt("IDCliente");
            String cliente_primeiroNome = cliente.getString("primeiroNome");
            String cliente_apelido = cliente.getString("apelido");
            int cliente_dta_nascimento = cliente.getInt("cliente_dta_nascimento");
            String cliente_sexo = cliente.getString("sexo");
            String cliente_avatar = cliente.getString("avatar");
            int cliente_num_tele = cliente.getInt("num_tele");
            int cliente_nif = cliente.getInt("nif");
            int cliente_altura = cliente.getInt("altura");
            int cliente_peso = cliente.getInt("peso");
            int cliente_massa_muscular = cliente.getInt("massa_muscular");
            int cliente_massa_gorda = cliente.getInt("massa_gorda");


            auxCliente = new Cliente(cliente_ID, cliente_primeiroNome, cliente_apelido, cliente_dta_nascimento, cliente_sexo, cliente_avatar, cliente_num_tele, cliente_nif, cliente_altura, cliente_peso, cliente_massa_muscular, cliente_massa_gorda);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxCliente;
    }

    public static ArrayList<Cliente> parserJsonClientes(JSONArray response) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject cliente = (JSONObject) response.get(i);
                    int cliente_ID = cliente.getInt("IDCliente");
                    String cliente_primeiroNome = cliente.getString("primeiroNome");
                    String cliente_apelido = cliente.getString("apelido");
                    int cliente_dta_nascimento = cliente.getInt("cliente_dta_nascimento");
                    String cliente_sexo = cliente.getString("sexo");
                    String cliente_avatar = cliente.getString("avatar");
                    int cliente_num_tele = cliente.getInt("num_tele");
                    int cliente_nif = cliente.getInt("nif");
                    int cliente_altura = cliente.getInt("altura");
                    int cliente_peso = cliente.getInt("peso");
                    int cliente_massa_muscular = cliente.getInt("massa_muscular");
                    int cliente_massa_gorda = cliente.getInt("massa_gorda");

                    Cliente c = new Cliente(cliente_ID, cliente_primeiroNome, cliente_apelido, cliente_dta_nascimento, cliente_sexo, cliente_avatar, cliente_num_tele, cliente_nif, cliente_altura, cliente_peso, cliente_massa_muscular, cliente_massa_gorda);
                    clientes.add(c);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return clientes;
    }


    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public static String parserJsonLogin(String response) {
        String token = null;
        try {
            JSONObject login = new JSONObject(response);
            if (login.getBoolean("success"))
                token = login.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }


    public static String parserJsonAvatarCliente(String response){
        String avatar = null;
        try {
            JSONObject login = new JSONObject(response);
            if (login.getBoolean("success"))
                avatar = login.getString("avatar");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return avatar;
    }

}
