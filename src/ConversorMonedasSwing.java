import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConversorMonedasSwing extends JFrame {

    private JComboBox<String> comboOrigen;
    private JComboBox<String> comboDestino;
    private JTextField campoMonto;
    private JLabel labelResultado;
    private JButton botonEnviar;
    private JButton botonSalir;

    private final List<String> registrosConversiones = new ArrayList<>();
    private final SolicitudConversion solicitudConversion = new SolicitudConversion();

    public ConversorMonedasSwing() {
        setTitle("Conversor de Monedas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el layout de la ventana principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear menú "Opciones"
        JMenu menuOpciones = new JMenu("Opciones");
        menuBar.add(menuOpciones);

        // Crear los elementos del menú
        JMenuItem opcionIngresarCodigos = new JMenuItem("Ingresar código de países");
        JMenuItem opcionVerRegistros = new JMenuItem("Ver registros de conversiones");

        // Agregar los elementos al menú
        menuOpciones.add(opcionIngresarCodigos);
        menuOpciones.add(opcionVerRegistros);

        // Configurar el JFrame con la barra de menú
        setJMenuBar(menuBar);

        // Crear los componentes
        comboOrigen = new JComboBox<>(new String[]{"USD", "ARS", "BRL", "EUR"});
        comboDestino = new JComboBox<>(new String[]{"USD", "ARS", "BRL", "EUR"});
        campoMonto = new JTextField(10);
        labelResultado = new JLabel("Resultado: ");
        botonEnviar = new JButton("Enviar");
        botonSalir = new JButton("Salir");

        // Panel para la entrada del usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Monto a Convertir:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(campoMonto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Moneda de Origen:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(comboOrigen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Moneda de Destino:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(comboDestino, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(labelResultado, gbc);

        // Botones Enviar y Salir
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(botonEnviar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(botonSalir, gbc);

        // Acción del botón Enviar
        botonEnviar.addActionListener(e -> realizarConversion());

        // Acción del botón Salir
        botonSalir.addActionListener(e -> System.exit(0));

        // Acción del menú "Ingresar código de países"
        opcionIngresarCodigos.addActionListener(e -> ingresarCodigosManual());

        // Acción del menú "Ver registros de conversiones"
        opcionVerRegistros.addActionListener(e -> mostrarRegistros());
    }

    // Método para realizar la conversión
    private void realizarConversion() {
        try {
            String codigoOrigen = (String) comboOrigen.getSelectedItem();
            String codigoDestino = (String) comboDestino.getSelectedItem();
            double monto = Double.parseDouble(campoMonto.getText());

            Moneda origen = new Moneda("Moneda Origen", codigoOrigen, "$");
            Moneda destino = new Moneda("Moneda Destino", codigoDestino, "€");

            ResultadoConversion resultado = solicitudConversion.obtenerTasaConversion(origen, destino, monto);
            if (resultado != null) {
                labelResultado.setText("Resultado: " + monto + " " + codigoOrigen + " => "
                        + resultado.montoConvertido() + " " + codigoDestino
                        + " | Tasa: " + resultado.tasaCambio());

                // Registrar la conversión con marca de tiempo
                registrarConversion(codigoOrigen, codigoDestino, monto, resultado.montoConvertido());
            } else {
                labelResultado.setText("No se pudo realizar la conversión.");
            }
        } catch (NumberFormatException e) {
            labelResultado.setText("Error: Monto inválido.");
        } catch (Exception e) {
            labelResultado.setText("Ocurrió un error: " + e.getMessage());
        }
    }

    // Método para registrar la conversión con marca de tiempo
    private void registrarConversion(String origen, String destino, double monto, double montoConvertido) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String registro = "Conversión: " + monto + " " + origen + " => " + montoConvertido + " " + destino
                + " | Fecha y hora: " + fechaHora.format(formato);
        registrosConversiones.add(registro);
    }

    // Método para mostrar los registros de conversiones
    private void mostrarRegistros() {
        if (registrosConversiones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay registros de conversiones.");
        } else {
            StringBuilder registros = new StringBuilder();
            for (String registro : registrosConversiones) {
                registros.append(registro).append("\n");
            }
            JOptionPane.showMessageDialog(this, registros.toString(), "Registros de Conversiones", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para ingresar los códigos manualmente
    private void ingresarCodigosManual() {
        String codigoOrigen = JOptionPane.showInputDialog(this, "Ingrese el código de la moneda de origen:");
        String codigoDestino = JOptionPane.showInputDialog(this, "Ingrese el código de la moneda de destino:");
        double monto = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese el monto a convertir:"));

        Moneda origen = new Moneda("Moneda Origen", codigoOrigen, "$");
        Moneda destino = new Moneda("Moneda Destino", codigoDestino, "€");

        ResultadoConversion resultado = null;
        try {
            resultado = solicitudConversion.obtenerTasaConversion(origen, destino, monto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (resultado != null) {
            registrarConversion(codigoOrigen, codigoDestino, monto, resultado.montoConvertido());
            JOptionPane.showMessageDialog(this, "Resultado: " + monto + " " + codigoOrigen + " => "
                    + resultado.montoConvertido() + " " + codigoDestino
                    + " | Tasa: " + resultado.tasaCambio());
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo realizar la conversión.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConversorMonedasSwing frame = new ConversorMonedasSwing();
            frame.setVisible(true);
        });
    }
}
