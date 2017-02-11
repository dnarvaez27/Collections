package dnarvaez27.collections.heap;

import dnarvaez27.collections.ICollection;

/**
 * Interface que constituye las funciones basicas de un Heap
 * 
 * @author dnarvaez27
 * @param <T> Tipo de elementos a guardar en el Heap
 */
public interface IHeap<T> extends ICollection
{
	/**
	 * Constante que define si el Heap es implementado con el comparador por defecto de la clase de los elementos del Heap
	 */
	public static final int NORMAL = 1;
	
	/**
	 * Constatne que define si el Heap es implementado con el inverso del comparador por defecto de la clase de los elementos del Heap
	 */
	public static final int REVERSE = 0;
	
	/**
	 * Agrega un elemento al Heap
	 * 
	 * @param element Elemento a ser agregado
	 */
	public void add( T element );
	
	/**
	 * Elimina y retorna el elemento que se encuentre en la raiz del Heap
	 * 
	 * @return Elemento que se encuentre en la raiz del Heap
	 */
	public T poll( );
	
	/**
	 * Retorna el elemento que se encuentre en la raiz del Heap
	 * 
	 * @return Elemento que se encuentre en la raiz del Heap
	 */
	public T peek( );
	
	/**
	 * Retorna la cantidad de elementos en el Heap
	 * 
	 * @return Cantidad de elementos en el Heap
	 */
	public int size( );
	
	/**
	 * Verifica si el Heap esta vacio o no
	 * 
	 * @return True si el Heap esta vacio, False de lo contrario
	 */
	public boolean isEmpty( );
	
	/**
	 * Retorna la altura del Heap
	 * 
	 * @return Altura del Heap
	 */
	public int height( );
	
	/**
	 * Establece las clases a utilizar en el uso de varargs {@link #add(Object...)}
	 *
	 * @param classElement Clase de los elementos
	 */
	public void setClass( Class<T> classElement );
	
	/**
	 * Retorna un Iterable con los elementos del Heap en <b>Pre-Orden</b>
	 * <ol>
	 * <li>Raiz
	 * <li>Izquierda
	 * <li>Derecha
	 * </ol>
	 * 
	 * @return Iterable con los elementos del Heap en Pre-Orden
	 */
	public Iterable<T> elements( );
}