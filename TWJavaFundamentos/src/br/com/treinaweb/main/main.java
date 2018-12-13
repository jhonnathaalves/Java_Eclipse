package br.com.treinaweb.main;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite um numero: ");
		//int numero1 = Integer.parseInt(scanner.nextLine());
		int numero1 = scanner.nextInt();
		//System.out.println(numero1);
		System.out.print("Digite a operação: ");
		char operacao = scanner.next().charAt(0);
		System.out.print("Digite o segundo numero: ");
		int numero2 = scanner.nextInt();
		System.out.println(String.format("Você quer fazer a operação %d %c %d", numero1,operacao,numero2));
		int resultado = 0;
		
		if(operacao == '+') {
			resultado = numero1 + numero2;
		} else if (operacao == '-') {
			resultado = numero1 - numero2;
		} else if (operacao == '*') {
			resultado = numero1 * numero2;
		} else if (operacao == '/') {
			resultado = numero1 / numero2;
		} else {
			System.out.println("Operação Invalida!");
		}
		
		System.out.println(String.format("%d %c %d = %d", numero1, operacao, numero2, resultado ));
		scanner.close();
		
	}

}
