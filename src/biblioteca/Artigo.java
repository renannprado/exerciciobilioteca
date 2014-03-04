package biblioteca;

import java.util.ArrayList;
import java.util.Date;

public class Artigo extends Publicacao 
{
	private ArrayList<Autor> autores;
	private ArrayList<String> palavrasChave;
	
	public Artigo (String titulo, Editor editor,Date data, ArrayList<Autor> autores, ArrayList<String> palavrasChave, int codigo)
	{
		super(titulo, editor, data, codigo);
		this.autores = autores;
		this.palavrasChave = palavrasChave;
	}

	/**
	 * @return the autores
	 */
	public ArrayList<Autor> getAutores() {
		return autores;
	}

	/**
	 * @return the palavrasChave
	 */
	public ArrayList<String> getPalavrasChave() {
		return palavrasChave;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Artigo [autores=" + autores + ", palavrasChave="
				+ palavrasChave + ", getAutores()=" + getAutores()
				+ ", getPalavrasChave()=" + getPalavrasChave()
				+ ", getCodigo()=" + getCodigo() + ", getTitulo()="
				+ getTitulo() + ", getEditor()=" + getEditor() + ", getData()="
				+ getData() + ", toString()=" + super.toString() + "]";
	}
}
