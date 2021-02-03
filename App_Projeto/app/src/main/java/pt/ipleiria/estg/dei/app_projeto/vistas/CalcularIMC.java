package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.R;

public class CalcularIMC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_imc);
    }

    public void onClickVoltar(View view){
        Intent intent = new Intent(this, PerfilFragment.class);
        startActivity(intent);
    }

    public void onClickCalcular(View view){
        EditText altura = findViewById(R.id.editTextAlturaIMC);
        EditText peso = findViewById(R.id.editTextPesoIMC);
        TextView IMC = findViewById(R.id.textViewIMC);
        TextView clas = findViewById(R.id.textViewClassificacao);

        if((altura.getText() != null) && (peso.getText() != null)){
            float alturaf = Float.parseFloat(String.valueOf(altura.getText()));
            float pesof = Float.parseFloat(String.valueOf(peso.getText()));
            float altquad = alturaf * alturaf;
            float IMCf = Math.round((pesof / altquad)*10000);
            IMC.setText(String.valueOf(IMCf));

            if(IMCf < 18.5){
                clas.setText("Abaixo do Peso");
                clas.setTextColor(Color.parseColor("#FAE40D"));
            }else if(IMCf >= 18.5 && IMCf < 25){
                clas.setText("Peso Normal");
                clas.setTextColor(Color.parseColor("#72FE26"));
            }else if(IMCf >= 25 && IMCf < 30){
                clas.setText("Sobrepeso");
                clas.setTextColor(Color.parseColor("#FAAB0D"));
            }else if(IMCf >= 30 && IMCf < 35){
                clas.setText("Obesidade grau 1");
                clas.setTextColor(Color.parseColor("#FA8A0D"));
            }else if(IMCf >= 35 && IMCf < 40){
                clas.setText("Obesidade grau 2");
                clas.setTextColor(Color.parseColor("#FA5F0D"));
            }else if(IMCf >= 40){
                clas.setText("Obesidade grau 3");
                clas.setTextColor(Color.parseColor("#F0130C"));
            }
        }
    }
}