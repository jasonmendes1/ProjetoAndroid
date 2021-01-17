package pt.ipleiria.estg.dei.app_projeto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;

public class PlanosTreinoJSONParser {

    public static ArrayList<PlanosTreino> parserJsonPlanosTreino(JSONArray response, Context context){
        ArrayList<PlanosTreino> listaPlanosTreino = new ArrayList<PlanosTreino>();
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject planostreino = (JSONObject) response.get(i);
                int id = planostreino.getInt("IDPlanoTreino");
                String semana = planostreino.getString("semana");
                Date dia = (Date) planostreino.get("dia_treino");
                //PlanosTreino auxplanotreino = new PlanosTreino(id,dia,semana);
                //listaPlanosTreino.add(auxplanotreino);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaPlanosTreino;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
