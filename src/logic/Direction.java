package logic;

public enum Direction {
    HAUT,
    BAS,
    GAUCHE,
    DROITE;
	
	public static Direction DirectionInverse(Direction direction){
		switch(direction) {
			case HAUT:
				return Direction.BAS;
			case BAS:
				return Direction.HAUT;
			case GAUCHE:
				return Direction.DROITE;
			case DROITE:
				return Direction.GAUCHE;			
		}
		return direction;
	}
}


