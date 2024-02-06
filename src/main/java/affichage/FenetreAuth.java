/**
 *
 */
package affichage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import authentification.Authentification;

/**
 * @author jeremy Castex
 *
 */

public class FenetreAuth {
	public JFrame f;

	public FenetreAuth() {
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("employees.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject infosObject = (JSONObject) obj;
            System.out.println(infosObject);
             
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
            String username = (String) infosObject.get("username");
            String cle = (String) infosObject.get("cle");
            String autoconnect = (String) infosObject.get("autoconnect");
            
			if (autoconnect.equals("yes")) {
				Authentification.setUsername(username);
				Authentification.setAutoconnect("yes");
				Authentification.setCle(cle);
				new PageAccueil();
				System.out.println(infosObject);
			} else {          
            
            f = new JFrame("Connexion to your gitlab Account: ");// creating instance of JFrame

    		// creation des element grahique
    		// creation label connexion
    		JLabel l0 = new JLabel("Please connect to your account");
    		l0.setBounds(80, 20, 220, 100);
    		// cr�ation du boutton connexion
    		JButton b1;
    		b1 = new JButton("Connexion");
    		b1.setBounds(190, 260, 110, 30);
    		JButton b2;
    		b2 = new JButton("forget password");
    		b2.setBounds(160, 340, 140, 20);
    		JButton b3;
    		b3 = new JButton("Sign up");
    		b3.setBounds(10, 340, 140, 20);
    		// creation des deux champs, un text et un mdp
    		final JTextField t1;
    		t1 = new JTextField("");
    		t1.setBounds(100, 150, 200, 30);
    		JLabel l1 = new JLabel("Username:");
    		l1.setBounds(20, 150, 80, 30);
    		final JPasswordField value = new JPasswordField();
    		value.setBounds(100, 200, 200, 30);
    		JLabel l2 = new JLabel("Password:");
    		l2.setBounds(20, 200, 80, 30);

    		final JLabel label = new JLabel();
    		label.setBounds(20, 300, 400, 50);
    		
    		//on s'occupe de la checkbox       
            /*JCheckBox checkbox = new JCheckBox("Stay Connected");   
            checkbox.setBounds(100,230, 150,30);  */  
    		
    		// on ajoute les elements creer plus haut
    		f.add(l0);
    		f.add(t1);
    		f.add(value);
    		f.add(l1);
    		f.add(l2);
    		f.add(b1);
    		f.add(b2);
    		f.add(b3);
    		f.add(label);
    		//f.add(checkbox); 

    		// action sur le clique du bouton
    		b1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				Authentification.setUsername(t1.getText());
    				Authentification.setPassword(new String(value.getPassword()));
    				if (Authentification.authentification()) {
    					label.setText(" Connexion succeed");
    					/*System.out.println(checkbox.isSelected());
    					if (checkbox.isSelected()) {
    						infosObject.remove("username");
    			            infosObject.remove("password");
    			            infosObject.put("username",Authentification.getUsername());
    			            infosObject.put("password", Authentification.getPassword());
    			            try (FileWriter file = new FileWriter("employees.json")) {
    			                //We can write any JSONArray or JSONObject instance to the file
    			                file.write(infosObject.toJSONString()); 
    			                file.flush();
    			     
    			            } catch (IOException ea) {
    			                ea.printStackTrace();
    			            }
    				    }*/
    					f.dispose();
    					new PageAccueil();
    					
    				} else {
    					label.setText("Connexion Failed, please check username or password");
    				}
    			}
    		});
    		b2.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				try {
    					if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
    						Desktop.getDesktop().browse(new URI("https://gitlab.com/users/password/new"));
    					}
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();

    				}
    			}
    		});
    		b3.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				try {
    					if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
    						Desktop.getDesktop().browse(new URI("https://gitlab.com/users/sign_up"));
    					}
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();

    				}
    			}
    		});
    		/*checkbox.addItemListener(new ItemListener() {    
                public void itemStateChanged(ItemEvent e) {                 
                   label.setText((e.getStateChange()==1?"infos will be saved":"infos won't be saved")); 
                   
                }    
             });  */  
    		f.setSize(400, 500);// 400 width and 500 height
    		f.setLayout(null);// using no layout managers
    		f.setVisible(true);// making the frame visible
            
			}
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
	}

}
