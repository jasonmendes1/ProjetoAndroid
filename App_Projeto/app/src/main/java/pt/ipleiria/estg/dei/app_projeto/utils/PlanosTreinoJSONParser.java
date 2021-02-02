package pt.ipleiria.estg.dei.app_projeto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.PlanosTreino;

public class PlanosTreinoJSONParser {

    public static ArrayList<PlanosTreino> parserJsonPlanosTreino(JSONArray response, Context context){
        ArrayList<PlanosTreino> tempListaPlanosTreino = new ArrayList<PlanosTreino>();
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject planostreino = (JSONObject) response.get(i);

                int id = planostreino.getInt("IDPlanoTreino");
                int id_PT = planostreino.getInt("id_PT");
                String dia_treino = planostreino.getString("dia_treino");
                String semana = planostreino.getString("semana");

                PlanosTreino auxplanotreino = new PlanosTreino(id, id_PT, dia_treino, semana);
                tempListaPlanosTreino.add(auxplanotreino);
            }
        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        System.out.println("--> PARSER LISTA TEMP: "+ tempListaPlanosTreino);
        return tempListaPlanosTreino;
    }


    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
