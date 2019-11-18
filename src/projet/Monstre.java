package projet;

import java.util.Random;

/**
 * @author Lucas ANTOINE
 * Cree le 24 mars 2019
 */

/**
 * Classe du monstre de la chasse aux monstres, son but etant de visiter chacune
 * des cases du plateau de jeu avant d'etre trouve par le chasseur. Pour se
 * faire il peut donc se deplacer d'une case dans la direction de son choix, ou
 * choisir de rester sur sa case. Attention cependant aux pieges qui
 * desorienteront le monstre et lui fera faire un deplacement aleatoire.
 */
public class Monstre extends Joueur {
	protected final static Random ALEA = new Random();
	private int casesR;
	private String imageMonstre = "file:./res/JoueurMonstre.png";

	/**
	 * Constructeur de l'objet Monstre prenant en parametre deux entiers symbolisant
	 * la position du Monstre sur le Plateau
	 * 
	 * @param x = ligne de la case du monstre
	 * @param y = colonne de la case du monstre
	 * @param p = plateau de jeu
	 */
	public Monstre(int x, int y, Plateau p) {
		super(x, y);
		this.casesR = p.getTaillePlateau() - 1;
	}

	/**
	 * Constructeur derive de l'objet Monstre prenant en parametre un Plateau et
	 * placant le joueur sur une case aleatoire
	 * 
	 * @param plateau = plateau de jeu
	 */
	public Monstre(Plateau plateau) {
		this(Monstre.ALEA.nextInt(plateau.getCote()), Monstre.ALEA.nextInt(plateau.getCote()), plateau);
	}

	/**
	 * Renvoie le nombre de cases restantes a visiter avant une victoire du Monstre
	 * 
	 * @return le nombre de cases a visiter
	 */
	public int getCasesR() {
		return this.casesR;
	}

	/**
	 * Modifie la position du monstre sur le plateau en fonction d'une direction
	 * 
	 * @param d = Direction vers laquelle le monstre veut se deplacer
	 * @param p = plateau de jeu
	 */
	protected void setPosition(Direction d, Plateau p) {
		if (super.verifPosition(d, p)) {
			p.setMonstre(this.getX(), this.getY(), false);
			super.courante.setPosition(d);
			if (!p.getCase(getX(), getY()).isVisite()) {
				this.casesR--;
			}
			p.setMonstre(this.getX(), this.getY(), true);
		}
	}

	public String toString() {
		return "[Monstre : Position (" + this.getX() + "," + this.getY() + ")" + " Cases restantes (" + this.getCasesR()
				+ ")]";
	}

	/**
	 * Renvoie vrai si le Monstre a gagne la partie, renvoie faux sinon
	 * 
	 * @return vrai si le Monstre a gagne, faux sinon.
	 */
	public boolean victoire() {
		return this.casesR == 0;
	}

	/** Met le nombre de cases restantes a visiter au monstre a zero */
	public void setCasesRNulles() {
		this.casesR = 0;
	}

	public boolean isIA() {
		return false;
	}

	/**
	 * Modifie la position du monstre sur le plateau de jeu quand celui-ci est piege
	 * par le Chasseur
	 * 
	 * @param p = plateau de jeu
	 */
	public void setPositionPiege(Plateau p) {
		try {
			Direction[] aleatoire = Direction.values();
			this.setPosition(aleatoire[ALEA.nextInt(aleatoire.length)], p);
		} catch (ArrayIndexOutOfBoundsException e) {
			this.setPositionPiege(p);
		}
	}

	/**
	 * Renvoie le lien vers l'image correspondant au Monstre sous forme de chaine de
	 * caracteres
	 * 
	 * @return le lien vers l'image correspondant au Monstre
	 */
	public String getImageMonstre() {
		return this.imageMonstre;
	}
}