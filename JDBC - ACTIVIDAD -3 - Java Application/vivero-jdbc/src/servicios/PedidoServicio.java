package servicios;

import entidades.Pedido;
import entidades.DetallePedido;

import java.util.List;

public class PedidoServicio {
    
    public void guardarPedido(Pedido pedido, List<DetallePedido> detalles) {
        // Implementa la lógica para guardar el pedido y sus detalles en la base de datos
        // Ejemplo: Lógica para guardar en la base de datos
        System.out.println("Guardando pedido: " + pedido);
        for (DetallePedido detalle : detalles) {
            System.out.println("Guardando detalle: " + detalle);
        }
    }

    // Otros métodos de PedidoServicio
}
