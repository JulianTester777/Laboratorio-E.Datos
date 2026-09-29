package ECommerce;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();



        //Insertar productos
        System.out.println("=== INSERTANDO PRODUCTOS ===");
        catalogo.insertar(new Producto("P001", "Laptop", 2500000));
        catalogo.insertar(new Producto("P002", "Mouse", 15000));
        catalogo.insertar(new Producto("P003", "Teclado", 45000));
        catalogo.insertar(new Producto("P004", "Cargador", 120000));
        catalogo.insertar(new Producto("P005", "Audifonos", 30000));
        System.out.println("Productos en el catalogo: " + catalogo.cantidad());

        //Buscar
        System.out.println("\n=== BUSCANDO POR CODIGO ===");
        Producto encontrado = catalogo.buscar("P003");
        System.out.println("Resultado: " + encontrado);

        Producto noExiste = catalogo.buscar("P999");
        System.out.println("Buscar P999: "
                + (noExiste == null ? "no existe en el catalogo" : noExiste));

        //Ordenar por precio
        System.out.println("\n=== ORDENADOS POR PRECIO ===");
        List<Producto> ordenados = catalogo.OrdenarPorPrecio();
        for (Producto p : ordenados) {
            System.out.println(p);
        }


        int[] cantidades = {100, 1000, 10000, 100000};

        System.out.println("\n\n==============================================");
        System.out.println("              TABLA DE MEDICION");
        System.out.println("==============================================");
        System.out.println("Productos\tTiempo (ns)\tMemoria (KB)");
        System.out.println("----------------------------------------------");

        for (int cantidad : cantidades) {

            Catalogo prueba = new Catalogo();

            Runtime runtime = Runtime.getRuntime();


            runtime.gc();

            long memoriaAntes =
                    runtime.totalMemory() - runtime.freeMemory();

            // Comenzar medición del tiempo
            long inicio = System.nanoTime();

            // Insertar productos
            for (int i = 1; i <= cantidad; i++) {
                prueba.insertar(
                        new Producto(
                                "P" + i,
                                "Producto" + i,
                                1000 + i
                        )
                );
            }


            long fin = System.nanoTime();

            long tiempo = fin - inicio;

            // Medir memoria después
            long memoriaDespues =
                    runtime.totalMemory() - runtime.freeMemory();

            long memoriaKB = (memoriaDespues - memoriaAntes) / 1024;

            // Mostrar resultado
            System.out.println(
                    cantidad + "\t\t" +
                            tiempo + "\t\t" +
                            memoriaKB
            );
        }
    }
}