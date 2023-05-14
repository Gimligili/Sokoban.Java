package ihm;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.ContenuCase;
import logic.Controleur;

@SuppressWarnings("serial")
public class Editor extends JFrame implements MouseListener, MouseMotionListener {

	static final int TAILLE_IMAGE = 32;
	static int LARGEUR_FENETRE = 0;
	static int HAUTEUR_FENETRE = 0;
	static Controleur controleur;
	static ContenuCase content = ContenuCase.ARRIERE_PLAN;

	public Editor (int nbLignes, int nbColonnes, String name) throws IOException  {  
		
		FileWriter levelWriter = new FileWriter(new File(new File(".").getCanonicalPath() + "/levels/" + name + ".txt"));
		BufferedWriter lowerWriter = new BufferedWriter(levelWriter);
		for (int i = 0; i < nbLignes; i++ ) {
			lowerWriter.write("_".repeat(nbColonnes));
			lowerWriter.newLine();
		}
		lowerWriter.close();
		levelWriter.close();
		
		controleur = new Controleur(new File(new File(".").getCanonicalPath() + "/levels/" + name + ".txt").getPath());
		LARGEUR_FENETRE = controleur.entrepot.getNbColonnes() * TAILLE_IMAGE;
        HAUTEUR_FENETRE = controleur.entrepot.getNbLignes() * TAILLE_IMAGE;
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle("Sokoban v1.0 par Gabriel FARAGO");
        this.setPreferredSize(new Dimension(LARGEUR_FENETRE + 150, Math.max(HAUTEUR_FENETRE + 150, 330)));
        this.setResizable(false);
        
        Icon gardien_icon = new ImageIcon("img/Joueur.jpg");
        Icon background_icon = new ImageIcon("img/Background.jpg");
        Icon caisse_icon = new ImageIcon("img/Caisse.jpg");
        Icon caisse_rangee_icon = new ImageIcon("img/CaisseRangee.jpg");
        Icon gardien_rangement_icon = new ImageIcon("img/JoueurRangement.jpg");
        Icon mur_icon = new ImageIcon("img/Mur.jpg");
        Icon rangement_icon = new ImageIcon("img/Rangement.jpg");
        Icon vide_icon = new ImageIcon("img/Vide.jpg");
	 	JButton gardien = new JButton(gardien_icon);
	 	JButton background = new JButton(background_icon);
	 	JButton caisse = new JButton(caisse_icon);
	 	JButton caisse_rangee = new JButton(caisse_rangee_icon);
	 	JButton gardien_rangement = new JButton(gardien_rangement_icon);
	 	JButton mur = new JButton(mur_icon);
	 	JButton rangement = new JButton(rangement_icon);
	 	JButton vide = new JButton(vide_icon);
	 	JLabel invalid_level = new JLabel("", SwingConstants.CENTER);
	 	invalid_level.setForeground(Color.RED);
	 	invalid_level.setBounds(LARGEUR_FENETRE, 145, 150, 20);
	 	JButton validate = new JButton("Terminer");
	 	validate.setBounds(LARGEUR_FENETRE + 20, 170 , 110, 30);
	 	JButton back = new JButton("Retour");
	 	back.setBounds(LARGEUR_FENETRE + 20, 210 , 110, 30);
	 	JButton quit = new JButton("Quitter");
	 	quit.setBounds(LARGEUR_FENETRE + 20, 250 , 110, 30);
	 	gardien.setOpaque(false);
	 	gardien.setContentAreaFilled(false);
	 	gardien.setBounds(LARGEUR_FENETRE + 30, 0, 32, 32);
	 	background.setOpaque(false);
	 	background.setContentAreaFilled(false);
	 	background.setBounds(LARGEUR_FENETRE + 30, 37, 32, 32);
	 	caisse.setOpaque(false);
	 	caisse.setContentAreaFilled(false);
	 	caisse.setBounds(LARGEUR_FENETRE + 30, 74, 32, 32);
	 	caisse_rangee.setOpaque(false);
	 	caisse_rangee.setContentAreaFilled(false);
	 	caisse_rangee.setBounds(LARGEUR_FENETRE + 30, 111, 32, 32);
	 	gardien_rangement.setOpaque(false);
	 	gardien_rangement.setContentAreaFilled(false);
	 	gardien_rangement.setBounds(LARGEUR_FENETRE + 75, 0, 32, 32);
	 	mur.setOpaque(false);
	 	mur.setContentAreaFilled(false);
	 	mur.setBounds(LARGEUR_FENETRE + 75, 37, 32, 32);
	 	rangement.setOpaque(false);
	 	rangement.setContentAreaFilled(false);
	 	rangement.setBounds(LARGEUR_FENETRE + 75, 74, 32, 32);
	 	vide.setOpaque(false);
	 	vide.setContentAreaFilled(false);
	 	vide.setBounds(LARGEUR_FENETRE + 75, 111, 32, 32);
	 	
	 	
	 	quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File level_drop = new File(new File(".").getCanonicalPath() + "/levels/" + name + ".txt");
					level_drop.delete();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 				
				System.exit( 0 );
			}
	    });
	 	back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					File level_drop = new File(new File(".").getCanonicalPath() + "/levels/" + name + ".txt");
					level_drop.delete();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					new FenetreAccueil();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	    });
	 	validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int gardien = 0;
				int nb_caisse = 0;
				int nb_target = 0;
				for (int i = 0; i < nbLignes * nbColonnes; i++) {
					int c = i / nbColonnes;
					int l = i % nbColonnes;
					ContenuCase end_content = controleur.entrepot.getCase(c, l).getContent();
					if (end_content == ContenuCase.JOUEUR || end_content == ContenuCase.JOUEUR_RANGEMENT) {
						gardien ++;
					}
					if (end_content == ContenuCase.CAISSE || end_content == ContenuCase.CAISSE_RANGEE) {
						nb_caisse ++;
					}
					if (end_content == ContenuCase.JOUEUR_RANGEMENT || end_content == ContenuCase.RANGEMENT || end_content == ContenuCase.CAISSE_RANGEE) {
						nb_target ++;
					}
					
				}
				if (gardien == 1 && nb_target >= nb_caisse && nb_caisse > 0) {
					String ligne = "";
					for (int i = 0; i < nbLignes * nbColonnes; i++) {
						
						int c = i / nbColonnes;
						int l = i % nbColonnes;
						ContenuCase end_content = controleur.entrepot.getCase(c, l).getContent();
						switch (end_content) {
							case JOUEUR:
								ligne += "G";
								break;
							case JOUEUR_RANGEMENT:
								ligne += "B";
								break;
							case CAISSE:
								ligne += "C";
								break;
							case CAISSE_RANGEE:
								ligne += "V";
								break;
							case CASE_VIDE:
								ligne += "#";
								break;
							case RANGEMENT:
								ligne += "T";
								break;
							case ARRIERE_PLAN:
								ligne += "_";
								break;
							case MUR:
								ligne += "M";
								break;
						}
						
						if (ligne.length() == nbColonnes && i+1 == nbColonnes) {
							try {
								FileWriter levelWriter = new FileWriter(new File(new File(".").getCanonicalPath() + "/levels/" + name + ".txt"));
								BufferedWriter lowerWriter = new BufferedWriter(levelWriter);
								lowerWriter.write(ligne);
								lowerWriter.close();
								levelWriter.close();
								ligne = "";
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else if (ligne.length() == nbColonnes) {
							try {
								FileWriter levelWriter = new FileWriter(new File(new File(".").getCanonicalPath() + "/levels/" + name + ".txt"), true);
								BufferedWriter lowerWriter = new BufferedWriter(levelWriter);
								lowerWriter.newLine();
								lowerWriter.write(ligne);
								lowerWriter.close();
								levelWriter.close();
								ligne = "";
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					dispose();
					try {
						new FenetreAccueil();
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					invalid_level.setText("Niveau invalide !");
				}
			}
	    });
	 	
	 	gardien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.JOUEUR;				
			}
	    });
	 	background.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.ARRIERE_PLAN;
			}
	    });
	 	caisse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.CAISSE;				
			}
	    });
	 	caisse_rangee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.CAISSE_RANGEE;
			}
	    });
	 	gardien_rangement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.JOUEUR_RANGEMENT;
			}
	    });
	 	mur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.MUR;
			}
	    });
	 	rangement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.RANGEMENT;
			}
	    });
	 	vide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				content = ContenuCase.CASE_VIDE;
			}
	    });
	 	
        
	 	this.add(gardien);
	 	this.add(background);
	 	this.add(caisse);
	 	this.add(caisse_rangee);
	 	this.add(gardien_rangement);
	 	this.add(mur);
	 	this.add(rangement);
	 	this.add(vide);
	 	this.add(validate);
	 	this.add(quit);
	 	this.add(back);
	 	this.add(invalid_level);
        this.add( new PaneauSokoban( controleur ));
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.pack();
        this.setVisible( true );
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getX() < LARGEUR_FENETRE && e.getY() < HAUTEUR_FENETRE) {
			
			
			//controleur.entrepot.getCase(c-1, l).setContent(content);
			//repaint();
		}
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() < LARGEUR_FENETRE + 31 && e.getY() < HAUTEUR_FENETRE + 31) {
			int l = Math.max((e.getX() - 10) / 32, 0);
			int c = Math.max((e.getY() - 32) / 32, 0);
			controleur.entrepot.getCase(c, l).setContent(content);
			repaint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}


