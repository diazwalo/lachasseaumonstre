package interaction;

import map.Mouvment;

public class Interaction {
	
	/**
	 * Debut de la methode Interaction (a continuer)
	 */
	public static void askMouvement() {
		//faire une methode generique pour generer ce tableau
		String[] strMvt=new String[] {"z", "s", "d", "q", "zd", "zq", "sd", "sq", "dz", "qz", "ds", "qs"};
		Mouvment[] tabMvt=getTabMvt(strMvt);
		
		
	}
	
	private static Mouvment[] getTabMvt(String[] strMvt) {
		Mouvment[] tabMvt=new Mouvment[12];
		Mouvment[] add=new Mouvment[] {Mouvment.NORDEST, Mouvment.NORDOUEST, Mouvment.SUDEST, Mouvment.SUDOUEST};
		int idx=0;
		for (Mouvment mouvment : Mouvment.values()) {
			tabMvt[idx]=mouvment;
			idx++;
		}for (int i = 0; i < add.length; i++) {
			tabMvt[i+idx]=add[i];
		}return tabMvt;
	}
}
