package projet;

/**
 * @author Lucas ANTOINE
 * Cree le 24 mars 2019
 */

/**
 * Enumeration permettant de definir la direction du deplacement du monstre en
 * fonction des quatres points cardinaux, mais aussi d'une 5eme valeur
 * permettant au monstre de ne pas se deplacer.
 */
public enum Direction {
	NORD("N"), SUD("S"), EST("E"), OUEST("O"), ICI("I");

	private String direction;

	/**
	 * Constructeur d'une Direction prenant en parametre la premiere lettre de son
	 * 
	 * @param direction = nom de direction
	 * 
	 */
	private Direction(String direction) {
		this.direction = direction;
	}

	/**
	 * Renvoie la direction vers laquelle est tournee le monstre
	 * 
	 * @return String direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Renvoie une direction en fonction de son attribut
	 * 
	 * @param initiale = attribut d'une des directions
	 * @return la direction correspondant a l'initiale prise en parametre
	 */
	public static Direction get(String initiale) {
		for (Direction d : Direction.values()) {
			if (d.getDirection().equals(initiale)) {
				return d;
			}
		}
		return null;
	}
}
