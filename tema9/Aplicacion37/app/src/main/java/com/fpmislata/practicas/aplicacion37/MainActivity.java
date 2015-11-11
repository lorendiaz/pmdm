package com.fpmislata.practicas.aplicacion37;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button v_btnPreferencias;
    private Button v_btnMostrarPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v_btnPreferencias = (Button) findViewById(R.id.btnPreferencias);
        v_btnMostrarPreferencias = (Button) findViewById(R.id.btnMostrarPreferencias);

        v_btnPreferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent i = new Intent(getApplicationContext(), OpcionesActivity.class);
                startActivity(i);
            }
        });

        v_btnMostrarPreferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                Log.i("Aplicacion37", "Reproducir música: " + pref.getBoolean("reproducirMusica", false));
                Log.i("Aplicacion37", "Nick: " + pref.getString("nick", ""));
                Log.i("Aplicacion37", "Pais: " + pref.getString("pais", ""));
            }
        });
    }
}



