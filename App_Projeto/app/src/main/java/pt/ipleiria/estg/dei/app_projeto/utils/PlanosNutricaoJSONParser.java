package pt.ipleiria.estg.dei.app_projeto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;

public class PlanosNutricaoJSONParser {

    public static ArrayList<PlanosNutricao> parserJsonPlanosNutricao(JSONArray response, Context context){
        ArrayList<PlanosNutricao> templistaplanosnutricao = new ArrayList<PlanosNutricao>();
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject planosnutricao = (JSONObject) response.get(i);

                int IDPlanoNutricao = planosnutricao.getInt("IDPlanoNutricao");
                int Segunda = planosnutricao.getInt("Segunda");
                int Terca = planosnutricao.getInt("Terca");
                int Quarta = planosnutricao.getInt("Quarta");
                int Quinta = planosnutricao.getInt("Quinta");
                int Sexta = planosnutricao.getInt("Sexta");
                int Sabado = planosnutricao.getInt("Sabado");
                String Semana = planosnutricao.getString("Semana");

                PlanosNutricao auxplanosnutricao = new PlanosNutricao(IDPlanoNutricao,Segunda,Terca,Quarta,Quinta,Sexta,Sabado,Semana);
                templistaplanosnutricao.add(auxplanosnutricao);
            }
        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        System.out.println("--> PARSER LISTANUTRICAO TEMP: "+ templistaplanosnutricao);
        return templistaplanosnutricao;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
