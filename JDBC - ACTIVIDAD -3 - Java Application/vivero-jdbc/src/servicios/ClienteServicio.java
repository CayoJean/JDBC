package servicios;

import entidades.Cliente;
import persistencia.ClienteDAO;

public class ClienteServicio {
    private ClienteDAO cd;

    public ClienteServicio() {
        this.cd = new ClienteDAO();
    }

    public Cliente crearNuevoCliente(int codigoC, String nombre, String nombreContacto, String apellidoContacto,
            String telefono, String fax, String ciudad, String region, String pais, String codigoPostal,
            int idEmpleado, double limiteCredito) throws Exception {

        // Validaciones
        validacionesNyA(nombreContacto, apellidoContacto);

        // Crear instancia de Cliente con los datos recibidos
        Cliente cliente = new Cliente(codigoC, nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, region,
                pais, codigoPostal, idEmpleado, limiteCredito);

        // Llamar al método guardarCliente de ClienteDAO
        cd.guardarCliente(cliente);

        return cliente;
    }

    // Método para validar que el nombre y el apellido de contacto no sean nulos
    public void validacionesNyA(String nombreContacto, String apellidoContacto) throws Exception {
        if (nombreContacto == null || nombreContacto.trim().isEmpty()) {
            throw new Exception("El nombre del contacto no puede ser nulo o estar vacío.");
        }
        if (apellidoContacto == null || apellidoContacto.trim().isEmpty()) {
            throw new Exception("El apellido del contacto no puede ser nulo o estar vacío.");
        }
    }
}
