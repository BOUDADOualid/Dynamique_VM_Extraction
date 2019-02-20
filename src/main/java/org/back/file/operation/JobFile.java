package org.back.file.operation;

import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;


public class JobFile {

	public static void DeleteAllFils(String Path) {

		File folderToTrun = new File(Path);
		File[] listOfFilesToTrun = folderToTrun.listFiles();
		if (listOfFilesToTrun != null) {
			for (File file : listOfFilesToTrun) {
				file.delete();
			}
		}
	}

	
	public static void decompressAllFils(String chemin) throws ZipException {
		   File folder = new File(chemin);
		      File[] listOfFiles = folder.listFiles();
		      for (File file : listOfFiles) {
		        if (file.getName().contains("indathenareportingesope"))
		        {
		          ZipFile zipFile = new ZipFile(file.getAbsolutePath());
		          
		          zipFile.extractAll(chemin);
		          file.delete();
		        }
		      }
		      File[] listOfFilesAll = folder.listFiles();
		      for (File file : listOfFilesAll) {
		        if (file.getName().contains("zip"))
		        {
		          ZipFile zipFile = new ZipFile(file.getAbsolutePath());
		          zipFile.extractAll(chemin);
		          //file.delete();
		        }
		      }
		    }
		    
		    }
		  