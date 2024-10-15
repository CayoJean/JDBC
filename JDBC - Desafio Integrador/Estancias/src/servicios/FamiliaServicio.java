package servicios;

import persistencia.FamiliaDaoExt;
import entidades.Familia;

import java.util.List;

public class FamiliaServicio {

    private FamiliaDaoExt familiaDao;

    public FamiliaServicio() {
        this.familiaDao = new FamiliaDaoExt();
    }

    public void listarFamiliasConHijosMenores() {
        try {
            List<Familia> familias = familiaDao.buscarFamiliasConHijosMenores();
            if (familias.isEmpty()) {
                System.out.println("No se encontraron familias con al menos 3 hijos y edad máxima menor a 10 años.");
            } else {
                for (Familia familia : familias) {
                    System.out.println(familia);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar familias: " + e.getMessage());
        }
    }

    // Nuevo método para listar familias con correos de Hotmail
    public void mostrarFamiliasConHotmail() {
        try {
            List<Familia> familias = familiaDao.buscarFamiliasConHotmail();
            if (familias.isEmpty()) {
                System.out.println("No se encontraron familias con correos de Hotmail.");
            } else {
                for (Familia familia : familias) {
                    System.out.println("ID: " + familia.getId());
                    System.out.println("Nombre: " + familia.getNombre());
                    System.out.println("Email: " + familia.getEmail());
                    System.out.println("------------------------------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar familias: " + e.getMessage());
        }
    }
}
