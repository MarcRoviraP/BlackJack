package barajas;

public class Carta {

	private int numero;
	private int palo;
	private int valor;
	private static int cont;

	public Carta(int numero, int palo) {

		this.numero = numero;
		this.palo = palo;

	}

	public Carta(int id) {

		this.numero = restar(id);
		this.palo = cont;
		cont = 0;
	}

	public int Numero() {

		return this.numero;
	}

	public int Palo() {

		return this.palo;
	}

	public String NombreNumero() {

		String v[] = { "", "as", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez","jota","reina","rey"};

		return v[this.numero];
	}

	public String NombreCarta() {

		String v[] = { "", "corazones", "diamantes", "picas", "treboles" };

		return v[this.palo];
	}

	@Override
	public String toString() {

		return String.format("%-6s de %s", NombreNumero(),NombreCarta());
				}

	public int devuelvePosCaracter(char c, String nombre) {

		return nombre.indexOf(c);
	}

	public int getNumero() {
		return numero;
	}

	public int getPalo() {
		return palo;
	}

	public static int restar(int num) {
		if (num < 1) {
			return num + 13;
		}else {
			
			cont++;
			return restar(num - 13);
		}
		
	}
	
	public int getValor() {
		this.valor = this.getNumero();
		
		if (this.getNumero() > 10 && this.getNumero() < 14) {
			this.valor = 10;
		}
		return this.valor;
	}
	
	public void setValor(int numero) {
		
		this.valor += numero;
		
	}

}
