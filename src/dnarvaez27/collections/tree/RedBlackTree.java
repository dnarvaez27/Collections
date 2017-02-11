package dnarvaez27.collections.tree;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.elements.NodoBinarioKV;

/**
 * Clase que representa la estructura de un arbol Rojo-Negro<br>
 * Este arbol permite un auto-balanceo de sus nodos, haciendo de las busquedas más eficientes. <br>
 * El fuerte del arbol rojo-negro son las inserciones y remociones de elementos
 *
 * @author d.narvaez11
 * @author d.jaimes
 * @param <K> Tipo de la llave
 * @param <V> Tipo del valor
 */
@SuppressWarnings( "unchecked" )
public class RedBlackTree<K extends Comparable<K>, V> extends AbstractTree<K, V>
{
	/**
	 * Clase que representa un nodo del arbol Rojo-Negro<br>
	 * Un nodo contiene una tupla de Llave-Valor y un Color
	 *
	 * @author d.narvaez11
	 * @author d.jaimes
	 */
	private class NodoTree extends NodoBinarioKV<K, V>
	{
		/**
		 * Constante para representar un Nodo negro
		 */
		private static final int BLACK = 0;
		
		/**
		 * Constante para representar un Nodo rojo
		 */
		private static final int RED = 1;
		
		/**
		 * Color asociado al nodo
		 */
		private int color;
		
		/**
		 * Padre del nodo
		 */
		private NodoTree parent;
		
		/**
		 * Construye un nodo rojo con los elementos dados por parámetro
		 *
		 * @param parent Padre del nodo, Null si es la raiz
		 * @param key Llave del nodo
		 * @param value Valor del nodo
		 */
		public NodoTree( NodoTree parent, K key, V value )
		{
			this( parent, key, value, RED );
		}
		
		/**
		 * Construye un nodo con los parametros dados por parametro
		 *
		 * @param parent Padre del nodo
		 * @param key Llave del nodo
		 * @param value Valor del nodo
		 * @param color Color del nodo
		 */
		public NodoTree( NodoTree parent, K key, V value, int color )
		{
			super( key, value );
			this.parent = parent;
			this.color = color;
		}
		
		/**
		 * Agrega un nodo rojo a su jerarquia
		 *
		 * @param key Llave del nuevo nodo
		 * @param value Valor del nuevo nodo
		 * @see RedBlackTree.NodoTree#add(Comparable, Object, int)
		 */
		public void add( K key, V value )
		{
			add( key, value, RED );
		}
		
		/**
		 * Agrega un nuevo nodo a su jerarquia, o a si mismo si coinciden las llaves
		 *
		 * @param key Llave del nuevo nodo
		 * @param value Valor del nuevo nodo
		 * @param color Color del nuevo nodo
		 */
		public void add( K key, V value, int color )
		{
			int comp = this.key.compareTo( key );
			if( comp == 0 )
			{
				setValue( value );
			}
			else
			{
				NodoTree nodo = new NodoTree( this, key, value, color );
				if( comp > 0 )
				{
					if( left == null )
					{
						left = nodo;
					}
					else
					{
						( ( NodoTree ) left ).add( key, value );
					}
				}
				else
				{
					if( right == null )
					{
						right = nodo;
					}
					else
					{
						( ( NodoTree ) right ).add( key, value );
					}
				}
				verificarInsert( );
			}
		}
		
