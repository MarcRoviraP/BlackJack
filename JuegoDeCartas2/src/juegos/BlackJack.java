package juegos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import barajas.*;
import enumerados.*;
import excepciones.*;
import utilidades.*;

public class BlackJack implements Relacionable {

	private static int jugadores;
	private static int numJuador;
	private ArrayList<Jugador> listaJugadores;
	private PrintWriter pw;
	private PrintWriter pwCrupier;

	public BlackJack(int jugadores) {
		super();
		BlackJack.jugadores = jugadores;
		this.listaJugadores = new ArrayList<Jugador>();

	}

	public void Jugar() {
		Scanner sc = new Scanner(System.in);
		String nombre = "";
		String eleccioParar = "s";
		String seguirJugando = "s";
		String opcionComprarFichas = "1";
		int fichas = Integer.MAX_VALUE;
		int comprarFichas = 0;
		boolean valor = true;
		String[] posiciones = { "primer", "segundo", "tercer", "cuarto", "quinto", "sexto", "septimo" };

		Jugador croupier = new Jugador("Croupier");

		for (int i = 0; i < jugadores; i++) {

			valor = true;
			while (valor) {
				System.out.println("Inserta el nombre del " + posiciones[i] + " jugador: ");

				try {

					nombre = sc.nextLine();
					if (nombre.contains("{")) {

						throw new CaracterInvalidoException(nombre);
					}
					if (nombre.length() < 1 || nombre.length() > 42) {

						throw new TamañoIregularExeption();
					}

					valor = false;
				} catch (CaracterInvalidoException e) {
					e.error();
				} catch (TamañoIregularExeption e) {

					e.error();
				}
			}

			Jugador jugador = new Jugador(nombre);
			jugador.setNumJugador(++numJuador);
			this.listaJugadores.add(jugador);

		}

		while (seguirJugando.equals("s")) {
			Baraja b = new Baraja(true);

			croupier.baraja.listaCartas.clear();
			croupier.baraja.listaCartas.add(b.Robar());
			for (Jugador jugador : listaJugadores) {

				valor = true;
				jugador.baraja.listaCartas.clear();
				jugador.setEstat(Plantarse.seguir);
				jugador.setGanar(Ganar.ganado);
				fichas = Integer.MAX_VALUE;

				while (valor) {

					try {
						valor = false;
						System.out.println(
								jugador.getNombre() + " escribe la cantidad de fichas que quieres apostar. Disponible "
										+ jugador.getFichas());

						fichas = sc.nextInt();
						if (fichas < 0) {
							throw new InputMismatchException();
						}
						if (fichas > jugador.getFichas()) {

							throw new NoFichasException(jugador.getNombre());
						}
					} catch (NoFichasException e) {

						valor = true;
						e.imprimirError();

						sc.nextLine();
						while (valor) {
							try {

								opcionComprarFichas = sc.nextLine();

								if (!opcionComprarFichas.equals("1") && !opcionComprarFichas.equals("2")) {

									throw new OpcionIncorrectaException("1", "2");
								}

								valor = false;
							} catch (OpcionIncorrectaException e2) {

								e2.error();
							}

						}

						valor = true;
						if (opcionComprarFichas.equals("2")) {

							valor = false;

							while (!valor) {

								try {
									System.out.println("Inserta el numero de fichas desado.");
									comprarFichas = sc.nextInt();
									if (comprarFichas < 0) {
										throw new InputMismatchException();
									}
									valor = true;

								} catch (InputMismatchException e2) {
									sc.nextLine();
									errorInputMismatchException();

								}
							}
							comprarFichas(jugador, comprarFichas);
						}

					} catch (InputMismatchException e) {

						sc.nextLine();
						valor = true;
						errorInputMismatchException();
					}
				}
				jugador.setFichas(jugador.getFichas() - fichas);
				jugador.setApuesta(fichas);

				jugador.baraja.listaCartas.add(b.Robar());
				jugador.baraja.listaCartas.add(b.Robar());

				if (jugador.baraja.getValorCartesTotal() == 11) {

					guanyarBlackJack(jugador);

				}
			}

			estatPartida(croupier);

			sc.nextLine();
			while (!pararPartida()) {

				for (Jugador jugador : listaJugadores) {

					if (jugador.getEstat().equals(Plantarse.seguir)) {

						valor = true;
						while (valor) {
							try {
								System.out.println(jugador.getNombre() + " quieres seguir o plantarte? (s / p)");

								eleccioParar = sc.nextLine().toLowerCase();

								if (!eleccioParar.equals("s") && !eleccioParar.equals("p")) {
									throw new OpcionIncorrectaException("s", "p");
								}
								valor = false;
							} catch (OpcionIncorrectaException e) {
								e.error();
							}
						}

						jugador.setEstat(eleccioParar.equals("p") ? Plantarse.parar : Plantarse.seguir);

						if (jugador.getEstat().equals(Plantarse.seguir)) {

							jugador.baraja.listaCartas.add(b.Robar());

						}

						if (jugador.baraja.getValorCartesTotal() >= 21) {

							jugador.setEstat(Plantarse.parar);
							jugador.setGanar(Ganar.perdido);
						}

						if (jugador.baraja.getValorCartesTotal() == 21) {

							jugador.setGanar(Ganar.ganado);
						}
					}

				}
				limpiarPantalla();
				estatPartida(croupier);
			}

			while (croupier.baraja.getValorCartesTotal() < 16) {

				croupier.baraja.listaCartas.add(b.Robar());

				if (croupier.baraja.getValorCartesTotal() > 21) {

					croupier.setGanar(Ganar.perdido);

					for (Jugador jugador : listaJugadores) {

						if (jugador.baraja.getValorCartesTotal() < 22) {

							jugador.setGanar(Ganar.ganado);
						}
					}
				} else {

					for (Jugador jugador : listaJugadores) {

						if (!jugador.getGanar().equals(Ganar.perdido)) {

							esMayorQue(croupier, jugador);
							esMenorQue(croupier, jugador);
							esIgualQue(croupier, jugador);
						}

					}
				}
				limpiarPantalla();

				estatPartida(croupier);

				imprimirEstatMentresCroupier(croupier);

				System.out.println("Pulsa intro para siguiente carta . . .");
				sc.nextLine();

			}

			for (Jugador jugador : listaJugadores) {
				// Imprimir nombre y valor de las cartas del jugador
				System.out.print(Colores.PURPURA_CLARO_BOLD + String.format("%-10s", jugador.getNombre()));
				System.out.print(Colores.PURPURA_CLARO_BOLD + " Valor cartas: ");
				System.out.print(Colores.AZUL_CIELO + String.format("%-3d", jugador.baraja.getValorCartesTotal()));

				// Imprimir nombre y valor de las cartas del crupier
				System.out.print(Colores.VERDE_BOLD + String.format("%7s", croupier.getNombre()));
				System.out.print(Colores.VERDE_BOLD + " Valor cartas: ");
				System.out.print(Colores.CYAN + String.format("%-3d", croupier.baraja.getValorCartesTotal()));

				// Imprimir resultado y fichas
				if (jugador.getGanar().equals(Ganar.empate)) {

					System.out.println(Colores.AMARILLO_BOLD + " + 0 fichas empate" + Colores.RESET);
					jugador.setFichas(jugador.getFichas() + jugador.getApuesta());

				} else if (jugador.getGanar().equals(Ganar.ganado)) {

					System.out.println(Colores.VERDE_BOLD + String.format(" + %d fichas victoria", jugador.getApuesta())
					+ Colores.RESET);
					jugador.setFichas(jugador.getFichas() + jugador.getApuesta() * 2);

				} else {

					System.out.println(Colores.ROJO_BOLD + String.format(" - %d fichas derrota", jugador.getApuesta())
					+ Colores.RESET);
				}
			}
			valor = true;
			while (valor) {

				try {

					System.out.println("Quieres seguir jugando? (s / n)");

					seguirJugando = sc.nextLine().toLowerCase();

					if (!seguirJugando.equals("s") && !seguirJugando.equals("n")) {
						throw new OpcionIncorrectaException("s", "n");
					}
					valor = false;
				} catch (OpcionIncorrectaException e) {
					e.error();
				}
			}
		}

		System.out.println(Colores.AZUL_CIELO + "HASTA LA PROXIMA !!!");

		sc.close();
	}

