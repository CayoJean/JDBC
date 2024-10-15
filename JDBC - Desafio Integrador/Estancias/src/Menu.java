import java.util.List;
import java.util.Scanner;

import entidades.Casa;
// Asegúrate de tener esta clase
import persistencia.CasaDao;
import persistencia.ClienteDAO;
import servicios.CasaServicio;
import servicios.FamiliaServicio;
import java.sql.Date; // Importa java.sql.Date

public class Menu {
    private FamiliaServicio familiaServicio = new FamiliaServicio(); // Incluir más servicios según necesidad
    private CasaServicio casaServicio = new CasaServicio(); // Mueve esta instancia aquí para evitar múltiples instancias
    private CasaDao casaDao = new CasaDao(); // Declarar e inicializar casaDao aquí
 // Declarar e inicializar estanciaDAO

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n----- Menú de Funcionalidades -----");
            System.out.println("1. Listar familias con al menos 3 hijos y edad máxima < 10 años.");
            System.out.println("2. Listar casas disponibles entre 1 y 31 de agosto de 2020 en Reino Unido.");
            System.out.println("3. Listar familias con correo Hotmail.");
            System.out.println("4. Buscar casas disponibles a partir de una fecha y número de días.");
            System.out.println("5. Listar clientes con sus estancias.");
            System.out.println("6. Listar estancias reservadas por cliente.");
            System.out.println("7. Incrementar precio por día en 5% para casas en Reino Unido.");
            System.out.println("8. Mostrar número de casas por país.");
            System.out.println("9. Listar casas del Reino Unido con comentarios 'limpias'.");
            System.out.println("10. Insertar nueva estancia verificando disponibilidad.");
            System.out.println("0. Salir.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    familiaServicio.listarFamiliasConHijosMenores();
                    break;
                case 2:
                    System.out.println("Buscando casas disponibles entre el 1 y el 31 de agosto de 2020 en Reino Unido...");
                    Date fechaInicio = Date.valueOf("2020-08-01");
                    Date fechaFin = Date.valueOf("2020-08-31");

                    List<Casa> casasDisponibles = casaServicio.listarCasasDisponibles(fechaInicio, fechaFin);
                    if (casasDisponibles.isEmpty()) {
                        System.out.println("No se encontraron casas disponibles.");
                    } else {
                        for (Casa casa : casasDisponibles) {
                            System.out.println(casa); // Asegúrate de que Casa tenga un método toString() adecuado
                        }
                    }
                    break;
                case 3:
                    familiaServicio.mostrarFamiliasConHotmail();
                    break;
                case 4:
                    // Captura la fecha y el número de días desde el usuario
                    System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
                    String fechaInicioStr = scanner.nextLine();
                    System.out.print("Ingrese el número de días: ");
                    int numeroDias = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea

                    // Convertir la fecha ingresada a java.sql.Date
                    Date fechaInicio2 = Date.valueOf(fechaInicioStr);
                    Date fechaFin2 = new Date(fechaInicio2.getTime() + (numeroDias * 86400000L)); // Sumar los días a la fecha

                    List<Casa> casasDisponibles2 = casaServicio.listarCasasDisponibles(fechaInicio2, fechaFin2);
                    if (casasDisponibles2.isEmpty()) {
                        System.out.println("No se encontraron casas disponibles.");
                    } else {
                        for (Casa casa : casasDisponibles2) {
                            System.out.println(casa); // Asegúrate de que Casa tenga un método toString() adecuado
                        }
                    }
                    break;
                case 5:
                    // Solo declara clienteDAO aquí
                    ClienteDAO clienteDAO = new ClienteDAO();
                    try {
                        clienteDAO.listarClientesConEstancias(); // Llama al método para listar clientes
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    ClienteDAO clienteDAO2 = new ClienteDAO();
                    try {
                        clienteDAO2.listarEstanciasPorCliente(); // Cambiar aquí
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    // Asumiendo que estás llamando a incrementarPrecioPorDia en el menú
                    casaServicio.incrementarPrecioPorDia(5); // Incrementa el precio en un 5%
                    break;
                case 8:
                    casaDao.mostrarNumeroCasasPorPais(); // Ya no hay error aquí
                    break;
                case 9:
                    List<Casa> casasLimpias = casaDao.buscarCasasLimpiasEnReinoUnido();

                    if (casasLimpias.isEmpty()) {
                        System.out.println("No se encontraron casas limpias en el Reino Unido.");
                    } else {
                        for (Casa casa : casasLimpias) {
                            System.out.println("Casa ID: " + casa.getIdCasa());
                            // Imprimir más detalles si es necesario
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}