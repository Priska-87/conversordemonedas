<h1 align="center">Conversor de Monedas - Java Challenge</h1>

<h2>Descripción del Proyecto</h2>
<p>
Este proyecto es un <strong>conversor de monedas</strong> desarrollado en Java que permite convertir montos entre diferentes divisas usando datos en tiempo real proporcionados por una <strong>API de tasas de cambio</strong>. La aplicación ofrece una interfaz gráfica sencilla (desarrollada en Swing) y la posibilidad de interactuar desde la consola. El proyecto fue diseñado con el objetivo de practicar consultas a una API y el manejo de datos externos.
</p>

<h2>Funcionalidad</h2>
<ul>
  <li><strong>Conversión directa entre monedas específicas:</strong> 
    <ul>
      <li>Dólar (USD) => Peso Argentino (ARS)</li>
      <li>Dólar (USD) => Real Brasileño (BRL)</li>
    </ul>
  </li>
  <li><strong>Opción de ingreso manual</strong> de códigos de monedas para realizar conversiones entre cualquier divisa soportada por la API.</li>
  <li><strong>Registro de conversiones realizadas:</strong> Se almacena un historial de conversiones, incluyendo el momento en que se realizó y las monedas involucradas.</li>
  <li><strong>Interfaz gráfica (GUI)</strong> usando Swing, donde se puede:
    <ul>
      <li>Seleccionar los tipos de moneda.</li>
      <li>Ingresar el monto a convertir.</li>
      <li>Visualizar el resultado de la conversión.</li>
      <li>Consultar el registro de conversiones anteriores.</li>
    </ul>
  </li>
</ul>

<h2>Requerimientos</h2>
<ul>
  <li><strong>Lenguaje:</strong> Java</li>
  <li><strong>Entorno de Desarrollo:</strong> IntelliJ IDEA</li>
  <li><strong>Control de Versiones:</strong> Git y GitBash</li>
  <li><strong>Bibliotecas utilizadas:</strong>
    <ul>
      <li><code>java.net.http.HttpClient</code> para realizar las solicitudes HTTP.</li>
      <li><code>com.google.gson</code> para el procesamiento de respuestas JSON.</li>
      <li><code>javax.swing</code> para la interfaz gráfica.</li>
      <li><code>java.time</code> para la gestión de fechas y horas (marca de tiempo).</li>
    </ul>
  </li>
</ul>

<h3>Requerimientos funcionales</h3>
<ol>
  <li>Realizar consultas a la API de ExchangeRate para obtener las tasas de cambio.</li>
  <li>Manejar errores comunes como códigos de monedas incorrectos o valores numéricos no válidos.</li>
  <li>Registrar las conversiones realizadas, incluyendo el código de las monedas y la fecha/hora.</li>
  <li>Proveer una interfaz gráfica para facilitar la interacción del usuario.</li>
</ol>

<h3>Requerimientos de instalación</h3>
<ul>
  <li>Java Development Kit (JDK) 17 o superior.</li>
  <li>Dependencias necesarias:
    <ul>
      <li>GSON (librería para manejo de JSON).</li>
      <li>Swing (librería estándar de Java para interfaces gráficas).</li>
    </ul>
  </li>
</ul>

<h2>Instrucciones de Instalación</h2>
<ol>
  <li>Clonar el repositorio:
    <pre><code>git clone https://github.com/usuario/conversor-monedas.git</code></pre>
  </li>
  <li>Abrir el proyecto en <strong>IntelliJ IDEA</strong>.</li>
  <li>Instalar las dependencias requeridas mediante el gestor de dependencias de IntelliJ.</li>
  <li>Ejecutar el proyecto directamente desde el entorno de desarrollo o usando la línea de comandos.</li>
</ol>

<h2>Uso</h2>
<h3>Ejecución desde la Consola</h3>
<p>
Una vez ejecutado el programa, aparecerá un menú interactivo donde el usuario puede:
</p>
<ul>
  <li>Seleccionar las monedas entre las que desea convertir.</li>
  <li>Ingresar el monto a convertir.</li>
  <li>Ver el resultado en la consola, incluyendo el monto convertido y la tasa de cambio.</li>
