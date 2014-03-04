package biblioteca.excecoes;

public class AgenteDuplicado extends Exception 
{
	private String message;
	
	public AgenteDuplicado(String message)
	{
		this.message = message;
	}
	
	public String Message() { return this.message; }
}
