package processadores;

import interfaces.ProcessadorTexto;

public class RemoverCaracteresEspeciaisProcessador implements ProcessadorTexto {

	@Override
	public String processar(String texto) {
		// Susbitutiu tudo que não seja letra ou número por "[CE]"
		return texto.replaceAll("[^0-9a-zA-Z\\n\\s]", "[CE]");
	}
}
