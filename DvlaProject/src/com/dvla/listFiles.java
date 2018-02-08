package com.dvla;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
public class listFiles {

		public static void main(String[] args) throws IOException {
			
		File dir = new File("/Users/Jokrish/Documents/Test");//Directory Path
		String[] extensions = new String[] { "xlsx", "csv"};//Enter the extensions required
		System.out.println("File Name \t\t "+"MIME Type \t\t\t\t "+"Size \t\t"+"Extension");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
					System.out.println( file.getName()+"\t "+new MimetypesFileTypeMap().getContentType(file)+"\t\t"+ file.length()+"\t\t"
                    +FilenameUtils.getExtension(file.getCanonicalPath()));
				
				}

	}
		
		
	}	