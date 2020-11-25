package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;

import pt.ipleiria.estg.dei.app_projeto.R;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class LoginActivity extends AppCompatActivity {
    public static final String EMAIl = "EMAIL";
    private EditText etEmail, etPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
    }


    public void onClickLogin(View view) {
        email=etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if(!isEmailValid(email)) {
            etEmail.setError(getString(R.string.email_errado));
            return;
        }
        if(isPasswordValid(password)) {
            etPassword.setError(getString(R.string.password_errada));
            return;
        }

        // Toast.makeText(this,email,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(EMAIL, email);
        startActivity(intent);
        finish();
    }

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password){
        if (password==null)
            return false;
        return password.length()<=4;
    }

    public void onClickAqui(View view) {
        Intent intent = new Intent(this, LoginErroActivity.class);
        intent.putExtra(EMAIL, email);
        startActivity(intent);
    }
}
