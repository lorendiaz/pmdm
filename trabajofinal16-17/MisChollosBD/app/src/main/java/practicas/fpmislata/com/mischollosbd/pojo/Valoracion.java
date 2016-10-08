package practicas.fpmislata.com.mischollosbd.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by loren on 05/08/16.
 */
public class Valoracion implements Serializable {

    private int id;
    private Usuario usuario;
    private Date fecha;
    private float nota;
    private String loMejor;
    private String loPeor;

    public Valoracion() {
        super();
    }

    public Valoracion(int id, Usuario usuario, Date fecha, float nota, String loMejor, String loPeor) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.nota = nota;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    @Override
    public String toString() {
        return "Valoracion{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", fecha=" + fecha +
                ", nota=" + nota +
                ", loMejor='" + loMejor + '\'' +
                ", loPeor='" + loPeor + '\'' +
                '}';
    }
}
