package logic;

import java.util.ArrayList;
import java.util.List;


public class Gardien {
	private int ligne;
	private int colonne;

	public Gardien(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	public void set_pos(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	public List<Integer> getPosition() {
		List<Integer> position = new ArrayList<>();
		position.add(ligne);
		position.add(colonne);
		return position;
	}
}
	

