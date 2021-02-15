package pt.ipleiria.estg.dei.app_projeto.vistas;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pt.ipleiria.estg.dei.app_projeto.MenuMainActivity;
import pt.ipleiria.estg.dei.app_projeto.R;
import pt.ipleiria.estg.dei.app_projeto.models.Cliente;
import pt.ipleiria.estg.dei.app_projeto.models.Singleton;

public class UploadFotoActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private String urlAPI, ipURL;
    private int ID_User;
    private Cliente cliente;

    private String token;
    public static final int CAMERA_REQUEST = 1;
    public static final int IMAGEM_REQUEST = 2;
    public static final int PERMISSAO_CAMARA = 10;
    public static final int PERMISSAO_IMAGEM = 20;
    private String currentPhotoPath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_foto);
        ivAvatar = findViewById(R.id.ivAvatar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.CHAVE_USERNAME, Context.MODE_PRIVATE);
        token = sharedPrefUser.getString(MenuMainActivity.TOKEN, null);

        ipURL = Singleton.getInstance(getApplicationContext()).getIPInput();
        urlAPI = "http://" + ipURL + "/ProjetoWeb/api/web/v1/cliente/get";

        ID_User = Singleton.getInstance(getApplicationContext()).getIdUser();
        System.out.println("--> url ClienteAPI: " + urlAPI + "/" + ID_User);

       /*Button btnUpload = findViewById(R.id.buttonUpload);
       btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpload();
            }
        });

        */
    }



    private void dialogUpload() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione uma opção para carregar imagem:");

        String[] opcoes = {"Câmara", "Galeria"};
        builder.setItems(opcoes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= 23 &&
                                ActivityCompat.checkSelfPermission(getApplicationContext(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ActivityCompat.checkSelfPermission(getApplicationContext(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                ActivityCompat.checkSelfPermission(getApplicationContext(),
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                            ActivityCompat.requestPermissions(UploadFotoActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.CAMERA,}, PERMISSAO_CAMARA);
                        else
                            tirarFotoCamera();
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= 23 &&
                                ActivityCompat.checkSelfPermission(getApplicationContext(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                            ActivityCompat.requestPermissions(UploadFotoActivity.this,
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSAO_CAMARA:
                if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                        (grantResults[1] == PackageManager.PERMISSION_GRANTED) &&
                        (grantResults[2] == PackageManager.PERMISSION_GRANTED))
                    tirarFotoCamera();
                break;
            case PERMISSAO_IMAGEM:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    carregarFotoGaleria();
                break;
        }
    }

    public void carregarFotoGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGEM_REQUEST);
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
            Uri photoURI = FileProvider.getUriForFile(this,
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    String[] paths = new String[]{currentPhotoPath};
                    Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                    ivAvatar.setImageBitmap(bitmap);
                    Singleton.getInstance(getApplicationContext()).adicionarImagemApi(getStringImage(bitmap), cliente, getApplicationContext());
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
                        ivAvatar.setImageBitmap(bitmap);
                        Singleton.getInstance(getApplicationContext()).adicionarImagemApi(getStringImage(bitmap),cliente,getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}