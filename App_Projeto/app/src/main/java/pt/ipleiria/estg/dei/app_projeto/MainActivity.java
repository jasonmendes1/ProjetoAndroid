package pt.ipleiria.estg.dei.app_projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ipleiria.estg.dei.app_projeto.vistas.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonComecar = findViewById(R.id.btnComecar);
    }

    public void onClickComecar(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
