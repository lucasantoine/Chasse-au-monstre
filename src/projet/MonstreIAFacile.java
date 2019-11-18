package projet;

/**
 * @author Lucas ANTOINE Creee le 9 mai 2019
 */
/**
 * Classe d'une IA Facile heritant de MonstreIA redefinissant les methodes de
 * deplacement et changeant l'image renvoyee. A ce niveau de difficulte, l'IA se
 * deplace de maniere aleatoire sur le plateau.
 */

public class MonstreIAFacile extends MonstreIA {
	private String imageMonstre = "file:./res/monstreIAFacile.png";

	/**
	 * Constructeur derive de l'objet MonstreIAFacile prenant en parametre un
	 * Plateau et placant le MonstreIAFacile sur une case aleatoire
	 * 
	 * @param plateau = plateau de jeu
	 */
	public MonstreIAFacile(Plateau plateau) {
		super(plateau);
	}

	/**
	 * Constructeur de l'objet MonstreIAFacile prenant en parametre deux entiers
	 * symbolisant la position du MonstreIAFacile sur le Plateau
	 * 
	 * @param x = ligne de la case du monstreIAFacile
	 * @param y = colonne de la case du monstreIAFacile
	 * @param p = plateau de jeu
	 */
	public MonstreIAFacile(int x, int y, Plateau p) {
		super(x, y, p);
	}

	/**
	 * Difficulte facile : Deplacements aleatoires sur le plateau de jeu
	 */
	public void setPosition(Plateau p) {
		try {
			Direction[] aleatoire = Direction.values();
			super.setPosition(aleatoire[ALEA.nextInt(aleatoire.length)], p);
		} catch (ArrayIndexOutOfBoundsException e) {
			this.setPosition(p);
		}
	}

	@Override
	public String getImageMonstre() {
		return this.imageMonstre;
	}
}
