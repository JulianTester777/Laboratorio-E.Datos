public class Solicitud {

    private int id;
    private String usuario;
    private String origen;
    private String destino;

    public Solicitud(int id, String usuario,
                     String origen, String destino) {

        this.id = id;
        this.usuario = usuario;
        this.origen = origen;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Solicitud #" + id +
                " | Usuario: " + usuario +
                " | " + origen + " -> " + destino;
    }
}