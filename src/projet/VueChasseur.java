package projet;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * @author Lucas ANTOINE
 * Créé le 28 avr. 2019
 */

/**
 * Classe d'affichage du Plateau de jeu avec les informations necessaires au
 * chasseur et en cachant les informations sur le monstre
 */
public class VueChasseur {
	private final String NL = System.getProperty("line.separator");

	/**
	 * Renvoie un Plateau sous forme textuelle pour le Chasseur
	 * 
	 * @param p = plateau de jeu
	 * @return un plateau sous forme textuel uniquement pour le chasseur
	 */
	public String toStringVueChasseur(Plateau p) {
		String res = "";
		for (Case[] c1 : p.getPlateau()) {
			for (Case c2 : c1) {
				res += c2.toStringVueChasseur();
			}
			res += NL;
		}
		return res;
	}

	/**
	 * Renvoie un Plateau sous forme graphique (JavaFX) pour le Chasseur
	 * 
	 * @param p = plateau de jeu
	 * @param c = chasseur de la partie
	 * @return Parent representant le plateau de jeu sous forme graphique
	 */
	public static Parent createGPlateau(Plateau p, Chasseur c) {
		Pane root = new Pane();

		List<Case.CaseGraphique> list = new ArrayList<Case.CaseGraphique>();
		for (int i = 0; i < p.getCote(); i++) {
			for (int j = 0; j < p.getCote(); j++) {
				list.add(p.getCase(i, j).toCGVueChasseur(c));
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Case.CaseGraphique cg = list.get(i);
			cg.setTranslateX(70 * (i % p.getCote()));
			cg.setTranslateY(70 * (i / p.getCote()));
			root.getChildren().add(cg);
		}

		return root;
	}
}