
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static Scanner entrada;
	private static String chave;
	private static String textoClaro;

	static boolean verifica(char x, char [][] matriz) {
		
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j ++) {
				if(x == matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
		
	public static  char [][]matriz() {
		
		boolean result;
		String abc = "abcdefghijklmnopqrstuvxyz";
		int cont = chave.length();
		int k = 0;
		int l = 0;
		
		char[][] matriz = new char[5][5];
		
		for (int i = 0; i < 5; i ++) {
			//System.out.println("");
			for (int j = 0; j < 5; j ++) {
				if(cont > 0) {
					result = verifica(chave.charAt(k),matriz);
					while (result == true && k < chave.length()-1) {
						k ++;
						cont --;
						result = verifica(chave.charAt(k),matriz);
					}	
					if (result == false) {
						matriz [i][j] =  chave.charAt(k);
						k++;
						cont --;
					}else {
						result = verifica(abc.charAt(l),matriz);
						while (result == true && l < abc.length()-1) {
							l++;
							result = verifica(abc.charAt(l),matriz);
						}
						if (result == false) {
							matriz [i][j] = abc.charAt(l);
							l ++;
						}
						cont --;
					}
				}else{
					result = verifica(abc.charAt(l),matriz);
					while (result == true && l < abc.length()-1) {
						l++;
						result = verifica(abc.charAt(l),matriz);
					}
					if (result == false) {
						matriz [i][j] = abc.charAt(l);
						l ++;
					}
				}
			}
		}
		return matriz;
	}
	
	static  ArrayList<String> separaTexto(String textoClaro) {
		ArrayList<String> pares = new ArrayList<>() ;
		String par = "";
		
		for (int i = 0; i < textoClaro.length(); i ++) { 
			par = par + textoClaro.substring(i, i+1);	
			if(par.length() == 2) {
				if ((par.substring(0, 1)).equals(par.substring(1, 2))) {
					if (par.substring(1, 2) .equals ("x")) {
						par = par.substring(0, 1) + "z";	
					}else {
						par = par.substring(0, 1) + "x";
					}
					i--;
				}
				pares.add(par);
				par = "";
			}else if (i+1 == textoClaro.length() && par.length() != 2) {
				if (par.substring(0, 1) .equals ("x")) {
					par = textoClaro.substring(i, i+1 ) + "w";	
				}else {
					par = textoClaro.substring(i, i+1 ) + "x";
				}
				pares.add(par);
			}	
		}
		return pares;
	}
	
	 static int [] buscar(char x,char [][]matriz) {
		int []posicao = new int[2];
		 for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j ++) {
				if(x == matriz[i][j]) {
					posicao[0] = i;
					posicao[1] = j;
					return posicao;
				}
			}
		}
		 return null;
	}
	 
	static String cifraTexto() {
		String textoCifrado = "";
		ArrayList<String> pares = separaTexto(textoClaro);
		int []p_letra0 = new int[2];
		int []p_letra1 = new int[2];
		char [][] matriz = matriz();
		char letra0,letra1;
		
		for (int i = 0; i < pares.size();i++) {
			letra0 = pares.get(i).charAt(0);
			letra1 = pares.get(i).charAt(1);
			p_letra0 = buscar(letra0,matriz);
			p_letra1 = buscar(letra1,matriz);
			if(p_letra0[0] == p_letra1[0]){
				if(p_letra0[1] < 4) {
					textoCifrado += matriz[p_letra0 [0]][p_letra0[1]+1];
				}else {
					textoCifrado += matriz[p_letra0 [0]][0];
				}
				if(p_letra1[1] < 4) {
					textoCifrado += matriz[p_letra1 [0]][p_letra1[1]+1];
				}else {
					textoCifrado += matriz[p_letra1 [0]][0];
				}
			}else if(buscar(letra0,matriz)[1] == buscar(letra1,matriz)[1]){
				if(p_letra0[0] < 4) {
					textoCifrado += matriz[p_letra0 [0]+1][p_letra0[1]];
				}else {
					textoCifrado += matriz[0][p_letra0 [0]];
				}
				if(p_letra1[0] < 4) {
					textoCifrado += matriz[p_letra1 [0]+1][p_letra1[1]];
				}else {
					textoCifrado += matriz[0][p_letra1 [0]];
				}		
			}else {
				textoCifrado += matriz[p_letra0 [0]][p_letra1[1]];
				textoCifrado += matriz[p_letra1 [0]][p_letra0[1]];
			}	
		}
		return textoCifrado;
	}
	
	static void decifraTexto() {
	
		String textoDecifrado = "";
		String cifra = cifraTexto();
		int []p_letra0 = new int[2];
		int []p_letra1 = new int[2];
		char [][] matriz = matriz();
		char letra0,letra1;
		String texto = "";
		
		for (int i = 0; i < cifra.length();i++) {
			letra0 = cifra.charAt(i);
			letra1 = cifra.charAt(i+1);
			p_letra0 = buscar(letra0,matriz);
			p_letra1 = buscar(letra1,matriz);
			if(p_letra0[0] == p_letra1[0]){
				if(p_letra0[1] > 0) {
					textoDecifrado += matriz[p_letra0 [0]][p_letra0[1]-1];
				}else {
					textoDecifrado += matriz[p_letra0 [0]][4];
				}
				if(p_letra1[1] > 0) {
					textoDecifrado += matriz[p_letra1 [0]][p_letra1[1]-1];
				}else {
					textoDecifrado += matriz[p_letra1 [0]][4];
				}
			}else if(buscar(letra0,matriz)[1] == buscar(letra1,matriz)[1]){
				if(p_letra0[0] > 0) {
					textoDecifrado += matriz[p_letra0 [0]-1][p_letra0[1]];
				}else {
					textoDecifrado += matriz[4][p_letra0 [0]];
				}
				if(p_letra1[0] > 0) {
					textoDecifrado += matriz[p_letra1 [0]-1][p_letra1[1]];
				}else {
					textoDecifrado += matriz[4][p_letra1 [0]];
				}
			}else {
				textoDecifrado += matriz[p_letra0 [0]][p_letra1[1]];
				textoDecifrado += matriz[p_letra1 [0]][p_letra0[1]];
			}
			i++;
		}
		for (int n = 1;n < textoDecifrado.length()-1;n++) {
			if (n==1) {
				texto += textoDecifrado.charAt(n-1);
			}
			if(textoDecifrado.charAt(n) == 'x' || textoDecifrado.charAt(n) == 'z') {
				if(textoDecifrado.charAt(n-1) == textoDecifrado.charAt(n+1)) {	
				}else {
					texto += textoDecifrado.charAt(n);
				}
			}else {
				texto += textoDecifrado.charAt(n);
			}
		}
		System.out.println(texto);
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		entrada = new Scanner(System.in);
		System.out.println("Digite a chave:");
		chave = entrada.nextLine();
		System.out.println("-----------------------------------------------");
		System.out.println("Digite o texto:");
		textoClaro = entrada.nextLine();
		System.out.println("-----------------------------------------------");
		System.out.println("Texto cifrado:");
		System.out.println(cifraTexto());
		System.out.println("-----------------------------------------------");
		System.out.println("Texto decifrado:");
		decifraTexto();
	}
}
