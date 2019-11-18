package projet;

/**
 * @author Lucas ANTOINE Cree le 5 avr. 2019
 */

/**
 * Classe Plateau permettant de modeliser un plateau de jeu carre sous forme
 * d'un tableau de cases a deux dimensions. C'est sur ce plateau que la partie
 * se deroule et ou se trouve un Monstre, un Chasseur, et les eventuels pieges
 * poses par le Chasseur
 */
public class Plateau {
	private final String NL = System.getProperty("line.separator");
	private Case[][] plateau;

	/**
	 * Constructeur permettant d'initialiser un plateau de jeu d'au moins 5 cases de
	 * cote, et de 10 maximum.
	 * 
	 * @param cote = taille de tableau variable.
	 */
	public Plateau(int cote) {
		if (cote >= 5 && cote <= 10) {
			this.plateau = new Case[cote][cote];
		} else if (cote > 10) {
			this.plateau = new Case[10][10];
		} else {
			this.plateau = new Case[5][5];
		}
		this.initialiserPlateau();
	}

	/**
	 * Constructeur derive initialisant un plateau de jeu par defaut a une longueur
	 * de 5 cases de cote
	 */
	public Plateau() {
		this(5);
	}

	/**
	 * /** Renvoie le plateau de jeu
	 * 
	 * @return le plateau de jeu
	 */
	public Case[][] getPlateau() {
		return plateau;
	}

	/**
	 * Permet de modifier l'etat de la case concernant la presence ou non d'un
	 * chasseur
	 * 
	 * @param x        = ligne de la case sur le plateau
	 * @param y        = colonne de la case sur le plateau
	 * @param chasseur = vrai ou faux
	 */
	public void setChasseur(int x, int y, boolean chasseur) {
		this.plateau[x][y].setChasseur(chasseur);
	}

	/**
	 * Permet de modifier l'etat de la case concernant la presence ou non d'un
	 * monstre
	 * 
	 * @param x       = ligne de la case sur le plateau
	 * @param y       = colonne de la case sur le plateau
	 * @param monstre = vrai ou faux
	 */
	public void setMonstre(int x, int y, boolean monstre) throws ArrayIndexOutOfBoundsException {
		this.plateau[x][y].setMonstre(monstre);
		this.plateau[x][y].setVisite();
	}

	/**
	 * Renvoie un Plateau sous sa forme textuelle globale
	 */
	public String toString() {
		String res = "";
		for (Case[] c1 : plateau) {
			for (Case c2 : c1) {
				res += c2.toString();
			}
			res += NL;
		}
		return res;
	}

	/**
	 * Renvoie le nombre de cases du Plateau
	 * 
	 * @return nombre de cases du Plateau
	 */
	public int getTaillePlateau() {
		return (int) Math.pow(this.plateau.length, 2.0);
	}

	/** Initialise le plateau avec des Cases vides */
	private void initialiserPlateau() {
		for (int i = 0; i < this.plateau.length; i++) {
			for (int j = 0; j < this.plateau[i].length; j++) {
				this.plateau[i][j] = new Case(i, j);
			}
		}
	}

	/**
	 * Renvoie la longueur d'un cote du tableau
	 * 
	 * @return longueur d'un cote du Plateau
	 */
	public int getCote() {
		return this.plateau.length;
	}

	/**
	 * Modifie la case (x,y) en case visitee
	 * 
	 * @param x = ligne de la case du Plateau
	 * @param y = colonne de la case du Plateau
	 */
	public void setVisite(int x, int y) {
		this.plateau[x][y].setVisite();
	}

	/**
	 * Modifie la case (x,y) en case piegee
	 * 
	 * @param x = ligne de la case du Plateau
	 * @param y = colonne de la case du Plateau
	 */
	public void setPiege(int x, int y) {
		this.plateau[x][y].setPiege(true);
	}

	/**
	 * Renvoie vrai si l'integralite du Plateau a ete visite
	 * 
	 * @return vrai si le plateau est visite, faux sinon.
	 */
	public boolean plateauVisite() {
		for (Case[] c1 : plateau) {
			for (Case c2 : c1) {
				if (!c2.isVisite()) {
					return false;
				}
			}
		}
		return true;
	}

	/** Modifie le Plateau en Plateau completement visite */
	public void setVisite() {
		for (Case[] c1 : plateau) {
			for (Case c2 : c1) {
				c2.setVisite();
			}
		}
	}

	/** Incremente le compteur de tour pour les cases non visitees par le monstre */
	public void incr() {
		for (Case[] c1 : this.plateau) {
			for (Case c2 : c1) {
				if (!c2.isVisite()) {
					c2.incrTour();
				}
			}
		}
	}

	/**
	 * Renvoie la case de coordonnees (x,y) sur le plateau
	 * 
	 * @param x = ligne de la case sur le plateau
	 * @param y = colonne de la case sur le plateau
	 * @return la case (x,y) sur le plateau
	 */
	public Case getCase(int x, int y) {
		return this.plateau[x][y];
	}
}
