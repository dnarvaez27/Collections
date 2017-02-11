package dnarvaez27.collections.list;

import java.util.Iterator;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;

/**
 * FIFO Queue Data Structure
 *
 * @author dnarvaez27
 * @param <T> Tipo de los elementos
 */
public class Queue<T> implements Iterable<T>, ICollection
{
	/**
	 * Lista implementada para el Queue
	 */
	private DoubleLinkedList<T> lista;
	
	/**
	 * Construye un Queue
	 */
	public Queue( )
	{
		lista = new DoubleLinkedList<>( );
	}
	
	/**
	 * Para poder usar este metodo, es <u><b>OBLIGATORIO</b></u> haber inicializado las clases por medio de {@link #setClass(Class)}
	 * Agrega el o los elementos especificados por parametro
	 *
	 * @param values Valores a ser agregados al Heap
	 * @throws NullPointerException Si no se han inicializado la classe del elemento
	 */
	@Override
	public void addElements( Object ... values )
	{
		lista.addElements( values );
	}
	
	@Override
	public void clear( )
	{
		lista.clear( );
	}
	
	/**
	 * Verifica si el elemento dado por parametro esta en el Queue
	 *
	 * @param element Elemento de interes
	 * @return True si el elemento esta en el Queue, False de lo contrario
	 */
	public boolean contains( T element )
	{
		return lista.contains( element );
	}
	
	/**
	 * Elimina y retorna el primer elemento del Queue
	 *
	 * @return Primer elemento del Queue
	 */
	public T dequeue( )
	{
		return lista.removeFirst( );
	}
	
	@Override
	public Iterable<?> elements( )
	{
		return this;
	}
	
	/**
	 * Agrega el elemento dado por parametro al final del Queue
	 *
	 * @param element Elemento a agregar al Queue
	 */
	public void enqueue( T element )
	{
		lista.addLast( element );
	}
	
	/**
	 * Verifica si el Queue esta vacio o no
	 *
	 * @return True si el Queue esta vacio, False de lo contrario
	 */
	@Override
	public boolean isEmpty( )
	{
		return lista.isEmpty( );
	}
	
	/**
	 * Retorna un Iterator para recorrer los elementos del Queue
	 */
	@Override
	public Iterator<T> iterator( )
	{
		return lista.iterator( );
	}
	
	/**
	 * Retorna el primer elemento del Queue
	 *
	 * @return Primer elemento del Queue
	 */
	public T peek( )
	{
		return lista.getFirst( );
	}
	
	/**
	 * Establece las clases a utilizar en el uso de varargs add(Object)
	 *
	 * @param classElement Clase de los elementos
	 */
	public void setClass( Class<T> classElement )
	{
		lista.setClass( classElement );
	}
	
	/**
	 * Retorna el numero de elementos del Queue
	 *
	 * @return Numero de elementos del Queue
	 */
	@Override
	public int size( )
	{
		return lista.size( );
	}
	
	/**
	 * Retorna una representacion del Queue
	 */
	@Override
	public String toString( )
	{
		StringBuilder toPrint = new StringBuilder( );
		Iterator<T> it = lista.iterator( );
		while( it.hasNext( ) )
		{
			T elem = it.next( );
			toPrint.append( "-> " + elem );
			if( it.hasNext( ) )
			{
				toPrint.append( " | " );
			}
		}
		return toPrint.toString( );
	}
}