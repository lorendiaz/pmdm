package com.fpmislata.practicas.aplicacion36;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText v_edtCorreo;
    Button v_btnSalvar;
    Button v_btnRecuperar;
    TextView v_txtValorSalvado;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaramos los elementos con los que vamos a trabajar
        v_edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        v_btnSalvar = (Button) findViewById(R.id.btnSalvar);
        v_btnRecuperar = (Button) findViewById(R.id.btnRecuperar);
        v_txtValorSalvado = (TextView) findViewById(R.id.txtValorSalvado);

        // Obtenemos la referencia a la coleccion de preferencias
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // Declaramos el evento onClick en el boton de salvar el correo, de forma que salvaremos  los datos
        v_btnSalvar.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", v_edtCorreo.getText().toString());
                editor.commit();
            }
        });

        // Declaramos el evento onClick en el boton de cargar el correo de las preferencias
        v_btnRecuperar.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                String correo = prefs.getString("email", "Sin valor");
                v_txtValorSalvado.setText(correo);
            }
        });
    }
}
