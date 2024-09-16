package processadores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RemoverPalavrasOfensivasProcessador extends ProcessadorBase {

	private String pathPalavrasOfensivas;

	public RemoverPalavrasOfensivasProcessador() {
		this.pathPalavrasOfensivas = "palavras_ofensivas.txt";
	}

	public RemoverPalavrasOfensivasProcessador(String pathPalavrasOfensivas) {
		this.pathPalavrasOfensivas = pathPalavrasOfensivas;
	}

	public String getPathPalavrasOfensivas() {
		return pathPalavrasOfensivas;
	}

	public void setPathPalavrasOfensivas(String pathPalavrasOfensivas) {
		this.pathPalavrasOfensivas = pathPalavrasOfensivas;
	}

	@Override
	public String processarTexto(String texto) {
		String palavrasOfensivas = lerArquivo(pathPalavrasOfensivas);
		String[] vetorPalavras = palavrasOfensivas.split("\\n");

		for (String palavra : vetorPalavras) {
			texto = texto.replace(palavra.trim(), "[REMOVIDO]");
		}
		return texto;
	}

	private String lerArquivo(String pathPalavrasOfensivas) {
		StringBuilder conteudo = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(pathPalavrasOfensivas))) {
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

}
