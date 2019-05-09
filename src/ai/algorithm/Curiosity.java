package ai.algorithm;

import ai.graph.Graph;
import ai.models.Edge;
import ai.models.Node;
import ai.util.NodeUtil;
import map.AbstractMap;
import map.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Curiosity
{
    private Map<String, Node> listNode;
    private Map<String, Edge> listEdge;

    public Curiosity(Graph graph)
    {
        this.listNode = graph.getListNode();
        this.listEdge = graph.getListEdge();
    }

    public List<Position> getPath(String from, String to, AbstractMap sm)
    {
        boolean toLeft = true;
        Position position = NodeUtil.formatNode(from);
        List<Position> positions = new ArrayList<Position>(listNode.size());

        for (int i = 0; i < listNode.size()-1; i++) {
            List<Position> positionList = new ArrayList<Position>();
            positionList.addAll(position.getAdjacentPosition(sm));
            positions.add(position);
            System.out.println(position.getAdjacentPosition(sm));
            if(toLeft) {
                if(positionList.contains(position.toSouth())) {
                    position = position.toSouthWest();
                    continue;
                } else if (positionList.contains(position.toSouthWest())) {
                    position = position.toSouth();
                    continue;
                } else if(positionList.contains(position.toLeft())) {
                    System.out.println("Ici");
                    position = position.toLeft();
                    continue;
                } else if(positionList.contains(position.toNorthWest())) {
                    position = position.toNorthWest();
                    continue;
                } else if(positionList.contains(position.toNorth())) {
                    position = position.toNorth();
                    continue;
                }
                toLeft = false;
            } else {
                if(positionList.contains(position.toSouth())) {
                    position = position.toSouthWest();
                    continue;
                } else if (positionList.contains(position.toSouthEast())) {
                    position = position.toSouth();
                    continue;
                } else if(positionList.contains(position.toRight())) {
                    position = position.toLeft();
                    continue;
                } else if(positionList.contains(position.toNorthEast())) {
                    position = position.toNorthWest();
                    continue;
                } else if(positionList.contains(position.toNorth())) {
                    position = position.toNorth();
                    continue;
                }
                toLeft = true;
            }

        }

        return positions;
    }
}
