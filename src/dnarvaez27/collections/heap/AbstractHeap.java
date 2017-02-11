package dnarvaez27.collections.heap;

import java.util.Comparator;

/**
 * Clase abstracta que deine las funcinoalidades compartidas de la estructura Heap
 * 
 * @author dnarvaez27
 * @param <T> Tipo de datos a alojar
 */
public abstract class AbstractHeap<T extends Comparable<? super T>> implements IHeap<T>
{
	/**
	 * Atributo de clase de los elementos, utilizado para el uso de {@link #add(Object...)}
	 */
	protected Class<T> classElement;
	
	/**
	 * Comparador que permite al Heap tener un criterio de ordenamiento fuera de la clase<br>
	 * Permite que sea un Max-Heap o un Min-Heap
	 */
	protected Comparator<T> comparator;
	
	/**
	 * Tamano del Heap
	 */
	protected int size;
	
	/**
	 * Crea un Heap básico<br>
	 * Inicializa el tamaño en 0
	 */
	public AbstractHeap( )
	{
		size = 0;
	}
	
	/**
	 * Inicializa el comparador
	 *
	 * @param comparator comparador a utilizar
	 */
	public AbstractHeap( Comparator<T> comparator )
	{
		this.comparator = comparator;
	}
	
	/**
	 * Inicializa la orientacion del Heap
	 *
	 * @param orientation Orientacion del Heap: {@link IHeap#NORMAL} o {@link IHeap#REVERSE}
	 */
	public AbstractHeap( int orientation )
	{
		this( );
		if( orientation == REVERSE )
		{
			this.comparator = new Comparator<T>( )
			{
				@Override
				public int compare( T o1, T o2 )
				{
					return o2.compareTo( o1 );
				}
			};
		}
	}
	
	/**
	 * Establece la orientacion y el comparador del Heap<br>
	 *
	 * @param orientation Orientacion del Heap: {@link IHeap#NORMAL} o {@link IHeap#REVERSE}
	 * @param comparator Comparador a utilizar
	 */
	protected AbstractHeap( int orientation, Comparator<T> comparator )
	{
		this( orientation );
		if( orientation == NORMAL )
		{
			this.comparator = comparator;
		}
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
		if( classElement == null )
		{
			throw new NullPointerException( "Class must be initialized. Use setClass( Class )" );
		}
		for( Object object : values )
		{
			add( classElement.cast( object ) );
		}
	}
	
	@Override
	public void clear( )
	{
		size = 0;
	}
	
	@Override
	public boolean isEmpty( )
	{
		return size == 0;
	}
	
	@Override
	public void setClass( Class<T> classElement )
	{
		this.classElement = classElement;
	}
	
	@Override
	public int size( )
	{
		return size;
	}
}