		/**
		 * Elimina un nodo de la jerarquia con la llave dada por parametro
		 *
		 * @param key Llave del nodo a ser eliminado
		 * @return Valor del nodo eliminado, null si el nodo con la llave dada no existe
		 */
		public V delete( K key )
		{
			V valueToReturn = null;
			int comp = key.compareTo( this.key );
			if( comp == 0 )
			{
				valueToReturn = value;
				if( left != null )
				{
					NodoTree miReemplazo = ( ( NodoTree ) left ).removeMostRight( );
					setValue( miReemplazo.getValue( ) );
					setKey( miReemplazo.getKey( ) );
					setColor( miReemplazo.color );
					// setLeft( left.getLeft( ) );
				}
				else
				{
					if( parent.getLeft( ).equals( this ) )
					{
						parent.setLeft( left );
					}
					else
					{
						parent.setRight( right );
					}
				}
			}
			else if( comp < 0 )
			{
				if( left != null )
				{
					valueToReturn = ( ( NodoTree ) left ).delete( key );
				}
			}
			else
			{
				if( right != null )
				{
					valueToReturn = ( ( NodoTree ) right ).delete( key );
				}
			}
			// verificarDelete( );//TODO
			return valueToReturn;
		}
		
		/**
		 * Verifica si el hijo derecho es rojo, de ser asi se rota hacia la izquierda
		 *
		 * @see #verificarInsert()
		 * @see #leftRotatation()
		 */
		private void derechaRojo( )
		{
			if( ( right != null ) && ( ( NodoTree ) right ).isRed( ) )
			{
				// System.out.println( "Derecho Rojo: " + key );
				leftRotatation( );
				// System.out.println( root.toStringHeap( ) );
			}
		}
		
		/**
		 * Verifica si existen dos nodos rojos en linea. De ser así se rota hacia la derecha
		 *
		 * @see #verificarInsert()
		 * @see #rightRotatation()
		 */
		private void dosEnLinea( )
		{
			if( ( left != null ) && ( ( NodoTree ) left ).isRed( ) )
			{
				if( ( left.getLeft( ) != null ) && ( ( NodoTree ) left.getLeft( ) ).isRed( ) )
				{
					// System.out.println( "Dos en linea: " + key );
					rightRotatation( );
					// System.out.println( root.toStringHeap( ) );
				}
			}
		}
		
		/**
		 * Reasigna los padres de los nodos a sus correspondientes
		 */
		private void fixParents( )
		{
			if( left != null )
			{
				( ( NodoTree ) left ).setParent( this );
				( ( NodoTree ) left ).fixParents( );
			}
			if( right != null )
			{
				( ( NodoTree ) right ).setParent( this );
				( ( NodoTree ) right ).fixParents( );
			}
		}
		
		/**
		 * Retorna el valor del nodo en su jerarquia o de si mismo si las llaves coinciden
		 *
		 * @param key Llave del valor en interes
		 * @return El valor del nodo con la llave dada o null si no existe en la jerarquia
		 */
		public V get( K key )
		{
			V toReturn = null;
			int comp = key.compareTo( this.key );
			if( comp == 0 )
			{
				toReturn = value;
			}
			else if( comp < 0 )
			{
				if( left != null )
				{
					toReturn = ( ( NodoTree ) left ).get( key );
				}
			}
			else
			{
				if( right != null )
				{
					toReturn = ( ( NodoTree ) right ).get( key );
				}
			}
			return toReturn;
		}
		
		/**
		 * Verifica si los hijos del nodo son rojos.
		 * De ser asi se cambian los colores de los hijos y este nodo cambia su color a rojo
		 *
		 * @see #verificarInsert()
		 */
		private void hijosRojos( )
		{
			if( ( left != null ) && ( right != null ) )
			{
				if( ( ( NodoTree ) left ).isRed( ) && ( ( NodoTree ) right ).isRed( ) )
				{
					// System.out.println( "Hijos Rojos: " + key );
					
					( ( NodoTree ) left ).setColor( BLACK );
					( ( NodoTree ) right ).setColor( BLACK );
					this.color = RED;
					verificarInsert( );
					// System.out.println( root.toStringHeap( ) );
				}
			}
		}
		
		/**
		 * Verifica si el nodo es rojo o no
		 *
		 * @return True si el nodo es rojo, False de lo contrario
		 */
		public boolean isRed( )
		{
			return color == RED;
		}
		
