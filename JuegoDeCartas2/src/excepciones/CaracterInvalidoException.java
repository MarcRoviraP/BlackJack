package excepciones;

public class CaracterInvalidoException extends Exception{

	String palabra;
	
	public CaracterInvalidoException(String palabra) {

		this.palabra = palabra;
	}
	
	
	public void error () {
		
		System.err.println("Has insertado un caracter invalido en " + palabra + ".Caracter invalido: {");
		
	}
}
