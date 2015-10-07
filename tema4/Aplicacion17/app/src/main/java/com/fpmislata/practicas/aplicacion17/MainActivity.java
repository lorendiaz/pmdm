package com.fpmislata.practicas.aplicacion17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lista;
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instancia del ListView
        lista = (ListView) findViewById(R.id.lista);

        //Inicializar el adaptador con la fuente de datos
        adaptador = new MiArrayAdapter<Tarea>(this, DatosTareas.TAREAS);

        //Relacionando la lista con el adaptador
        lista.setAdapter(adaptador);

        //Estableciendo la escucha
        lista.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_clear) {
                //Limpiar todos los elementos
                adaptador.clear();
                return true;
            }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Tarea tareaActual = (Tarea)adaptador.getItem(position);
        String msg = "Elegiste la tarea:\n"+tareaActual.getNombre()+"-"+tareaActual.getHora();
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

    }
}