		/**
		 * Rota el nodo hacia la izquierda<br>
		 */
		private void leftRotatation( )
		{
			// <--
			if( right != null )
			{
				// System.out.println( "To the left: " + key );
				NodoTree leftState = new NodoTree( this, key, value );
				leftState.setLeft( left );
				if( right.getLeft( ) != null )
				{
					NodoTree tempLeft = ( NodoTree ) right.getLeft( );
					leftState.setRight( tempLeft );
				}
				right.setLeft( null );
				
				setValue( ( ( Entry<K, V> ) right ).getValue( ) );
				setKey( ( ( Entry<K, V> ) right ).getKey( ) );
				setRight( right.getRight( ) );
				setLeft( leftState );
				
				verificarInsert( );
			}
		}
		
		/**
		 * Elimina y retorna el nodo que se encuentre lo más a la derecha posible
		 *
		 * @return Nodo que se encuentre lo más a la derecha posible
		 */
		private NodoTree removeMostRight( )
		{
			
			if( right != null )
			{
				return ( ( NodoTree ) right ).removeMostRight( );
			}
			else
			{
				NodoTree toReturn = this;
				if( ( parent.getRight( ) != null ) && ( ( dnarvaez27.collections.elements.Entry<K, V> ) parent.getRight( ) ).getKey( ).equals( key ) )
				{
					parent.setRight( left );
				}
				else
				{
					parent.setLeft( left );
				}
				
				return toReturn;
			}
		}
		
		/**
		 * Rota el nodo hacia la derecha
		 */
		private void rightRotatation( )
		{
			// -->
			if( left != null )
			{
				// System.out.println( " To the Right: " + key );
				NodoTree rightState = new NodoTree( this, key, value );
				rightState.setRight( right );
				
				if( left.getRight( ) != null )
				{
					NodoTree tempRight = ( NodoTree ) left.getRight( );
					rightState.setLeft( tempRight );
				}
				left.setRight( null );
				
				setValue( ( ( Entry<K, V> ) left ).getValue( ) );
				setKey( ( ( Entry<K, V> ) left ).getKey( ) );
				setLeft( left.getLeft( ) );
				setRight( rightState );
				
				verificarInsert( );
			}
		}
		
		/**
		 * Asigna el color del nodo
		 *
		 * @param color Color del nodo a ser asignado
		 */
		public void setColor( int color )
		{
			this.color = color;
		}
		
		/**
		 * Asigna el padre del nodo
		 *
		 * @param parent Padre del nodo a ser asignado
		 */
		public void setParent( NodoTree parent )
		{
			this.parent = parent;
		}
		
		/**
		 * Retorna una representacion del nodo
		 */
		@Override
		public String toString( )
		{
			return String.format( "%s( %s )%s", key.toString( ), value.toString( ), isRed( ) ? "*" : "" );
		}
		
		/**
		 * Verifica los posibles casos para que el arbol rojo-negro mantenga su balance
		 */
		private void verificarInsert( )
		{
			hijosRojos( );
			derechaRojo( );
			dosEnLinea( );
		}
	}
	
	/**
	 * Construye un arbol Rojo-Negro<br>
	 * Se inicializa con valores de clase dados por parametro, para el uso del método {@link #add(Object...)}
	 *
	 * @param classKey Clase de la llave
	 * @param classValue Clase del valor
	 */
	public RedBlackTree( Class<K> classKey, Class<V> classValue )
	{
		super( );
		setClasses( classKey, classValue );
	}
	
	/**
	 * Construye un Arbol Rojo-Negro
	 */
	public RedBlackTree( )
	{
		super( );
	}
	
	/**
	 * Agrega la tupla Llave-Valor al arbol
	 *
	 * @param key Llave de la tupla
	 * @param value Valor de la llave
	 */
	public void add( K key, V value )
	{
		if( key == null )
		{
			throw new NullPointerException( "La llave no puede ser null" );
		}
		if( value == null )
		{
			delete( key );
		}
		else
		{
			if( root == null )
			{
				root = new NodoTree( null, key, value );
			}
			else
			{
				root.add( key, value );
				( ( NodoTree ) root ).fixParents( );
			}
		}
	}
}