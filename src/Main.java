import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.SwingUtilities;
import ihm.FenetreAccueil;


public class Main implements Runnable  {
	
	    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Main());

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
