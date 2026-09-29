package plataformaventas;

import java.util.Objects;

public class Producto implements Comparable<Producto> {
    private String id;
    private boolean disponibilidad;
    private double precio;
    private Categoria categoria;
    private int cantidad;

    public Producto(String id, boolean disponibilidad, double precio, Categoria categoria, int cantidad) {
        this.id = id;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public double getPrecio() {
        return precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(getId(), producto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public int compareTo(Producto otro) {
        int comparacionPrecio = Double.compare(this.precio, otro.getPrecio());
        if (comparacionPrecio != 0) {
            return comparacionPrecio;
        }
        // Si tienen el mismo precio, desempata por ID para no borrar productos duplicados en precio
        return this.id.compareTo(otro.getId());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", cantidad=" + cantidad +
                '}';
    }
}