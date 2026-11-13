import java.util.Objects;

public class ClienteServicio {
    private String numeroTurno;      // Identificador principal ejemplo T-01
    private String nombreCliente;
    private String tipoServicio;     // Como Corte, Tintura, Cepillado, Barba.
    private String estilistaAsignado;
    private String estado;           // Como En espera, Atendido, Cancelado.

    // Constructor completo
    public ClienteServicio(String numeroTurno, String nombreCliente, String tipoServicio, String estilistaAsignado, String estado) {
        this.numeroTurno = numeroTurno;
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.estilistaAsignado = estilistaAsignado;
        this.estado = estado;
    }

    // Constructor auxiliar para búsquedas y cancelaciones por número de turno
    public ClienteServicio(String numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    public String getNumeroTurno() {
        return numeroTurno;
    }

    public void setNumeroTurno(String numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getEstilistaAsignado() {
        return estilistaAsignado;
    }

    public void setEstilistaAsignado(String estilistaAsignado) {
        this.estilistaAsignado = estilistaAsignado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("[Turno: %s | Cliente: %s | Servicio: %s | Estilista: %s | Estado: %s]",
                numeroTurno, nombreCliente, tipoServicio, estilistaAsignado, estado);
    }

    // Comparación obligatoria únicamente por el identificador principal (numeroTurno)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteServicio that = (ClienteServicio) o;
        return numeroTurno != null && numeroTurno.equalsIgnoreCase(that.numeroTurno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroTurno != null ? numeroTurno.toLowerCase() : null);
    }
}