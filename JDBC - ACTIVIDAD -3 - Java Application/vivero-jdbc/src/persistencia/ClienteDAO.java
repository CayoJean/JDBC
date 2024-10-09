package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Cliente;

public class ClienteDAO extends DAO {

    // Método para guardar un cliente en la base de datos
    public void guardarCliente(Cliente cliente) throws Exception {
        try {
            if (cliente == null) {
                throw new Exception("El cliente no puede ser nulo.");
            }

            String sql = "INSERT INTO Cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, " +
                    "telefono, fax, ciudad, region, pais, codigo_postal, id_empleado, limite_credito) " +
                    "VALUES (" + cliente.getCodigoCliente() + ", '" + cliente.getNombreCliente() + "', '" +
                    cliente.getNombreContacto() + "', '" + cliente.getApellidoContacto() + "', '" +
                    cliente.getTelefono() + "', '" + cliente.getFax() + "', '" + cliente.getCiudad() + "', '" +
                    cliente.getRegion() + "', '" + cliente.getPais() + "', '" + cliente.getCodigoPostal() + "', " +
                    cliente.getIdEmpleado() + ", " + cliente.getLimiteCredito() + ");";

            insertarModificarEliminarDataBase(sql); // Llama al método de la clase abstracta DAO
            System.out.println("Cliente guardado exitosamente.");

        } catch (Exception e) {
            throw e; // Lanza la excepción para manejarla externamente
        }
    }

    // Método para listar todos los clientes desde la base de datos
    public void listarTodosLosClientes() throws Exception {
        try {
            String sql = "SELECT id_cliente, nombre_contacto, apellido_contacto FROM cliente";
            consultarDataBase(sql); // Llama al método de la clase abstracta DAO

            Cliente cliente = null;
            List<Cliente> clientes = new ArrayList<>();

            // Procesa los resultados de la consulta
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                clientes.add(cliente); // Añade el cliente a la lista
            }

            // Imprime cada cliente de la lista
            for (Cliente clienteUnitario : clientes) {
                System.out.println(clienteUnitario.imprimirNyA());
            }

            System.out.println(""); // Imprime una línea vacía
            desconectarDataBase(); // Cierra la conexión a la base de datos

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola
            desconectarDataBase(); // Asegura la desconexión de la base de datos
            throw e; // Lanza la excepción para manejarla externamente
        }
    }

    // ✏️ Actividad Entidad Producto

    // Método para eliminar un cliente por su código
    public void eliminarClientePorCodigo(int codigoCliente) throws Exception {
        String sql = "DELETE FROM cliente WHERE codigo_cliente = " + codigoCliente + ";";
        try {
            insertarModificarEliminarDataBase(sql); // Llama al método de la clase abstracta DAO
            System.out.println("Cliente con código " + codigoCliente + " eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
            throw e; // Lanza la excepción para manejarla externamente
        }
    }

    // ✏️ Actividad Consultas de clientes

    // Método para buscar un cliente por su código
    public void buscarClientePorCodigo(int codigoCliente) throws Exception {
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = " + codigoCliente + ";";
        try {
            ResultSet resultSet = consultarDataBase(sql); // Llama al método de la clase abstracta DAO

            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));

                // Imprime la información del cliente usando toString
                System.out.println(cliente.toString());
            } else {
                System.out.println("No se encontró un cliente con el código " + codigoCliente);
            }

            desconectarDataBase(); // Cierra la conexión a la base de datos

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error en la consola
            desconectarDataBase(); // Asegura la desconexión de la base de datos
            throw e; // Lanza la excepción para manejarla externamente
        }
    }






    // EJERCICIOS COMPLEMENTARIO


    /*
    // Eliminar un cliente específico por id_cliente
    public void eliminarClientePorCodigo(int idCliente) throws Exception {
        String sql = "DELETE FROM Cliente WHERE id_cliente = " + idCliente + ";";
        insertarModificarEliminarDataBase(sql);
        System.out.println("Cliente eliminado exitosamente.");
    }*/

    // Listar clientes vinculados a un id_empleado
    public void listarClientesPorEmpleado(int idEmpleado) throws Exception {
        String sql = "SELECT * FROM Cliente WHERE id_empleado = " + idEmpleado + ";";
        consultarDataBase(sql);

        List<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            // Agrega otros atributos del cliente según sea necesario
            clientes.add(cliente);
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    // Incrementar el límite de crédito en un 15%
    public void incrementarLimiteCredito() throws Exception {
        String sql = "UPDATE Cliente SET limite_credito = limite_credito * 1.15;";
        insertarModificarEliminarDataBase(sql);
        System.out.println("Límite de crédito incrementado en un 15%.");
    }

}
