package practicas.fpmislata.com.mischollosbd.api;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import practicas.fpmislata.com.mischollosbd.bd.MiBD;
import practicas.fpmislata.com.mischollosbd.pojo.Alojamiento;
import practicas.fpmislata.com.mischollosbd.pojo.Chollo;
import practicas.fpmislata.com.mischollosbd.pojo.Usuario;
import practicas.fpmislata.com.mischollosbd.pojo.Valoracion;

/**
 * Created by LOREN on 05/10/2016.
 */

public class MisChollosAPI {

    private static MisChollosAPI instance = null;

    public static MisChollosAPI getInstance(Context contextoAplicacion) {
        if(instance == null) {
            instance = new MisChollosAPI();
            MiBD.setContextoAplicacion(contextoAplicacion);
        }
        return instance;
    }

    // Operacion Login: Verifica que el Usuario existe y que su contraseña es correcta. Recibira un Usuario
    // que solo contendrá el nif y la password.
    public Usuario login(Usuario c){
        Usuario aux = (Usuario) MiBD.getInstanceBD().getUsuarioDAO().searchByPassword(c);
        if(aux==null){
            return null;
        }else if (aux.getClaveSeguridad().trim().equals(c.getClaveSeguridad().trim())){
            return aux;
        }else{
            return null;
        }
    }

    // Operacion changePassword: Cambia la password del usuario. Recibirá el usuario de la aplicación con la password cambiada.
    // Si devuelve un 1 es que ha verificado el cambio de password como correcto y todo ha ido bien, mientras que si devuelve
    // mientras que si devuelve un 0 no ha verificado el cambio de password como correcto y ha habido un error al cambiarlo.
    public long changePassword(Usuario c){
        long resultado =  MiBD.getInstanceBD().getUsuarioDAO().update(c);
        if (resultado==0) {
            return 0;
        } else {
            return 1;
        }
    }

    // Operacion getListChollos: Obtiene una lista de chollos
    public ArrayList<Chollo> getListChollos(){
        return MiBD.getInstanceBD().getCholloDAO().getAll();
    }

    // Operacion getListAlojamientosByChollo: Obtiene una lista de alojamientos a partir de un chollo que se le pasa
    public ArrayList<Alojamiento> getListAlojamientosByChollo(Chollo chollo){
        return MiBD.getInstanceBD().getAlojamientoDAO().getAlojamientosByChollo(chollo);
    }

    // Operacion makeValoracion: metodo que se encarga de insertar una valoracion de un usuario y
    // de un alojamiento en la base de datos, así como de actualizar los totales de las valoraciones
    // del alojamiento
    public void makeValoracion(Valoracion valoracion) throws Exception{
        // Hacer valoracion
    }

    // Operacion getValoraciones: Obtiene una lista de las valoraciones de un alojamiento.
    public ArrayList<Valoracion> getValoraciones(Alojamiento alojamiento){
        return MiBD.getInstanceBD().getValoracionDAO().getValoracionesByAlojamiento(alojamiento);
    }

    // Operacion calcularValoracionesAlojamiento: método que se encarga de actualizar las valoraciones
    // del alojamiento a partir de la lista de valoraciones existentes del alojamiento. Las valoraciones
    // son calculadas en forma de media. Solo puede ser invocado desde dentro de makeValoracion(Valoracion valoracion)
    private void calcularValoracionesAlojamiento(Alojamiento alojamiento) throws Exception{

    }


}
