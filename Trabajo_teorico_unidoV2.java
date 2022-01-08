package Paquete;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Trabajo_teorico_unido {

	static int[][] matriz;

	public static void main(String[] args) {
		int opcion = 0;
		int dimensiones = 0;
		/*
		 * Llama al metodo "filtrarEscritura" para que el usuario escriba las
		 * dimensiones de la matriz siguiendo el criterio [MxM].
		 */
		do {
			System.out.println("Escriba las dimensiones de la matriz que quiera crear:");
			dimensiones = filtrarEscritura();
			if (dimensiones <= 0) {
				System.out.println("No se pueden crear matrices con números iguales o inferiores a 0.");
			}
		} while (dimensiones <= 0);
		matriz = crearMatriz(dimensiones);
		rellenarMatriz();
		/*
		 * Muestra el menú con las distintas opciones que tiene el usuario. Llama al
		 * metodo 'filtrarEscritura' para que el usuario escriba por teclado la opción
		 * que quiera realizar.
		 */
		do {
			System.out.println("Menú:\n" + "1. Mostrar las sumas de todas las diagonales.\n"
					+ "2. Mostrar la suma de una sola diagonal.\n" + "3. Mostrar la matriz M.\n"
					+ "4. Mostrar todas las diagonales.\n" + "5. Finalizar el programa.\n"
					+ "Escriba el número de la opción que desea realizar:");
			opcion = filtrarEscritura();
			switch (opcion) {
			case 1:
				mostrarSumasDiagonales();
				break;
			case 2:
				pedirDiagonal();
				break;
			case 3:
				mostrarMatriz();
				break;
			case 4:
				recorrerDiagonales();
				break;
			case 5:
				System.out.println("Programa finalizado.");
				break;
			default:
				System.out.println("Error. Vuelva a escribir la opción otra vez\n");
				break;
			}
		} while (opcion != 5);
	}

	/**
	 * Recoge los datos guardados en un fichero llamado numeros.txt y los integra en
	 * la matriz
	 */
	private static void rellenarMatriz() {
		String archivo = "numeros.txt";
		Scanner lectura = null;
		try {
			lectura = new Scanner(new File(archivo));
			for (int fl = 0; fl < matriz.length && lectura.hasNextInt(); fl++) {
				for (int cl = 0; cl < matriz[fl].length && lectura.hasNextInt(); cl++) {
					matriz[fl][cl] = lectura.nextInt();
				}
			}
			lectura.close();
		} catch (IOException err) {
			System.err.println("Error de E/S " + err);
		}
	}

	/**
	 * Genera una matriz con las dimensiones generadas a partir de lo escrito por el
	 * usuario.
	 */
	private static int[][] crearMatriz(int dimensiones) {
		int[][] matrizgenerica = new int[dimensiones][dimensiones];
		return matrizgenerica;
	}

	/**
	 * Recoge los datos escritos por teclado por el usuario. En el caso que sea un
	 * número, devuelve el valor. Si son letras o carácteres especiales muestra un
	 * mensaje de error y hasta que el usuario escriba un número.
	 */
	public static int filtrarEscritura() {
		int numero = 0;
		Scanner lectura = new Scanner(System.in);
		try {
			numero = lectura.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("Solo puede escribir números. Escribe uno a continuación:");
			numero = filtrarEscritura();
		}
		return numero;

	}

	/**
	 * Recorre las diagonales de la matriz Empezando por la diagonal mayor, bajando
	 * por las filas. Despues recorre las colunas
	 */
	private static void mostrarSumasDiagonales() {
		int diagonal = 1;
		for (int i = 0; i < matriz.length; i++)
			System.out.println("la suma de la diagonal " + diagonal++ + " vale " + mostrarSumaDiagonal(i, 0));
		for (int i = 1; i < matriz[0].length; i++)
			System.out.println("la suma de la diagonal " + diagonal++ + " vale " + mostrarSumaDiagonal(0, i));
	}

	/**
	 * Devuelve el resultado del sumatorio de todos los elementos de una diagonal
	 * hacia la derecha
	 * 
	 * @param fila    posición vertical del punto de inicio
	 * @param columna posición horizontal del punto de inicio
	 * @return
	 */
	private static int mostrarSumaDiagonal(int fila, int columna) {
		int resultado = 0;
		while (fila < matriz.length && columna < matriz.length)
			resultado += matriz[fila++][columna++];
		return resultado;
	}

	/**
	 * Pide al usuario que indique cual es la fila y columna de la matriz de la cual
	 * desea obtener el sumatorio de todos los elementos de la diagonal
	 */
	private static void pedirDiagonal() {
		System.out.println("Dime la fila de la diagonal");
		int fila = filtrarEscritura();
		System.out.println("Dime la columna de la diagonal");
		int columna = filtrarEscritura();
		while (fila != 0 && columna != 0 || fila >= matriz.length || columna >= matriz.length) {
			System.out.println("La fila o la columna deben ser \"0\"\n"
					+ "Ninguo de los valores puede ser mayor o igual que la dimensión de la matriz \"" + matriz.length
					+ "\"");
			System.out.println("Dime la fila de la diagonal");
			fila = filtrarEscritura();
			System.out.println("Dime la columna de la diagonal");
			columna = filtrarEscritura();
		}
		System.out.println(mostrarSumaDiagonal(fila, columna));
	}

	/**
	 * Muestra la matriz mediante un bucle for
	 */
	private static void mostrarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++)
				System.out.println(matriz[i][j] + " ");
		}
		System.out.println();
	}

	/**
	 * Muestra la posición inicial y los valores de la diagonal hacia la derecha
	 */
	private static void recorrerDiagonales() {
		for (int i = 0; i < matriz.length; i++) {// obtención de la posición de las filas
			System.out.print("Posición: " + i + ",0: ");
			elementosDiagonal(i, 0);
		}
		for (int i = 1; i < matriz.length; i++) {// obtención de la posición de las columnas
			System.out.print("Posición: 0," + i + ": ");
			elementosDiagonal(0, i);

		}
	}

	/**
	 * Muestra los elementos de las diagonales de la matriz
	 * 
	 * @param filas    representa la fila en la que nos posicionamos
	 * @param columnas representa la columna en la que nos posicionamos
	 */
	private static void elementosDiagonal(int filas, int columnas) {
		while (filas < matriz.length && columnas < matriz.length) {
			System.out.print(matriz[filas][columnas] + " ");
			filas++;
			columnas++;
		}
		System.out.println();
	}
}
