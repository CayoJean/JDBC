package entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Casa {
    private int idCasa;
    private String calle;
    private int numero;
    private String codigoPostal;
    private String ciudad;
    private String pais;
    private Date fechaDesde;
    private Date fechaHasta;
    private int tiempoMinimo;
    private int tiempoMaximo;
    private BigDecimal precioHabitacion;
    private String tipoVivienda;
    private String comentarios; // Añade este atributo

    // Getters y Setters
    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(int tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public BigDecimal getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(BigDecimal precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getComentarios() { // Método getter para comentarios
        return comentarios;
    }

    public void setComentarios(String comentarios) { // Método setter para comentarios
        this.comentarios = comentarios;
    }

    // Método getId() agregado
    public int getId() {
        return idCasa; // Retorna el ID de la casa
    }

    @Override
    public String toString() {
        return "Casa ID: " + idCasa + "\n" +
                "Calle: " + calle + ", Número: " + numero + "\n" +
                "Código Postal: " + codigoPostal + ", Ciudad: " + ciudad + "\n" +
                "Fecha Disponible Desde: " + fechaDesde + " Hasta: " + fechaHasta + "\n" +
                "Precio por Habitación: " + precioHabitacion + "\n" +
                "Tipo de Vivienda: " + tipoVivienda + "\n" +
                "Comentarios: " + comentarios + "\n" + // Agrega comentarios en toString
                "------------------------------------------------------";
    }
}

