package ai.util;

import map.Position;

public class EdgeUtil {

    public static String formatEdge(Position positionOne, Position positionTwo)
    {
        return NodeUtil.formatNode(positionOne) + "|" + NodeUtil.formatNode(positionTwo);
    }
}
