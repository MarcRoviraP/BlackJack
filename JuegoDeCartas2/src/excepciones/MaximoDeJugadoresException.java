package excepciones;

public class MaximoDeJugadoresException extends Exception{

	private int num;

	public MaximoDeJugadoresException(int num) {
		super();
		this.num = num;
	}
	
	public void error() {
		
		System.err.println("El numero " + num + " no es correcto introduce un numero entre 1 y 7");
	}
}
