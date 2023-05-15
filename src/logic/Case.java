package logic;



public class Case {
    private int ligne;
    private int colonne;
    private ContenuCase content;
    public Entrepot entrepot;
    
    public Case (int ligne, int colonne, ContenuCase content, Entrepot entrepot) {
    	this.ligne = ligne;
    	this.colonne = colonne;
    	this.content = content;
    	this.entrepot = entrepot;
    }
    
    public ContenuCase getContent() {
    	return this.content;
    }
    
    public void setContent (ContenuCase content) {
    	this.content = content;
    }
    
    public void setContentVoisine(Direction direction, ContenuCase content) {
    	switch (direction) {
		case HAUT:
			entrepot.getCase(ligne - 1, colonne).setContent(content);
			break;
		case BAS:
			entrepot.getCase(ligne + 1, colonne).setContent(content);
			break;
		case GAUCHE:
			entrepot.getCase(ligne, colonne - 1).setContent(content);
			break;
		case DROITE:
			entrepot.getCase(ligne, colonne + 1).setContent(content);
			break;
	}
    }

    

    public ContenuCase getContentVoisine(Direction direction) {
    	switch (direction) {
			case HAUT:
				return entrepot.getCase(ligne - 1, colonne).getContent();
			case BAS:
				return entrepot.getCase(ligne + 1, colonne).getContent();
			case GAUCHE:
				return entrepot.getCase(ligne, colonne - 1).getContent();
			case DROITE:
				return entrepot.getCase(ligne, colonne + 1).getContent();
    	}
		return content;
    }

    public boolean acceptGardian(Direction direction) {
    	switch (this.content) {
    		case MUR: 
    			return false;
    		case ARRIERE_PLAN:
    			return false;
    		case CAISSE:
    			switch(direction) {
    				case HAUT:
    					if (this.ligne == 0) {
    						return false;
    					}
    					break;
    				case BAS:
    					if (this.ligne == entrepot.getNbLignes() - 1) {
    						return false;
    					}
    					break;
    				case GAUCHE:
    					if (this.colonne == 0) {
    						return false;
    					}
    					break;
    				case DROITE:
    					if (this.colonne == entrepot.getNbColonnes() - 1) {
    						return false;
    					}
    					break;
    				
    			}
    			switch(this.getContentVoisine(direction)) {
    				case CAISSE:
    					return false;
    				case CAISSE_RANGEE:
    					return false;
    				case MUR:
    					return false;
    				case ARRIERE_PLAN:
    					return false;
    				case RANGEMENT:
    					this.setContent(ContenuCase.JOUEUR);
    					this.setContentVoisine(direction, ContenuCase.CAISSE_RANGEE);
    					break;
    				case CASE_VIDE:
    					this.setContent(ContenuCase.JOUEUR);
    					this.setContentVoisine(direction, ContenuCase.CAISSE);
    					break;
    				default:
    					break;
    			}
    			break;
    		case CAISSE_RANGEE:
    			switch(direction) {
				case HAUT:
					if (this.ligne == 0) {
						return false;
					}
					break;
				case BAS:
					if (this.ligne == entrepot.getNbLignes() - 1) {
						return false;
					}
					break;
				case GAUCHE:
					if (this.colonne == 0) {
						return false;
					}
					break;
				case DROITE:
					if (this.colonne == entrepot.getNbColonnes() - 1) {
						return false;
					}
					break;
				
    			}
    			switch(this.getContentVoisine(direction)) {
					case CAISSE:
						return false;
					case MUR:
						return false;
					case CAISSE_RANGEE:
						return false;
					case RANGEMENT:
						this.setContent(ContenuCase.JOUEUR_RANGEMENT);
    					this.setContentVoisine(direction, ContenuCase.CAISSE_RANGEE);
    					break;
					case CASE_VIDE:
						this.setContent(ContenuCase.JOUEUR_RANGEMENT);
    					this.setContentVoisine(direction, ContenuCase.CAISSE);
    					break;
					default:
						break;
    			}
    			break;
    		case CASE_VIDE:
    			this.setContent(ContenuCase.JOUEUR);
    			break;
    		case RANGEMENT:
    			this.setContent(ContenuCase.JOUEUR_RANGEMENT);
    			break;
    		default:
    			break;
    	}
    	Direction previous_player = Direction.DirectionInverse(direction);
    	switch(this.getContentVoisine(previous_player)) {
    		case JOUEUR:
    			this.setContentVoisine(previous_player, ContenuCase.CASE_VIDE);
    			return true;
    		case JOUEUR_RANGEMENT:
    			this.setContentVoisine(previous_player, ContenuCase.RANGEMENT);
    			return true;
    		default:
    			break;
    	}
    	return false;
	
    }




}
