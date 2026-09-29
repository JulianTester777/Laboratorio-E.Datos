package hospital;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Paciente implements Comparable<Paciente>{
    private String id;
    private String nombre;
    private String telefono;
    private int edad;
    private int numeroAntecedentes;
    private LocalDateTime fechaIngreso;
    private Estado estado;
    private Gravedad gravedad;
    private Map<Integer,HistorialMedico> listHistorialMedico;
    private int contador;

    public Paciente(String id,
                    String nombre, String telefono,
                    int edad,
                    int numeroAntecedentes, LocalDateTime fechaIngreso,
                    Estado estado, Gravedad gravedad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.numeroAntecedentes = numeroAntecedentes;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.gravedad = gravedad;
        this.listHistorialMedico = new HashMap<>();
        this.contador=1;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Estado getEstado() {
        return estado;
    }
    public int getNumeroAntecedentes(){
        return numeroAntecedentes;
    }
    public Gravedad getGravedad(){
        return gravedad;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGravedad(Gravedad gravedad) {
        this.gravedad = gravedad;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public void agregarHistorial(LocalDateTime fechaAtencion, String diagnostico, String tratamiento){
        HistorialMedico historialMedico1 = new HistorialMedico(fechaAtencion,diagnostico,tratamiento);
        int idHistorial = contador++;
        listHistorialMedico.put(idHistorial,historialMedico1);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Paciente paciente)) return false;
        return Objects.equals(getId(), paciente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public int compareTo(Paciente otro) {
        int comparacionGravedad = this.gravedad.compareTo(otro.getGravedad());
        if(comparacionGravedad != 0){
            return comparacionGravedad;
        }
        return this.numeroAntecedentes - otro.getNumeroAntecedentes();
    }
}
