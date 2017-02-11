package dnarvaez27.collections.heap;

import java.util.Comparator;

import dnarvaez27.collections.elements.NodoBinarioKV.NodoBinario;
import dnarvaez27.collections.list.Queue;

/**
 * Estructura de Heap implementado con Punteros/Nodos
 *
 * @author dnarvaez27
 * @param <T> Tipo de Elementos a ser guardados en el Heap
 */
@SuppressWarnings( "unchecked" )
public class HeapPointer<T extends Comparable<? super T>> extends AbstractHeap<T>
{
	/**
	 * Clase que representa un nodo binario para el Heap
	 *
	 * @author dnarvaez27
	 */
	private class NodoHeap extends NodoBinario<T>
	{
		/**
		 * Construye un nodo con el del elemento dado
		 *
		 * @param element Elemento a ser almacenado en el nodo
		 */
		public NodoHeap( T element )
		{
			super( element );
		}
		
		/**
		 * @see #swim()
		 */
		@Override
		public void add( T element )
		{
			boolean agrego = false;
			if( left == null )
			{
				left = new NodoHeap( element );
				agrego = true;
			}
			else if( right == null )
			{
				right = new NodoHeap( element );
				agrego = true;
			}
			
			if( !agrego )
			{
				if( ( left != null ) && ( ( NodoHeap ) left ).isPerfect( ) && ( right.height( ) <= left.height( ) ) && ( right.size( ) < left.size( ) ) )
				{
					( ( NodoHeap ) right ).add( element );
				}
				else
				{
					( ( NodoHeap ) left ).add( element );
				}
			}
			swim( );
		}
		
		/**
		 * <b>No tiene en cuenta la llave</b><br>
		 * En su lugar use {@link #poll()}
		 * Tiene el mismo efecto
		 *
		 * @param key <b>NO es tenida en cuenta</b>
		 * @return El elemento del nodo y reasigna
		 * @see #poll()
		 */
		@Override
		public T delete( T key )
		{
			return poll( );
		}
		
		/**
		 * Retorna un iterable de los elementos de la jerarquia del nodo en pre-orden
		 *
		 * @return Iterable de los elementos de la jerarquia del nodo en pre-orden
		 */
		@Override
		public Iterable<T> elements( )
		{
			Queue<T> queue = new Queue<>( );
			
			queue.enqueue( element );
			if( left != null )
			{
				for( T ts : ( ( NodoHeap ) left ).elements( ) )
				{
					queue.enqueue( ts );
				}
			}
			if( right != null )
			{
				for( T ts : ( ( NodoHeap ) right ).elements( ) )
				{
					queue.enqueue( ts );
				}
			}
			
			return queue;
		}
		
		/**
		 * Retorna el elemento del nodo y se reasigna al hijo que corresponda<br>
		 * Se reemplaza con el ultimo elemento del Heap y se realiza un {@link #sink()}
		 *
		 * @return El elemento del nodo
		 */
		public T poll( )
		{
			T elem = element;
			element = removeLastElement( );
			sink( );
			return elem;
		}
		
		/**
		 * Remueve y retorna el ultimo elemento del Heap
		 *
		 * @return Ultimo elemento del Heap
		 */
		private T removeLastElement( )
		{
			T elem = null;
			
			boolean bothExist = ( left != null ) && ( right != null );
			if( bothExist )
			{
				boolean sizeGreatLeft = left.size( ) > right.size( );
				boolean rightPerfect = ( ( NodoHeap ) right ).isPerfect( );
				boolean sameSizesNotOne = ( left.size( ) == right.size( ) ) && ( left.size( ) != 1 );
				boolean sameSizesOne = ( left.size( ) == right.size( ) ) && ( left.size( ) == 1 );
				if( sizeGreatLeft && rightPerfect )
				{
					elem = ( ( NodoHeap ) left ).removeLastElement( );
				}
				else if( ( sizeGreatLeft || sameSizesNotOne ) && ( left.height( ) == right.height( ) ) )
				{
					elem = ( ( NodoHeap ) right ).removeLastElement( );
				}
				else if( sameSizesOne )
				{
					elem = ( ( NodoBinario<T> ) right ).getElement( );
					setRight( null );
				}
			}
			else if( left != null ) // R NULL
			{
				elem = ( ( NodoBinario<T> ) left ).getElement( );
				setLeft( null );
			}
			return elem;
		}
		
