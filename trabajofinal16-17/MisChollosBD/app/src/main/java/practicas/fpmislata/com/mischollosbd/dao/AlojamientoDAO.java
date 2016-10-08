package practicas.fpmislata.com.mischollosbd.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import practicas.fpmislata.com.mischollosbd.bd.MiBD;
import practicas.fpmislata.com.mischollosbd.pojo.Alojamiento;
import practicas.fpmislata.com.mischollosbd.pojo.Chollo;

/**
 * Created by LOREN on 05/10/2016.
 */

public class AlojamientoDAO implements PojoDAO {
    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "alojamientos" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_IDCHOLLO   = "idChollo";
    // Informacion resumida
    public static final String C_COLUMNA_PRECIOPERSONA = "precioPersona";
    public static final String C_COLUMNA_INFORMACIONRESUMIDA = "informacionResumida";
    public static final String C_COLUMNA_SIINCLUYE = "siIncluye";
    public static final String C_COLUMNA_NOINCLUYE = "noIncluye";
    public static final String C_COLUMNA_NOTASIMPORTANTES = "notasImportantes";
    public static final String C_COLUMNA_VIAJE = "viaje";
    public static final String C_COLUMNA_DESCUENTOS = "descuentos";
    public static final String C_COLUMNA_CONDICIONES = "condiciones";
    public static final String C_COLUMNA_LOCALIDAD = "localidad";
    // Alojamiento
    public static final String C_COLUMNA_DESCRIPCIONGENERAL = "descripcionGeneral";
    public static final String C_COLUMNA_SERVICIOSALOJAMIENTO = "serviciosAlojamiento";
    public static final String C_COLUMNA_SERVICIOSHABITACION = "serviciosHabitacion";
    public static final String C_COLUMNA_HORARIOSHABITACION = "horariosHabitacion";
    // Valoracion
    public static final String C_COLUMNA_NOTA   = "nota";
    public static final String C_COLUMNA_ESTRELLAS = "estrellas";
    public static final String C_COLUMNA_NUMEROENCUESTAS = "numeroEncuestas";
    public static final String C_COLUMNA_HABITACIONES = "habitaciones";
    public static final String C_COLUMNA_SERVICIOS = "servicios";
    public static final String C_COLUMNA_LIMPIEZA = "limpieza";
    public static final String C_COLUMNA_COMIDA = "comida";
    public static final String C_COLUMNA_PERSONAL   = "personal";
    public static final String C_COLUMNA_CALIDADPRECIO = "calidadPrecio";

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{C_COLUMNA_ID,C_COLUMNA_IDCHOLLO,C_COLUMNA_PRECIOPERSONA,
            C_COLUMNA_INFORMACIONRESUMIDA,C_COLUMNA_SIINCLUYE,C_COLUMNA_NOINCLUYE,C_COLUMNA_NOTASIMPORTANTES,
            C_COLUMNA_VIAJE,C_COLUMNA_DESCUENTOS,C_COLUMNA_CONDICIONES,C_COLUMNA_LOCALIDAD,C_COLUMNA_DESCRIPCIONGENERAL,
            C_COLUMNA_SERVICIOSALOJAMIENTO,C_COLUMNA_SERVICIOSHABITACION,C_COLUMNA_HORARIOSHABITACION,
            C_COLUMNA_NOTA,C_COLUMNA_ESTRELLAS,C_COLUMNA_NUMEROENCUESTAS,C_COLUMNA_HABITACIONES,
            C_COLUMNA_SERVICIOS,C_COLUMNA_LIMPIEZA,C_COLUMNA_COMIDA,C_COLUMNA_PERSONAL,C_COLUMNA_CALIDADPRECIO} ;

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Alojamiento c = (Alojamiento) obj;
        contentValues.put("idChollo" , c.getChollo().getId());
        // Informacion resumida
        contentValues.put("precioPersona", c.getPrecioPersona());
        contentValues.put("informacionResumida", c.getInformacionResumida());
        // Informacion
        contentValues.put("siIncluye", c.getSiIncluye());
        contentValues.put("noIncluye", c.getNoIncluye());
        contentValues.put("notasImportantes", c.getNotasImportantes());
        contentValues.put("viaje", c.getViaje());
        contentValues.put("localidad", c.getLocalidad());
        // Alojamiento
        contentValues.put("descripcionGeneral", c.getDescripcionGeneral());
        contentValues.put("serviciosAlojamiento", c.getServiciosAlojamiento());
        contentValues.put("serviciosHabitacion", c.getServiciosHabitacion());
        contentValues.put("horariosHabitacion", c.getHorariosHabitacion());
        // Valoracion
        contentValues.put("nota", c.getNota());
        contentValues.put("estrellas", c.getEstrellas());
        contentValues.put("numeroEncuestas", c.getNumeroEncuestas());
        contentValues.put("habitaciones", c.getHabitaciones());
        contentValues.put("servicios", c.getServicios());
        contentValues.put("limpieza", c.getLimpieza());
        contentValues.put("comida", c.getComida());
        contentValues.put("personal", c.getPersonal());
        contentValues.put("calidadPrecio", c.getCalidadPrecio());

