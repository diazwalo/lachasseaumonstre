package ai.graph;

import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
import ai.util.NodeUtil;
import map.Case;
import map.IMap;
import map.Position;
import java.util.*;

/**
 * Cette classe explore le plateau du jeu afin de le représenter mathématiquement sous forme
 * d'un graphe composé composé de sommets (nodes) et d'arêtes (edges). Une fois sur cette forme, des algorithmes génériques peuvent 
 * être appliqué.
 * @author PHPierre
 *
 */
public class Graph {
    private Map<String, Node> listNode;
    private Map<String, Edge> listEdge;
    
    //http://www.ssaurel.com/blog/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm/

    public Graph()
    {
    	this.initialze();
    }
    
    public Graph(IMap graph)
    {
    	this.initialze();
    	this.listNode = this.generateNode(graph.getTab());
    	this.listEdge = this.generateEdge(graph);
    }
    
    private void initialze()
    {
    	this.listNode = new TreeMap<String, Node>();
    	this.listEdge = new HashMap<String, Edge>();
    }
    
    /**
     * Explore un tableau pour creer un diagramme de noeuds
     * @param cases Le tableau contenant les cases du jeu
     * @return listNode La liste des cases transformés en sommets
     */
    public Map<String, Node> generateNode(Case[][] cases)
    {
    	Node defaultNode = new Node();
    	
    	for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                String nodeName = NodeUtil.formatNode(new Position(i, j));
                listNode.put(nodeName, defaultNode);
            }
        }
    	return listNode;
    }
    
    public Map<String, Edge> generateEdge(IMap map)
    {
    	Case[][] cases = map.getTab();
    	Map<String, Edge> edges = new TreeMap<String, Edge>();

    	for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Position positionActual = new Position(i, j);
                List<Position> positions = positionActual.getAdjacentPosition(map);
                Map<String, Edge> test = this.positionToEdge(positionActual, positions);
                edges.putAll(test);
            }
        }

    	return edges;
    }

    public Map<String, Node> getListNode()
    {
        return this.listNode;
    }

    public Map<String, Edge> getListEdge()
    {
        return this.listEdge;
    }


    public Map<String, Edge> positionToEdge(Position positionActual, List<Position> positions)
    {
        Map<String, Edge> edges = new TreeMap<String, Edge>();
        for (Position position : positions) {
        	String nameNodeOne = NodeUtil.formatNode(positionActual);
        	String nameNodeTwo = NodeUtil.formatNode(position);
        	String nameEdge = EdgeUtil.formatEdge(positionActual, position);
        	
        	this.listNode.put(nameNodeOne, new Node());
        	this.listNode.put(nameNodeTwo, new Node());
        	
        	Node nodeOne = this.listNode.get(nameNodeOne);   
        	Node nodeTwo = this.listNode.get(nameNodeTwo);
			edges.put(nameEdge, new Edge(nodeOne, nodeTwo, 10));
        }
        
    	return edges;
    }

}