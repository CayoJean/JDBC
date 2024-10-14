package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Familia;

public class FamiliaDaoExt extends DAO {

    public List<Familia> listarFamilias() throws Exception {
        List<Familia> familias = new ArrayList<>();
        String sql = "SELECT * FROM familia";

        try (Connection conexion = conectarDataBase();
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Familia familia = new Familia();
                familia.setId(rs.getInt("id"));
                familia.setNombre(rs.getString("nombre"));
                familia.setEdadMinima(rs.getInt("edad_minima"));
                familia.setEdadMaxima(rs.getInt("edad_maxima"));
                familia.setNumHijos(rs.getInt("num_hijos"));
                familia.setEmail(rs.getString("email"));

                familias.add(familia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al listar familias: " + e.getMessage(), e);
        }
        return familias;
    }
}
