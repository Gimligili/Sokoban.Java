package logic;


import java.io.IOException;
import java.util.List;

import logic.Direction;


@SuppressWarnings("unused")
public class Controleur {
    private int niveau;
    private String path_to_level;
    public Entrepot entrepot;
    public Gardien gardien;
    
    
    
    public Controleur (String path_to_level) throws IOException {
    	if (path_to_level.contains("levels\\level")) {
    		int pos = path_to_level.length() - 5;
    		this.niveau = Integer.valueOf(path_to_level.substring(pos, pos + 1));
    		if (niveau == 0) {
    			niveau = 10;
    		}
    	}
    	else {    	
    		this.niveau = 99; //code for imported levels
    	}
    	this.path_to_level = path_to_level;
    	this.gardien = new Gardien(0,0);
    	
    	this.entrepot = new Entrepot(path_to_level ,this.niveau, this);
    }
    
    public void action(Direction direction) {
    	List<Integer> position = gardien.getPosition();
    	int l = position.get(0);
    	int c = position.get(1);
    	switch (direction) {
    		case HAUT:
    			if (l != 0) {
    				if (entrepot.getCase((l - 1), c).acceptGardian(direction)) {
    					gardien.set_pos(l-1, c);
    				};
    			};
    			break;
    		case BAS:
    			if (l != entrepot.getNbLignes() - 1) {
    				if (entrepot.getCase((l + 1), c).acceptGardian(direction)) {
    					gardien.set_pos(l+1, c);
    				};
    			};
    			break;
    		case GAUCHE:
    			if (c != 0) {
    				if (entrepot.getCase(l, c - 1).acceptGardian(direction)) {
    					gardien.set_pos(l, c-1);
    				};
    			};
    			break;
    		case DROITE:
    			if (c != entrepot.getNbColonnes() - 1) {
    				if (entrepot.getCase(l, c + 1).acceptGardian(direction)) {
    					gardien.set_pos(l, c+1);
    				};
    			};
    			break;
    	};
    }

    public boolean levelEnd() {
    	return entrepot.checkVictory();
    }

    public int getLevel() {
    	return niveau;
    }
    
    public String getPathToLevel() {
    	return path_to_level;
    }

	public void restart() throws IOException {
		this.gardien = new Gardien(0,0);
    	this.entrepot = new Entrepot(this.path_to_level, this.niveau, this);
	}    

}
