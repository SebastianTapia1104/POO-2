package prograsof.gestioninventario.model;

import java.util.Objects;

public class Producto {
    // Atributos
    private final String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadEnStock;

    // Constructor
    public Producto(String id, String nombre, String descripcion, double precio, int cantidadEnStock) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID de producto no puede ser nulo ni vacío.");
        }
        this.id = id;
        this.nombre = nombre != null ? nombre : "";
        this.descripcion = descripcion != null ? descripcion : "";
        this.precio = Math.max(0d, precio);
        this.cantidadEnStock = Math.max(0, cantidadEnStock);
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getCantidadEnStock() { return cantidadEnStock; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre != null ? nombre : this.nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion != null ? descripcion : this.descripcion; }
    public void setPrecio(double precio) { if (precio > 0d) this.precio = precio; }
    public void setCantidadEnStock(int cantidadEnStock) { if (cantidadEnStock >= 0) this.cantidadEnStock = cantidadEnStock; }

    // Método para actualizar precio
    public void actualizarPrecio(double nuevoPrecio) { setPrecio(nuevoPrecio); }

    // Método para descripción detallada
    public String obtenerDescripcionDetallada() {
        return "ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion
                + ", Precio: $" + precio + ", Stock: " + cantidadEnStock;
    }

    // Igualdad por ID
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return id.equals(producto.id);
    }
    
    
    @Override public int hashCode() { return Objects.hash(id); }
}
