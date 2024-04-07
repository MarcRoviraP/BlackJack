package excepciones;

public class OpcionIncorrectaException extends Exception{

	private String op1;
	private String op2;
	public OpcionIncorrectaException(String op1, String op2) {
		super();
		this.op1 = op1;
		this.op2 = op2;
	}
	
	public void error() {
		
		System.err.println("Inserta una opcion valida " + op1 + " o " + op2 + ".");
	}
}
