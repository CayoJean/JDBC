package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAO extends DAO {

    public void eliminarProductoPorCodigo(int idProducto) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conexion = conectarDataBase();
                PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
            throw e;
        }
    }




    
    // EJERCICIOS COMPLEMENTARIO

    // Modificar un registro específico por id_producto
    public void modificarProductoPorId(int idProducto, String nuevoNombre, double nuevoPrecio) throws Exception {
        String sql = "UPDATE producto SET nombre = '" + nuevoNombre + "', precio_venta = " + nuevoPrecio
                + " WHERE id_producto = " + idProducto + ";";
        insertarModificarEliminarDataBase(sql);
        System.out.println("Producto modificado exitosamente.");
    }

    // Listar productos con la menor cantidad_en_stock
    public void listarProductosConMenorStock() throws Exception {
        String sql = "SELECT * FROM producto ORDER BY cantidad_en_stock ASC LIMIT 1;";
        consultarDataBase(sql);

        while (resultSet.next()) {
            // Procesa los resultados como desees
            System.out.println("Producto con menor stock: " + resultSet.getString("nombre"));
        }
    }

    // Listar productos con el menor precio_venta
    public void listarProductosConMenorPrecio() throws Exception {
        String sql = "SELECT * FROM producto ORDER BY precio_venta ASC LIMIT 1;";
        consultarDataBase(sql);

        while (resultSet.next()) {
            // Procesa los resultados como desees
            System.out.println("Producto con menor precio: " + resultSet.getString("nombre"));
        }
    }

}
