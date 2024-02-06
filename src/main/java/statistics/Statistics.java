package statistics;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.gitlab4j.api.models.Member;
import org.jfree.chart.ChartFactory;

import java.awt.image.BufferedImage;

/**
 * @author Diane 
 */

//import javax.xml.soap.SOAPPart;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Statistics {
	static private String ProjectName;
	static private List<Member> listMembers;
	static private List<Integer> listCommits;
	private BufferedImage image;
	JLabel wIcon;

	public Statistics () {
		Statistics.ProjectName=new String();
		Statistics.listMembers=new ArrayList<Member>();
		Statistics.listCommits=new ArrayList<Integer>();
	};

	public static String getProjectName() {
		return ProjectName;
	}



	public static void setProjectName(String projectName) {
		ProjectName = projectName;
	}



	public static List<Member> getListMembers() {
		return listMembers;
	}



	public static void setListMembers(List<Member> listMembers) {
		Statistics.listMembers = listMembers;
	}



	public static List<Integer> getListCommits() {
		return listCommits;
	}



	public static void setListCommits(List<Integer> listCommits) {
		Statistics.listCommits = listCommits;
	}



	public void Commits() 
	{
		//pie chart
		DefaultPieDataset piedataset = new DefaultPieDataset();
		for (int i=0; i<listMembers.size(); i++)
		{
			piedataset.setValue(listMembers.get(i).getName(), listCommits.get(i));
		}
		
		// Add value to Data set
		JFreeChart chart = ChartFactory.createPieChart(
				"Number of Commit per Personnes", //title
				piedataset,
				true, //legend
				true, //tool tip
				false //use to generate URL
		);
		
		System.out.println("Statistic Works");
		
		// generate the chart
		try {
			ChartUtilities.saveChartAsJPEG(
					new File("/Users/madidina/git/info7/"+ ProjectName +".jpeg"),
					chart,
					500,
					300
			);
		image = ImageIO.read(new File("/Users/madidina/git/info7/"+ ProjectName +".jpeg"));
		wIcon = new JLabel(new ImageIcon(image));
		wIcon.setVisible(true);
		
		}catch (Exception e)
		{
			System.err.println("error" + e);
		}
	}
	
}
