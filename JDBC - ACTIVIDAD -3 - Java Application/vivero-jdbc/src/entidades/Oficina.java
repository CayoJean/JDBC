package entidades;

public class Oficina {
    private int idOficina;
    private String nombreOficina;
    private String direccion;

    // Constructor
    public Oficina(int idOficina, String nombreOficina, String direccion) {
        this.idOficina = idOficina;
        this.nombreOficina = nombreOficina;
        this.direccion = direccion;
    }

    // Getters
    public int getIdOficina() {
        return idOficina;
    }

    public String getNombreOficina() {
        return nombreOficina;  // Asegúrate de que este método exista
    }

    public String getDireccion() {
        return direccion;  // Asegúrate de que este método exista
    }

    // toString() para imprimir la información de la oficina
    @Override
    public String toString() {
        return "Oficina{" +
                "idOficina=" + idOficina +
                ", nombreOficina='" + nombreOficina + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}