package persistencia;

import entidades.Oficina; // Asegúrate de que este import esté presente

public class OficinaDAO extends DAO {

    // Método para agregar una nueva oficina
    public void agregarOficina(Oficina oficina) throws Exception {
        String sql = "INSERT INTO Oficina (id_oficina, nombre_oficina, direccion) VALUES (" +
                oficina.getIdOficina() + ", '" +
                oficina.getNombreOficina() + "', '" +
                oficina.getDireccion() + "');";
        insertarModificarEliminarDataBase(sql);
    }

    // Método para listar todas las oficinas
    public void listarOficinas() throws Exception {
        String sql = "SELECT * FROM Oficina;";
        consultarDataBase(sql);
        
        while (resultSet.next()) {
            Oficina oficina = new Oficina(
                resultSet.getInt("id_oficina"),
                resultSet.getString("nombre_oficina"),
                resultSet.getString("direccion")
            );
            System.out.println(oficina); // Imprime la información de la oficina
        }
    }
}
