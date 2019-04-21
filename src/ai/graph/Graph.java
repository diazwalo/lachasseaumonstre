package ai.graph;

import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
import ai.util.NodeUtil;
import map.Case;
import map.CaseType;
import map.IMap;
import map.Position;
import map.SquareMap;

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
    
    /**
     * Initialise un graph vide, utilise seulement pour les unitaires.
     */
    public Graph()
    {
    	this.initialze();
    }
    
    /**
     * Execute le processus d'exploration du plateau pour en generer un graphe compose d'aretes et de sommets.
     * @param graph Le plateau de jeu
     */
    public Graph(IMap graph)
    {
    	this.initialze();
    	this.listNode = this.generateNode(graph);
    	this.listEdge = this.generateEdge(graph);
    }
    
    /**
     * Initialise les variables globales de la classe.
     */
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
    public Map<String, Node> generateNode(IMap map)
    {
    	Map<String, Node> listNode = new TreeMap<String, Node>();
    	Case[][] cases = map.getTab();
    	
    	for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
            	if(cases[i][j].getCaseType() != CaseType.OBSTACLE) {
            		Node node = new Node();
            		Position positionActual = new Position(i, j);
                	
                	List<Position> positions = positionActual.getAdjacentPosition(map);
                	for (int k = 0; k < positions.size(); k++) {
                		node.addAdjacentNode(NodeUtil.formatNode(positions.get(k)), new Node());
    				}

                    String nodeName = NodeUtil.formatNode(positionActual);
                    listNode.put(nodeName, node);
            	}
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
                Map<String, Edge> edgeAdjacent = this.positionToEdge(positionActual, positions);
                edges.putAll(edgeAdjacent);
            }
        }

    	return edges;
    }

    /**
     * Retourne la liste de sommets
     * @return Map<String, Node>
     */
    public Map<String, Node> getListNode()
    {
        return this.listNode;
    }

    /**
     * Retourne la liste des aretes
     * @return Map<String, Edge>
     */
    public Map<String, Edge> getListEdge()
    {
        return this.listEdge;
    }

    /**
     * Creer une liste d'aretes a partir d'une position et de ses positions adjacentes.
     * @param positionActual La position qui est adjacente aux autres arretes
     * @param positions Une liste de positions qui ont pour adjacente la premiere.
     * @return Map<String, Edge> Une liste d'aretes
     */
    public Map<String, Edge> positionToEdge(Position positionActual, List<Position> positions)
    {
        Map<String, Edge> edges = new TreeMap<String, Edge>();
        for (Position position : positions) {
        	String nameNodeOne = NodeUtil.formatNode(positionActual);
        	String nameNodeTwo = NodeUtil.formatNode(position);
        	String nameEdge = EdgeUtil.formatEdge(positionActual, position);
        	
			edges.put(nameEdge, new Edge(nameNodeOne, nameNodeTwo));
        }
        
    	return edges;
    }

}