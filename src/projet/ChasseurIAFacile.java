package projet;

/**
 * @author Lucas ANTOINE Cree le 9 mai 2019
 */
/**
 * Classe d'une IA Facile heritant de ChasseurIA redefinissant les methodes de
 * deplacement et changeant l'image renvoyee. A ce niveau de difficulte, l'IA se
 * deplace de maniere aleatoire sur le plateau et ne pose jamais de pieges.
 */

public class ChasseurIAFacile extends ChasseurIA {
	String imageChasseur = "file:./res/chasseurIAFacile.png";

	/**
	 * Constructeur derive de l'objet ChasseurIAFacile prenant en parametre un
	 * plateau et placant le chasseurIAFacile sur une case aleatoire avec un nombre
	 * de pieges par defaut
	 * 
	 * @param p = plateau de jeu
	 */
	public ChasseurIAFacile(Plateau p) {
		super(p);
	}

	/**
	 * Constructeur de l'objet ChasseurIAFacile prenant en parametre deux entiers
	 * symbolisant la position du ChasseurIAFacile sur le plateau et le nombre de
	 * pieges du ChasseurIAFacile en debut de partie
	 * 
	 * @param x        = ligne de la case du chasseurIAFacile
	 * @param y        = colonne de la case du chasseurIAFacile
	 * @param nbPieges = nombre de pieges du chasseurIAFacile
	 */
	public ChasseurIAFacile(int x, int y, int nbPieges) {
		super(x, y, nbPieges);
	}

	/**
	 * Difficulte Facile : Deplacements aleatoires sur le plateau de jeu.
	 */
	public void setPosition(Plateau p) {
		this.setChasseur(p, ALEA.nextInt(p.getCote()), ALEA.nextInt(p.getCote()));
	}

	@Override
	public String getImageChasseur() {
		return this.imageChasseur;
	}

}
