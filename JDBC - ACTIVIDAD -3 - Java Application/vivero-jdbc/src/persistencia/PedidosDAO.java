package persistencia;

public class PedidosDAO extends DAO {
    
    // Listar todos los pedidos con su detalle de un cliente específico
    public void listarPedidosPorCliente(int idCliente) throws Exception {
        String sql = "SELECT * FROM pedido WHERE id_cliente = " + idCliente + ";";
        consultarDataBase(sql);
        
        while (resultSet.next()) {
            // Procesa los resultados
            System.out.println("Pedido de cliente " + idCliente + ": " + resultSet.getString("detalles"));
        }
        desconectarDataBase(); // Asegúrate de desconectar
    }

    // Listar pedidos en un estado específico
    public void listarPedidosPorEstado(String estado) throws Exception {
        String sql = "SELECT * FROM pedido WHERE estado = '" + estado + "';";
        consultarDataBase(sql);
        
        while (resultSet.next()) {
            // Procesa los resultados
            System.out.println("Pedido en estado " + estado + ": " + resultSet.getString("detalles"));
        }
        desconectarDataBase(); // Asegúrate de desconectar
    }

    // Listar pedidos con un producto específico
    public void listarPedidosPorProducto(int idProducto) throws Exception {
        String sql = "SELECT * FROM pedido WHERE id_producto = " + idProducto + ";";
        consultarDataBase(sql);
        
        while (resultSet.next()) {
            // Procesa los resultados
            System.out.println("Pedido con producto " + idProducto + ": " + resultSet.getString("detalles"));
        }
        desconectarDataBase(); // Asegúrate de desconectar
    }
}
