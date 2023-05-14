package logic;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d20b6a1e-c4b8-428a-83db-6146d5e7312f")
public class Case {
    @objid ("adf4810a-928b-4e18-8b5d-b2ddc81fb0c1")
    private boolean Target;
    @objid ("f4583045-d3dd-4446-9779-67046a4f7e5f")
    private int ligne;
    @objid ("a8be238e-12ce-4f2c-8ba6-c73b5351158a")
    private int colonne;    
    @objid ("e82dc778-2c1b-48ad-88b9-9958d98718ef")
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

    

    @objid ("aecf3306-9088-4b93-b650-01ad9d058ea7")
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

    @objid ("1fc4c97c-730b-45a3-a303-9786b3ba25ee")
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
