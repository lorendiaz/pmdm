package com.fpmislata.practicas.aplicacion42;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int operacion;
    private LatLng fpMislata = new LatLng(39.481768, -0.421316);
    private LatLng fpCheste = new LatLng(39.476488, -0.648207);
    private LatLng hamburgueseria = new LatLng(39.478992, -0.426408);

    private PolylineOptions polilinea = new PolylineOptions()
            .add(fpMislata)
            .add(new LatLng(39.482039, -0.422245))
            .add(new LatLng(39.481244, -0.422760))
            .add(new LatLng(39.478858, -0.424340))
            .add(new LatLng(39.478352, -0.424717))
            .add(new LatLng(39.478418, -0.425030))
            .add(new LatLng(39.478762, -0.425824))
            .add(new LatLng(39.479084, -0.426598))
            .add(new LatLng(39.479337, -0.427238))
            .add(new LatLng(39.479367, -0.427436))
            .add(new LatLng(39.479287, -0.427600))
            .add(new LatLng(39.479145, -0.427570))
            .add(new LatLng(39.479061, -0.427397))
            .add(new LatLng(39.479095, -0.427119))
            .add(new LatLng(39.479080, -0.426925))
            .add(new LatLng(39.479000, -0.426538))
            .add(new LatLng(39.478808, -0.426067))
            .add(new LatLng(39.478609, -0.425992))
            .add(new LatLng(39.478544, -0.425843))
            .add(new LatLng(39.478406, -0.425858));

    private PolygonOptions poligono = new PolygonOptions()
            .add(new LatLng(39.482094, -0.421222),
                 new LatLng(39.481636, -0.421953),
                 new LatLng(39.481357, -0.421599),
                 new LatLng(39.481877, -0.420799),
                 new LatLng(39.482094, -0.421222))
            .strokeColor(Color.RED)
            .fillColor(Color.BLUE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        operacion = extras.getInt("operacion");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Comprobamos que operacion vamos a hacer con el mapa
        if (operacion==0){
            // No hacemos nada ya que solo vamos a realizar una apertura general del mapa
        }else if(operacion==1){
            // Nos situamos en la ubicacion actual
            ubicacionActual();
        }else if(operacion==2){
            setMarker(fpMislata,"CIPFP Mislata","Se encuentra en Mislata");
            setMarker(fpCheste,"CIPFP Cheste","Se encuentra en Cheste");
        }else if(operacion==3){
            setMarker(fpMislata,"CIPFP Mislata","Se encuentra en Mislata");
            setMarker(hamburgueseria,"McDonals","Se encuentra en Mislata");
            setPolilyne(polilinea);
        }else if(operacion==4){
            setPoligono(poligono);
        }
    }

    // Nos posicionamos en la ubicacion actual
    private void ubicacionActual() {
        //Seteamos el tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Activamos la capa o layer MyLocation
        mMap.setMyLocationEnabled(true);
    }

    // Metodo que nos permite crear marcadores
    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); //Color del marcador
    }

    // Metodo que añade la polilinea al mapa
    private void setPolilyne(PolylineOptions options){
        Polyline polyline = mMap.addPolyline(options);
    }

    // Metodo que añade el poligono al mapa
    private void setPoligono(PolygonOptions opts){
        Polygon poligono = mMap.addPolygon(opts);
    }

/*
        // Añadimos la marca del CIPFP Mislata y movemos la camara
        LatLng fpMislata = new LatLng(39.481768, -0.421316);
        // Creamos las opciones del marcador
        MarkerOptions mo = new MarkerOptions();
        // Añadimos la posicion
        mo.position(fpMislata);
        // Añadimos el titulo
        mo.title("Marca del CIPFP Mislata");
        // Los añadimos al mapa
        mMap.addMarker(mo);
        // Movemos la camara hacia las coordenadas indicadas. CameraUpdateFactory contiene
        // métodos que cambian la cámara de un mapa
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fpMislata,18));
        */

}