		/*
		 * Reubica los elementos en la jerarquia cuya posicion deba ser en un nivel inferior
		 */
		private void sink( )
		{
			boolean bothExists = ( left != null ) && ( right != null );
			if( bothExists )
			{
				
				boolean leftGreat = ( comparator != null ? comparator.compare( ( ( NodoBinario<T> ) left ).getElement( ), ( ( NodoBinario<T> ) right ).getElement( ) ) : ( ( NodoBinario<T> ) left ).getElement( ).compareTo( ( ( NodoBinario<T> ) right ).getElement( ) ) ) >= 0;
				if( leftGreat && ( ( comparator != null ? comparator.compare( ( ( NodoBinario<T> ) left ).getElement( ), element ) : ( ( NodoBinario<T> ) left ).getElement( ).compareTo( element ) ) > 0 ) )
				{
					T elem = ( ( NodoBinario<T> ) left ).getElement( );
					( ( NodoHeap ) left ).setElement( element );
					setElement( elem );
					
					( ( NodoHeap ) left ).sink( );
				}
				else if( ( comparator != null ? comparator.compare( ( ( NodoBinario<T> ) right ).getElement( ), element ) : ( ( NodoBinario<T> ) right ).getElement( ).compareTo( element ) ) > 0 )
				{
					T elem = ( ( NodoBinario<T> ) right ).getElement( );
					( ( NodoHeap ) right ).setElement( element );
					setElement( elem );
					
					( ( NodoHeap ) right ).sink( );
				}
			}
			else if( ( left != null ) && ( ( comparator != null ? comparator.compare( ( ( NodoBinario<T> ) left ).getElement( ), element ) : ( ( NodoBinario<T> ) left ).getElement( ).compareTo( element ) ) > 0 ) )
			{
				T elem = ( ( NodoBinario<T> ) left ).getElement( );
				( ( NodoHeap ) left ).setElement( element );
				setElement( elem );
				
				( ( NodoHeap ) left ).sink( );
			}
		}
		
		/**
		 * Reubica los elementos en la jerarquia cuya posicion deba ser en un nivel superior
		 */
		private void swim( )
		{
			if( ( left != null ) && ( ( comparator != null ? comparator.compare( ( ( NodoBinario<T> ) left ).getElement( ), element ) : ( ( NodoBinario<T> ) left ).getElement( ).compareTo( element ) ) > 0 ) )
			{
				T elem = ( ( NodoBinario<T> ) left ).getElement( );
				( ( NodoHeap ) left ).setElement( element );
				setElement( elem );
				
				( ( NodoHeap ) left ).swim( );
			}
			else if( ( right != null ) && ( ( comparator != null ? comparator.compare( ( ( NodoBinario<T> ) right ).getElement( ), element ) : ( ( NodoBinario<T> ) right ).getElement( ).compareTo( element ) ) > 0 ) )
			{
				T elem = ( ( NodoBinario<T> ) right ).getElement( );
				( ( NodoHeap ) right ).setElement( element );
				setElement( elem );
				
				( ( NodoHeap ) right ).swim( );
			}
		}
	}
	
	/**
	 * Raiz del Heap
	 */
	private NodoHeap root;
	
	/**
	 * Construye un Heap utilizando el comparador por defecto de la clase
	 */
	public HeapPointer( )
	{
		super( );
	}
	
	/**
	 * Construye un Heap utilizando el comparador dado por parametro
	 *
	 * @param comparator Comparador a utilizar en el Heap
	 */
	public HeapPointer( Comparator<T> comparator )
	{
		super( NORMAL, comparator );
	}
	
	/**
	 * Construye un Heap utilizando una de las constantes de la clase: {@link IHeap#NORMAL} y {@link IHeap#REVERSE}
	 *
	 * @param orientation Orientecion del Heap utilizando las constantes de la clase
	 */
	public HeapPointer( int orientation )
	{
		super( orientation, null );
	}
	
	@Override
	public void add( T element )
	{
		if( root == null )
		{
			root = new NodoHeap( element );
		}
		else
		{
			root.add( element );
		}
		size++;
	}
	
	@Override
	public Iterable<T> elements( )
	{
		if( root != null )
		{
			return root.elements( );
		}
		return new Queue<>( );
	}
	
	/**
	 * Retorna la altura del Heap
	 *
	 * @return Altura del Heap
	 */
	@Override
	public int height( )
	{
		int height = 0;
		if( root != null )
		{
			height = root.height( );
		}
		return height;
	}
	
	@Override
	public T peek( )
	{
		if( root != null )
		{
			return root.getElement( );
		}
		return null;
	}
	
	@Override
	public T poll( )
	{
		T val = null;
		
		if( root != null )
		{
			if( root.size( ) == 1 )
			{
				val = root.getElement( );
				root = null;
			}
			else
			{
				val = root.poll( );
			}
		}
		size--;
		
		return val;
	}
	
	/**
	 * Retorna una representacion arbolecente del Heap
	 */
	@Override
	public String toString( )
	{
		if( root != null )
		{
			return root.toStringTree( );
		}
		return "";
	}
	
	@Override
	public void clear( )
	{
		super.clear( );
		root = null;
	}
}