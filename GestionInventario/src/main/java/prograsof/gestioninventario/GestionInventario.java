package prograsof.gestioninventario;

import prograsof.gestioninventario.model.Producto;
import prograsof.gestioninventario.service.Inventario;

import java.util.Collection;
import java.util.Scanner;

// MENU PRINCIPAL
public class GestionInventario {
    private final Inventario inventario = new Inventario();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("--- Sistema de Gestión de Inventario ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Actualizar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Buscar Productos por Nombre/Descripción");
            System.out.println("5. Listar Todos los Productos");
            System.out.println("6. Generar Informe de Inventario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) { System.out.print("Ingrese un número válido: "); scanner.next(); }
            opcion = scanner.nextInt(); scanner.nextLine(); // consumir salto

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> actualizarProducto();
                case 3 -> eliminarProducto();
                case 4 -> buscarProducto();
                case 5 -> listarProductos();
                case 6 -> inventario.generarInformeInventario();
                case 0 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 0);
        scanner.close();
    }

    private void agregarProducto() {
        System.out.println("\n--- Agregar Nuevo Producto ---");
        System.out.print("ID del producto: "); String id = scanner.nextLine();
        System.out.print("Nombre del producto: "); String nombre = scanner.nextLine();
        System.out.print("Descripción del producto: "); String descripcion = scanner.nextLine();
        System.out.print("Precio del producto: "); double precio = leerDoublePositivo();
        System.out.print("Cantidad en stock: "); int cantidad = leerEnteroNoNegativo();

        Producto nuevo = new Producto(id, nombre, descripcion, precio, cantidad);
        inventario.agregarProducto(nuevo);
    }

    private void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");
        System.out.print("ID del producto a actualizar: "); String id = scanner.nextLine();

        System.out.print("Nuevo nombre (vacío = sin cambio): "); String nuevoNombre = scanner.nextLine();
        System.out.print("Nueva descripción (vacío = sin cambio): "); String nuevaDescripcion = scanner.nextLine();
        System.out.print("Nuevo precio (>0, 0 = sin cambio): "); double nuevoPrecio = leerDoubleNoNegativo();
        System.out.print("Nueva cantidad (>=0, -1 = sin cambio): "); int nuevaCantidad = leerEnteroConSentinela();

        if (nuevaCantidad == -1) {
            // mantener cantidad actual
            nuevaCantidad = -1;
        }

        inventario.actualizarInformacionProducto(
                id,
                nuevoNombre.isBlank() ? null : nuevoNombre,
                nuevaDescripcion.isBlank() ? null : nuevaDescripcion,
                nuevoPrecio == 0d ? -1d : nuevoPrecio,
                (nuevaCantidad < 0) ? -1 : nuevaCantidad
        );
    }

    private void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        System.out.print("ID del producto a eliminar: "); String id = scanner.nextLine();
        inventario.eliminarProducto(id);
    }

    private void buscarProducto() {
        System.out.println("\n--- Buscar Productos ---");
        System.out.print("Ingrese nombre o descripción: "); String termino = scanner.nextLine();
        Collection<Producto> resultados = inventario.buscarProductosPorNombre(termino);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            System.out.println("--- Resultados ---");
            resultados.forEach(p -> System.out.println(p.obtenerDescripcionDetallada()));
        }
    }

    private void listarProductos() {
        System.out.println("\n--- Listado de Productos ---");
        Collection<Producto> todos = inventario.listarTodosLosProductos();
        if (todos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            todos.forEach(p -> System.out.println(p.obtenerDescripcionDetallada()));
        }
    }

    // Utilidades de lectura
    private double leerDoublePositivo() {
        while (true) {
            while (!scanner.hasNextDouble()) { System.out.print("Ingrese un número válido: "); scanner.next(); }
            double v = scanner.nextDouble(); scanner.nextLine();
            if (v > 0d) return v;
            System.out.print("Debe ser > 0. Intente de nuevo: ");
        }
    }

    private double leerDoubleNoNegativo() {
        while (true) {
            while (!scanner.hasNextDouble()) { System.out.print("Ingrese un número válido: "); scanner.next(); }
            double v = scanner.nextDouble(); scanner.nextLine();
            if (v >= 0d) return v;
            System.out.print("Debe ser >= 0. Intente de nuevo: ");
        }
    }

    private int leerEnteroNoNegativo() {
        while (true) {
            while (!scanner.hasNextInt()) { System.out.print("Ingrese un entero válido: "); scanner.next(); }
            int v = scanner.nextInt(); scanner.nextLine();
            if (v >= 0) return v;
            System.out.print("Debe ser >= 0. Intente de nuevo: ");
        }
    }

    private int leerEnteroConSentinela() {
        while (true) {
            while (!scanner.hasNextInt()) { System.out.print("Ingrese un entero válido: "); scanner.next(); }
            int v = scanner.nextInt(); scanner.nextLine();
            if (v >= -1) return v;
            System.out.print("Debe ser -1 (sin cambio) o >= 0. Intente de nuevo: ");
        }
    }

    public static void main(String[] args) {
        new GestionInventario().mostrarMenu();
    }
}
