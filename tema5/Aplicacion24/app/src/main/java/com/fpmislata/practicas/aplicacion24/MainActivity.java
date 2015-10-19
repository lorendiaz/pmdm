package com.fpmislata.practicas.aplicacion24;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAlerta = null;
    private Button btnConfirmacion = null;
    private Button btnSeleccion = null;
    private Button btnPersonalizado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlerta = (Button)findViewById(R.id.btnAlerta);
        btnConfirmacion = (Button)findViewById(R.id.btnConfirmacion);
        btnSeleccion = (Button)findViewById(R.id.btnSeleccion);
        btnPersonalizado = (Button)findViewById(R.id.btnPersonalizado);

        btnAlerta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta");
            }
        });

        btnConfirmacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoConfirmacion dialogo = new DialogoConfirmacion();
                dialogo.show(fragmentManager, "tagConfirmacion");
            }
        });

        btnSeleccion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoSeleccion dialogo = new DialogoSeleccion();
                dialogo.show(fragmentManager, "tagSeleccion");
            }
        });

        btnPersonalizado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoPersonalizado dialogo = new DialogoPersonalizado();
                dialogo.show(fragmentManager, "tagPersonalizado");
            }
        });

    }
}