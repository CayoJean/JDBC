package persistencia;

import entidades.Estancia; 
import java.sql.Date; // Asegúrate de importar java.sql.Date

import java.sql.SQLException;

public class EstanciaDAO extends DAO {

    public boolean verificarDisponibilidad(int idCasa, Date fechaInicio, Date fechaFin) {
        boolean disponible = true;
        String sql = "SELECT * FROM estancias WHERE idCasa = ? AND " +
                     "((fechaInicio <= ? AND fechaFin >= ?) OR " +
                     "(fechaInicio <= ? AND fechaFin >= ?))";

        try {
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idCasa);
            preparedStatement.setDate(2, fechaFin);
            preparedStatement.setDate(3, fechaInicio);
            preparedStatement.setDate(4, fechaFin);
            preparedStatement.setDate(5, fechaInicio);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // Si hay resultados, significa que hay una colisión de fechas
                disponible = false;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad: " + e.getMessage());
        } finally {
            desconectarDataBase();
        }

        return disponible;
    }

    public void insertarEstancia(Estancia estancia) {
        if (verificarDisponibilidad(estancia.getIdCasa(), 
                new Date(estancia.getFechaInicio().getTime()), 
                new Date(estancia.getFechaFin().getTime()))) {
            String sql = "INSERT INTO estancias (idCasa, idCliente, fechaInicio, fechaFin) VALUES (?, ?, ?, ?)";
            try {
                preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setInt(1, estancia.getIdCasa());
                preparedStatement.setInt(2, estancia.getIdCliente());
                preparedStatement.setDate(3, new Date(estancia.getFechaInicio().getTime()));
                preparedStatement.setDate(4, new Date(estancia.getFechaFin().getTime()));
                preparedStatement.executeUpdate();
                System.out.println("Estancia insertada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertar la estancia: " + e.getMessage());
            } finally {
                desconectarDataBase();
            }
        } else {
            System.out.println("No se puede insertar la estancia: las fechas no están disponibles.");
        }
    }
}
