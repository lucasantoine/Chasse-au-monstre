package projet;

import java.util.Random;

/**
 * @author Louis CLARISSE Cree le 25 avr. 2019
 */

/**
 * Classe du chasseur de la chasse aux monstres, son but etant de trouver le
 * monstre avant que ce dernier ne visite tout le plateau. Pour se faire il peut
 * donc se deplacer sur la case du plateau de son choix, ou choisir de poser un
 * piege sur sa case courante. Lorsqu'un piege est declenche par le monstre, il
 * sera desoriente et il fera un deplacement aleatoire. Il a egalement la
 * capacite de savoir quand le monstre est passe pour la premiere fois sur une
 * case
 */
public class Chasseur extends Joueur {
	protected final static Random ALEA = new Random();
	private int nbPieges;
	private String imageChasseur = "file:./res/JoueurChasseur.png";

	/**
	 * Constructeur de l'objet Chasseur prenant en parametre deux entiers
	 * symbolisant la position du Chasseur sur le plateau et le nombre de pieges du
	 * Chasseur en debut de partie
	 * 
	 * @param x        = ligne de la case du chasseur
	 * @param y        = colonne de la case du chasseur
	 * @param nbPieges = nombre de pieges du chasseur
	 */
	public Chasseur(int x, int y, int nbPieges) {
		super(x, y);
		this.nbPieges = nbPieges;
	}

	/**
	 * Constructeur derive de l'objet Chasseur prenant en parametre un plateau et
	 * placant le chasseur sur une case aleatoire avec un nombre de pieges par
	 * defaut
	 * 
	 * @param plateau = plateau de jeu
	 */
	public Chasseur(Plateau plateau) {
		this(Chasseur.ALEA.nextInt(plateau.getCote()), Chasseur.ALEA.nextInt(plateau.getCote()), 3);
	}

	/**
	 * Renvoie vrai en cas de victoire du chasseur, renvoie faux sinon
	 * 
	 * @param m = monstre de la partie en cours
	 * @return vrai si le chasseur a gagne, faux sinon.
	 */
	public boolean victoire(Monstre m) {
		return super.getX() == m.getX() && super.getY() == m.getY();
	}

	/** Renvoie un chasseur sous forme de chaine de caracteres */
	@Override
	public String toString() {
		return "[Chasseur : Position (" + super.getX() + "," + super.getY() + "); Nombre de pieges : " + this.nbPieges
				+ "]";
	}

	/**
	 * Deplace le chasseur sur la case (x,y) du plateau de jeu
	 * 
	 * @param p = plateau de jeu
	 * @param x = ligne de la case
	 * @param y = colonne de la case
	 * @throws ArrayIndexOutOfBoundsException = Exception geree dans la classe
	 *                                        d'affichage Partie.
	 */
	protected void setChasseur(Plateau p, int x, int y) throws ArrayIndexOutOfBoundsException {
		p.setChasseur(x, y, true);
		p.setChasseur(super.getX(), super.getY(), false);
		super.courante.setPosition(x, y);
		p.setChasseur(x, y, true);
	}

	/**
	 * Permet de poser un piege sur la case courante du chasseur si possible, le
	 * fait se deplacer sinon
	 * 
	 * @param p = plateau de jeu
	 */
	public void setPiege(Plateau p) {
		if (this.nbPieges > 0) {
			p.setPiege(super.getX(), super.getY());
			this.nbPieges--;
		}
	}

	/**
	 * Renvoie vrai si le Chasseur est une IA, faux sinon.
	 */
	public boolean isIA() {
		return false;
	}

	/**
	 * Renvoie le nombre de pieges restant au Chasseur.
	 * 
	 * @return nbPieges
	 */
	public int getPieges() {
		return this.nbPieges;
	}

	/**
	 * Renvoie le lien vers l'image correspondant au Chasseur sous forme de chaine
	 * de caracteres
	 * 
	 * @return le lien vers l'image correspondant au Chasseur
	 */
	public String getImageChasseur() {
		return this.imageChasseur;
	}
}
