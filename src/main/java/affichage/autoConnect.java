package affichage;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import authentification.Authentification;

public class autoConnect {
	public JFrame f;

	public autoConnect() {
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
		
            f = new JFrame("adding your personnal token: ");// creating instance of JFrame

    		// creation des element grahique
    		// creation label connexion
    		JLabel l0 = new JLabel("Please add your personnal token");
    		l0.setBounds(80, 20, 220, 100);
    		// cr�ation du boutton connexion
    		JButton Add;
    		JButton Help;
    		Add = new JButton("Add");
    		Help = new JButton("Create my Access Token");
    		Add.setBounds(210, 200, 110, 30);
    		Help.setBounds(10, 200, 180, 30);
    		// creation des deux champs, un text et un mdp
    		final JTextField t1;
    		t1 = new JTextField("");
    		t1.setBounds(100, 150, 200, 30);
    		JLabel l1 = new JLabel("Token :");
    		l1.setBounds(20, 150, 80, 30);
    		//final JPasswordField value = new JPasswordField();
    		//value.setBounds(100, 200, 200, 30);

    		final JLabel label = new JLabel();
    		label.setBounds(20, 300, 400, 50);
    		  
    		
    		// on ajoute les elements creer plus haut
    		f.add(l0);
    		f.add(t1);
    		//f.add(value);
    		f.add(l1);
    		f.add(Add);
    		f.add(Help);
    		f.add(label);

    		// action sur le clique du bouton
    		Add.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				try (FileReader reader = new FileReader("employees.json"))
    		        {
    		            //Read JSON file
    		            Object obj = jsonParser.parse(reader);
    		            
    		 
    		            JSONObject infosObject = (JSONObject) obj;
    		            infosObject.remove("autoconnect");
    		            infosObject.remove("username");
    		            infosObject.remove("cle");
    		            infosObject.put("autoconnect","yes");
    		            infosObject.put("username",Authentification.getUsername());
    		            infosObject.put("cle", t1.getText());
    		            System.out.println(infosObject);
    		            try (FileWriter file = new FileWriter("employees.json")) {
    		                //We can write any JSONArray or JSONObject instance to the file
    		                file.write(infosObject.toJSONString()); 
    		                file.flush();
    		                f.dispose();
    		     
    		            } catch (IOException ea) {
			                ea.printStackTrace();
			            }
				    } catch (IOException e1) {
						e1.printStackTrace();
    			} catch (ParseException e2) {
						e2.printStackTrace();
					}
    			}
    			});
	Help.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
					Desktop.getDesktop().browse(new URI("https://code.telecomste.fr/-/profile/personal_access_tokens"));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			
		}
		});
	f.setSize(400, 500);// 400 width and 500 height
	f.setLayout(null);// using no layout managers
	f.setVisible(true);// making the frame visible

}

}
