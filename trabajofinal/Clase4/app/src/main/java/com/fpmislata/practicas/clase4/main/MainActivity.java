package com.fpmislata.practicas.clase4.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.fpmislata.practicas.clase4.R;
import com.fpmislata.practicas.clase4.bd.MiBancoOperacional;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView mensaje;
    private MiBancoOperacional mbo;
    private AdaptadorClientes adaptadorClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listView);
        mensaje = (TextView) findViewById(R.id.TextView1);

        mbo = new MiBancoOperacional(this);

        adaptadorClientes = new AdaptadorClientes(this, mbo.listaClientes());

        lista.setAdapter(adaptadorClientes);

    }
}
