package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.app_projeto.MenuMainActivity;
import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.listeners.UserListener;
import pt.ipleiria.estg.dei.app_projeto.models.Cliente;
import pt.ipleiria.estg.dei.app_projeto.models.SharedPreferencesConfig;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.models.User;
import pt.ipleiria.estg.dei.app_projeto.utils.ClienteJSONParser;
import pt.ipleiria.estg.dei.app_projeto.utils.UserJSONParser;

import pt.ipleiria.estg.dei.app_projeto.listeners.LoginListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.UserListener;
import pt.ipleiria.estg.dei.app_projeto.models.SharedPreferencesConfig;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;
import pt.ipleiria.estg.dei.app_projeto.models.User;
import pt.ipleiria.estg.dei.app_projeto.utils.UserJSONParser;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class LoginActivity extends AppCompatActivity implements UserListener {
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

    @Override
    public void onRefreshListaUser(User user) {
        if(user == null){
            etusername.setError("Username or Password is wrong");
        }
        else{
            SharedPreferencesConfig.write(SharedPreferencesConfig.ID_USER, user.getId());//save int in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.USERNAME_USER, user.getUsername());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.AUTH_KEY, user.getAuthKey());//save boolean in shared preference.
            Singleton.getInstance(getApplicationContext()).getClienteFromLogin(getApplicationContext(), ClienteJSONParser.isConnectionInternet(getApplicationContext()));
        }
    }

    @Override
    public void onRefreshListaCliente(Cliente cliente) {
        if(cliente != null) {
            //TODO:guardar no shared: email,username,token
            SharedPreferencesConfig.write(SharedPreferencesConfig.NOME_CLIENTE, cliente.getPrimeiroNome());//save int in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.APELIDO_CLIENTE, cliente.getApelido());//save int in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.DTA_NASCIMENTO_CLIENTE, cliente.getDta_nascimento());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.SEXO_CLIENTE, cliente.getSexo());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.AVATAR_CLIENTE, cliente.getAvatar());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.NUM_TELE_CLIENTE, cliente.getNum_tele());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.NIF_CLIENTE, cliente.getNif());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.ALTURA_CLIENTE, cliente.getAltura());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.PESO_CLIENTE, cliente.getPeso());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.MASSA_MUSCULAR_CLIENTE, cliente.getMassa_muscular());//save string in shared preference.
            SharedPreferencesConfig.write(SharedPreferencesConfig.MASSA_GORDA_CLIENTE, cliente.getMassa_gorda());//save string in shared preference.
            Intent main = new Intent(this, MenuMainActivity.class);
            main.putExtra("IDUSER", SharedPreferencesConfig.read(SharedPreferencesConfig.ID_USER, 0));
            main.putExtra("USERNAME", SharedPreferencesConfig.read(SharedPreferencesConfig.USERNAME_USER, null));
            main.putExtra("AUTH_KEY", SharedPreferencesConfig.read(SharedPreferencesConfig.AUTH_KEY, null));
            startActivity(main);
            finish();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void onResume() {
        Singleton.getInstance(getApplicationContext()).setUserListener(this);
        super.onResume();
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
