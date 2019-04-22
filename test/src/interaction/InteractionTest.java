package src.interaction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import interaction.Interaction;
import map.Mouvment;

public class InteractionTest {
	private Mouvment mvt;
	private String[] strMvt;
	private Mouvment[] tabMvt;
	
	@Before
	public void beforeTest(){
		this.strMvt=Interaction.generateStrMvt();
		this.tabMvt=Interaction.getTabMvt(strMvt);
	}
	
	@Test
	public void testGetSaisieMvt() { 
		//rentrez "z" et vaidez
		assertEquals(Mouvment.NORD, Interaction.getSaisieMvt(null, this.strMvt, this.tabMvt));
		
		//rentrez "$", validez puis "d" et validez
		assertEquals(Mouvment.EST, Interaction.getSaisieMvt(null, this.strMvt, this.tabMvt));
		
		//rentrez rien, validez puis "sq" ou "qs" et validez
		assertEquals(Mouvment.SUDOUEST, Interaction.getSaisieMvt(null, this.strMvt, this.tabMvt));
		
		//rentrez ce que vous voulez, validez puis terminez par l'un des deplacements valide a savoir une combinaison differente de 1 ou 2 lettre parmis z, q, s ou d en excluant 2 directions opposees.
		assertNotEquals(null, Interaction.getSaisieMvt(null, this.strMvt, this.tabMvt));
	}
}
