package com.fpmislata.practicas.clase4.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fpmislata.practicas.clase4.R;
import com.fpmislata.practicas.clase4.pojo.Cliente;

import java.util.ArrayList;

/**
 * Created by loren on 08/10/15.
 */
public class AdaptadorClientes extends ArrayAdapter<Cliente> {

    private ArrayList<Cliente> listaClientes;

    public AdaptadorClientes(Context context, ArrayList<Cliente> lista){
        super(context, R.layout.elemento_fila,lista);
        listaClientes = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());

        // Infla la fila
        View fila = inflater.inflate(R.layout.elemento_fila,null);

        // Asigna a cada elemento de la fila su valor
        TextView tv_nif = (TextView)fila.findViewById(R.id.tv_nif);
        tv_nif.setText(listaClientes.get(position).getNif());

        TextView lblnombre = (TextView)fila.findViewById(R.id.tv_nombre);
        lblnombre.setText(listaClientes.get(position).getNombre());

        TextView lblapellidos = (TextView)fila.findViewById(R.id.tv_apellidos);
        lblapellidos.setText(listaClientes.get(position).getApellidos());


        return fila;
    }
}
