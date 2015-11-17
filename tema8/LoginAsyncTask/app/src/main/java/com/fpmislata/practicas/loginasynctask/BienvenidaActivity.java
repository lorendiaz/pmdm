package com.fpmislata.practicas.loginasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BienvenidaActivity extends AppCompatActivity {

    private TextView v_txtEtiqueta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        Usuario a = (Usuario)this.getIntent().getSerializableExtra("usuario");

        v_txtEtiqueta = (TextView) findViewById(R.id.txtBienvenido);
        v_txtEtiqueta.setText("Bienvenido " + a.getNombre() + " " + a.getApellidos());
    }
}
