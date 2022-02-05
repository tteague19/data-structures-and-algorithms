package com.project.module14;

import java.util.Set;
import java.util.HashSet;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;;

public class GraphAlgorithmsTests {

    @Test
    public void testPrimsConnected() {

        Set<Vertex<String>> vertices = new HashSet<>();
        Vertex<String> vertexA = new Vertex<String>("A");
        Vertex<String> vertexB = new Vertex<String>("B");
        Vertex<String> vertexC = new Vertex<String>("C");
        Vertex<String> vertexD = new Vertex<String>("D");
        Vertex<String> vertexE = new Vertex<String>("E");
        Vertex<String> vertexF = new Vertex<String>("F");
        Vertex<String> vertexG = new Vertex<String>("G");
        Vertex<String> vertexH = new Vertex<String>("H");
        vertices.add(vertexA);
        vertices.add(vertexB);
        vertices.add(vertexC);
        vertices.add(vertexD);
        vertices.add(vertexE);
        vertices.add(vertexF);
        vertices.add(vertexG);
        vertices.add(vertexH);

        // We must create reverse edges to mimic an undirected graph.
        Set<Edge<String>> edges = new LinkedHashSet<>();
        Edge<String> edgeAD = new Edge<String>(vertexA, vertexD, 6);
        Edge<String> edgeDA = new Edge<String>(vertexD, vertexA, 6);

        Edge<String> edgeAE = new Edge<String>(vertexA, vertexE, 8);
        Edge<String> edgeEA = new Edge<String>(vertexE, vertexA, 8);

        Edge<String> edgeBC = new Edge<String>(vertexB, vertexC, 4);
        Edge<String> edgeCB = new Edge<String>(vertexC, vertexB, 4);

        Edge<String> edgeBG = new Edge<String>(vertexB, vertexG, 5);
        Edge<String> edgeGB = new Edge<String>(vertexG, vertexB, 5);

        Edge<String> edgeGH = new Edge<String>(vertexG, vertexH, 5);
        Edge<String> edgeHG = new Edge<String>(vertexH, vertexG, 5);

        Edge<String> edgeCE = new Edge<String>(vertexC, vertexE, 7);
        Edge<String> edgeEC = new Edge<String>(vertexE, vertexC, 7);

        Edge<String> edgeCF = new Edge<String>(vertexC, vertexF, 9);
        Edge<String> edgeFC = new Edge<String>(vertexF, vertexC, 9);

        Edge<String> edgeFH = new Edge<String>(vertexF, vertexH, 9);
        Edge<String> edgeHF = new Edge<String>(vertexH, vertexF, 9);

        edges.add(edgeAD);
        edges.add(edgeDA);
        edges.add(edgeAE);
        edges.add(edgeEA);
        edges.add(edgeBC);
        edges.add(edgeCB);
        edges.add(edgeBG);
        edges.add(edgeGB);
        edges.add(edgeGH);
        edges.add(edgeHG);
        edges.add(edgeCE);
        edges.add(edgeEC);
        edges.add(edgeCF);
        edges.add(edgeFC);
        edges.add(edgeFH);
        edges.add(edgeHF);

        Graph<String> graph = new Graph<String>(vertices, edges);

        Set<Edge<String>> actual = GraphAlgorithms.prims(vertexA, graph);
        Set<Edge<String>> expected = edges;
        expected.remove(edgeFH);
        expected.remove(edgeHF);

        assertTrue(actual.equals(expected));
    }

    @Test
    public void testPrimsDisconnected() {

        Set<Vertex<String>> vertices = new HashSet<>();
        Vertex<String> vertexA = new Vertex<String>("A");
        Vertex<String> vertexB = new Vertex<String>("B");
        Vertex<String> vertexC = new Vertex<String>("C");
        Vertex<String> vertexD = new Vertex<String>("D");
        Vertex<String> vertexE = new Vertex<String>("E");
        Vertex<String> vertexF = new Vertex<String>("F");
        Vertex<String> vertexG = new Vertex<String>("G");
        Vertex<String> vertexH = new Vertex<String>("H");
        vertices.add(vertexA);
        vertices.add(vertexB);
        vertices.add(vertexC);
        vertices.add(vertexD);
        vertices.add(vertexE);
        vertices.add(vertexF);
        vertices.add(vertexG);
        vertices.add(vertexH);

        // We must create reverse edges to mimic an undirected graph.
        Set<Edge<String>> edges = new HashSet<>();
        Edge<String> edgeAD = new Edge<String>(vertexA, vertexD, 6);
        Edge<String> edgeDA = new Edge<String>(vertexD, vertexA, 6);

        Edge<String> edgeAE = new Edge<String>(vertexA, vertexE, 8);
        Edge<String> edgeEA = new Edge<String>(vertexE, vertexA, 8);

        Edge<String> edgeBC = new Edge<String>(vertexB, vertexC, 4);
        Edge<String> edgeCB = new Edge<String>(vertexC, vertexB, 4);

        Edge<String> edgeBG = new Edge<String>(vertexB, vertexG, 5);
        Edge<String> edgeGB = new Edge<String>(vertexG, vertexB, 5);

        Edge<String> edgeCE = new Edge<String>(vertexC, vertexE, 7);
        Edge<String> edgeEC = new Edge<String>(vertexE, vertexC, 7);

        Edge<String> edgeCF = new Edge<String>(vertexC, vertexF, 9);
        Edge<String> edgeFC = new Edge<String>(vertexF, vertexC, 9);

        edges.add(edgeAD);
        edges.add(edgeDA);
        edges.add(edgeAE);
        edges.add(edgeEA);
        edges.add(edgeBC);
        edges.add(edgeCB);
        edges.add(edgeBG);
        edges.add(edgeGB);
        edges.add(edgeCE);
        edges.add(edgeEC);
        edges.add(edgeCF);
        edges.add(edgeFC);

        Graph<String> graph = new Graph<String>(vertices, edges);

        Set<Edge<String>> actual = GraphAlgorithms.prims(vertexA, graph);

        assertNull(actual);
    }
}

