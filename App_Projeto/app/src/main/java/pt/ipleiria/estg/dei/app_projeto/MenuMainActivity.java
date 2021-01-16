package pt.ipleiria.estg.dei.app_projeto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import pt.ipleiria.estg.dei.app_projeto.vistas.LoginActivity;
import pt.ipleiria.estg.dei.app_projeto.vistas.PerfilFragment;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;

    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    public static final String CHAVE_USERNAME = "USERNAME";
    public static final String CHAVE_ID = "-1";
    public static final String CHAVE_EMAIL = "EMAIL";
    private String username = "";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.nvAbrir ,R.string.nvFechar);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        carregarCabecalho();
        carregarFragmentoInicial();

    }

    private void carregarCabecalho() {
        username = getIntent().getStringExtra(CHAVE_USERNAME);
        String id = getIntent().getStringExtra(CHAVE_ID);
        String email = getIntent().getStringExtra(CHAVE_EMAIL);

        String usernameId = username + "#" + id;
        View view = navigationView.getHeaderView(0);
        TextView textViewUser = view.findViewById(R.id.tvNome);
        TextView txtEmail = view.findViewById(R.id.tvEmailPerfil);
        textViewUser.setText(usernameId);
        txtEmail.setText(email);
    }

    private void carregarFragmentoInicial(){
        navigationView.setCheckedItem(R.id.nav_profile);
        Fragment fragment = new PerfilFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        setTitle(R.string.perfil);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                System.out.println("-->PERFIL");
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_planos_treino:
                System.out.println("-->PLANOS-TREINO");
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_planos_nutricao:
                System.out.println("-->PLANOS-NUTRICAO");
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_horarios:
                System.out.println("-->HORARIOS");
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_info_gym:
                System.out.println("-->INFO-GYM");
                setTitle(menuItem.getTitle());
                break;
            case R.id.nav_logout:
                sharedPreferences = getSharedPreferences(MenuMainActivity.SECCAO_INFO_USER, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString(MenuMainActivity.SECCAO_INFO_USER, "-1");
                editor.apply();
                dialogLogout();
                break;
            default:
                fragment = new PerfilFragment();
        }

        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.contentFragment,
                    fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dialogLogout(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout")
                .setMessage(getString(R.string.confirmar_logout))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MenuMainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }})

                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }})
                .setIcon(android.R.drawable.ic_lock_power_off)
                .show();
    }

}
