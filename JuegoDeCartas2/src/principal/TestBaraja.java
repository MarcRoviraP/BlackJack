package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import utilidades.Colores;

public class TestBaraja {

	public static void main(String[] args) {
		String ruta = "src/ficheros/";
		int bereaker = 18;
		int cont = 1;
		String[] colores = {Colores.AMARILLO_CLARO_BOLD,Colores.AZUL_CLARO_BOLD,Colores.NARANJA,Colores.PURPURA_CLARO_BOLD,Colores.ROJO_BOLD,Colores.VERDE_CLARO_BOLD,Colores.BLANCO_BRILLANTE_BOLD,Colores.AZUL_FRANCIA};
		String vl[];
		// Definir constantes significativas
		final int TOTAL_JUGADORES = 2;
		final int LINEAS_POR_JUGADOR = 9;

		try {
		    for (int m = 0; m < TOTAL_JUGADORES / 2; m++) {
		        for (int i = 0; i < LINEAS_POR_JUGADOR; i++) {
		            Scanner sc = new Scanner(new File(ruta + "infoCrupier.txt"));
		            int contador = 0;
		            int limite = bereaker * m;
		            
		            for (int j = 0; j < limite; j++) {
		                sc.nextLine(); // Ignorar líneas hasta el límite
		            }
		            
		            String lineaFormateada = "";
		            while (sc.hasNext()) {
		                for (int j = 0; j < i; j++) {
		                    sc.nextLine();
		                    contador++;
		                }

		                String linea = sc.nextLine();
		                contador++;

		                    lineaFormateada += String.format("%-100s{ ", linea);
		                   

		                for (int j = 0; j < 8 - i; j++) {
		                    sc.nextLine();
		                    contador++;
		                }

		                // Salir del bucle si se alcanza el límite
		                if (contador % bereaker == 0) {
		                    break;
		                }
		            }
//		            lineaFormateada = lineaFormateada.substring(0,lineaFormateada.length()-2);
		            vl = lineaFormateada.split("\\{");
		            System.out.println(colores[--cont] + vl[0] + colores[++cont] + vl[1]);
		        }
		        cont += 2;
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace(); // Imprimir el mensaje de error
		}
		
		
	}
}
