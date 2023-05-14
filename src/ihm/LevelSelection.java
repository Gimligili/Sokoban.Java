package ihm;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import logic.Controleur;

public class LevelSelection {
	private static File path_selected;
	private static JButton browse;
	@SuppressWarnings("rawtypes")
	private static JComboBox levelList;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LevelSelection() throws FontFormatException, IOException{
		
		path_selected = new File(new File(".").getCanonicalPath() + "/levels/level1.txt");
		
		
		JFrame levelSelection= new JFrame("Sokoban v1.0 par Gabriel FARAGO"); 
		levelSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 	Font font_title = Font.createFont(Font.TRUETYPE_FONT, new File("font/Lostar.ttf"));
	 	font_title = font_title.deriveFont(Font.BOLD, 40);
	 	
	 	JLabel titre = new JLabel("Choix du niveau :", SwingConstants.CENTER);
	 	titre.setFont(font_title);
	 	titre.setBounds(0, 20, 400, 50);
	 	
	 	String[] existingLevels = {"Niveau 1", "Niveau 2" , "Niveau 3", "Niveau 4", "Niveau 5", "Niveau 6", "Niveau 7", "Niveau 8", "Niveau 9", "Niveau 10"};	 	
	 	
		levelList = new JComboBox(existingLevels);
	 	levelList.setSelectedIndex(0);
	 	levelList.setBounds(50, 100, 100, 50);
	 	
	 	Icon browse_icon = new ImageIcon("img/browser.png");
	 	browse = new JButton(browse_icon);
	 	browse.setOpaque(false);
	 	browse.setContentAreaFilled(false);
	 	browse.setBounds(250, 100, 100, 50);
	 	
	 	JLabel path = new JLabel("level1.txt", SwingConstants.CENTER);
	 	path.setBounds(50, 150, 300, 50);
	 	
	 	
	 	JButton validate = new JButton("Valider");  
	    JButton back = new JButton("Retour");
	    JButton quit = new JButton("Quitter");
	    
	    validate.setBounds(75,225,250,50);  
	    back.setBounds(25,300,150,50);
	    quit.setBounds(225, 300, 150, 50);
	    
	    
	    quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit( 0 );
			}
	    });
	    browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileBrowser = new JFileChooser();
				File f = null;
				try {
					f = new File(new File(".").getCanonicalPath() + "/levels");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    fileBrowser.setCurrentDirectory(f);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				fileBrowser.setFileFilter(filter);
			    int returnVal = fileBrowser.showOpenDialog(null);			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       String browser_result = fileBrowser.getSelectedFile().getName();
			       path.setText(browser_result);
			       path_selected = fileBrowser.getSelectedFile();
			    }
				
				
			}
	    });
	    
	    levelList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				path.setText("level" + Integer.toString(levelList.getSelectedIndex() + 1) + ".txt");
				try {
					path_selected = new File(new File(".").getCanonicalPath() + "/levels/level" +  Integer.toString(levelList.getSelectedIndex() + 1) + ".txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });
	    
	    validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				levelSelection.dispose();
				try {
					new FenetreSokoban(new Controleur(path_selected.getPath()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });
	    back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				levelSelection.dispose();
				try {
					new FenetreAccueil();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });
	  
	    
	    levelSelection.add(validate);  
	    levelSelection.add(back);
	    levelSelection.add(quit);
	    levelSelection.add(titre);
	    levelSelection.add(levelList);
	    levelSelection.add(browse);
	    levelSelection.add(path);
	    levelSelection.setSize(400,400);  
	    levelSelection.setLayout(null);  
	    levelSelection.setVisible(true); 
	    
	}

}
