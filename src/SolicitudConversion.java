import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

public class SolicitudConversion {

    private static final String CLAVE_API = "53732e28d8dbf66c608d16c0";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public ResultadoConversion obtenerTasaConversion(Moneda origen, Moneda destino, double monto) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        String url = URL_BASE + CLAVE_API + "/pair/" + origen.codigo() + "/" + destino.codigo();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonObject = JsonParser.parseString(respuesta.body()).getAsJsonObject();

        if ("success".equals(jsonObject.get("result").getAsString())) {
            double tasaCambio = jsonObject.get("conversion_rate").getAsDouble();
            double montoConvertido = monto * tasaCambio;
            return new ResultadoConversion(montoConvertido, tasaCambio);
        } else {
            System.out.println("Error en la solicitud: " + jsonObject.get("error-type").getAsString());
            return null;
        }
    }
}
