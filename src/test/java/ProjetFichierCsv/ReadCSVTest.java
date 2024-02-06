package ProjetFichierCsv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

//import project.ReadCSV;

public class ReadCSVTest {
	/*@Test
	public void ReadCsvTest() {

		ReadCSV reader = new ReadCSV(new File(getClass().getResource("/liste.csv").getFile()));

		HashMap<String, ArrayList<String>> test = new HashMap<>();
		ArrayList<String> members_info7 = new ArrayList<>();
		members_info7.add("diane.peres@telecom-st-etienne.fr");
		test.put("info7", members_info7);

		ArrayList<String> members_info = new ArrayList<>();
		members_info.add("test@telecom-st-etienne.fr");
		test.put("info", members_info);

		Assert.assertEquals(reader.readCSV(), test);
		test.remove("info", members_info);
		Assert.assertNotEquals(reader.readCSV(), test);
	}*/
}
