import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuConversor {

    private final SolicitudConversion solicitudConversion = new SolicitudConversion();
    private final List<RegistroConversion> registrosConversion = new ArrayList<>();  // Lista para almacenar registros

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Bienvenido al Conversor de Monedas");
                System.out.println("Seleccione una opción:");
                System.out.println("1) Dólar (USD) => Peso Argentino (ARS)");
                System.out.println("2) Dólar (USD) => Real Brasileño (BRL)");
                System.out.println("3) Ingresar códigos de países manualmente");
                System.out.println("4) Ver registros de conversiones");
                System.out.println("5) Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir nueva línea

                String codigoOrigen;
                String codigoDestino;

                switch (opcion) {
                    case 1:
                        codigoOrigen = "USD";
                        codigoDestino = "ARS";
                        break;
                    case 2:
                        codigoOrigen = "USD";
                        codigoDestino = "BRL";
                        break;
                    case 3:
                        System.out.println("Ingrese el código de la moneda de origen (Ej: USD): ");
                        codigoOrigen = scanner.nextLine().toUpperCase();
                        validarCodigoMoneda(codigoOrigen);

                        System.out.println("Ingrese el código de la moneda de destino (Ej: EUR): ");
                        codigoDestino = scanner.nextLine().toUpperCase();
                        validarCodigoMoneda(codigoDestino);
                        break;
                    case 4:
                        mostrarRegistros();  // Mostrar los registros de conversiones
                        continue;
                    case 5:
                        salir = true;
                        continue;
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                        continue;
                }

                if (!salir) {
                    System.out.println("Ingrese el monto a convertir: ");
                    double monto = scanner.nextDouble();
                    validarMonto(monto);

                    Moneda origen = new Moneda("Moneda Origen", codigoOrigen, "$");
                    Moneda destino = new Moneda("Moneda Destino", codigoDestino, "€");

                    ResultadoConversion resultado = solicitudConversion.obtenerTasaConversion(origen, destino, monto);
                    if (resultado != null) {
                        System.out.println("El monto convertido de " + codigoOrigen + " a " + codigoDestino + " es: "
                                + resultado.montoConvertido() + " con una tasa de: " + resultado.tasaCambio());

                        // Crear y guardar el registro de conversión
                        RegistroConversion registro = new RegistroConversion(codigoOrigen, codigoDestino,
                                monto, resultado.montoConvertido(),
                                resultado.tasaCambio());
                        registrosConversion.add(registro);
                    } else {
                        System.out.println("No se pudo realizar la conversión.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: El monto ingresado no es válido.");
                scanner.nextLine();  // Consumir la entrada incorrecta
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
        System.out.println("Gracias por usar el conversor de monedas. ¡Hasta pronto!");
    }

    private void validarCodigoMoneda(String codigo) {
        if (codigo.length() != 3) {
            throw new IllegalArgumentException("El código de la moneda debe tener 3 caracteres.");
        }
    }

    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser un valor positivo.");
        }
    }

    // Método para mostrar los registros de conversiones
    private void mostrarRegistros() {
        if (registrosConversion.isEmpty()) {
            System.out.println("No hay conversiones registradas.");
        } else {
            System.out.println("Registros de conversiones:");
            for (RegistroConversion registro : registrosConversion) {
                System.out.println(registro);
            }
        }
    }
}
