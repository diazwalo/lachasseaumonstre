package ai.comparator;

import java.util.Comparator;
import ai.models.EdgeTmp;

public class EdgeComparator implements Comparator<EdgeTmp>
{

	@Override
	public int compare(EdgeTmp o1, EdgeTmp o2) {
		return o1.getWeight() - o2.getWeight();
	}


}
