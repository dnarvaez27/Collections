package dnarvaez27.collections.tree;

/**
 * Interface que constituye las funcionalidades basicas de un arbol
 * con entradas de tipo Llave-Valor
 * 
 * @author dnarvaez27
 * @param <K> Tipo de las Llaves de las entradas
 * @param <V> Tipo de los valores de las entradas F
 */
public interface ITree<K, V>
{
	/**
	 * Agrega una nueva entrada dada la llave y el valor de esta
	 * 
	 * @param key Llave de la entrada a ser agregada
	 * @param value Valor de la entrada a ser agregada
	 */
	public void add( K key, V value );
	
	/**
	 * Elimina la entrada cuya llave coincide con la dada por parametro
	 * 
	 * @param key Llave de la entrada a eliminar
	 * @return Valor asociado a la entrada eliminada
	 */
	public V delete( K key );
	
	/**
	 * Retorna el valor asocidado a la entrada con la llave dada por parametro
	 * 
	 * @param key Llave de la entrada de interes
	 * @return El valor de la entrada cuya llave coincide con la dada por parametro
	 */
	public V get( K key );
	
	/**
	 * Verifica si el arbol esta vacio o no
	 * 
	 * @return True si el arbol esta vacio, False de lo contrario
	 */
	public boolean isEmpty( );
	
	/**
	 * Establece las clases a utilizar en el uso de varargs {@link dnarvaez27.ICollection#add(Object...)}
	 *
	 * @param classKey Clase de las llaves
	 * @param classValue Clase de los valores
	 */
	public void setClasses( Class<K> classKey, Class<V> classValue );
	
	/**
	 * Retorna el numero de elementos del arbol
	 * 
	 * @return Numero de elementos del arbol
	 */
	public int size( );
	
	/**
	 * Retorna la altura del arbol
	 * 
	 * @return Altura del arbol
	 */
	public int height( );
	
	/**
	 * Elimina los elementos del arbol
	 */
	public void clear( );
}