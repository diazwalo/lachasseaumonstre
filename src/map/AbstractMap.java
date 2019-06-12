package map;

import config.Config;
import interaction.Interaction;
import render.bonus.Bait;
import render.bonus.IBonus;
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
    
    /**
     * Genere les bonus de la Bete et du Chasseur si ces dernier sont demandes dans la Configue,les bonus sont places respectif de leurs cotes)
     */
    public void generationBonus() {
    	Position posTrap = new Position(-1, -1);
    	Position posBait = new Position(-1, -1);
    	
    	if(this.config.isTrap()) {
    		posTrap=this.createPositionTempoFirstBonus(0, 0, new boolean[] { true,  false }, false);
    	}
    	if(this.config.isWard()) {
    		this.createPositionTempoSecondBonus(0, 0, new boolean[] { false,  true }, posTrap, false);
    	}
		if(this.config.isBait()) {
			posBait=this.createPositionTempoFirstBonus((this.tab.length)-1, (this.tab[0].length)-1, new boolean[] { true,  false }, true);
		}
		if(this.config.isCamouflage()) {
			this.createPositionTempoSecondBonus((this.tab.length)-1, (this.tab[0].length)-1, new boolean[] { false,  true }, posBait, true);
		}
	}


    
    /**
     * Place le Premier Bonus de l'Entite concernee (grace au parametre bonusHunterOrBeast) de son cote
     * @param idxWidth place sur la largeure de l'entitee
     * @param idxHeight place sur la longueur de l'entitee
     * @param tabBonus La configuration des bonus.
     * @param bonusHunterOrBeast True pour la bete, false pour le monstre.
     * @return Position
     */
    public final Position createPositionTempoFirstBonus(int idxWidth, int idxHeight, boolean[] tabBonus, boolean bonusHunterOrBeast) {
        Random r=new Random();
        Position posBonusFinal=new Position(-1 , -1);

        while(posBonusFinal.isPos(-1, -1)) {
            int posBonusX=idxWidth+r.nextInt((this.tab.length/2));
            int posBonusY=idxHeight+r.nextInt((this.tab[0].length/2));
            
            if(idxWidth != 0 || idxHeight != 0) {
            	posBonusY=idxWidth-r.nextInt(idxWidth/2);
            	posBonusX=idxHeight-r.nextInt(idxHeight/2);
            }
            
            Position posBonusTempo=new Position(posBonusX, posBonusY);
            if(! this.tab[posBonusY][posBonusX].isObstacle() && 
            		! this.beast.getPos().equals(posBonusTempo) && 
            		! this.hunter.getPos().equals(posBonusTempo)) {
                posBonusFinal=posBonusTempo;
            }
        }

        if(bonusHunterOrBeast) {
        	tab[posBonusFinal.getPosY()][posBonusFinal.getPosX()].setBonusBeast(tabBonus);
        }
        else {
        	tab[posBonusFinal.getPosY()][posBonusFinal.getPosX()].setBonusHunter(tabBonus);
        }

        return posBonusFinal;
    }

    /**
     * Place le Deuxieme Bonus de l'Entite concernee (grace au parametre bonusHunterOrBeast) de son cote en s'assurant que ce dernier ne soit pas a la position du premier Bonus
     * @param idxWidth place sur la largeure de l'entitee
     * @param idxHeight place sur la longueur de l'entitee
     * @param tabBonus La configuration des bonus
     * @param posFirstBonus La position du premier bonus.
     * @param bonusHunterOrBeast True pour la bete, false pour le monstre.
     */
    public final void createPositionTempoSecondBonus(int idxWidth, int idxHeight, boolean[] tabBonus, Position posFirstBonus, boolean bonusHunterOrBeast) {
        Random r=new Random();
        Position posBonusFinal=new Position(-2 , -2);

        while(posBonusFinal.isPos(-2, -2)) {
            int posBonusX=idxHeight+r.nextInt(this.tab.length/2);
            int posBonusY=idxHeight+r.nextInt(this.tab[0].length/2);

            if(idxWidth != 0 || idxHeight != 0) {
            	posBonusY=idxWidth-r.nextInt(idxWidth/2);
            	posBonusX=idxHeight-r.nextInt(idxHeight/2);
            }

            Position posBonusTempo=new Position(posBonusX, posBonusY);

            if(! this.tab[posBonusY][posBonusX].isObstacle() && ! this.beast.getPos().equals(posBonusTempo) && ! this.hunter.getPos().equals(posBonusTempo) && ! posBonusFinal.equals(posFirstBonus)) {
                posBonusFinal=posBonusTempo;
            }
        }

        if(bonusHunterOrBeast)  {
        	tab[posBonusFinal.getPosY()][posBonusFinal.getPosX()].setBonusBeast(tabBonus);
        }else {
        	tab[posBonusFinal.getPosX()][posBonusFinal.getPosY()].setBonusHunter(tabBonus);
        }
    }

    /**
     * Retourne le tableau a deux dimentions de Case
     * @return Le tableau qui forme le tableau de jeu.
     */
    public final Case[][] getTab() {
        return this.tab;
    }

    /**
     * Retourne le Hunter
     * @return Toutes les proprietes du chsseur en jeu.
     */
    public Hunter getHunter() {
        return this.hunter;
    }

    /**
     * Retourne la Beast
     * @return Toutes les proprietes de la bete en jeu.
     */
    public final Beast getBeast() {
        return this.beast;
    }

    /**
     * Retourne si la bete a gagnee
     * @return Si le monstre a gagne ou non.
     */
    public final boolean isBeastWin() {
        return beastWin;
    }

    /**
     * Retourne si le chasseur a gagne
     * @return Si la bete a gagne ou non.
     */
    public final boolean isHunterWin() {
        return hunterWin;
    }

    /**
     * Passe hunterWin au Boolean passe en parametre
     * @param hunterWin Un boolean indiquant si le chasseur a gagne
     */
    public final void setHunterWin(boolean hunterWin) {
        this.hunterWin=hunterWin;
    }

    /**
     * Passe beastWin au Boolean passe en parametre
     * @param beastWin Un boolean indiquant si la bete a gagne.
     */
    public final void setBeastWin(boolean beastWin) {
        this.beastWin=beastWin;
    }

    /**
     * Ajoute le IBonus passe en parametre a la Liste de IBonus Actif
     * @param bonus Le bonus a activer.
     */
    public final void addBonusActif(IBonus bonus) {
        this.bonusActif.add(bonus);
    }

    /**
     * Retourne la Liste de IBonus Actif
     * @return La liste des bonus actifs.
     */
    public final List<IBonus> getBonusActif() {
        return this.bonusActif;
    }

    /**
     * Parcourt la Liste de IBonus Actif supprime ceux dont la Position est a null
     */
    public final void removeBonus() {
    	List<IBonus> bonusToRemove = new ArrayList<>();
        for (IBonus ib : this.bonusActif) {
            if(ib.getPos() == null) {
                bonusToRemove.add(ib);
            }
        }
        this.bonusActif.removeAll(bonusToRemove);
    }
    
    /**
     * Supprime le IBonus (passe en parametre) de la liste de IBonus Actif
     * @param ib Le bonus a supprimer.
     */
    public final void removeBonus(IBonus ib) {
    	this.bonusActif.remove(ib);
    }
    
    /**
     * Declanche le Leurre si le Chasseur passe a cote
     */
    public final void hunterIsNearBait() {
        for(IBonus ib : this.bonusActif) {

            if(ib instanceof Bait) {
                if(this.getHunter().getPos().getPosX()-ib.getPos().getPosX()<2 && this.getHunter().getPos().getPosX()-ib.getPos().getPosX()>-2 &&
                        this.getHunter().getPos().getPosY()-ib.getPos().getPosY()<2 && this.getHunter().getPos().getPosY()-ib.getPos().getPosY()>-2 ) {
                    ib.setTriggered();
                    ((Bait) ib).setVisible(true);
                }
                else {
                	( (Bait) ib).setVisible(false);
                }
            }
        }
    }


    /**
     * Retourne la Configuration fourni lors de l'instanciation d'un plateau de jeu.
     * @return Retourne la configuration.
     */
    public final Config getConfig() {
        return this.config;
    }

    /**
     * Teste si le deplacement souhaite pour Beast ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Hunter
     * @param mvt Le mouvement a tester pour l'entite monstre.
     * @return Si le monstre peut effectuer ce deplacement.
     */
    public final boolean mvtValideBeast(Mouvment mvt) {
        return this.beast.verifDeplacementSpe(tab, mvt, hunter);
    }

    /**
     * Teste si le deplacement souhaite pour Hunter ne fait pas sortir du tableau ou tomber sur un obstacle ou encore un Beast
     * @param mvt Le mouvement a tester pour l'entite Chasseur.
     * @return Si le chasseur peut effectuer ce deplacement.
     */
    public final boolean mvtValideHunter(Mouvment mvt) {
        return this.hunter.verifDeplacementSpe(tab, mvt, beast);
    }

    /**
     * Deplace la Beast avec le Mouvment passe en parametre si ce dernier est possible
     * @param mvt Le moovement a effectue par le bete
     * @return Si le deplacement a ete correctement effectue.
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
     * @param mvt Le moovement a effectue par le chasseur
     * @return Si le deplacement a ete correctement effectue.
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

    /**
     * Retourne la partie de la maniere ou la bete doit la voir et ce sous la forme d'une chaine de charactere
     * @return Le plateau de jeu vu par la bete.
     */
    public final String gameBeastToString() {
    	Interaction.clearScreen();
        String affichage="";
        for (int x = 0; x < this.tab.length; x++) {
            affichage+=" \n|";
            for (int y = 0; y < this.tab[x].length; y++) {
                affichage+=" "+this.tab[y][x].gameBeastShowView(this, new Position(y, x))+" |";
            }
        }
        return affichage;
    }

    /**
     * Retourn la Partie De la maniere ou le Chasseur doit la voir et ce sous la forme d'une chaine de charactere
     * @return Le plateau du jeu vu par le chasseur.
     */
    public final String gameHunterToString() {
    	Interaction.clearScreen();
        String affichage="";
        for (int x = 0; x < this.tab.length; x++) {
            affichage+=" \n|";
            for (int y = 0; y < this.tab[x].length; y++) {
              
            	affichage+=" "+this.tab[y][x].gameHunterShowView(this, new Position(y, x))+" |";
                
            }
        }
        return affichage;
    }
    
    /**
     * Effectue tout les changement a operer sur les bonus actifs lorsque l'on passe un tour
     */
    public void passTurnBonus() {
    	for (IBonus iBonus : bonusActif) {
			iBonus.nextTurnBonus();
		}
    	this.removeBonus();
    }
    
    
    /**
     * Retourne le plateau de jeu sous la forme textuelle d'un tableau avec des obstacles, des buffs, des Entitys (Mode spectateur).
     */
    public String toString() {
        String affichage="";
        for (int x = 0; x < this.tab.length; x++) {
            affichage+=" \n|";
            for (int y = 0; y < this.tab[x].length; y++) {
                if(this.beast.isPosEnt(y, x)) affichage+=" "+this.beast.toString()+" |";
                else if(this.hunter.isPosEnt(y, x)) affichage+=" "+this.hunter.toString()+" |";
                else affichage+=" "+this.tab[x/*y*/][y/*x*/].toString()+" |";
            }
        }
        return affichage;
    }

}
