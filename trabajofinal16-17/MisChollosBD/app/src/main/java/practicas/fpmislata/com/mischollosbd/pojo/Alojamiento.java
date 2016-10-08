package practicas.fpmislata.com.mischollosbd.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LOREN on 05/08/2016.
 */

public class Alojamiento implements Serializable {

    private int id;
    private Chollo chollo;
    // Informacion resumida
    private float precioPersona;
    private String informacionResumida;
    // Informacion
    private String siIncluye;
    private String noIncluye;
    private String notasImportantes;
    private String viaje;
    private String descuentos;
    private String condiciones;
    private String tasas;
    private String localidad;
    // Alojamiento
    private String descripcionGeneral;
    private String serviciosAlojamiento;
    private String serviciosHabitacion;
    private String horariosHabitacion;
    private String horariosCondiciones;
    // Valoracion
    private float nota;
    private int estrellas;
    private int numeroEncuestas;
    private float habitaciones;
    private float servicios;
    private float limpieza;
    private float comida;
    private float personal;
    private float calidadPrecio;
    private List<Valoracion> listaValoraciones;

    public Alojamiento() {
        super();
        this.listaValoraciones = new ArrayList<Valoracion>();
    }

    public Alojamiento(int id, Chollo chollo, float precioPersona, String informacionResumida,
                       String siIncluye, String noIncluye, String notasImportantes, String viaje,
                       String descuentos, String condiciones, String localidad, String descripcionGeneral,
                       String serviciosAlojamiento, String serviciosHabitacion, String horariosHabitacion,
                       String horariosCondiciones, float nota, int estrellas, int numeroEncuestas,
                       float habitaciones, float servicios, float limpieza, float comida, float personal,
                       float calidadPrecio) {
        this.id = id;
        this.chollo = chollo;
        this.precioPersona = precioPersona;
        this.informacionResumida = informacionResumida;
        this.siIncluye = siIncluye;
        this.noIncluye = noIncluye;
        this.notasImportantes = notasImportantes;
        this.viaje = viaje;
        this.descuentos = descuentos;
        this.condiciones = condiciones;
        this.localidad = localidad;
        this.descripcionGeneral = descripcionGeneral;
        this.serviciosAlojamiento = serviciosAlojamiento;
        this.serviciosHabitacion = serviciosHabitacion;
        this.horariosHabitacion = horariosHabitacion;
        this.horariosCondiciones = horariosCondiciones;
        this.nota = nota;
        this.estrellas = estrellas;
        this.numeroEncuestas = numeroEncuestas;
        this.habitaciones = habitaciones;
        this.servicios = servicios;
        this.limpieza = limpieza;
        this.comida = comida;
        this.personal = personal;
        this.calidadPrecio = calidadPrecio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chollo getChollo() {
        return chollo;
    }

    public void setChollo(Chollo chollo) {
        this.chollo = chollo;
    }

    public float getPrecioPersona() {
        return precioPersona;
    }

    public void setPrecioPersona(float precioPersona) {
        this.precioPersona = precioPersona;
    }

    public String getInformacionResumida() {
        return informacionResumida;
    }

    public void setInformacionResumida(String informacionResumida) {
        this.informacionResumida = informacionResumida;
    }

    public String getSiIncluye() {
        return siIncluye;
    }

    public void setSiIncluye(String siIncluye) {
        this.siIncluye = siIncluye;
    }

    public String getNoIncluye() {
        return noIncluye;
    }

    public void setNoIncluye(String noIncluye) {
        this.noIncluye = noIncluye;
    }

    public String getNotasImportantes() {
        return notasImportantes;
    }

    public void setNotasImportantes(String notasImportantes) {
        this.notasImportantes = notasImportantes;
    }

    public String getViaje() {
        return viaje;
    }

    public void setViaje(String viaje) {
        this.viaje = viaje;
    }

    public String getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(String descuentos) {
        this.descuentos = descuentos;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public String getServiciosAlojamiento() {
        return serviciosAlojamiento;
    }

    public void setServiciosAlojamiento(String serviciosAlojamiento) {
        this.serviciosAlojamiento = serviciosAlojamiento;
    }

    public String getServiciosHabitacion() {
        return serviciosHabitacion;
    }

    public void setServiciosHabitacion(String serviciosHabitacion) {
        this.serviciosHabitacion = serviciosHabitacion;
    }

    public String getHorariosHabitacion() {
        return horariosHabitacion;
    }

    public void setHorariosHabitacion(String horariosHabitacion) {
        this.horariosHabitacion = horariosHabitacion;
    }

    public String getHorariosCondiciones() {
        return horariosCondiciones;
    }

    public void setHorariosCondiciones(String horariosCondiciones) {
        this.horariosCondiciones = horariosCondiciones;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getNumeroEncuestas() {
        return numeroEncuestas;
    }

    public void setNumeroEncuestas(int numeroEncuestas) {
        this.numeroEncuestas = numeroEncuestas;
    }

    public float getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(float habitaciones) {
        this.habitaciones = habitaciones;
    }

    public float getServicios() {
        return servicios;
    }

    public void setServicios(float servicios) {
        this.servicios = servicios;
    }

    public float getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(float limpieza) {
        this.limpieza = limpieza;
    }

    public float getComida() {
        return comida;
    }

    public void setComida(float comida) {
        this.comida = comida;
    }

    public float getPersonal() {
        return personal;
    }

    public void setPersonal(float personal) {
        this.personal = personal;
    }

    public float getCalidadPrecio() {
        return calidadPrecio;
    }

    public void setCalidadPrecio(float calidadPrecio) {
        this.calidadPrecio = calidadPrecio;
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "calidadPrecio=" + calidadPrecio +
                ", id=" + id +
                ", chollo=" + chollo +
                ", precioPersona=" + precioPersona +
                ", informacionResumida='" + informacionResumida + '\'' +
                ", siIncluye='" + siIncluye + '\'' +
                ", noIncluye='" + noIncluye + '\'' +
                ", notasImportantes='" + notasImportantes + '\'' +
                ", viaje='" + viaje + '\'' +
                ", descuentos='" + descuentos + '\'' +
                ", condiciones='" + condiciones + '\'' +
                ", descripcionGeneral='" + descripcionGeneral + '\'' +
                ", serviciosAlojamiento='" + serviciosAlojamiento + '\'' +
                ", serviciosHabitacion='" + serviciosHabitacion + '\'' +
                ", horariosHabitacion='" + horariosHabitacion + '\'' +
                ", horariosCondiciones='" + horariosCondiciones + '\'' +
                ", nota=" + nota +
                ", estrellas=" + estrellas +
                ", numeroEncuestas=" + numeroEncuestas +
                ", habitaciones=" + habitaciones +
                ", servicios=" + servicios +
                ", limpieza=" + limpieza +
                ", comida=" + comida +
                ", personal=" + personal +
                ", localidad=" + localidad +
                '}';
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public List<Valoracion> getListaValoraciones() {
        return listaValoraciones;
    }

    public void setListaValoraciones(List<Valoracion> listaValoraciones) {
        this.listaValoraciones = listaValoraciones;
    }
}
