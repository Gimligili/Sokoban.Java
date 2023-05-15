package logic;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entrepot {
    private int nbLignes;

    private int nbColonnes;
    

    private List<Case> case_tableau = new ArrayList<Case> ();
    
    public Controleur controleur;
    
    
    public Entrepot(String path_to_level, int level, Controleur controleur) throws IOException {
    	this.controleur = controleur;
    	    	
    	Path file = Paths.get(path_to_level);
		List<String> lines = Collections.emptyList();
		lines = Files.readAllLines(file, StandardCharsets.UTF_8);
		
		this.nbLignes = lines.size();
		this.nbColonnes = lines.get(0).length();
		for (int i=0; i<this.nbLignes; i++) {
			for(int j=0; j<this.nbColonnes; j++) {
				
				switch (Character.toString(lines.get(i).charAt(j))) {
					case "_":
						// Case arrière-plan
						case_tableau.add(new Case(i, j, ContenuCase.ARRIERE_PLAN, this));
						break;
					case "M":
						// Case mu
						case_tableau.add(new Case(i, j, ContenuCase.MUR, this));
						break;
					case "#":
						// Case vide
						case_tableau.add(new Case(i, j, ContenuCase.CASE_VIDE, this));
						break;
					case "T":
						// Case cible
						case_tableau.add(new Case(i, j, ContenuCase.RANGEMENT, this));
						break;
					case "G":
						// Case joueur
						case_tableau.add(new Case(i, j, ContenuCase.JOUEUR, this));
						controleur.gardien.set_pos(i, j);
						break;
					case "C":
						// Case Caisse
						case_tableau.add(new Case(i, j, ContenuCase.CAISSE, this));
						break;
					case "B":
						// Case joueur sur une cible
						case_tableau.add(new Case(i, j, ContenuCase.JOUEUR_RANGEMENT, this));
						controleur.gardien.set_pos(i, j);
						break;
					case "V":
						// Case caisse déjà validée
						case_tableau.add(new Case(i, j, ContenuCase.CAISSE_RANGEE, this));
						break;
				}
				
			}
		}	
	}
    
    
    public Case getCase(int l, int c) {
    	return case_tableau.get(l*this.nbColonnes + c);
    }
    

    public boolean checkVictory() {
    	for (int i=0; i < case_tableau.size(); i++) {
    		if (case_tableau.get(i).getContent() == ContenuCase.CAISSE) {
    			return false;
    		}
    	};
    	return true;
    }
    
    public int getNbLignes() {
    	return nbLignes;
    }
    
    public int getNbColonnes() {
    	return nbColonnes;
    }
    
}
