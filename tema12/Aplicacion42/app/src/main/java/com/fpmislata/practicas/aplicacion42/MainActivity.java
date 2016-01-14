package com.fpmislata.practicas.aplicacion42;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Declaramos los botones
    private Button v_btnMapaGeneral;
    private Button v_btnUbicacionActual;
    private Button v_btnPuntosAlmacenados;
    private Button v_btnPolilineas;
    private Button v_btnPoligonos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos el boton del mapa general
        v_btnMapaGeneral = (Button) findViewById(R.id.button_Mapageneral);

        // Obtenemos el boton de ubicacion actual
        v_btnUbicacionActual = (Button) findViewById(R.id.button_Ubicacionactual);

        // Obtenemos el boton de puntos almacenados
        v_btnPuntosAlmacenados = (Button) findViewById(R.id.button_Markers);

        // Obtenemos el boton de polilineas
        v_btnPolilineas = (Button) findViewById(R.id.button_Polilineas);

        // Obtenemos el boton de poligonos
        v_btnPoligonos = (Button) findViewById(R.id.button_Poligonos);

        // Declaramos el listener para el click del mapa general
        v_btnMapaGeneral.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Declaramos la intencion de abrir el mapa general
                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putExtra("operacion", 0);
                startActivity(i);
            }
        });

        // Declaramos el listener para el click de la ubicacion actual
        v_btnUbicacionActual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Declaramos la intencion de abrir la ubicacion actual
                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putExtra("operacion", 1);
                startActivity(i);
            }
        });

        // Declaramos el listener para el click de los puntos almacenados
        v_btnPuntosAlmacenados.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Declaramos la intencion de abrir los puntos almacenados
                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putExtra("operacion", 2);
                startActivity(i);
            }
        });

        // Declaramos el listener para el click de las polilineas
        v_btnPolilineas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Declaramos la intencion de dibujar las polilineas
                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putExtra("operacion", 3);
                startActivity(i);
            }
        });

        // Declaramos el listener para el click de los poligonos
        v_btnPoligonos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Declaramos la intencion de dibujar las polilineas
                Intent i = new Intent(getApplicationContext(), MapaActivity.class);
                i.putExtra("operacion", 4);
                startActivity(i);
            }
        });

    }
}
