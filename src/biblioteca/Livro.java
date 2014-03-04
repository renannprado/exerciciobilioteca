package biblioteca;

import java.util.ArrayList;
import java.util.Date;

public class Livro extends Publicacao 
{
	private Assunto assunto;
	private ArrayList<Autor> autores;
	
	public Livro(String titulo, Editor editor, Date data, Assunto assunto, ArrayList<Autor> autores, int codigo)
	{
		super(titulo, editor, data, codigo);
		this.assunto = assunto;
		this.autores = autores;
	}

	/**
	 * @return the assunto
	 */
	public Assunto getAssunto() {
		return assunto;
	}

	/**
	 * @return the autores
	 */
	public ArrayList<Autor> getAutores() {
		return autores;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((autores == null) ? 0 : autores.hashCode());
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
		Livro other = (Livro) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (autores == null) {
			if (other.autores != null)
				return false;
		} else if (!autores.equals(other.autores))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Livro [assunto=" + assunto + ", autores=" + autores
				+ ", getAssunto()=" + getAssunto() + ", getAutores()="
				+ getAutores() + ", getCodigo()=" + getCodigo()
				+ ", getTitulo()=" + getTitulo() + ", getEditor()="
				+ getEditor() + ", getData()=" + getData() + ", toString()="
				+ super.toString() + "]";
	}
}
