package com.fpmislata.practicas.aplicacion20.principal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fpmislata.practicas.aplicacion20.R;
import com.fpmislata.practicas.aplicacion20.fragment.Activity_Fragment_Detalle;

/**
 * Created by loren on 11/10/15.
 */
public class Activity_Detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_detalle);

        Activity_Fragment_Detalle detalle =
                (Activity_Fragment_Detalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);

        detalle.mostrarDetalle(getIntent().getStringExtra("TextoDetalle"));
    }
}