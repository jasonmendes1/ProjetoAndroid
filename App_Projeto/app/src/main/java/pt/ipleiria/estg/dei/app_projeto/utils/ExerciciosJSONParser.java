package pt.ipleiria.estg.dei.app_projeto.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.app_projeto.models.Exercicio;

public class ExerciciosJSONParser {

    public static ArrayList<Exercicio> parserJsonExercicio(JSONArray response, Context context){
        ArrayList<Exercicio> tempListaExercicios = new ArrayList<Exercicio>();
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject exercicios = (JSONObject) response.get(i);

                int IDExer = exercicios.getInt("IDExer");
                String nome = exercicios.getString("nome");
                int repeticoes = exercicios.getInt("repeticoes");
                int tempo = exercicios.getInt("tempo");
                int serie = exercicios.getInt("serie");
                int repouso = exercicios.getInt("repouso");
                int tempo_total = exercicios.getInt("tempo_total");
                int num_maquina = exercicios.getInt("num_maquina");

                Exercicio auxexercicios = new Exercicio(IDExer, nome, repeticoes, tempo, serie, repouso, tempo_total, num_maquina);
                tempListaExercicios.add(auxexercicios);
            }
        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        System.out.println("--> PARSER LISTAEXERCICIOS TEMP: "+ tempListaExercicios.toString());
        return tempListaExercicios;
    }


    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
