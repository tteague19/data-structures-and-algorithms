package com.project.module14;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GraphAlgorithmsTests {

    @Test
    public void testDFS() {

        Set<Vertex<String>> vertices = new HashSet<>();
        Vertex<String> vertexA = new Vertex<String>("A");
        Vertex<String> vertexB = new Vertex<String>("B");
        Vertex<String> vertexC = new Vertex<String>("C");
        Vertex<String> vertexD = new Vertex<String>("D");
        Vertex<String> vertexE = new Vertex<String>("E");
        vertices.add(vertexA);
        vertices.add(vertexB);
        vertices.add(vertexC);
        vertices.add(vertexD);
        vertices.add(vertexE);

        // We must create reverse edges to mimic an undirected graph.
        Set<Edge<String>> edges = new HashSet<>();
        edges.add(new Edge<String>(vertexA, vertexB, 1));
        edges.add(new Edge<String>(vertexB, vertexA, 1));

        edges.add(new Edge<String>(vertexA, vertexC, 1));
        edges.add(new Edge<String>(vertexC, vertexA, 1));

        edges.add(new Edge<String>(vertexB, vertexC, 1));
        edges.add(new Edge<String>(vertexC, vertexB, 1));

        edges.add(new Edge<String>(vertexC, vertexD, 1));
        edges.add(new Edge<String>(vertexD, vertexC, 1));

        edges.add(new Edge<String>(vertexC, vertexE, 1));
        edges.add(new Edge<String>(vertexE, vertexC, 1));

        Graph<String> graph = new Graph<String>(vertices, edges);

        // List<Vertex<String>> actual = GraphAlgorithms.dfs(vertexA, graph);
        List<Vertex<String>> expected = new ArrayList<>();
        expected.add(vertexA);
        expected.add(vertexB);
        expected.add(vertexC);
        expected.add(vertexD);
        expected.add(vertexE);

        // assertEquals(actual, expected);
    }
}

