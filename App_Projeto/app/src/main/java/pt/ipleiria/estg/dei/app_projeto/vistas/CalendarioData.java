package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.R;

public class CalendarioData extends AppCompatActivity {
    private TextView calendarioData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_data);
        calendarioData = (TextView) findViewById(R.id.tvCalendarioData);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        calendarioData.setText(date);
    }
}