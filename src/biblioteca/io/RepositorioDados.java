package biblioteca.io;

import java.util.ArrayList;
import biblioteca.*;
import biblioteca.excecoes.*;

public interface RepositorioDados 
{	
	public void addAgente(Agente a) throws AgenteDuplicado;
	
	public Editor findEditor(int codigo);
	
	public Autor findAutor(int codigo);
	
	public Assunto findAssunto(String codigo);
	
	public void addPub(Publicacao p) throws PublicacaoDuplicada;
	
	public void removeAgente(int codigo) throws AgenteInexistente;
	
	public void removePub(int codigo) throws PublicacaoInexistente;
	
	public Publicacao findPub(int codigo);
	
	public void initAssuntos();
	
	public ArrayList<Publicacao> findPubs(String palavras);	
}
