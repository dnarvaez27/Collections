package dnarvaez27.collections.list.linkedlist;

import java.util.Iterator;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.elements.NodoLineal;
import dnarvaez27.collections.list.AbstractList;
import dnarvaez27.collections.list.IList;

/**
 * Estructura de Lista Doublemente Encadenada
 *
 * @author dnarvaez27
 * @param <T> Tipo de los elementos
 */
public class DoubleLinkedList<T> extends AbstractList<T> implements IList<T>, ICollection
{
	/**
	 * Clase que modela un nodo de la Lista Doublemente Encadenada
	 *
	 * @author dnarvaez27
	 */
	private class DoubleNodo extends NodoLineal<T>
	{
		/**
		 * Elemento anterior del nodo
		 */
		private DoubleNodo previous;
		
		/**
		 * Construye un nodo con el elemento dado por parametro
		 *
		 * @param element Elemento del nodo
		 */
		public DoubleNodo( T element )
		{
			super( element );
		}
		
		/**
		 * Retorna el nodo anterior
		 *
		 * @return Nodo Anterior
		 */
		public DoubleNodo getPrevious( )
		{
			return previous;
		}
		
		/**
		 * Verifica si tiene un anterior
		 *
		 * @return True si tiene anterior, False de lo contrario
		 */
		public boolean hasPrevious( )
		{
			return previous != null;
		}
		
		/**
		 * Asigna el nodo anterior
		 *
		 * @param previous Nodo a asignar como anterior
		 */
		public void setPrevious( DoubleNodo previous )
		{
			this.previous = previous;
		}
	}
	
	/**
	 * Cola de la lista. Ultimo elemento
	 */
	private DoubleNodo tail;
	
	/**
	 * Construye una Lista Doblemente Encadenada
	 */
	public DoubleLinkedList( )
	{
		super( );
		tail = null;
	}
	
	/**
	 * Construye una Lista Doblemente Encadenada a partir de la lista dada por parametro
	 *
	 * @param list Lista de elementos a agregar en la Lista Doblemente Encadenada
	 * @see #addAll(IList)
	 */
	public DoubleLinkedList( IList<T> list )
	{
		super( list );
	}
	
	/**
	 * Construye una Lista Doblemente Encadenada a partir de los elementos dados por parametro
	 *
	 * @param elements Elementos a agregar en la Lista Doblemente Encadenada
	 * @see #arrayToList(Object...)
	 * @see #DoubleLinkedList(IList)
	 */
	@SafeVarargs
	public DoubleLinkedList( T ... elements )
	{
		super( elements );
	}
	
	@Override
	public void add( int index, T element )
	{
		if( index < 0 )
		{
			throw new IndexOutOfBoundsException( index + " must be greater than 0" );
		}
		if( index > size )
		{
			throw new IndexOutOfBoundsException( "Index exceed size of list " + size + " ( " + index + " ) " );
		}
		
		if( ( head == null ) || ( index == size( ) ) )
		{
			add( element );
		}
		else if( index == 0 )
		{
			DoubleNodo nuevo = new DoubleNodo( element );
			nuevo.setNext( head );
			( ( DoubleNodo ) head ).setPrevious( nuevo );
			
			head = nuevo;
			size++;
		}
		else
		{
			int disTail = size - index - 1;
			if( disTail < index )
			{
				int contador = 0;
				DoubleNodo nodo = tail;
				while( ( contador++ != disTail ) && ( nodo != null ) )
				{
					nodo = nodo.getPrevious( );
				}
				if( nodo != null )
				{
					DoubleNodo nuevo = new DoubleNodo( element );
					if( nodo.hasPrevious( ) )
					{
						DoubleNodo prev = nodo.getPrevious( );
						nuevo.setPrevious( prev );
						prev.setNext( nuevo );
					}
					nuevo.setNext( nodo );
					nodo.setPrevious( nuevo );
					size++;
				}
				else
				{
					throw new IndexOutOfBoundsException( "Index exceed size of list " + size( ) + " ( " + index + " ) " );
				}
			}
			else
			{
				int contador = 0;
				DoubleNodo nodo = ( DoubleNodo ) head;
				while( ( contador++ != index ) && ( nodo != null ) )
				{
					nodo = ( DoubleNodo ) nodo.getNext( );
				}
				if( nodo != null )
				{
					DoubleNodo nuevo = new DoubleNodo( element );
					if( nodo.hasPrevious( ) )
					{
						DoubleNodo prev = nodo.getPrevious( );
						nuevo.setPrevious( prev );
						prev.setNext( nuevo );
					}
					nuevo.setNext( nodo );
					nodo.setPrevious( nuevo );
					size++;
				}
				else
				{
					throw new IndexOutOfBoundsException( "Index exceed size of list " + size( ) + " ( " + index + " ) " );
				}
			}
		}
	}
	
