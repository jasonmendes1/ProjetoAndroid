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
    public static final String NOME_CLIENTE = "NOME_PROFILE";
    public static final String NIF_CLIENTE = "NIF_PROFILE";

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
