package ai.algorithm;

import ai.exception.NodeNotFoundException;
import ai.graph.Graph;
import ai.models.Node;
import ai.util.NodeUtil;
import map.AbstractMap;
import map.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Cet algorithme parcours l'ensemble de la carte de jeu afin de passer sur toutes les cases.
 * @author PHPierre
 *
 */
public class Curiosity
{
    private Map<String, Node> listNode;

    /**
     * Applique l'algorithme Curiosity sur un objet Graph.
     * @param graph Un graph contenant les aretes et les sommets.
     */
    public Curiosity(Graph graph)
    {
    	this.listNode = graph.getListNode();
    }
    
	/**
	 * Genere le chemin afin de parcourir toute la carte.
	 * @param from L'identifiant du sommet de départ.
	 * @param sm Le plateau de jeu
	 * @return Une liste cotenant parcours de la bete.
	 */
    public List<Position> getPath(String from, AbstractMap sm)
    {
    	checkIfNodeExist(from);
        boolean toLeft = true;
        Position position = NodeUtil.formatNode(from);
        List<Position> positions = new ArrayList<Position>(listNode.size());
        int size = listNode.size();
        
        //We delete the start edge.
        this.listNode.remove(from);
        
        for (int i = 0; i < size; i++) {
            List<Position> positionList = position.getAdjacentPosition(sm);
            boolean verticalyDeplacement = false;
            if(i != 0)
            {
                positions.add(position);
            }

            if(cantMooveRight(position) && 
                    positionList.contains(position.toNorth()) && this.listNode.get(NodeUtil.formatNode(position.toNorth())) != null &&
                    positionList.contains(position.toNorthWest()) && this.listNode.get(NodeUtil.formatNode(position.toNorthWest())) != null
                    && this.listNode.get(NodeUtil.formatNode(position.toSouthWest())) == null
            ) {
                position = position.toNorth();
            }else if(toLeft) {
                if(positionList.contains(position.toSouth()) && this.listNode.get(NodeUtil.formatNode(position.toSouth())) != null) {
                    position = position.toSouth();
                } else if (positionList.contains(position.toSouthWest()) && this.listNode.get(NodeUtil.formatNode(position.toSouthWest())) != null) {
                    position = position.toSouthWest();
                } else if(positionList.contains(position.toLeft()) && this.listNode.get(NodeUtil.formatNode(position.toLeft())) != null) {
                    position = position.toLeft();
                } else if(positionList.contains(position.toNorthWest()) && this.listNode.get(NodeUtil.formatNode(position.toNorthWest())) != null) {
                    position = position.toNorthWest();
                    verticalyDeplacement = true;
                } else if(positionList.contains(position.toNorth()) && this.listNode.get(NodeUtil.formatNode(position.toNorth())) != null) {
                    position = position.toNorth();
                    verticalyDeplacement = true;
                }

            } else {
                if(positionList.contains(position.toSouth()) && this.listNode.get(NodeUtil.formatNode(position.toSouth())) != null) {
                    position = position.toSouth();
                } else if (positionList.contains(position.toSouthEast()) && this.listNode.get(NodeUtil.formatNode(position.toSouthEast())) != null) {
                    position = position.toSouthEast();
                } else if(positionList.contains(position.toRight()) && this.listNode.get(NodeUtil.formatNode(position.toRight())) != null) {
                    position = position.toRight();
                } else if(positionList.contains(position.toNorthEast()) && this.listNode.get(NodeUtil.formatNode(position.toNorthEast())) != null) {
                    position = position.toNorthEast();
                    verticalyDeplacement = true;
                } else if(positionList.contains(position.toNorth()) && this.listNode.get(NodeUtil.formatNode(position.toNorth())) != null) {
                    position = position.toNorth();
                    verticalyDeplacement = true;
                } 

            }
            
            
            if(cantMooveLeft(position) && verticalyDeplacement) {
                toLeft = false;
            }
            
            if(cantMooveRight(position) &&verticalyDeplacement) {
                toLeft = true;
            }
            
            this.listNode.remove(NodeUtil.formatNode(position));

        }

        return positions;
    }
    
    /**
     * Vérifie l'existance du sommet passé en paramètre.
     * @param from L'identifiant du sommet de départ.
     */
    private void checkIfNodeExist(String from)
	{
		if(!this.listNode.containsKey(from)) {
			throw new NodeNotFoundException(from);
		}
	}

    /**
     * Vérifie si les trois cases à droite sont indisponile et visité.
     * @param position La position actuelle.
     * @return Vrai si les positions sont indisponibles / Faux si il est possible de s'y rendre.
     */
	private boolean cantMooveRight(Position position)
    {
        return this.listNode.get(NodeUtil.formatNode(position.toRight())) == null &&
                this.listNode.get(NodeUtil.formatNode(position.toSouthEast())) == null &&
                this.listNode.get(NodeUtil.formatNode(position.toNorthEast())) == null;
    }
    
	/**
	 * Vérifie si les trois cases à gauche sont indisponile et visité.
	 * @param position La position actuelle.
	 * @return Vrai si les positions sont indisponibles / Faux si il est possible de s'y rendre.
	 */
    private boolean cantMooveLeft(Position position)
    {
        return this.listNode.get(NodeUtil.formatNode(position.toLeft())) == null &&
                this.listNode.get(NodeUtil.formatNode(position.toSouthWest())) == null &&
                this.listNode.get(NodeUtil.formatNode(position.toNorthWest())) == null;
    }
}
