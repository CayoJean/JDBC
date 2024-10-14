package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "vivero";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected Connection conectarDataBase() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false&serverTimezone=UTC";
        Class.forName(DRIVER);
        conexion = DriverManager.getConnection(url, USER, PASSWORD);
        System.out.println("Conexión exitosa a la base de datos.");
        return conexion;
    }

    protected void desconectarDataBase() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void insertarModificarEliminarDataBase(String sql) throws Exception {
        try (Connection conn = conectarDataBase(); 
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la operación: " + e.getMessage());
            throw e;
        }
    }

    protected ResultSet consultarDataBase(String sql) throws Exception {
        try {
            conectarDataBase();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet; // El resultado se devuelve para su uso
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            throw e;
        }
    }
}

