package dnarvaez27.collections.graph;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.exceptions.IllegalOperationException;
import dnarvaez27.collections.graph.algorithms.Algorithms;
import dnarvaez27.collections.hashtable.HashTableLP;
import dnarvaez27.collections.list.Stack;
import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;

/**
 * Clase que modela un Grafo
 *
 * @author dnarvaez27
 * @param <I> Tipo del Identificador de los Vertices
 * @param <E> Tipo del Elemento que contendrán los Vertices
 */
public class Graph<I, E> implements Algorithms
{
	/**
	 * Clase que representa un Arco de un Grafo
	 *
	 * @author dnarvaez27
	 */
	public class Edge implements Comparable<Edge>
	{
		/**
		 * Identificador del Vertice de Salida del Arco
		 */
		private I from;
		
		/**
		 * Identificador del Vertice de Llegada del Arco
		 */
		private I to;
		
		/**
		 * Lista de Pesos del Arco
		 */
		private DoubleLinkedList<Weight> weights;
		
		/**
		 * Objeto asociado al Arco
		 */
		private Object object;
		
		/**
		 * Construye un Arco de un Vertice de Salida y uno de Llegada a partir de sus Identificadores.<br>
		 * Se agrega un Peso DEFAULT, con valor de 1
		 *
		 * @param from Identificador del Vertice de Salida del Arco
		 * @param to Identificador del Vertice de Llegada del Arco
		 */
		public Edge( I from, I to )
		{
			this( from, to, new Weight( 1, Weight.DEFAULT ), null );
		}
		
		/**
		 * Construye un Arco de un Vertice de Salida y uno de Llegada a partir de sus Identificadores.<br>
		 * Se agrega un Peso DEFAULT, con valor de 1<br>
		 * Se agrega un objeto asociado al Arco
		 * 
		 * @param from Identificador del Vertice de Salida del Arco
		 * @param to Identificador del Vertice de Llegada del Arco
		 * @param object Objeto asociado al Arco
		 */
		public Edge( I from, I to, Object object )
		{
			this( from, to, new Weight( 1, Weight.DEFAULT ), object );
		}
		
		/**
		 * Construye un Arco de un Vertice de Salida y uno de Llegada a partir de sus Identificadores.<br>
		 * Se agrega un Peso del arco cuyo identificador esta dado por el String DEFAULT de la clase.
		 *
		 * @param from Identificador del Vertice de Salida del Arco
		 * @param to Identificador del Vertice de Llegada del Arco
		 * @param weight Peso a asociar con el Arco. Peso con Identificador DEFAULT
		 */
		public Edge( I from, I to, double weight )
		{
			this( from, to, new Weight( weight, Weight.DEFAULT ), null );
		}
		
		/**
		 * Construye un Arco de un Vertice de Salida y uno de Llefada a partir de sus Identificadores.<br>
		 * Se agregan los peso, dados por parámetro, al Arco<br>
		 * Se agrega un objeto asociado al Arco
		 *
		 * @param from Identificador del Vertice de Salida del Arco
		 * @param to Identificador del Vertice de Llegada del Arco
		 * @param lWeights Conjunto de Pesos a ser asociados al Arco.
		 */
		public Edge( I from, I to, Iterable<Weight> lWeights, Object object )
		{
			this( from, to, object );
			for( Weight weight : lWeights )
			{
				weights.add( weight );
			}
		}
		
		/**
		 * Construye un Arco de un Vertice de Salida y uno de Llefada a partir de sus Identificadores.<br>
		 * Se agregan los peso, dados por parámetro, al Arco
		 *
		 * @param from Identificador del Vertice de Salida del Arco
		 * @param to Identificador del Vertice de Llegada del Arco
		 * @param lWeights Conjunto de Pesos a ser asociados al Arco.
		 */
		public Edge( I from, I to, Iterable<Weight> lWeights )
		{
			this( from, to );
			for( Weight weight : lWeights )
			{
				weights.add( weight );
			}
		}
		
		/**
		 * Construye un Arco de un Vertice de Salida y uno de Llegada a partir de sus Identificadores.<br>
		 * Se agrega un Peso del arco dado por parámetro
		 *
		 * @param from Identificador del Vertice de Salida del Arco
		 * @param to Identificador del Vertice de Llegada del Arco
		 * @param weight Peso a asociar con el Arco
		 */
		public Edge( I from, I to, Weight weight, Object object )
		{
			this.from = from;
			this.to = to;
			this.object = object;
			this.weights = new DoubleLinkedList<>( );
			weights.add( weight );
		}
		
		/**
		 * Compara los Arcos a través del total de los pesos
		 */
		@Override
		public int compareTo( Edge o )
		{
			return Double.compare( getTotalWeight( ), o.getTotalWeight( ) );
		}
		
