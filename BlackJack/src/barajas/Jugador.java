package barajas;

import java.io.PrintWriter;

import enumerados.Ganar;
import enumerados.Plantarse;

public class Jugador {

	public Baraja baraja;
	private String nombre;
	private int fichas;
	private int apuesta;
	private int numJugador;
	private Plantarse estat;
	private Ganar ganar;

	public Jugador(String nombre) {
		this.nombre = nombre;
		this.baraja = new Baraja("a");
		this.estat = Plantarse.seguir;
		this.ganar = Ganar.ganado;
		this.fichas = 1000;
	}

	public int getFichas() {
		return fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
	}

	public void imprimir(PrintWriter pw) {

		//

		pw.printf("%-8s%-10s%-8s%-10s%-9s%-10s%n", "Nombre: ", this.nombre, " Fichas: ", this.fiches(),
				" Apuesta: ", this.apuesta());
		pw.println("--------------------------------------------------------------------------------");

		baraja.guardarFichero(pw);
		pw.println();

		//

	}

	public Plantarse getEstat() {
		return estat;
	}

	public void setEstat(Plantarse estat) {
		this.estat = estat;
	}

	public String getNombre() {
		return nombre;
	}

	public Ganar getGanar() {
		return ganar;
	}

	public void setGanar(Ganar ganar) {
		this.ganar = ganar;
	}

	public String fiches() {

		return this.nombre.equals("Croupier") ? "∞" : this.fichas + "";

	}

	public String apuesta() {

		return this.nombre.equals("Croupier") ? "∞" : this.apuesta + "";

	}

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public int getNumJugador() {
		return numJugador;
	}

	public void setNumJugador(int numJugador) {
		this.numJugador = numJugador;
	}

}
