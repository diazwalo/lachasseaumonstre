package ai.algorithm;

import ai.models.Edge;
import ai.models.Node;
import ai.util.NodeUtil;
import map.IMap;
import map.Position;

import java.util.*;

public class Graph {
    private Map<String, Node> listNode;
    private Map<String, Edge> listEdge;

    /*
        Node : i:j       ex : 1:1
        Edge : i:j|i:j   ex : 1:1|1:2
        http://www.ssaurel.com/blog/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm/
     */

    public Graph(IMap map)
    {
        Case[][] cases = map.getTab();

        //Node
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                String nodeName = NodeUtil.formatNode(new Position(i, j));
                this.listNode.put(nodeName, new Node());
            }
        }

        //Edge
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Position positionActual = new Position(i, j);
                List<Position> positions = positionActual.getAdjacentPosition(map);
                this.addEdges(this.positionToEdge(positionActual, positions));
            }
        }

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
        Map<String, Edge> edges = new HashMap<String, Edge>();
        for (Position position : positions) {
            String name = NodeUtil.formatNode(position);
            Node node = this.listNode.get(name);

            Edge edge = this.listEdge.get(name);
            edges.put(name, edge);
        }
        return edges;
    }

    public void addEdges(Map<String, Edge> edges)
    {
        for (String name : edges) {
            this.listEdge.put(name, edges.get(name));
        }
    }
}