package processadores;

import interfaces.ProcessadorTexto;

public abstract class ProcessadorBase implements ProcessadorTexto {
	private ProcessadorTexto next;

	public void setNext(ProcessadorTexto processador) {
		if (next == null) {
			next = processador; // Se for o primeiro na cadeia
		} else {
			next.setNext(processador);
		}
	}

	@Override
	public String processar(String texto) {
		String textoProcessado = processarTexto(texto);
		if (next != null) {
			return next.processar(textoProcessado);
		}
		return textoProcessado;
	}

	protected abstract String processarTexto(String texto);
}
