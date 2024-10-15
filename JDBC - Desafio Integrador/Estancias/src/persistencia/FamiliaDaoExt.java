package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Familia;

public class FamiliaDaoExt extends DAO {

    // Método existente
    public List<Familia> buscarFamiliasConHijosMenores() throws Exception {
        List<Familia> familias = new ArrayList<>();
        String sql = "SELECT * FROM familias WHERE num_hijos >= 3 AND edad_maxima < 10";

        try (Connection conexion = conectarDataBase();
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Familia familia = new Familia();
                familia.setId(rs.getInt("id_familia"));
                familia.setNombre(rs.getString("nombre"));
                familia.setEdadMinima(rs.getInt("edad_minima"));
                familia.setEdadMaxima(rs.getInt("edad_maxima"));
                familia.setNumHijos(rs.getInt("num_hijos"));
                familia.setEmail(rs.getString("email"));

                familias.add(familia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar familias con hijos menores.", e);
        }
        return familias;
    }

    // Nuevo método para buscar familias con correos de Hotmail
    public List<Familia> buscarFamiliasConHotmail() throws Exception {
        List<Familia> familias = new ArrayList<>();
        String sql = "SELECT * FROM familias WHERE email LIKE '%@hotmail.com'";

        try (Connection conexion = conectarDataBase();
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Familia familia = new Familia();
                familia.setId(rs.getInt("id_familia"));
                familia.setNombre(rs.getString("nombre"));
                familia.setEdadMinima(rs.getInt("edad_minima"));
                familia.setEdadMaxima(rs.getInt("edad_maxima"));
                familia.setNumHijos(rs.getInt("num_hijos"));
                familia.setEmail(rs.getString("email"));

                familias.add(familia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar familias con correos de Hotmail.", e);
        }
        return familias;
    }
}
