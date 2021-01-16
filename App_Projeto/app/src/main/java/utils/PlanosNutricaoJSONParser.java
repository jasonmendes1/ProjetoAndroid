package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosNutricao;

public class PlanosNutricaoJSONParser {

    public static ArrayList<PlanosNutricao> parserJsonPlanosNutricao(JSONArray response, Context context){
        ArrayList<PlanosNutricao> listaplanosnutricao = new ArrayList<PlanosNutricao>();
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject planosnutricao = (JSONObject) response.get(i);

                int id = planosnutricao.getInt("IDPlanoNutricao");
                int segunda = planosnutricao.getInt("segunda");
                int terca = planosnutricao.getInt("terca");
                int quarta = planosnutricao.getInt("quarta");
                int quinta = planosnutricao.getInt("quinta");
                int sexta = planosnutricao.getInt("sexta");
                int sabado = planosnutricao.getInt("sabado");
                String semana = planosnutricao.getString("semana");

                PlanosNutricao auxplanonutricao = new PlanosNutricao(id,segunda,terca,quarta,quinta,sexta,sabado,semana);
                listaplanosnutricao.add(auxplanonutricao);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaplanosnutricao;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
