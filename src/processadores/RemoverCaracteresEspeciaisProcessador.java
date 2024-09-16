package processadores;

public class RemoverCaracteresEspeciaisProcessador extends ProcessadorBase {

	@Override
	public String processarTexto(String texto) {
		// Susbitutiu tudo que não seja letra ou número por "[CE]"
		return texto.replaceAll("[^0-9a-zA-Z\\n\\s]", "[CE]");
	}
}