		/**
		 * Verifica si el Peso cuyo Identificador es dado por parámetro existe en el Arco
		 *
		 * @param id Identificador del Peso de interés
		 * @return True si el peso con el Identificador asociado existe en el Arco, False de lo contrario.
		 */
		public boolean existsWeight( String id )
		{
			for( Weight weight : weights )
			{
				if( weight.getIdentifier( ).equals( id ) )
				{
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Retorna el Identificador del Vertice de Salida
		 *
		 * @return Identificador del Vertice de Salida
		 */
		public I getFrom( )
		{
			return from;
		}
		
		/**
		 * Retorna el Identificador del Vertice de Llegada
		 *
		 * @return Identificador del Vertice de Llegada
		 */
		public I getTo( )
		{
			return to;
		}
		
		/**
		 * Retorna el Objeto asociado al Arco
		 * 
		 * @return Objeto asociado al Arco
		 */
		public Object getObject( )
		{
			return object;
		}
		
		/**
		 * Retorna el valor númerico total de los Pesos del Arco
		 *
		 * @return Valor númerico total de los Pesos del Arco
		 */
		public double getTotalWeight( )
		{
			double weight = 0;
			for( Weight w : weights )
			{
				weight += w.getWeight( );
			}
			return weight;
		}
		
		/**
		 * Retorna la lista de Pesos asociados al Arco
		 *
		 * @return Lista de Pesos asociados al Arco
		 */
		public DoubleLinkedList<Weight> getWeight( )
		{
			return weights;
		}
		
		/**
		 * Retorna el valor númerico del Peso cuyo Identificador es dado por parámetro
		 *
		 * @param id Identificador del Peso de interés
		 * @return Valor númerico del Peso cuyo Identificador es dado por parámetro. -1 Si el Peso buscado no existe
		 */
		public double getWeigth( String id )
		{
			for( Weight weight : weights )
			{
				if( weight.getIdentifier( ).equals( id ) )
				{
					return weight.getWeight( );
				}
			}
			return -1;
		}
		
		/**
		 * Elimina y retorna el Peso asociado al Identificador dado por parámetro
		 *
		 * @param id Identificador del Peso a eliminar
		 * @return Valor númerico del Peso eliminado. -1 Si el Peso a eliminar no existe
		 * @throws IllegalArgumentException Si se intenta eliminar el Peso DEFAULT
		 */
		public double removeWeight( String id )
		{
			if( id.equals( Weight.DEFAULT ) )
			{
				throw new IllegalArgumentException( "Cannot remove DEFAULT weight. To update DEFAULT weight, use setWeight(String,double)" );
			}
			
			for( Weight weight : weights )
			{
				if( weight.getIdentifier( ).equals( id ) )
				{
					weights.remove( weight );
					return weight.getWeight( );
				}
			}
			return -1;
		}
		
		/**
		 * Actualiza el valor númerico del Peso cuyo Identificador es dado por parámero
		 *
		 * @param id Identificador del Peso a actualizar
		 * @param weight Nuevo valor númerico del Peso
		 * @return Valor númerico del Peso asociado anteriormente. -1 Si el peso a actualizar no existe.
		 */
		public double setWeight( String id, double weight )
		{
			for( Weight w : weights )
			{
				if( w.getIdentifier( ).equals( id ) )
				{
					double old = w.getWeight( );
					w.setWeight( weight );
					return old;
				}
			}
			return -1;
		}
		
		/**
		 * Retorna una representación del Arco. From - To
		 */
		@Override
		public String toString( )
		{
			return from + "-" + to;
		}
		
		/**
		 * Retorna una representación del Arco. To
		 *
		 * @return
		 */
		public String toStringTo( )
		{
			return to.toString( );
		}
	}
	
	/**
	 * Clase que representa un Vertice del Grafo
	 *
	 * @author dnarvaez27
	 */
	public class Vertex
	{
		/**
		 * Arcos de Salida del Vertice
		 */
		private HashTableLP<I, Edge> edges;
		
		/**
		 * Elemento asociado al Vertice
		 */
		private E element;
		
		/**
		 * Identificador del Vertice
		 */
		private I identifier;
		
		/**
		 * Construye un Vertice con un Identificador y un Elemento asociado
		 *
		 * @param identifier Identificador del Vertice
		 * @param element Elemento del Vertice
		 */
		public Vertex( I identifier, E element )
		{
			this.identifier = identifier;
			this.element = element;
			this.edges = new HashTableLP<>( );
		}
		
		/**
		 * Agrega un Arco, si no existe, al Vertice
		 *
		 * @param edge Arco a agregar
		 */
		public void addEdge( Edge edge )
		{
			if( edges.get( edge.to ) == null )
			{
				edges.put( edge.getTo( ), edge );
			}
		}
		
		/**
		 * Retorna los Arcos asociados al Vertice
		 *
		 * @return Arcos asociados al Vertice
		 */
		public Iterable<Edge> getValuesEdges( )
		{
			return edges.values( );
		}
		
		public HashTableLP<I, Edge> getEdges( )
		{
			return edges;
		}
		
		/**
		 * Retorna el Arco que se dirige hacia el Vertice con el Identificador dado por parámetro
		 *
		 * @param to Identificador del Vertice de Llegada del Arco de interés
		 * @return Arco que se dirige hacia el Vertice con el Identificador dado por parámetro
		 */
		public Edge getEdgeTo( I to )
		{
			return edges.get( to );
		}
		
		/**
		 * Retorna el Elemento asociado al Vertice
		 *
		 * @return Elemento asociado al Vertice
		 */
		public E getElement( )
		{
			return element;
		}
		
		/**
		 * Retorna el Identificador asociado al Vertice
		 *
		 * @return Identificador asociado al Vertice
		 */
		public I getIdentifier( )
		{
			return identifier;
		}
		
		/**
		 * Retorna el peso total de los Arcos del Vertice
		 *
		 * @return Peso total de los Arcos del Vertice
		 */
		public double getWeight( )
		{
			double weight = 0;
			for( Entry<I, Edge> e : edges.entries( ) )
			{
				weight += e.getValue( ).getTotalWeight( );
			}
			return weight;
		}
		
		/**
		 * Elimina y Retorna el Arco hacia el Vertice cuyo Identificador es dado por parámetro
		 *
		 * @param to Identificador del Vertice cuyo Arco de llegada se eliminará
		 * @return Arco aliminado
		 */
		public Edge removeEdge( I to )
		{
			for( Entry<I, Edge> e : edges.entries( ) )
			{
				if( e.getValue( ).getTo( ).equals( to ) )
				{
					Edge ed = e.getValue( );
					edges.delete( to );
					return ed;
				}
			}
			return null;
		}
		
		/**
		 * Retorna una representación del Vertice. Identificador
		 */
		@Override
		public String toString( )
		{
			return identifier.toString( );
		}
	}
	
	/**
	 * Clase que representa el Peso de los Arcos
	 *
	 * @author dnarvaez27
	 */
	public static class Weight
	{
		/**
		 * Constante asociada al Peso por defecto de los Arcos
		 */
		public static final String DEFAULT = "Default";
		
		/**
		 * Identificador del Peso
		 */
		private String identifier;
		
		/**
		 * Valor númerico del Peso
		 */
		private double weight;
		
		/**
		 * Construye un Peso a partir de un Identificador y un valor numérico
		 *
		 * @param weight Valor asociado al Peso
		 * @param identifier Identificador del Peso
		 */
		public Weight( double weight, String identifier )
		{
			this.weight = weight;
			this.identifier = identifier;
		}
		
		/**
		 * Retorna el Identificador del Peso
		 *
		 * @return Identificador del Peso
		 */
		public String getIdentifier( )
		{
			return identifier;
		}
		
		/**
		 * Retorna el valor numérico del Peso
		 *
		 * @return Valor numérico del Peso
		 */
		public double getWeight( )
		{
			return weight;
		}
		
		/**
		 * Establece el Identificador del Peso
		 *
		 * @param identifier Identificador nuevo del Peso
		 */
		public void setIdentifier( String identifier )
		{
			this.identifier = identifier;
		}
		
		/**
		 * Establece el valor númerico del Peso
		 *
		 * @param weight Valor numérico nuevo del Peso
		 */
		public void setWeight( double weight )
		{
			this.weight = weight;
		}
		
		/**
		 * Retorna una representación del Peso. Id: Valor
		 */
		@Override
		public String toString( )
		{
			return identifier + ": " + weight;
		}
	}
	
	/**
	 * Constante para unir Vertices del Grafo en el método toString
	 */
	public static final String ARROW = "->";
	
	/**
	 * Constante para representar null en métodos toString
	 */
	public static final String EMPTY = "-";
	
	/**
	 * HashTable que contiene la información asociada al grafo.<br>
	 * Representa el grafo implementado como una lista de adyacencia
	 */
	private HashTableLP<I, Vertex> adjacencyList;
	
	/**
	 * Representa el tipo del grafo. Dirijido o no
	 */
	private boolean directed;
	
	/**
	 * Número de arcos del grafo
	 */
	private int numEdges;
	
	/**
	 * Número de vertices del grafo
	 */
	private int numVertex;
	
	/**
	 * Construye un Grafo cuyo tipo (Dirifijo, No-Dirijido) es establecido por parámetro
	 *
	 * @param directed Establece si el Grafo es Dirijido (True) o no (False)
	 */
	public Graph( boolean directed )
	{
		adjacencyList = new HashTableLP<>( );
		this.directed = directed;
	}
	
	/**
	 * Construye un Grafo a partir de otro,
	 * junto con todas sus caracteristicas
	 *
	 * @param G Grafo a ser clonado
	 */
	public Graph( Graph<I, E> G )
	{
		this( G.numVertex, G.directed );
		this.numVertex = G.numVertex;
		this.adjacencyList = G.adjacencyList;
	}
	
	/**
	 * Construye un Grafo a partir de los Vertices y Arcos de otro.<br>
	 * Se puede cambiar el tipo de Grafo (Dirijido, No-Dirijido) por medio del parámetro <code>directed</code><br>
	 *
	 * @param G Grafo a ser clonado
	 * @param directed Establece si el Grafo es Dirijido (True) o no (False)
	 */
	public Graph( Graph<I, E> G, boolean directed )
	{
		this( G.numVertex, directed );
		this.numVertex = G.numVertex;
		Iterable<Entry<I, Vertex>> it = G.getGraph( ).entries( );
		for( Entry<I, Vertex> entry : it )
		{
			addVertex( entry.getKey( ), entry.getValue( ).getElement( ) );
		}
		for( Entry<I, Vertex> entry : it )
		{
			for( Edge edge : entry.getValue( ).getValuesEdges( ) )
			{
				if( !directed )
				{
					addEdge( edge.from, edge.to, true, edge.weights, edge.weights, edge.object );
				}
				else
				{
					if( adjacencyList.get( edge.to ).edges.get( edge.from ) == null )
					{
						addEdge( edge.from, edge.to, false, edge.weights, edge.weights, edge.object );
					}
				}
			}
		}
	}
	
	/**
	 * Construye un Grafo a partir de los Vertices deotro. Junto con los arcos dados por parámetro<br>
	 * Se puede cambiar el tipo de Grafo (Dirijido, No-Dirijido) por medio del parámetro <code>directed</code><br>
	 *
	 * @param G Grafo a ser clonado
	 * @param directed Establece si el Grafo es Dirijido (True) o no (False)
	 * @param edges Conjunto de Arcos que serán asociados en el Grafo
	 */
	public Graph( Graph<I, E> G, boolean directed, Iterable<Edge> edges )
	{
		this( G.numVertex, directed );
		this.numVertex = G.numVertex;
		
		for( Entry<I, Vertex> edge : G.adjacencyList.entries( ) )
		{
			addVertex( edge.getKey( ), edge.getValue( ).getElement( ) );
		}
		
		for( Edge edge : edges )
		{
			addEdge( edge.from, edge.to, edge.weights );
		}
	}
	
	/**
	 * Construye un Grafo con un número de vertices inicial dado por parámetro
	 *
	 * @param numVertex Número de vertices inicial del Grafo
	 */
	public Graph( int numVertex )
	{
		this( numVertex, false );
	}
	
	/**
	 * Construye un Grafo con un número de vertices dado por parámetro.<br>
	 * Además establece el tipo del Grafo (Dirijido o No-Dirijido)
	 *
	 * @param numVertex Número de vertices iniciales del grafo
	 * @param directed Establece si el Grafo es Dirijido (True) o no (False)
	 */
	public Graph( int numVertex, boolean directed )
	{
		adjacencyList = new HashTableLP<>( numVertex, 0.75f );
		this.directed = directed;
	}
	
	public static void main( String[ ] args )
	{
		Graph<Integer, String> G = new Graph<>( !true );
		G.addVertex( 0, "a" );
		G.addVertex( 1, "b" );
		G.addVertex( 2, "c" );
		G.addVertex( 3, "d" );
		G.addVertex( 4, "e" );
		G.addVertex( 5, "f" );
		
		G.addEdge( 0, 1, 2 );
		G.addEdge( 0, 2, 3 );
		G.addEdge( 1, 3, 5 );
		G.addEdge( 1, 4, 2 );
		G.addEdge( 3, 4, 1 );
		G.addEdge( 3, 5, 2 );
		G.addEdge( 4, 5, 4 );
		
		System.out.println( "Undirected: \n" + G.toString( ) );
		
		Dijkstra<Integer, String> dijkstra = new Dijkstra<>( G, 0, Weight.DEFAULT );
		System.out.println( dijkstra.pathTo( 2 ) );
		System.out.println( dijkstra.distTo( 2 ) );
		// System.out.println( G.getIncoming( 1 ) );
		// Graph<Integer, Integer>.LazyPrim lp = G.new LazyPrim( G, Weight.DEFAULT );
		// System.out.println( "MST:" );
		// Iterable<Graph<Integer, Integer>.Edge> it = lp.edges( );
		// for( Graph<Integer, Integer>.Edge edge : it )
		// {
		// System.out.println( edge );
		// }
		// System.out.println( "MST: \n" + new Graph<>( G, !false, it ) );
		// System.out.println( G.isInCycle( 6 ) );
		// System.out.println( "Directed: \n" + new Graph<>( G, true ) );
		// Graph<Integer, Integer> G2 = new Graph<Integer, Integer>( true );
		// G2.addVertex( 1, 1 );
		// G2.addVertex( 2, 2 );
		// G2.addVertex( 3, 3 );
		//
		// G2.addEdge( 1, 2 );
		// G2.addEdge( 2, 3 );
		// G2.addEdge( 3, 1 );
		//
		// System.out.println( G2 );
		// System.out.println( G2.removeEdge( 1, 2 ) );
		// System.out.println( G2 );
		// System.out.println( G2.isInCycle( 3 ) );
		
		// Graph<Integer, String>.BFS bfs = G.new BFS( G, 0 );
		// System.out.println( bfs.getEdgeTo( ) );
		//
		// System.out.println( G.getTopologicalOrderVertex( ) );
	}
	
	/**
	 * Agrega un Arco entre dos vertices cuyos Identificadores estan dados por parámetro<br>
	 * Se agrega un peso DEFAULT al arco, cuyo valor es 1.<br>
	 * <br>
	 * <i>Nota: Si el Grafo es No-Dirijido el Arco se agrega en ambas direcciones (Salida->Llegada, Llegada->Salida)</i>
	 *
	 * @param aVertex Identificador del Vertice de salida
	 * @param bVertex Identificador del Vertice de llegada
	 */
	public void addEdge( I aVertex, I bVertex )
	{
		addEdge( aVertex, bVertex, !directed, 1, 1 );
	}
	
	/**
	 * Agrega un Arco entre dos vertices cuyos identificadores estan dados por parámetro.<br>
	 * El Arco puede ser bi-direccional (<->) o uni-direccional (->), Esto se define por el parámtro both.<br>
	 * A el/los Arcos se les agrega un peso DEFAULT, cuyo valor es 1
	 *
	 * @param aVertex Identificador del Vertice de salida
	 * @param bVertex Identificador del Vertice de llegada
	 * @param both Establece si el Arco, también se debe agregar en direccion inversa
	 */
	public void addEdge( I aVertex, I bVertex, boolean both )
	{
		addEdge( aVertex, bVertex, both, 1, 1 );
	}
	
	/**
	 * Agrega un Arco con peso entre dos Vertices, cuyos identificadores estan dados por parámetro<br>
	 * El arco puede ser bi-direccional (<->) o uni-direccional (->). Esto se define por el parámetro both.<br>
	 * Si el arco es bi-direccional, se puede agregar un peso al Arco inverso<br>
	 * <br>
	 * <i> Nota: Si no es bi-direccional y se agrega un peso para el arco inverso, este no tendrá ningún efecto</i>
	 *
	 * @param aVertex Identificador del Vertice de salida
	 * @param bVertex Identificador del Vertice de llegada
	 * @param both Establece si el Arco, también se debe agregar en dirección inversa (Llegada -> Salida)
	 * @param aWeight Peso del Arco que va desde el Vertice de Salida al de Llegada
	 * @param bWeight Peso del Arco que va desde el Vertice de Llegada al de Salida
	 */
	public void addEdge( I aVertex, I bVertex, boolean both, double aWeight, double bWeight )
	{
		if( !( exists( bVertex ) && exists( aVertex ) ) )
		{
			throw new NullPointerException( "Los vertices de entrada deben existir" );
		}
		
		numEdges++;
		adjacencyList.get( aVertex ).addEdge( new Edge( aVertex, bVertex, aWeight ) );
		if( both )
		{
			adjacencyList.get( bVertex ).addEdge( new Edge( bVertex, aVertex, bWeight ) );
			if( directed )
			{
				numEdges++;
			}
		}
	}
	
	/**
	 * Agrega un Arco con varios pesos entre dos Vertices, cuyos identificadores estan dados por parámetro<br>
	 * El arco puede ser bi-direccional o uni-direccional. Esto se define por el parámetro both.<br>
	 * Si el arco es bi-direccional, se pueden agregar los pesos al Arco inverso<br>
	 * <br>
	 * <i>Nota: Si no es bi-direccional y se agregan los pesos para el arco inverso, estos no tendrán ningún efecto.</i>
	 *
	 * @param aVertex Identificador del Vertice de salida
	 * @param bVertex Identificador del Vertice de llegada
	 * @param both Establece si el Arco, también se debe agregar en dirección inversa (Llegada -> Salida)
	 * @param aWeights Conjunto de pesos que serán asociados al Arco que va desde el Vertice de Salida al de Llegada
	 * @param bWeights Conjunto de pesos que serán asociados al Arco que va desde el Vertice de Llegada al de Salida
	 */
	public void addEdge( I aVertex, I bVertex, boolean both, Iterable<Weight> aWeights, Iterable<Weight> bWeights, Object object )
	{
		if( !( exists( bVertex ) && exists( aVertex ) ) )
		{
			throw new NullPointerException( "Los vertices de entrada deben existir" );
		}
		numEdges++;
		adjacencyList.get( aVertex ).addEdge( new Edge( aVertex, bVertex, aWeights, object ) );
		if( both )
		{
			adjacencyList.get( bVertex ).addEdge( new Edge( bVertex, aVertex, bWeights ) );
			if( directed )
			{
				numEdges++;
			}
		}
	}
	
	/**
	 * Agrega un Arco con peso entre dos vertices cuyos identificadores estan dados por parámetro.<br>
	 * El peso se asocia tanto al Arco normal (->), como al Arco inverso (<-)<br>
	 * <br>
	 * <i>Nota: Si el Grafo es No-Dirijido el Arco se agrega en ambas direcciones (Salida->Llegada, Llegada->Salida)</i>
	 *
	 * @param aVertex Identificador del Vertice de Salida
	 * @param bVertex Identificador del Vertice de Llegada
	 * @param aWeight Peso del Arco que va desde el Vertice de Salida al de Llegada y viceversa
	 */
	public void addEdge( I aVertex, I bVertex, double aWeight )
	{
		addEdge( aVertex, bVertex, !directed, aWeight, aWeight );
	}
	
	/**
	 * Agrega un Arco <b>bi-direccional (<->)</b> con peso entre dos vertices cuyos identificadores estan dados por parámetro.<br>
	 *
	 * @param aVertex Identificador del Vertice de Salida
	 * @param bVertex Identificador del Vertice de Llegada
	 * @param aWeight Peso del Arco que va desde el Vertice de Salida al de Llegada
	 * @param bWeight Peso del Arco que va desde el Vertice de Llegada al de Salida
	 */
	public void addEdge( I aVertex, I bVertex, double aWeight, double bWeight )
	{
		addEdge( aVertex, bVertex, true, aWeight, bWeight );
	}
	
	/**
	 * Agrega un Arco con varios pesos entre dos Vertices, cuyos identificadores estan dados por parámetro.<br>
	 * <br>
	 * <i>Nota: Si el Grafo es No-Dirijido el Arco se agrega en ambas direcciones (Salida->Llegada, Llegada->Salida)</i>
	 *
	 * @param aVertex Identificador del Vertice de Salida
	 * @param bVertex Identificador del Vertice de Llegada
	 * @param aWeights Conjunto de Pesos del Arco que va desde el Vertice de Salida al de Llegada y viceversa
	 */
	public void addEdge( I aVertex, I bVertex, Iterable<Weight> aWeights )
	{
		addEdge( aVertex, bVertex, !directed, aWeights, aWeights, null );
	}
	
	/**
	 * Agrega un Arco con varios pesos entre dos Vertices, cuyos identificadores estan dados por parámetro.<br>
	 * Se agrega un objeto asociado al Arco<br>
	 * <br>
	 * <i>Nota: Si el Grafo es No-Dirijido el Arco se agrega en ambas direcciones (Salida->Llegada, Llegada->Salida)</i>
	 *
	 * @param aVertex Identificador del Vertice de Salida
	 * @param bVertex Identificador del Vertice de Llegada
	 * @param aWeights Conjunto de Pesos del Arco que va desde el Vertice de Salida al de Llegada y viceversa
	 */
	public void addEdge( I aVertex, I bVertex, Iterable<Weight> aWeights, Object object )
	{
		addEdge( aVertex, bVertex, !directed, aWeights, aWeights, object );
	}
	
	/**
	 * Agrega un Vertice al Grafo, con un ID dado que identificará al Vertice
	 *
	 * @param id Identificador del Vertice que lo identificará
	 * @param elem Elemento que contrendrá el Vertice
	 */
	public void addVertex( I id, E elem )
	{
		adjacencyList.put( id, new Vertex( id, elem ) );
		numVertex++;
	}
	
	/**
	 * Retorna un String de los identificadores como una representación de una Entrada del Grafo.<br>
	 * La entrada simboliza un Vertice del Grafo y sus adyacentes de Salida.
	 *
	 * @param entry Entrada a ser representada
	 * @return String como una representación de los identificadores de la Entrada del Grafo
	 */
	private String entryToString( Entry<I, Vertex> entry )
	{
		StringBuilder sBuilder = new StringBuilder( );
		
		sBuilder.append( String.format( "%s|", entry.getKey( ) ) );
		
		for( Entry<I, Edge> e : entry.getValue( ).edges.entries( ) )
		{
			sBuilder.append( ARROW + " " + e.getValue( ).toStringTo( ) );
		}
		
		return sBuilder.toString( );
	}
	
	/**
	 * Retorna un String de los elementos como una representación de una Entrada del Grafo.<br>
	 * La entrada simboliza un Vertice del Grafo y sus adyacentes de Salida.
	 *
	 * @param entry Entrada a ser representada
	 * @return String como una representacion de los elementos de la Entrada del Grafo
	 */
	private String entryToStringElement( Entry<I, Vertex> entry )
	{
		StringBuilder sBuilder = new StringBuilder( );
		
		sBuilder.append( String.format( "%s|", adjacencyList.get( entry.getKey( ) ).element ) );
		
		for( Entry<I, Edge> v : entry.getValue( ).edges.entries( ) )
		{
			sBuilder.append( ARROW + " " + adjacencyList.get( v.getKey( ) ).element );
		}
		
		return sBuilder.toString( );
	}
	
	/**
	 * Verifica si el Vertice cuyo Identificador es dado por parámetro existe en el Grafo
	 *
	 * @param id Identificador del Vertice de interés
	 * @return True si el Vertice existe dentro de la estructura del Grafo, False de lo contrario.
	 */
	public boolean exists( I id )
	{
		return adjacencyList.get( id ) != null;
	}
	
	/**
	 * Retorna el número de componentes del Grafo.
	 *
	 * @return Número de componentes del Grafo
	 * @see DFS
	 */
	public int getComponents( )
	{
		return new DFS<I, E>( this ).getComponents( );
	}
	
	/**
	 * Retorna el Elemento asociado al Iddentificador dado por parámetro
	 *
	 * @param id Identificador del Elemento de interés
	 * @return Elemento asociado al Identificador dado por parámetro. Null si no existe el parámetro
	 */
	public E getElement( I id )
	{
		Vertex vertex = adjacencyList.get( id );
		if( vertex != null )
		{
			return vertex.getElement( );
		}
		return null;
	}
	
	/**
	 * Retorna un Arco dado su Identificador de inicio y su Identificador de fin
	 * 
	 * @param from Identificador de donde inicia el Arco
	 * @param to Identificador de donde termina el Arco
	 * @return Arco cuyo inicio y fin estan dados pos los identificadores dados por parámetro
	 */
	public Edge getEdge( I from, I to )
	{
		return adjacencyList.get( from ).getEdgeTo( to );
	}
	
	/**
	 * Retorna la estructura implementada por el Grafo junto con su información.<br>
	 *
	 * @return Estructura implementada por el Grafo junto con su información.
	 */
	public HashTableLP<I, Vertex> getGraph( )
	{
		return adjacencyList;// TODO Solo nodos y solo Adj?
	}
	
	/**
	 * Retorna una lista con los Elementos de Entrada del Vertice cuyo Identificador esta dado por parámetro.
	 *
	 * @param to Identificador del Vertice de interés
	 * @return Lista de Elementos de Entrada del Vertice
	 */
	public DoubleLinkedList<E> getIncoming( I to )
	{
		DoubleLinkedList<E> incoming = new DoubleLinkedList<>( );
		
		for( Entry<I, Vertex> entry : adjacencyList.entries( ) )
		{
			for( Entry<I, Graph<I, E>.Edge> vertex : entry.getValue( ).edges.entries( ) )
			{
				if( vertex.getKey( ).equals( to ) )
				{
					incoming.add( entry.getValue( ).getElement( ) );
					break;
				}
			}
		}
		
		return incoming;
	}
	
	/**
	 * Retorna una lista con los Identificadores de Entrada del Vertice cuyo Identificador esta dado por parámetro.
	 *
	 * @param to Identificador del Vertice de interés
	 * @return Lista de Identificadores de Entrada del Vertice
	 */
	public DoubleLinkedList<I> getIncomingId( I to )
	{
		DoubleLinkedList<I> incoming = new DoubleLinkedList<>( );
		
		for( Entry<I, Vertex> entry : adjacencyList.entries( ) )
		{
			for( Entry<I, Graph<I, E>.Edge> vertex : entry.getValue( ).edges.entries( ) )
			{
				if( vertex.getKey( ).equals( to ) )
				{
					incoming.add( entry.getValue( ).getIdentifier( ) );
					break;
				}
			}
		}
		
		return incoming;
	}
	
	/**
	 * Retorna el número de Arcos del Grafo
	 *
	 * @return Número de Arcos del Grafo
	 */
	public int getNumEdges( )
	{
		return numEdges;
	}
	
	/**
	 * Retorna el número de Vertices del Grafo
	 *
	 * @return Número de Vertices del Grafo
	 */
	public int getNumVertex( )
	{
		return numVertex;
	}
	
	/**
	 * Retorna una lista con los Elementos de Salida del Vertice cuyo Identificador esta dado por parámetro.
	 *
	 * @param from Identificador del Vertice de interés
	 * @return Lista de Elementos de Salida del Vertice
	 */
	public DoubleLinkedList<E> getOutgoing( I from )
	{
		DoubleLinkedList<E> outgoing = new DoubleLinkedList<>( );
		for( Entry<I, Edge> entry : adjacencyList.get( from ).edges.entries( ) )
		{
			outgoing.add( adjacencyList.get( entry.getKey( ) ).getElement( ) );
		}
		return outgoing;
	}
	
	/**
	 * Retorna los Arcos de Salida del Vertice cuyo Identificador esta dado por parámetro
	 * 
	 * @param from Identificador del Vertice de interés
	 * @return Arcos de Salida del Vertice
	 */
	public DoubleLinkedList<Edge> getOutgoingEdges( I from )
	{
		DoubleLinkedList<Edge> outgoing = new DoubleLinkedList<>( );
		for( Entry<I, Edge> entry : adjacencyList.get( from ).edges.entries( ) )
		{
			outgoing.add( entry.getValue( ) );
		}
		return outgoing;
	}
	
	/**
	 * Retorna una lista con los Identificadores de Salida del Vertice cuyo Identificador esta dado por parámetro.
	 *
	 * @param from Identificador del Vertice de interés
	 * @return Lista con los Identificadores de Salida del Vertice
	 */
	public DoubleLinkedList<I> getOutgoingId( I from )
	{
		DoubleLinkedList<I> outgoing = new DoubleLinkedList<>( );
		for( Entry<I, Edge> entry : adjacencyList.get( from ).edges.entries( ) )
		{
			outgoing.add( adjacencyList.get( entry.getKey( ) ).getIdentifier( ) );
		}
		return outgoing;
	}
	
	/**
	 * Retorna una representación en Orden Topológico del Grafo de los Identificadores de este.
	 *
	 * @return Representación del Orden Topológico del Grafo
	 * @see DFS
	 */
	public Stack<I> getTopologicalOrder( )
	{
		return new DFS<I, E>( this ).getPostorder( );
	}
	
	/**
	 * Retorna una representación en Orden Topológico del Grafo de los Elementos de este.
	 *
	 * @return Representación del Orden Topológico del Grafo
	 * @see DFS
	 */
	public Stack<E> getTopologicalOrderElement( )
	{
		Stack<E> toplogical = new Stack<>( );
		for( I i : new DFS<I, E>( this ).getPostorder( ) )
		{
			toplogical.push( adjacencyList.get( i ).element );
		}
		return toplogical;
	}
	
	/**
	 * Retorna el peso total del Grafo.<br>
	 * El peso se calcula a partir de los pesos de todos los arcoss
	 *
	 * @return Peso total del Grafo.
	 */
	public double getWeight( )
	{
		double weight = 0;
		for( Entry<I, Vertex> e : adjacencyList.entries( ) )
		{
			weight += e.getValue( ).getWeight( );
		}
		return weight;
	}
	
	/**
	 * Verifica si hay un camino entre los Vertices cuyos Identificadores están dados por parámetro.
	 *
	 * @param from Identificador del Vertice de Salida
	 * @param to Identificador del Vertice de Llegada
	 * @return True si existe un camino desde el Vertice de Salida al Vertice de Llegada, False de lo contrario
	 * @see DFS
	 */
	public boolean hasPath( I from, I to )
	{
		return new DFS<I, E>( this, from, false ).getEdgeTo( ).get( to ) != null;
	}
	
	/**
	 * Verifica si el Grafo es dirijido o no
	 *
	 * @return True si el Grafo es dirijido, False de lo contrario
	 */
	public boolean isDirected( )
	{
		return directed;
	}
	
	/**
	 * Verifica si el componente, en el que se encuentra el vertice cuyo Identificador esta dado por parámetro,
	 * tiene un ciclo
	 *
	 * @param init Identificador del Vertice en interés
	 * @return True si el componente del Vertice tiene un ciclo, False de lo contrario
	 * @see DFS
	 * @throws IllegalOperationException Si se intenta detectar un ciclo en un grafo No-Dirijido
	 */
	public boolean isInCycle( I init )
	{
		if( !directed )
		{
			throw new IllegalOperationException( "Cannot identify cycles on undirected Graphs" );
		}
		return new DFS<I, E>( this, init ).hasCycle( );
	}
	
	/**
	 * Retorna una representación de un Camino, dado por los Identificadores, desde el Vertice Salida al Vertice de Llegada.<br>
	 *
	 * @param from Identificador del Vertice de Salida
	 * @param to Identificador del Vertice de Llegada
	 * @return Representación de un Camino entre el Vertice de Salida y el de Llegada
	 * @see DFS
	 */
	public Stack<I> path( I from, I to )
	{
		Stack<I> stack = new Stack<>( );
		DFS<I, E> dfs = new DFS<I, E>( this, from, false );
		if( dfs.getEdgeTo( ).get( to ) != null )
		{
			stack.push( to );
			I temp = dfs.getEdgeTo( ).get( to );
			while( ( temp != null ) && !temp.equals( from ) )
			{
				stack.push( temp );
				temp = dfs.getEdgeTo( ).get( temp );
			}
			stack.push( from );
		}
		return stack;
	}
	
	/**
	 * Elimina y retorna el Arco entre el Vertice de Salida y el de Llegada
	 *
	 * @param from Identificador del Vertice de Salida
	 * @param to Identificador del Vertice de Llegada
	 * @return Arco eliminado
	 */
	public Edge removeEdge( I from, I to )
	{
		numEdges--;
		return adjacencyList.get( from ).removeEdge( to );
	}
	
	/**
	 * Elimina y retorna el Vertice cuyo Identificador esta dado por parámetro.<br>
	 * Elimina todos los Arcos que llegaban al Vertice
	 *
	 * @param id Identificador del Vertice a eliminar
	 * @return Vertice eliminado
	 */
	public Vertex removeVertex( I id )
	{
		removeVertexIncomming( id );
		numVertex--;
		return adjacencyList.delete( id );
	}
	
	/**
	 * Elimina los Arcos que llegaban al Vertice cuyo Identificador esta dado por parámetro
	 *
	 * @param id Identificador del Vertice en interés
	 */
	private void removeVertexIncomming( I id )
	{
		for( Entry<I, Vertex> entry : adjacencyList.entries( ) )
		{
			entry.getValue( ).removeEdge( id );
		}
	}
	
	/**
	 * Elimina un Peso con un Id dado del Arco (->) entre los Vertices cuyos Identificadores estan dados por parámetro
	 *
	 * @param from Identificador del Vertice de Salida
	 * @param to Identificador del Vertice de Llegada
	 * @param id Identificador del Peso a Eliminar
	 * @return
	 */
	public double removeWeightEdge( I from, I to, String id )
	{
		Vertex eFrom = adjacencyList.get( from );
		return eFrom != null ? eFrom.getEdgeTo( to ).removeWeight( id ) : 0;// TODO Verify Null
	}
	
	/**
	 * Modifica el valor de un peso del Arco que va desde el Vertice de Salida al de Llegada
	 *
	 * @param from Identificador del Vertice de Salida
	 * @param to Identificador del Vertice de Llegada
	 * @param id Identificador del Peso a modificar
	 * @param weight Nuevo Peso a asociar con el Identificador
	 * @return Peso anterior asociado al Identificador
	 */
	public double setWeight( I from, I to, String id, double weight )
	{
		Vertex eFrom = adjacencyList.get( from );
		return eFrom != null ? eFrom.getEdgeTo( to ).setWeight( id, weight ) : -1;
	}
	
	/**
	 * Retorna una representación del Grafo con Identificadores.<br>
	 * Vertice de Salida | Arco1, Arco2, ..., ArcoN
	 */
	@Override
	public String toString( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		
		for( Entry<I, Vertex> entry : adjacencyList.entries( ) )
		{
			if( entry != null )
			{
				sBuilder.append( entryToString( entry ) + System.lineSeparator( ) );
			}
		}
		
		return sBuilder.toString( );
	}
	
	/**
	 * /**
	 * Retorna una representación del Grafo con Elementos.<br>
	 * Vertice de Salida | Arco1, Arco2, ..., ArcoN
	 *
	 * @return Representación del Grafo c
	 */
	public String toStringVertex( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		
		for( Entry<I, Vertex> entry : adjacencyList.entries( ) )
		{
			if( entry != null )
			{
				sBuilder.append( entryToStringElement( entry ) + System.lineSeparator( ) );
			}
		}
		
		return sBuilder.toString( );
	}
}