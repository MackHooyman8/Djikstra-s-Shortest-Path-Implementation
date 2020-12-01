// --== CS400 File Header Information ==--
// Name: Mack Hooyman
// Email: mhooyman@wisc.edu
// Team: EG
// Role: Test Engineer 2
// TA: Keren Chen
// Lecturer: Gary Dahl

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<Integer> graph;
    @BeforeEach
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph.insertVertex(i);
        // insert edges from Week 08. Dijkstra's Activity
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,7,2);
        graph.insertEdge(1,8,4);
        graph.insertEdge(2,4,4);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,1,6);
        graph.insertEdge(3,7,2);
        graph.insertEdge(4,5,4);
        graph.insertEdge(5,0,2);
        graph.insertEdge(5,1,4);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,3,1);
        graph.insertEdge(7,0,3);
        graph.insertEdge(7,6,1);
        graph.insertEdge(8,9,3);
        graph.insertEdge(9,4,5);
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled 0 to 8
     * (should be 15), and from the vertex labelled 9 to 8 (should be 17).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost(0,8) == 15);    
        assertTrue(graph.getPathCost(9,8) == 17);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled 0 to 8, and from the vertex labelled 9 to 8.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath(0, 8).toString().equals(
            "[0, 2, 6, 3, 1, 8]"
        ));
        assertTrue(graph.shortestPath(9, 8).toString().equals(
            "[9, 4, 5, 1, 8]"
        ));
    }
    
    /**
     * Checks the cost of the farthest possible shortest path on the graph
     */
    @Test
    public void checkFarthestDistance() {
    	assertTrue(graph.getPathCost(6, 5) == 15);
    }
    
    /**
     * Checks the orderedSequence of the farthest possible shortest path on the graph
     */
    @Test
    public void checkPathContents() {
    	assertTrue(graph.shortestPath(6, 5).toString().equals(
    		"[6, 3, 7, 0, 2, 4, 5]"
    	));
    }
    
    /**
     * Checks the cost and ordered sequence of the shortest possible path on the graph
     */
    @Test
    public void checkShortestPath() {
    	assertTrue(graph.shortestPath(5, 5).toString().equals("[5]") && graph.getPathCost(5, 5) == 0);
    }
    
    /**
     * Checks the ordered sequence and cost of the shortest path from vertex 2 to vertex 7
     */
    @Test
    public void checkAnotherPath() {
    	assertTrue(graph.shortestPath(2, 7).toString().equals(
    			"[2, 6, 3, 7]") && graph.getPathCost(2, 7) == 6);
    }

}