	public void estatPartida(Jugador crupier) {

		try {
			this.pw = new PrintWriter(new File("src/ficheros/infoJugador.txt"));
			this.pwCrupier = new PrintWriter(new File("src/ficheros/infoCrupier.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Jugador jugador : listaJugadores) {

			jugador.imprimir(pw);
		}
		pw.close();

		crupier.imprimir(pwCrupier);
		pwCrupier.close();
		Baraja.imprimirBaraja();
		System.out.println(Colores.RESET);
	}

	public boolean pararPartida() {
		boolean parar = true;

		for (Jugador jugador : listaJugadores) {

			parar = jugador.getEstat().equals(Plantarse.parar) ? true : false;

			if (!parar)
				break;
		}
		return parar;
	}

	public void imprimirEstatMentresCroupier(Jugador crupier) {

		System.out.println();

		System.out.println("----------------------------------------------------------");
		for (Jugador jugador : listaJugadores) {

			// Cambiar color
			if (jugador.getGanar().equals(Ganar.empate)) {

				System.out.print(Colores.NARANJA + jugador.getNombre() + " esta empatando al crupier ");

			} else if (jugador.getGanar().equals(Ganar.ganado)) {

				System.out.print(Colores.VERDE_LIMA + jugador.getNombre() + " esta ganando al crupier ");

			} else if (jugador.getGanar().equals(Ganar.perdido)) {
				System.out.print(Colores.ROJO_TORINO + jugador.getNombre() + " esta perdiendo ");

			}

			System.out.print(jugador.getNombre() + ": " + jugador.baraja.getValorCartesTotal() + " Croupier: "
					+ crupier.baraja.getValorCartesTotal());

			System.out.println(Colores.RESET);
		}
		System.out.println("----------------------------------------------------------");
		System.out.println();
	}

	@Override
	public void esMayorQue(Jugador crupier, Jugador jugador) {

		if (crupier.baraja.getValorCartesTotal() > jugador.baraja.getValorCartesTotal()) {

			jugador.setGanar(Ganar.perdido);
		}
	}

	@Override
	public void esMenorQue(Jugador crupier, Jugador jugador) {
		if (crupier.baraja.getValorCartesTotal() < jugador.baraja.getValorCartesTotal()) {

			jugador.setGanar(Ganar.ganado);
		}

	}

	@Override
	public void esIgualQue(Jugador crupier, Jugador jugador) {
		if (crupier.baraja.getValorCartesTotal() == jugador.baraja.getValorCartesTotal()) {

			jugador.setGanar(Ganar.empate);
		}

	}

	public void comprarFichas(Jugador j, int fichas) {

		j.setFichas(j.getFichas() + fichas);
	}

	public void errorInputMismatchException() {

		System.err.println("Error introduce un numero entero.");
	}

	public void limpiarPantalla() {

		for (int i = 0; i < 50; i++) {

			System.out.println();
		}
	}

	public static int getJugadores() {
		return jugadores;
	}

	public void guanyarBlackJack(Jugador j) {

		for (Carta carta : j.baraja.listaCartas) {

			if (carta.getValor() == 1) {

				j.baraja.setValorCartesTotal(21);
				j.setEstat(Plantarse.parar);
			}
		}

	}

}
