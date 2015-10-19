package com.fpmislata.practicas.aplicacion21;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SegundaActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
    }

    public void salir(View view){
        this.finish();
    }
}
