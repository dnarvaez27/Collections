package dnarvaez27.collections.list.linkedlist;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.elements.NodoLineal;
import dnarvaez27.collections.list.AbstractList;
import dnarvaez27.collections.list.IList;

/**
 * Estructura de Lista Sencillamente Encadenada
 *
 * @author dnarvaez27
 * @param <T> Tipo de los elementos
 */
public class SimpleLinkedList<T> extends AbstractList<T> implements IList<T>, ICollection
{
	
	/**
	 * Construye una Lista Sencillamente Encadenada
	 */
	public SimpleLinkedList( )
	{
		head = null;
	}
	
	/**
	 * Construye una Lista Sencillamente Encadenada a partir de la lista dada por parametro
	 *
	 * @param list Lista de elementos a agregar en la Lista Sencillamente Encadenada
	 * @see #addAll(IList)
	 */
	public SimpleLinkedList( IList<T> list )
	{
		super( list );
	}
	
	/**
	 * Construye una Lista Sencillamente Encadenada a partir de los elementos dados por parametro
	 *
	 * @param elements Elementos a agregar en la Lista Doblemente Encadenada
	 * @see #arrayToList(Object...)
	 * @see #addAll(IList)
	 */
	@SafeVarargs
	public SimpleLinkedList( T ... elements )
	{
		super( elements );
	}
	
	@Override
	public void add( int index, T elem )
	{
		if( head == null )
		{
			add( elem );
		}
		else if( index == 0 )
		{
			NodoLineal<T> nuevo = new NodoLineal<>( elem );
			nuevo.setNext( head );
			head = nuevo;
			size++;
		}
		else
		{
			int contador = 0;
			NodoLineal<T> nodoActual = head;
			while( ( contador++ != ( index - 1 ) ) && ( nodoActual != null ) )
			{
				nodoActual = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodoActual.getNext( );
			}
			
			if( nodoActual != null )
			{
				NodoLineal<T> nuevo = new NodoLineal<>( elem );
				nuevo.setNext( nodoActual.getNext( ) );
				nodoActual.setNext( nuevo );
				size++;
			}
			else
			{
				throw new IndexOutOfBoundsException( "Index exceed size of list " + size( ) + " ( " + index + " ) " );
			}
		}
	}
	
	@Override
	public boolean add( T elem )
	{
		if( head == null )
		{
			head = new NodoLineal<>( elem );
		}
		else
		{
			NodoLineal<T> nuevo = new NodoLineal<>( elem );
			// Ciclo !!!???
			NodoLineal<T> actual = head;
			while( actual.getNext( ) != null )
			{
				actual = ( dnarvaez27.collections.elements.NodoLineal<T> ) actual.getNext( );
			}
			actual.setNext( nuevo );
		}
		size++;
		return true;
	}
	
	@Override
	public boolean addAll( IList<T> lista )
	{
		SimpleLinkedList<T> nueva = new SimpleLinkedList<>( lista );
		NodoLineal<T> actual = head;
		if( head == null )
		{
			head = nueva.head;
			size += lista.size( );
		}
		else
		{
			while( actual.getNext( ) != null )
			{
				actual = ( dnarvaez27.collections.elements.NodoLineal<T> ) actual.getNext( );
			}
			actual.setNext( nueva.head );
			size += lista.size( );
		}
		return true;
	}
	
	@Override
	public boolean addAll( int index, IList<T> lista )
	{
		if( ( head == null ) || ( index == size( ) ) )
		{
			addAll( lista );
		}
		else if( index == 0 )
		{
			SimpleLinkedList<T> nuevo = new SimpleLinkedList<>( lista );
			NodoLineal<T> nodo = nuevo.head;
			while( nodo.getNext( ) != null )
			{
				nodo = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
			}
			nodo.setNext( head );
			head = nuevo.head;
			size += lista.size( );
		}
		else
		{
			int contador = 0;
			NodoLineal<T> nodoActual = head;
			while( ( contador++ != ( index - 1 ) ) && ( nodoActual != null ) )
			{
				nodoActual = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodoActual.getNext( );
			}
			
			if( nodoActual != null )
			{
				SimpleLinkedList<T> nuevo = ( SimpleLinkedList<T> ) lista;
				NodoLineal<T> nodo = nuevo.head;
				while( nodo.hasNext( ) )
				{
					nodo = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
				}
				
				nodo.setNext( nodoActual.getNext( ) );
				nodoActual.setNext( nuevo.head );
				size += lista.size( );
			}
			else
			{
				throw new IndexOutOfBoundsException( "Index exceed size of list " + size( ) + " ( " + index + " ) " );
			}
		}
		return false;
	}
	
