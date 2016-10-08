package practicas.fpmislata.com.mischollosbd.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loren on 05/08/16.
 */
public class Usuario implements Serializable{

    private int id;
    private String nif;
    private String nombre;
    private String apellidos;
    private int edad;
    private String localidad;
    private String claveSeguridad;
    private String email;
    private List<Chollo> listaChollos;

    public Usuario() {
        super();
        this.listaChollos = new ArrayList<>();
    }

    public Usuario(int id, String nif, String nombre, String apellidos, int edad, String localidad, String claveSeguridad, String email) {
        this.id = id;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.localidad = localidad;
        this.claveSeguridad = claveSeguridad;
        this.email = email;
        this.listaChollos = new ArrayList<Chollo>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Chollo> getListaChollos() {
        return listaChollos;
    }

    public void setListaChollos(List<Chollo> listaChollos) {
        this.listaChollos = listaChollos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", localidad='" + localidad + '\'' +
                ", claveSeguridad='" + claveSeguridad + '\'' +
                '}';
    }
}
