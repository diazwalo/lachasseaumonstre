package ai.exception;

/**
 * @author mayeuxp
 *
 */
public class NodeNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Cette fonction affiche une erreur si un accès à un sommet inconnu est rencontré
	 * @param nodeName L'identifiant du sommet.
	 */
	public NodeNotFoundException(String nodeName)
	{
		System.out.println("Le sommet "+ nodeName + " n'existe pas sur le plateau de jeu.");
		System.out.println("Un sommet est représenté sous la forme suivante : X:Y");
	}

}
