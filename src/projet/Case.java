package projet;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Lucas ANTOINE
 * Cree le 28 mars 2019
 */

/**
 * Classe heritant de position definissant les cases qui composent le plateau
 * avec ses differents attributs : Case visitee (quand le monstre est deja passe
 * par cette case), Case Monstre (quand le monstre se trouve sur cette case),
 * Case Chasseur (quand le chasseur se trouve sur cette case), Case piegee
 * (quand un piege se trouve sur cette case). Cette classe contient une classe
 * interne CaseGraphique permettant l'affichage des Cases en mode graphique
 * (JavaFX).
 */
public class Case extends Position {
	private boolean monstre;
	private boolean chasseur;
	private boolean piege;
	private boolean visite;
	private int cptTours;

	/**
	 * Constructeur creant une case predefinie en (x,y)
	 * 
	 * @param x = ligne de la Case
	 * @param y = colonne de la Case
	 */
	public Case(int x, int y) {
		super(x, y);
		this.monstre = false;
		this.chasseur = false;
		this.piege = false;
		this.visite = false;
		this.cptTours = 1;
	}

	/** Incremente le compteur de tours de la Case */
	public void incrTour() {
		this.cptTours++;
	}

	/**
	 * Renvoie vrai si un monstre est present sur la case, faux sinon
	 * 
	 * @return vrai si un monstre est present, faux sinon
	 */
	public boolean isMonstre() {
		return this.monstre;
	}

	/**
	 * Permet de modifier l'etat de la case concernant la presence ou non d'un
	 * monstre
	 * 
	 * @param monstre = vrai ou faux
	 */
	public void setMonstre(boolean monstre) {
		this.monstre = monstre;
	}

	/**
	 * Renvoie vrai si un Chasseur est present sur la case, faux sinon
	 * 
	 * @return vrai si un Chasseur est present, faux sinon
	 */
	public boolean isChasseur() {
		return this.chasseur;
	}

	/**
	 * Permet de modifier l'etat de la case concernant la presence ou non d'un
	 * chasseur
	 * 
	 * @param chasseur = vrai ou faux
	 */
	public void setChasseur(boolean chasseur) {
		this.chasseur = chasseur;
	}

	/**
	 * Renvoie vrai si un piege est present sur la case, faux sinon
	 * 
	 * @return vrai si un piege est present, faux sinon
	 */
	public boolean isPiege() {
		return this.piege;
	}

	/**
	 * Permet de modifier l'etat de la case concernant la presence ou non d'un piege
	 * 
	 * @param piege = vrai ou faux
	 */
	public void setPiege(boolean piege) {
		this.piege = piege;
	}

	/**
	 * Renvoie vrai si la case a ete visitee par un monstre
	 * 
	 * @return vrai si la case est visitee, faux sinon.
	 */
	public boolean isVisite() {
		return this.visite;
	}

	/** Permet de modifier la case en case visitee */
	public void setVisite() {
		if (!this.visite) {
			this.visite = true;
		}
	}

	/**
	 * Renvoie le compteur de tours de la case
	 * 
	 * @return le compteur de tours de la case.
	 */
	public int getCptTours() {
		return cptTours;
	}

	/**
	 * Renvoie le tour a laquelle la case a ete visitee sinon renvoie -1
	 * 
	 * @return le tour a laquelle la case a ete visitee, -1 si elle n'a pas ete
	 *         visitee.
	 */
	public int getTourVisite() {
		return this.isVisite() ? this.cptTours : -1;
	}

	/**
	 * Renvoie une case sous forme textuelle globale en fonction de ses attributs
	 */
	public String toString() {
		if (this.monstre) {
			return this.toStringMonstre();
		}
		if (this.chasseur) {
			return this.toStringChasseur();
		}
		if (!this.chasseur && !this.monstre && this.piege) {
			return this.toStringPiege();
		}
		if (!this.chasseur && !this.monstre && this.visite) {
			return this.toSringVisite();
		} else {
			return "[   ]";
		}
	}

	/**
	 * Renvoie une case sous forme textuelle pour le monstre en fonction de ses
	 * attributs
	 * 
	 * @return une case visible par le monstre uniquement
	 */
	public String toStringVueMonstre() {
		if (this.monstre) {
			return this.toStringMonstre();
		}
		if (!this.chasseur && !this.monstre && this.visite) {
			return this.toSringVisite();
		} else {
			return "[   ]";
		}
	}

	/**
	 * Renvoie une case sous forme textuelle pour le chasseur en fonction de ses
	 * attributs
	 * 
	 * @return une case visible uniquement par le Chasseur
	 */
	public String toStringVueChasseur() {
		if (this.chasseur) {
			return this.toStringChasseur();
		}
		if (!this.chasseur && this.piege) {
			return this.toStringPiege();
		} else {
			return "[   ]";
		}
	}

