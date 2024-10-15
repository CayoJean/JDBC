package servicios;

import entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {
    // Método para listar clientes
    public List<Cliente> listarClientesConEstancias() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT id_cliente, nombre, email FROM clientes"; // Ajusta según tu estructura de base de datos

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return clientes;
    }

    // Método para obtener conexión a la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/estancias_exterior?useSSL=false&serverTimezone=UTC",
                "root",
                "root");
    }
}
