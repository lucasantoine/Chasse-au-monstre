package projet;

/**
 * 
 * @author CLARISSE Louis Cree le 20 mai 2019
 */
/**
 * Classe d'une IA Moyenne heritant de MonstreIA redefinissant les methodes de
 * deplacement et changeant l'image renvoyee. A ce niveau de difficulte, l'IA se
 * deplace dans une direction sur le plateau jusqu'a tomber sur un bord du
 * plateau. Il change alors de direction et recommence. Il ne peut pas se
 * deplacer sur une case qu'il a deja visite. En cas de blocage, il se deplace
 * aleatoirement jusqu'a pouvoir reprendre son motif de deplacement.
 */
public class MonstreIAMoyen extends MonstreIA {
	private Direction d;
	private String imageMonstre = "file:./res/monstreIAMoyen.png";

	/**
	 * Constructeur derive de l'objet MonstreIAMoyen prenant en parametre un Plateau
	 * et placant le MonstreIAMoyen sur une case aleatoire
	 * 
	 * @param p = plateau de jeu
	 */
	public MonstreIAMoyen(Plateau p) {
		super(p);
		d = Direction.values()[Monstre.ALEA.nextInt(5)];
	}

	/**
	 * Constructeur de l'objet MonstreIAMoyen prenant en parametre deux entiers
	 * symbolisant la position du MonstreIAMoyen sur le Plateau
	 * 
	 * @param x = ligne de la case du monstreIAMoyen
	 * @param y = colonne de la case du monstreIAMoyen
	 * @param p = plateau de jeu
	 */
	public MonstreIAMoyen(int x, int y, Plateau p) {
		super(x, y, p);
		d = Direction.values()[Monstre.ALEA.nextInt(5)];
	}

	/**
	 * Difficulte moyenne : Deplacements dans une direction sur le plateau jusqu'a
	 * tomber sur un bord du plateau. Il change alors de direction et recommence. Il
	 * ne peut pas se deplacer sur une case qu'il a deja visite. En cas de blocage,
	 * il se deplace aleatoirement jusqu'a pouvoir reprendre son motif de
	 * deplacement.
	 */
	public void setPosition(Plateau p) {
		try {
			Direction[] directions = Direction.values();
			if (p.getPlateau()[super.getX()][super.getY()].isPiege()) {
				super.setPosition(directions[Monstre.ALEA.nextInt(directions.length)], p);
			} else {
				for (int i = 0; i < directions.length; i++) {
					if (!this.verifCaseVisitee(d, p)) {
						super.setPosition(d, p);
						return;
					}
					d = directions[(d.ordinal() + 1) % 5];
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			this.setPosition(p);
		}
	}

	/**
	 * Renvoie vrai si la case situee dans la direction passee en parametre est
	 * visitee, renvoie faux sinon
	 * 
	 * @param d = Direction du deplacement
	 * @param p = Plateau de jeu
	 * @return vrai si la case situee dans la direction passee en parametre est
	 *         visitee, renvoie faux sinon
	 */
	private boolean verifCaseVisitee(Direction d, Plateau p) {
		if (super.verifPosition(d, p)) {
			switch (d) {
			case NORD:
				return p.getCase(super.getX() - 1, super.getY()).isVisite() ? true : false;
			case SUD:
				return p.getCase(super.getX() + 1, super.getY()).isVisite() ? true : false;
			case EST:
				return p.getCase(super.getX(), super.getY() + 1).isVisite() ? true : false;
			case OUEST:
				return p.getCase(super.getX(), super.getY() - 1).isVisite() ? true : false;
			case ICI:
				return true;
			default:
				return false;
			}
		} else {
			return true;
		}
	}

	public String getImageMonstre() {
		return this.imageMonstre;

	}
}
