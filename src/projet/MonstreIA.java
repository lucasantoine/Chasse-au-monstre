package projet;

/**
 * @author ANTOINE Lucas Cree le 9 mai 2019
 */
/**
 * Classe abstraite d'une IA heritant de Monstre redefinissant les methodes de
 * deplacement et changeant l'image renvoyee. Il existe 2 classes heritant de
 * celle-ci modifiant l'intelligence du Monstre selon son degre de difficulte.
 */
public abstract class MonstreIA extends Monstre {

	/**
	 * Constructeur de l'objet MonstreIA prenant en parametre deux entiers
	 * symbolisant la position du MonstreIA sur le Plateau
	 * 
	 * @param x = ligne de la case du monstreIA
	 * @param y = colonne de la case du monstreIA
	 * @param p = plateau de jeu
	 */
	public MonstreIA(int x, int y, Plateau p) {
		super(x, y, p);
	}

	/**
	 * Constructeur derive de l'objet MonstreIA prenant en parametre un Plateau et
	 * placant le MonstreIA sur une case aleatoire
	 * 
	 * @param plateau = plateau de jeu
	 */
	public MonstreIA(Plateau plateau) {
		super(plateau);
	}

	public boolean isIA() {
		return true;
	}

	/**
	 * Redefinition de la methode setPosition du Monstre afin de creer des motifs de
	 * deplacements predefinis sur le plateau, definis selon le niveau de difficulte
	 * de l'IA
	 * 
	 * @param p = plateau de jeu
	 */
	public abstract void setPosition(Plateau p);

	/**
	 * Renvoie le lien vers l'image correspondant au MonstreIA sous forme de chaine
	 * de caracteres
	 * 
	 * @return le lien vers l'image correspondant au MonstreIA
	 */
	public abstract String getImageMonstre();
}
