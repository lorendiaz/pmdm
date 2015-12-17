package com.fpmislata.practicas.aplicacion38;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by loren on 10/12/15.
 */
public class HipotecasDAO {

    /**
     * Definimos constante con el nombre de la tabla, así no nos equivocamos
     */
    public static final String C_TABLA = "hipotecas" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla, así no nos equivocamos
     */
    public static final String C_COLUMNA_ID   = "_id";
    public static final String C_COLUMNA_NOMBRE = "nombre";
    public static final String C_COLUMNA_CONDICIONES = "condiciones";
    public static final String C_COLUMNA_CONTACTO = "contacto";
    public static final String C_COLUMNA_EMAIL = "email";
    public static final String C_COLUMNA_TELEFONO = "telefono";
    public static final String C_COLUMNA_OBSERVACIONES = "observaciones";
    public static final String C_COLUMNA_PASIVO = "pasivo_sn";

    private Context contexto;
    private HipotecasSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_COLUMNA_ID, C_COLUMNA_NOMBRE, C_COLUMNA_CONDICIONES, C_COLUMNA_CONTACTO, C_COLUMNA_EMAIL, C_COLUMNA_TELEFONO, C_COLUMNA_OBSERVACIONES, C_COLUMNA_PASIVO} ;

    public HipotecasDAO(Context context) {
        this.contexto = context;
    }

    public HipotecasDAO abrir() {
        dbHelper = new HipotecasSQLiteHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbHelper.close();
    }

    /**
     * Devuelve un cursor con todas las filas y todas las columnas de la tabla
     */
    public Cursor getCursor() {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);
        return c;
    }

    /**
     * Devuelve cursor con todos las columnas del registro
     */
    public Cursor getRegistro(long id) {
        String condicion = C_COLUMNA_ID + "=" + id;
        Cursor c = db.query( true, C_TABLA, columnas, condicion, null, null, null, null, null);

        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Inserta los valores en un registro de la tabla
     */
    public long insert(ContentValues reg) {
        if (db == null)
            abrir();

        return db.insert(C_TABLA, null, reg);
    }

    /**
     * Modificar el registro
     */
    public long update(ContentValues reg) {
        long result = 0;

        if (db == null)
            abrir();

        if (reg.containsKey(C_COLUMNA_ID)) {
            //
            // Obtenemos el id y lo borramos de los valores a actualizar, ya que el id no se actualizar
            //
            long id = reg.getAsLong(C_COLUMNA_ID);
            reg.remove(C_COLUMNA_ID);

            //
            // Actualizamos el registro con el identificador que hemos extraido
            //
            result = db.update(C_TABLA, reg, "_id=" + id, null);
        }
        return result;
    }

    /**
     * Eliminar el registro con el identificador indicado
     */
    public long delete(long id) {
        if (db == null)
            abrir();

        return db.delete(C_TABLA, "_id=" + id, null);
    }
}
