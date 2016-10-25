package practicas.fpmislata.com.mischollosbd.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loren on 05/08/16.
 */
public class Chollo implements Serializable {

    private int id;
    private String titulo;
    private int orden;
    private List<Alojamiento> listaAlojamientos;

    public Chollo() {
        super();
        this.listaAlojamientos = new ArrayList<Alojamiento>();
    }

    public Chollo(int id, String titulo, int orden) {
        this.id = id;
        this.titulo = titulo;
        this.orden = orden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    /*public List<Alojamiento> getListaAlojamientos() {
        return listaAlojamientos;
    }

    public void setListaAlojamientos(List<Alojamiento> listaAlojamientos) {
        this.listaAlojamientos = listaAlojamientos;
    }*/

    @Override
    public String toString() {
        return "Chollo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", orden=" + orden +
                '}';
    }
}
