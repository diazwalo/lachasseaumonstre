package map;

import config.Config;
import render.bonus.Bait;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.text.Beast;
import render.text.Hunter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractMap
{
    protected Case[][] tab;
    protected Hunter hunter;
    protected Beast beast;
    protected Config config;
    protected boolean beastWin;
    protected boolean hunterWin;
    private List<IBonus> bonusActif = new ArrayList<IBonus>();

    public abstract void generationMap();

    public abstract void generationBonus(int middle);

    public final Position createPositionTempoFirstBonus(int middle, boolean[] tabBonus, boolean bonusHunterOrBeast) {
        Random r=new Random();
        Position posBonusFinal=new Position(-1 , -1);

        while(posBonusFinal.isPos(-1, -1)) {
            int posBonusX=(middle)+r.nextInt((this.tab.length)/2);
            int posBonusY=(middle)+r.nextInt((this.tab[posBonusX].length)/2);
            Position posBonusTempo=new Position(posBonusX, posBonusY);

            if(! this.tab[posBonusY][posBonusX].isObstacle() && ! this.beast.getPos().equals(posBonusTempo) && ! this.hunter.getPos().equals(posBonusTempo)) {
                posBonusFinal=posBonusTempo;
            }
        }

        if(bonusHunterOrBeast) {
        	tab[posBonusFinal.getPosX()][posBonusFinal.getPosY()].setBonusBeast(tabBonus);
        }
        else {
        	tab[posBonusFinal.getPosY()][posBonusFinal.getPosX()].setBonusHunter(tabBonus);
        }

        return posBonusFinal;
    }

    public final void createPositionTempoSecondBonus(int middle, boolean[] tabBonus, Position posFirstBonus, boolean bonusHunterOrBeast) {
        Random r=new Random();
        Position posBonusFinal=new Position(-1 , -1);

        while(posBonusFinal.isPos(-1, -1)) {
            int posBonusX=(middle)+r.nextInt((this.tab.length)/2);
            int posBonusY=(middle)+r.nextInt((this.tab[posBonusX].length)/2);
            Position posBonusTempo=new Position(posBonusX, posBonusY);

            if(! this.tab[posBonusY][posBonusX].isObstacle() && ! this.beast.getPos().equals(posBonusTempo) && ! this.hunter.getPos().equals(posBonusTempo) && ! posBonusFinal.equals(posFirstBonus)) {
                posBonusFinal=posBonusTempo;
            }
        }

        if(bonusHunterOrBeast)  {
        	tab[posBonusFinal.getPosY()][posBonusFinal.getPosX()].setBonusBeast(tabBonus);
        }
        else {
        	tab[posBonusFinal.getPosY()][posBonusFinal.getPosX()].setBonusHunter(tabBonus);
        }
    }

    /**
     * Retourne le tableau a deux dimentions de Case
     * @return Case[][]
     */
    public final Case[][] getTab() {
        return this.tab;
    }

    /**
     * Retourne le Hunter
     * @return Hunter
     */
    public Hunter getHunter() {
        return this.hunter;
    }

    /**
     * Retourne la Beast
     * @return Beast
     */
    public final Beast getBeast() {
        return this.beast;
    }

    /**
     * Retourne si la bete a gagnee
     * @return boolean
     */
    public final boolean isBeastWin() {
        return beastWin;
    }

    /**
     * Retourne si le chasseur a gagn�
     * @return boolean
     */
    public final boolean isHunterWin() {
        return hunterWin;
    }

    public final void setHunterWin(boolean hunterWin) {
        this.hunterWin=hunterWin;
    }

    public final void setBeastWin(boolean beastWin) {
        this.beastWin=beastWin;
    }

    public final void setBonusActif(IBonus bonus) {
        this.bonusActif.add(bonus);
    }

    public final List<IBonus> getBonusActif() {
        return this.bonusActif;
    }

    public final void removePiege() {
        for (IBonus ib : this.bonusActif) {
            if(ib.getPos().equals(null)) {
                this.bonusActif.remove(ib);
            }
        }
    }
    
    public final void removePiege(IBonus ib) {
    	this.bonusActif.remove(ib);
    }

    public final void HunterIsNearBait() {
        for(IBonus ib : this.bonusActif) {

            if(ib.getClass().isInstance(new Bait())) {
                if(this.getHunter().getPos().getPosX()-ib.getPos().getPosX()<2 && this.getHunter().getPos().getPosX()-ib.getPos().getPosX()>-2 &&
                        this.getHunter().getPos().getPosY()-ib.getPos().getPosY()<2 && this.getHunter().getPos().getPosY()-ib.getPos().getPosY()>-2 ) {
                    ib.setTriggered();
                }
            }
        }
    }


    /**
     * Retourne la Config
     * @return Config
     */
    public final Config getConfig() {
        return this.config;
    }

    /**
     * Teste si le deplacement souhaite pour Beast ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Hunter
     * @param mvt
     * @return boolean
     */
    public final boolean mvtValideBeast(Mouvment mvt) {
        return this.beast.verifDeplacementSpe(tab, mvt, hunter);
    }

    /**
     * Teste si le deplacement souhaite pour Hunter ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Beast
     * @param mvt
     * @return boolean
     */
    public final boolean mvtValideHunter(Mouvment mvt) {
        return this.hunter.verifDeplacementSpe(tab, mvt, beast);
    }

    /**
     * Deplace la Beast avec le Mouvment passe en parametre si ce dernier est possible
     * @return boolean
     */
    public final boolean moveBeast(Mouvment mvt) {
        if(this.mvtValideBeast(mvt)) {
            this.beast.setPos(mvt);
            this.tab[this.beast.getPos().getPosX()][this.beast.getPos().getPosY()].modifBeastWalk(true);
            return true;
        }return false;
    }

    /**
     * Deplace la Hunter avec le Mouvment passe en parametre si ce dernier est possible
     * @return boolean
     */
    public final boolean moveHunter(Mouvment mvt) {
        if(this.mvtValideHunter(mvt)) {
            this.hunter.setPos(mvt);
            return true;
        }return false;
    }

    /**
     * Parcours le tableau de Case et met a jour "beastPas" qui correspond a la duree depuis laquel Beast a ete sur la Case
     */
    public final void setBeastWalk() {
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab[i].length; j++)
                this.tab[i][j].modifBeastWalk(this.beast.isPosEnt(i, j));
    }

    public final String gameBeastToString() {
        String affichage="";
        for (int x = 0; x < this.tab.length; x++) {
            affichage+=" \n|";
            for (int y = 0; y < this.tab[x].length; y++) {
                affichage+=" "+this.tab[y][x].gameBeastShowView(this, new Position(y, x))+" |";
            }
        }
        return affichage;
    }

    public final String gameHunterToString() {
        String affichage="";
        for (int x = 0; x < this.tab.length; x++) {
            affichage+=" \n|";
            for (int y = 0; y < this.tab[x].length; y++) {
                if(this.hunter.isBlinded()) {
                    this.tab[y][x].setBlinded();
                    affichage+=" "+this.tab[y][x].gameHunterShowView(this, new Position(y, x))+" |";
                }else {
                    affichage+=" "+this.tab[y][x].gameHunterShowView(this, new Position(y, x))+" |";
                }
            }
        }
        return affichage;
    }

    
    public void triggerBonus() {
    	
    	for(IBonus b : this.bonusActif) {
    		
    		if(b.getPos() != null && b.getPos().equals(this.beast.getPos()) && b instanceof Trap) {
    			System.out.println("detected");
    			b.setTriggered();
    			
    		}
    		
    	}
    	
    	 
    }
    
    public void passTurnBonus() {
    	for (IBonus iBonus : bonusActif) {
			iBonus.nextTurnBonus();
		}
    }
    
    
    /**
     * Retourne les La Map sous la forme textuelle d'un tableau avec des obstacles, des buffs, des Entity
     */
    public String toString() {
        String affichage="";
        for (int x = 0; x < this.tab.length; x++) {
            affichage+=" \n|";
            for (int y = 0; y < this.tab[x].length; y++) {
                if(this.beast.isPosEnt(y, x)) affichage+=" "+this.beast.toString()+" |";
                else if(this.hunter.isPosEnt(y, x)) affichage+=" "+this.hunter.toString()+" |";
                else affichage+=" "+this.tab[y][x].toString()+" |";
            }
        }
        return affichage;
    }

}
