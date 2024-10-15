/*package servicios;

import entidades.Casa; // Asegúrate de que esta línea esté presente
import persistencia.CasaDao; // Importa el DAO
import java.util.List;
import java.sql.Date; // Para usar java.sql.Date

public class CasaServicio {
    private CasaDao casaDao;

    // Constructor que inicializa el DAO
    public CasaServicio() {
        this.casaDao = new CasaDao(); // Inicializa el DAO
    }

    // Método para listar casas disponibles entre dos fechas
    public List<Casa> listarCasasDisponibles(Date fechaInicio, Date fechaFin) {
        // Llama al método del DAO y retorna la lista de casas disponibles
        return casaDao.buscarCasasDisponibles(fechaInicio, fechaFin);
    }
}
*/
/*

package servicios;

import entidades.Casa; 
import persistencia.CasaDao; 
import java.util.List;
import java.sql.Date;

public class CasaServicio {
    private CasaDao casaDao;

    public CasaServicio() {
        this.casaDao = new CasaDao();
    }

    public List<Casa> listarCasasDisponibles(Date fechaInicio, Date fechaFin) {
        return casaDao.buscarCasasDisponibles(fechaInicio, fechaFin);
    }

    public void incrementarPrecioPorDia(double porcentaje) {
        int filasActualizadas = casaDao.incrementarPrecioPorDia(porcentaje);
        System.out.println("Se han actualizado " + filasActualizadas + " precios de casas en el Reino Unido.");
        // Aquí puedes optar por listar los precios actualizados si lo deseas
    }
}
*/


package servicios;

import entidades.Casa; 
import persistencia.CasaDao; 
import java.util.List;
import java.sql.Date;

public class CasaServicio {
    private CasaDao casaDao;

    public CasaServicio() {
        this.casaDao = new CasaDao();
    }

    public List<Casa> listarCasasDisponibles(Date fechaInicio, Date fechaFin) {
        return casaDao.buscarCasasDisponibles(fechaInicio, fechaFin);
    }

    public void incrementarPrecioPorDia(double porcentaje) {
        int filasActualizadas = casaDao.incrementarPrecioPorDia(porcentaje);
        System.out.println("Se han actualizado " + filasActualizadas + " precios de casas en el Reino Unido.");
        // Aquí puedes optar por listar los precios actualizados si lo deseas
    }

    // Nuevo método para verificar la disponibilidad de una casa
    public boolean verificarDisponibilidad(int idCasa, Date fechaInicio, int numeroDias) {
        // Aquí puedes implementar la lógica para verificar la disponibilidad
        Date fechaFin = new Date(fechaInicio.getTime() + (numeroDias * 86400000L)); // Calcular la fecha de fin
        List<Casa> casasDisponibles = listarCasasDisponibles(fechaInicio, fechaFin);
        // Verifica si la casa está en la lista de casas disponibles
        for (Casa casa : casasDisponibles) {
            if (casa.getId() == idCasa) { // Asumiendo que Casa tiene un método getId()
                return true; // La casa está disponible
            }
        }
        return false; // La casa no está disponible
    }
}
