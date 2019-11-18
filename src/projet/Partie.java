package projet;

import java.awt.Window;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Lucas ANTOINE Cree le 16 avr. 2019
 */

/**
 * Classe d'affichage permettant de lancer le jeu et lancer la Chasse aux
 * monstre
 */
public class Partie extends Application {
	private final static Random ALEA = new Random();
	private static int cptM = 0;
	protected static int cptC = 0;
	private static Plateau plateau;
	protected static Stage stage;
	protected static Stage modale;

	/**
	 * Methode permettant l'affichage du menu et la navigation parmi les differentes
	 * composantes du jeu
	 */
	private static void menu() {
		Label label = new Label("Chasse au monstre");
		Button jouer = new Button("Jouer");
		Button regles = new Button("Regles du jeu");
		Button quitter = new Button("Quitter");
		Image menu = new Image("file:./res/menu.png");
		BackgroundImage backgroundMenu = new BackgroundImage(menu, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));

		label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		label.setStyle("-fx-alignment: center;" + "-fx-font: 40px Verdana;" + "-fx-font-weight: bold;");
		label.setMinHeight(80);
		jouer.setMaxSize(800, Double.MAX_VALUE);
		jouer.setMinHeight(75);
		jouer.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
		regles.setMaxSize(700, Double.MAX_VALUE);
		regles.setStyle("-fx-font: 20px Verdana;");
		regles.setMinHeight(50);
		quitter.setMaxSize(700, Double.MAX_VALUE);
		quitter.setMinHeight(50);
		quitter.setStyle("-fx-font: 20px Verdana;");

		VBox vbox = new VBox(4, label, jouer, regles, quitter);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setBackground(new Background(backgroundMenu));
		vbox.setPrefSize(1500, 1500);

