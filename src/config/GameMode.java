package config;

/**
 * Cette enumeration liste les modes de jeu disponible aux utilisateurs.
 * AIvsAI        : L'ordinateur joue contre lui meme et controle les deux entites.
 * BEASTvsAI     : Le joueur controle le monstre et l'ordinateur controle le chasseur.
 * HUNTERvsAI    : Le joueur controle le chasseur et l'ordinateur controle le monstre.
 * BEASTvsHUNTER : Un des deux joueurs controle le monstre et son adversaire controle le chasseur.
 * @author PHPierre
 *
 */
public enum GameMode {
	AIvsAI,
	BEASTvsAI,
	HUNTERvsAI,
	BEASTvsHUNTER;
}
