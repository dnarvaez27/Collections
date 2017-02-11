package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.graph.Graph;
import dnarvaez27.collections.hashtable.HashTableLP;
import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;

public class TestGraph
{
	private Graph<Integer, String> G;
	
	private String[ ] ABC =
	{
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G",
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O",
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W",
			"X",
			"Y",
			"Z"
	};
	
	private void scenarioUndirected( )
	{
		G = new Graph<>( false );
	}
	
	private void scenarioDirected1( )
	{
		G = new Graph<>( true );
	}
	
	private void scenarioDirected2( )
	{
		scenarioDirected1( );
		for( int i = 0; i < ABC.length; i++ )
		{
			G.addVertex( i, ABC[ i ] );
		}
	}
	
	private void scenarioDirected3( )
	{
		scenarioDirected1( );
		for( int i = 0; i < ABC.length; i++ )
		{
			G.addVertex( i, ABC[ i ] );
		}
		
		for( int i = 0; i < ABC.length; i++ )
		{
			for( int j = i; j < ABC.length; j++ )
			{
				if( i != j )
				{
					G.addEdge( i, j );
				}
			}
		}
	}
	
	@Test
	public void addVertex( )
	{
		scenarioDirected1( );
		
		for( int i = 0; i < ABC.length; i++ )
		{
			G.addVertex( i, ABC[ i ] );
		}
		
		assertEquals( "El número de Vertices no es el correcto", ABC.length, G.getNumVertex( ) );
		
		for( int i = 0; i < ABC.length; i++ )
		{
			assertTrue( "No se agrego el elemento", G.exists( i ) );
		}
		
		Iterable<Entry<Integer, Graph<Integer, String>.Vertex>> it = G.getGraph( ).entries( );
		Integer a = 0;
		for( Entry<Integer, Graph<Integer, String>.Vertex> entry : it )
		{
			assertEquals( "La llave de la entrada no es el esperado", a, entry.getKey( ) );
			assertEquals( "El elemento de la entrada no es el esperado", ABC[ a++ ], entry.getValue( ).getElement( ) );
		}
		
		scenarioUndirected( );
		
		for( int i = 0; i < ABC.length; i++ )
		{
			G.addVertex( i, ABC[ i ] );
		}
		
		assertEquals( "El número de Vertices no es el correcto", ABC.length, G.getNumVertex( ) );
		
		it = G.getGraph( ).entries( );
		a = 0;
		for( Entry<Integer, Graph<Integer, String>.Vertex> entry : it )
		{
			assertEquals( "La llave de la entrada no es el esperado", a, entry.getKey( ) );
			assertEquals( "El elemento de la entrada no es el esperado", ABC[ a++ ], entry.getValue( ).getElement( ) );
		}
	}
	
	@Test
	public void addEdge( )
	{
		scenarioDirected2( );
		for( int i = 0; i < ABC.length; i++ )
		{
			for( int j = i; j < ABC.length; j++ )
			{
				if( i != j )
				{
					G.addEdge( i, j, j % 2 == 0, i, j );
				}
			}
		}
		
		assertEquals( "El peso total del grafo no es correcto", 5200, G.getWeight( ), 0.0 );
		
		for( int i = 0; i < ABC.length; i++ )
		{
			for( int j = i; j < ABC.length; j++ )
			{
				if( i != j )
				{
					assertTrue( "No se agrego el Arco correctamente. No existe el Arco i->j", G.getIncomingId( j ).contains( i ) );
				}
			}
		}
		
		DoubleLinkedList<Entry<Integer, Graph<Integer, String>.Vertex>> entries = G.getGraph( ).entries( );
		for( int i = 0; i < ABC.length; i++ )
		{
			DoubleLinkedList<Integer> list = G.getOutgoingId( i );
			for( int j = i; j < ABC.length; j++ )
			{
				if( i != j )
				{
					assertTrue( "En los arcos de i, debería estar el elemento j", list.contains( j ) );
					if( j % 2 == 0 )
					{
						assertTrue( "En los arcos de j, debería estar el elemento i", G.getOutgoingId( j ).contains( i ) );
						assertEquals( "El peso del arco (j->i) no es el esperado", j, entries.get( j ).getValue( ).getEdgeTo( i ).getTotalWeight( ), 0.0 );
					}
					else
					{
						assertFalse( "En los arcos de j, NO deberia estar el elemento i", G.getOutgoingId( j ).contains( i ) );
					}
					assertEquals( "El peso del arco (i->j) no es el esperado", i, entries.get( i ).getValue( ).getEdgeTo( j ).getTotalWeight( ), 0.0 );
				}
			}
		}
	}
	
	@Test
	public void removeVertex( )
	{
		scenarioDirected2( );
		
		for( int i = 0; i < ABC.length; i++ )
		{
			Graph<Integer, String>.Vertex v = G.removeVertex( i );
			assertEquals( "El Vertice eliminado no es el correcto", Integer.valueOf( i ), v.getIdentifier( ) );
			assertEquals( "El número de vertices no es el correcto", ABC.length - ( i + 1 ), G.getNumVertex( ) );
			
			HashTableLP<Integer, Graph<Integer, String>.Vertex> adj = G.getGraph( );
			assertNull( "El Vertice no se eliminó correctamente", adj.get( i ) );
			
			DoubleLinkedList<Entry<Integer, Graph<Integer, String>.Vertex>> entries = adj.entries( );
			for( Entry<Integer, Graph<Integer, String>.Vertex> entry : entries )
			{
				assertNull( "No se eliminaron los arcos que llegaban al vertice eliminado", entry.getValue( ).getEdgeTo( i ) );
			}
		}
	}
	
	@Test
	public void removeEdge( )
	{
		scenarioDirected3( );
		
		int nE = G.getNumEdges( );
		for( int i = 0; i < ABC.length; i++ )
		{
			for( int j = i; j < ABC.length; j++ )
			{
				if( i != j )
				{
					Graph<Integer, String>.Edge e = G.removeEdge( i, j );
					assertNotNull( "No se retornó el Arco eliminado", e );
					assertEquals( "El número de Arcos no es el correcto", --nE, G.getNumEdges( ) );
				}
			}
		}
	}
}