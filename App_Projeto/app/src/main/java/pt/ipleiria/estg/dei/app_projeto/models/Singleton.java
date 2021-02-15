package pt.ipleiria.estg.dei.app_projeto.models;

import android.app.Application;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import pt.ipleiria.estg.dei.app_projeto.listeners.EmentaListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.ExerciciosListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.LoginListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.PlanosNutricaoListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.PlanosTreinoListener;
import pt.ipleiria.estg.dei.app_projeto.listeners.UserListener;
import pt.ipleiria.estg.dei.app_projeto.utils.ClienteJSONParser;
import pt.ipleiria.estg.dei.app_projeto.utils.EmentaJSONParser;
import pt.ipleiria.estg.dei.app_projeto.utils.ExerciciosJSONParser;
import pt.ipleiria.estg.dei.app_projeto.utils.PlanosNutricaoJSONParser;
import pt.ipleiria.estg.dei.app_projeto.utils.PlanosTreinoJSONParser;


public class Singleton extends Application {

    private ArrayList<Cargo> cargos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Desconto> descontos;
    private ArrayList<Ementa> ementa;
    private ArrayList<Exercicio> exercicios;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<ListaPlanos> listaPlanos;
    private ArrayList<PlanosNutricao> planosNutricaos;
    private ArrayList<PlanosTreino> planosTreinos;
    private ArrayList<Subscricao> subscricaos;
    private ArrayList<TipoSubscricao> tipoSubscricaos;

    public List<PlanosTreino> planostreinoAPI;
    public List<PlanosNutricao> planosnutricaoAPI;

    private LoginListener loginListener;
    private int User_id = 10;
    private UserListener userListener;
    private PlanosTreinoListener planosTreinoListener;
    private PlanosNutricaoListener planosNutricaoListener;
    private ExerciciosListener exerciciosListener;
    private EmentaListener ementaListener;



    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();

    private static RequestQueue volleyQueue = null;

