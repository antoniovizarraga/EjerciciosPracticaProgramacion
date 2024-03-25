package ejerciciospractica;

import java.util.Scanner;
import java.util.InputMismatchException;

/* We're going to define a class to use it in all of the exercises. The idea behind this
 * is that we'll be asking the user to write a value and return it following the try-catch
 * method and the do-while loop. We will be making functions for 3 data types:
 * Strings, integers, and doubles. Which they are the main ones we actually
 * use in class. */
public class GetValue {

	public static final Scanner SCANNER = new Scanner(System.in);

	// We start making the Integer function.
	public static int getInteger(String textToShowToTheUser) {

		// We create the Scanner object.
		// We create the variable which we'll be using for
		// returning it later and also will be the
		// condition on the do-while loop.
		int returnValue = 0;

		// We make the do-while loop first, and then the
		// try-catch code block.
		do {
			try {
				// Ask the user for a value
				System.out.println(textToShowToTheUser);

				// And we open the Scanner
				returnValue = SCANNER.nextInt();

				// If the value throws a InputMismatchException error,
			} catch (InputMismatchException e) {
				// Clean the Scanner buffer just in case
				SCANNER.nextLine();

				// Print the error in the terminal
				System.err.println("ERROR: Has introducido un valor que no es un número entero. Vuelve a intentarlo.");
			}

			// And we're going to make this any time until the user writes the correct
			// value.
		} while (returnValue == 0);


		// And finally, return the value.
		return returnValue;
	}

	
	// This is just the same function as before, but with Doubles.
	public static double getDouble(String textToShowToTheUser) {
		double returnValue = 0;

		do {
			try {
				System.out.println(textToShowToTheUser);
				returnValue = SCANNER.nextDouble();

			} catch (InputMismatchException e) {
				SCANNER.nextLine();
				System.err.println("ERROR: Has introducido un valor que no es un número entero. Vuelve a intentarlo.");
			}

		} while (returnValue == 0);


		return returnValue;
	}

	/*
	 * And the same for Strings. Though this is different because Strings just
	 * accepts any character as a String, so the InputMismatchException here is
	 * useless.
	 */
	public static String getString(String textToShowToTheUser) {
		String returnValue = "";

		System.out.println(textToShowToTheUser);
		returnValue = SCANNER.nextLine();


		return returnValue;
	}

}
