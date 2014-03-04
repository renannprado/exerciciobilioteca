package biblioteca.excecoes;

public class ExcecaoDePublicacao extends Exception 
{ 
	private String message;
	
	public ExcecaoDePublicacao(String message)
	{
		this.message = message;
	}
	
	public String Message() { return this.message; }	
}
