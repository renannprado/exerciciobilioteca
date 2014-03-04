package biblioteca.excecoes;

public class AgenteInexistente extends Exception 
{
	private String message;
	
	public AgenteInexistente(String message)
	{
		this.message = message;
	}
	
	public String Message() { return this.message; }
}
