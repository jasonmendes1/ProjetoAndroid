package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.app_projeto.MenuMainActivity;
import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.listeners.LoginListener;
import pt.ipleiria.estg.dei.app_projeto.models.SharedPreferencesConfig;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.utils.UserJSONParser;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    public static final String USERNAME = "USERNAME";
    private EditText etusername, etPassword;
    private String email;
    private String textIP;
    private TextView textViewIP;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        etusername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        textViewIP = findViewById(R.id.textViewIP);
    }


    public void onCreate(View view) {
        String ip = textViewIP.getText().toString().trim();

        Integer UserIdSaved = SharedPreferencesConfig.read(SharedPreferencesConfig.ID_USER, 0);
        String UserUsernameSaved = SharedPreferencesConfig.read(SharedPreferencesConfig.USERNAME_USER, null);
        String UserAuthkeySaved = SharedPreferencesConfig.read(SharedPreferencesConfig.AUTH_KEY, null);

        if (UserIdSaved != 0 && UserUsernameSaved != null && UserAuthkeySaved != null) {
            Intent main = new Intent(this, MenuMainActivity.class);
            main.putExtra("IDUSER", UserIdSaved);
            main.putExtra("USERNAME", UserUsernameSaved);
            main.putExtra("AUTH_KEY", UserAuthkeySaved);
            System.out.println("->>>>>>>>>>>>>>>>>>> Tinha lá dados guardados:" + UserIdSaved + " | " + UserUsernameSaved + " | " + UserAuthkeySaved);
            startActivity(main);
            finish();
        }
    }

        public void onClickLogin(View view){
            String username = etusername.getText().toString();
            String password = etusername.getText().toString();


            Singleton.getInstance(getApplicationContext()).verificaLoginAPI_POST(username, password, getApplicationContext(), UserJSONParser.isConnectionInternet(getApplicationContext()));

        }








       /* if(textViewIP.getText() == ""){
            Toast.makeText(this, "Adiciona / Altera o IP!", Toast.LENGTH_SHORT).show();
        }else {
            Singleton.getInstance(getApplicationContext()).setIp(ip);
            System.out.println("--> SharedPreferences IP: " + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null));

            Singleton.getInstance(getApplicationContext()).setLoginListener(this);
            String pw_encriptada = Singleton.getInstance(getApplicationContext()).getEncrypted(password);
            System.out.println("--> encryptado: " + pw_encriptada);
            Singleton.getInstance(getApplicationContext()).verificaLoginAPI_POST(email, pw_encriptada);
        }
    }

    public void onClickAqui(View view) {
        Intent intent = new Intent(this, LoginErroActivity.class);
        intent.putExtra(EMAIL, email);
        startActivity(intent);
    }

    @Override
    public void onRefreshLogin(JSONArray response) {
        int iduser = 0;
        String username = null, email = null;
        for (int i = 0; i < response.length(); i++) {
            JSONObject obj = null;
            try {
                obj = response.getJSONObject(i);
                iduser = obj.getInt("id");
                username = obj.getString("username");
                email = obj.getString("email");


                if (iduser == -1) {
                    Toast.makeText(this, "Username ou Password Errada!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Singleton.getInstance(getApplicationContext()).setIdUser(iduser);
        Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
        intent.putExtra(MenuMainActivity.CHAVE_ID, iduser+"");
        startActivity(intent);
        finish();
    }

        */
}
