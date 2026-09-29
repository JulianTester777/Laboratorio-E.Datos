import java.util.LinkedList;
import java.util.Queue;

public class PlataformaTaxi {

    private Queue<Solicitud> solicitudes;

    public PlataformaTaxi() {
        solicitudes = new LinkedList<>();
    }

    // Registrar solicitud
    public void registrar(Solicitud solicitud) {
        solicitudes.offer(solicitud);
    }

    // Atender la solicitud más antigua
    public Solicitud atender() {
        return solicitudes.poll();
    }

    // Cancelar una solicitud específica
    public boolean cancelar(int id) {
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getId() == id) {
                return solicitudes.remove(solicitud);
            }
        }

        return false;
    }

    // Mostrar solicitudes pendientes
    public void mostrarPendientes() {
        for (Solicitud solicitud : solicitudes) {
            System.out.println(solicitud);
        }
    }
}