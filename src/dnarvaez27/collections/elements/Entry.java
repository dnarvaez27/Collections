package dnarvaez27.collections.elements;

/**
 * Entrada de tupla Llave-Valor
 * 
 * @author dnarvaez27
 * @param <K> Tipo de la llave
 * @param <V> Tipo de el valor
 */
public class Entry<K, V>
{
	/**
	 * Llave asociada al elemento del nodo
	 */
	protected K key;
	
	/**
	 * Valor asociado al elemento del nodo
	 */
	protected V value;
	
	/**
	 * Entrada de una tupla Llave-Valor
	 * 
	 * @param key Llave de la Entrada
	 * @param value Valor de la Entrada
	 */
	public Entry( K key, V value )
	{
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Retorna la llave del nodo
	 *
	 * @return Llave del nodo
	 */
	public K getKey( )
	{
		return key;
	}
	
	/**
	 * Retorna el valor del nodo
	 *
	 * @return Valor del nodo
	 */
	public V getValue( )
	{
		return value;
	}
	
	/**
	 * Asigna la llave del nodo
	 *
	 * @param key Llave a ser asignada
	 */
	public void setKey( K key )
	{
		this.key = key;
	}
	
	/**
	 * Asigna el valor al nodo
	 *
	 * @param value Valor a ser asignado
	 */
	public void setValue( V value )
	{
		this.value = value;
	}
	
	/**
	 * Retorna una representacion de la entrada
	 */
	public String toString( )
	{
		return key + ":" + value;
	}
}