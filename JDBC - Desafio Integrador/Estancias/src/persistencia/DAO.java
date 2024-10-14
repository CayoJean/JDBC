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
    private final String DATABASE = "estancias_exterior";  // Asegúrate de que la base de datos 'estancias' exista
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Cambiado de protected a public para poder acceder desde la clase App
    public Connection conectarDataBase() throws SQLException, ClassNotFoundException {
        System.out.println();
        try {
            // Cargar el controlador JDBC
            Class.forName(DRIVER);
        
            // Modificar la URL para incluir la zona horaria
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE 
                        + "?useSSL=false&serverTimezone=UTC"; // Puedes cambiar 'UTC' por tu zona horaria preferida
        
            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            
            // Mensaje de éxito en la conexión
            System.out.println("Conexión exitosa a la base de datos.");
            return conexion; // Devuelve la conexión
        } catch (ClassNotFoundException | SQLException e) {
            // Mostrar el error si ocurre y lanzar la excepción
            System.out.println("Error en la conexión: " + e.getMessage());
            throw e;
        }
    }

    // Cambiado de protected a public
    public void desconectarDataBase() throws SQLException, ClassNotFoundException {
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
            throw e;
        }
    }

    // Se mantiene protected, ya que las operaciones de la base de datos deberían estar limitadas a esta clase o sus subclases
    protected void insertarModificarEliminarDataBase(String sql) throws Exception {
        try {
            conectarDataBase();
            statement = conexion.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la operación: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
    }

    // Se mantiene protected
    protected ResultSet consultarDataBase(String sql) throws Exception {
        try {
            conectarDataBase();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            throw e;
        }
        return resultSet;
    }
}
