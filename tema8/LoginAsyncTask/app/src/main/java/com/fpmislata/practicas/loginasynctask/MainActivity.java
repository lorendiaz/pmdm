package com.fpmislata.practicas.loginasynctask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView v_edtusuario;
    private TextView v_edtpassword;
    private Button v_btnBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v_edtusuario = (TextView) findViewById(R.id.edt_Usuario);
        v_edtpassword = (TextView) findViewById(R.id.edt_Password);
        v_btnBoton = (Button) findViewById(R.id.btn_loguearse);

        v_btnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Recuperamos los datos en nu nuevo usuario
                String nombre = v_edtusuario.getText().toString();
                String password = v_edtpassword.getText().toString();
                Usuario usuario = new Usuario(nombre,password);

                // Creamos el login asincrono y mandamos ejecutarlo
                HacerLoginAsincrono tarea = new HacerLoginAsincrono();
                tarea.execute(usuario);
            }
        });
    }


    /* En primer lugar hay que entender que la AsyncTask es básicamente una tarea asíncrona, y lo que
    esta dentro lo va a ejecutar en una tarea asincronamente. Tiene 4 partes: antes de la ejecucion,
    durante la ejecucion, despues de la ejecucion y mostrando el progreso.

    La AsyncTask se define de la siguiente forma:
    AsyncTask<ParametrosDeEntrada, ProgresoDelProceso, ResultadoDeldoInBackground>

     */

    // Lo mejor es crear la clase asincrona dentro de la clase a la que vamos a hacer el método asincrono
    class HacerLoginAsincrono extends AsyncTask<Usuario, Integer, Usuario> {

        // Declaramos una ventana de dialogo de progreso
        private ProgressDialog progreso;

        /*
           PARTE 1: En este método tenemos que realizar los trabajos previos a la ejcución de la tarea.
           Se utiliza normalmente para configurar la tarea y para mostrar en el la interfaz de usuario que empieza la tarea.
        */
        @Override
        protected void onPreExecute() {

            // Creamos la ventana de dialogo de progreso
            progreso = new ProgressDialog(MainActivity.this);
            // Asignamos el estilo
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // Indicamos un mensaje diciendo lo que estamos haciendo
            progreso.setMessage("Haciendo login...");
            // Activamos la posibilidad de cancelar
            progreso.setCancelable(true);

            // Como hemos activado la posibilidad de cancelar hemos de ponerle un escuchador al dialogo
            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    HacerLoginAsincrono.this.cancel(true);
                }
            });

            // Asignamos el valor máximo
            progreso.setMax(100);
            // Asignamos el valor actual
            progreso.setProgress(0);
            // Mostramos el dialogo
            progreso.show();

            // En este momento da comienzo el doinBackground
        }

        /*
            PARTE 2: Es llamado cuando termina onPreExecute(). Es la parte más importante donde tenemos que
            realizar la tarea propiamente dicha. Es el único método de los cuatro que no se ejecuta en el hilo
            del interfaz de usuario. Lo va a hacer en un hilo nuevo creado para este propósito.

            Aqui entran los parámetros de entrada, que en este caso tendremos como parámetro de entrada
            un usuario (se puede obtener un array de usuarios). Como resultado del proceso
            tendremos un nuevo usuario ya logueado o nulo.
         */

        @Override
        protected Usuario //ResultadoDeldoInBackground
                doInBackground(Usuario... usuario) { //ParametrosDeEntrada
            // Se encarga de publicar en la barra de progreso el progreso de la operacion
            publishProgress(25);
            // Esperamos un segundo
            SystemClock.sleep(1000);

            /*
                Para comprobar el usuario nos inventamos lo siguiente:
                Generamos un numero aleatorio de prueba: si es 0 no se habra verificado el usuario, por
                lo que el usuario a devolver será null, en cambio si es 1 el numero se habra verificado el
                usuario y devolveremos un usuario con valores. Esto que hago aqui ni caso, ya que es algo
                para que de valores aleatorios en cada prueba que hagais.
            */

            Random r = new Random();
            int numero = r.nextInt(2);
            // Aumentamos el progreso
            publishProgress(50);
            // Esperamos un segundo
            SystemClock.sleep(1000);

            // Realizamos la comparacion
            Usuario resultado = null;
            if(numero==0){
                // No hace nada ya que ya tenemos creado resultado como usuario
            }else if(numero==1){
                // Voy a hacer una tonteria como crear un nuevo y asignarle apellidos y edad, de esta
                // forma parece que lo ha encontrado en la base de datos al hacer un login autentico
                resultado = new Usuario(usuario[0].getNombre(),usuario[0].getPassword());
                resultado.setApellidos("Perez");
                resultado.setEdad(32);
            }
            // Aumentamos de nuevo el progreso
            publishProgress(75);
            // Esperamos otro segundo
            SystemClock.sleep(1000);
            // Llegamos al final del progreso
            publishProgress(100);

            // Retornamos el resultado
            return resultado;

            // En este momento se va a parar al método onPostExecute
        }

        /*

            PARTE 3: Este método se usa para mostrar en el interfaz de usuario el resultado de la tarea.
            El parámetro de entrada (de la clase Result) corresponde con el objeto devuelto por el método doInBackground().

            En este caso nos devuelve el Usuario ya validado o nulo, si no existe.
         */

        @Override
        protected void onPostExecute(Usuario resultado) {
            progreso.dismiss();
            // Comprobamos si nos ha llegado como resultado el usuario validado o no
            if(resultado==null){
                Toast.makeText(MainActivity.this, "No se ha podido validar el usuario.", Toast.LENGTH_SHORT).show();
            }else {
                //Pasamos el objeto usuario completo serialiandolo a la actividad BienvenidaActivity
                Intent intencionDatosCliente = new Intent(MainActivity.this, BienvenidaActivity.class);
                // Creamos el bundle
                Bundle c1 = new Bundle();
                // Serializamos el usuario
                c1.putSerializable("usuario", resultado);
                intencionDatosCliente.putExtras(c1);
                // Iniciamos la actividad
                startActivity(intencionDatosCliente);
            }
        }

        /*

            PARTE 4: Este método se utiliza para mostrar el progreso de la tarea al usuario.
            Se ejecuta en el hilo interfaz de usuario, por lo que podremos interactuar con las vistas.
            El progreso de una determinada tarea ha de ser controlado por el programador llamando al método
            publishProgress(Progress...) desde doInBackground(). La clase Progress es utilizada para pasar
            la información de progreso. Un uso frecuente es reemplazarla por Integer y representar
            el porcentaje de progreso en un valor entre el 0 y el 100.
         */

        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Se ha cancelado.", Toast.LENGTH_SHORT).show();
        }

    }
}
