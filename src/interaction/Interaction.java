package interaction;

import java.util.Scanner;
import map.Mouvment;

public class Interaction {
	
	/**
	 * Retourne le Mouvement saisie par le Joueur
	 * @return Mouvment
	 */
	public static Mouvment askMouvement() {
		Mouvment res=null;
		String[] strMvt=new String[] {"z", "s", "d", "q", "zd", "zq", "sd", "sq", "dz", "qz", "ds", "qs"};
		Mouvment[] tabMvt=getTabMvt(strMvt);
		res=getSaisieMvt(res, strMvt, tabMvt);
		return res;
	}
	
	/**
	 * boucle pour demander a l'utilisateur de saisir un mouvment valide
	 * @param res
	 * @param strMvt
	 * @param tabMvt
	 * @return Mouvment
	 */
	public static Mouvment getSaisieMvt(Mouvment res, String[] strMvt, Mouvment[] tabMvt) {
		while(res==null) {
			String saisie=getSaisie();
			for (int idx = 0; idx < strMvt.length; idx++) {
				if(strMvt[idx].equals(saisie)) res=tabMvt[idx];
			}if(res==null) System.out.println("Veuillez saisir correctement Les infos !\n");
		}return res;
	}
	
	/**
	 * Retourn toute les Mouvements que le Joueur peut saisir associes au tableau strMvt
	 * @param strMvt
	 * @return Mouvment[]
	 */
	private static Mouvment[] getTabMvt(String[] strMvt) {
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
}
