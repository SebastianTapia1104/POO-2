package prograsof.gestioninventario.test.model;

import prograsof.gestioninventario.model.Producto; 
import prograsof.gestioninventario.service.Inventario;

public class TestInventario {
    public static void main(String[] args) {
        System.out.println("--- Pruebas Unitarias: Inventario ---");
        Inventario inventario = new Inventario();

        Producto p1 = new Producto("P001", "Teclado", "Teclado mecánico", 75.00, 20);
        Producto p2 = new Producto("P002", "Mouse", "Mouse ergonómico", 30.00, 15);

        // Agregar
        inventario.agregarProducto(p1);
        assert inventario.listarTodosLosProductos().contains(p1);
        inventario.agregarProducto(p2);
        assert inventario.listarTodosLosProductos().contains(p2);
        assert inventario.listarTodosLosProductos().size() == 2;

        // Agregar nulo
        int sizeBefore = inventario.listarTodosLosProductos().size();
        inventario.agregarProducto(null);
        assert inventario.listarTodosLosProductos().size() == sizeBefore;

        // Eliminar existente
        boolean eliminado = inventario.eliminarProducto("P001");
        assert eliminado;
        assert !inventario.listarTodosLosProductos().contains(p1);
        assert inventario.listarTodosLosProductos().size() == 1;

        // Eliminar inexistente
        int sizeAntes = inventario.listarTodosLosProductos().size();
        boolean elimInexistente = inventario.eliminarProducto("P003");
        assert !elimInexistente;
        assert inventario.listarTodosLosProductos().size() == sizeAntes;

        System.out.println("--- Fin Pruebas: Inventario ---");
    }
}