	/**
	 * Renvoie une case avec un monstre sous forme textuelle
	 * 
	 * @return une case avec un monstre sous forme textuelle
	 */
	private String toStringMonstre() {
		return "[ M ]";
	}

	/**
	 * Renvoie une case avec un chasseur sous forme textuelle
	 * 
	 * @return une case avec un chasseur sous forme textuelle
	 */
	private String toStringChasseur() {
		return "[ C ]";
	}

	/**
	 * Renvoie une case avec un piege sous forme textuelle
	 * 
	 * @return une case avec un piege sous forme textuelle
	 */
	private String toStringPiege() {
		return "[ X ]";
	}

	/**
	 * Renvoie une case visitee sous forme textuelle
	 * 
	 * @return une case visitee sous forme textuelle
	 */
	private String toSringVisite() {
		return "[|||]";
	}

	/**
	 * Renvoie une case sous forme graphique pour le monstre en fonction de ses
	 * attributs
	 * 
	 * @param m = monstre de la partie
	 * @return une case graphique visible par le monstre uniquement
	 */
	public CaseGraphique toCGVueMonstre(Monstre m) {
		if (this.monstre) {
			return this.toCGMonstre(m);
		}
		if (!this.monstre && this.visite) {
			return this.toCGVisite();
		} else {
			return new CaseGraphique(new Image("file:./res/NonVisitee.jpeg", 70, 70, true, true));
		}
	}

	/**
	 * Renvoie une case sous forme graphique pour le chasseur en fonction de ses
	 * attributs
	 * 
	 * @param c = chasseur de la partie
	 * @return une case graphique visible uniquement par le Chasseur
	 */
	public CaseGraphique toCGVueChasseur(Chasseur c) {
		if (this.chasseur) {
			return this.toCGChasseur(c);
		}
		if (!this.chasseur && this.piege) {
			return this.toCGPiege();
		} else {
			return new CaseGraphique(new Image("file:./res/NonVisitee.jpeg", 70, 70, true, true));
		}
	}

	/**
	 * Renvoie une case avec un monstre sous forme graphique
	 * 
	 * @param m = Monstre permettant de determiner l'image a appliquer sur la Case
	 * 
	 * @return une case avec un monstre sous forme graphique
	 */
	private CaseGraphique toCGMonstre(Monstre m) {
		if (!m.isIA()) {
			return new CaseGraphique(new Image("file:./res/caseJoueurMonstre.png", 70, 70, true, true));
		} else if (m.getImageMonstre().equals("file:./res/monstreIAFacile.png")) {
			return new CaseGraphique(new Image("file:./res/caseMonstreIAFacile.png", 70, 70, true, true));
		} else {
			return new CaseGraphique(new Image("file:./res/caseMonstreIAMoyen.png", 70, 70, true, true));
		}
	}

	/**
	 * Renvoie une case avec un chasseur sous forme graphique
	 * 
	 * @param c = Chasseur permettant de determiner l'image a appliquer sur la Case
	 * 
	 * @return une case avec un chasseur sous forme graphique
	 */
	private CaseGraphique toCGChasseur(Chasseur c) {
		if (!c.isIA()) {
			return new CaseGraphique(new Image("file:./res/caseJoueurChasseur.png", 70, 70, true, true));
		} else if (c.getImageChasseur().equals("file:./res/chasseurIAFacile.png")) {
			return new CaseGraphique(new Image("file:./res/caseChasseurIAFacile.png", 70, 70, true, true));
		} else {
			return new CaseGraphique(new Image("file:./res/caseChasseurIAMoyen.png", 70, 70, true, true));
		}
	}

	/**
	 * Renvoie une case avec un piege sous forme graphique
	 * 
	 * @return une case avec un piege sous forme graphique
	 */
	private CaseGraphique toCGPiege() {
		return new CaseGraphique(new Image("file:./res/Piege.jpeg", 70, 70, true, true));
	}

	/**
	 * Renvoie une case visitee sous forme graphique
	 * 
	 * @return une case visitee sous forme graphique
	 */
	private CaseGraphique toCGVisite() {
		return new CaseGraphique(new Image("file:./res/Visitee.jpeg", 70, 70, true, true));
	}

	/**
	 * @author Lucas ANTOINE
	 */
	/**
	 * Classe interne heritant de StackPane permettant de construire une Case sous
	 * forme graphique (JavaFX) a l'aide d'une Image inseree dans un Rectangle.
	 * L'affichage depend des attributs appliques a la Case.
	 *
	 */
	public class CaseGraphique extends StackPane {
		private ImageView ig;

		/**
		 * Constructeur d'une CaseGraphique prenant en parametre une Image, inseree dans
		 * un Rectangle de taille (70, 70).
		 * 
		 * @param value = Image appliquee a la CaseGraphique
		 */
		public CaseGraphique(Image value) {
			Rectangle border = new Rectangle(70, 70);
			border.setFill(null);
			border.setStroke(Color.BLACK);
			ig = new ImageView(value);
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, ig);

		}
	}
}