    private String tokenAPI = "";
    private String urlAPI = "http://" + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, "192.168.1.7") + "/ProjetoWeb/api/web/v1";
    private String CURRENT_IP = "192.168.1.7";
    private String ipURL;
    private String UrlAPIusersLogin = "http://192.168.1.7/ProjetoWeb/api/web/v1/userregisterandlogin/loginuser/";
    private String mUrlGetStuffFromUser = "http://" + CURRENT_IP + ":80/ProjetoWeb/api/web/v1/";

    private FitnessLeagueBDHelper fitnessLeagueBDHelper = null;

    private Cliente cliente;

    private static Singleton INSTANCE = null;
    private static final int ADICIONAR_BD = 1;
    private static final int EDITAR_BD = 2;


    public static synchronized Singleton getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Singleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private Singleton(Context context) {
        cargos = new ArrayList<>();
        clientes = new ArrayList<>();
        descontos = new ArrayList<>();
        ementa = new ArrayList<>();
        exercicios = new ArrayList<>();
        funcionario = new ArrayList<>();
        listaPlanos = new ArrayList<>();
        planosNutricaos = new ArrayList<>();
        planosTreinos = new ArrayList<>();
        subscricaos = new ArrayList<>();
        tipoSubscricaos = new ArrayList<>();

        fitnessLeagueBDHelper = new FitnessLeagueBDHelper(context);
    }

    public void setIP(String ip) {
        CURRENT_IP = ip;
        SharedPreferencesConfig.write(SharedPreferencesConfig.SETTINGS_IP, CURRENT_IP);//save boolean in shared preference.
        mUrlGetStuffFromUser = "http://" + CURRENT_IP + ":80/ProjetoWeb/api/web/v1";

    }

    public String getIPInput() {
        return CURRENT_IP;
    }

    public ArrayList<Cargo> getCargos() {
        return cargos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
/*
    public ArrayList<Cliente> getClientesBD() {
        clientes = fitnessLeagueBDHelper.getAllClientesDB();
        return clientes;
    }
*/
    public ArrayList<Subscricao> getSubscricao() {
        return subscricaos;
    }

    public ArrayList<Desconto> getDescontos() {
        return descontos;
    }

    public ArrayList<Ementa> getEmenta() {
        return ementa;
    }

    public ArrayList<Funcionario> getFuncionario() {
        return funcionario;
    }

    public ArrayList<ListaPlanos> getListaPlanos() {
        return listaPlanos;
    }

    public ArrayList<PlanosNutricao> getPlanosNutricaos() {
        return planosNutricaos;
    }

    public ArrayList<PlanosTreino> getPlanosTreinos() {
        return planosTreinos;
    }

    public ArrayList<Subscricao> getSubscricaos() {
        return subscricaos;
    }

    public ArrayList<TipoSubscricao> getTipoSubscricaos() {
        return tipoSubscricaos;
    }


    public Cliente getCliente(int id) {
        for (Cliente c : clientes)
            if (c.getIDCliente() == id)
                return c;
        return null;
    }

    public Exercicio getExercicio(int id) {
        for (Exercicio e : exercicios)
            if (e.getIDExer() == id)
                return e;
        return null;
    }

    public Ementa getEmenta(int id) {
        for (Ementa em : ementa)
            if (em.getIDEmenta() == id)
                return em;
        return null;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Exercicio> getExerciciosBD() {
        exercicios = fitnessLeagueBDHelper.getAllExercicios();
        return exercicios;
    }


    public void setUserListener(UserListener userListener) {
        this.userListener = userListener;
    }

    public void setPlanosTreinoListener(PlanosTreinoListener planosTreinoListener) {
        this.planosTreinoListener = planosTreinoListener;
    }

    public void setPlanosNutricaoListener(PlanosNutricaoListener planosNutricaoListener) {
        this.planosNutricaoListener = planosNutricaoListener;
    }

    public void setExerciciosListener(ExerciciosListener exerciciosListener) {
        this.exerciciosListener = exerciciosListener;
    }

    public void setEmentaListener(EmentaListener ementaListener) {
        this.ementaListener = ementaListener;
    }




    public String getEncrypted(String plainText) {
        if (plainText == null) {
            return null;
        }
        Key salt = getSalt();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encodedValue, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed to encrypt data");
    }

    static Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void verificaLoginAPI_POST(final String username, final String password, final Context context) {
        System.out.println("--> url:" + urlAPI + "/user/verificaLogin?username=" + username + "&password_hash=" + password);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, urlAPI + "/userregisterandlogin/verificaLogin?username=" + username + "&password_hash=" + password, null, new Response.Listener<JSONArray>() {
        //StringRequest req = new StringRequest(Request.Method.POST, urlAPI + "/userregisterandlogin/verificaLogin?username=" + username + "&password_hash=" + password, new Response.Listener<String>() {
            @Override
            public void onResponse(JSONArray response) {
                //String token = ClienteJSONParser.parserJsonLogin(response);
                if (loginListener != null)
                    loginListener.onRefreshLogin(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(req);
    }

/*
    public void verificaLoginAPI_POST(final String username, final String password, final Context context, final boolean isConnected) {
        if (!isConnected) {
            Toast.makeText(context, "Sem internet", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, UrlAPIusersLogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    User user = UserJSONParser.parserJsonUser(response, context);
                    userListener.onRefreshListaUser(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            volleyQueue.add(request);
        }
    }

 */


    public int getIdUser() {
        return User_id;
    }

    public void setIdUser(int idUser) {
        this.User_id = User_id;
    }

    public void adicionarClientesBD(Cliente cliente) {
        Cliente clienteAux = getCliente(cliente.getIDCliente());
        if (clienteAux != null)
            fitnessLeagueBDHelper.adicionarClienteDB(clienteAux);
    }

    public void editarClientesBD(Cliente cliente) {
        Cliente clienteAux = getCliente(cliente.getIDCliente());
        if (clienteAux != null)
            fitnessLeagueBDHelper.editarClienteDB(clienteAux);
    }


    /*
        public void getAllExerFromPlanosTreino(final Context context, boolean isConnected, ArrayList<PlanosTreino> planosTreinos){
            if(!isConnected){
                planosTreinos = fitnessLeagueBDHelper.getAllPlanosTreino();
                if()
            }else{

            }
        }

    */
    public void getAllClientesAPI(final Context context) {
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();
        System.out.println("--> API URL FEED: " + mUrlGetStuffFromUser + "/cliente");

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlGetStuffFromUser + "/cliente", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                clientes = ClienteJSONParser.parserJsonClientes(response);
                System.out.println("--> RESPOSTA GET CLIENTES API: " + clientes);
                if (userListener != null) {
                    userListener.onRefreshListaCliente(clientes);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO GET PLANOS API: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public void getAllPlanosTreinoFromClientAPI(final Context context, final boolean isConnected) {
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();
        System.out.println("--> API URL FEED: " + mUrlGetStuffFromUser + "/planotreino/getexercicios/" + getIdUser());

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlGetStuffFromUser + "/planotreino/getexercicios/" + getIdUser(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                planosTreinos = PlanosTreinoJSONParser.parserJsonPlanosTreino(response, context);
                System.out.println("--> RESPOSTA GET PLANOS API: " + planosTreinos);
                if (planosTreinoListener != null) {
                    planosTreinoListener.onRefreshPlanosTreino(planosTreinos);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO GET PLANOS API: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public void getAllExerciciosFromClientAPI(final Context context, final boolean isConnected) {
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();
        System.out.println("--> API URL EXERCICIO: " + mUrlGetStuffFromUser + "/exercicio");

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlGetStuffFromUser + "/exercicio", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                exercicios = ExerciciosJSONParser.parserJsonExercicio(response, context);
                System.out.println("--> RESPOSTA GET EXERCICIOS API: " + exercicios);
                if (exerciciosListener != null) {
                    exerciciosListener.onRefreshExericios(exercicios);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO GET PLANOS API: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public void getAllEmentaFromClientAPI(final Context context, final boolean isConnected) {
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();
        System.out.println("--> API URL EMENTA: " + mUrlGetStuffFromUser + "/ementa");

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlGetStuffFromUser + "/ementa", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ementa = EmentaJSONParser.parserJsonEmenta(response, context);
                System.out.println("--> RESPOSTA GET EMENTA API: " + ementa);
                if (ementaListener != null) {
                    ementaListener.onRefreshEmenta(ementa);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO GET PLANOS API: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public void getAllPlanosNutricaoFromClientAPI(final Context context, final boolean isConnected) {
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();
        System.out.println("--> API URL PLANOS NUTRICAO: " + mUrlGetStuffFromUser + "/planonutricao/getementas/" + getIdUser());

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlGetStuffFromUser + "/planonutricao/getementas/" + getIdUser(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                planosNutricaos = PlanosNutricaoJSONParser.parserJsonPlanosNutricao(response, context);
                System.out.println("--> RESPOSTA GET PLANOS API: " + planosNutricaos);
                if (planosNutricaoListener != null) {
                    planosNutricaoListener.onRefreshPlanosNutricao(planosNutricaos);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO GET PLANOS API: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public void adicionarImagemApi(final String image, final Cliente cliente, final Context context){
        StringRequest req = new StringRequest(Request.Method.POST, mUrlGetStuffFromUser + "/cliente" + getIdUser() + "/upload", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String capa = ClienteJSONParser.parserJsonAvatarCliente(response);

                if (userListener != null)
                    userListener.onRefreshEditar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("image", image);
                return params;
            }
        };
        volleyQueue.add(req);
    }
/*
    public void editarClienteAPI(final Cliente cliente, final Context context) {
        StringRequest req = new StringRequest(Request.Method.PUT, mUrlGetStuffFromUser + "/cliente/get/" + getIdUser(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Cliente c = ClienteJSONParser.parserJsonCliente(response, context);
                onUpdateListaClientesBD(c, EDITAR_BD);
                System.out.println("--> RESPOSTA PUT EDITAR API: " + cliente);

                if (userListener != null)
                    userListener.onRefreshEditar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ClientePrimeiroNome", cliente.getPrimeiroNome());
                params.put("ClienteApelido", cliente.getApelido());
                params.put("ClienteDataNasc", cliente.getDta_nascimento() + "");
                params.put("ClienteNumTele", cliente.getNum_tele() + "");
                params.put("ClienteNIF", cliente.getNif() + "");
                params.put("ClienteSexo", cliente.getSexo());
                params.put("ClienteAvatar", cliente.getAvatar());
                params.put("ClienteAltura", cliente.getAltura() + "");
                params.put("ClientePeso", cliente.getNif() + "");
                params.put("ClienteMassaMuscular", cliente.getMassa_muscular() + "");
                params.put("ClienteMassaGorda", cliente.getMassa_gorda() + "");
                return params;
            }
        };
        volleyQueue.add(req);
    }
 */

    public void editarClienteAPI(final String ClientePrimeiroNome, final String ClienteApelido, final Integer ClienteDataNasc, final Integer ClienteNumTele, final Integer ClienteNIF, final String ClienteSexo, final Integer ClienteAltura, final Integer ClientePeso, final Integer ClienteMassaMuscular, final Integer ClienteMassaGorda, final Context context) {
        StringRequest req = new StringRequest(Request.Method.PUT, mUrlGetStuffFromUser + "/cliente/get/" + getIdUser(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cliente.setPrimeiroNome(ClientePrimeiroNome);
                cliente.setApelido(ClienteApelido);
                cliente.setDta_nascimento(ClienteDataNasc);
                cliente.setNum_tele(ClienteNumTele);
                cliente.setNif(ClienteNIF);
                cliente.setSexo(ClienteSexo);
                cliente.setAltura(ClienteAltura);
                cliente.setPeso(ClientePeso);
                cliente.setMassa_muscular(ClienteMassaMuscular);
                cliente.setMassa_gorda(ClienteMassaGorda);

                if (userListener != null)
                    userListener.onRefreshEditar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ClientePrimeiroNome", cliente.getPrimeiroNome());
                params.put("ClienteApelido", cliente.getApelido());
                params.put("ClienteDataNasc", cliente.getDta_nascimento() + "");
                params.put("ClienteNumTele", cliente.getNum_tele() + "");
                params.put("ClienteNIF", cliente.getNif() + "");
                params.put("ClienteSexo", cliente.getSexo());
                params.put("ClienteAvatar", cliente.getAvatar());
                params.put("ClienteAltura", cliente.getAltura() + "");
                params.put("ClientePeso", cliente.getNif() + "");
                params.put("ClienteMassaMuscular", cliente.getMassa_muscular() + "");
                params.put("ClienteMassaGorda", cliente.getMassa_gorda() + "");
                return params;
            }
        };
        volleyQueue.add(req);
    }


    public void adicionarClienteAPI(final Cliente cliente, final Context context) {
        System.out.println("--> API URL Cliente " + mUrlGetStuffFromUser + "/cliente/get/" + getIdUser());

        StringRequest req = new StringRequest(Request.Method.POST, mUrlGetStuffFromUser + "/cliente/get/" + getIdUser(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Cliente c = ClienteJSONParser.parserJsonCliente(response, context);
                onUpdateListaClientesBD(c, ADICIONAR_BD);

                if (userListener != null)
                    userListener.onRefreshEditar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ClientePrimeiroNome", cliente.getPrimeiroNome());
                params.put("ClienteApelido", cliente.getApelido());
                params.put("ClienteDataNasc", cliente.getDta_nascimento() + "");
                params.put("ClienteNumTele", cliente.getNum_tele() + "");
                params.put("ClienteNIF", cliente.getNif() + "");
                params.put("ClienteSexo", cliente.getSexo());
                params.put("ClienteAvatar", cliente.getAvatar());
                params.put("ClienteAltura", cliente.getAltura() + "");
                params.put("ClientePeso", cliente.getNif() + "");
                params.put("ClienteMassaMuscular", cliente.getMassa_muscular() + "");
                params.put("ClienteMassaGorda", cliente.getMassa_gorda() + "");
                return params;
            }
        };
        volleyQueue.add(req);
    }

    private void onUpdateListaClientesBD(Cliente cliente, int operacao) {
        switch (operacao) {
            case ADICIONAR_BD:
                adicionarClientesBD(cliente);
                break;
            case EDITAR_BD:
                editarClientesBD(cliente);
                break;
        }
    }
/*
    public void adicionarImagemApi(final String image, final Cliente user, final Context context){
        StringRequest request = new StringRequest(Request.Method.GET, mUrlGetStuffFromUser + SharedPreferencesConfig.read(SharedPreferencesConfig.ID_USER, 0) + "/cliente?access-token=" + SharedPreferencesConfig.read(SharedPreferencesConfig.AUTH_KEY, null), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String avatar = ClienteJSONParser.parserJsonAvatarCliente(response);

                if (userListener != null)
                    userListener.onRefreshListaCliente(userListener);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("image", image);
                return params;
            }
        };
        volleyQueue.add(req);
    }
    */
}
