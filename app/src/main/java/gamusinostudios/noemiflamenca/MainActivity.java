package gamusinostudios.noemiflamenca;

import android.app.VoiceInteractor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.share);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Modificar este sección para modificar la acción del boton compartir
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Crea contenedor
        ConstraintLayout contenedor = (ConstraintLayout) findViewById(R.id.contenedor);

        FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.nav_vestidos) {
            contenedor.removeAllViews();
            //Actualmente este fragment no hace nada.
            //fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment01()).commit();
            listaVestidos();
        } else if (id == R.id.nav_faldas) {
            contenedor.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment02()).commit();
        } else if (id == R.id.nav_accesorios) {
            contenedor.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment03()).commit();
        } else if (id == R.id.nav_contacto) {
            contenedor.removeAllViews();
            fragmentManager.beginTransaction().replace(R.id.contenedor, new Fragment04()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void listaVestidos(){

        //Crea contenedor
        ConstraintLayout contenedor = (ConstraintLayout) findViewById(R.id.contenedor);
        //Crea TextView
        final TextView miTextView = new TextView(getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "https://mayoral-poisons.000webhostapp.com/consultarUsuario.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Respuesta correcta
                    miTextView.setText("Resultado: "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Respuesta incorrecta
                    miTextView.setText("La base de datos tardó mucho en responder...");
                }
            });
            queue.add(stringRequest);

        //Agrega propiedades al TextView.
        miTextView.setTextColor(Color.BLUE);

        //Agrega vistas al contenedor.
        contenedor.addView(miTextView);
    }
}

