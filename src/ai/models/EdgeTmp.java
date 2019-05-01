package ai.models;

public class EdgeTmp {
	private String name;
	
	private String nodeOneName;
	private String nodeTwoName;
	private int weight;
	
	
	public EdgeTmp(String name, String nodeOneName, String nodeTwoName, int weight) {
		this.name = name;
		this.nodeOneName = nodeOneName;
		this.nodeTwoName = nodeTwoName;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeOneName() {
		return nodeOneName;
	}
	public void setNodeOneName(String nodeOneName) {
		this.nodeOneName = nodeOneName;
	}
	public String getNodeTwoName() {
		return nodeTwoName;
	}
	public void setNodeTwoName(String nodeTwoName) {
		this.nodeTwoName = nodeTwoName;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EdgeTmp [name=" + name + ", nodeOneName=" + nodeOneName + ", nodeTwoName=" + nodeTwoName + ", weight="
				+ weight + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeTmp other = (EdgeTmp) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
