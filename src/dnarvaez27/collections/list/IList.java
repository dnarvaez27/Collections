package dnarvaez27.collections.list;

import java.util.Iterator;

/**
 * Estructura de Lista lineal
 *
 * @author dnarvaez27
 * @param <T> Tipo de los elementos
 */
public interface IList<T> extends Iterable<T>
{
	/**
	 * Agrega un elemento a la lista en un indice dado
	 *
	 * @param index Indice donde se agregara el elemento
	 * @param elem Elemento a ser agregado
	 */
	public void add( int index, T elem );
	
	/**
	 * Agrega un elemento a la lista
	 *
	 * @param elem Elemento a ser agregado
	 * @return True si pudo ser agregado, False de lo contrario
	 */
	public boolean add( T elem );
	
	/**
	 * Agrega todos los elementos de la lista dada por parametro
	 *
	 * @param list Lista de elementos a agregar
	 * @return True si pudo agregar todos los elementos, False si uno o mas elementos no pudieron se agregados
	 */
	public boolean addAll( IList<T> list );
	
	/**
	 * Agrega todos los elementos de la lista dada por parametro en un indice de la lista
	 *
	 * @param index Indice de la lista donde se agregaran los elementos
	 * @param list Lista de elementos a agregar
	 * @return True si pudo agregar todos los elementos, False si uno o mas elementos no pudieron se agregados
	 */
	public boolean addAll( int index, IList<T> list );
	
	/**
	 * Agrega el elemento en la primera posicion de la lista
	 *
	 * @param elem Elemento a agregar
	 * @return True si pudo agregarlo, False de lo contrario
	 */
	public boolean addFirst( T elem );
	
	/**
	 * Agrega el elemento en la ultima posicion de la lista
	 *
	 * @param elem Elemento a agregar
	 * @return True si pudo agregarlo, False de lo contrario
	 */
	public boolean addLast( T elem );
	
	/**
	 * Elimina todos los elementos de la lista
	 */
	public void clear( );
	
	/**
	 * Verifica si el elemento dado por parametro esta en la lista
	 *
	 * @param elem Elemento a verificar
	 * @return True si el elemento esta en la lista, False de lo contrario
	 */
	public boolean contains( T elem );
	
	/**
	 * Retorna el elemento en el indice dado por parametro
	 *
	 * @param index Indice del elemento de interes
	 * @return Elemento en el indice dado por parametro
	 */
	public T get( int index );
	
	/**
	 * Retorna el primer elemento de la lista
	 *
	 * @return Elemento en la primera posicion de la lista
	 */
	public T getFirst( );
	
	/**
	 * Retorna el indice del elemento en la lista.
	 *
	 * @param element Elemento a evaluar el indice en la lista
	 * @return Indice del elemento en la lista. -1 Si el elemento no esta en la lista
	 */
	public int indexOf( T element );
	
	/**
	 * Verifica si la lista esta vacia o no
	 *
	 * @return True si la lista esta vacia, False de lo contrario
	 */
	public boolean isEmpty( );
	
	/**
	 * Retorna un Iterator para recorrer los elementos de la lista
	 *
	 * @return Iterator para recorrer los elementos de la lista
	 */
	@Override
	public Iterator<T> iterator( );
	
	/**
	 * Retorna el ultimo indice del elemento que coincida con el parametro
	 *
	 * @param element Elemento de interés
	 * @return El ultimo indice del elemento que coincida con el parametro. -1 Si el elemento no esta en la lista
	 */
	public int lastIndexOf( T element );
	
	/**
	 * Elimina el elemento en la posicion dada por parametro
	 *
	 * @param index Indice del elemento a eliminar
	 * @return El elemento eliminado
	 */
	public T remove( int index );
	
	/**
	 * Elimina el elemento que coincida con el parametro
	 *
	 * @param elem Elemento a eliminar
	 * @return True si el elemento fue eliminado, False si no fue eliminado o no se encontraba en la lista
	 */
	public boolean remove( T elem );
	
	/**
	 * Elimina todos de la lista que se encuentren en la lista dada por parametro
	 *
	 * @param list Lista de elementos a eliminar
	 * @return True si se pudieron eliminar todos los elementos, False si uno o mas elementos no fueron eliminados o no existian en la lista
	 */
	public boolean removeAll( IList<T> list );
	
	/**
	 * Elimina y retorna el primer elemento de la lista
	 *
	 * @return El primer elemento de la lista
	 */
	public T removeFirst( );
	
	/**
	 * Elimina y retorna el ultimo elemento de la lista
	 *
	 * @return El ultimo elemento de la lista
	 */
	public T removeLast( );
	
	/**
	 * Sobreescribe el elemento en una posicion, con el elemento dados por parametro
	 *
	 * @param index indice del elemento a sobreescribir
	 * @param element Elemento a ubicar en la posicion dada
	 * @return El elemento anterior en la posicion dada
	 */
	public T set( int index, T element );
	
	/**
	 * Establece las clases a utilizar en el uso de varargs {@link AbstractList#add(Object)}
	 *
	 * @param classElement Clase de los elementos
	 */
	public void setClass( Class<T> classElement );
	
	/**
	 * Retorna el numero de elementos de la lista
	 *
	 * @return Numero de elementos de la lista
	 */
	public int size( );
	
	/**
	 * Retorna una Sublista de los elementos de la lista comprendidos en el rango dado por parametro
	 *
	 * @param fromIndex Posicion de inicio del rango. Incluyente
	 * @param toIndex Fin del rango. Excluyente
	 * @return Sublista con los elementos de la lista comprendidos en el rango
	 */
	public IList<T> subList( int fromIndex, int toIndex );
	
	/**
	 * Retorna un Arreglo con los elementos de la lista
	 *
	 * @return Arreglo con los elementos de la lista
	 */
	public Object[ ] toArray( );
}