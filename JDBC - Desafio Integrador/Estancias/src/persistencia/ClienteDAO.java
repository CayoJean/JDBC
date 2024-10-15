package persistencia;

import java.sql.ResultSet;

public class ClienteDAO extends DAO {

    // Otros métodos y constructores aquí
    public void listarClientesConEstancias() throws Exception {
        // Aquí estamos seleccionando el nombre del cliente y la ciudad y tipo de vivienda de la casa
        String consulta = "SELECT c.id_cliente, c.nombre, ca.ciudad, ca.tipo_vivienda " +
                            "FROM clientes c " +
                            "JOIN estancias e ON c.id_cliente = e.id_cliente " +
                            "JOIN casas ca ON e.id_casa = ca.id_casa;";

        ResultSet resultado = consultarDataBase(consulta);

        boolean hayResultados = false; // Para verificar si hay resultados

        // Imprimir encabezado
        System.out.printf("%-20s %-20s %-20s%n", "Cliente", "Ciudad", "Tipo de Vivienda");
        System.out.println("-------------------------------------------------------------");

        while (resultado.next()) {
            String nombreCliente = resultado.getString("nombre");
            String ciudadCasa = resultado.getString("ciudad");
            String tipoVivienda = resultado.getString("tipo_vivienda");

            // Imprimir resultados con formato
            System.out.printf("%-20s %-20s %-20s%n", nombreCliente, ciudadCasa, tipoVivienda);
            hayResultados = true; // Hay resultados
        }
        
        if (!hayResultados) {
            System.out.println("No se encontraron clientes con estancias.");
        }
    }



    // Numero 6 Buscar y listar todas las estancias que han sido reservadas por un cliente, mostrar el nombre, país y ciudad del cliente y además la información de la casa que reservó. La que reemplazaría a la anterior 
    public void listarEstanciasPorCliente() throws Exception {
        String consulta = "SELECT c.nombre AS cliente_nombre, " +
                "c.pais AS cliente_pais, " +
                "c.ciudad AS cliente_ciudad, " +
                "ca.calle AS casa_calle, " +
                "ca.numero AS casa_numero, " +
                "ca.codigo_postal AS casa_codigo_postal, " +
                "ca.ciudad AS casa_ciudad, " +
                "ca.pais AS casa_pais " +
                "FROM clientes c " +
                "JOIN estancias e ON c.id_cliente = e.id_cliente " +
                "JOIN casas ca ON e.id_casa = ca.id_casa;";

        ResultSet resultado = consultarDataBase(consulta);

        boolean hayResultados = false; // Para verificar si hay resultados
        while (resultado.next()) {
            String nombreCliente = resultado.getString("cliente_nombre");
            String paisCliente = resultado.getString("cliente_pais");
            String ciudadCliente = resultado.getString("cliente_ciudad");
            String calleCasa = resultado.getString("casa_calle");
            String numeroCasa = resultado.getString("casa_numero");
            String codigoPostalCasa = resultado.getString("casa_codigo_postal");
            String ciudadCasa = resultado.getString("casa_ciudad");
            String paisCasa = resultado.getString("casa_pais");

            System.out.println("Cliente: " + nombreCliente + ", País: " + paisCliente + ", Ciudad: " + ciudadCliente +
                                "\n  Casa: " + calleCasa + " " + numeroCasa + ", Código Postal: " + codigoPostalCasa +
                                ", Ciudad: " + ciudadCasa + ", País: " + paisCasa);
            hayResultados = true; // Hay resultados
        }

        if (!hayResultados) {
            System.out.println("No se encontraron estancias reservadas por clientes.");
        }
    }
}
