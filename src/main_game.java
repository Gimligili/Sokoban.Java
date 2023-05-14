import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.SwingUtilities;
import ihm.FenetreAccueil;


public class main_game implements Runnable  {
	
	    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new main_game());

    }



	@Override
	public void run() {
		try {
			new FenetreAccueil();
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
