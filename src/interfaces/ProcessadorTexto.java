package interfaces;

public interface ProcessadorTexto {
	String processar(String texto);
	void setNext(ProcessadorTexto processador);
}