		jouer.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			choixPartie();
		});

		regles.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			regles();
		});

		quitter.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			System.exit(0);
		});

		stage.setScene(new Scene(vbox, 1500, 800));
	}

	/** Methode permettant d'afficher les regles de la Chasse aux monstre */
	private static void regles() {
		Button retour = new Button("Retour");
		retour.setMinSize(300, 40);
		retour.setStyle("-fx-font: 20px Verdana;");
		Image regles = new Image("file:./res/regles.jpg");
		BackgroundImage backgroundRegles = new BackgroundImage(regles, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));
		Label titre = new Label("Regles du jeu");
		titre.setTextFill(Color.WHITE);
		titre.setStyle("-fx-font: 30px Verdana;" + "-fx-font-weight: bold;");

		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			modale.close();
		});

		Label label = new Label("But du jeu :\r\n" + "\r\n"
				+ "	Il s'agit d'un jeu de plateau a deux joueurs. Chaque joueur joue un role predefini ayant chacun ses specificites. \r\n"
				+ "Il y a un Chasseur et Monstre.\r\n"
				+ "Il n'y a pas de limite de temps, la partie se termine lorsque le Monstre a visite toutes les cases du plateau ou lorsque le Chasseur a neutralise\r\n"
				+ "le monstre. Vous pouvez jouer a deux joueurs, seul contre une IA ou meme regarder deux IA s'affronter.\r\n"
				+ "\r\n" + "Les roles :\r\n" + "\r\n" + "1-Le Monstre : \r\n"
				+ "	Son but est de visiter l'integralite des cases du plateau. A chaque tour, il soit se deplacer vers l'un des quatres points cardinaux\r\n"
				+ " ou alors rester sur la meme case. Il ne sait pas ou se trouve le Chasseur et peut \r\n"
				+ "toujours revenir sur ses pas et revisiter une case ayant deja ete decouverte.\r\n"
				+ " Il peut se deplacer via les boutons situes sur l'ecran, ou grace aux fleches directionnelles et la touche <i> (ICI) du clavier.\r\n"
				+ "\r\n" + "2-Le Chasseur : \r\n"
				+ "	Son role est de trouver le Monstre avant que ce dernier n'ait eu le temps de visiter toutes les cases du plateau. \r\n"
				+ "Pour neutraliser le monstre il doit se deplacer sur la meme case que ce dernier. Chaque tour le chasseur a deux choix : \r\n"
				+ "Poser un piege ou se deplacer. Il peut se deplacer sur n'importe quelle case du plateau et poser un piege sur la case ou il se trouve. Le piege se \r\n"
				+ "declenche si le monstre marche dessus et lui fera faire un deplacement aleatoire.\r\n"
				+ " Lorsque que le chasseur se deplace sur une case deja visite par le monstre, il saura depuis combien de tour le monstre est passe par la. \r\n"
				+ "Le chasseur peut se deplacer ou poser un piege grace aux zones de texte / boutons situes sur l'ecran, ou peut se deplacer en cliquant sur la case \r\n"
				+ "voulue sur le plateau.\r\n" + "\r\n" + "Les types de parties :\r\n" + "\r\n"
				+ "1-Partie par defaut :\r\n"
				+ "	Partie avec un plateau (5x5), des positions aleatoires et 3 pieges pour le chasseur.\r\n" + "\r\n"
				+ "2-Partie avec plateau personnalise :\r\n"
				+ "	Partie sur un plateau de taille choisie au prealable, des positions aleatoires et 3 pieges pour le chasseur.\r\n"
				+ "\r\n" + "3-Partie personnalisee :\r\n"
				+ "	Partie sur un plateau de taille choisie au prealable, des positions et un nombre de pieges choisis.");
		label.setTextFill(Color.WHITE);
		VBox vbox = new VBox(titre, label, retour);
		vbox.setSpacing(35);
		vbox.setPadding(new Insets(25, 0, 0, 25));
		label.setStyle("-fx-font: 20px Verdana;");
		vbox.setBackground(new Background(backgroundRegles));

		modale.setScene(new Scene(vbox, 1700, 1200));
		modale.showAndWait();
	}

	/**
	 * Methode qui permet de lancer une partie de Chasse aux monstre
	 * 
	 * @param m = monstre de la partie
	 * @param c = chasseur de la partie
	 * @param p = plateau de jeu de la partie
	 */
	private static void jouer(Monstre m, Chasseur c, Plateau p) {
		modale.setTitle("Chasse au monstre");
		plateau.setMonstre(m.getX(), m.getY(), true);
		plateau.setChasseur(c.getX(), c.getY(), true);
		cptM = 1;
		cptC = 1;
		if (monstreCommence()) {
			ecran(m, c, 0);
		} else {
			ecran(m, c, 1);
		}
	}

	/**
	 * Methode d'affichage en cas de victoire d'un Joueur
	 * 
	 * @param m = monstre de la partie
	 * @param c = chasseur de la partie
	 * @param i = entier pour changer l'orthographe du message en fonction du numero
	 *          du tour
	 */
	private static void ecranVictoire(Monstre m, Chasseur c, int i) {
		Button ok = new Button("Ok");
		ok.setMinSize(600, 60);
		ok.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
		Label label;
		ImageView iv;
		Image victoire = new Image("file:./res/victoire.gif");
		BackgroundImage backgroundVictoire = new BackgroundImage(victoire, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));

		if (i == 0) {
			if (cptM == 1) {
				label = new Label("Le monstre a gagné en " + cptM + " tour !");
			} else {
				label = new Label("Le monstre a gagné en " + cptM + " tours !");
			}
			iv = new ImageView(new Image(m.getImageMonstre(), 400, 400, true, true));
		} else {
			if (cptC == 1) {
				label = new Label("Le chasseur a gagné en " + cptC + " tour !");
			} else {
				label = new Label("Le chasseur a gagné en " + cptM + " tours !");
			}
			iv = new ImageView(new Image(c.getImageChasseur(), 400, 400, true, true));
		}
		label.setStyle("-fx-font: 30px Verdana;" + "-fx-font-weight: bold;");
		label.setTextFill(Color.WHITE);
		VBox vbox = new VBox(label, iv, ok);
		vbox.setSpacing(25);
		vbox.setAlignment(Pos.CENTER);
		vbox.setBackground(new Background(backgroundVictoire));
		Scene scene = new Scene(vbox, 1600, 800);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				modale.close();
			}
		});

		ok.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			modale.close();
		});

		modale.setScene(scene);
	}

	/**
	 * Methode determinant qui commence entre le chasseur et le monstre
	 * 
	 * @return vrai si le monstre commence, faux sinon.
	 */
	private static boolean monstreCommence() {
		return ALEA.nextInt(2) == 1;
	}

	/**
	 * Methode simulant le tour du monstre
	 * 
	 * @param m = monstre de la partie
	 * @param c = chasseur de la partie
	 */
	private static void tourMonstre(Monstre m, Chasseur c) {
		cptM++;
		plateau.incr();
		if (!m.isIA()) {
			Label piege = new Label("");
			piege.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
			piege.setTextFill(Color.WHITE);
			Label numTour = new Label("Tour " + cptC);
			numTour.setStyle("-fx-font: 30px Verdana;" + "-fx-font-weight: bold;");
			numTour.setTextFill(Color.WHITE);
			Image tour = new Image("file:./res/jeu.gif");
			BackgroundImage backgroundTour = new BackgroundImage(tour, null, null, null,
					new BackgroundSize(800, 800, true, true, true, true));
			ImageView monstre = new ImageView(new Image(m.getImageMonstre(), 400, 400, true, true));
			Label error = new Label("");
			error.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
			error.setTextFill(Color.RED);
			Button haut = new Button("↑");
			haut.setPrefSize(40, 75);
			haut.setStyle("-fx-font: 20px Verdana;");
			haut.setAlignment(Pos.CENTER);
			Button bas = new Button("↓");
			bas.setPrefSize(40, 75);
			bas.setStyle("-fx-font: 20px Verdana;");
			bas.setAlignment(Pos.CENTER);
			Button gauche = new Button("⟵");
			gauche.setPrefSize(75, 40);
			gauche.setStyle("-fx-font: 20px Verdana;");
			Button droite = new Button("⟶");
			droite.setPrefSize(75, 40);
			droite.setStyle("-fx-font: 20px Verdana;");
			Button ici = new Button("•");
			ici.setPrefSize(40, 40);
			ici.setStyle("-fx-font: 20px Verdana;");
			Button continuer = new Button("Continuer");
			continuer.setDisable(true);
			continuer.setOpacity(0);
			HBox controlH = new HBox(gauche, ici, droite);
			controlH.setAlignment(Pos.CENTER);
			controlH.setSpacing(10);
			VBox control = new VBox(haut, controlH, bas);
			control.setAlignment(Pos.CENTER);
			control.setSpacing(10);
			VBox vbox = new VBox(numTour, monstre, error, control, piege);
			VBox v = new VBox(VueMonstre.createGPlateau(plateau, m));
			v.setAlignment(Pos.TOP_LEFT);
			v.setPadding(new Insets(50, 0, 0, 0));
			HBox hbox = new HBox(v, vbox);
			vbox.setAlignment(Pos.TOP_CENTER);
			hbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(40);
			hbox.setSpacing(705);
			hbox.setBackground(new Background(backgroundTour));
			HBox.setMargin(vbox, new Insets(10, 10, 10, 10));
			Scene scene = new Scene(hbox, 1600, 900);

			if (plateau.getCase(m.getX(), m.getY()).isPiege()) {
				m.setPositionPiege(plateau);
				piege.setText("Vous avez ete piege par le chasseur !\n <Appuyez sur Entree pour continuer>");
				control.setDisable(true);
			}

			scene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.UP) {
					if (m.verifPosition(Direction.NORD, plateau)) {
						m.setPosition(Direction.NORD, plateau);
						if (m.victoire()) {
							ecranVictoire(m, c, 0);
						} else {
							ecran(m, c, 1);
						}
					}
				}
				if (e.getCode() == KeyCode.DOWN) {
					if (m.verifPosition(Direction.SUD, plateau)) {
						m.setPosition(Direction.SUD, plateau);
						if (m.victoire()) {
							ecranVictoire(m, c, 0);
						} else {
							ecran(m, c, 1);
						}
					}
				}
				if (e.getCode() == KeyCode.LEFT) {
					if (m.verifPosition(Direction.OUEST, plateau)) {
						m.setPosition(Direction.OUEST, plateau);
						if (m.victoire()) {
							ecranVictoire(m, c, 0);
						} else {
							ecran(m, c, 1);
						}
					}
				}
				if (e.getCode() == KeyCode.RIGHT) {
					if (m.verifPosition(Direction.EST, plateau)) {
						m.setPosition(Direction.EST, plateau);
						if (m.victoire()) {
							ecranVictoire(m, c, 0);
						} else {
							ecran(m, c, 1);
						}
					}
				}
				if (e.getCode() == KeyCode.I) {
					if (m.verifPosition(Direction.ICI, plateau)) {
						m.setPosition(Direction.ICI, plateau);
						if (m.victoire()) {
							ecranVictoire(m, c, 0);
						} else {
							ecran(m, c, 1);
						}
					}
				}
				if (e.getCode() == KeyCode.ENTER) {
					if (control.isDisabled()) {
						if (m.victoire()) {
							ecranVictoire(m, c, 0);
						} else {
							ecran(m, c, 1);
						}
					}
				} else {
					error.setText("Deplacement impossible !");
				}
			});

			haut.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (m.verifPosition(Direction.NORD, plateau)) {
					m.setPosition(Direction.NORD, plateau);
					if (m.victoire()) {
						ecranVictoire(m, c, 0);
					} else {
						ecran(m, c, 1);
					}
				} else {
					error.setText("Deplacement impossible !");
				}
			});

			bas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (m.verifPosition(Direction.SUD, plateau)) {
					m.setPosition(Direction.SUD, plateau);
					if (m.victoire()) {
						ecranVictoire(m, c, 0);
					} else {
						ecran(m, c, 1);
					}
				} else {
					error.setText("Deplacement impossible !");
				}
			});

			gauche.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (m.verifPosition(Direction.OUEST, plateau)) {
					m.setPosition(Direction.OUEST, plateau);
					if (m.victoire()) {
						ecranVictoire(m, c, 0);
					} else {
						ecran(m, c, 1);
					}
				} else {
					error.setText("Deplacement impossible !");
				}
			});

			droite.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (m.verifPosition(Direction.EST, plateau)) {
					m.setPosition(Direction.EST, plateau);
					if (m.victoire()) {
						ecranVictoire(m, c, 0);
					} else {
						ecran(m, c, 1);
					}
				} else {
					error.setText("Deplacement impossible !");
				}
			});

			ici.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				m.setPosition(Direction.ICI, plateau);
				if (m.victoire()) {
					ecranVictoire(m, c, 0);
				} else {
					ecran(m, c, 1);
				}
			});

			modale.setScene(scene);
		} else if (c.isIA() && m.isIA()) {
			partieIA(m, c, 0);
		} else {
			((MonstreIA) m).setPosition(plateau);
			ecran(m, c, 1);
		}
	}

	/**
	 * Methode simulant le tour du chasseur
	 * 
	 * @param c = chasseur de la partie
	 * @param m = monstre de la partie
	 */
	private static void tourChasseur(Chasseur c, Monstre m) {
		cptC++;
		if (!c.isIA()) {
			Label numTour = new Label("Tour " + cptC);
			numTour.setStyle("-fx-font: 30px Verdana;" + "-fx-font-weight: bold;");
			numTour.setTextFill(Color.WHITE);
			Image tour = new Image("file:./res/jeu.gif");
			BackgroundImage backgroundTour = new BackgroundImage(tour, null, null, null,
					new BackgroundSize(800, 800, true, true, true, true));
			Label error = new Label("");
			error.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
			ImageView chasseur = new ImageView(new Image(c.getImageChasseur(), 300, 300, true, true));
			error.setTextFill(Color.RED);
			Label visite = new Label("");
			if (plateau.getCase(c.getX(), c.getY()).isVisite()) {
				visite.setText("Le monstre est passe par la il y a "
						+ (cptC - plateau.getCase(c.getX(), c.getY()).getCptTours()) + " tours !");
			}
			visite.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
			visite.setTextFill(Color.WHITE);
			Label nbPieges = new Label("Nombre de pieges restants : " + c.getPieges());
			nbPieges.setStyle("-fx-font: 20px Verdana;");
			nbPieges.setTextFill(Color.WHITE);
			TextField tfx = new TextField();
			TextField tfy = new TextField();
			Label ligne = new Label("Numero de ligne (entre 0 et " + (plateau.getCote() - 1) + ") :");
			Label colonne = new Label("Numero de colonne (entre 0 et " + (plateau.getCote() - 1) + ") :");
			ligne.setStyle("-fx-font: 20px Verdana;");
			ligne.setTextFill(Color.WHITE);
			colonne.setStyle("-fx-font: 20px Verdana;");
			colonne.setTextFill(Color.WHITE);
			tfx.setPromptText("Numero de ligne");
			tfy.setPromptText("Numero de colonne");
			Button piege = new Button("Poser un piege");
			piege.setMinSize(400, 40);
			piege.setStyle("-fx-font: 20px Verdana;");
			Button aleatoire = new Button("", new ImageView(new Image("file:./res/de.png", 30, 30, true, true)));
			aleatoire.setMinSize(400, 40);
			Button ok = new Button("OK");
			ok.setMinSize(600, 60);
			ok.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
			Parent p = VueChasseur.createGPlateau(plateau, c);
			VBox vbox = new VBox(numTour, chasseur, nbPieges, piege, ligne, tfx, colonne, tfy, aleatoire, ok, visite,
					error);
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(15);
			VBox vbox2 = new VBox(p);
			vbox2.setPadding(new Insets(50, 0, 0, 0));
			vbox2.setAlignment(Pos.TOP_CENTER);
			HBox hbox = new HBox(vbox2, vbox);
			vbox.setPrefWidth(500);
			hbox.setBackground(new Background(backgroundTour));
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(700);
			Scene scene = new Scene(hbox, 1600, 900);
			tfx.setText(c.getX() + "");
			tfy.setText(c.getY() + "");

			if (c.getPieges() == 0) {
				piege.setDisable(true);
			}

			scene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					try {
						c.setChasseur(plateau, Integer.parseInt(tfx.getText()), Integer.parseInt(tfy.getText()));
						if (c.victoire(m)) {
							ecranVictoire(m, c, 1);
						} else {
							ecran(m, c, 0);
						}
					} catch (NumberFormatException j) {
						error.setText("Seuls les nombres entiers sont acceptes !");
						tfx.setText(c.getX() + "");
						tfy.setText(c.getY() + "");
					} catch (ArrayIndexOutOfBoundsException j) {
						error.setText("Seuls les nombres entre 0 et " + (plateau.getCote() - 1) + " sont acceptes !");
						tfx.setText(c.getX() + "");
						tfy.setText(c.getY() + "");
					}
				}
				if (e.getCode() == KeyCode.P) {
					if (!plateau.getCase(c.getX(), c.getY()).isPiege()) {
						c.setPiege(plateau);
						ecran(m, c, 0);
					} else {
						error.setText("Il y a deja un piege sur cette case !");
					}
				}
			});

			p.setOnMouseClicked(e -> {
				try {
					c.setChasseur(plateau, ((int) (e.getY() / 70)), ((int) (e.getX() / 70)));
					if (c.victoire(m)) {
						ecranVictoire(m, c, 1);
					} else {
						ecran(m, c, 0);
					}
				} catch (NumberFormatException j) {
					error.setText("Seuls les nombres entiers sont acceptes !");
					tfx.setText(c.getX() + "");
					tfy.setText(c.getY() + "");
				} catch (ArrayIndexOutOfBoundsException j) {
					error.setText("Seuls les nombres entre 0 et " + (plateau.getCote() - 1) + " sont acceptes !");
					tfx.setText(c.getX() + "");
					tfy.setText(c.getY() + "");
				}
			});

			piege.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (!plateau.getCase(c.getX(), c.getY()).isPiege()) {
					c.setPiege(plateau);
					ecran(m, c, 0);
				} else {
					error.setText("Il y a deja un piege sur cette case !");
				}
			});

			aleatoire.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				tfx.setText("" + ALEA.nextInt(plateau.getCote()));
				tfy.setText("" + ALEA.nextInt(plateau.getCote()));
			});

			ok.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				try {
					c.setChasseur(plateau, Integer.parseInt(tfx.getText()), Integer.parseInt(tfy.getText()));
					if (c.victoire(m)) {
						ecranVictoire(m, c, 1);
					} else {
						ecran(m, c, 0);
					}
				} catch (NumberFormatException j) {
					error.setText("Seuls les nombres entiers sont acceptes !");
				} catch (ArrayIndexOutOfBoundsException j) {
					error.setText("Seuls les nombres entre 0 et " + (plateau.getCote() - 1) + " sont acceptes !");
				}
			});

			modale.setScene(scene);
		} else if (c.isIA() && m.isIA()) {
			partieIA(m, c, 1);
		} else {
			((ChasseurIA) c).setPosition(plateau);
			ecran(m, c, 0);
		}
	}

	/**
	 * Methode permettant de cacher le plateau et le changement de Joueur entre le
	 * Chasseur et le Monstre
	 * 
	 * @param m = monstre de la partie
	 * @param c = chasseur de la partie
	 * @param i = entier pour determiner quel joueur doit jouer ensuite
	 */
	private static void ecran(Monstre m, Chasseur c, int i) {
		Label label;
		ImageView imageM = new ImageView(new Image(m.getImageMonstre(), 400, 400, true, true));
		ImageView imageC = new ImageView(new Image(c.getImageChasseur(), 400, 400, true, true));
		if (i == 0) {
			label = new Label("C'est au tour du monstre de jouer !");
			imageC.setOpacity(0.5);
		} else {
			label = new Label("C'est au tour du chasseur de jouer !");
			imageM.setOpacity(0.5);
		}
		label.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;" + "-fx-background-color: black;");
		label.setTextFill(Color.GHOSTWHITE);
		Button ok = new Button("Ok");
		ok.setMinSize(200, 50);
		ok.setStyle("-fx-font: 15px Verdana;");
		VBox vbox = new VBox(label, ok);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		Image ecran = new Image("file:./res/ecranAttente.jpg");
		BackgroundImage backgroundEcran = new BackgroundImage(ecran, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));
		HBox hbox = new HBox(imageM, vbox, imageC);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(50);
		hbox.setBackground(new Background(backgroundEcran));
		Scene scene = new Scene(hbox, 1400, 800);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (i == 0) {
					tourMonstre(m, c);
				} else {
					tourChasseur(c, m);
				}
			}
		});

		ok.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (i == 0) {
				tourMonstre(m, c);
			} else {
				tourChasseur(c, m);
			}
		});

		modale.setScene(scene);
	}

	/** Methode permettant aux joueurs de choisir leur type partie */
	private static void choixPartie() {
		modale.setTitle("Choix Partie");

		Button partieD = new Button("Partie par defaut");
		partieD.setStyle("-fx-font: 20px Verdana;");
		Button partiePP = new Button("Partie avec plateau personnalise");
		partiePP.setStyle("-fx-font: 20px Verdana;");
		Button partieP = new Button("Partie personnalisee");
		partieP.setStyle("-fx-font: 20px Verdana;");
		Button retour = new Button("Retour");
		retour.setStyle("-fx-font: 20px Verdana;");

		Image choixPartie = new Image("file:./res/modale.png");
		BackgroundImage backgroundChoix = new BackgroundImage(choixPartie, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));

		partieD.setMaxSize(600, Double.MAX_VALUE);
		partieD.setMinHeight(50);
		partiePP.setMaxSize(600, Double.MAX_VALUE);
		partiePP.setMinHeight(50);
		partieP.setMaxSize(600, Double.MAX_VALUE);
		partieP.setMinHeight(50);
		retour.setMaxSize(500, Double.MAX_VALUE);
		retour.setMinHeight(40);

		partieD.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			partieDefaut();
		});

		partiePP.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			partiePlateauPerso(1);
		});

		partieP.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			partiePlateauPerso(2);
		});

		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			modale.close();
			menu();
		});

		VBox vbox = new VBox(4, partieD, partiePP, partieP, retour);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setBackground(new Background(backgroundChoix));

		modale.setScene(new Scene(vbox, 1000, 400));
		modale.showAndWait();
	}

	/** Methode lancant une partie par defaut */
	private static void partieDefaut() {
		plateau = new Plateau();
		choixJoueurs(plateau, 0);
	}

	/**
	 * Methode permettant de specifier la taile du plateau de jeu
	 * 
	 * @param i = entier permettant de differencier une partie perso d'une partie
	 *          plateau perso
	 */
	private static void partiePlateauPerso(int i) {
		modale.setTitle("Partie avec plateau personnalise");
		Label taille = new Label("Taille du plateau :");
		taille.setStyle("-fx-font: 20px Verdana;");
		taille.setTextFill(Color.WHITE);
		TextField tf = new TextField();
		Button ok = new Button("OK");
		ok.setMinSize(150, 40);
		ok.setStyle("-fx-font: 20px Verdana;");
		Button retour = new Button("Retour");
		retour.setMinSize(150, 40);
		retour.setStyle("-fx-font: 20px Verdana;");
		Image choixPartie = new Image("file:./res/modale.png");
		BackgroundImage backgroundChoix = new BackgroundImage(choixPartie, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));

		ok.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			try {
				plateau = new Plateau(Integer.parseInt(tf.getText()));
				choixJoueurs(plateau, i);
			} catch (NumberFormatException f) {
				tf.setStyle("-fx-background-color: red;");
				tf.setText("");
			}
		});

		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			modale.close();
			menu();
		});

		HBox hbox = new HBox(2, taille, tf);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		HBox hbox2 = new HBox(retour, ok);
		hbox2.setAlignment(Pos.BOTTOM_CENTER);
		hbox2.setSpacing(500);
		VBox vbox = new VBox(2, hbox, hbox2);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(100);
		vbox.setBackground(new Background(backgroundChoix));
		Scene scene = new Scene(vbox, 1000, 400);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				try {
					plateau = new Plateau(Integer.parseInt(tf.getText()));
					choixJoueurs(plateau, i);
				} catch (NumberFormatException f) {
					tf.setStyle("-fx-background-color: red;");
					tf.setText("");
				}
			}
		});
		modale.setScene(scene);
	}

	/**
	 * Methode d'affichage d'une partie entre 2 IA
	 * 
	 * @param m = Monstre IA de la partie
	 * @param c = Chasseur IA de la partie
	 * @param i = entier permettant de savoir a quel IA est-ce le tour
	 */
	private static void partieIA(Monstre m, Chasseur c, int i) {
		Label numTour;
		VBox v;
		Button ok = new Button("OK");
		ok.setMinSize(600, 60);
		ok.setStyle("-fx-font: 20px Verdana;" + "-fx-font-weight: bold;");
		Image tour = new Image("file:./res/jeu.gif");
		BackgroundImage backgroundTour = new BackgroundImage(tour, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));

		ok.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (i == 0) {
				if (m.victoire()) {
					ecranVictoire(m, c, 0);
				} else {
					ecran(m, c, 1);
				}
			} else {
				if (c.victoire(m)) {
					ecranVictoire(m, c, 1);
				} else {
					ecran(m, c, 0);
				}
			}
		});
		if (i == 0) {
			if (plateau.getCase(m.getX(), m.getY()).isPiege()) {
				m.setPositionPiege(plateau);
			} else {
				((MonstreIA) m).setPosition(plateau);
			}
			numTour = new Label("Tour " + cptM);
			v = new VBox(VueMonstre.createGPlateau(plateau, m));
		} else {
			((ChasseurIA) c).setPosition(plateau);
			numTour = new Label("Tour " + cptC);
			v = new VBox(VueChasseur.createGPlateau(plateau, c));
		}
		VBox vbox = new VBox(numTour, ok);
		v.setPadding(new Insets(150, 0, 0, 0));
		v.setAlignment(Pos.TOP_CENTER);
		numTour.setStyle("-fx-font: 30px Verdana;" + "-fx-font-weight: bold;");
		numTour.setTextFill(Color.WHITE);
		HBox hbox = new HBox(v, vbox);
		hbox.setBackground(new Background(backgroundTour));
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(700);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(300);
		Scene scene = new Scene(hbox, 1600, 900);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (i == 0) {
					if (m.victoire()) {
						ecranVictoire(m, c, 0);
					} else {
						ecran(m, c, 1);
					}
				} else {
					if (c.victoire(m)) {
						ecranVictoire(m, c, 1);
					} else {
						ecran(m, c, 0);
					}
				}
			}
		});

		modale.setScene(scene);
	}

	/**
	 * Methode permettant de choisir ses specifites de partie, entre une partie avec
	 * ou sans IAs, ou meme une partie ou l'on choisit notre case de depart
	 * 
	 * @param p = plateau de jeu
	 * @param i = entier permettant de differencier une partie perso d'une partie
	 *          plateau perso
	 */
	private static void choixJoueurs(Plateau p, int i) {
		modale.setX(Window.CENTER_ALIGNMENT);
		modale.setY(Window.CENTER_ALIGNMENT);
		Button retour = new Button("Retour");
		retour.setMinSize(250, 60);
		retour.setStyle("-fx-font: 20px Verdana;");
		Button ok = new Button("Jouer");
		ok.setMinSize(250, 60);
		ok.setStyle("-fx-font: 20px Verdana; -fx-font-weight: bold;");
		Label m1 = new Label("Veuillez definir la position du Monstre :");
		m1.setTextFill(Color.WHITE);
		m1.setStyle("-fx-font: 20px Verdana; -fx-font-weight: bold;");
		Label m2 = new Label("Ligne (entre 0 et " + (p.getCote() - 1) + ") :");
		m2.setTextFill(Color.WHITE);
		m2.setStyle("-fx-font: 20px Verdana;");
		Label m3 = new Label("Colonne (entre 0 et " + (p.getCote() - 1) + ") :");
		m3.setTextFill(Color.WHITE);
		m3.setStyle("-fx-font: 20px Verdana;");
		Label c1 = new Label("Veuillez definir la position du Chasseur :");
		c1.setTextFill(Color.WHITE);
		c1.setStyle("-fx-font: 20px Verdana; -fx-font-weight: bold;");
		Label c2 = new Label("Ligne (entre 0 et " + (p.getCote() - 1) + ") :");
		c2.setTextFill(Color.WHITE);
		c2.setStyle("-fx-font: 20px Verdana;");
		Label c3 = new Label("Colonne (entre 0 et " + (p.getCote() - 1) + ") :");
		c3.setTextFill(Color.WHITE);
		c3.setStyle("-fx-font: 20px Verdana;");
		Label c4 = new Label("Nombre de pieges du Chasseur :");
		c4.setTextFill(Color.WHITE);
		c4.setStyle("-fx-font: 20px Verdana;");
		TextField tfc1 = new TextField();
		TextField tfc2 = new TextField();
		TextField tfc3 = new TextField();
		TextField tfm1 = new TextField();
		TextField tfm2 = new TextField();
		HBox hboxV = new HBox(retour, ok);
		hboxV.setAlignment(Pos.CENTER);
		hboxV.setSpacing(800);
		HBox hboxC1 = new HBox(c2, tfc1);
		hboxC1.setAlignment(Pos.CENTER);
		HBox hboxC2 = new HBox(c3, tfc2);
		hboxC2.setAlignment(Pos.CENTER);
		HBox hboxC3 = new HBox(c4, tfc3);
		hboxC3.setAlignment(Pos.CENTER);
		hboxC1.setSpacing(15);
		hboxC2.setSpacing(15);
		hboxC3.setSpacing(15);
		HBox hboxM1 = new HBox(m2, tfm1);
		hboxM1.setAlignment(Pos.CENTER);
		hboxM1.setSpacing(15);
		HBox hboxM2 = new HBox(m3, tfm2);
		hboxM2.setAlignment(Pos.CENTER);
		hboxM2.setSpacing(15);
		ComboBox<String> monstre = new ComboBox<String>();
		monstre.setMinSize(400, 40);
		ComboBox<String> chasseur = new ComboBox<String>();
		chasseur.setMinSize(400, 40);
		ImageView iMonstre = new ImageView(new Image("file:./res/JoueurMonstre.png", 400, 400, true, true));
		ImageView iChasseur = new ImageView(new Image("file:./res/JoueurChasseur.png", 400, 400, true, true));
		Image choixPartie = new Image("file:./res/choixJoueur.jpg");
		BackgroundImage backgroundChoix = new BackgroundImage(choixPartie, null, null, null,
				new BackgroundSize(800, 800, true, true, true, true));
		monstre.getItems().add("Joueur Monstre");
		monstre.getItems().add("IA Monstre Moyenne");
		monstre.getItems().add("IA Monstre Facile");
		chasseur.getItems().add("Joueur Chasseur");
		chasseur.getItems().add("IA Chasseur Moyenne");
		chasseur.getItems().add("IA Chasseur Facile");
		monstre.getSelectionModel().select(0);
		chasseur.getSelectionModel().select(0);

		monstre.setOnAction(e -> {
			if (monstre.getSelectionModel().getSelectedIndex() == 0) {
				iMonstre.setImage(new Image("file:./res/JoueurMonstre.png", 400, 400, true, true));
			} else if (monstre.getSelectionModel().getSelectedIndex() == 1) {
				iMonstre.setImage(new Image("file:./res/monstreIAMoyen.png", 400, 400, true, true));
			} else if (monstre.getSelectionModel().getSelectedIndex() == 2) {
				iMonstre.setImage(new Image("file:./res/monstreIAFacile.png", 400, 400, true, true));
			}
		});

		chasseur.setOnAction(e -> {
			if (chasseur.getSelectionModel().getSelectedIndex() == 0) {
				iChasseur.setImage(new Image("file:./res/JoueurChasseur.png", 400, 400, true, true));
			} else if (chasseur.getSelectionModel().getSelectedIndex() == 1) {
				iChasseur.setImage(new Image("file:./res/chasseurIAMoyen.png", 400, 400, true, true));
			} else if (chasseur.getSelectionModel().getSelectedIndex() == 2) {
				iChasseur.setImage(new Image("file:./res/chasseurIAFacile.png", 400, 400, true, true));
			}
		});

		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (i == 0) {
				modale.close();
			} else {
				partiePlateauPerso(i);
			}
		});

		ok.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			try {
				if (i != 2) {
					initialiserPartiePlateauPerso(new int[] { monstre.getSelectionModel().getSelectedIndex(),
							chasseur.getSelectionModel().getSelectedIndex() });
				} else {
					initialiserPartiePerso(new int[] { monstre.getSelectionModel().getSelectedIndex(),
							Integer.parseInt(tfm1.getText()), Integer.parseInt(tfm2.getText()),
							chasseur.getSelectionModel().getSelectedIndex(), Integer.parseInt(tfc1.getText()),
							Integer.parseInt(tfc2.getText()), Integer.parseInt(tfc3.getText()) });
				}
			} catch (NumberFormatException j) {
				tfc1.setStyle("-fx-background-color: red;");
				tfc2.setStyle("-fx-background-color: red;");
				tfc3.setStyle("-fx-background-color: red;");
				tfm1.setStyle("-fx-background-color: red;");
				tfm2.setStyle("-fx-background-color: red;");
			} catch (ArrayIndexOutOfBoundsException j) {
				tfc1.setStyle("-fx-background-color: red;");
				tfc2.setStyle("-fx-background-color: red;");
				tfc3.setStyle("-fx-background-color: red;");
				tfm1.setStyle("-fx-background-color: red;");
				tfm2.setStyle("-fx-background-color: red;");
			}
		});

		VBox vMonstre = new VBox(monstre, iMonstre, m1, hboxM1, hboxM2);
		vMonstre.setAlignment(Pos.TOP_CENTER);
		vMonstre.setSpacing(50);
		VBox vChasseur = new VBox(chasseur, iChasseur, c1, hboxC1, hboxC2, hboxC3);
		vChasseur.setAlignment(Pos.TOP_CENTER);
		vChasseur.setSpacing(50);
		HBox hbox = new HBox(vMonstre, vChasseur);
		hbox.setSpacing(550);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(hbox, hboxV);
		vbox.setBackground(new Background(backgroundChoix));
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 20, 20, 20));
		vbox.setSpacing(60);

		if (i != 2) {
			m1.setDisable(true);
			m2.setDisable(true);
			m3.setDisable(true);
			tfm1.setDisable(true);
			tfm2.setDisable(true);
			c1.setDisable(true);
			c2.setDisable(true);
			c3.setDisable(true);
			c4.setDisable(true);
			tfc1.setDisable(true);
			tfc2.setDisable(true);
			tfc3.setDisable(true);
		}

		Scene scene = new Scene(vbox, 1600, 1000);

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				try {
					if (i != 2) {
						initialiserPartiePlateauPerso(new int[] { monstre.getSelectionModel().getSelectedIndex(),
								chasseur.getSelectionModel().getSelectedIndex() });
					} else {
						initialiserPartiePerso(new int[] { monstre.getSelectionModel().getSelectedIndex(),
								Integer.parseInt(tfm1.getText()), Integer.parseInt(tfm2.getText()),
								chasseur.getSelectionModel().getSelectedIndex(), Integer.parseInt(tfc1.getText()),
								Integer.parseInt(tfc2.getText()), Integer.parseInt(tfc3.getText()) });
					}
				} catch (NumberFormatException j) {
					tfc1.setStyle("-fx-background-color: red;");
					tfc2.setStyle("-fx-background-color: red;");
					tfc3.setStyle("-fx-background-color: red;");
					tfm1.setStyle("-fx-background-color: red;");
					tfm2.setStyle("-fx-background-color: red;");
				} catch (ArrayIndexOutOfBoundsException j) {
					tfc1.setStyle("-fx-background-color: red;");
					tfc2.setStyle("-fx-background-color: red;");
					tfc3.setStyle("-fx-background-color: red;");
					tfm1.setStyle("-fx-background-color: red;");
					tfm2.setStyle("-fx-background-color: red;");
				}
			}
		});

		modale.setScene(scene);
	}

	/**
	 * Methode permettant de lancer une partie avec un plateau personnalise en
	 * fonction des specificites des joueurs
	 * 
	 * @param val = tableau d'entier modelisant les specificites des joueurs
	 */
	private static void initialiserPartiePlateauPerso(int[] val) {
		Monstre m = null;
		Chasseur c = null;
		switch (val[0]) {
		case (0):
			m = new Monstre(plateau);
			break;
		case (1):
			m = new MonstreIAMoyen(plateau);
			break;
		case (2):
			m = new MonstreIAFacile(plateau);
			break;
		default:
			break;
		}
		switch (val[1]) {
		case (0):
			c = new Chasseur(plateau);
			break;
		case (1):
			c = new ChasseurIAMoyen(plateau);
			break;
		case (2):
			c = new ChasseurIAFacile(plateau);
			break;
		default:
			break;
		}
		jouer(m, c, plateau);
	}

	/**
	 * Methode permettant de lancer une partie personnalisee en fonction des
	 * specificites des joueurs
	 * 
	 * @param val = tableau d'entier modelisant les specificites des joueurs
	 */
	private static void initialiserPartiePerso(int[] val) {
		Monstre m = null;
		Chasseur c = null;
		switch (val[0]) {
		case (0):
			m = new Monstre(val[1], val[2], plateau);
			break;
		case (1):
			m = new MonstreIAMoyen(val[1], val[2], plateau);
			break;
		case (2):
			m = new MonstreIAFacile(val[1], val[2], plateau);
			break;
		default:
			break;
		}
		switch (val[3]) {
		case (0):
			c = new Chasseur(val[4], val[5], val[6]);
			break;
		case (1):
			c = new ChasseurIAMoyen(val[4], val[5], val[6]);
			break;
		case (2):
			c = new ChasseurIAFacile(val[4], val[5], val[6]);
			break;
		default:
			break;
		}
		jouer(m, c, plateau);
	}

	/**
	 * Methode permettant de lancer l'Application.
	 * 
	 * @param args = null ici
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Partie.stage = stage;
		stage.setTitle("Chasse au monstre");
		menu();
		stage.setResizable(false);
		modale = new Stage();
		modale.initOwner(stage);
		modale.initModality(Modality.WINDOW_MODAL);
		modale.setAlwaysOnTop(true);
		stage.show();
	}
}