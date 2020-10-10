package gui;

import java.util.Locale;

import javax.swing.UIManager;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class ApplicationLauncher {

	public static void main(String[] args) {
		ConfigXML c = ConfigXML.getInstance();
		LoginGUI b = new LoginGUI();
		b.setVisible(true);

		try {
			BLFacade appFacadeInterface;
			System.out.println(c.getLocale());
			Locale.setDefault(new Locale(c.getLocale()));
			System.out.println("Locale: " + Locale.getDefault());
			// if (c.isBusinessLogicLocal()) {
			DataAccess da = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			appFacadeInterface = new BLFacadeImplementation(da);
			// }
			LoginGUI.setBusinessLogic(appFacadeInterface);
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
	}
}
