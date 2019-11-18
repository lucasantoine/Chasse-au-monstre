package projet;

import java.util.Random;

/**
 * @author Lucas ANTOINE
 * Cree le 6 avr. 2019
 */

/**
 * Classe abstraite Joueur contenant les methodes communes aux classes Monstre
 * et Chasseur
 */
public abstract class Joueur {
	private final static Random ALEA = new Random();
	protected Case courante;

	/**
	 * Constructeur de l'objet Joueur prenant en parametre deux entiers symbolisant
	 * la Position du Joueur sur le Plateau
	 * 
	 * @param x = ligne de la case du joueur
	 * @param y = colonne de la case du joueur
	 */
	public Joueur(int x, int y) {
		this.courante = new Case(x, y);
	}

	/**
	 * Constructeur derive de l'objet Joueur prenant en parametre un Plateau et
	 * placant le joueur sur une case aleatoire
	 * 
	 * @param plateau = plateau de jeu
	 */
	public Joueur(Plateau plateau) {
		this(Joueur.ALEA.nextInt(((int) Math.sqrt(plateau.getTaillePlateau()))),
				Joueur.ALEA.nextInt(((int) Math.sqrt(plateau.getTaillePlateau()))));
	}

	/**
	 * Renvoie la ligne du Joueur
	 * 
	 * @return la ligne du Joueur
	 */
	public int getX() {
		return this.courante.getX();
	}

	/**
	 * Renvoie la colonne du Joueur
	 * 
	 * @return la colonne du Joueur
	 */
	public int getY() {
		return this.courante.getY();
	}

	/**
	 * Renvoie la Case courante du Joueur
	 * 
	 * @return la Case courante du Joueur
	 */
	public Case getCaseCourante() {
		return this.courante;
	}

	/**
	 * Verifie que le deplacement est possible dans la direction passee en parametre
	 * 
	 * @param d = Direction
	 * @param p = plateau de jeu
	 * @return vrai si le deplacement vers d est possible, faux sinon.
	 */
	protected boolean verifPosition(Direction d, Plateau p) {
		if (d.equals(Direction.NORD)) {
			return this.getX() == 0 ? false : true;
		} else if (d.equals(Direction.SUD)) {
			return this.getX() == p.getCote() - 1 ? false : true;
		} else if (d.equals(Direction.EST)) {
			return this.getY() == p.getCote() - 1 ? false : true;
		} else if (d.equals(Direction.OUEST)) {
			return this.getY() == 0 ? false : true;
		} else {
			return true;
		}
	}

	/**
	 * Renvoie le Joueur sous forme textuelle
	 * 
	 * @return le Joueur sous forme textuelle
	 */
	public abstract String toString();

	/**
	 * Renvoie vrai si le Joueur est une IA, faux sinon
	 * 
	 * @return vrai si le Joueur est une IA, faux sinon
	 */
	public abstract boolean isIA();
}