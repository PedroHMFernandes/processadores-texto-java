package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import interfaces.ProcessadorTexto;
import processadores.RemoverCaracteresEspeciaisProcessador;
import processadores.RemoverEspacosProcessador;
import processadores.RemoverPalavrasOfensivasProcessador;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Entrada do nome do arquivo de texto
		System.out.println("Digite o nome do arquivo de entrada (com extensão .txt):");
		String arquivoEntrada = scanner.nextLine();

		// Leitura do arquivo de entrada
		String texto = lerArquivo(arquivoEntrada);
		if (texto == null) {
			System.err.println("Erro ao ler o arquivo de entrada.");
			scanner.close();
			return;
		}

		// Criação e encadeamento dos processadores
		ProcessadorTexto processador = new RemoverEspacosProcessador();
		processador.setNext(new RemoverCaracteresEspeciaisProcessador());
		processador.setNext(new RemoverPalavrasOfensivasProcessador());
		
		// Processar o texto
		String textoProcessado = processador.processar(texto);
		
		// Gerar arquivo de saída com o texto processado
		String nomeArquivoSaida = gerarNomeArquivoSaida(arquivoEntrada);
		escreverArquivo(nomeArquivoSaida, textoProcessado);

		System.out.println("Processamento concluído. Arquivo gerado: " + nomeArquivoSaida);

		scanner.close();
	}

	// Função para ler o conteúdo de um arquivo de texto
	private static String lerArquivo(String nomeArquivo) {
		StringBuilder conteudo = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				conteudo.append(linha).append(System.lineSeparator());
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
			return null;
		}
		return conteudo.toString();
	}

	// Função para escrever o conteúdo processado em um arquivo de saída
	private static void escreverArquivo(String nomeArquivo, String conteudo) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
			bw.write(conteudo);
		} catch (IOException e) {
			System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
		}
	}

	// Função para gerar o nome do arquivo de saída com o sufixo "_processado"
	private static String gerarNomeArquivoSaida(String nomeArquivoEntrada) {
		int pontoIndex = nomeArquivoEntrada.lastIndexOf('.');
		if (pontoIndex > 0) {
			return nomeArquivoEntrada.substring(0, pontoIndex) + "_processado.txt";
		} else {
			return nomeArquivoEntrada + "_processado.txt";
		}
	}
}