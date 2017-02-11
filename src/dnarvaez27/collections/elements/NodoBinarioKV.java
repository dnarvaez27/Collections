package dnarvaez27.collections.elements;

import dnarvaez27.collections.list.Queue;

/**
 * Nodo para implementacion binaria con entradas tipo tupla: Llave-Valor
 * 
 * @author dnarvaez27
 * @param <K> Tipo de las llaves de las entradas
 * @param <V> Tipo de los valores de las entradas
 */
@SuppressWarnings( "unchecked" )
public abstract class NodoBinarioKV<K, V> extends Entry<K, V> implements INodoBinario
{
	/**
	 * Nodo para implementacion binaria de un unico elemento
	 * 
	 * @author dnarvaez27
	 * @param <T> Tipo de elementos a alojar
	 */
	public static abstract class NodoBinario<T> extends NodoBinarioKV<T, T>
	{
		/**
		 * Elemento que almacena el nodo
		 */
		protected T element;
		
		/**
		 * Construye un nodo con el del elemento dado
		 *
		 * @param element Elemento a ser almacenado en el nodo
		 */
		public NodoBinario( T element )
		{
			super( element, null );
			this.element = element;
			left = null;
			right = null;
		}
		
		/**
		 * Agrega un elemento en la jerarquia del Nodo
		 *
		 * @param element Elemento a agregar
		 */
		abstract public void add( T element );
		
		/**
		 * Agrega la llave y el valor como Nodos distintos
		 *
		 * @see #add(Object)
		 * @param key Elemento a ser agregado
		 * @param value Elemento a ser agregado
		 */
		@Override
		public void add( T key, T value )
		{
			add( key );
			add( value );
		}
		
		@Override
		public T get( T key )
		{
			throw new UnsupportedOperationException( "No soporta tupla Llave-Valor" );
		}
		
		/**
		 * Retorna el elemento almacenado en el nodo
		 *
		 * @return Elemento almacenado en el nodo
		 */
		public T getElement( )
		{
			return element;
		}
		
		/**
		 * Asigna el elemento que almacenara el nodo
		 *
		 * @param element Elemento a ser almacenado
		 */
		public void setElement( T element )
		{
			this.element = element;
		}
		
		/**
		 * Retorna una representacion del elemento
		 */
		@Override
		public String toString( )
		{
			return element.toString( );
		}
		
		@Override
		public Iterable<T> elements( )
		{
			Queue<T> queue = new Queue<>( );
			if( left != null )
			{
				for( T ts : ( ( NodoBinario<T> ) left ).elements( ) )
				{
					queue.enqueue( ts );
				}
			}
			queue.enqueue( element );
			if( right != null )
			{
				for( T ts : ( ( NodoBinario<T> ) right ).elements( ) )
				{
					queue.enqueue( ts );
				}
			}
			return queue;
		}
	}
	
	/**
	 * Hijo izquierdo del nodo
	 */
	protected INodoBinario left;
	
	/**
	 * Hijo derecho del nodo
	 */
	protected INodoBinario right;
	
	public NodoBinarioKV( K key, V value )
	{
		super( key, value );
	}
	
	/**
	 * Agrega una entrada Llave-Valor a la jerarquia del Nodo
	 *
	 * @param key Llave de la entrada
	 * @param value Valor de la entrada
	 */
	abstract public void add( K key, V value );
	
	/**
	 * Elimina una entrada con la llave dada por parametro.
	 * Retorna el valor de la entrada
	 *
	 * @param key Llave de la entrada a ser eliminada
	 * @return Valor de la entrada. Null si la entrada no se encontraba
	 *         en la jerarquia del nodo
	 */
	abstract public V delete( K key );
	
	/**
	 * Retorna el valor de la entrada cuya llave coincide con la dada por parametro
	 *
	 * @param key Llave de la entrada de interés
	 * @return Valor de la entrada cuya llave es dada por parametro.
	 *         Null si la entrada no se encontraba en la jerarquia del nodo
	 */
	abstract public V get( K key );
	
	/**
	 * Retorna el hijo izquierdo del nodo
	 *
	 * @return Hijo izquierdo del nodo
	 */
	@Override
	public INodoBinario getLeft( )
	{
		return left;
	}
	
