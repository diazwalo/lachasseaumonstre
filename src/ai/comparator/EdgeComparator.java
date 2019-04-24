package ai.comparator;

import java.util.Comparator;
import java.util.Map;

import ai.models.Edge;

public class EdgeComparator implements Comparator<String>
{

	Map<String, Edge> edgeList;
	
	public EdgeComparator(Map<String, Edge> edgeList)
	{
		this.edgeList = edgeList;
	}
	
	@Override
	public int compare(String o1, String o2)
	{
		return this.edgeList.get(o1).getWeight() - this.edgeList.get(o2).getWeight();
	}

}
