package ejerciciospractica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Crea una función que calcule y retorne cuántos días hay entre dos cadenas
 * de texto que representen fechas.
 * - Una cadena de texto que representa una fecha tiene el formato "dd/MM/yyyy".
 * - La función recibirá dos String y retornará un Int.
 * - La diferencia en días será absoluta (no importa el orden de las fechas).
 * - Si una de las dos cadenas de texto no representa una fecha correcta se
 *   lanzará una excepción.
 */
public class EjercicioFechas {

	/**
	 * Este atributo controla el formato que se usa en las fechas. El formato es:
	 * "dd/MM/yyyy". Ejemplo: "10/06/2023".
	 */
	public static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Función que se encargará de calcular la diferencia de días entre dos fechas dadas
	 * (Sin importar el orden).
	 * @param fecha1 La primera fecha que se introducirá como String
	 * @param fecha2 Segunda fecha introducida como String
	 * @return La diferencia de días entre las dos fechas
	 */
	public static int calcularDiferenciaFechas(String fecha1, String fecha2) {
		
		/* ACLARACIÓN: No controlamos la excepción porque el ejercicio especifica
		 * explícitamente que si no logra calcular la fecha, que el programa
		 * lance dicha excepción. */
		
		// Creamos el entero que contendrá el valor de la diferencia de los días.
		int resultadoDias = 0;
		
		/* Creamos un objeto de tipo LocalDate (Que representa una fecha en Java usando
	       el paquete de java.time, un nuevo paquete introducido en la versión 8 de Java)
	       con la fecha que se le pasa como parámetro a la función. Lo haremos así con las
	       dos fechas para luego usar un enum del paquete java.time que se encargará de
	       calcular la diferencia de días entre los dos objetos de tipo LocalDate. */
		LocalDate fechaObj1 = LocalDate.parse(fecha1, FECHA_FORMATO);
		LocalDate fechaObj2 = LocalDate.parse(fecha2, FECHA_FORMATO);
		
		/* Asignamos a resultadoDias los días de diferencias entre las fechas usando
		 * el enum ChronoUnit. Lo casteamos a int porque la función del enum que se
		 * encarga de calcular la diferencia de días pide que el valor que devuelva
		 * sea un long. El ejercicio pide que el valor sea de tipo int, por eso se
		 * castea a un int. */
		resultadoDias = (int) ChronoUnit.DAYS.between(fechaObj1, fechaObj2);	
		
		/* Si en el caso de que el valor que devuelva sea negativo (Esto sucede cuando
		 * la fecha primaria es menor que la fecha secundaria), simplemente lo
		 * pasamos a positivo multiplicándolo por -1 en el caso de que el valor
		 * sea negativo. */
		if(resultadoDias < 0) {
			resultadoDias *= -1;
		}
		
		return resultadoDias;
	}
	
	public static void main(String[] args) {
		
		// Pedimos los valores de las fechas al usuario.
		String fechaPrimaria = GetValue.getString("Introduzca una fecha con el formato: \"dd/MM/yyyy\".");
		String fechaSecundaria = GetValue.getString("Introduzca otra fecha con el formato: \"dd/MM/yyyy\".");
		
		// Calculamos la diferencia de días.
		int diferenciaDias = calcularDiferenciaFechas(fechaPrimaria, fechaSecundaria);
		
		// Y mostramos el resultado final.
		System.out.println("La diferencias de días entre " + fechaPrimaria + " y " + fechaSecundaria + " es de: " +
		diferenciaDias + " días.");

	}

}
