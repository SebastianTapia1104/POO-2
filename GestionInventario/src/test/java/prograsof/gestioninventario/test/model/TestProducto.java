package prograsof.gestioninventario.test.model;

import prograsof.gestioninventario.model.Producto;

public class TestProducto {

    public static void main(String[] args) {
        System.out.println("--- Pruebas Unitarias: Producto ---");

        // Creación
        Producto p = new Producto("P001", "Laptop", "Laptop de alto rendimiento", 1200.00, 10);
        assert p.getId().equals("P001");
        assert p.getNombre().equals("Laptop");
        assert p.getDescripcion().equals("Laptop de alto rendimiento");
        assert p.getPrecio() == 1200.00;
        assert p.getCantidadEnStock() == 10;
        System.out.println("Creación: OK");

        // Actualización precio
        p.actualizarPrecio(1150.50);
        assert p.getPrecio() == 1150.50;
        System.out.println("Actualizar precio: OK");

        // Actualización cantidad
        p.setCantidadEnStock(8);
        assert p.getCantidadEnStock() == 8;
        System.out.println("Actualizar stock: OK");

        System.out.println("--- Fin Pruebas: Producto ---");
    }
}
