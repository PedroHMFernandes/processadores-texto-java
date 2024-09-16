package processadores;

public class RemoverEspacosProcessador extends ProcessadorBase {

	@Override
	public String processarTexto(String texto) {
		// replaceAll(" {2,}", " ") - substitui, onde há 2 ou mais espaços, por apenas um espaço
		// trim() - remove espaços em branco nas extremidades
		return texto.replaceAll(" {2,}", " ").trim();

	}
}
