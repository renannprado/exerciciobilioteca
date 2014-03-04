import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import biblioteca.*;
import biblioteca.excecoes.*;
import biblioteca.fachada.Biblioteca;

public class Main 
{
	static private Scanner scan = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Biblioteca.getInstance();
		int op = 0;
		
		do
		{
			op = Menu();
			
			switch (op)
			{
				case 1: 
				{
					adicionarPublicacao();
					break;
				}
				case 2: 
				{
					adicionarAutor();
					break;
				}
				case 3: 
				{
					adicionarEditor();
					break;
				}
				case 4:
				{
					procurarPublicacao();
					break;
				}
				case 5:
				{
					procurarAutor();
					break;
				}
				case 6:
				{
					procurarEditor();
					break;
				}
				case 7:
				{
					removerAgente();
					break;
				}
				case 8:
				{
					removerPublicacao();
					break;
				}
				default: 
				{
					out.println("Digite uma opcao valida.");
					break;
				}
			}
		}while(op != 7);
	}
	
	static private int Menu()
	{
		int op;		
		
		do
		{
			out.println("Biblioteca V0.1");
			out.println("1 - Adicionar publicacao.");
			out.println("2 - Adicionar autor.");
			out.println("3 - Adicionar editor.");
			out.println("4 - Procurar publicacao.");
			out.println("5 - Procurar autor.");
			out.println("6 - Procurar editor.");
			out.println("7 - Remover agente.");
			out.println("8 - Remover publicacao.");
			out.println("9 - Sair.");
			out.print("Escolha sua opcao: ");
			
			op = scan.nextInt();
			
		}while(op > 9 && op < 1);

		return op;
	}
	
	private static void adicionarPublicacao()
	{
		int op;
		scan = new Scanner(System.in);
		
		do
		{
			out.println("1 - Adicionar ARTIGO.");
			out.println("2 - Adicionar LIVRO.");
			out.println("3 - Adicionar REVISTA.");
			out.println("4 - Voltar.");
			out.print("Escolha sua opcao: ");
			
			op = scan.nextInt();
			
			switch (op)
			{
				case 1:
				{
					adicionarArtigo();
					break;
				}
				case 2:
				{
					adicionarLivro();
					break;
				}
				case 3:
				{
					if (Biblioteca.getInstance().qtdArtigos() == 0)
					{
						scan = new Scanner(System.in);
						String resp;
						out.println("Nao ha nenhum artigo cadastrado. Voce nao podera adicionar uma revista.\n Deseja cadastrar artigos? (S/N)");
						resp = scan.nextLine();
						if (resp.toUpperCase().equals("S"))
						{
							adicionarArtigo();
							if (Biblioteca.getInstance().qtdArtigos() == 0)
							{
								out.println("Nao ha nenhum artigo cadastrado. Voce nao podera adicionar uma revista.");
							}
							else
							{
								adicionarRevista();
							}
						}
					}
					else
						adicionarRevista();
					break;
				}
				default:
				{
					out.println("Digite uma opcao valida.\n");
					break;
				}
			}
		}while(op != 4);
	}
	
	private static void adicionarAutor()
	{
		scan = new Scanner(System.in);
	
		String nome;
		String sobrenome;
		int codigoAutor;
		
		out.println("\n!AUTOR!");
		out.print("Nome........: ");
		nome = scan.nextLine();
		out.print("Sobrenome...: ");
		sobrenome = scan.nextLine();
		
		out.print("Codigo Autor: ");
		codigoAutor = scan.nextInt();
		
		try
		{
			Biblioteca.getInstance().addAgente(new Autor(nome, sobrenome, codigoAutor));
		}
		catch (AgenteDuplicado ex)
		{
			out.println(ex.Message());
			return;
		}	
	}
	
	private static void adicionarEditor()
	{
		scan = new Scanner(System.in);
	
		String nome;
		int codigoEditor;
		
		out.println("\n!EDITOR!");
		out.print("Nome.........: ");
		nome = scan.nextLine();
		
		out.print("Codigo Editor: ");
		codigoEditor = scan.nextInt();
		
		try
		{
			Biblioteca.getInstance().addAgente(new Editor(nome, codigoEditor));
		}
		catch (AgenteDuplicado ex)
		{
			out.println(ex.Message());
			return;
		}	
	}
	
	private static void adicionarLivro()
	{
		Assunto as = null;
		Editor e = null;
		ArrayList<Autor> listaAutor = null;
		String titulo;
		String codigosAutor;
		Date data = new Date();
		int codigoLivro;
		 
		scan = new Scanner(System.in);
		
		out.println("\n!LIVRO!");
		out.print("Titulo......................................: ");
		titulo = scan.nextLine();
		
		out.print("Data de publicacao (formato: dd/mm/aaaa)....: ");
		String sData[] = scan.nextLine().split("/");
		try
		{
			data.setDate(Integer.parseInt(sData[0]));
			data.setMonth(Integer.parseInt(sData[1]));
			data.setYear(Integer.parseInt(sData[2]));
		}
		catch (Exception ex)
		{
			out.println("Alguma coisa esta errada com a data, tente novamente.");
			return;
		}	
		
		out.print("Codigo Autor [codigos separados por virgula]: ");
		codigosAutor = scan.nextLine();
		String arrayCodAutor[] = codigosAutor.split(",");
		for (int i = 0; i < arrayCodAutor.length; i++)
		{
			Autor a = Biblioteca.getInstance().findAutor(Integer.parseInt(arrayCodAutor[i]));
			if (a != null)
			{
				if (listaAutor == null)
					listaAutor = new ArrayList<Autor>();
				listaAutor.add(a);					
			}
			else 
			{
				out.println("Editor com codigo " + arrayCodAutor[i] + " nao existe.");
			}
		}
		if (listaAutor == null)
		{
			out.println("O livro nao pode ser adicionado. Digite codigos de autores validos.");
			return;
		}
		
		out.print("Codigo Assunto...............................: ");
		as = Biblioteca.getInstance().findAssunto(scan.nextLine());
		if (as == null)
		{
			out.println("O livro nao pode ser adicionado. Digite um codigo de assunto valido.");
			return;
		}		
		
		out.print("Codigo Livro................................: ");
		codigoLivro = scan.nextInt();	
		
		out.print("Codigo Editor...............................: ");
		e = Biblioteca.getInstance().findEditor(scan.nextInt());
		if (e == null)
		{
			out.println("O livro nao pode ser adicionado. Digite um codigo de editor valido.");
			return;
		}
		
		try
		{
			Biblioteca.getInstance().addPub(new Livro(titulo, e, data, as, listaAutor, codigoLivro));
			out.println("O livro foi adicionado com sucesso.");
		}
		catch (PublicacaoDuplicada ex)
		{
			out.println(ex.Message());
			return;
		}
	}
	
	private static void adicionarArtigo()
	{
		Editor e = null;
		ArrayList<Autor> listaAutor = null;
		ArrayList<String> listaPalChave = new ArrayList<String>();
		String titulo;
		String codigosAutor;
		Date data = new Date();
		int codigoArtigo;
		
		scan = new Scanner(System.in);
		
		out.println("\n!ARTIGO!");
		
		out.print("Titulo..............................................: ");
		titulo = scan.nextLine();
		
		out.print("Data de publicacao (formato: dd/mm/aaaa)............: ");
		String sData[] = scan.nextLine().split("/");
		try
		{
			data.setDate(Integer.parseInt(sData[0]));
			data.setMonth(Integer.parseInt(sData[1]));
			data.setYear(Integer.parseInt(sData[2]));
		}
		catch (Exception ex)
		{
			out.println("Alguma coisa esta errada com a data, tente novamente.");
			return;
		}	
		
		out.print("Palavras chave para o artigo [separadas por virgula]: ");
		String palavrasChave[] = scan.nextLine().split(",");
		for (int i = 0; i < palavrasChave.length; i++)
		{
			listaPalChave.add(palavrasChave[i]);
		}	
		
		out.print("Codigo Autor [separados por virgula]................: ");
		codigosAutor = scan.nextLine();
		String arrayCodAutor[] = codigosAutor.split(",");
		for (int i = 0; i < arrayCodAutor.length; i++)
		{
			Autor a = Biblioteca.getInstance().findAutor(Integer.parseInt(arrayCodAutor[i]));
			if (a != null)
			{
				if (listaAutor == null)
					listaAutor = new ArrayList<Autor>();
				listaAutor.add(a);					
			}
			else 
			{
				out.println("Autor com codigo " + arrayCodAutor[i] + " nao existe.");
			}
		}
		if (listaAutor == null)
		{
			out.println("O artigo nao pode ser adicionado. Digite codigos de autores validos.");
			return;
		}	
		
		out.print("Codigo Editor.......................................: ");
		e = Biblioteca.getInstance().findEditor(scan.nextInt());
		if (e == null)
		{
			out.println("O artigo nao pode ser adicionado. Digite um codigo de editor valido.");
			return;
		}
		
		out.print("Codigo Artigo................................: ");
		codigoArtigo = scan.nextInt();	
		
		try
		{
			Biblioteca.getInstance().addPub(new Artigo(titulo, e, data, listaAutor, listaPalChave, codigoArtigo));
			out.println("O livro foi adicionado com sucesso.");
		}
		catch (PublicacaoDuplicada ex)
		{
			out.println(ex.Message());
			return;
		}
	}	
	
	private static void adicionarRevista()
	{
		Editor e = null;
		ArrayList<Artigo> artigos = null;
		String titulo;
		Date data = new Date();
		int codigoRevista;
		
		scan = new Scanner(System.in);
		
		out.println("\n!REVISTA!");
		out.print("Titulo......................................: ");
		titulo = scan.nextLine();
		
		out.print("Data de publicacao (formato: dd/mm/aaaa)....: ");
		String sData[] = scan.nextLine().split("/");
		try
		{
			data.setDate(Integer.parseInt(sData[0]));
			data.setMonth(Integer.parseInt(sData[1]));
			data.setYear(Integer.parseInt(sData[2]));
		}
		catch (Exception ex)
		{
			out.println("Alguma coisa esta errada com a data, tente novamente.");
			return;
		}	
		
		out.print("Codigo Artigos [codigos separados por virgula]: ");
		String arrayCodArtigo[] = scan.nextLine().split(",");
		for (int i = 0; i < arrayCodArtigo.length; i++)
		{
			Publicacao a = Biblioteca.getInstance().findPub(Integer.parseInt(arrayCodArtigo[i]));
			if (a instanceof Artigo)
			{
				if (artigos == null)
					artigos = new ArrayList<Artigo>();
				artigos.add((Artigo)a);					
			}
			else 
			{
				out.println("Artigo com codigo " + arrayCodArtigo[i] + " nao existe ou nao e codigo de artigo.");
			}
		}
		if (artigos == null)
		{
			out.println("A revista nao pode ser adicionada. Digite codigos de artigos validos.");
			return;
		}
		
		out.print("Codigo Editor...............................: ");
		e = Biblioteca.getInstance().findEditor(scan.nextInt());
		if (e == null)
		{
			out.println("A revista nao pode ser adicionada. Digite um codigo de editor valido.");
			return;
		}
		
		out.print("Codigo Revista..............................: ");
		codigoRevista = scan.nextInt();
		
		try
		{
			Biblioteca.getInstance().addPub(new Revista(titulo, e, data, artigos, codigoRevista));
			out.println("A revista foi adicionado com sucesso.");
		}
		catch (PublicacaoDuplicada ex)
		{
			out.println(ex.Message());
			return;
		}
	}

	private static void procurarPublicacao()
	{
		scan = new Scanner(System.in);
		ArrayList<Publicacao> pubs = null;
		
		out.println("!Busca de publicacoes!");
		out.println("Digite uma palavra chave: ");
		String busca = scan.nextLine();
		
		pubs = Biblioteca.getInstance().findPubs(busca);
		
		for (Publicacao p : pubs)
		{
			out.println(p.toString());
		}
	}
	
	private static void procurarAutor()
	{
		scan = new Scanner(System.in);
		
		out.println("!Busca de autor!");
		out.println("Digite o codigo do autor: ");
		out.println(Biblioteca.getInstance().findAutor(scan.nextInt()).toString());
	}
	
	private static void procurarEditor()
	{
		scan = new Scanner(System.in);
		
		out.println("!Busca de editor!");
		out.println("Digite o codigo do editor: ");
		out.println(Biblioteca.getInstance().findEditor(scan.nextInt()).toString());
	}

	private static void removerAgente()
	{
		scan = new Scanner(System.in);
		
		out.println("!Remover agente!");
		out.println("Digite o codigo do agente: ");
		try
		{
			Biblioteca.getInstance().removeAgente(scan.nextInt());
			out.println("O agente foi removido com sucesso.");
		}
		catch (AgenteInexistente e)
		{
			out.println(e.Message());
		}
	}
	
	private static void removerPublicacao()
	{
		scan = new Scanner(System.in);
		
		out.println("!Remover publicacao!");
		out.println("Digite o codigo da publicacao: ");
		try
		{
			Biblioteca.getInstance().removePub(scan.nextInt());
			out.println("A publicacao foi removida com sucesso.");
		}
		catch (PublicacaoInexistente e)
		{
			out.println(e.Message());
		}
	}
}
