package pt.ipleiria.estg.dei.app_projeto.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FitnessLeagueBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "gym";
    private static final String TABLE_CLIENTE = "cliente";
    private static final String TABLE_FUNCIONARIO = "funcionario";
    private static final String TABLE_PLANOTREINO = "planostreino";
    private static final String TABLE_PLANONUTRICAO = "planosnutricao";

    private static final String CLIENTE_ID = "IDCliente";
    private static final String CLIENTE_PRIMEIRONOME = "primeiroNome";
    private static final String CLIENTE_APELIDO = "apelido";
    private static final String CLIENTE_DATANASCIMENTO = "dt_nascimento";
    private static final String CLIENTE_SEXO = "sexo";
    private static final String CLIENTE_AVATAR = "avatar";
    private static final String CLIENTE_TELEMOVEL = "num_tele";
    private static final String CLIENTE_NIF = "nif";
    private static final String CLIENTE_ALTURA = "altura";
    private static final String CLIENTE_PESO = "peso";
    private static final String CLIENTE_MASSAMUSCULAR = "massa_muscular";
    private static final String CLIENTE_MASSAGORDA = "massa_gorda";


    private static final String FUNCIONARIO_ID = "IDFuncionario";
    private static final String FUNCIONARIO_PRIMEIRONOME = "primeiroNome";
    private static final String FUNCIONARIO_APELIDO = "apelido";
    private static final String FUNCIONARIO_DATANASCIMENTO = "dt_nascimento";
    private static final String FUNCIONARIO_SEXO = "sexo";
    private static final String FUNCIONARIO_AVATAR = "avatar";
    private static final String FUNCIONARIO_TELEMOVEL = "num_tele";
    private static final String FUNCIONARIO_NIF = "nif";

    private static final String IDPLANOTREINO = "IDPlanoTreino";
    private static final String IDPT = "id_PT";
    private static final String DIATREINO = "dia_treino";
    private static final String SEMANAPT = "semana";

    private static final String IDPLANONUTRICAO = "IDPlanoNutricao";
    private static final String SEGUNDA = "Segunda";
    private static final String TERCA = "Terca";
    private static final String QUARTA = "Quarta";
    private static final String QUINTA = "Quinta";
    private static final String SEXTA = "Sexta";
    private static final String SABADO = "Sabado";
    private static final String SEMANAPN = "Semana";


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
                CLIENTE_NIF + " INTEGER NOT NULL, " +
                CLIENTE_ALTURA + " INTEGER NOT NULL, " +
                CLIENTE_PESO + " INTEGER NOT NULL, " +
                CLIENTE_MASSAMUSCULAR + " INTEGER NOT NULL, " +
                CLIENTE_MASSAGORDA + " INTEGER NOT NULL);";
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


        String TBL_CREATE_PLANOTREINO = "CREATE TABLE " + TABLE_PLANOTREINO + "(" +
                IDPLANOTREINO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                IDPT + " INTEGER NOT NULL, " +
                DIATREINO + " DATE NOT NULL, " +
                SEMANAPT + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_PLANOTREINO);

        String TBL_CREATE_PLANONUTRICAO = "CREATE TABLE " + TABLE_PLANONUTRICAO + "(" +
                IDPLANONUTRICAO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SEGUNDA + " INTEGER NOT NULL, " +
                TERCA + " INTEGER NOT NULL, " +
                QUARTA + " INTEGER NOT NULL, " +
                QUINTA + " INTEGER NOT NULL, " +
                SEXTA + " INTEGER NOT NULL, " +
                SABADO + " INTEGER NOT NULL, " +
                SEMANAPN + " TEXT NOT NULL);";
        db.execSQL(TBL_CREATE_PLANONUTRICAO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /*Planos De Treino*/
    public boolean removeAllPlanosTreinoBD(){
        return database.delete(TABLE_PLANOTREINO,null,null) > 0;
    }

    public PlanosTreino adicionarPlanoTreinoBD(PlanosTreino planosTreino){
        ContentValues values = new ContentValues();
        values.put(IDPLANOTREINO, planosTreino.getIDPlanoTreino());
        values.put(IDPT, planosTreino.getid_PT());
        values.put(DIATREINO, planosTreino.getDia_treino());
        values.put(SEMANAPT, planosTreino.getSemana());

        long id = this.database.insert(TABLE_PLANOTREINO,null,values);
        if(id > -1){
            planosTreino.setIDPlanoTreino((int) id);
            return planosTreino;
        }
        return null;
    }

    public ArrayList<Cliente> getAllClientesDB() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cursor cursor = this.database.query(TABLE_CLIENTE, new String[]{CLIENTE_ID, CLIENTE_PRIMEIRONOME, CLIENTE_APELIDO, CLIENTE_DATANASCIMENTO, CLIENTE_SEXO, CLIENTE_AVATAR, CLIENTE_DATANASCIMENTO, CLIENTE_SEXO, CLIENTE_AVATAR, CLIENTE_TELEMOVEL, CLIENTE_NIF, CLIENTE_ALTURA, CLIENTE_PESO, CLIENTE_MASSAMUSCULAR, CLIENTE_MASSAGORDA},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente auxCliente = new Cliente(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                        cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9),
                        cursor.getInt(10),cursor.getInt(11));

                clientes.add(auxCliente);
            } while (cursor.moveToNext());
        }
        return clientes;
    }

    public ArrayList<PlanosTreino> getAllPlanosTreinoBD(){
        ArrayList<PlanosTreino> planosTreinos = new ArrayList<PlanosTreino>();
        Cursor cursor = this.database.query(TABLE_PLANOTREINO, new String[]{IDPLANOTREINO,IDPT,DIATREINO,SEMANAPT},null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                PlanosTreino auxplanotreino = new PlanosTreino(cursor.getInt(0),cursor.getInt(0),cursor.getString(0),cursor.getString(0));
                auxplanotreino.setIDPlanoTreino(cursor.getInt(0));
                planosTreinos.add(auxplanotreino);
            }while(cursor.moveToNext());
        }
        return planosTreinos;
    }

    /*Planos De Treino*/

    /*Planos De Nutrição*/

    public boolean removeAllPlanosNutricaoBD(){
        return database.delete(TABLE_PLANONUTRICAO,null,null) > 0;
    }

    public PlanosNutricao adicionarPlanoNutricao(PlanosNutricao planosNutricao){
        ContentValues values = new ContentValues();
        values.put(IDPLANONUTRICAO, planosNutricao.getIDPlanoNutricao());
        values.put(SEGUNDA, planosNutricao.getSegunda());
        values.put(TERCA, planosNutricao.getTerca());
        values.put(QUARTA, planosNutricao.getQuarta());
        values.put(QUINTA, planosNutricao.getQuinta());
        values.put(SEXTA, planosNutricao.getSexta());
        values.put(SABADO, planosNutricao.getSabado());
        values.put(SEMANAPN, planosNutricao.getSemana());

        long id = this.database.insert(TABLE_PLANONUTRICAO,null,values);
        if(id > -1){
            planosNutricao.setIDPlanoNutricao((int) id);
            return planosNutricao;
        }
        return null;
    }

    public ArrayList<PlanosNutricao> getAllPlanosNutricao(){
        ArrayList<PlanosNutricao> planosNutricaos = new ArrayList<PlanosNutricao>();
        Cursor cursor = this.database.query(TABLE_PLANONUTRICAO, new String[]{IDPLANONUTRICAO, SEGUNDA, TERCA,QUARTA,QUINTA,SEXTA,SABADO,SEMANAPN}, null, null, null,null, null);

        if(cursor.moveToFirst()){
            do{
                PlanosNutricao auxplanonutri = new PlanosNutricao(cursor.getInt(0),cursor.getInt(0),cursor.getInt(0),cursor.getInt(0),cursor.getInt(0),cursor.getInt(0),cursor.getInt(0),cursor.getString(0));
                auxplanonutri.setIDPlanoNutricao(cursor.getInt(0));
                planosNutricaos.add(auxplanonutri);
            }while (cursor.moveToNext());
        }
        return planosNutricaos;
    }

    public void adicionarClienteDB(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(CLIENTE_PRIMEIRONOME, cliente.getPrimeiroNome());
        values.put(CLIENTE_APELIDO, cliente.getApelido());
        values.put(CLIENTE_TELEMOVEL, cliente.getNum_tele());
        values.put(CLIENTE_NIF, cliente.getNif());
        values.put(CLIENTE_SEXO, cliente.getSexo());
        values.put(CLIENTE_ALTURA, cliente.getAltura());
        values.put(CLIENTE_PESO, cliente.getPeso());
        values.put(CLIENTE_MASSAMUSCULAR, cliente.getMassa_muscular());
        values.put(CLIENTE_MASSAGORDA, cliente.getMassa_gorda());

        this.database.insert(TABLE_CLIENTE, null, values);
    }

    public boolean editarClienteDB(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put(CLIENTE_PRIMEIRONOME, cliente.getPrimeiroNome());
        values.put(CLIENTE_APELIDO, cliente.getApelido());
        values.put(CLIENTE_TELEMOVEL, cliente.getNum_tele());
        values.put(CLIENTE_NIF, cliente.getNif());
        values.put(CLIENTE_SEXO, cliente.getSexo());
        values.put(CLIENTE_ALTURA, cliente.getAltura());
        values.put(CLIENTE_PESO, cliente.getPeso());
        values.put(CLIENTE_MASSAMUSCULAR, cliente.getMassa_muscular());
        values.put(CLIENTE_MASSAGORDA, cliente.getMassa_gorda());

        return this.database.update(TABLE_CLIENTE, values, "id=?", new String[]{cliente.getIDCliente() + ""}) > 0;
    }

    /*Planos De Nutrição*/

}
