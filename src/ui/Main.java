package ui;

import java.util.Scanner;

public class Main {
	private Scanner numScan = new Scanner(System.in);
	public static void main (String[] args){
		Main m=new Main();
		m.menu();
	}	
	public void menu() {
		boolean typeMode = true;
		boolean run = true;
		System.out.println(
				"******************************\n"+
				"***********AIR-PORT***********\n"
			+   "******************************\n");
		while (run) {
			System.out.println("**************************************************\n" + "1. Grafo representado por matriz de adjacencia \n"
					+ "2. Grafo representado por lista de adjacencia \n" 
				+ "**************************************************\n");
			int eleccion = numScan.nextInt();

			switch (eleccion) {
			case 1:
				break;

			case 2:

				break;
			}
		}
	}
}
