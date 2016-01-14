package com.fpmislata.practicas.aplicacion41;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

// Implementa LocationListener ya que se va a utilizar la clase como un listener para recibir las
// actualizaciones del nombreProveedor de posicionamiento
public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final long TIEMPO_MIN = 10 * 1000; // 10 segundos
    private static final long DISTANCIA_MIN = 5; // 5 metros

    private static final String[] A = {"n/d", "preciso", "impreciso"};
    private static final String[] P = {"n/d", "bajo", "medio", "alto"};
    private static final String[] E = {"fuera de servicio",
            "temporalmente no disponible ", "disponible"};

    private LocationManager servicioLocalizacion;
    private String nombreProveedor;
    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salida = (TextView) findViewById(R.id.salida);

        // Obtenemos un objeto que nos proporciona acceso a los servicios de localizacion del sistema
        servicioLocalizacion = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Obtenemos todos los nombreProveedores de localizacion y los mostramos
        imprimePantalla("Proveedores de localización disponibles: \n ");
        List<String> nombreProveedores = servicioLocalizacion.getAllProviders();
        for (String nombreProveedor : nombreProveedores) {
            muestraProveedor(nombreProveedor);
        }

        // Ahora crearemos un objeto que nos permitirá indicar los criterios
        Criteria criterio = new Criteria();
        // Indica si se permite al nombreProveedor incurrir en costes monetarios
        criterio.setCostAllowed(false);
        // Indica si el nombreProveedor debe proporcionar información de altitud
        criterio.setAltitudeRequired(false);
        // Indica la precisión deseada para la latitud y longitud
        criterio.setAccuracy(Criteria.ACCURACY_FINE);

        // Obtenemos el mejor nombreProveedor que se adapte a los criterios dados
        nombreProveedor = servicioLocalizacion.getBestProvider(criterio, true);

        imprimePantalla("Mejor nombreProveedor: " + nombreProveedor + "\n");
        imprimePantalla("Comenzamos con la última localización conocida:");

        // Declaramos un objeto Location que nos almacenará la situación donde nos encontramos
        Location localizacion = null;



        // Comprobamos que tenemos los dos permisos definidos en el manifest
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED))
        {
            // Devuelve una ubicación que indica los datos de la última ubicación conocida
            // solución obtenida desde el nombreProveedor dado.
            localizacion = servicioLocalizacion.getLastKnownLocation(nombreProveedor);
        }

        // Si hemos obtenido una localizacion la muestra, sino
        // mostrara que es una localizacion desconocida
        muestraLocalizacion(localizacion);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Ahora suscribimos la actividad MainActivity al nombreProveedor de
        // localización, así conseguimos que a la actividad
        // le lleguen las actualizaciones del nombreProveedor de posicionamiento

        // Comprobamos que tenemos los dos permisos definidos en el manifest
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED))
        {
            servicioLocalizacion.requestLocationUpdates(nombreProveedor, TIEMPO_MIN, DISTANCIA_MIN, this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Comprobamos que tenemos los dos permisos definidos en el manifest
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
            (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED))
        {
            // Borra todas las actualizaciones pendientes de informar a MainActivity
            servicioLocalizacion.removeUpdates(this);
        }
    }

    // ****************************************
    // Métodos de la interfaz LocationListener
    // ****************************************

    public void onLocationChanged(Location location) {
        imprimePantalla("Nueva localización: ");
        muestraLocalizacion(location);
    }

    public void onProviderDisabled(String nombreProveedor) {
        imprimePantalla("Proveedor deshabilitado: " + nombreProveedor + "\n");
    }

    public void onProviderEnabled(String nombreProveedor) {
        imprimePantalla("Proveedor habilitado: " + nombreProveedor + "\n");
    }

    public void onStatusChanged(String nombreProveedor, int estado, Bundle extras) {
        imprimePantalla("Cambia estado nombreProveedor: " + nombreProveedor + ", estado="
                + E[Math.max(0, estado)] + ", extras=" + extras + "\n");
    }

    // ****************************************
    // Métodos para mostrar información
    // ****************************************

    private void imprimePantalla(String cadena) {
        salida.append(cadena + "\n");
    }

    private void muestraLocalizacion(Location localizacion) {
        if (localizacion == null)
            imprimePantalla("Localización desconocida\n");
        else
            imprimePantalla(localizacion.toString() + "\n");
    }

    private void muestraProveedor(String nombreProveedor) {
        // Obtenemos una referencia del provider mediante su nombre llamando
        // al método getProvider(nombre)
        LocationProvider info = servicioLocalizacion.getProvider(nombreProveedor);
        // A partir de aqui podemos obtener informacion del mismo
        imprimePantalla("LocationProvider[ " + "getName=" + info.getName()
                + ", isProviderEnabled="
                + servicioLocalizacion.isProviderEnabled(nombreProveedor) + ", getAccuracy="
                + A[Math.max(0, info.getAccuracy())] + ", getPowerRequirement="
                + P[Math.max(0, info.getPowerRequirement())]
                + ", hasMonetaryCost=" + info.hasMonetaryCost()
                + ", requiresCell=" + info.requiresCell()
                + ", requiresNetwork=" + info.requiresNetwork()
                + ", requiresSatellite=" + info.requiresSatellite()
                + ", supportsAltitude=" + info.supportsAltitude()
                + ", supportsBearing=" + info.supportsBearing()
                + ", supportsSpeed=" + info.supportsSpeed() + " ]\n");
    }
}
