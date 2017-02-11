package dnarvaez27.collections.elements;

/**
 * Interface que constituye las funciones basicas de un nodo con implementacion lineal
 * 
 * @author dnarvaez27
 * @param <T> Tipo de datos a alojar
 */
public interface INodoLineal<T>
{
	/**
	 * Retorna el elemento del nodo
	 *
	 * @return Elemento del nodo
	 */
	public T getElement( );
	
	/**
	 * Retorna el nodo siguiente
	 *
	 * @return Nodo Siguiente
	 */
	public INodoLineal<T> getNext( );
	
	/**
	 * Verifica si tiene un nodo siguiente
	 *
	 * @return True si tiene siguiente, False de lo contrario
	 */
	public boolean hasNext( );
	
	/**
	 * Asigna el elemento al nodo
	 *
	 * @param element Elemento a asignar
	 */
	public void setElement( T element );
	
	/**
	 * Asigna el nodo siguiente
	 *
	 * @param next Nodo a asignar como siguiente
	 */
	public void setNext( INodoLineal<T> next );
}