package projet;

/**
 * 
 * @author CLARISSE Louis Cree le 20 mai 2019
 */
/**
 * Classe d'une IA Moyenne heritant de ChasseurIA redefinissant les methodes de
 * deplacement et changeant l'image renvoyee. A ce niveau de difficulte, l'IA se
 * deplace de maniere aleatoire sur le plateau jusqu'a trouver une case visitee
 * par le monstre. A ce moment la, son prochain deplacement se fera dans une
 * zone ou le monstre pourrait se trouver , en prenant en compte le tour ou la
 * case a ete visitee. Il pose un piege tous les 3 tours jusqu'a ne plus en
 * avoir.
 */
public class ChasseurIAMoyen extends ChasseurIA {
	String imageChasseur = "file:./res/chasseurIAMoyen.png";

	/**
	 * Constructeur derive de l'objet ChasseurIAMoyen prenant en parametre un
	 * plateau et placant le chasseurIAMoyen sur une case aleatoire avec un nombre
	 * de pieges par defaut
	 * 
	 * @param p = plateau de jeu
	 */
	public ChasseurIAMoyen(Plateau p) {
		super(p);
	}

	/**
	 * Constructeur de l'objet ChasseurIAMoyen prenant en parametre deux entiers
	 * symbolisant la position du ChasseurIAMoyen sur le plateau et le nombre de
	 * pieges du ChasseurIAMoyen en debut de partie
	 * 
	 * @param x        = ligne de la case du chasseurIAMoyen
	 * @param y        = colonne de la case du chasseurIAMoyen
	 * @param nbPieges = nombre de pieges du chasseurIAMoyen
	 */
	public ChasseurIAMoyen(int x, int y, int nbPieges) {
		super(x, y, nbPieges);
	}

	/**
	 * Difficulte moyenne : Deplacements aleatoires jusqu'a trouver une case visitee
	 * par le monstre. A ce moment la, son prochain deplacement se fera dans une
	 * zone ou le monstre pourrait se trouver , en prenant en compte le tour ou la
	 * case a ete visitee. Il pose un piege tous les 3 tours jusqu'a ne plus en
	 * avoir.
	 */
	public void setPosition(Plateau p) {
		if (Partie.cptC % 3 == 0 && super.getPieges() > 0) {
			this.setPiege(p);
		}
		if (!p.getPlateau()[super.getX()][super.getY()].isVisite()) {
			this.setChasseur(p, ALEA.nextInt(p.getCote()), ALEA.nextInt(p.getCote()));
		} else {
			if ((Partie.cptC) - (p.getPlateau()[super.getX()][super.getY()].getCptTours()) > (p.getCote() - 1)) {
				this.setChasseur(p, ALEA.nextInt(p.getCote()), ALEA.nextInt(p.getCote()));
			} else {
				this.setChasseur(p,
						ALEA.nextInt((Partie.cptC) - (p.getPlateau()[super.getX()][super.getY()].getCptTours())),
						ALEA.nextInt((Partie.cptC) - (p.getPlateau()[super.getX()][super.getY()].getCptTours())));
			}
		}
	}

	@Override
	public String getImageChasseur() {
		return this.imageChasseur;
	}

}
