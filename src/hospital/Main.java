package hospital;

import java.time.LocalDateTime;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("Hospital Central", "Calle 123");
        Random random = new Random();
        Gravedad[] gravedades = Gravedad.values();

        int[] cantidades = {100, 1000, 50000};

        for (int n : cantidades) {
            // --- 1. TIEMPO DE REGISTRO ---
            long inicioRegistro = System.nanoTime();
            for (int i = 1; i <= n; i++) {
                // Conversión de Enum desde String
                Estado estado = Estado.valueOf("ESPERANDO");
                Gravedad gravedad = gravedades[random.nextInt(gravedades.length)];

                hospital.registrarPacientes(
                        "ID-" + i,
                        "Paciente " + i,
                        "300123456",
                        25,
                        random.nextInt(5),
                        LocalDateTime.now(),
                        estado,
                        gravedad
                );
            }
            long finRegistro = System.nanoTime();

            // --- 2. TIEMPO DE ATENCIÓN ---
            long inicioAtencion = System.nanoTime();
            for (int i = 0; i < n; i++) {
                hospital.atenderPaciente();
            }
            long finAtencion = System.nanoTime();

            // Mostrar resultados
            System.out.println("=== " + n + " PACIENTES ===");
            System.out.println("Tiempo Registro: " + (finRegistro - inicioRegistro) / 1_000_000.0 + " ms");
            System.out.println("Tiempo Atención: " + (finAtencion - inicioAtencion) / 1_000_000.0 + " ms\n");
        }
    }
}