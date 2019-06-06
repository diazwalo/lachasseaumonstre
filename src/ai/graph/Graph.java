package ai.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
import ai.util.NodeUtil;
import map.*;

/**
 * Cette classe explore le plateau du jeu afin de le representer mathematiquement sous forme
 * d'un graphe compose de sommets (nodes) et d'aretes (edges). Une fois sur cette forme, des algorithmes generiques peuvent 
 * etre applique.
 * @author PHPierre
 *
 */
public class Graph {
    private Map<String, Node> listNode;
    private Map<String, Edge> listEdge;
    
    /**
     * Initialise un graph vide, utilise seulement pour les tests unitaires.
     */
    public Graph()
    {
    	this.initialze();
    }
    
    /**
     * Execute le processus d'exploration du plateau pour en generer un graphe compose d'aretes et de sommets.
     * @param graph Le plateau de jeu
     */
    public Graph(AbstractMap graph)
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
     * @param map Une instance d'une map
     * @return listNode La liste des cases transformes en sommets
     */
    public Map<String, Node> generateNode(AbstractMap map)
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
    
    public Map<String, Edge> generateEdge(AbstractMap map)
    {
    	Case[][] cases = map.getTab();
    	Map<String, Edge> edges = new TreeMap<String, Edge>();

    	for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
            	if(cases[i][j].getCaseType() != CaseType.OBSTACLE) {
	                Position positionActual = new Position(i, j);
	                List<Position> positions = positionActual.getAdjacentPosition(map);
	                Map<String, Edge> edgeAdjacent = this.positionToEdge(positionActual, positions);
	                edges.putAll(edgeAdjacent);
            	}
            }
        }

    	return edges;
    }

    /**
     * Retourne la liste de sommets
     * @return Une Map contenant les identifiants et son sommets.
     */
    public Map<String, Node> getListNode()
    {
        return this.listNode;
    }

    /**
     * Retourne la liste des aretes
     * @return Une Map contenant les identifiants et son arete.
     */
    public Map<String, Edge> getListEdge()
    {
        return this.listEdge;
    }

    /**
     * Creer une liste d'aretes a partir d'une position et de ses positions adjacentes.
     * @param positionActual La position qui est adjacente aux autres arretes
     * @param positions Une liste de positions qui ont pour adjacente la premiere.
     * @return Une liste d'aretes signifiant les chemins possibles. 
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