package com.fpmislata.practicas.aplicacion20.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.fpmislata.practicas.aplicacion20.R;
import com.fpmislata.practicas.aplicacion20.adaptador.AdaptadorCorreos;
import com.fpmislata.practicas.aplicacion20.pojo.Correo;

import java.util.ArrayList;

/**
 * Created by loren on 10/10/15.
 */
public class Activity_Fragment_Lista extends Fragment {

    private ArrayList<Correo> datos = new ArrayList<Correo>();
    private ListView lstListado;
    private CorreosListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onCreateView");
        return inflater.inflate(R.layout.layout_fragment_lista, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onActivityCreated");
        super.onActivityCreated(state);

        lstListado = (ListView) getView().findViewById(R.id.LstListado);

        datos.add(new Correo("Persona 1", "Asunto del correo 1", "Texto del correo 1"));
        datos.add(new Correo("Persona 2", "Asunto del correo 2", "Texto del correo 2"));
        datos.add(new Correo("Persona 3", "Asunto del correo 3", "Texto del correo 3"));
        datos.add(new Correo("Persona 4", "Asunto del correo 4", "Texto del correo 4"));
        datos.add(new Correo("Persona 5", "Asunto del correo 5", "Texto del correo 5"));

        lstListado.setAdapter(new AdaptadorCorreos(this, datos));


        lstListado.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onCorreoSeleccionado(
                            (Correo) lstListado.getAdapter().getItem(pos));
                }
            }
        });
    }

    public void setCorreosListener(CorreosListener listener) {
        this.listener=listener;
    }

    @Override
    public void onAttach (Activity activity) {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onViewStateRestored (Bundle savedInstanceState) {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart () {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onStart");
        super.onStart();
    }

    @Override
    public void onResume () {
        super.onResume();
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onResume");
    }

    @Override
    public void onPause () {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onPause");
        super.onPause();
    }

    @Override
    public void onStop () {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView () {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy () {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach () {
        Log.v(Activity_Fragment_Lista.class.getSimpleName(), "onDetach");
        super.onDetach();
    }    
}