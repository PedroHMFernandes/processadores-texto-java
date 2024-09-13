package processadores;

import interfaces.ProcessadorTexto;

public class RemoverEspacosProcessador implements ProcessadorTexto {

	@Override
	public String processar(String texto) {
		// replaceAll(" {2,}", " ") - substitui, onde há 2 ou mais espaços, por apenas um espaço
		// trim() - remove espaços em branco nas extremidades
		return texto.replaceAll(" {2,}", " ").trim();

	}
}