package dnarvaez27.collections.elements;

/**
 * Clase que modela un Nodo de implementacion Lineal
 * 
 * @author dnarvaez27
 * @param <T> Tipo de datos a alojar
 */
public class NodoLineal<T> implements INodoLineal<T>
{
	/**
	 * Nodo siguiente
	 */
	private NodoLineal<T> next;
	
	/**
	 * Elemento alojado en el nodo
	 */
	private T element;
	
	/**
	 * Construye un nodo con el elemento dado por parametro
	 * 
	 * @param element Elemento a alojar en el nodo
	 */
	public NodoLineal( T element )
	{
		this.element = element;
	}
	
	public NodoLineal<T> getNext( )
	{
		return next;
	}
	
	public void setNext( INodoLineal<T> next )
	{
		this.next = ( dnarvaez27.collections.elements.NodoLineal<T> ) next;
	}
	
	public T getElement( )
	{
		return element;
	}
	
	public void setElement( T element )
	{
		this.element = element;
	}
	
	public boolean hasNext( )
	{
		return next != null;
	}
}