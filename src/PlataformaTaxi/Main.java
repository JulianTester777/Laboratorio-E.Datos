package PlataformaTaxi;

public class Main {

    public static void main(String[] args) {

        PlataformaTaxi plataforma = new PlataformaTaxi();

        // Registrar solicitudes
        plataforma.registrar(
                new Solicitud(1, "Juan", "Centro", "Universidad")
        );

        plataforma.registrar(
                new Solicitud(2, "Ana", "Norte", "Centro")
        );

        plataforma.registrar(
                new Solicitud(3, "Pedro", "Sur", "Norte")
        );

        // Mostrar solicitudes pendientes
        System.out.println("SOLICITUDES PENDIENTES:");
        plataforma.mostrarPendientes();

        // Atender la solicitud más antigua
        System.out.println("\nSOLICITUD ATENDIDA:");

        long inicio = System.nanoTime();

        Solicitud atendida = plataforma.atender();

        long fin = System.nanoTime();

        System.out.println(atendida);
        System.out.println("Tiempo de atención: "
                + (fin - inicio) + " ns");

        // Cancelar una solicitud específica
        System.out.println("\nCANCELANDO SOLICITUD #3:");

        inicio = System.nanoTime();

        plataforma.cancelar(3);

        fin = System.nanoTime();

        System.out.println("Tiempo de cancelación: "
                + (fin - inicio) + " ns");

        // Mostrar solicitudes pendientes
        System.out.println("\nSOLICITUDES PENDIENTES:");
        plataforma.mostrarPendientes();



        // FASE 4: MEDICIÓN CON DIFERENTES TAMAÑOS

        int[] cantidades = {100, 1000, 10000, 100000};

        System.out.println("\n\n==============================================");
        System.out.println("       MEDICIÓN DE LA FASE 4");
        System.out.println("==============================================");
        System.out.println("Solicitudes\tTiempo (ns)\tMemoria (KB)");
        System.out.println("----------------------------------------------");

        for (int cantidad : cantidades) {

            PlataformaTaxi prueba = new PlataformaTaxi();

            Runtime runtime = Runtime.getRuntime();

            // Intentar liberar memoria antes de la prueba
            runtime.gc();

            long memoriaAntes =
                    runtime.totalMemory() - runtime.freeMemory();

            // Comenzar medición del tiempo
            inicio = System.nanoTime();

            // Registrar solicitudes
            for (int i = 1; i <= cantidad; i++) {

                prueba.registrar(
                        new Solicitud(
                                i,
                                "Usuario" + i,
                                "Origen" + i,
                                "Destino" + i
                        )
                );
            }

            // Terminar medición del tiempo
            fin = System.nanoTime();

            long tiempo = fin - inicio;

            // Medir memoria después
            long memoriaDespues =
                    runtime.totalMemory() - runtime.freeMemory();

            long memoriaUsada =
                    memoriaDespues - memoriaAntes;

            long memoriaKB = memoriaUsada / 1024;

            // Mostrar resultado
            System.out.println(
                    cantidad + "\t\t" +
                            tiempo + "\t\t" +
                            memoriaKB
            );
        }
    }
}