import persistencia.DAO;

public class App {
    public static void main(String[] args) {
        DAO dao = new DAO() {};  // Creas una instancia de DAO usando una clase anónima
        
        try {
            // Conectar a la base de datos
            dao.conectarDataBase();
            
            // Después de realizar las operaciones, desconectas la base de datos
            dao.desconectarDataBase();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
