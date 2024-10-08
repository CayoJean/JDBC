package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) {
        DAO dao = new DAO() {}; // Instancia anónima de la clase abstracta DAO
        try {
            // Estableciendo la conexión a la base de datos
            dao.connectarDataBase();
    
            // Llamada al método buscarClientes
            buscarClientes(dao.conexion);
    
            // Llamada a getProductosParaReponer con un valor específico
            int puntoReponer = 5; // Cambia este valor según tus necesidades
            getProductosParaReponer(dao.conexion, puntoReponer);
    
            // Llamada al método getProductosGama para gama Aromáticas
            getProductosGama(dao.conexion, 3); // Asegúrate de usar el ID de gama correcto
    
            // Llamada a getPedidosPorCliente con un ID de cliente específico
            int idCliente = 1; // Cambia este valor según el cliente que deseas consultar
            getPedidosPorCliente(dao.conexion, idCliente);
    
            // Llamada a getPedidosPorEstado con un estado específico
            getPedidosPorEstado(dao.conexion, "Pendiente"); // Asegúrate de usar el estado correcto
    
            // Llamada al método para listar productos por gama
            getProductosPorGamaList(dao.conexion);
    
            // Llamada al método para listar productos no comprados
            getProductosNoComprados(dao.conexion);
    
            // Llamada al método para listar pedidos por producto específico
            getPedidosPorProducto(dao.conexion, "AR-001"); // Cambia el ID del producto según sea necesario
    
            // Llamada al método para listar pedidos entre dos fechas
            getPedidosPorFechas(dao.conexion, "2024-01-01", "2024-12-31"); // Cambia las fechas según sea necesario
    
            // Llamada al método para listar productos por proveedor
            getProductosPorProveedor(dao.conexion, "HiperGarden Tools"); // Cambia el código del proveedor según sea necesario
    
            // Llamada al método para listar pedidos completos por cliente
            getPedidosPorClienteCompleto(dao.conexion, idCliente); // Usa el ID del cliente existente
    
        } catch (Exception e) {
            System.out.println("Error durante la conexión: " + e.getMessage());
        } finally {
            try {
                // Cerrando la conexión a la base de datos
                dao.desconectarDataBase();
            } catch (Exception e) {
                System.out.println("Error al desconectar la base de datos: " + e.getMessage());
            }
        }
    }
    


    // INICIO DE CTIVIDAD DE Método para buscar clientes
    public static void buscarClientes(Connection conexion) {
        System.out.println();
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " " + apellido + " - " + telefono);
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void buscarClientePorCodigo(Connection conexion, int codigo) {
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = " + codigo;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Código: " + rs.getInt("codigo_cliente"));
                System.out.println("Nombre: " + rs.getString("nombre_contacto"));
                System.out.println("Apellido: " + rs.getString("apellido_contacto"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
            } else {
                System.out.println("No se encontró ningún cliente con el código: " + codigo);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void buscarClientesPorEmpleado(Connection conexion, int codigoEmpleado) {
        String sql = "SELECT c.nombre_contacto, c.apellido_contacto, c.telefono "
                + "FROM cliente c "
                + "JOIN empleado e ON c.id_empleado = e.id_empleado "
                + "WHERE e.id_empleado = " + codigoEmpleado;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " " + apellido + " - " + telefono);
            }

            if (count == 0) {
                System.out.println("No se encontraron clientes asociados al empleado con código: " + codigoEmpleado);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // FIN DE BUSCAR CLIENTES







    // INICIO DE ✏️ Actividad Productos
    public static void getProductosParaReponer(Connection conexion, int ProductosReponer) {
        System.out.println();
        String sql = "SELECT id_producto, nombre, cantidad_en_stock FROM producto WHERE cantidad_en_stock > " + ProductosReponer;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                String id = rs.getString("id_producto");
                String nombre = rs.getString("nombre");
                String stock = rs.getString("cantidad_en_stock");
                count++;
                System.out.println(count + " - ID: " + id + "- Producto: " + nombre + " -  Stock: " + stock);
            }

            if (count == 0) {
                System.out.println("No se encontraron productos con un mayor stock que: " + ProductosReponer);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }


    public static void getProductosGama(Connection conexion, int codigoGama) {
        System.out.println();
        String sql = "SELECT p.codigo_producto, p.nombre, g.id_gama, g.gama " 
        + "FROM producto p " 
        + "JOIN gama_producto g ON g.id_gama = p.id_gama "
        + "WHERE g.id_gama = " + codigoGama;
        

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                
                String CódigoP = rs.getString("codigo_producto");
                String NombreP = rs.getString("nombre");
                int CódigoG = rs.getInt("id_gama");
                String NombreG = rs.getString("gama");
                count++;
                System.out.println(count + " - Código: " + CódigoP + "- Producto: " + NombreP + " -  Código Gama: " + CódigoG
                                    + " -  Gama: " + NombreG);
            }

            if (count == 0) {
                System.out.println("No se encontró ningún producto con el gama: " + codigoGama);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // FIN DE ✏️ Actividad Productos











    // INICIO DE ✏️ Actividad Pedidos

    // Método para obtener pedidos de un cliente por su ID
    public static void getPedidosPorCliente(Connection conexion, int idCliente) {
        System.out.println();
        String sql = "SELECT id_pedido, fecha_pedido, estado FROM pedido WHERE id_cliente = " 
                + idCliente;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                String fechaPedido = rs.getString("fecha_pedido");
                String Estado = rs.getString("estado");
                count++;
                System.out.println(count + " - Pedido Nº: " + numeroPedido + ", Fecha: " + fechaPedido + ", Total: " + Estado);
            }

            if (count == 0) {
                System.out.println("No se encontraron pedidos para el cliente con ID: " + idCliente);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // Método para obtener pedidos según su estado
    public static void getPedidosPorEstado(Connection conexion, String estado) {
        System.out.println();
        String sql = "SELECT id_pedido, id_cliente, fecha_pedido, fecha_esperada FROM pedido WHERE estado = ?"; // Usar parámetro en lugar de concatenar
    
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql); // Usar PreparedStatement
            pstmt.setString(1, estado); // Asignar el estado como parámetro
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
    
            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                int codigoCliente = rs.getInt("id_cliente");
                String fechaPedido = rs.getString("fecha_pedido");
                String fechaEsperada = rs.getString("fecha_esperada");
                count++;
                System.out.println(count + " - Pedido Nº: " + numeroPedido + ", Cliente ID: " + codigoCliente + ", Fecha del pedido: " + fechaPedido + ", Fecha esperada: " + fechaEsperada);
            }
    
            if (count == 0) {
                System.out.println("No se encontraron pedidos con el estado: " + estado);
            }
    
            rs.close();
            pstmt.close(); // Cambiado a pstmt
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // FIN DE ✏️ Actividad Pedidos









    // ✏️  Actividad: Ejercicios Complementarios

    // 1. Listar la cantidad de productos y la gama a la que pertenecen

    public static void getProductosPorGamaList(Connection conexion) {
        System.out.println();
        System.out.println("Ejercicios Complementarios");
        System.out.println();
        String sql = "SELECT COUNT(*) AS cantidad, g.gama AS gama " +
                        "FROM producto p " +
                        "JOIN gama_producto g ON p.id_gama = g.id_gama " +
                        "GROUP BY g.gama";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                String gama = rs.getString("gama");
                System.out.println("Cantidad " + cantidad + " - Gama " + gama);
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // -------------------------------------------------


    // 2. Listar los productos que nunca han sido comprados
    public static void getProductosNoComprados(Connection conexion) {
        System.out.println();
        String sql = "SELECT p.id_producto, p.nombre " +
                        "FROM producto p " +
                        "LEFT JOIN detalle_pedido dp ON p.id_producto = dp.id_producto " +
                        "WHERE dp.id_producto IS NULL";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
                String idProducto = rs.getString("id_producto");
                String nombre = rs.getString("nombre");
                System.out.println("Producto No Comprado: ID = " + idProducto + ", Nombre = " + nombre);
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // -------------------------------------------------


    // 3. Listar pedidos que tienen un producto específico
    public static void getPedidosPorProducto(Connection conexion, String idProducto) {
        System.out.println();
        String sql = "SELECT p.id_pedido, p.fecha_pedido, p.estado " +
                        "FROM pedido p " +
                        "JOIN detalle_pedido dp ON p.id_pedido = dp.id_pedido " +
                        "WHERE dp.id_producto = '" + idProducto + "'";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                String fechaPedido = rs.getString("fecha_pedido");
                String estado = rs.getString("estado");
                System.out.println("Pedido Nº: " + numeroPedido + ", Fecha: " + fechaPedido + ", Estado: " + estado);
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // -------------------------------------------------



    // 4. Buscar todos los pedidos realizados entre dos fechas
    public static void getPedidosPorFechas(Connection conexion, String desde, String hasta) {
        System.out.println();
        String sql = "SELECT id_pedido, id_cliente, fecha_pedido " +
                        "FROM pedido " +
                        "WHERE fecha_pedido BETWEEN '" + desde + "' AND '" + hasta + "'";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                int codigoCliente = rs.getInt("id_cliente");
                String fechaPedido = rs.getString("fecha_pedido");
                System.out.println("Pedido Nº: " + numeroPedido + ", Cliente ID: " + codigoCliente + ", Fecha: " + fechaPedido);
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // -------------------------------------------------
    
    
    // 5. Listar todos los productos de un proveedor específico
    public static void getProductosPorProveedor(Connection conexion, String codigoProveedor) {
        System.out.println();
    
        String sql = "SELECT id_producto, nombre "
                    + "FROM producto "
                    + "WHERE proveedor = '" + codigoProveedor + "'";
    
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String nombreProducto = rs.getString("nombre");
                System.out.println("Producto ID: " + idProducto + ", Nombre: " + nombreProducto);
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    

    // -------------------------------------------------



    // 6. Listar todos los pedidos completos de un cliente específico
    public static void getPedidosPorClienteCompleto(Connection conexion, int idCliente) {
        System.out.println();
        String sql = "SELECT p.id_pedido, p.fecha_pedido, p.estado, dp.id_producto, dp.cantidad " +
                        "FROM pedido p " +
                        "JOIN detalle_pedido dp ON p.id_pedido = dp.id_pedido " +
                        "WHERE p.id_cliente = " + idCliente;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
                int numeroPedido = rs.getInt("id_pedido");
                String fechaPedido = rs.getString("fecha_pedido");
                String estado = rs.getString("estado");
                String idProducto = rs.getString("id_producto");
                int cantidad = rs.getInt("cantidad");
                System.out.println("Pedido Nº: " + numeroPedido + ", Fecha: " + fechaPedido + ", Estado: " + estado + ", Producto: ID = " + idProducto + ", Cantidad = " + cantidad);
            }
    
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    // -------------------------------------------------
    // FIN DE EJERCICIOS COMPLEMENTARIO
}



