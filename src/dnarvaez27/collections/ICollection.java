package dnarvaez27.collections;

/**
 * Interface que modela las funcionalidades basicas de las estructuras de datos
 * 
 * @author dnarvaez27
 */
public interface ICollection
{
	/**
	 * Agrega elemento(s) a la estructura<br>
	 * Para información especifica referirse a la documentación de la estructura
	 * 
	 * @param values Elemento(s) a agregar
	 */
	public void addElements( Object ... values );
	
	/**
	 * Retorna el numero de elementos de la coleccion
	 * 
	 * @return Numero de elementos de la coleccion
	 */
	public int size( );
	
	/**
	 * Verifica si hay o no elementos en la coleccion
	 * 
	 * @return True si hay uno o mas elementos, False de lo contrario
	 */
	public boolean isEmpty( );
	
	/**
	 * Retorna un Iterable de los elementos de la coleccion
	 * 
	 * @return Iterable de los elementos de la coleccion
	 */
	public Iterable<?> elements( );
	
	/**
	 * Elimina todos los elementos de la coleccion
	 */
	public void clear( );
}