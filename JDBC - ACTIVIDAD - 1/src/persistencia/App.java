package persistencia;

public class App {

    public static void main(String[] args) {
        DAO dao = new DAO() {}; // Instancia anónima de la clase abstracta DAO
        try {
            // Estableciendo la conexión a la base de datos
            dao.connectarDataBase();

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

}
