package entidades;
import java.sql.Date;

public class Estancia {
    private int idEstancia;
    private int idCasa;
    private int idCliente;
    private Date fechaInicio;
    private Date fechaFin;

    // Constructor que acepta todos los parámetros
    public Estancia(int idEstancia, int idCasa, int idCliente, Date fechaInicio, Date fechaFin) {
        this.idEstancia = idEstancia;
        this.idCasa = idCasa;
        this.idCliente = idCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Métodos getters y setters
    public int getIdEstancia() {
        return idEstancia;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    @Override
    public String toString() {
        return "Estancia{" +
                "idEstancia=" + idEstancia +
                ", idCasa=" + idCasa +
                ", idCliente=" + idCliente +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
