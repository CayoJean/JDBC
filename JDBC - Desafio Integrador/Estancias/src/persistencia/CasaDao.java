package persistencia;

import entidades.Casa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class CasaDao {
    // Método para obtener conexión a la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/estancias_exterior?useSSL=false&serverTimezone=UTC", 
                "root", 
                "root");
    }

    // Método para buscar casas disponibles en un rango de fechas
    public List<Casa> buscarCasasDisponibles(Date fechaInicio, Date fechaFin) {
        List<Casa> casasDisponibles = new ArrayList<>();
        String query = "SELECT * FROM casas WHERE fecha_desde <= ? AND fecha_hasta >= ? AND pais = 'Reino Unido'";

        try (Connection conn = getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setDate(1, fechaFin);   // Fecha de fin
            stmt.setDate(2, fechaInicio); // Fecha de inicio
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(rs.getInt("id_casa"));
                casa.setCalle(rs.getString("calle"));
                casa.setNumero(rs.getInt("numero"));
                casa.setCodigoPostal(rs.getString("codigo_postal"));
                casa.setCiudad(rs.getString("ciudad"));
                casa.setPais(rs.getString("pais"));
                casa.setFechaDesde(rs.getDate("fecha_desde"));
                casa.setFechaHasta(rs.getDate("fecha_hasta"));
                casa.setTiempoMinimo(rs.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(rs.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(rs.getBigDecimal("precio_habitacion"));
                casa.setTipoVivienda(rs.getString("tipo_vivienda"));
                
                casasDisponibles.add(casa);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return casasDisponibles;
    }

    // Método para incrementar el precio por día en un porcentaje dado para casas en el Reino Unido
    public int incrementarPrecioPorDia(double porcentaje) {
        String consultaActualizacion = "UPDATE casas SET precio_habitacion = precio_habitacion * ? WHERE pais = 'Reino Unido'";
        int filasActualizadas = 0;
    
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(consultaActualizacion)) {
    
            double factorAumento = 1 + (porcentaje / 100);
            stmt.setDouble(1, factorAumento);
            filasActualizadas = stmt.executeUpdate();
    
            // Listar los precios actualizados después de la actualización
            System.out.println("Precios actualizados de casas en Reino Unido:");
            listarPreciosActualizados(); 
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }
    
        return filasActualizadas;
    }
    
    private void listarPreciosActualizados() {
        String query = "SELECT calle, numero, precio_habitacion FROM casas WHERE pais = 'Reino Unido'";
    
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                String calle = rs.getString("calle");
                int numero = rs.getInt("numero");
                BigDecimal precio = rs.getBigDecimal("precio_habitacion");
                System.out.println("Casa: " + calle + " " + numero + ", Precio: " + precio);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    // Método para mostrar el número de casas por país
    public void mostrarNumeroCasasPorPais() {
        String query = "SELECT pais, COUNT(*) AS total_casas FROM casas GROUP BY pais";
    
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
    
            System.out.println("Número de casas por país:");
            while (rs.next()) {
                String pais = rs.getString("pais");
                int totalCasas = rs.getInt("total_casas");
                System.out.println("País: " + pais + ", Total de casas: " + totalCasas);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }
    }

    // Método para buscar casas "limpias" en el Reino Unido
    public List<Casa> buscarCasasLimpiasEnReinoUnido() {
        List<Casa> casasLimpias = new ArrayList<>();
        String sql = "SELECT DISTINCT c.id_casa " +
                     "FROM casas c " +
                     "JOIN comentarios com ON c.id_casa = com.id_casa " +
                     "WHERE c.pais = 'Reino Unido' " +
                     "AND com.comentario LIKE '%limpia%'";
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(rs.getInt("id_casa"));
                casasLimpias.add(casa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return casasLimpias;
    }
    
    
}
