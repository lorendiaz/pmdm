package practicas.fpmislata.com.mischollosbd.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import practicas.fpmislata.com.mischollosbd.bd.MiBD;
import practicas.fpmislata.com.mischollosbd.pojo.Chollo;


/**
 * Created by LOREN on 05/10/2016.
 */

public class CholloDAO implements PojoDAO {
    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "chollos" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_TITULO   = "titulo";
    public static final String C_COLUMNA_ORDEN = "orden";

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{C_COLUMNA_ID, C_COLUMNA_TITULO, C_COLUMNA_ORDEN};

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Chollo c = (Chollo) obj;
        contentValues.put("titulo" , c.getTitulo());
        contentValues.put("orden" , c.getOrden());

        return MiBD.getDB().insert(CholloDAO.C_TABLA, null, contentValues);
    }

    @Override
    public long update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Chollo c = (Chollo) obj;
        contentValues.put("titulo" , c.getTitulo());
        contentValues.put("orden" , c.getOrden());

        String condicion = "_id=" + String.valueOf(c.getId());
        return MiBD.getDB().update(CholloDAO.C_TABLA, contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Chollo c = (Chollo) obj;
        String condicion = "_id=" + String.valueOf(c.getId());

        MiBD.getDB().delete(CholloDAO.C_TABLA, condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Chollo c = (Chollo) obj;
        String condicion = "";
        if(TextUtils.isEmpty(c.getTitulo())){
            condicion = "_id=" + String.valueOf(c.getId());
        }else{
            condicion = "titulo LIKE '%" + String.valueOf(c.getTitulo())  + "%'";
        }

        Cursor cursor = MiBD.getDB().query(CholloDAO.C_TABLA, columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setTitulo(cursor.getString(1));
            c.setOrden(cursor.getInt(2));

            // Obtenemos la lista de alojamientos
            //c.setListaAlojamientos(MiBD.getInstance(null).getAlojamientoDAO().getAlojamientos(c));
        }else{
            c = null;
        }
        cursor.close();
        return c;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Chollo> listaChollos = new ArrayList<>();
        Cursor cursor = MiBD.getDB().query(CholloDAO.C_TABLA, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                Chollo c = new Chollo();
                c.setId(cursor.getInt(0));
                c.setTitulo(cursor.getString(1));
                c.setOrden(cursor.getInt(2));

                // Obtenemos la lista de alojamientos
                //c.setListaAlojamientos(MiBD.getInstance(null).getAlojamientoDAO().getAlojamientos(c));

                listaChollos.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaChollos;
    }
}