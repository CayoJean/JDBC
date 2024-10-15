package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null; // Cambiado a PreparedStatement
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DATABASE = "estancias_exterior"; 
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection conectarDataBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE 
                        + "?useSSL=false&serverTimezone=UTC"; 
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            return conexion; 
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
            throw e;
        }
    }

    public void desconectarDataBase() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close(); // Cierra PreparedStatement
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }

    protected void insertarModificarEliminarDataBase(String sql) throws Exception {
        try {
            conectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.executeUpdate(); // No se necesita statement para esta operación
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la operación: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
    }

    protected ResultSet consultarDataBase(String sql) throws Exception {
        try {
            conectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            throw e;
        }
        return resultSet;
    }
}
