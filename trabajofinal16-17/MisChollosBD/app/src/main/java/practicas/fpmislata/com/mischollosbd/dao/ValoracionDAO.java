package practicas.fpmislata.com.mischollosbd.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import practicas.fpmislata.com.mischollosbd.bd.MiBD;
import practicas.fpmislata.com.mischollosbd.pojo.Alojamiento;
import practicas.fpmislata.com.mischollosbd.pojo.Usuario;
import practicas.fpmislata.com.mischollosbd.pojo.Valoracion;

/**
 * Created by LOREN on 24/10/2016.
 */

public class ValoracionDAO implements PojoDAO {
    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "valoraciones" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_IDUSUARIO   = "idUsuario";
    public static final String C_COLUMNA_IDALOJAMIENTO   = "idAlojamiento";
    public static final String C_COLUMNA_FECHA = "fecha";
    public static final String C_COLUMNA_NOTA = "nota";
    public static final String C_COLUMNA_ESTRELLAS = "estrellas";
    public static final String C_COLUMNA_HABITACIONES = "habitaciones";
    public static final String C_COLUMNA_SERVICIOS = "servicios";
    public static final String C_COLUMNA_LIMPIEZA = "limpieza";
    public static final String C_COLUMNA_COMIDA = "comida";
    public static final String C_COLUMNA_PERSONAL   = "personal";
    public static final String C_COLUMNA_CALIDADPRECIO = "calidadPrecio";
    public static final String C_COLUMNA_LOMEJOR = "loMejor";
    public static final String C_COLUMNA_LOPEOR = "loPeor";

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{C_COLUMNA_ID, C_COLUMNA_IDUSUARIO, C_COLUMNA_IDALOJAMIENTO,
    C_COLUMNA_FECHA, C_COLUMNA_NOTA, C_COLUMNA_ESTRELLAS, C_COLUMNA_HABITACIONES, C_COLUMNA_SERVICIOS,
    C_COLUMNA_LIMPIEZA, C_COLUMNA_LIMPIEZA, C_COLUMNA_COMIDA, C_COLUMNA_PERSONAL, C_COLUMNA_CALIDADPRECIO,
    C_COLUMNA_LOMEJOR,C_COLUMNA_LOPEOR};

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Valoracion c = (Valoracion) obj;
        contentValues.put("idUsuario" , c.getUsuario().getId());
        contentValues.put("idAlojamiento" , c.getAlojamiento().getId());
        contentValues.put("fecha", c.getFecha());
        contentValues.put("nota" , c.getNota());
        contentValues.put("estrellas" , c.getEstrellas());
        contentValues.put("habitaciones" , c.getHabitaciones());
        contentValues.put("servicios" , c.getServicios());
        contentValues.put("limpieza" , c.getLimpieza());
        contentValues.put("comida" , c.getComida());
        contentValues.put("personal" , c.getPersonal());
        contentValues.put("calidadPrecio" , c.getCalidadPrecio());
        contentValues.put("loMejor" , c.getLoMejor());
        contentValues.put("loPeor" , c.getLoPeor());

        return MiBD.getDB().insert(ValoracionDAO.C_TABLA, null, contentValues);
    }

    @Override
    public long update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Valoracion c = (Valoracion) obj;
        contentValues.put("idUsuario" , c.getUsuario().getId());
        contentValues.put("idAlojamiento" , c.getAlojamiento().getId());
        contentValues.put("fecha", c.getFecha());
        contentValues.put("nota" , c.getNota());
        contentValues.put("estrellas" , c.getEstrellas());
        contentValues.put("habitaciones" , c.getHabitaciones());
        contentValues.put("servicios" , c.getServicios());
        contentValues.put("limpieza" , c.getLimpieza());
        contentValues.put("comida" , c.getComida());
        contentValues.put("personal" , c.getPersonal());
        contentValues.put("calidadPrecio" , c.getCalidadPrecio());
        contentValues.put("loMejor" , c.getLoMejor());
        contentValues.put("loPeor" , c.getLoPeor());

        String condicion = "_id=" + String.valueOf(c.getId());

        return MiBD.getDB().insert(ValoracionDAO.C_TABLA, null, contentValues);
    }

    @Override
    public void delete(Object obj) {
        Valoracion c = (Valoracion) obj;
        String condicion = "_id=" + String.valueOf(c.getId());

        //Se borra la valoracion indicado en el campo de texto
        MiBD.getDB().delete(ValoracionDAO.C_TABLA, condicion, null);
    }

    @Override
    public Object search(Object obj) {
        // No tiene sentido su implementacion
        return null;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Valoracion> listaValoraciones = new ArrayList<>();
        Cursor cursor = MiBD.getDB().query(ValoracionDAO.C_TABLA, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                Valoracion c = new Valoracion();
                c.setId(cursor.getInt(0));

                // Obtenemos el usuario y lo asignamos
                Usuario a = new Usuario();
                a.setId(cursor.getInt(1));
                a = (Usuario) MiBD.getInstanceBD().getUsuarioDAO().search(a);
                c.setUsuario(a);

                // Obtenemos el alojamiento y lo asignamos
                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setId(cursor.getInt(2));
                alojamiento = (Alojamiento) MiBD.getInstanceBD().getAlojamientoDAO().search(alojamiento);
                c.setAlojamiento(alojamiento);

                c.setFecha(cursor.getLong(3));
                c.setNota(cursor.getFloat(4));
                c.setEstrellas(cursor.getInt(5));
                c.setServicios(cursor.getFloat(6));
                c.setLimpieza(cursor.getFloat(7));
                c.setComida(cursor.getFloat(8));
                c.setPersonal(cursor.getFloat(9));
                c.setCalidadPrecio(cursor.getFloat(10));
                c.setLoMejor(cursor.getString(11));
                c.setLoPeor(cursor.getString(12));

                listaValoraciones.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaValoraciones;
    }

    public ArrayList getValoracionesByAlojamiento(Alojamiento alojamienton) {
        ArrayList<Valoracion> listaValoraciones = new ArrayList<>();
        String condicion = "idAlojamiento=" + String.valueOf(alojamienton.getId());
        Cursor cursor = MiBD.getDB().query(ValoracionDAO.C_TABLA, columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                Valoracion c = new Valoracion();
                c.setId(cursor.getInt(0));

                // Obtenemos el usuario y lo asignamos
                Usuario a = new Usuario();
                a.setId(cursor.getInt(1));
                a = (Usuario) MiBD.getInstanceBD().getUsuarioDAO().search(a);
                c.setUsuario(a);

                // Obtenemos el alojamiento y lo asignamos
                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setId(cursor.getInt(2));
                alojamiento = (Alojamiento) MiBD.getInstanceBD().getAlojamientoDAO().search(alojamiento);
                c.setAlojamiento(alojamiento);

                c.setFecha(cursor.getLong(3));
                c.setNota(cursor.getFloat(4));
                c.setEstrellas(cursor.getInt(5));
                c.setServicios(cursor.getFloat(6));
                c.setLimpieza(cursor.getFloat(7));
                c.setComida(cursor.getFloat(8));
                c.setPersonal(cursor.getFloat(9));
                c.setCalidadPrecio(cursor.getFloat(10));
                c.setLoMejor(cursor.getString(11));
                c.setLoPeor(cursor.getString(12));

                listaValoraciones.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaValoraciones;
    }
}
