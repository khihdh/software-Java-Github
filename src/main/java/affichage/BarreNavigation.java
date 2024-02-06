package affichage;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import authentification.Authentification;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BarreNavigation{

	
		JFrame frame;
		public BarreNavigation(final JFrame frame) 
		{
			this.frame=frame;
			//on cr�e les diff�rents �l�ments de la barre de navigation
		   JMenu menu;
		   JMenuItem myProjectButton2, myGroupsButton2, myImportProjectButton2, deconnexionButton2,accessTokenButton;
		   
		// Cr�er la barre de menu
		    JMenuBar menubar = new JMenuBar();
		    
		    // Cr�er le menu
		    menu = new JMenu("Menu");
		    
		    // Cr�er les �l�ments du menu et sous menu
		    
		    myProjectButton2 = new JMenuItem("Projects");
		    
		    myProjectButton2.addActionListener(new ActionListener() {
		    	
		        public void actionPerformed(ActionEvent arg) {
					
		        	myProjectButton2ActionPerformed(arg);
		       }

				private void myProjectButton2ActionPerformed(ActionEvent arg) {
					// TODO Auto-generated method stub     
					PageAccueil.onglets.setSelectedIndex(2);
				}
		    });
		    
		    
		    
		    myGroupsButton2 = new JMenuItem("Groups");  
		    
		    myGroupsButton2.addActionListener(new ActionListener() {
		    	
		        public void actionPerformed(ActionEvent arg) {
					
		        	myGroupsButton2ActionPerformed(arg);
		       }

				private void myGroupsButton2ActionPerformed(ActionEvent arg) {
					// TODO Auto-generated method stub
				       PageAccueil.onglets.setSelectedIndex(1);
					
				}
		    });
		    
		    
		    
		   // myImportProjectButton2 = new JMenuItem("mes modules");
		    deconnexionButton2 = new JMenuItem("Logout");
		    accessTokenButton = new JMenuItem("AccessToken");
		    
		    deconnexionButton2.addActionListener(new ActionListener() {
		    	
		        public void actionPerformed(ActionEvent arg) {
					
		        	deconnexionButton2ActionPerformed(arg);
		       }
		    });
		    
		    accessTokenButton.addActionListener(new ActionListener() {
		    	
		        public void actionPerformed(ActionEvent arg) {
					
		        	accessTokenButton2ActionPerformed(arg);
		       }
		    });
		    

		    
		    // Ajouter les �l�ments au menu
		    menu.add(myGroupsButton2);
		    menu.add(myProjectButton2);  
		    //menu.add(myImportProjectButton2);
		    menu.add(accessTokenButton);
		    menu.add(deconnexionButton2);
		    
		    // Ajouter le menu au barre de menu
		    menubar.add(menu);
		    
		    frame.setJMenuBar(menubar);
	   }
	   
	   public void deconnexionButton2ActionPerformed(ActionEvent evt) {  
		   JSONParser jsonParser = new JSONParser();
		   try (FileReader reader = new FileReader("employees.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	            
	 
	            JSONObject infosObject = (JSONObject) obj;
	            infosObject.remove("username");
	            infosObject.remove("cle");
	            infosObject.remove("autoconnect");
	            infosObject.put("username","none");
	            infosObject.put("cle", "none");
	            infosObject.put("autoconnect", "no");
	            System.out.println(infosObject);
	            try (FileWriter file = new FileWriter("employees.json")) {
	                //We can write any JSONArray or JSONObject instance to the file
	                file.write(infosObject.toJSONString()); 
	                file.flush();
	     
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            Authentification.setUsername("");
	          	Authentification.setPassword("");
	          	Authentification.setAcceptFalse();
	          	this.frame.dispose();
	          	new FenetreAuth(); 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
       }

	   public void accessTokenButton2ActionPerformed(ActionEvent evt) {  
		   new autoConnect();
	            
       }
}
