package gui;

import javax.swing.UIManager;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;

public class ApplicationLauncher {

	private static BLFacadeImplementation businessLogic;

	public static void main(String[] args) {
		ConfigXML c = ConfigXML.getInstance();
		LoginGUI b = new LoginGUI(c);
		b.setVisible(true);

		try {
			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
	}
}
