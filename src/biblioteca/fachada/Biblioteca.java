package biblioteca.fachada;

import java.util.ArrayList;

import biblioteca.*;
import biblioteca.excecoes.*;
import biblioteca.io.RepositorioDadosMemoria;

public class Biblioteca 
{
	private RepositorioDadosMemoria repDados;

	private static Biblioteca instancia;
	
	public static Biblioteca getInstance()
	{
		if (instancia == null)
		{
			instancia = new Biblioteca();
		}		
		return instancia;
	}
	
	private Biblioteca()
	{
		repDados = new RepositorioDadosMemoria();		
	}	
	
	public void addAgente(Agente a) throws AgenteDuplicado
	{
		repDados.addAgente(a);
	}
	
	public Autor findAutor(int codigo)
	{
		return repDados.findAutor(codigo);
	}
	
	public Editor findEditor(int codigo)
	{
		return repDados.findEditor(codigo);
	}
	
	public void addPub(Publicacao p) throws PublicacaoDuplicada
	{
		repDados.addPub(p);
	}
	
	public void removePub(int codigo) throws PublicacaoInexistente
	{
		repDados.removePub(codigo);
	}
	
	public void removeAgente(int codigo) throws AgenteInexistente
	{
		repDados.removeAgente(codigo);
	}
	
	public Publicacao findPub(int codigo)
	{
		return repDados.findPub(codigo);
	}
	
	public ArrayList<Publicacao> findPubs(String palavras)
	{
		return repDados.findPubs(palavras);
	}
	
	public Assunto findAssunto(String codigo)
	{
		return repDados.findAssunto(codigo);
	}
	
	public int qtdArtigos()
	{
		return repDados.qtdArtigos();
	}
}
