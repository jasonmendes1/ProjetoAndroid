package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.R;

public class LoginErroActivity extends AppCompatActivity {
    private Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginerro);
        setTitle("Login");

        buttonVoltar = findViewById(R.id.btnVoltar);
    }

    public void onClickVoltar(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
