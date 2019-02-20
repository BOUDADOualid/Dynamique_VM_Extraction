package org.back.config.metier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	public static FileConf configuration(String chemin) {
	    FileConf FC=new FileConf();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(chemin);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			
			FC.setPathToSave(prop.getProperty("pathToSave"));
//			FC.setChromeDrive(prop.getProperty("ChromeDrive"));
			
//éliminer à couse carctére spicieux au niveau de nom fichier reçus 
//			FC.setFileNameEncours(prop.getProperty("FileNameEncours"));
//            FC.setFileNameRecus(prop.getProperty("FileNameRecus"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return FC;

	}

}
