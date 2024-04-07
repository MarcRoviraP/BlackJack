package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import excepciones.MaximoDeJugadoresException;
import excepciones.MenuNoValidoException;
import juegos.BlackJack;
import utilidades.Colores;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		boolean valor = true;
		int min = 1, max = 2, opcio = 0;
		menu();

		while (valor) {

			try {
				valor = false;
				opcio = sc.nextInt();

				if (opcio < min || opcio > max) {

					throw new MenuNoValidoException(opcio, min, max);
				}

			} catch (InputMismatchException e) {

				valor = true;
				error();

			} catch (MenuNoValidoException e) {
				valor = true;

				e.error();
			}
			sc.nextLine();

		}
		switch (opcio) {

		case 1:

			blackjack(sc);
			break;

		case 2:
			reglas(sc);
			break;
		}

		sc.close();
	}

	public static void reglas(Scanner sc) {
		
		

		System.out.println("                                                                                                                                                                 \n"
				+ "                                                                                                                                                                 \n"
				+ "                                                                                                                                                                 \n"
				+ "                                                       ┌────────────────────────────────────────────────────────────────────────────────────────────────┐\n"
				+ "                   				       │										        	│\n"
				+ "                                                       │	El Objetivo: El objetivo del juego es obtener una mano con un valor total lo		│                 \n"
				+ "                                                       │	más cercano posible a 21 sin pasarse.							│                    \n"
				+ "                                                       │												│                    \n"
				+ "                                                       │	El Valor de las Cartas: Las cartas numéricas (2-10) tienen el valor de su número. 	│                    \n"
				+ "                                                       │	Las cartas de figuras (J, Q, K) valen cada una 10 puntos. El As vale 1. En caso de que	│                    \n"
				+ "                                                       │	salga As en las primeras dos cartas y la otra carta tenga un valor de 10 su valor sera	│                    \n"
				+ "                                                       │	de 11 y automaticamente el valor de la baraja ascendera a 21.				│                    \n"
				+ "                    AAAAAA                             │												│                    \n"
				+ "                   AAAAAAAA                            │	La Partida: El juego comienza con cada jugador recibiendo dos cartas boca arriba. 	│                    \n"
				+ "                  AAAAAAAAA                            │	El crupier recibe una carta boca arriba y otra boca abajo.				│                    \n"
				+ "                  AAAAAAAAA       AA                   │												│                    \n"
				+ "                   AAAAAAAA    0AA                     │	Opciones del Jugador: Después de recibir sus dos cartas, el jugador puede optar por 	│                    \n"
				+ "                     AAAA    AAA                       │	\"pedir\" (recibir otra carta), \"plantarse\" (quedarse con las cartas actuales).		│                    \n"
				+ "                     IAA   AAA                         │												│                    \n"
				+ "                    AAAAAAA                            │	La Decisión del Crupier: Una vez que todos los jugadores han tomado sus decisiones, 	│                    \n"
				+ "                  AAAAAAA                              │	el crupier revela su carta boca abajo. El crupier debe \"pedir\" cartas hasta que 	│                    \n"
				+ "                  A1AAAAAA                             │	tenga un total de al menos 16.								│                    \n"
				+ "                 A  AAAAAA                             │												│                    \n"
				+ "                A   AAAAAr                             │	Determinar al Ganador: Si la mano del jugador es más cercana a 21 que la del crupier,	│                    \n"
				+ "               AA   AAAAA                              │	el jugador gana y recibe un pago igual a su apuesta. Si la mano del crupier es más 	│                    \n"
				+ "               A    AAAAA                              │	cercana a 21 o si el jugador se pasa de 21, el crupier gana y el jugador pierde su 	│                    \n"
				+ "                    A   A                              │	apuesta. En caso de empate, se devuelve la apuesta al jugador. 				│                    \n"
				+ "                    A   A                              │												│                    \n"
				+ "                    A   A                              │												│                    \n"
				+ "                    A   A                              └────────────────────────────────────────────────────────────────────────────────────────────────┘                    \n"
				+ "                    A   A                                           \n"
				+ "                    A   A                                              \n"
				+ "                                                                                                                                                                 \n"
				+ "                                                                                                                                                                 \n"
				+ "                                                                                                                                                                 \n"
				+ "                                                                                                                                                                 ");
		System.out.println();
		System.out.println("Pulsa intro para jugar . . .");

		sc.nextLine();

		blackjack(sc);

	}

	public static void menu() {

		// ANSI color codes for gradient effect
		String[] colors = { Colores.ROJO_BOLD, Colores.ROJO_CLARO_BOLD, Colores.AMARILLO_CLARO_BOLD,
				Colores.AMARILLO_BOLD, Colores.VERDE_BOLD, Colores.PURPURA_BOLD };

		// Menu header with gradient effect
		System.out.println("\n" + colors[0]
				+ " /$$$$$$$  /$$                     /$$                /$$$$$                     /$$      \n"
				+ colors[1]
				+ "| $$__  $$| $$                    | $$               |__  $$                    | $$      \n"
				+ colors[2]
				+ "| $$  \\ $$| $$  /$$$$$$   /$$$$$$$| $$   /$$            | $$  /$$$$$$   /$$$$$$$| $$   /$$\n"
				+ colors[1]
				+ "| $$$$$$$ | $$ |____  $$ /$$_____/| $$  /$$/            | $$ |____  $$ /$$_____/| $$  /$$/\n"
				+ colors[0]
				+ "| $$__  $$| $$  /$$$$$$$| $$      | $$$$$$/        /$$  | $$  /$$$$$$$| $$      | $$$$$$/ \n"
				+ colors[1]
				+ "| $$  \\ $$| $$ /$$__  $$| $$      | $$_  $$       | $$  | $$ /$$__  $$| $$      | $$_  $$ \n"
				+ colors[2]
				+ "| $$$$$$$/| $$|  $$$$$$$|  $$$$$$$| $$ \\  $$      |  $$$$$$/|  $$$$$$$|  $$$$$$$| $$ \\  $$\n"
				+ colors[3]
				+ "|_______/ |__/ \\_______/ \\_______/|__/  \\__/       \\______/  \\_______/ \\_______/|__/  \\__/\n"
				+ "");

		// Menu footer with gradient effect
		System.out.println("\n" + Colores.VERDE_BOLD + "  ___                  __                              \n"
				+ " <  /    ____      __ / / __ __  ___ _ ___ _  ____     \n" + Colores.VERDE_ESMERALDA
				+ " / /  _ /___/     / // / / // / / _ `// _ `/ / __/     \n"
				+ "/_/  (_)          \\___/  \\_,_/  \\_, / \\_,_/ /_/        \n"
				+ "                               /___/                   \n" + Colores.AZUL_CLARO_BOLD
				+ "   ___                 ___                __           \n"
				+ "  |_  |    ____       / _ \\ ___   ___ _  / / ___ _  ___\n"
				+ " / __/  _ /___/      / , _// -_) / _ `/ / / / _ `/ (_-<\n" + Colores.AZUL_CIELO
				+ "/____/ (_)          /_/|_| \\__/  \\_, / /_/  \\_,_/ /___/\n"
				+ "                                /___/                  \n" + "");

		System.out.print(Colores.RESET);
		
		
	}

	public static void blackjack(Scanner sc) {

		int jugadores = 0;
		boolean valor = true;

		System.out.println("Inserta el numero de jugadores: (1-7)");

		while (valor) {

			try {
				valor = false;

				jugadores = sc.nextInt();

				if (jugadores < 1 || jugadores > 7) {

					throw new MaximoDeJugadoresException(jugadores);
				}

			} catch (InputMismatchException e) {
				valor = true;
				error();

			} catch (MaximoDeJugadoresException e) {
				valor = true;
				e.error();
			}
			sc.nextLine();
		}

		BlackJack b = new BlackJack(jugadores);

		b.Jugar();

	}

	public static void error() {

		System.err.println("Introduce un numero valido");

	}
}
