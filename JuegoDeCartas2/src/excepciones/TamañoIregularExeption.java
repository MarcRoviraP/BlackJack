package excepciones;

public class TamañoIregularExeption extends Exception{


	public TamañoIregularExeption() {
	}
	
	public void error() {
		
		System.err.println("Inserta un nombre entre 1 - 42 caracteres");
	}
	
}
