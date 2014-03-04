package biblioteca;

import java.util.ArrayList;
import java.util.Date;

public class Revista extends Publicacao 
{
	private ArrayList<Artigo> artigos;
	
	public Revista(String titulo, Editor editor, Date data, ArrayList<Artigo> artigos, int codigo)
	{
		super(titulo, editor, data, codigo);
		this.artigos = artigos;
	}

	/**
	 * @return the artigos
	 */
	public ArrayList<Artigo> getArtigos() {
		return artigos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((artigos == null) ? 0 : artigos.hashCode());
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
		Revista other = (Revista) obj;
		if (artigos == null) {
			if (other.artigos != null)
				return false;
		} else if (!artigos.equals(other.artigos))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Revista [artigos=" + artigos + ", getArtigos()=" + getArtigos()
				+ ", getCodigo()=" + getCodigo() + ", getTitulo()="
				+ getTitulo() + ", getEditor()=" + getEditor() + ", getData()="
				+ getData() + ", toString()=" + super.toString() + "]";
	}
}
