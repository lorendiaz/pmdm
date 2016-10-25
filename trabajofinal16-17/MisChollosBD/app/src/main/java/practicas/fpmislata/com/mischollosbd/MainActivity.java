package practicas.fpmislata.com.mischollosbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import practicas.fpmislata.com.mischollosbd.api.MisChollosAPI;
import practicas.fpmislata.com.mischollosbd.pojo.Alojamiento;
import practicas.fpmislata.com.mischollosbd.pojo.Chollo;
import practicas.fpmislata.com.mischollosbd.pojo.Usuario;
import practicas.fpmislata.com.mischollosbd.pojo.Valoracion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MisChollosAPI api = MisChollosAPI.getInstance(this.getApplicationContext());

        TextView txtdatos=(TextView)findViewById(R.id.textView1);

        // Introducimos los datos como si fuera la pantalla inicial
        Log.e(this.getComponentName().getClassName(), "Logeando con el usuario 11111111A");
        Usuario a = new Usuario();
        a.setNif("11111111A");
        a.setClaveSeguridad("1234");

        // logueamos al Usuario
        a = api.login(a);

        txtdatos.append("Datos del usuario logueado\n");
        txtdatos.append("-----------------------------------------\n");
        txtdatos.append(a.toString()+"\n");
        txtdatos.append("\n");


        // Cambiamos la password
        txtdatos.append("Cambiamos la password del usuario\n");
        txtdatos.append("-----------------------------------------\n");
        txtdatos.append("\n");
        a.setClaveSeguridad("12345");
        long p = api.changePassword(a);
        txtdatos.append("Hemos obtenido tras cambiar un " + String.valueOf(p));
        txtdatos.append("\n");
        txtdatos.append("Password cambiada a 12345.\n");
        txtdatos.append("\n");

        // Creamos un nuevo Usuario y intentamos loguearnos con la clave anterior
        txtdatos.append("Intentamos loguear el Usuario con la password inicial, que era 1234\n");
        txtdatos.append("---------------------------------------------------------------------------------\n");
        txtdatos.append("\n");
        Usuario b = new Usuario();
        b.setNif("11111111A");
        b.setClaveSeguridad("1234");

        b = api.login(b);

        if(b==null){
            txtdatos.append("No ha podido loguearse con 1234 como password.\n");
            txtdatos.append("\n");
        }else{
            txtdatos.append("Error: Ha podidod loguearse con la password anterior. Es necesario revisar el código.\n");
            txtdatos.append("\n");
        }

        // Volvemos a dejar la password como estaba y nos logueamos de nuevo
        txtdatos.append("Volvemos a dejar la password como estaba y nos logueamos de nuevo\n");
        txtdatos.append("---------------------------------------------------------------------------------\n");
        txtdatos.append("\n");
        Usuario c = new Usuario();
        c.setNif("11111111A");
        c.setClaveSeguridad("12345");

        c = api.login(c);
        c.setClaveSeguridad("1234");
        api.changePassword(c);
        txtdatos.append("Password cambiada a 1234. Nos logueamos de nuevo.\n");
        txtdatos.append("\n");

        Usuario d = new Usuario();
        d.setNif("11111111A");
        d.setClaveSeguridad("1234");

        // logueamos al Usuario
        d = api.login(d);

        txtdatos.append("Datos del Usuario logueado\n");
        txtdatos.append("-----------------------------------------\n");
        txtdatos.append(d.toString() + "\n");
        txtdatos.append("\n");

        txtdatos.append("Obtenemos la lista de chollos.\n");
        txtdatos.append("------------------------------------------------------------------------------\n");
        ArrayList<Chollo> listaChollos =  api.getListChollos();

        for(int i=0;i<listaChollos.size();i++){
            txtdatos.append("\n" + listaChollos.get(i).toString() + "\n");
        }
        txtdatos.append("\n");

        txtdatos.append("Obtenemos la lista de alojamientos del primer chollo.\n");
        txtdatos.append("----------------------------------------------------------------------------------------------------\n");
        ArrayList<Alojamiento> listaAlojamientos = api.getListAlojamientosByChollo(listaChollos.get(0));

        txtdatos.append("Se han obtenido " + listaAlojamientos.size() + " alojamientos.\n");
        txtdatos.append("----------------------------------------------------------------------------------------------------\n");

        for(int i=0;i<listaAlojamientos.size();i++){
            txtdatos.append("\n" + listaAlojamientos.get(i).getTituloPrincipal() + "\n");
        }

        txtdatos.append("\n");

        txtdatos.append("Obtenemos las valoraciones del usuario 1 y del alojamiento 1.\n");
        txtdatos.append("----------------------------------------------------------------------------------------------------\n");
        ArrayList<Valoracion> listaValoraciones = api.getValoraciones(listaAlojamientos.get(0));
        txtdatos.append("Se han obtenido " + listaValoraciones.size() + " valoraciones.\n");
        txtdatos.append("----------------------------------------------------------------------------------------------------\n");
        for(int i=0;i<listaValoraciones.size();i++){
            txtdatos.append("\n" + listaValoraciones.get(i) + "\n");
        }



    }
}
