package br.com.consultaCPF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ConsultaCPF {

	public static void main(String[] args) {
		List<String> cpfs = new ArrayList<String>();
		try {
			FileReader lerCPF = new FileReader("cpf.txt");
			BufferedReader leitura = new BufferedReader(lerCPF);
			try {
				String linha = "";
				while (linha != null) {
					linha = leitura.readLine();
					cpfs.add(linha);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			boolean teste = verificaValidade(cpfs);
			if (teste == true) {
				System.out.println("Verificação Encerrada!!");
				System.exit(0);
			}
		}

	}

	public static Boolean verificaValidade(List<String> cpfs) {
		int[] numeroCPF = new int[11];
		int validos = 0, invalidos = 0, total = 0;

		for (String cpf : cpfs) {
			String numero = cpf;
			if (cpf != null) {
				for (int i = 0; i < 11; i++) {
					String n = Character.toString(numero.charAt(i));
					int CPFnumber = converteNumber(n);
					numeroCPF[i] = CPFnumber;
				}
				boolean valida = validaCPF(numeroCPF);
				if (valida == true) {
					validos++;
				} else {
					invalidos++;
				}
				total++;
			}
		}
		System.out.println(validos + " CPFS VÁLIDOS!");
		System.out.println(invalidos + " CPFS Inválidos!");
		System.out.println(total + " CPFS Verificados!!!");
		return true;
	}

	// lógica de validação do CPF
	public static Boolean validaCPF(int[] CPF) {
		int soma = 0, digito = 0, numero = 0;
		for (int i = 0; i < 9; i++) {
			numero = CPF[i] * (10 - i);
			soma += numero;
		}
		digito = verificaDigito(soma);
		if (digito == CPF[9]) {
			soma = 0;
			for (int i = 0; i < 10; i++) {
				soma += CPF[i] * (11 - i);
			}
			digito = verificaDigito(soma);
			if (digito == CPF[10]) {
				return true;
			}
		}
		return false;
	}

	// converte String em inteiro
	public static Integer converteNumber(String numero) {
		return Integer.parseInt(numero);
	}

	// lógica do digito
	public static Integer verificaDigito(int soma) {
		int digito = soma % 11;
		if (digito < 2) {
			digito = 0;
		} else {
			digito = 11 - digito;
		}
		return digito;
	}
}
