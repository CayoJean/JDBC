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
import servicios.ClienteServicio;


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



            

            // ✏️  Actividad Cliente Servicio

            // 1. Crear una instancia de ClienteServicio
            ClienteServicio clienteServicio = new ClienteServicio();

            // 2. Intentar crear un nuevo cliente con datos válidos
            System.out.println("Intentando crear cliente con datos válidos...");
            clienteServicio.crearNuevoCliente(1003, "Jardinería La Primavera", "Ana", "Rodríguez",
                    "654321987", "123123123", "Sevilla", "Andalucía", "España", "41001", 3, 1500.75);
            System.out.println("Cliente creado correctamente.");

            // 3. Intentar crear un cliente con datos inválidos (sin nombre de contacto)
            System.out.println("Intentando crear cliente con nombre de contacto vacío...");
            clienteServicio.crearNuevoCliente(1004, "Vivero del Sur", "", "García",
                    "987654321", "123456789", "Madrid", "Madrid", "España", "28001", 4, 2200.00);
            System.out.println("Este mensaje no debería mostrarse, ya que debería lanzarse una excepción.");


        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}

