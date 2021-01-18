package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.app_projeto.MenuMainActivity;
import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.listeners.LoginListener;
import pt.ipleiria.estg.dei.app_projeto.models.SharedPreferencesConfig;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    public static final String USERNAME = "USERNAME";
    private EditText etusername, etpassword;
    private String email;
    private String textIP;
    private TextView textViewIP;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername = findViewById(R.id.editTextUsername);
        etpassword = findViewById(R.id.editTextPassword);
        textViewIP = findViewById(R.id.textViewIP);

        SharedPreferencesConfig.init(getApplicationContext());

        textViewIP.setText(SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null));

        Integer UserIdSaved = SharedPreferencesConfig.read(SharedPreferencesConfig.ID_USER, 0);
        String UserUsernameSaved = SharedPreferencesConfig.read(SharedPreferencesConfig.USERNAME_USER, null);
        String UserAuthkeySaved = SharedPreferencesConfig.read(SharedPreferencesConfig.AUTH_KEY, null);

        if(UserIdSaved != 0 && UserUsernameSaved != null && UserAuthkeySaved != null){
            Intent main = new Intent(this, MenuMainActivity.class);
            main.putExtra("IDUSER", UserIdSaved);
            main.putExtra("USERNAME", UserUsernameSaved);
            main.putExtra("AUTH_KEY", UserAuthkeySaved);
            System.out.println("->>>>>>>>>>>>>>>>>>> Tinha lá dados guardados:"+ UserIdSaved + " | "+UserUsernameSaved + " | "+UserAuthkeySaved);
            startActivity(main);
            finish();
        }

    }

        public void onClickLogin(View view){
            String username = etusername.getText().toString().trim().toLowerCase();
            String password = etpassword.getText().toString().trim();
            String ip = textViewIP.getText().toString().trim();

            if(textViewIP.getText() == ""){
                Toast.makeText(this, "Adiciona / Altera o IP!", Toast.LENGTH_SHORT).show();
            }else{
                Singleton.getInstance(getApplicationContext()).setIP(ip);
                System.out.println("--> SharedPreferences IP: " + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null));

                Singleton.getInstance(getApplicationContext()).setLoginListener(this);
                String pw_encryptada = Singleton.getInstance(getApplicationContext()).getEncrypted(password);
                System.out.println("--> encryptado: " + pw_encryptada);
                Singleton.getInstance(getApplicationContext()).verificaLoginAPI_POST(username,pw_encryptada);

                // Singleton.getInstance(getApplicationContext()).verificaLoginAPI_POST(username, password, getApplicationContext(), UserJSONParser.isConnectionInternet(getApplicationContext()));
            }
    }

    public void onClickIP(View view) {
        dialogIP();
    }

    private void dialogIP(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        final EditText IPinput = new EditText(this);
        builder.setView(IPinput);
        IPinput.setText(textViewIP.getText());
        builder.setTitle("IP")
                .setMessage("Coloca aqui o teu IPv4")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textIP = IPinput.getText().toString();
                        textViewIP.setText(textIP);
                    }})
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }})
                //.setIcon(android.R.drawable.ic_lock_power_off)
                .show();
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
        Toast.makeText(this, "Logado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
        intent.putExtra(MenuMainActivity.CHAVE_EMAIL, email);
        intent.putExtra(MenuMainActivity.CHAVE_ID, iduser+"");
        startActivity(intent);
        finish();
    }




    public void onClickAqui(View view) {
        Intent intent = new Intent(this, LoginErroActivity.class);
        startActivity(intent);
    }

    /*
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_three_dots_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.action_settings){
            createDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    public Dialog createDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ip_picker_dialog);
        TextView titulo = dialog.findViewById(R.id.ip_picker_dialog_title);
        titulo.setText("IP ADDRESS");
        dialog.setTitle("IP ADDRESS");
        final EditText textIP = dialog.findViewById(R.id.ipDialog);
        textIP.setText(SharedPreferencesConfig.read(SharedPreferencesConfig.SETTINGS_IP, null));
        Button dialogButtonSave = (Button) dialog.findViewById(R.id.buttonSave);
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.buttonCancel);

        dialogButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance(getApplicationContext()).setIP(textIP.getText().toString());
                Toast.makeText(getApplicationContext(), Singleton.getInstance(getApplicationContext()).getIPInput(), Toast.LENGTH_SHORT).show();
                textIP.setText(Singleton.getInstance(getApplicationContext()).getIPInput());
                dialog.dismiss();
            }
        });

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
        return dialog;
    }






     */




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
