package projetFichierCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

/**
 * @author Diane PERES
 *
 */

public class ReadCSV {

	private static BufferedReader br;
	private File csvFile;

	public ReadCSV(File csvFile) {
		super();
		this.csvFile = csvFile;
	}

	public File getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

	public HashMap<String, ArrayList<String>> readCSV() {
		HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
		try {
			String line = "";
			String[] init = {""};
			//File csvFile = new File("liste.csv");// Chemin d'acc�s au CSV nomm� liste.csv par d�fault
			br = new BufferedReader(new FileReader(this.csvFile));
			while ((line = br.readLine()) != null) {// lire chaque ligne du fichier
				if (hash.size() == 0) {
					init =  line.split(";");
					for (int i=0; i<init.length; i++) {
						ArrayList<String> list = new ArrayList<String>();
						hash.put(init[i], list);
					}
				}else {
					String[] count = line.split(";"); // s�parer par une virgule
					for (int i=0; i<count.length; i++) {
							hash.get(init[i]).add(count[i]);
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hash;
	}
}
