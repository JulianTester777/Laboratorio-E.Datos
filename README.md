# Casos de Estudio: Java Collections

Análisis, selección e implementación de estructuras de datos de la Java Collections Framework para cuatro escenarios. Cada escenario sigue la misma metodología en cuatro fases: comprender el problema, seleccionar la estructura, analizar la complejidad y medir el rendimiento real.

## Autores

- John Deiby Morales Guzman
- Julian David Montoya Vanegas

## Metodología

1. **Fase 1 - Comprender el problema:** tipo de datos, operaciones frecuentes, operaciones críticas, volumen y relevancia del orden. No se programa todavía.
2. **Fase 2 - Selección de la estructura:** se elige la estructura por operación y se justifica.
3. **Fase 3 - Análisis de complejidad:** tiempo y memoria de cada operación antes de programar.
4. **Fase 4 - Implementación y medición:** pruebas con distintos tamaños de datos (100, 1.000, 10.000, 100.000), midiendo tiempo de ejecución y memoria aproximada.

## Resumen de escenarios

| Escenario | Estructuras elegidas | Idea principal |
|---|---|---|
| 1. Registro de pacientes | LinkedHashMap + PriorityQueue | Búsqueda por documento en O(1), sin duplicados, orden de llegada y atención por gravedad en O(log n) |
| 2. Plataforma de ventas masivas | HashMap + LinkedList + TreeSet | Búsqueda por código en O(1), inserción al inicio en O(1), orden por precio en O(log n) |
| 3. Solicitud de taxis | Queue con LinkedList | Cola FIFO: registrar y atender la más antigua en O(1) |
| 4. Catálogo de e-commerce | HashMap | Insertar y buscar por código en O(1); ordenar por precio solo cuando se solicita |

## Escenario 1: Sistema de registro de pacientes

**Requisitos:** registrar pacientes, mantener el orden de llegada, buscar por número de documento y evitar duplicados.

**Decisión:** no se usa una sola estructura.

- `LinkedHashMap`: búsqueda rápida por documento, sin duplicados y con orden de llegada.
- `PriorityQueue`: atiende primero a los pacientes más graves y, en caso de empate, según el número de antecedentes.

**Descartadas:** `HashMap` (no conserva el orden) y `TreeMap` (ordena por clave, no por llegada).

**Resultado:** con 50.000 pacientes el sistema procesa el registro en menos de 50 ms y con un consumo cercano a 18 MB de RAM. Ambas colecciones guardan referencias al mismo objeto `Paciente`, por lo que no se duplican los datos.

## Escenario 2: Plataforma de ventas masivas

**Requisitos:** buscar por código (muy frecuente), mostrar productos ordenados por precio, insertar miles de productos por hora al inicio de la lista y filtrar por categoría.

| Operación | Estructura | Tiempo |
|---|---|---|
| Buscar por código | HashMap | O(1) |
| Insertar al inicio | LinkedList (`addFirst`) | O(1) |
| Ordenar por precio | TreeSet | O(log n) |
| Filtrar por categoría | Streams sobre la lista | O(n) |

**Resultados medidos:**

- 100 búsquedas por código tomaron menos de 1 ms (entre 0,14 ms y 0,17 ms con volumen alto).
- Insertar 10.000 productos con sus relaciones de orden tomó 27,88 ms.
- Filtrar 10.000 elementos por categoría (más de 2.200 coincidencias) tomó 1,67 ms.

**Conclusión:** la implementación híbrida cumple todas las restricciones y el filtrado con streams no requiere una estructura adicional a esta escala.

## Escenario 3: Plataforma de solicitud de taxis

**Requisitos:** registrar solicitudes, atender la más antigua (FIFO), cancelar una solicitud específica y mostrar las pendientes.

**Estructura:** `Queue` implementada con `LinkedList`.

| Operación | Método | Tiempo |
|---|---|---|
| Registrar | `add` / `offer` | O(1) |
| Atender la más antigua | `poll` | O(1) |
| Cancelar por ID | `remove` | O(n) |
| Mostrar pendientes | recorrido | O(n) |

**Mediciones:**

| Solicitudes | Tiempo (ns) | Memoria aprox. (KB) |
|---|---|---|
| 100 | 5.048.400 | 512 |
| 1.000 | 12.669.600 | 228 |
| 10.000 | 43.997.900 | 2.244 |
| 100.000 | 154.878.000 | 22.864 |

**Conclusión:** el tiempo crece con el volumen de datos, como se esperaba. La memoria muestra una tendencia general al aumento, con variaciones atribuibles a la administración automática de memoria de Java. La estructura es adecuada incluso con 100.000 solicitudes.

## Escenario 4: Catálogo de productos de e-commerce

**Requisitos:** buscar por código e insertar productos de forma rápida, y mostrar productos ordenados por precio solo cuando se solicite.

**Estructura:** `HashMap` con el código como clave. Para ordenar por precio, los valores se copian a una lista y se ordenan bajo demanda.

| Operación | Tiempo |
|---|---|
| Buscar por código | O(1) |
| Insertar producto | O(1) |
| Ordenar por precio | O(n log n), solo cuando se solicita |

**Mediciones:**

| Productos | Tiempo (ns) | Memoria aprox. (KB) |
|---|---|---|
| 100 | 1.018.800 | 1.024 |
| 1.000 | 9.238.600 | 196 |
| 10.000 | 12.736.500 | 1.807 |
| 100.000 | 89.530.100 | 20.767 |

**Conclusión:** el rendimiento se mantiene estable al crecer los datos. La operación más costosa es el ordenamiento, pero no se ejecuta de forma permanente.

## Estructura del repositorio

```
.
├── escenario1-pacientes/
├── escenario2-ventas-masivas/
├── escenario3-taxis/
├── escenario4-ecommerce/
└── README.md
```

## Tecnologías

- Java
- Java Collections Framework (`HashMap`, `LinkedHashMap`, `TreeSet`, `PriorityQueue`, `LinkedList`)

## Cómo ejecutar

```bash
cd escenario3-taxis
javac *.java
java Main
```