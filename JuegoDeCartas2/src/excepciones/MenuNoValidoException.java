package excepciones;

public class MenuNoValidoException extends Exception {

	private int num;
	private int min;
	private int max;

	public MenuNoValidoException(int num,int min,int max) {
		this.num = num;
		this.min = min;
		this.max = max;
	}
	
	public void error() {
		
		System.err.println("El numero " + num + " no esta entre " + min + " - " + max );
	}
}
