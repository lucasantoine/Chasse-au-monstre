package projet;

/**
 * @author Louis CLARISSE
 * Créé le 28 mars 2019
 */

/**
 * Classe Position, donnant la position sur le plateau d'un joueur, chasseur ou
 * monstre. Composee de ses coordonnees x et y, correspondant aux lignes et
 * colonnes du plateau de jeu.
 */

public class Position {
	protected int x;
	protected int y;

	/**
	 * Constructeur creant une position de coordonnees (x,y)
	 * 
	 * @param x
	 *            = ligne de la position
	 * @param y
	 *            = colonne de la position
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Constructeur creant une position par defaut */
	public Position() {
		this(0, 0);
	}

	/**
	 * Renvoie la valeur de x
	 * 
	 * @return la ligne de la position
	 */
	public int getX() {
		return x;
	}

	/**  */
	/**
	 * Modifie la valeur de x
	 * 
	 * @param x
	 *            = changement de ligne
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Renvoie la valeur de y
	 * 
	 * @return la colonne de la position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Modifie la valeur de y
	 * 
	 * @param y
	 *            = changement de colonne
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Modifie la position en fonction des valeurs passees en parametres
	 * 
	 * @param x
	 *            = valeur de la ligne
	 * @param y
	 *            = valeur de la colonne
	 */
	public void setPosition(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/** Affiche une position sous forme textuelle */
	public String toString() {
		return "(" + this.getX() + "," + this.getY() + ")";
	}

	/**
	 * Modifie la valeur de position en fonction de direction
	 * 
	 * @param d
	 *            = Direction du deplacement
	 */
	public void setPosition(Direction d) {
		if (d.equals(Direction.NORD)) {
			this.setX(this.x - 1);
		}
		if (d.equals(Direction.SUD)) {
			this.setX(this.x + 1);
		}
		if (d.equals(Direction.EST)) {
			this.setY(this.y + 1);
		}
		if (d.equals(Direction.OUEST)) {
			this.setY(this.y - 1);
		}
	}
}