	@Override
	public boolean addFirst( T elem )
	{
		if( head == null )
		{
			head = new NodoLineal<>( elem );
		}
		else
		{
			NodoLineal<T> nuevo = new NodoLineal<>( elem );
			nuevo.setNext( head );
			head = nuevo;
		}
		size++;
		return true;
	}
	
	@Override
	public T get( int index )
	{
		if( index >= size )
		{
			throw new IndexOutOfBoundsException( "IndexOutOfBounds" + " ( " + size + " ) " + index );
		}
		else if( index < 0 )
		{
			throw new IndexOutOfBoundsException( "IndexOutOfBounds: index must be greater than zero. " + index );
		}
		
		int contador = 0;
		NodoLineal<T> sig = head;
		T buscado = null;
		while( ( sig != null ) && ( buscado == null ) )
		{
			if( contador == index )
			{
				buscado = sig.getElement( );
			}
			else
			{
				contador++;
				sig = ( dnarvaez27.collections.elements.NodoLineal<T> ) sig.getNext( );
			}
		}
		return buscado;
	}
	
	@Override
	public int lastIndexOf( T element )
	{
		int contador = -1;
		NodoLineal<T> sig = head;
		int lastPos = -1;
		while( sig != null )
		{
			if( sig.getElement( ).equals( element ) )
			{
				lastPos = contador;
			}
			else
			{
				contador++;
				sig = ( dnarvaez27.collections.elements.NodoLineal<T> ) sig.getNext( );
			}
		}
		return lastPos;
	}
	
	@Override
	public T remove( int index )
	{
		if( index >= size )
		{
			throw new IndexOutOfBoundsException( index + " | " + size );
		}
		
		if( index == 0 )
		{
			T element = head.getElement( );
			head = ( dnarvaez27.collections.elements.NodoLineal<T> ) head.getNext( );
			size--;
			return element;
		}
		else
		{
			int contador = 0;
			
			NodoLineal<T> actual = head;
			// AbstractNodo<T> sigSig = null;
			// AbstractNodo<T> sig = null;
			
			while( actual != null )
			{
				if( contador == ( index - 1 ) )
				{
					if( actual.getNext( ) != null )
					{
						T elem = actual.getNext( ).getElement( );
						actual.setNext( actual.getNext( ).getNext( ) );
						size--;
						return elem;
					}
					return null;
				}
				else
				{
					actual = ( dnarvaez27.collections.elements.NodoLineal<T> ) actual.getNext( );
					contador++;
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean remove( T elem )
	{
		if( head.getElement( ).equals( elem ) )
		{
			size--;
			head = ( dnarvaez27.collections.elements.NodoLineal<T> ) head.getNext( );
			return true;
		}
		else
		{
			NodoLineal<T> actual = head;
			
			while( actual != null )
			{
				if( ( actual.getNext( ) != null ) && actual.getNext( ).getElement( ).equals( elem ) )
				{
					actual.setNext( actual.getNext( ).getNext( ) );
					size--;
					return true;
				}
				else
				{
					actual = ( dnarvaez27.collections.elements.NodoLineal<T> ) actual.getNext( );
				}
			}
		}
		return false;
	}
	
	@Override
	public T removeFirst( )
	{
		if( size > 0 )
		{
			return remove( 0 );
		}
		return null;
	}
	
	@Override
	public T removeLast( )
	{
		T elem = null;
		if( ( head != null ) && ( head.getNext( ) == null ) )
		{
			elem = head.getElement( );
			head = null;
		}
		else
		{
			NodoLineal<T> nodo = head;
			while( ( nodo.getNext( ) != null ) && ( nodo.getNext( ).getNext( ) != null ) )
			{
				nodo = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
			}
			nodo.setNext( null );
		}
		size--;
		return elem;
	}
	
	@Override
	public T set( int index, T element )
	{
		int contador = 0;
		NodoLineal<T> nodo = head;
		while( nodo != null )
		{
			if( contador == index )
			{
				T old = nodo.getElement( );
				nodo.setElement( element );
				return old;
			}
			else
			{
				contador++;
				nodo = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
			}
		}
		return null;
	}
	
	@Override
	public SimpleLinkedList<T> subList( int fromIndex, int toIndex )
	{
		if( toIndex > size )
		{
			throw new IndexOutOfBoundsException( toIndex + " | " + size );
		}
		if( fromIndex < 0 )
		{
			throw new IndexOutOfBoundsException( fromIndex + " must be greater than 0" );
		}
		
		SimpleLinkedList<T> simpleLinkedList = new SimpleLinkedList<>( );
		
		NodoLineal<T> nodo = head;
		int contador = 0;
		while( nodo != null )
		{
			if( !( contador < toIndex ) )
			{
				break;
			}
			if( ( contador >= fromIndex ) && ( contador < toIndex ) )
			{
				simpleLinkedList.add( nodo.getElement( ) );
			}
			contador++;
			nodo = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
		}
		return simpleLinkedList;
	}
}