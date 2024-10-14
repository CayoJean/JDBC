package servicios;

import entidades.Pedido;
import entidades.DetallePedido;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("===== Menú de Opciones =====");
        System.out.println("1. Guardar un nuevo Pedido");
        System.out.println("2. Listar pedidos por Cliente");
        System.out.println("3. Listar pedidos por Estado");
        System.out.println("4. Listar pedidos por Producto");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        
        switch (opcion) {
            case 1:
                crearNuevoPedido();
                break;
            case 2:
                listarPedidosPorCliente();
                break;
            case 3:
                listarPedidosPorEstado();
                break;
            case 4:
                listarPedidosPorProducto();
                break;
            case 5:
                System.out.println("Saliendo del programa...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida, intenta nuevamente.");
                mostrarMenu(); // Vuelve a mostrar el menú
        }
    }

    private void crearNuevoPedido() {
        System.out.println("=== Crear Nuevo Pedido ===");
        
        System.out.print("Código de Pedido: ");
        int codigoPedido = scanner.nextInt();
        
        System.out.print("Fecha de Pedido (YYYY-MM-DD): ");
        String fechaInput = scanner.next();
        Date fechaPedido = Date.valueOf(fechaInput);
        
        System.out.print("Fecha Esperada (YYYY-MM-DD): ");
        fechaInput = scanner.next();
        Date fechaEsperada = Date.valueOf(fechaInput);
        
        // Puedes agregar más entradas para fechaEntrega, estado, comentarios, y idCliente si es necesario.
        
        Pedido pedido = new Pedido(codigoPedido, fechaPedido, fechaEsperada, null, "Pendiente", "Nuevo pedido", 101);

        // Agregar detalles al pedido
        List<DetallePedido> detalles = new ArrayList<>();
        
        // Suponiendo que quieres agregar detalles
        System.out.print("Número de detalles a agregar: ");
        int numDetalles = scanner.nextInt();
        for (int i = 0; i < numDetalles; i++) {
            System.out.print("ID Producto: ");
            int idProducto = scanner.nextInt();
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            System.out.print("Precio Unidad: ");
            double precioUnidad = scanner.nextDouble();
            
            DetallePedido detalle = new DetallePedido(idProducto, pedido.getIdPedido(), cantidad, precioUnidad, (short) (i + 1));
            detalles.add(detalle);
        }

        // Guardar el pedido utilizando PedidoServicio
        PedidoServicio pedidoServicio = new PedidoServicio();
        pedidoServicio.guardarPedido(pedido, detalles);

        System.out.println("Pedido creado con éxito.");
        mostrarMenu(); // Vuelve a mostrar el menú
    }

    private void listarPedidosPorCliente() {
        // Implementa la lógica para listar pedidos por cliente
        System.out.println("=== Listar Pedidos por Cliente ===");
        // Lógica para listar pedidos
        mostrarMenu(); // Vuelve a mostrar el menú
    }

    private void listarPedidosPorEstado() {
        // Implementa la lógica para listar pedidos por estado
        System.out.println("=== Listar Pedidos por Estado ===");
        // Lógica para listar pedidos
        mostrarMenu(); // Vuelve a mostrar el menú
    }

    private void listarPedidosPorProducto() {
        // Implementa la lógica para listar pedidos por producto
        System.out.println("=== Listar Pedidos por Producto ===");
        // Lógica para listar pedidos
        mostrarMenu(); // Vuelve a mostrar el menú
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu(); // Llama a mostrarMenu al iniciar la aplicación
    }
}
