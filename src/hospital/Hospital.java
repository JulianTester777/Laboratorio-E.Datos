package hospital;

import java.time.LocalDateTime;
import java.util.*;

public class Hospital {
    private String nombre;
    private String direccion;
    private Map<String,Paciente> listPacientes;
    private Queue<Paciente> listPacientesPrioridad;

    public Hospital(String nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
        this.listPacientes = new LinkedHashMap<>();
        this.listPacientesPrioridad = new PriorityQueue<>();
    }

    public void registrarPacientes(String id,
                                   String nombre, String telefono,
                                   int edad,
                                   int numeroAntecedentes, LocalDateTime fechaIngreso,
                                   Estado estado, Gravedad gravedad){

        Paciente p1 = new Paciente(id,nombre,telefono,edad,numeroAntecedentes,fechaIngreso,estado,gravedad);

        listPacientes.put(p1.getId(),p1);

        listPacientesPrioridad.offer(p1);
    }

    public Paciente buscarPacienteporId(String idPacienteBuscar){
        return listPacientes.get(idPacienteBuscar);
    }

    public Paciente atenderPaciente(){
        Paciente p1 = listPacientesPrioridad.poll();
        if(p1 != null){
            p1.setEstado(Estado.ATENDIDO);
        }
        return p1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
