package com.fpmislata.practicas.clase4.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.fpmislata.practicas.clase4.bd.MiBD;
import com.fpmislata.practicas.clase4.pojo.Cliente;

import java.util.ArrayList;

/**
 * Created by loren on 07/10/15.
 */
public class ClienteDAO implements PojoDAO{


    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Cliente c = (Cliente) obj;
        contentValues.put("nif" , c.getNif());
        contentValues.put("nombre", c.getNombre());
        contentValues.put("apellidos", c.getApellidos());
        contentValues.put("claveSeguridad", c.getClaveSeguridad());
        contentValues.put("email", c.getEmail());
        return MiBD.getDB().insert("clientes", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        Cliente c = (Cliente) obj;
        contentValues.put("nif" , c.getNif());
        contentValues.put("nombre", c.getNombre());
        contentValues.put("apellidos", c.getApellidos());
        contentValues.put("claveSeguridad", c.getClaveSeguridad());
        contentValues.put("email", c.getEmail());

        String condicion = "id=" + String.valueOf(c.getId());

        return MiBD.getDB().update("clientes", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Cliente c = (Cliente) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el cliente indicado en el campo de texto
        MiBD.getDB().delete("clientes", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Cliente c = (Cliente) obj;
        String condicion = "id=" + String.valueOf(c.getId());
        String[] columnas = {
                "id","nif","nombre","apellidos","claveseguridad","email"
        };
        Cursor cursor = MiBD.getDB().query("clientes", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setNif(cursor.getString(1));
            c.setNombre(cursor.getString(2));
            c.setApellidos(cursor.getString(3));
            c.setClaveSeguridad(cursor.getString(4));
            c.setEmail(cursor.getString(5));

            // Obtenemos la lista de cuentas que tiene el cliente
            CuentaDAO cuentaDAO = new CuentaDAO();
            c.setListaCuentas(cuentaDAO.getAll(c));

            return c;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        String[] columnas = {
                "id","nif","nombre","apellidos","claveseguridad","email"
        };
        Cursor cursor = MiBD.getDB().query("clientes", columnas, null, null, null, null, null);
        CuentaDAO cuentaDAO = new CuentaDAO();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Cliente c = new Cliente();
                c.setId(cursor.getInt(0));
                c.setNif(cursor.getString(1));
                c.setNombre(cursor.getString(2));
                c.setApellidos(cursor.getString(3));
                c.setClaveSeguridad(cursor.getString(4));
                c.setEmail(cursor.getString(5));
                c.setListaCuentas(cuentaDAO.getAll(c));
                listaClientes.add(c);

            } while(cursor.moveToNext());
        }
        return listaClientes;
    }

    @Override
    public ArrayList getAll(Object obj) {
        return null;
    }
}
