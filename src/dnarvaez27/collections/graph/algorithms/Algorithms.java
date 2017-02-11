package dnarvaez27.collections.graph.algorithms;

import java.util.Comparator;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.graph.Graph;
import dnarvaez27.collections.hashtable.HashTableLP;
import dnarvaez27.collections.heap.HeapArray;
import dnarvaez27.collections.heap.IndexedHeap;
import dnarvaez27.collections.list.Queue;
import dnarvaez27.collections.list.Stack;

public interface Algorithms
{
	public class BFS<I, E>
	{
		private Graph<I, E> G;
		
		private HashTableLP<I, I> edgeTo;
		
		private HashTableLP<I, Boolean> marked;
		
		public BFS( Graph<I, E> G, I init )
		{
			this.G = G;
			marked = new HashTableLP<>( );
			edgeTo = new HashTableLP<>( );
			
			bfs( init );
			for( Entry<I, Graph<I, E>.Vertex> entry : G.getGraph( ).entries( ) )
			{
				if( ( marked.get( entry.getKey( ) ) == null ) || !marked.get( entry.getKey( ) ) )
				{
					bfs( entry.getKey( ) );
				}
			}
		}
		
		public void bfs( I init )
		{
			Queue<I> queue = new Queue<>( );
			marked.put( init, true );
			queue.enqueue( init );
			while( !queue.isEmpty( ) )
			{
				I actual = queue.dequeue( );
				for( Entry<I, Graph<I, E>.Edge> v : G.getGraph( ).get( actual ).getEdges( ).entries( ) )
				{
					I i = v.getKey( );
					Boolean bool = marked.get( i );
					if( ( bool == null ) || !bool )
					{
						marked.put( i, true );
						edgeTo.put( i, actual );
						queue.enqueue( i );
					}
				}
			}
		}
		
		public String getEdgeTo( )
		{
			StringBuilder sBuilder = new StringBuilder( );
			for( Entry<I, Graph<I, E>.Vertex> entry : G.getGraph( ).entries( ) )
			{
				I v = edgeTo.get( entry.getKey( ) );
				sBuilder.append( String.format( "%s | %s" + System.lineSeparator( ), entry.getKey( ), v == null ? Graph.EMPTY : v ) );
			}
			
			return sBuilder.toString( );
		}
	}
	
	public class DFS<I, E>
	{
		private Graph<I, E> G;
		
		private int components;
		
		private boolean cycle;
		
		private HashTableLP<I, I> edgesTo;
		
		private HashTableLP<I, Boolean> marked;
		
		private Stack<I> postorder;
		
		public DFS( Graph<I, E> G )
		{
			this( G, null, true );
		}
		
		public DFS( Graph<I, E> G, I init )
		{
			this( G, init, false );
		}
		
		public DFS( Graph<I, E> G, I init, boolean loop )
		{
			if( ( init == null ) && !loop )
			{
				throw new NullPointerException( "Se debe ingresar el nodo de inicio" );
			}
			
			this.G = G;
			
			marked = new HashTableLP<>( G.getNumVertex( ), 0.75f );
			edgesTo = new HashTableLP<>( G.getNumVertex( ), 0.75f );
			postorder = new Stack<>( );
			
			if( init != null )
			{
				components++;
				dfs( G.getGraph( ).getEntry( init ) );
			}
			
			if( loop )
			{
				for( Entry<I, Graph<I, E>.Vertex> entry : G.getGraph( ).entries( ) )
				{
					if( ( marked.get( entry.getKey( ) ) == null ) || !marked.get( entry.getKey( ) ) )
					{
						components++;
						dfs( entry );
					}
				}
			}
		}
		
		public void dfs( Entry<I, Graph<I, E>.Vertex> entry )
		{
			marked.put( entry.getKey( ), true );
			for( Entry<I, Graph<I, E>.Edge> e : entry.getValue( ).getEdges( ).entries( ) )
			{
				Boolean bool = marked.get( e.getKey( ) );
				if( ( bool == null ) || !bool )
				{
					edgesTo.put( e.getKey( ), entry.getKey( ) );
					dfs( G.getGraph( ).getEntry( e.getKey( ) ) );
				}
				else
				{
					cycle = G.isDirected( );// TODO
				}
			}
			postorder.push( entry.getKey( ) );
		}
		
		public String getEdgetoString( )
		{
			StringBuilder sBuilder = new StringBuilder( );
			for( Entry<I, Graph<I, E>.Vertex> entry : G.getGraph( ).entries( ) )
			{
				I v = edgesTo.get( entry.getKey( ) );
				sBuilder.append( String.format( "%s | %s" + System.lineSeparator( ), entry.getKey( ), v == null ? Graph.EMPTY : v ) );
			}
			
			return sBuilder.toString( );
		}
		
		public HashTableLP<I, I> getEdgeTo( )
		{
			return edgesTo;
		}
		
		public Stack<I> getPostorder( )
		{
			return postorder;
		}
		
		public boolean hasCycle( )
		{
			return cycle;
		}
		
		public int getComponents( )
		{
			return components;
		}
	}
	
	public class Dijkstra<I, E>
	{
		public class Tupla implements Comparable<Tupla>
		{
			private double dist;
			
			private I from;
			
			private I to;
			
