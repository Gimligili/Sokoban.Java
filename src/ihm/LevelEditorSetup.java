package ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class LevelEditorSetup {
	private static int nbLignes;
	private static int nbColonnes;
	private static String name;
	private static JButton validate ;
	
	
	
	public LevelEditorSetup () throws FontFormatException, IOException {
		JFrame set_parameters= new JFrame("Sokoban v1.0 par Gabriel FARAGO"); 
		set_parameters.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		set_parameters.setResizable(false);
	 
	 	Font font_title = Font.createFont(Font.TRUETYPE_FONT, new File("font/Lostar.ttf"));
	 	font_title = font_title.deriveFont(Font.BOLD, 35);
	 	
	 	JLabel titre = new JLabel("Éditeur de niveau", SwingConstants.CENTER);
	 	titre.setFont(font_title);
	 	titre.setBounds(0, 0, 400, 50);
	 	
	    validate = new JButton("Valider");  
	    JButton back = new JButton("Retour");
	    JButton quit = new JButton("Quitter");
	    
	    
	    JLabel name_display = new JLabel("Nom du niveau :", SwingConstants.CENTER);
	    name_display.setBounds(50, 90, 150, 30);
	    JTextField nameInput = new JTextField(15);
	    nameInput.setBounds(220 , 90, 150, 30);
	    
	    
	    JLabel ligne_display = new JLabel("Nombre de lignes :", SwingConstants.CENTER);
	    ligne_display.setBounds(50, 130, 200, 30);
	    JLabel colonne_display = new JLabel("Nombre de colonnes :", SwingConstants.CENTER);
	    colonne_display.setBounds(50, 160, 200, 30);
	    JTextField nbLignesInput = new JTextField(5);
	    nbLignesInput.setBounds(250 , 130, 50, 30);
	    JTextField nbColonnesInput = new JTextField(5);
	    nbColonnesInput.setBounds(250, 160, 50, 30);
	    
	    JLabel input_error = new JLabel("", SwingConstants.CENTER);
	    input_error.setForeground(Color.RED);
	    input_error.setBounds(100, 175, 200, 50);
	    
	    
	    validate.setBounds(75,225,250,50);  
	    back.setBounds(25,300,150,50);
	    quit.setBounds(225, 300, 150, 50);
	    
	    quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit( 0 );
			}
	    });
	    
	    validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean inLigneCorrect = true;
				Boolean inColonneCorrect = true;
				Boolean correctName = true;
				if (nameInput.getText().contains("level") || nameInput.getText().contains("/") || nameInput.getText().contains("\\")) {
					correctName = false;
				}
				for (int i = 0; i < nbLignesInput.getText().length(); i++) {
					inLigneCorrect = (inLigneCorrect && Character.isDigit(nbLignesInput.getText().charAt(i)));
				}
				for (int i = 0; i < nbColonnesInput.getText().length(); i++) {
					inColonneCorrect = (inColonneCorrect && Character.isDigit(nbColonnesInput.getText().charAt(i)));
				}
				
				if (inLigneCorrect && inColonneCorrect && correctName && nbLignesInput.getText().length() > 0 && nbColonnesInput.getText().length() > 0 && nameInput.getText().length() > 0) {
					
					File level = null;
					try {
						level =  new File(new File(".").getCanonicalPath() + "/levels/" + nameInput.getText() + ".txt");						
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						if (level.createNewFile()) {
							nbLignes = Integer.parseInt(nbLignesInput.getText());
							nbColonnes = Integer.parseInt(nbColonnesInput.getText());
							name = nameInput.getText();
							set_parameters.dispose();
							new Editor(nbLignes, nbColonnes, name);
						} 
						else {
							input_error.setText("Ce nom est déjà utilisé !");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if (!correctName) {
					input_error.setText("Nom incorrect !");
				}
				else {
					
					input_error.setText("Veuillez entrer des entiers !");
				}
			}
	    });
	    back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set_parameters.dispose();
				try {
					new FenetreAccueil();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });
	    
	    
	    set_parameters.add(validate);  
	    set_parameters.add(back);
	    set_parameters.add(quit);
	    set_parameters.add(titre);
	    set_parameters.add(nbLignesInput);
	    set_parameters.add(nbColonnesInput);
	    set_parameters.add(ligne_display);
	    set_parameters.add(colonne_display);
	    set_parameters.add(input_error);
	    set_parameters.add(name_display);
	    set_parameters.add(nameInput);
	    set_parameters.setSize(400,400);  
	    set_parameters.setLayout(null);  
	    set_parameters.setVisible(true);  
	}
	}
	
	