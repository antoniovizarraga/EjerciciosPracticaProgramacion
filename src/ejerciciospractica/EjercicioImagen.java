package ejerciciospractica;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Este ejercicio tratará de sacar el aspect ratio o relación de aspecto de una
 * imagen dada como URL.
 */
public class EjercicioImagen {

	/**
	 * Función que se encargará de descargar una imagen en la carpeta del ejercicio
	 * actual para luego poder analizarla y saber su relación de aspecto.
	 * 
	 * @param enlace El enlace desde el cual descargaremos la imagen. El enlace debe
	 *               acabar con un: ".jpg", o: ".png", ".webp"... Etc para que la
	 *               función funcione.
	 * 
	 * @return Devuelve un booleano indicando si se pudo realizar la operación o no.
	 */
	public static boolean descargarImagen(String enlace) {

		// Creamos el booleano que devolveremos.
		boolean transaccion = true;

		// Con el try-catch, hacemos lo siguiente:
		try {

			// Creamos un objeto (Que actúa como un puntero que apunta a un enlace) de tipo
			// URL.
			URL url = new URL(enlace);

			// Creamos o abrimos un stream de datos para crear un archivo.
			InputStream is = url.openStream();

			// Creamos la salida del archivo, especificando su directorio de salida y su
			// nombre.
			FileOutputStream fo = new FileOutputStream("./src/ejerciciospractica/output.jpg");

			// Creamos un entero que representará los bytes de la imagen, e irá leyendo
			// todos sus bits hasta que llegue al final, momento en el cual se saldrá
			// del bucle.
			int b = 0;

			// Lee todos los bytes de la imagen hasta escribirlos en el Stream de salida.
			while ((b = is.read()) != -1) {
				fo.write(b);
			}

			// Cerramos las corrientes de datos.
			fo.close();
			is.close();

			// Capturamos las diferentes excepciones que pudieran surgir.
		} catch (MalformedURLException e) {
			transaccion = false;
		}

		catch (IOException e) {
			transaccion = false;
		}

		// Devolvemos el estado de la función, indicando si funcionó o no con un
		// boolean.
		return transaccion;
	}

	/**
	 * Función que calcula si un número está en un rango de un mínimo y un máximo.
	 * 
	 * @param x      El número que comprobaremos si está en el rango o no.
	 * @param minimo El valor mínimo del rango
	 * @param maximo El valor máximo del rango
	 * @return Devuelve true o false ya que lo único que nos interesa saber es si el
	 *         número está en el rango o no.
	 */
	public static boolean rango(double x, double minimo, double maximo) {
		return minimo <= x && x <= maximo;
	}

	/**
	 * Función que calcula la relación del aspecto de la imagen.
	 * 
	 * @return Devuelve un String con el formato: "x:y" (Donde la X es el ancho de
	 *         la imagen y la Y la altura) indicando la relación de aspecto de la
	 *         imagen.
	 * @throws IOException Lanza esta excepción si la función detecta un error de
	 *                     entrada/salida a la hora de leer la imagen.
	 */
	public static String calcularRelacionAspecto() throws IOException {

		// Creamos el String donde contendrá la relación de aspecto de la imagen.
		String relacionAspecto = "";

		// Leemos el archivo que hemos creado de la imagen.
		File archivo = new File("./src/ejerciciospractica/output.jpg");

		// Cargamos la imagen en memoria.
		BufferedImage imagen = ImageIO.read(archivo);

		// Obtenemos la anchura y la altura de la imagen.
		double anchura = imagen.getWidth();
		double altura = imagen.getHeight();

		// Obtenemos el número que representa las distintas relaciones
		// de aspecto que puede tener una imagen.
		double numeroRelacion = anchura / altura;

		// Truncamos el número a dos decimales.
		double resultado = Math.floor(numeroRelacion * 100.0) / 100.0;

		/*
		 * Vamos comprobando si el número entra en alguno de los rangos que se
		 * especifican más abajo. Si entra en uno de ellos, establece el valor
		 * correspondiente de su relación de aspecto en el String de relacionAspecto
		 * para devolverlo.
		 * 
		 * Este código podría estar mejor optimizado, pero debido a que
		 * los switch sólo funcionan con valores enteros, cadenas o enums,
		 * me vi obligado a hacerlo individualmente con una función que
		 * compruebe si un valor está en un rango de valores dados. Por
		 * lo que busqué en Stack Overflow y después de ir buscando
		 * varias soluciones, leyendo el código de todas ellas e ir
		 * contemplando cuál sería la mejor solución, llegué a esta
		 * conclusión. Quizás también este código se podría extraer
		 * en otra función distinta más pequeña para así minimizar
		 * o economizar el código.
		 * 
		 */
		if (rango(resultado, 0, 1)) {
			relacionAspecto = "1:1";
		} else if (rango(resultado, 1.1, 1.25))  {
			relacionAspecto = "5:4";
		} else if (rango(resultado, 1.26, 1.33)) {
			relacionAspecto = "4:3";
		} else if (rango(resultado, 1.34, 1.5)) {
			relacionAspecto = "3:2";
		} else if (rango(resultado, 1.51, 1.55)) {
			relacionAspecto = "14:9";
		} else if (rango(resultado, 1.56, 1.6)) {
			relacionAspecto = "16:10";
		} else if (rango(resultado, 1.61, 1.77)) {
			relacionAspecto = "16:9";
		} else if (rango(resultado, 1.78, 1.85)) {
			relacionAspecto = "37:20";
		} else if (rango(resultado, 1.86, 2)) {
			relacionAspecto = "18:9";
		} else if (rango(resultado, 2.1, 2.16)) {
			relacionAspecto = "13:6 o 19.5:9";
		}

		return relacionAspecto;
	}

	public static void main(String[] args) throws IOException {
		
		// Pedimos al usuario el enlace de la imagen.
		String imageURL = GetValue.getString("Introduzca la URL de la imagen que desea saber su relación de aspecto: ");

		// Creamos el booleano para saber si se pudo indicar la imagen, y pedimos al
		// usuario el enlace de la imagen.
		boolean imagen = descargarImagen(imageURL);

		// Si se pudo descargar la imagen, indíquelo al usuario.
		if (imagen) {
			System.out.println("Imagen descargada correctamente.");
		} else {
			System.out.println("La imagen no pudo ser descargada.");
		}

		// Calculamos la relacion de aspecto.
		String relacionAspecto = calcularRelacionAspecto();

		// Imprimimos el resultado final.
		System.out.println("La relación de aspecto de la imagen es de: " + relacionAspecto);

		// Cerramos el Scanner.
		GetValue.SCANNER.close();

	}

}
