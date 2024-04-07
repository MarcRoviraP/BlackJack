package excepciones;

public class NoFichasException extends Exception {

	private String nombre;

	public NoFichasException(String nombre) {

		this.nombre = nombre;
	}
	
	public void imprimirError() {
		
		System.err.println(nombre + " no tiene suficientes fichas para pagar. Ingresa una cantidad mas pequeña o compra mas fichas.");
		
		System.out.println("1.- Ingresar otra cantidad.");
		System.out.println("2.- Comprar fichas.");
	}
	
	
}
