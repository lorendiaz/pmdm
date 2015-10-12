package com.fpmislata.practicas.clase4.bd;

import android.content.Context;

import java.util.ArrayList;

import com.fpmislata.practicas.clase4.dao.ClienteDAO;
import com.fpmislata.practicas.clase4.dao.CuentaDAO;
import com.fpmislata.practicas.clase4.dao.MovimientoDAO;
import com.fpmislata.practicas.clase4.pojo.Cliente;


/**
 * Created by loren on 08/10/15.
 */
public class MiBancoOperacional {

    private ClienteDAO clienteDAO;
    private CuentaDAO cuentaDAO;
    private MovimientoDAO movimientoDAO;

    public MiBancoOperacional(Context context){
        MiBD miBD = MiBD.getInstance(context);
        clienteDAO = new ClienteDAO();
        cuentaDAO = new CuentaDAO();
        movimientoDAO = new MovimientoDAO();
    }

    public ArrayList<Cliente> listaClientes(){
        return clienteDAO.getAll();
    }
}
