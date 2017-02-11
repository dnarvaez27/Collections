package dnarvaez27.collections.elements;

/**
 * Interface que constituye las funciones basicas de un nodo con implementacion binaria
 * 
 * @author dnarvaez27
 */
public interface INodoBinario
{
	/**
	 * Retorna el hijo izquierdo del nodo
	 * 
	 * @return Hijo izquierdo del nodo
	 */
	public INodoBinario getLeft( );
	
	/**
	 * Retorna el hijo derecho del nodo
	 * 
	 * @return Hijo derecho del nodo
	 */
	public INodoBinario getRight( );
	
	/**
	 * Retorna el numero de elementos de la jerarquia del nodo
	 * 
	 * @return Numero de elementos de la jerarquia del nodo
	 */
	public int size( );
	
	/**
	 * Retorna la altura de la jerarquia del nodo
	 * 
	 * @return Altura de la jerarquia del nodo
	 */
	public int height( );
	
	/**
	 * Asigna el hijo derecho del nodo
	 * 
	 * @param right Nodo a asignar como hijo derecho
	 */
	public void setRight( INodoBinario right );
	
	/**
	 * Asigna el hijo izquierdo del nodo
	 * 
	 * @param left Nodo a asignar como hijo izquierdo
	 */
	public void setLeft( INodoBinario left );
	
	/**
	 * Retorna un String con la representacion arbolecente de la jerarquia del nodo
	 * 
	 * @return String con la representacion arbolecente de la jerarquia del nodo
	 */
	public String toStringTree( );
	
	/**
	 * Retorna un Iterable de los elementos contenidos en la jerarquia del nodo
	 * 
	 * @return Iterable con los elementos contenidos en la jerarquia del nodo
	 */
	public Iterable<?> elements( );
}