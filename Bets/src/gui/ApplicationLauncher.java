package gui;

import java.util.Locale;

import javax.swing.UIManager;

import businessLogic.BLFacade;
import configuration.ConfigXML;

public class ApplicationLauncher {

	public static void main(String[] args) {
		ConfigXML c = ConfigXML.getInstance();
		System.out.println(c.getLocale());
		Locale.setDefault(new Locale(c.getLocale()));
		System.out.println("Locale: " + Locale.getDefault());
		LoginGUI b = new LoginGUI(false);
		b.setVisible(true);

		try {
			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
	}
}
