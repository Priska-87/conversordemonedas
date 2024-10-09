import java.time.LocalDateTime;

public class RegistroConversion {
    private final String codigoOrigen;
    private final String codigoDestino;
    private final double montoOriginal;
    private final double montoConvertido;
    private final double tasaCambio;
    private final LocalDateTime fechaHora;

    public RegistroConversion(String codigoOrigen, String codigoDestino, double montoOriginal,
                              double montoConvertido, double tasaCambio) {
        this.codigoOrigen = codigoOrigen;
        this.codigoDestino = codigoDestino;
        this.montoOriginal = montoOriginal;
        this.montoConvertido = montoConvertido;
        this.tasaCambio = tasaCambio;
        this.fechaHora = LocalDateTime.now();  // Marca de tiempo actual
    }

    @Override
    public String toString() {
        return "Conversión realizada el " + fechaHora +
                ": " + montoOriginal + " " + codigoOrigen +
                " a " + montoConvertido + " " + codigoDestino +
                " con una tasa de cambio de " + tasaCambio;
    }
}
