package entidades;

public class Cliente {

    private int id;
    private String nombre;
    private String apellido;

    // Constructor sin parámetros agregado
    public Cliente() {
        // Puede estar vacío o con lógica por defecto
    }

    // Constructor con parámetros (si necesitas uno)
    public Cliente(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getter y Setter para "id"
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para "nombre"
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para "apellido"
    public String getApellido() {
        return apellido;
    }

    // Método setApellido agregado
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