			public Tupla( double dist, I to, I from )
			{
				this.to = to;
				this.dist = dist;
				this.from = from;
			}
			
			public I getTo( )
			{
				return to;
			}
			
			public double getDist( )
			{
				return dist;
			}
			
			public I getFrom( )
			{
				return from;
			}
			
			@Override
			public int compareTo( Tupla o )
			{
				return Double.compare( o.dist, dist );
			}
			
			public String toString( )
			{
				return from + "=" + dist;
			}
		}
		
		private Graph<I, E> G;
		
		private HashTableLP<I, Tupla> tuplas;
		
		private String idWeight;
		
		private IndexedHeap<Tupla> priority;
		
		public Dijkstra( Graph<I, E> G, I s, String idWeight )
		{
			// Inicializacion
			this.G = G;
			this.idWeight = idWeight;
			tuplas = new HashTableLP<>( G.getNumVertex( ) * 2, 0.75f );
			priority = new IndexedHeap<>( ( int ) ( G.getNumVertex( ) * 1.5 ) );
			for( Entry<I, Graph<I, E>.Vertex> entry : G.getGraph( ).entries( ) )
			{
				I to = entry.getKey( );
				I from = null;
				tuplas.put( to, new Tupla( Double.POSITIVE_INFINITY, to, from ) );
			}
			// Asignacion del primero
			Tupla inicial = new Tupla( 0.0, s, null );
			tuplas.put( s, inicial );
			priority.add( inicial );
			
			// Relajar
			while( !priority.isEmpty( ) )
			{
				I marcado = priority.poll( ).getTo( );
				relax( marcado );
			}
		}
		
		private void relax( I marcado )
		{
			Iterable<Entry<I, Graph<I, E>.Edge>> adyacentes = G.getGraph( ).get( marcado ).getEdges( ).entries( );
			for( Entry<I, Graph<I, E>.Edge> entry : adyacentes )
			{
				// Informacion del Arco
				Graph<I, E>.Edge e = entry.getValue( );
				I w = e.getTo( ); // ID Adyacente
				double weight = e.getWeigth( idWeight );
				
				// Comparacion
				double suma = tuplas.get( marcado ).getDist( ) + weight;
				if( suma < tuplas.get( w ).getDist( ) )
				{
					// w original (quien soy), v anterior
					Tupla old = tuplas.get( w ); // Lo que tenía antes
					Tupla nueva = new Tupla( suma, w, marcado ); // Nueva asignacion
					priority.set( old, nueva ); // Change
					tuplas.put( w, nueva ); // Reemplazo en las tuplas con la nueva informacion
				}
			}
		}
		
		public double distTo( I v )
		{
			return tuplas.get( v ).getDist( );
		}
		
		public boolean hasPathTo( I v )
		{
			return tuplas.get( v ).getDist( ) < Double.POSITIVE_INFINITY;
		}
		
		public Iterable<I> pathTo( I v )
		{
			if( !hasPathTo( v ) )
			{
				return null;
			}
			Stack<I> path = new Stack<>( );
			path.push( v );
			I back = tuplas.get( v ).getFrom( );
			while( back != null )
			{
				path.push( back );
				back = tuplas.get( back ).getFrom( );
			}
			return path;
		}
		
	}
	
	public class LazyPrim<I, E>
	{
		private Graph<I, E> G;
		
		private HashTableLP<I, Boolean> marked;
		
		private Queue<Graph<I, E>.Edge> mst;
		
		private HeapArray<Graph<I, E>.Edge> priorityQueue;
		
		public LazyPrim( Graph<I, E> G, final String idWeight )
		{
			this.G = G;
			marked = new HashTableLP<>( G.getNumVertex( ), 0.75f );
			mst = new Queue<>( );
			priorityQueue = new HeapArray<>( new Comparator<Graph<I, E>.Edge>( )
			{
				@Override
				public int compare( Graph<I, E>.Edge o1, Graph<I, E>.Edge o2 )
				{
					return Double.compare( o2.getWeigth( idWeight ), o1.getWeigth( idWeight ) );
				}
			} );
			
			visit( G.getGraph( ).entries( ).get( 0 ).getKey( ) );
			while( !priorityQueue.isEmpty( ) )
			{
				Graph<I, E>.Edge e = priorityQueue.poll( );
				I v = e.getFrom( );
				I w = e.getTo( );
				Boolean bV = marked.get( v );
				Boolean bW = marked.get( w );
				boolean markedV = ( bV != null ) && bV;
				boolean markedW = ( bW != null ) && bW;
				if( markedV && markedW )
				{
					continue;
				}
				mst.enqueue( e );
				if( !markedV )
				{
					visit( v );
				}
				if( !markedW )
				{
					visit( w );
				}
			}
		}
		
		public Iterable<Graph<I, E>.Edge> edges( )
		{
			return mst;
		}
		
		public void visit( I v )
		{
			marked.put( v, true );
			for( Graph<I, E>.Edge edge : G.getGraph( ).get( v ).getValuesEdges( ) )
			{
				I a = edge.getFrom( ).equals( v ) ? edge.getTo( ) : edge.getFrom( );
				Boolean b = marked.get( a );
				boolean markedE = ( b != null ) && b;
				if( !markedE )
				{
					priorityQueue.add( edge );
				}
			}
		}
	}
}