</ul>
<h3>Ejecución desde la Interfaz Gráfica (Swing)</h3>
<p>
La ventana principal del programa ofrece:
</p>
<ul>
  <li>Campos para seleccionar el código de moneda de origen y destino.</li>
  <li>Un campo para ingresar el monto a convertir.</li>
  <li>Botones para enviar la solicitud de conversión y visualizar el resultado.</li>
  <li>Opción para ver el registro de conversiones realizadas.</li>
</ul>

<h2>Capturas de Pantalla</h2>
<h3>Ejemplo 1: Uso en la consola</h3>
<p><em>Captura de pantalla de la interacción en consola, mostrando el menú, la selección de monedas y el resultado de la conversión.</em></p>

![Captura de pantalla 2024-10-10 135643](https://github.com/user-attachments/assets/65d9cbe2-e56f-452f-af0b-7483206cb51e)

![Captura de pantalla 2024-10-10 135740](https://github.com/user-attachments/assets/e9682f35-390e-4e14-84c6-8e92c0248988)

<h3>Ejemplo 2: Uso de la interfaz gráfica (Swing)</h3>
<p><em>Captura de pantalla de la interfaz gráfica, con la selección de monedas, el ingreso del monto, y la visualización del resultado.</em></p>

![Captura de pantalla 2024-10-10 141303](https://github.com/user-attachments/assets/67700ad0-9d1f-4632-9a12-9c8df7fe942a)

![Captura de pantalla 2024-10-10 135915](https://github.com/user-attachments/assets/c590ffd4-93b5-4975-9e4b-cd6da861393e)

![Captura de pantalla 2024-10-10 140702](https://github.com/user-attachments/assets/4b4c040b-a65d-4fd7-93fc-76b76abffa28)


<h2>Principales Clases y Métodos</h2>

<h3><code>SolicitudConversion</code></h3>
<p>
Este es el componente central que realiza la solicitud a la API. Los métodos más importantes son:
</p>
<ul>
  <li><code>obtenerTasaConversion(Moneda origen, Moneda destino, double monto)</code>:
    Realiza una consulta HTTP a la API de ExchangeRate y retorna la tasa de cambio entre las dos monedas especificadas.
  </li>
</ul>

<h3><code>ResultadoConversion</code></h3>
<p>
Clase encargada de almacenar el resultado de una conversión. Contiene el monto convertido y la tasa de cambio obtenida de la API.
</p>

<h3><code>MenuConversor</code></h3>
<p>
Clase que maneja la interacción con el usuario a través de la consola. Ofrece opciones para seleccionar monedas, ingresar montos y realizar la conversión.
</p>

<h3><code>RegistroConversion</code></h3>
<p>
Esta clase almacena la información de cada conversión realizada, incluyendo la marca de tiempo (fecha y hora) de la operación.
</p>

<h3><code>VentanaConversor</code> (Swing)</h3>
<p>
Esta clase crea la interfaz gráfica de la aplicación, donde el usuario puede ingresar los datos necesarios para realizar una conversión y consultar el historial.
</p>

<h2>Manejo de Errores</h2>
<p>
La aplicación maneja diferentes tipos de errores que pueden surgir durante el uso, tales como:
</p>
<ul>
  <li><strong>Códigos de moneda inválidos:</strong> Se valida que los códigos tengan 3 caracteres.</li>
  <li><strong>Montos no válidos:</strong> Se valida que el monto ingresado sea un número positivo.</li>
  <li><strong>Errores de conexión:</strong> Se captura y maneja cualquier error que ocurra al hacer la solicitud a la API.</li>
</ul>

<h2>API Utilizada</h2>
<p>
La aplicación usa la API de <strong>ExchangeRate-API</strong> para obtener las tasas de cambio entre divisas. Se requiere una clave de API válida, la cual debe ser colocada en el archivo <code>SolicitudConversion</code> en el campo correspondiente.
</p>
<p><strong>Documentación de la API:</strong> <a href="https://www.exchangerate-api.com/docs/java-currency-api">https://www.exchangerate-api.com/docs/java-currency-api</a></p>

![Captura de pantalla 2024-10-10 140127](https://github.com/user-attachments/assets/87df2eee-bc10-44fa-8102-acf55629282f)

