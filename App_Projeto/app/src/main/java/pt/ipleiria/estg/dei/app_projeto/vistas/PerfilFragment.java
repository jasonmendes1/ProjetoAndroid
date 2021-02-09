package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class PerfilFragment extends Fragment {

    private TextView tvNome, tvSexo2, tvEmail2, tvTelefone2, tvDataNasc2, tvNif2, tvDataInicio, tvDataExpirar;
    private ImageView ivAvatar;
    private SharedPreferences sharedPreferences;
    private RequestQueue mQueue;
    private String urlAPI, ipURL;
    private int ID_User;
    private FragmentManager fragmentManager;
    private Button btnCalcularIMC, btnUpload;
    private String currentPhotoPath;


    public static final int CAMERA_REQUEST = 1;
    public static final int IMAGEM_REQUEST = 2;
    public static final int PERMISSAO_CAMARA = 10;
    public static final int PERMISSAO_IMAGEM = 20;


    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
        mQueue = Volley.newRequestQueue(getContext());

        tvNome = rootView.findViewById(R.id.textViewNome);
        tvSexo2 = rootView.findViewById(R.id.textViewSexo2);
        tvDataNasc2 = rootView.findViewById(R.id.textViewDataNasc2);
        tvTelefone2 = rootView.findViewById(R.id.textViewTelefone2);
        tvNif2 = rootView.findViewById(R.id.textViewNif2);
        tvDataInicio = rootView.findViewById(R.id.textViewDataInicio);
        tvDataExpirar = rootView.findViewById(R.id.textViewDataExpirar);
        tvEmail2 = rootView.findViewById(R.id.textViewEmail2);
        ivAvatar = rootView.findViewById(R.id.ivAvatar);
        btnCalcularIMC = rootView.findViewById(R.id.btnCalcularIMC);
        btnUpload = rootView.findViewById(R.id.buttonUpload);


        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EditarPerfilFragment();
                fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalcularIMC.class);
                startActivity(intent);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadFotoActivity.class);
                startActivity(intent);
            }
        });


        ipURL = Singleton.getInstance(getContext()).getIPInput();
        urlAPI = "http://" + ipURL + "/ProjetoWeb/api/web/v1/cliente/get";

        ID_User = Singleton.getInstance(getContext()).getIdUser();
        System.out.println("--> url ClienteAPI: " + urlAPI + "/" + ID_User);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlAPI + "/" + ID_User, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("-->RESPOSTA ");
                        try {
                            JSONObject cliente = response.getJSONObject(0);
                            System.out.println("--> cliente: " + cliente.toString());


                            String ClientePrimeiroNome = cliente.getString("ClientePrimeiroNome");
                            String ClienteSexo = cliente.getString("ClienteSexo");
                            String ClienteDataNasc = cliente.getString("ClienteDataNasc");
                            int ClienteNumTele = cliente.getInt("ClienteNumTele");
                            int ClienteNIF = cliente.getInt("ClienteNIF");
                            String DataSubscricao = cliente.getString("DataSubscricao");
                            String DataExpirar = cliente.getString("DataExpirar");
                            String UserEmail = cliente.getString("UserEmail");

                            //ArrayList<Cliente> clientes = Singleton.getInstance(getContext()).getClientes();

                            //Cliente c = clientes.get(0);
                            tvNome.setText(ClientePrimeiroNome);
                            tvSexo2.setText(ClienteSexo);
                            tvDataNasc2.setText(ClienteDataNasc);
                            tvTelefone2.setText(String.valueOf(ClienteNumTele));
                            tvNif2.setText(String.valueOf(ClienteNIF));
                            tvDataInicio.setText("Início: " + DataSubscricao);
                            tvDataExpirar.setText("Expira: " + DataExpirar);
                            tvEmail2.setText(UserEmail);
                            /*Glide.with(getContext())
                                    .load(c.getAvatar())
                                    .placeholder(R.drawable.avatar_example)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivAvatar);
*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
        return rootView;

    }
/*
    private void carregarCliente() {

        ArrayList<Cliente> clientes = Singleton.getInstance(getContext()).getClientes();
        ArrayList<Subscricao> subscricaos = Singleton.getInstance(getContext()).getSubscricaos();

        if (clientes.size() > 0) {
            Cliente c = clientes.get(0);
            // Subscricao s = subscricaos.get(0);

            tvNome.setText(c.getPrimeiroNome());
            tvSexo2.setText(c.getSexo());
            tvDataNasc2.setText(c.getDta_nascimento());
            tvTelefone2.setText(String.valueOf(c.getNum_tele()));
            tvNif2.setText(String.valueOf(c.getNif()));
            //tvDataInicio.setText("Início: " + s.getData_subscricao());
            //tvDataExpirar.setText("Expira: " + s.getData_expirar());
            // tvEmail2.setText(UserEmail);
            Glide.with(getContext())
                    .load(c.getAvatar())
                    .placeholder(R.drawable.avatar_example)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivAvatar);
        }
    }

 */
/*
    private void dialogUpload() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecione uma opção para carregar imagem:");

        String[] opcoes = {"Câmara", "Galeria"};
        builder.setItems(opcoes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= 23 &&
                                ActivityCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ActivityCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ActivityCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)

                            requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.CAMERA,}, PERMISSAO_CAMARA);
                        else
                            tirarFotoCamera();
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= 23 &&
                                ActivityCompat.checkSelfPermission(getActivity(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                            requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_IMAGEM);
                        else
                            carregarFotoGaleria();
                        break;
                    default:
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void tirarFotoCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = criarFicheiroImagem();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(getActivity(),
                    getPackageName() + ".fileprovider", photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    private File criarFicheiroImagem() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imgNomeFich = "JPEG_" + timeStamp + "_";
        File storageDir = new
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getParentFile(), "Books");
        storageDir.mkdirs();
        File imagem = File.createTempFile(imgNomeFich, ".jpg", storageDir);
        currentPhotoPath = imagem.getAbsolutePath();
        return imagem;
    }

    public void carregarFotoGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGEM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    String[] paths = new String[]{currentPhotoPath};
                    Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                    ivAvatar.setImageBitmap(bitmap);
                    Singleton.getInstance(getActivity()).adicionarImagemApi(getStringImage(bitmap), livro, getApplicationContext());
                    MediaScannerConnection.scanFile(this, paths, null, new
                            MediaScannerConnection.MediaScannerConnectionClient() {

                                @Override
                                public void onMediaScannerConnected() {
                                    Log.d("Detalhes", "onScanCompleted");
                                }

                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.d("Detalhes", "onScanCompleted");
                                }
                            });
                }
                break;
            case IMAGEM_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    Uri filePath = intent.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        ivCapa.setImageBitmap(bitmap);
                        SingletonGestorLivros.getInstance(getApplicationContext()).adicionarImagemApi(getStringImage(bitmap),livro,getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
        }
    }

 */

}