	@Override
	public boolean add( T element )
	{
		if( head == null )
		{
			head = new DoubleNodo( element );
			tail = ( DoubleNodo ) head;
		}
		else
		{
			DoubleNodo nuevo = new DoubleNodo( element );
			nuevo.setPrevious( tail );
			tail.setNext( nuevo );
			
			tail = ( DoubleNodo ) tail.getNext( );
		}
		size++;
		return true;
	}
	
	@Override
	public boolean addAll( IList<T> list )
	{
		if( !list.isEmpty( ) )
		{
			DoubleLinkedList<T> doubleLinkedList = ( DoubleLinkedList<T> ) list;
			
			if( head == null )
			{
				head = doubleLinkedList.head;
				tail = doubleLinkedList.tail;
			}
			else
			{
				tail.setNext( doubleLinkedList.head );
				( ( DoubleNodo ) doubleLinkedList.head ).setPrevious( tail );
				tail = doubleLinkedList.tail;
			}
			size += doubleLinkedList.size( );
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addAll( int index, IList<T> collection )
	{
		if( index < 0 )
		{
			throw new IndexOutOfBoundsException( index + " must be greater than 0" );
		}
		
		if( index > size )
		{
			throw new IndexOutOfBoundsException( "Index exceed size of list " + size + " ( " + index + " ) " );
		}
		
		if( !collection.isEmpty( ) )
		{
			if( ( head == null ) || ( index == size( ) ) )
			{
				addAll( collection );
			}
			else if( index == 0 )
			{
				DoubleLinkedList<T> doubleLinkedList = new DoubleLinkedList<>( collection );
				( ( DoubleNodo ) head ).setPrevious( doubleLinkedList.tail );
				doubleLinkedList.tail.setNext( head );
				head = doubleLinkedList.head;
				
				size += doubleLinkedList.size( );
			}
			else
			{
				// Implementar conteo de reversa
				int disTail = size - index - 1;
				if( disTail < index )
				{
					int contador = 0;
					DoubleNodo nodo = tail;
					while( ( contador++ != disTail ) && ( nodo != null ) )
					{
						nodo = nodo.getPrevious( );
					}
					if( nodo != null )
					{
						DoubleLinkedList<T> doubleLinkedList = new DoubleLinkedList<>( collection );
						if( nodo.hasPrevious( ) )
						{
							DoubleNodo prev = nodo.getPrevious( );
							( ( DoubleNodo ) doubleLinkedList.head ).setPrevious( prev );
							prev.setNext( doubleLinkedList.head );
						}
						doubleLinkedList.tail.setNext( nodo );
						nodo.setPrevious( doubleLinkedList.tail );
						size += doubleLinkedList.size( );
					}
					else
					{
						throw new IndexOutOfBoundsException( "Index exceed size of list " + size( ) + " ( " + index + " ) " );
					}
				}
				else
				{
					int contador = 0;
					DoubleNodo nodo = ( DoubleNodo ) head;
					while( ( contador++ != index ) && ( nodo != null ) )
					{
						nodo = ( DoubleNodo ) nodo.getNext( );
					}
					if( nodo != null )
					{
						DoubleLinkedList<T> doubleLinkedList = new DoubleLinkedList<>( collection );
						if( nodo.hasPrevious( ) )
						{
							DoubleNodo prev = nodo.getPrevious( );
							( ( DoubleNodo ) doubleLinkedList.head ).setPrevious( prev );
							prev.setNext( doubleLinkedList.head );
						}
						doubleLinkedList.tail.setNext( nodo );
						nodo.setPrevious( doubleLinkedList.tail );
						size += doubleLinkedList.size( );
					}
					else
					{
						throw new IndexOutOfBoundsException( "Index exceed size of list " + size( ) + " ( " + index + " ) " );
					}
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addFirst( T elem )
	{
		if( head == null )
		{
			head = new DoubleNodo( elem );
		}
		else
		{
			DoubleNodo nuevo = new DoubleNodo( elem );
			nuevo.setNext( head );
			( ( DoubleNodo ) head ).setPrevious( nuevo );
			head = nuevo;
		}
		size++;
		return true;
	}
	
	@Override
	public void clear( )
	{
		super.clear( );
		tail = null;
	}
	
	/**
	 * Verifica si todos los elementos de la lista dada por parametro
	 * se encuentran en la lista
	 * 
	 * @param collection Lista de elementos a verificar
	 * @return True si Todos los elementos se encuentran en la lista,
	 *         False si uno o mas elementos no estan en la lista
	 */
	public boolean containsAll( IList<T> collection )
	{
		Iterator<T> iterator = collection.iterator( );
		while( iterator.hasNext( ) )
		{
			if( !contains( iterator.next( ) ) )
			{
				return false;
			}
		}
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
		
		int distTail = size - index - 1;
		if( distTail < index )
		{
			int contador = 0;
			DoubleNodo nodo = tail;
			while( nodo != null )
			{
				if( contador++ == distTail )
				{
					return nodo.getElement( );
				}
				nodo = nodo.getPrevious( );
			}
			return null;
		}
		else
		{
			int contador = 0;
			DoubleNodo nodo = ( DoubleNodo ) head;
			while( nodo != null )
			{
				if( contador++ == index )
				{
					return nodo.getElement( );
				}
				nodo = ( DoubleNodo ) nodo.getNext( );
			}
			return null;
		}
	}
	
	@Override
	public int lastIndexOf( T element )
	{
		int contador = 1;
		DoubleNodo nodo = tail;
		while( nodo != null )
		{
			if( nodo.getElement( ).equals( element ) )
			{
				return size( ) - contador;
			}
			contador++;
			nodo = nodo.getPrevious( );
		}
		return -1;
	}
	
	@Override
	public T remove( int index )
	{
		if( index >= size )
		{
			throw new IndexOutOfBoundsException( index + " | " + size );
		}
		
		if( !isEmpty( ) )
		{
			if( index == 0 )
			{
				T element = head.getElement( );
				if( head.hasNext( ) )
				{
					head = ( dnarvaez27.collections.elements.NodoLineal<T> ) head.getNext( );
					size--;
					return element;
				}
				else
				{
					head = null;
					tail = null;
				}
				size--;
				return element;
			}
			else
			{
				int disTail = size - index - 1;
				if( disTail < index )
				{
					int contador = 0;
					DoubleNodo nodo = tail;
					while( nodo != null )
					{
						if( contador++ == disTail )
						{
							if( nodo.hasPrevious( ) )
							{
								nodo.getPrevious( ).setNext( nodo.getNext( ) );
								if( nodo.hasNext( ) )
								{
									( ( DoubleNodo ) nodo.getNext( ) ).setPrevious( nodo.getPrevious( ) );
								}
							}
							else
							{
								head = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
								( ( DoubleNodo ) head ).setPrevious( null );
							}
							size--;
							return nodo.getElement( );
						}
						nodo = nodo.getPrevious( );
					}
				}
				else
				{
					int contador = 0;
					DoubleNodo nodo = ( DoubleNodo ) head;
					while( nodo != null )
					{
						if( contador++ == index )
						{
							if( nodo.hasPrevious( ) )
							{
								nodo.getPrevious( ).setNext( nodo.getNext( ) );
								if( nodo.hasNext( ) )
								{
									( ( DoubleNodo ) nodo.getNext( ) ).setPrevious( nodo.getPrevious( ) );
								}
							}
							else
							{
								head = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
								( ( DoubleNodo ) head ).setPrevious( null );
							}
							size--;
							return nodo.getElement( );
						}
						nodo = ( DoubleNodo ) nodo.getNext( );
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean remove( T element )
	{
		DoubleNodo nodo = ( DoubleNodo ) head;
		while( nodo != null )
		{
			if( nodo.getElement( ).equals( element ) )
			{
				if( nodo.hasPrevious( ) )
				{
					nodo.getPrevious( ).setNext( nodo.getNext( ) );
					if( nodo.hasNext( ) )
					{
						( ( DoubleNodo ) nodo.getNext( ) ).setPrevious( nodo.getPrevious( ) );
					}
				}
				else
				{
					head = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
					if( head != null )
					{
						( ( DoubleNodo ) head ).setPrevious( null );
					}
				}
				size--;
				return true;
			}
			nodo = ( DoubleNodo ) nodo.getNext( );
		}
		return false;
	}
	
	@Override
	public T removeFirst( )
	{
		if( !isEmpty( ) )
		{
			T element = head.getElement( );
			if( head.hasNext( ) )
			{
				head = ( dnarvaez27.collections.elements.NodoLineal<T> ) head.getNext( );
			}
			else
			{
				head = null;
				tail = null;
			}
			size--;
			return element;
		}
		return null;
	}
	
	@Override
	public T removeLast( )
	{
		T elem = null;
		if( tail != null )
		{
			elem = tail.getElement( );
			if( tail.getPrevious( ) != null )
			{
				tail.getPrevious( ).setNext( null );
				tail.setPrevious( null );
			}
		}
		size--;
		return elem;
	}
	
	/**
	 * Elimina todos los elementos de la lista, a excepcion de los que se encuentran en la lista
	 * 
	 * @param lista Lista de elementos a retener
	 * @return True si la lista cambio, False de lo contrario
	 */
	public boolean retainAll( final DoubleLinkedList<T> lista )
	{
		boolean changed = false;
		DoubleNodo nodo = ( DoubleNodo ) head;
		while( nodo != null )
		{
			if( !lista.contains( nodo.getElement( ) ) )
			{
				if( nodo.hasPrevious( ) )
				{
					nodo.getPrevious( ).setNext( nodo.getNext( ) );
					if( nodo.hasNext( ) )
					{
						( ( DoubleNodo ) nodo.getNext( ) ).setPrevious( nodo.getPrevious( ) );
					}
				}
				else
				{
					head = ( dnarvaez27.collections.elements.NodoLineal<T> ) nodo.getNext( );
					if( head != null )
					{
						( ( DoubleNodo ) head ).setPrevious( null );
					}
				}
				changed = true;
			}
			nodo = ( DoubleNodo ) nodo.getNext( );
		}
		return changed;
	}
	
	@Override
	public T set( int index, T element )
	{
		if( index >= size )
		{
			throw new IndexOutOfBoundsException( index + " | " + size );
		}
		
		int disTail = size - index - 1;
		if( disTail < index )
		{
			int contador = 0;
			DoubleNodo nodo = tail;
			while( nodo != null )
			{
				if( contador++ == disTail )
				{
					T oldElement = nodo.getElement( );
					nodo.setElement( element );
					return oldElement;
				}
				nodo = nodo.getPrevious( );
			}
		}
		else
		{
			int contador = 0;
			DoubleNodo nodo = ( DoubleNodo ) head;
			while( nodo != null )
			{
				if( contador++ == index )
				{
					T oldElement = nodo.getElement( );
					nodo.setElement( element );
					return oldElement;
				}
				nodo = ( DoubleNodo ) nodo.getNext( );
			}
		}
		return null;
	}
	
	@Override
	public DoubleLinkedList<T> subList( int fromIndex, int toIndex )
	{
		if( toIndex > size )
		{
			throw new IndexOutOfBoundsException( toIndex + " | " + size );
		}
		if( fromIndex < 0 )
		{
			throw new IndexOutOfBoundsException( fromIndex + " must be greater than 0" );
		}
		
		DoubleLinkedList<T> doubleLinkedList = new DoubleLinkedList<>( );
		
		int disTail = size - fromIndex - 1;
		if( disTail < fromIndex )
		{
			DoubleNodo nodo = tail;
			int contador = 0;
			boolean encontro = false;
			while( nodo != null )
			{
				if( !( contador < toIndex ) )
				{
					break;
				}
				if( ( contador >= disTail ) && ( contador < ( ( toIndex - fromIndex ) + disTail ) ) )
				{
					encontro = true;
					doubleLinkedList.add( nodo.getElement( ) );
				}
				contador++;
				if( encontro )
				{
					nodo = ( DoubleNodo ) nodo.getNext( );
				}
				else
				{
					nodo = nodo.getPrevious( );
				}
			}
		}
		else
		{
			DoubleNodo nodo = ( DoubleNodo ) head;
			int contador = 0;
			while( nodo != null )
			{
				if( !( contador < toIndex ) )
				{
					break;
				}
				if( ( contador >= fromIndex ) && ( contador < toIndex ) )
				{
					doubleLinkedList.add( nodo.getElement( ) );
				}
				contador++;
				nodo = ( DoubleNodo ) nodo.getNext( );
			}
		}
		return doubleLinkedList;
	}
}