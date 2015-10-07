package com.fpmislata.practicas.aplicacion19;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridView grdOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grdOpciones = (GridView) findViewById(R.id.GridOpciones);

        String[] datos = new String[50];
        for (int i = 1; i <= 50; i++)
            datos[i - 1] = "Dato " + i;

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        grdOpciones = (GridView) findViewById(R.id.GridOpciones);

        grdOpciones.setAdapter(adaptador);

        grdOpciones.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            android.view.View v, int position, long id) {
                        Toast.makeText(getApplicationContext(),"Opción seleccionada: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
