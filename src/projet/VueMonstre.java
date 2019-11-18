package projet;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * @author Lucas ANTOINE
 * CrÃ©Ã© le 28 avr. 2019
 */

/**
 * Classe d'affichage du Plateau de jeu avec les informations necessaires au
 * monstre et en cachant les informations sur le chasseur
 */
public class VueMonstre {
	private final String NL = System.getProperty("line.separator");

	/**
	 * Renvoie un Plateau sous forme textuelle pour le Monstre
	 * 
	 * @param p = plateau de jeu
	 * @return un plateau sous forme textuel uniquement pour le monstre
	 */
	public String toStringVueMonstre(Plateau p) {
		String res = "";
		for (Case[] c1 : p.getPlateau()) {
			for (Case c2 : c1) {
				res += c2.toStringVueMonstre();
			}
			res += NL;
		}
		return res;
	}

	/**
	 * Renvoie un Plateau sous forme graphique (JavaFX) pour le Monstre
	 * 
	 * @param p = plateau de jeu
	 * @param m = Monstre de la partie
	 * @return Parent representant le plateau de jeu sous forme graphique
	 */
	public static Parent createGPlateau(Plateau p, Monstre m) {
		Pane root = new Pane();
		List<Case.CaseGraphique> list = new ArrayList<Case.CaseGraphique>();
		for (int i = 0; i < p.getCote(); i++) {
			for (int j = 0; j < p.getCote(); j++) {
				list.add(p.getCase(i, j).toCGVueMonstre(m));
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Case.CaseGraphique c = list.get(i);
			c.setTranslateX(70 * (i % p.getCote()));
			c.setTranslateY(70 * (i / p.getCote()));
			root.getChildren().add(c);
		}

		return root;
	}
}