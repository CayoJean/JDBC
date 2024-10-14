package servicios;

import persistencia.FamiliaDaoExt;
import entidades.Familia;
import java.util.List;

public class FamiliaServicio {
    private FamiliaDaoExt familiaDao;

    public FamiliaServicio() {
        this.familiaDao = new FamiliaDaoExt();
    }

    public List<Familia> listarFamilias() throws Exception {
        return familiaDao.listarFamilias();
    }

    public void imprimirFamilias() throws Exception {
        List<Familia> familias = listarFamilias();
        for (Familia familia : familias) {
            System.out.println("Familia ID: " + familia.getId() + ", Nombre: " + familia.getNombre() +
                    ", Edad Mínima: " + familia.getEdadMinima() + ", Edad Máxima: " + familia.getEdadMaxima() +
                    ", Número de Hijos: " + familia.getNumHijos() + ", Email: " + familia.getEmail());
        }
    }
}
