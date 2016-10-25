package practicas.fpmislata.com.mischollosbd.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by loren on 05/08/16.
 */
public class Valoracion implements Serializable {

    private int id;
    private Usuario usuario;
    private Alojamiento alojamiento;
    private Long fecha;
    private float nota;
    private int estrellas;
    private float habitaciones;
    private float servicios;
    private float limpieza;
    private float comida;
    private float personal;
    private float calidadPrecio;
    private String loMejor;
    private String loPeor;

    public Valoracion() {
        super();
    }

    public Valoracion(int id, Usuario usuario, Alojamiento alojamiento, Long fecha, float nota,
                      int estrellas, float habitaciones, float servicios, float limpieza,
                      float comida, float personal, float calidadPrecio, String loMejor, String loPeor) {
        this.id = id;
        this.usuario = usuario;
        this.alojamiento = alojamiento;
        this.fecha = fecha;
        this.nota = nota;
        this.estrellas = estrellas;
        this.habitaciones = habitaciones;
        this.servicios = servicios;
        this.limpieza = limpieza;
        this.comida = comida;
        this.personal = personal;
        this.calidadPrecio = calidadPrecio;
        this.loMejor = loMejor;
        this.loPeor = loPeor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getLoMejor() {
        return loMejor;
    }

    public void setLoMejor(String loMejor) {
        this.loMejor = loMejor;
    }

    public String getLoPeor() {
        return loPeor;
    }

    public void setLoPeor(String loPeor) {
        this.loPeor = loPeor;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
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
        return "Valoracion{" +
                "id=" + id +
                ", usuario=" + usuario.getId() +
                ", alojamiento=" + alojamiento.getId() +
                ", fecha=" + getFormatDate(fecha) +
                ", nota=" + nota +
                ", estrellas=" + estrellas +
                ", habitaciones=" + habitaciones +
                ", servicios=" + servicios +
                ", limpieza=" + limpieza +
                ", comida=" + comida +
                ", personal=" + personal +
                ", calidadPrecio=" + calidadPrecio +
                ", loMejor='" + loMejor + '\'' +
                ", loPeor='" + loPeor + '\'' +
                '}';
    }

    private String getFormatDate(Long fecha){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateString = formatter.format(fecha);
        return formattedDateString;
    }
}
