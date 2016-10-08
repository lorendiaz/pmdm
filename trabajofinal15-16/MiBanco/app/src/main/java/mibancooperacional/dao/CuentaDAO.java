package mibancooperacional.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import java.util.ArrayList;

import mibancooperacional.bd.MiBD;
import mibancooperacional.pojo.Cliente;
import mibancooperacional.pojo.Cuenta;

/**
 * Created by loren on 08/10/15.
 */
public class CuentaDAO implements PojoDAO{

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Cuenta c = (Cuenta) obj;

        contentValues.put("banco" , c.getBanco());
        contentValues.put("sucursal", c.getSucursal());
        contentValues.put("dc", c.getDc());
        contentValues.put("numerocuenta", c.getNumeroCuenta());
        contentValues.put("idcliente", c.getCliente().getId());
        contentValues.put("saldoactual",c.getSaldoActual());
        return MiBD.getDB().insert("cuentas", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Cuenta c = (Cuenta) obj;
        contentValues.put("banco" , c.getBanco());
        contentValues.put("sucursal", c.getSucursal());
        contentValues.put("dc", c.getDc());
        contentValues.put("numerocuenta", c.getNumeroCuenta());
        contentValues.put("saldoactual",c.getSaldoActual());
        contentValues.put("idcliente", c.getCliente().getId());

        String condicion = "id=" + String.valueOf(c.getId());

        return MiBD.getDB().update("cuentas", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Cuenta c = (Cuenta) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("cuentas", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Cuenta c = (Cuenta) obj;
        String condicion = "";
        if(TextUtils.isEmpty(c.getBanco())){
            condicion = "id=" + String.valueOf(c.getId());
        }else{
            condicion = "banco=" + String.valueOf(c.getBanco())  + " AND sucursal = " + String.valueOf(c.getSucursal()) +
                    " AND dc = " + String.valueOf(c.getDc()) + " AND numerocuenta = " + String.valueOf(c.getNumeroCuenta());
        }
        String[] columnas = {
                "id","banco","sucursal","dc","numerocuenta","saldoactual", "idcliente"
        };
        Cursor cursor = MiBD.getDB().query("cuentas", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setBanco(cursor.getString(1));
            c.setSucursal(cursor.getString(2));
            c.setDc(cursor.getString(3));
            c.setNumeroCuenta(cursor.getString(4));
            c.setSaldoActual(cursor.getFloat(5));

            // Obtenemos el cliente y lo asignamos
            Cliente a = new Cliente();
            a.setId(cursor.getInt(6));
            a = (Cliente) MiBD.getInstance(null).getClienteDAO().search(a);
            c.setCliente(a);

            // Obtenemos la lista de movimientos y los asignamos
            //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

            return c;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        String[] columnas = {
                "id","banco","sucursal","dc","numerocuenta","saldoactual", "idcliente"
        };
        Cursor cursor = MiBD.getDB().query("cuentas", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Cuenta c = new Cuenta();
                c.setId(cursor.getInt(0));
                c.setBanco(cursor.getString(1));
                c.setSucursal(cursor.getString(2));
                c.setDc(cursor.getString(3));
                c.setNumeroCuenta(cursor.getString(4));
                c.setSaldoActual(cursor.getFloat(5));

                // Obtenemos el cliente y lo asignamos
                Cliente a = new Cliente();
                a.setId(cursor.getInt(6));
                a = (Cliente) MiBD.getInstance(null).getClienteDAO().search(a);
                c.setCliente(a);

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

                listaCuentas.add(c);

            } while(cursor.moveToNext());
        }
        return listaCuentas;
    }

    public ArrayList getCuentas(Cliente cliente) {
        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        String condicion = "idcliente=" + String.valueOf(cliente.getId());
        String[] columnas = {
                "id", "banco", "sucursal", "dc", "numerocuenta", "saldoactual", "idcliente"
        };
        Cursor cursor = MiBD.getDB().query("cuentas", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Cuenta c = new Cuenta();
                c.setId(cursor.getInt(0));
                c.setBanco(cursor.getString(1));
                c.setSucursal(cursor.getString(2));
                c.setDc(cursor.getString(3));
                c.setNumeroCuenta(cursor.getString(4));
                c.setSaldoActual(cursor.getFloat(5));

                c.setCliente(cliente);

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

                listaCuentas.add(c);

            } while (cursor.moveToNext());
        }
        return listaCuentas;
    }

}
