package com.sgdn.sgdn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sgdn.sgdn.Dominio.Maquina;
import com.sgdn.sgdn.Fragment.AddMaquinaFragment;
import com.sgdn.sgdn.Fragment.ComponenteFragment;
import com.sgdn.sgdn.Fragment.DashboardFragment;
import com.sgdn.sgdn.Fragment.DetalhesNotificacaoFragment;
import com.sgdn.sgdn.Fragment.InspecaoFragment;
import com.sgdn.sgdn.Fragment.MaquinaFragment;
import com.sgdn.sgdn.Fragment.NotificacaoFragment;
import com.sgdn.sgdn.R;
import com.sgdn.sgdn.SrcDataBase.DataBase;

import java.util.ArrayList;

public class MenuLateralActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MenuLateralActivity.this, MenuLateralActivity.class);
                startActivity(i);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setando Fragmento do Dashboard no menu lateral inicial do app
        String valor = "false";
        if (getIntent().getExtras() != null) {
            DetalhesNotificacaoFragment fragment = new DetalhesNotificacaoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", getIntent().getExtras().getString("isNotification"));
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_container, new DashboardFragment())
                    .commit();
        }
        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_container, new DashboardFragment())
                    .commit();
        }
        */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_maquina) {
            // Setando fragmento para máquinas
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.frame_container, new MaquinaFragment()).commit();

        } else if (id == R.id.nav_componente) {
            // Setando fragmento para componentes
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.frame_container, new ComponenteFragment()).commit();

        } else if (id == R.id.nav_inspecao) {
            // Setando fragmento para inspeção
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.frame_container, new InspecaoFragment()).commit();

        } else if (id == R.id.nav_notificacao) {
            // Setando fragmento para notificação
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.frame_container, new NotificacaoFragment()).commit();
        } else if (id == R.id.nav_sair) {
            Intent i = new Intent(this, AutenticarActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