        return MiBD.getDB().insert(AlojamientoDAO.C_TABLA, null, contentValues);
    }

    @Override
    public long update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Alojamiento c = (Alojamiento) obj;
        contentValues.put("idChollo" , c.getChollo().getId());
        // Informacion resumida
        contentValues.put("precioPersona", c.getPrecioPersona());
        contentValues.put("informacionResumida", c.getInformacionResumida());
        // Informacion
        contentValues.put("siIncluye", c.getSiIncluye());
        contentValues.put("noIncluye", c.getNoIncluye());
        contentValues.put("notasImportantes", c.getNotasImportantes());
        contentValues.put("viaje", c.getViaje());
        contentValues.put("localidad", c.getLocalidad());
        // Alojamiento
        contentValues.put("descripcionGeneral", c.getDescripcionGeneral());
        contentValues.put("serviciosAlojamiento", c.getServiciosAlojamiento());
        contentValues.put("serviciosHabitacion", c.getServiciosHabitacion());
        contentValues.put("horariosHabitacion", c.getHorariosHabitacion());
        // Valoracion
        contentValues.put("nota", c.getNota());
        contentValues.put("estrellas", c.getEstrellas());
        contentValues.put("numeroEncuestas", c.getNumeroEncuestas());
        contentValues.put("habitaciones", c.getHabitaciones());
        contentValues.put("servicios", c.getServicios());
        contentValues.put("limpieza", c.getLimpieza());
        contentValues.put("comida", c.getComida());
        contentValues.put("personal", c.getPersonal());
        contentValues.put("calidadPrecio", c.getCalidadPrecio());

        String condicion = "_id=" + String.valueOf(c.getId());

        return MiBD.getDB().update(AlojamientoDAO.C_TABLA, contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Alojamiento c = (Alojamiento) obj;
        String condicion = "_id=" + String.valueOf(c.getId());

        //Se borra el alojamiento indicado en el campo de texto
        MiBD.getDB().delete(AlojamientoDAO.C_TABLA, condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Alojamiento c = (Alojamiento) obj;
        String condicion = "";
        if(TextUtils.isEmpty(c.getLocalidad())){
            condicion = "_id=" + String.valueOf(c.getId());
        }else{
            condicion = "localidad LIKE '%" + String.valueOf(c.getLocalidad())  + "%'";
        }

        Cursor cursor = MiBD.getDB().query(AlojamientoDAO.C_TABLA, columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));

            // Obtenemos el chollo y lo asignamos
            Chollo a = new Chollo();
            a.setId(cursor.getInt(1));
            a = (Chollo) MiBD.getInstanceBD().getCholloDAO().search(a);
            c.setChollo(a);

            c.setPrecioPersona(cursor.getFloat(2));
            c.setInformacionResumida(cursor.getString(3));
            c.setSiIncluye(cursor.getString(4));
            c.setNoIncluye(cursor.getString(5));
            c.setNotasImportantes(cursor.getString(6));

            c.setViaje(cursor.getString(7));
            c.setDescuentos(cursor.getString(8));
            c.setCondiciones(cursor.getString(9));
            c.setLocalidad(cursor.getString(10));

            c.setDescripcionGeneral(cursor.getString(11));
            c.setServiciosAlojamiento(cursor.getString(12));
            c.setServiciosHabitacion(cursor.getString(13));
            c.setHorariosHabitacion(cursor.getString(14));
            c.setNota(cursor.getFloat(15));

            c.setEstrellas(cursor.getInt(16));
            c.setNumeroEncuestas(cursor.getInt(17));
            c.setHabitaciones(cursor.getFloat(18));
            c.setServicios(cursor.getFloat(19));
            c.setLimpieza(cursor.getFloat(20));
            c.setComida(cursor.getFloat(21));
            c.setPersonal(cursor.getFloat(22));
            c.setCalidadPrecio(cursor.getFloat(23));

            // Obtenemos la lista de valoraciones
            //c.setListaValoraciones(MiBD.getInstance(null).getValoracionesDAO().getValoraciones(c));

        }else{
            c = null;
        }
        cursor.close();
        return c;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Alojamiento> listaAlojamientos = new ArrayList<>();
        Cursor cursor = MiBD.getDB().query(AlojamientoDAO.C_TABLA, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                Alojamiento c = new Alojamiento();
                c.setId(cursor.getInt(0));

                // Obtenemos el chollo y lo asignamos
                Chollo a = new Chollo();
                a.setId(cursor.getInt(1));
                a = (Chollo) MiBD.getInstanceBD().getCholloDAO().search(a);
                c.setChollo(a);

                c.setPrecioPersona(cursor.getFloat(2));
                c.setInformacionResumida(cursor.getString(3));
                c.setSiIncluye(cursor.getString(4));
                c.setNoIncluye(cursor.getString(5));
                c.setNotasImportantes(cursor.getString(6));

                c.setViaje(cursor.getString(7));
                c.setDescuentos(cursor.getString(8));
                c.setCondiciones(cursor.getString(9));
                c.setLocalidad(cursor.getString(10));

                c.setDescripcionGeneral(cursor.getString(11));
                c.setServiciosAlojamiento(cursor.getString(12));
                c.setServiciosHabitacion(cursor.getString(13));
                c.setHorariosHabitacion(cursor.getString(14));
                c.setNota(cursor.getFloat(15));

                c.setEstrellas(cursor.getInt(16));
                c.setNumeroEncuestas(cursor.getInt(17));
                c.setHabitaciones(cursor.getFloat(18));
                c.setServicios(cursor.getFloat(19));
                c.setLimpieza(cursor.getFloat(20));
                c.setComida(cursor.getFloat(21));
                c.setPersonal(cursor.getFloat(22));
                c.setCalidadPrecio(cursor.getFloat(23));

                // Obtenemos la lista de valoraciones
                //c.setListaValoraciones(MiBD.getInstance(null).getValoracionesDAO().getValoraciones(c));

                listaAlojamientos.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaAlojamientos;
    }

    public ArrayList getAlojamientosByChollo(Chollo chollo) {
        ArrayList<Alojamiento> listaAlojamientos = new ArrayList<>();
        String condicion = "idChollo=" + String.valueOf(chollo.getId());

        Cursor cursor = MiBD.getDB().query(AlojamientoDAO.C_TABLA, columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                Alojamiento c = new Alojamiento();
                c.setId(cursor.getInt(0));

                // Obtenemos el chollo y lo asignamos
                Chollo a = new Chollo();
                a.setId(cursor.getInt(1));
                a = (Chollo) MiBD.getInstanceBD().getCholloDAO().search(a);
                c.setChollo(a);

                c.setPrecioPersona(cursor.getFloat(2));
                c.setInformacionResumida(cursor.getString(3));
                c.setSiIncluye(cursor.getString(4));
                c.setNoIncluye(cursor.getString(5));
                c.setNotasImportantes(cursor.getString(6));

                c.setViaje(cursor.getString(7));
                c.setDescuentos(cursor.getString(8));
                c.setCondiciones(cursor.getString(9));
                c.setLocalidad(cursor.getString(10));

                c.setDescripcionGeneral(cursor.getString(11));
                c.setServiciosAlojamiento(cursor.getString(12));
                c.setServiciosHabitacion(cursor.getString(13));
                c.setHorariosHabitacion(cursor.getString(14));
                c.setNota(cursor.getFloat(15));

                c.setEstrellas(cursor.getInt(16));
                c.setNumeroEncuestas(cursor.getInt(17));
                c.setHabitaciones(cursor.getFloat(18));
                c.setServicios(cursor.getFloat(19));
                c.setLimpieza(cursor.getFloat(20));
                c.setComida(cursor.getFloat(21));
                c.setPersonal(cursor.getFloat(22));
                c.setCalidadPrecio(cursor.getFloat(23));

                // Obtenemos la lista de valoraciones
                //c.setListaValoraciones(MiBD.getInstance(null).getValoracionesDAO().getValoraciones(c));

                listaAlojamientos.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaAlojamientos;
    }
}
