package biblioteca.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import biblioteca.*;
import biblioteca.excecoes.*;

public class RepositorioDadosMemoria implements RepositorioDados 
{
	HashMap<String,Assunto> assuntos;
	HashMap<Integer, Agente> agentes;
	HashMap<Integer, Publicacao> pubs;
	
	public RepositorioDadosMemoria()
	{
		this.initAssuntos();
		agentes = new HashMap<Integer, Agente>();
		pubs = new HashMap<Integer, Publicacao>();
	}
	
	public void addAgente(Agente a) throws AgenteDuplicado
	{
		if ((this.findAutor(a.getCodigo()) == null) && (this.findEditor(a.getCodigo()) == null))
		{
			agentes.put(a.getCodigo(), a);
		}
		else
			throw new AgenteDuplicado("Este codigo de agente ja existe. O agente nao pode ser adicionado.");		
	}
	
	public Editor findEditor(int codigo)
	{
		Agente a = agentes.get(codigo);
		if (a instanceof Editor)
			return (Editor) a;
		else
			return null;
	}
	
	public Autor findAutor(int codigo)
	{
		Agente a = agentes.get(codigo);
		if (a instanceof Autor)
			return (Autor) a;
		else
			return null;
	}
	
	public void addPub(Publicacao p) throws PublicacaoDuplicada
	{
		if (this.findPub(p.getCodigo()) == null)
		{
			pubs.put(p.getCodigo(), p);
		}
		else
			throw new PublicacaoDuplicada("Este codigo de publicacao ja existe. A publicacao nao pode ser adicionada.");
	}
	
	public void removeAgente(int codigo) throws AgenteInexistente
	{
		if (agentes.remove(codigo) == null)
		{
			throw new AgenteInexistente("O agnete nao pode ser removido. Nao ha um agente com codigo \"" + codigo + "\".");
		}
	}
	
	public Publicacao findPub(int codigo)
	{
		return pubs.get(codigo);
	}
	
	public void initAssuntos()
	{
		assuntos = new HashMap<String, Assunto>();
		assuntos.put("000", new Assunto("000", "Generalidades"));
		assuntos.put("100", new Assunto("100", "Filosofia"));
		assuntos.put("200", new Assunto("200", "Religião"));
		assuntos.put("300", new Assunto("300", "Ciências Sociais"));
		assuntos.put("400", new Assunto("400", "Línguas"));
		assuntos.put("500", new Assunto("500", "Ciências Naturais"));
		assuntos.put("600", new Assunto("600", "Ciências Aplicadas"));
		assuntos.put("700", new Assunto("700", "Artes"));
		assuntos.put("800", new Assunto("800", "Literatura"));
		assuntos.put("900", new Assunto("900", "História"));
	}
	
	public ArrayList<Publicacao> findPubs(String palavras)
	{
		Collection<Publicacao> c = pubs.values();
		ArrayList<Publicacao> retorno = new ArrayList<Publicacao>();
		
		for (Publicacao p : c)
		{
			if (p.getTitulo().contains(palavras))
			{
				retorno.add(p);
			}
			else if (p instanceof Artigo)
			{
				ArrayList<String> keyWords = ((Artigo)p).getPalavrasChave();
				for (String k : keyWords)
				{
					if (k.contains(palavras))
					{
						retorno.add(p);
					}
				}
			}
		}
		
		if (retorno.size() > 0)
		{
			return retorno;
		}
		else
		{
			return null;
		}
	}
	
	public void removePub(int codigo) throws PublicacaoInexistente
	{
		if (pubs.remove(codigo) == null)
		{
			throw new PublicacaoInexistente("A publicacao nao pode ser removida. Nao ha uma publicacao com codigo \"" + codigo + "\".");
		}
	}
	
	public Assunto findAssunto(String codigo)
	{
		return assuntos.get(codigo);
	}
	
	public int qtdArtigos()
	{
		Collection<Publicacao> c = pubs.values();
		int qtd = 0;
		
		for (Publicacao p : c)
		{
			if (p instanceof Artigo)
			{
				qtd++;
			}
		}
		
		return qtd;
	}
}