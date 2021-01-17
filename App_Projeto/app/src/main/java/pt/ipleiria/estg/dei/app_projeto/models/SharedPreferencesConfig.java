package pt.ipleiria.estg.dei.app_projeto.models;

import android.content.Context;
import android.content.SharedPreferences;

import pt.ipleiria.estg.dei.app_projeto.MainActivity;

public class SharedPreferencesConfig
{
    private static SharedPreferences mSharedPref;
    public static final String IP = "IP";
    public static final String ENTRAR = "IP";
    public static final String USERNAME_USER = "USERNAME_USER";
    public static final String ID_USER = "ID_USER";
    public static final String AUTH_KEY = "AUTH_KEY";
    public static final String SETTINGS_IP = "SETTINGS_IP";
    public static final String NOME_CLIENTE = "NOME_CLIENTE";
    public static final String APELIDO_CLIENTE = "APELIDO_CLIENTE";
    public static final String DTA_NASCIMENTO_CLIENTE = "DTA_NASCIMENTO_CLIENTE";
    public static final String SEXO_CLIENTE = "SEXO_CLIENTE";
    public static final String AVATAR_CLIENTE = "AVATAR_CLIENTE";
    public static final String NUM_TELE_CLIENTE = "NUM_TELE_CLIENTE";
    public static final String NIF_CLIENTE = "NIF_CLIENTE";
    public static final String ALTURA_CLIENTE = "ALTURA_CLIENTE";
    public static final String PESO_CLIENTE = "PESO_CLIENTE";
    public static final String MASSA_MUSCULAR_CLIENTE = "MASSA_MUSCULAR_CLIENTE";
    public static final String MASSA_GORDA_CLIENTE = "MASSA_GORDA_CLIENTE";

    private SharedPreferencesConfig()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), MainActivity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }
}
