package plataformaventas;

import java.util.*;

public class PlataformaVentas {
    private String nombrePlataforma;
    private Map<String, Producto> mapaProductos; // Búsqueda O(1)
    private LinkedList<Producto> listaNovedades;  // Inserción al inicio O(1)
    private TreeSet<Producto> setPorPrecio;       // Orden por precio O(log n)

    public PlataformaVentas(String nombrePlataforma) {
        this.nombrePlataforma = nombrePlataforma;
        this.mapaProductos = new HashMap<>();
        this.listaNovedades = new LinkedList<>();
        this.setPorPrecio = new TreeSet<>();
    }

    // Insertar productos nuevos AL INICIO
    public void registrarProductoNuevo(String id, boolean disponibilidad, double precio, Categoria categoria, int cantidad) {
        Producto p = new Producto(id, disponibilidad, precio, categoria, cantidad);

        mapaProductos.put(p.getId(), p);
        listaNovedades.addFirst(p); // Agrega al inicio en O(1)
        setPorPrecio.add(p);        // Mantiene el orden por precio O(log n)
    }

    // Buscar por ID en O(1)
    public Producto buscarPorCodigo(String id) {
        return mapaProductos.get(id);
    }

    // Mostrar productos ordenados por precio
    public Set<Producto> obtenerProductosOrdenadosPorPrecio() {
        return setPorPrecio;
    }

    // Filtrar por categoría usando operaciones funcionales (Streams)
    public List<Producto> filtrarPorCategoria(Categoria categoria) {
        return listaNovedades.stream()
                .filter(p -> p.getCategoria() == categoria)
                .toList();
    }

    public LinkedList<Producto> getListaNovedades() {
        return listaNovedades;
    }
}