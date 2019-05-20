package ai.exception;

public class NodeNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public NodeNotFoundException(String nodeName)
	{
		System.out.println("Le sommet "+ nodeName + " n'existe pas sur le plateau de jeu.");
		System.out.println("Un sommet est représenté sous la forme suivante : X:Y");
	}

}
