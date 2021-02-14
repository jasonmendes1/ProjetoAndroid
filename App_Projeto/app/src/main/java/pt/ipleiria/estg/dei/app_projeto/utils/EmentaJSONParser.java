package pt.ipleiria.estg.dei.app_projeto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.Ementa;

public class EmentaJSONParser {

    public static ArrayList<Ementa> parserJsonEmenta(JSONArray response, Context context){
        ArrayList<Ementa> tempListaEmenta = new ArrayList<Ementa>();
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject exercicios = (JSONObject) response.get(i);

                int IDEmenta = exercicios.getInt("IDEmenta");
                String nomeEmenta = exercicios.getString("nomeEmenta");
                String PequenoAlmoco = exercicios.getString("PequenoAlmoco");
                String Almoco = exercicios.getString("Almoco");
                String Lanche1 = exercicios.getString("Lanche1");
                String Lanche2 = exercicios.getString("Lanche2");
                String Jantar = exercicios.getString("Jantar");


                Ementa auxexercicios = new Ementa(IDEmenta, nomeEmenta, PequenoAlmoco, Almoco, Lanche1, Lanche2, Jantar);
                tempListaEmenta.add(auxexercicios);
            }
        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        System.out.println("--> PARSER LISTAEXERCICIOS TEMP: "+ tempListaEmenta.toString());
        return tempListaEmenta;
    }


    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
