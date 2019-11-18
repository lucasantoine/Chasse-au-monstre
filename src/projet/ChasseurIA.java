package projet;

/**
 * @author ANTOINE Lucas Cree le 9 mai 2019
 */
/**
 * Classe abstraite d'une IA heritant de Chasseur redefinissant les methodes de
 * deplacement et changeant l'image renvoyee. Il existe 2 classes heritant de
 * celle-ci modifiant l'intelligence du Chasseur selon son degre de difficulte.
 */
public abstract class ChasseurIA extends Chasseur {

	/**
	 * Constructeur de l'objet ChasseurIA prenant en parametre deux entiers
	 * symbolisant la position du ChasseurIA sur le plateau et le nombre de pieges
	 * du ChasseurIA en debut de partie
	 * 
	 * @param x        = ligne de la case du chasseurIA
	 * @param y        = colonne de la case du chasseurIA
	 * @param nbPieges = nombre de pieges du chasseurIA
	 */
	public ChasseurIA(int x, int y, int nbPieges) {
		super(x, y, nbPieges);
	}

	/**
	 * Constructeur derive de l'objet ChasseurIA prenant en parametre un plateau et
	 * placant le chasseurIA sur une case aleatoire avec un nombre de pieges par
	 * defaut
	 * 
	 * @param p = plateau de jeu
	 */
	public ChasseurIA(Plateau p) {
		super(p);
	}

	public boolean isIA() {
		return true;
	}

	/**
	 * Redefinition de la methode setPosition du Chasseur afin de creer des motifs
	 * de deplacements predefinis sur le plateau, definis selon le niveau de
	 * difficulte de l'IA
	 * 
	 * @param p = plateau de jeu
	 */
	public abstract void setPosition(Plateau p);

	/**
	 * Renvoie le lien vers l'image correspondant au ChasseurIA sous forme de chaine
	 * de caracteres
	 * 
	 * @return le lien vers l'image correspondant au ChasseurIA
	 */
	public abstract String getImageChasseur();
}
