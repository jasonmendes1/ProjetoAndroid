package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.MenuMainActivity;
import pt.ipleiria.estg.dei.app_projeto.R;

public class InfoGymActivity extends AppCompatActivity {
    private Button buttonVoltar;
    private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_gym);
        setTitle("Info Gym");

        buttonVoltar = findViewById(R.id.buttonVoltar);

        SharedPreferences sharedPrefUser = getApplication().getSharedPreferences(MenuMainActivity.USER, Context.MODE_PRIVATE);
        token = sharedPrefUser.getString(MenuMainActivity.TOKEN, null);

    }

    public void onClickVoltar(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}