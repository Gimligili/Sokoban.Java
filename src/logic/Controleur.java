package logic;


import java.io.IOException;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import logic.Direction;


@SuppressWarnings("unused")
@objid ("026d9907-84c1-4c9c-8850-2911838b590b")
public class Controleur {
    @objid ("8298b685-a7e7-4654-9e00-24c7628f423c")
    private int niveau;
    private String path_to_level;
    @objid ("c0ab4450-f4be-42c8-985f-401216e2fcb2")
    public Entrepot entrepot;
    @objid ("675a2ff5-2cca-460f-a4d7-3c7077216561")
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
    
    
    @objid ("dd628d4a-62fd-470e-928b-230512649b45")
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

    @objid ("8a77d5f2-125b-4aa3-8d40-db266d21bb8a")
    public boolean levelEnd() {
    	return entrepot.checkVictory();
    }

    @objid ("ce43c50f-704a-4ba9-8ce6-f24c2463f075")
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
