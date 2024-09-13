package processadores;

import interfaces.ProcessadorTexto;

public class RemoverCaracteresEspeciaisProcessador implements ProcessadorTexto {

<<<<<<< HEAD
	@Override
	public String processar(String texto) {
		return texto.replaceAll("[^0-9a-zA-Z\\n\\s]", "[CE]");
	}
}
 
=======
}
>>>>>>> 7beb2c06f7aaba768485df349148f31f5f402992
