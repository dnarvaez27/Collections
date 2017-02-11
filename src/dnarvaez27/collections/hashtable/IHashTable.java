package dnarvaez27.collections.hashtable;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.elements.Entry;

/**
 * Interface que constituye las funciones basicas de una HashTable
 *
 * @author dnarvaez27
 * @param <K> Tipo de las llaves
 * @param <V> Tipo de los valores
 */
public interface IHashTable<K, V> extends ICollection
{
	/**
	 * Elimina y retorna el elemento asociado a la llave dada por parametro
	 *
	 * @param key Llava del elemento a eliminar
	 * @return Elemento eliminado
	 */
	public V delete( K key );
	
	/**
	 * Retorna el elemento asociado a la llave dada por parametro
	 *
	 * @param key Llave del elemento en interés
	 * @return Valor asociado a la llave, null si la llave no corresponde a ningun valor
	 */
	public V get( K key );
	
	/**
	 * Verifica si la HashTable esta vacia o no
	 *
	 * @return True si la HashTable esta vacia, False de lo contrario
	 */
	public boolean isEmpty( );
	
	/**
	 * Agrega un elemento a la HashTable<br>
	 * Si ya existia una llave igual se sobreescribe el valor del elemento
	 *
	 * @param key Llave del elemento a almacenar
	 * @param value Valor a almacenar
	 * @return El elemento anterior de la llave, null si no existia
	 */
	public V put( K key, V value );
	
	/**
	 * Retorna el número de elementos de la HashTable
	 *
	 * @return Numero de elementos de la HashTable
	 */
	public int size( );
	
	/**
	 * Retorna un Iterable de las entradas de la HashTable
	 * 
	 * @return Iterable de las entradas de la HashTable
	 */
	public Iterable<Entry<K, V>> entries( );
	
	/**
	 * Retorna una representacion de la HashTable<br>
	 * El proposito de esta representacion es por Debug o
	 * interés del almacenamiento de los datos en la HashTable<br>
	 */
	public String toString( );
}