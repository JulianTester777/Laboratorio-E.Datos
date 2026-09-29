package plataformaventas;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        PlataformaVentas plataforma = new PlataformaVentas("Mercado Masivo");
        Random random = new Random();
        Categoria[] categorias = Categoria.values();

        int[] cantidades = {100, 1000, 10000, 500000};

        for (int n : cantidades) {
            // 1. REGISTRO E INSERCIÓN AL INICIO
            long inicioRegistro = System.nanoTime();
            for (int i = 1; i <= n; i++) {
                String id = "PROD-" + i;
                double precio = 10.0 + (1000.0 - 10.0) * random.nextDouble();
                Categoria cat = categorias[random.nextInt(categorias.length)];

                plataforma.registrarProductoNuevo(id, true, precio, cat, random.nextInt(50) + 1);
            }
            long finRegistro = System.nanoTime();

            // 2. BÚSQUEDA POR CÓDIGO (100 consultas)
            long inicioBusqueda = System.nanoTime();
            for (int i = 1; i <= 100; i++) {
                plataforma.buscarPorCodigo("PROD-" + random.nextInt(n));
            }
            long finBusqueda = System.nanoTime();

            // 3. FILTRADO POR CATEGORÍA CON STREAMS
            long inicioFiltro = System.nanoTime();
            List<Producto> electronica = plataforma.filtrarPorCategoria(Categoria.ELECTRONICA);
            long finFiltro = System.nanoTime();

            // RESULTADOS DE TIEMPO
            System.out.println("=== " + n + " PRODUCTOS ===");
            System.out.printf("Tiempo Registro (Inserción al inicio + TreeSet): %.3f ms%n", (finRegistro - inicioRegistro) / 1_000_000.0);
            System.out.printf("Tiempo Búsqueda O(1) (100 consultas): %.3f ms%n", (finBusqueda - inicioBusqueda) / 1_000_000.0);
            System.out.printf("Tiempo Filtro Stream Categoría (%d encontrados): %.3f ms%n%n", electronica.size(), (finFiltro - inicioFiltro) / 1_000_000.0);
        }
    }
}