	/**
	 * Retorna el hijo derecho del nodo
	 *
	 * @return Hijo derecho del nodo
	 */
	@Override
	public INodoBinario getRight( )
	{
		return right;
	}
	
	/**
	 * Retorna la altura del arbol formado por la jerarquia del nodo
	 *
	 * @return Altura del arbol formado por la jerarquia del nodo
	 */
	@Override
	public int height( )
	{
		int height = 1;
		int leftHeight = left != null ? left.height( ) : 0;
		int rightHeight = right != null ? right.height( ) : 0;
		height += leftHeight >= rightHeight ? leftHeight : rightHeight;
		return height;
	}
	
	/**
	 * Verifica si el arbol formado por la jerarquia del nodo es perfecta o no.<br>
	 * Se define que un arbol es perfecto si el numero de elementos a la izquierda
	 * de este son iguales al numero de elementos a la derecha
	 *
	 * @return True si el arbol formado por la jerarquia del nodo es perfecto, False de lo contrario
	 */
	protected boolean isPerfect( )
	{
		boolean complete = true;
		if( right != null )
		{
			complete = right.size( ) == left.size( );
		}
		else
		{
			complete = left == null;
		}
		return complete;
	}
	
	/**
	 * Retorna una representacion del nodo como una estructura arbolecente
	 *
	 * @param pre Prefijo del String
	 * @param tab Tabulacion del String
	 * @param n Nodo cuya jerarquia será retornada
	 * @return String con la informacion jerarquica del nodo
	 */
	protected String paintTree( String pre, String tab, INodoBinario n )
	{
		StringBuilder sBuilder = new StringBuilder( );
		if( value != null )
		{
			sBuilder.append( tab + pre + n.toString( ) );
			tab = tab.replace( "├", "│" );
			tab = tab.replace( "└", " " );
			pre = pre.replace( "L:", "" );
			pre = pre.replace( "R:", "" );
			tab += "     " + new String( new char[ n.toString( ).length( ) / 2 ] ).replace( '\0', ' ' );
			
			if( left != null )
			{
				String t2 = "└";
				if( right != null )
				{
					t2 = "├";
				}
				sBuilder.append( ( ( NodoBinarioKV<K, V> ) left ).paintTree( pre + "L:", tab + t2, left ) );
			}
			if( right != null )
			{
				sBuilder.append( ( ( NodoBinarioKV<K, V> ) right ).paintTree( pre + "R:", tab + "└", right ) );
			}
		}
		return sBuilder.toString( );
	}
	
	/**
	 * Asigna el hijo izquierdo del nodo
	 *
	 * @param left Hijo izquierdo a ser asignado
	 */
	@Override
	public void setLeft( INodoBinario left )
	{
		this.left = left;
	}
	
	/**
	 * Asigna el hijo derecho del nodo
	 *
	 * @param right Hijo derecho a ser asignado
	 */
	@Override
	public void setRight( INodoBinario right )
	{
		this.right = right;
	}
	
	/**
	 * Retorna el numero de elementos en la jerarquia del nodo
	 *
	 * @return Numero de elementos en la jerarquia del nodo
	 */
	@Override
	public int size( )
	{
		int size = 1;
		if( left != null )
		{
			size += left.size( );
		}
		if( right != null )
		{
			size += right.size( );
		}
		return size;
	}
	
	/**
	 * Retorna una representacion arbolecente de la jerarquia del nodo
	 *
	 * @return Representacion arbolecente de la jerarquia del nodo
	 */
	@Override
	public String toStringTree( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		sBuilder.append( paintTree( "─────", "", this ) );
		return sBuilder.toString( );
	}
	
	@Override
	public Iterable<?> elements( )
	{
		Queue<Entry<K, V>> queue = new Queue<>( );
		if( left != null )
		{
			for( Entry<K, V> e : ( Iterable<Entry<K, V>> ) left.elements( ) )
			{
				queue.enqueue( e );
			}
		}
		queue.enqueue( this );
		if( right != null )
		{
			for( Entry<K, V> e : ( Iterable<Entry<K, V>> ) right.elements( ) )
			{
				queue.enqueue( e );
			}
		}
		return queue;
	}
}