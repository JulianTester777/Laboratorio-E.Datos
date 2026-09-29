package hospital;

import java.time.LocalDateTime;

public class HistorialMedico{
    private LocalDateTime fechaAtencion;
    private String diagnostico;
    private String tratamiento;

    public HistorialMedico(LocalDateTime fechaAtencion, String diagnostico, String tratamiento){
        this.fechaAtencion = fechaAtencion;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public LocalDateTime getFechaAtencion(){
        return fechaAtencion;
    }
    public String getDiagnostico(){
        return diagnostico;
    }
    public String getTratamiento(){
        return tratamiento;
    }

    public void setFechaAtencion(LocalDateTime fechaAtencion){
        this.fechaAtencion = fechaAtencion;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
