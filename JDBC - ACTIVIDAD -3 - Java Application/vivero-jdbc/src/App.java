/*public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
*/


/* 
// ES PARA AGREGAR CLIENTES A LA BASE DE DATOS
import persistencia.ClienteDAO;
import entidades.Cliente;

public class App {
    public static void main(String[] args) {
        try {
            // 1. Crear una instancia de ClienteDAO
            ClienteDAO clienteDAO = new ClienteDAO();

            // 2. Insertar dos registros en la base de datos
            Cliente cliente1 = new Cliente(1001, "Jardinería Flores", "Carlos", "Gómez", "123456789", "987654321", "Barcelona", "Cataluña", "España", "08001", 1, 1200.50);
            Cliente cliente2 = new Cliente(1002, "Viveros del Norte", "Laura", "Martínez", "123123123", "321321321", "Valencia", "Valencia", "España", "46001", 2, 1800.75);

            clienteDAO.guardarCliente(cliente1);
            clienteDAO.guardarCliente(cliente2);

            // 3. Invocar el método listarTodosLosClientes
            clienteDAO.listarTodosLosClientes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}   // FIN PARA AGREGAR CLIENTES A LA BASE DE DATOS
*/




import persistencia.ClienteDAO;


public class App {
    public static void main(String[] args) {
        try {
            // 1. Crear una instancia de ClienteDAO
            ClienteDAO clienteDAO = new ClienteDAO();

            // 2. Insertar dos registros en la base de datos
            // Cliente cliente1 = new Cliente(1001, "Jardinería Flores", "Carlos", "Gómez", "123456789", "987654321", "Barcelona", "Cataluña", "España", "08001", 1, 1200.50);
            // Cliente cliente2 = new Cliente(1002, "Viveros del Norte", "Laura", "Martínez", "123123123", "321321321", "Valencia", "Valencia", "España", "46001", 2, 1800.75);

            // clienteDAO.guardarCliente(cliente1);
            // clienteDAO.guardarCliente(cliente2);

            // 3. Invocar el método listarTodosLosClientes
            clienteDAO.listarTodosLosClientes();

            // 4. Eliminar un cliente
            int idClienteAEliminar = 1002; // Cambia esto por el código del cliente que quieres eliminar
            clienteDAO.eliminarClientePorCodigo(idClienteAEliminar);

            // 5. Listar nuevamente para verificar la eliminación
            clienteDAO.listarTodosLosClientes();

            // 6. Buscar un cliente por su código
            int codigoClienteABuscar = 1001; // Cambia esto por el código del cliente que deseas buscar
            System.out.println("Buscando cliente con código: " + codigoClienteABuscar);
            clienteDAO.buscarClientePorCodigo(codigoClienteABuscar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
