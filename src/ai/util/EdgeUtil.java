package ai.util;

import ai.models.Node;

public class EdgeUtil {

    public static String formatEdge(Node nodeOne, Node nodeTwo)
    {
        return nodeOne.getName() + "|" + nodeTwo.getName();
    }
}
