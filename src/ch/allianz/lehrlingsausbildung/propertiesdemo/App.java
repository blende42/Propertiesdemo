package ch.allianz.lehrlingsausbildung.propertiesdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class App {

	public static void main(String[] args) {
		App app = new App();
		try {
			app.start(args);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void start(String[] args) throws FileNotFoundException, IOException {
		// could pass in name of file(s) in arguments to main (args[])
		// and use hard-coded names as fallback only...
		// TBD as an exercise: try loading this from a jar-file (InputStream.getResourceAsStream or such...)
		String searchPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String defaultConfig = searchPath + "default.properties";
		Properties defaultProperties = new Properties();
		defaultProperties.load(new FileInputStream(defaultConfig));
		String user = defaultProperties.getProperty("dbuser");
		System.out.println("user is: "+user);
		
		// now load specialized version of property-file:
		String mySpecialConfig = searchPath + "veryspecial.properties";
		Properties myVerySpecialProperties = new Properties(defaultProperties);
		myVerySpecialProperties.load(new FileInputStream(mySpecialConfig));
		user=myVerySpecialProperties.getProperty("dbuser");
		System.out.println("user is: "+user);
		// now some locale-stuff introducing ResourceBundle
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
		System.out.println("hi using the locale "+Locale.getDefault().getLanguage()+"_"+Locale.getDefault().getCountry()+" is: "+resourceBundle.getString("hi"));
		
		
	}
}
