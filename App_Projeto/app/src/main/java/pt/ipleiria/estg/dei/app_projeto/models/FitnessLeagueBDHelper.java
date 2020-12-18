package pt.ipleiria.estg.dei.app_projeto.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FitnessLeagueBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "gym";
    private static final String TABLE_CLIENTE = "cliente";
    private static final String TABLE_FUNCIONARIO = "funcionario";

    private static final String CLIENTE_ID = "IDCliente";
    private static final String CLIENTE_PRIMEIRONOME = "primeiroNome";
    private static final String CLIENTE_APELIDO = "apelido";
    private static final String CLIENTE_DATANASCIMENTO = "dt_nascimento";
    private static final String CLIENTE_SEXO = "sexo";
    private static final String CLIENTE_AVATAR = "avatar";
    private static final String CLIENTE_TELEMOVEL = "num_tele";
    private static final String CLIENTE_NIF = "nif";

    private static final String FUNCIONARIO_ID = "IDFuncionario";
    private static final String FUNCIONARIO_PRIMEIRONOME = "primeiroNome";
    private static final String FUNCIONARIO_APELIDO = "apelido";
    private static final String FUNCIONARIO_DATANASCIMENTO = "dt_nascimento";
    private static final String FUNCIONARIO_SEXO = "sexo";
    private static final String FUNCIONARIO_AVATAR = "avatar";
    private static final String FUNCIONARIO_TELEMOVEL = "num_tele";
    private static final String FUNCIONARIO_NIF = "nif";


    private final SQLiteDatabase database;

    public FitnessLeagueBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TBL_CREATE_CLIENTE = "CREATE TABLE " + TABLE_CLIENTE + "(" +
                CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CLIENTE_PRIMEIRONOME + " TEXT NOT NULL, " +
                CLIENTE_APELIDO + " TEXT NOT NULL, " +
                CLIENTE_DATANASCIMENTO + " DATE NOT NULL, " +
                CLIENTE_SEXO + " TEXT NOT NULL, " +
                CLIENTE_AVATAR + " TEXT NOT NULL, " +
                CLIENTE_TELEMOVEL + " TEXT NOT NULL, " +
                CLIENTE_NIF + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_CLIENTE);

        String TBL_CREATE_FUNCIONARIO = "CREATE TABLE " + TABLE_FUNCIONARIO + "(" +
                FUNCIONARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FUNCIONARIO_PRIMEIRONOME + " TEXT NOT NULL, " +
                FUNCIONARIO_APELIDO + " TEXT NOT NULL, " +
                FUNCIONARIO_DATANASCIMENTO + " DATE NOT NULL, " +
                FUNCIONARIO_SEXO + " TEXT NOT NULL, " +
                FUNCIONARIO_AVATAR + " TEXT NOT NULL, " +
                FUNCIONARIO_TELEMOVEL + " TEXT NOT NULL, " +
                FUNCIONARIO_NIF + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_FUNCIONARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
