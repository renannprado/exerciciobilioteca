package biblioteca;

public class Editor extends Agente 
{
	private String nome;
	
	public Editor (String nome, int codigo)
	{		
		super(codigo);
		this.nome = nome;	
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editor other = (Editor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Editor [nome=" + nome + ", getNome()=" + getNome()
				+ ", getCodigo()=" + getCodigo() + ", toString()="
				+ super.toString() + "]";
	}
}
