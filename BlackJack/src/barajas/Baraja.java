package barajas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import juegos.BlackJack;
import utilidades.Colores;

public class Baraja {

	public ArrayList<Carta> listaCartas;
	private int valorCartesTotal;

	public Baraja() {

		this(true);
	}

	public Baraja(String r) {

		this.listaCartas = new ArrayList<Carta>();
	}

	public Baraja(boolean barajar) {

		this.listaCartas = new ArrayList<Carta>();

		crearBaraja();

		if (barajar) {
			Barajar();
		}
	}

	public void crearBaraja() {
		this.listaCartas.clear();

		Carta carta;

		for (int i = 1; i <= 52; i++) {

			carta = new Carta(i);

			this.listaCartas.add(carta);
		}

	}

	public int NumeroCartas() {

		return this.listaCartas.size();
	}

	public boolean Vacio() {

		return this.listaCartas.isEmpty();
	}

	public void Barajar() {
		ArrayList<Carta> listaSecundaria = new ArrayList<Carta>();
		int random;

		while (!this.listaCartas.isEmpty()) {

			random = (int) (Math.random() * this.listaCartas.size());

			listaSecundaria.add(this.listaCartas.get(random));
			this.listaCartas.remove(random);
		}

		this.listaCartas = new ArrayList<Carta>(listaSecundaria);
	}

	public void Cortar(int posicion) {
		Carta carta;

		for (int i = 0; i < posicion; i++) {

			carta = this.listaCartas.get(0);
			this.listaCartas.remove(0);
			this.listaCartas.add(carta);
		}
	}

	public Carta Robar() {
		Carta carta;

		carta = this.listaCartas.get(0);
		this.listaCartas.remove(0);

		return carta;

	}

	public void InsertaCartaAlFinal(int id_carta) {

		Carta carta = new Carta(id_carta);

		this.listaCartas.add(carta);
	}

	public void InsertaCartaAlFinal(Carta carta) {

		this.listaCartas.add(carta);
	}

	public void InsertaCartaAlPrincipio(int id_carta) {

		Carta carta = new Carta(id_carta);

		this.listaCartas.add(0, carta);
	}

	public void InsertaCartaAlPrincipio(Carta carta) {

		this.listaCartas.add(0, carta);
	}

	public int getValorCartesTotal() {

		if (valorCartesTotal == 21) {

		} else {

			this.valorCartesTotal = 0;

			for (Carta carta : listaCartas) {

				this.valorCartesTotal += carta.getValor();
			}
		}
		return this.valorCartesTotal;

	}

	public void setValorCartesTotal(int n) {

		this.valorCartesTotal = n;
	}

	public void imprimirBarajaTexto() {
		int c = 0;

		for (Carta carta : this.listaCartas) {

			System.out.printf("%0,2d - %-12s %d%n", ++c, carta.toString(), carta.getValor());
		}
	}

	public void guardarFichero(PrintWriter pw) {

		ArrayList<String> listaImprimirCartas = new ArrayList<String>();

		for (Carta carta : listaCartas) {

			listaImprimirCartas.add(dibujarCarta(carta.getNumero(), carta.getPalo()));
		}
		imprimirBaraja(listaImprimirCartas, pw);

	}

	public static void imprimirBaraja() {

		imprimirBaraja("infoJugador.txt");
		imprimirBaraja("infoCrupier.txt");
	}

	public static void imprimirBaraja(String nom) {
		String ruta = "src/ficheros/" + nom;
		int total = BlackJack.getJugadores();
		String[] colores = { Colores.AMARILLO_CLARO_BOLD, Colores.AZUL_CLARO_BOLD, Colores.NARANJA,
				Colores.PURPURA_CLARO_BOLD, Colores.ROJO_BOLD, Colores.VERDE_CLARO_BOLD, Colores.BLANCO_BRILLANTE_BOLD,
				Colores.AZUL_FRANCIA };
		int bereaker = 18;
		int cont = 1;
		String vl[];
		final int LINEAS_POR_JUGADOR = 9;
		int contador;
		int limite;
		String lineaFormateada;
		String linea;

		if (nom.equals("infoCrupier.txt")) {
			total = 1;
			colores[0] = Colores.ROSA;
		}
		total += total % 2 == 0 ? 0 : 1;
		try {

			for (int m = 0; m < total / 2; m++) {
		        for (int i = 0; i < LINEAS_POR_JUGADOR; i++) {
					Scanner sc = new Scanner(new File(ruta));

					contador = 0;
					limite = bereaker * m;
					for (int j = 0; j < limite; j++) {
						// Start
						sc.nextLine();
					}
					//
					lineaFormateada = "";

					while (sc.hasNext()) {

						for (int j = 0; j < i; j++) {

							sc.nextLine();
							contador++;
						}

						linea = sc.nextLine();
						contador++;

						lineaFormateada += String.format("%-100s{ ", linea);

						for (int j = 0; j < 8 - i; j++) {
							sc.nextLine();
							contador++;
						}

						// Salir del bucle si se alcanza el límite
						if (contador % bereaker == 0)
							break;

					}
					vl = lineaFormateada.split("\\{");
					System.out.println(colores[--cont] + vl[0] + colores[++cont] + vl[1]);
				}

				cont += 2;

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}

	}

	public String dibujarCarta(int valor, int palo) {
		String[] valores = { "", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		String[] palos = { "", "♠", "♥", "♦", "♣" };

		String figura = valores[valor];
		String color = palos[palo];

		StringBuilder sb = new StringBuilder();
		sb.append("+-----+\n");
		sb.append(String.format("|%2s   |\n", figura));
		sb.append("|  " + color + "  |\n");
		sb.append("|     |\n");
		sb.append(String.format("|   %-2s|\n", figura));
		sb.append("+-----+\n");

		return sb.toString();
	}

	public void imprimirBaraja(ArrayList<String> listaCartas, PrintWriter pw) {

		int numFilas = 6; // Número de filas por carta
		for (int fila = 0; fila < numFilas; fila++) { // Iterar sobre cada fila de las cartas
			for (String carta : listaCartas) { // Iterar sobre cada carta en la lista
				String[] lineasCarta = carta.split("\n"); // Dividir la carta en sus líneas individuales
				pw.print(lineasCarta[fila] + "   "); // Imprimir la línea correspondiente de la carta
			}
			pw.println(); // Salto de línea después de imprimir todas las cartas en una fila
		}

	}

}
