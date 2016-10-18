package practicas.fpmislata.com.mischollosbd.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import practicas.fpmislata.com.mischollosbd.bd.MiBD;
import practicas.fpmislata.com.mischollosbd.pojo.Usuario;

/**
 * Created by LOREN on 05/08/2016.
 */

public class UsuarioDAO implements PojoDAO {
    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "usuarios" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_NIF   = "nif";
    public static final String C_COLUMNA_NOMBRE = "nombre";
    public static final String C_COLUMNA_APELLIDOS = "apellidos";
    public static final String C_COLUMNA_EDAD = "edad";
    public static final String C_COLUMNA_LOCALIDAD = "localidad";
    public static final String C_COLUMNA_CLAVESEGURIDAD = "claveSeguridad";
    public static final String C_COLUMNA_EMAIL = "email";

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{C_COLUMNA_ID, C_COLUMNA_NIF, C_COLUMNA_NOMBRE,
            C_COLUMNA_APELLIDOS, C_COLUMNA_EDAD, C_COLUMNA_LOCALIDAD, C_COLUMNA_CLAVESEGURIDAD,
            C_COLUMNA_EMAIL};


    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Usuario c = (Usuario) obj;
        contentValues.put("nif" , c.getNif());
        contentValues.put("nombre" , c.getNombre());
        contentValues.put("apellidos" , c.getApellidos());
        contentValues.put("edad" , c.getEdad());
        contentValues.put("localidad" , c.getLocalidad());
        contentValues.put("claveSeguridad" , c.getClaveSeguridad());
        contentValues.put("email" , c.getEmail());

        return MiBD.getDB().insert(UsuarioDAO.C_TABLA, null, contentValues);

    }

    @Override
    public long update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Usuario c = (Usuario) obj;
        contentValues.put("nif" , c.getNif());
        contentValues.put("nombre" , c.getNombre());
        contentValues.put("apellidos" , c.getApellidos());
        contentValues.put("edad" , c.getEdad());
        contentValues.put("localidad" , c.getLocalidad());
        contentValues.put("claveSeguridad" , c.getClaveSeguridad());
        contentValues.put("email" , c.getEmail());

        String condicion = "_id=" + String.valueOf(c.getId());
        return MiBD.getDB().update(UsuarioDAO.C_TABLA, contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Usuario c = (Usuario) obj;
        String condicion = "_id=" + String.valueOf(c.getId());

        MiBD.getDB().delete(UsuarioDAO.C_TABLA, condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Usuario c = (Usuario) obj;
        String condicion = "";
        if(!TextUtils.isEmpty(c.getApellidos())){
            condicion = "apellidos LIKE '%" + String.valueOf(c.getApellidos())  + "%'";
        }else if(!TextUtils.isEmpty(c.getNif())){
            condicion = "nif=" + "'" + String.valueOf(c.getNif()) + "'";
        }else{
            condicion = "_id=" + String.valueOf(c.getId());
        }

        Cursor cursor = MiBD.getDB().query(UsuarioDAO.C_TABLA, columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setNif(cursor.getString(1));
            c.setNombre(cursor.getString(2));
            c.setApellidos(cursor.getString(3));
            c.setEdad(cursor.getInt(4));
            c.setLocalidad(cursor.getString(5));
            c.setClaveSeguridad(cursor.getString(6));
            c.setEmail(cursor.getString(7));

        }else{
            c = null;
        }
        cursor.close();
        return c;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Cursor cursor = MiBD.getDB().query(UsuarioDAO.C_TABLA, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Usuario c = new Usuario();
                c.setId(cursor.getInt(0));
                c.setNif(cursor.getString(1));
                c.setNombre(cursor.getString(2));
                c.setApellidos(cursor.getString(3));
                c.setEdad(cursor.getInt(4));
                c.setLocalidad(cursor.getString(5));
                c.setClaveSeguridad(cursor.getString(6));
                c.setEmail(cursor.getString(7));
                listaUsuarios.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaUsuarios;
    }
}
