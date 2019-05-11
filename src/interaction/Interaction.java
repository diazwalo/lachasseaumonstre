package interaction;

import java.util.Scanner;
import map.Mouvment;

public class Interaction {
	
	/**
	 * Retourne le Mouvement saisie par le Joueur
	 * @return Le Mouvement saisiet
	 */
	public static Mouvment askMouvement() {
		Mouvment res=null;
		String[] strMvt=generateStrMvt();
		Mouvment[] tabMvt=getTabMvt(strMvt);
		res=getSaisieMvt(res, strMvt, tabMvt);
		return res;
	}
	
	/**
	 * Retourne un tableau contenant des Strings pouvant etre saisies correspondant a un Mouvment
	 * @return Un tableau avec les combinaisons de touches possible.
	 */
	public static String[] generateStrMvt() {
		return new String[] {"z", "s", "d", "q", "zd", "zq", "sd", "sq", "dz", "qz", "ds", "qs"};
	}
	
	/**
	 * boucle pour demander a l'utilisateur de saisir un mouvment valide
	 * @param res Le mouvement a effectuer.
	 * @param strMvt La liste des touches clavies acceptes.
	 * @param tabMvt La liste des mouvements acceptes.
	 * @return Mouvment
	 */
	public static Mouvment getSaisieMvt(Mouvment res, String[] strMvt, Mouvment[] tabMvt) {
		while(res==null) {
			String saisie=getSaisie();
			
			for (int idx = 0; idx < strMvt.length; idx++) {
				if(strMvt[idx].equals(saisie)) res=tabMvt[idx];
			}
			
			if(res==null) System.out.println("Veuillez saisir correctement Les infos !\n");
		}
		
		return res;
	}
	
	/**
	 * Retourn toute les Mouvements que le Joueur peut saisir associes au tableau strMvt
	 * @param strMvt La liste des touches clavies acceptes.
	 * @return Mouvment[]
	 */
	public static Mouvment[] getTabMvt(String[] strMvt) {
		Mouvment[] tabMvt=new Mouvment[12];
		Mouvment[] add=new Mouvment[] {Mouvment.NORDEST, Mouvment.NORDOUEST, Mouvment.SUDEST, Mouvment.SUDOUEST};
		int idx=0;
		
		for (Mouvment mouvment : Mouvment.values()) {
			tabMvt[idx]=mouvment;
			idx++;
		}
		
		for (int i = 0; i < add.length; i++) {
			tabMvt[i+idx]=add[i];
		}
		
		return tabMvt;
	}
	
	/**
	 * Retourne la saisie du Joueur
	 * @return String
	 */
	private static String getSaisie() {
		Scanner sc = new Scanner(System.in); 
		
		System.out.print("Veuillez saisir la direction:");
		String saisie = sc.nextLine();		
		
		return saisie;

	}
	
	/**
	 * Cette fonction mets le programme dans une phase de sommeil pour x secondes.
	 * @param sec Le nombre de seconde a attendre avant de continuer
	 * @see http://blog.paumard.org/cours/java-api/chap05-concurrent-premier-thread.html
	 */
	public static void waitASec(double sec) {
		try
		{
		    Thread.sleep((int)(sec*1000));
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Cette fonction attend que l'utilisateur saisisse le bouton clavier "enter"
	 */
	public static void pressEnter() {
		String saisie=" ";
		Scanner sc = new Scanner(System.in);
		System.out.print(" \nAppuyez sur ENTRER pour continuer.\n");
		
		while(saisie.length() != 0) {
			saisie = sc.nextLine();
		}
	}
	
	public static String askBonus(String firstBonus, String secondBonus) {
		Scanner sc = new Scanner(System.in); 
		
		System.out.print("Veuillez saisir \"1\" pour "+ firstBonus +", 2 pour "+ secondBonus +" ou appuyez sur Entrer si vous ne voulez rien faire.");
		String saisie = sc.nextLine();
		
		return saisie;
	}
}
