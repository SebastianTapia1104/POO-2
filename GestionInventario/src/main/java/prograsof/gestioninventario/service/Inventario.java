package prograsof.gestioninventario.service;

import prograsof.gestioninventario.model.Producto;

import java.util.*;
import java.util.stream.Collectors;

public class Inventario {
    private final Map<String, Producto> productos = new HashMap<>();

    // Agregar + control de errores
    public void agregarProducto(Producto producto) {
        if (producto == null || producto.getId() == null) {
            System.out.println("No se puede agregar un producto nulo o sin ID.");
            return;
        }
        productos.put(producto.getId(), producto);
        System.out.println("Producto '" + producto.getNombre() + "' agregado al inventario.");
    }

    // Eliminar por ID (true si existía)
    public boolean eliminarProducto(String id) {
        if (id == null) return false;
        if (productos.remove(id) != null) {
            System.out.println("Producto con ID '" + id + "' eliminado del inventario.");
            return true;
        }
        System.out.println("Producto con ID '" + id + "' no encontrado. No se puede eliminar.");
        return false;
    }

    // Buscar por nombre o descripción 
    public Collection<Producto> buscarProductosPorNombre(String termino) {
        if (termino == null) termino = "";
        final String q = termino.toLowerCase();
        return productos.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(q) ||
                             p.getDescripcion().toLowerCase().contains(q)) // insensible a las mayúsculas
                .collect(Collectors.toList());
    }

    // Listar todos
    public Collection<Producto> listarTodosLosProductos() {
        return Collections.unmodifiableCollection(productos.values());
    }

    // Actualizar información de un producto 
    public boolean actualizarInformacionProducto(String id, String nuevoNombre, String nuevaDescripcion,
                                                 double nuevoPrecio, int nuevaCantidad) {
        Producto p = productos.get(id);
        if (p == null) {
            System.out.println("Producto con ID '" + id + "' no encontrado para actualizar.");
            return false;
        }
        if (nuevoNombre != null && !nuevoNombre.isBlank()) p.setNombre(nuevoNombre);
        if (nuevaDescripcion != null && !nuevaDescripcion.isBlank()) p.setDescripcion(nuevaDescripcion);
        if (nuevoPrecio > 0d) p.setPrecio(nuevoPrecio);
        if (nuevaCantidad >= 0) p.setCantidadEnStock(nuevaCantidad);
        System.out.println("Producto con ID '" + id + "' actualizado.");
        return true;
    }

    // Informe de inventario
    public void generarInformeInventario() {
        System.out.println("\n--- INFORME DE INVENTARIO ---");
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            productos.values().forEach(p -> System.out.println(p.obtenerDescripcionDetallada()));
        }
        System.out.println("-----------------------------\n");
    }
}
