package affichage;

import java.awt.Component;
import java.util.List;

import javax.swing.SwingUtilities;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.User;

import authentification.Authentification;
import group.Group_;
import project.Project_;
/**
 *
 * @author rouss
 */
public class AjouterMembre extends javax.swing.JFrame {

	static GitLabApi gitLabApi;
	

    /**
     * @param proj
     * @param grp
     * @param choice
     */
	
    public AjouterMembre(Project_ proj, Group_ grp, int choice) {
    

    	project = proj;
    	group = grp;
    	//accessLevel = access;
		// Access Level  INVALID(-1), NONE(0), MINIMAL_ACCESS(5), GUEST(10), REPORTER(20), DEVELOPER(30), @Deprecated MASTER(40), MAINTAINER(40), OWNER(50), ADMIN(60);

    	choix = choice;
    	// choix = 0 project / 1 groupe
    	
    	
   
    			
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        AccessTitle = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 0, 51));
        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Add Member");
        jTextField1.setBorder(null);
        if (choix == 0) {
        	jTextField1.setText("Add Member");
        }
        if (choix == 1) {
        	jTextField1.setText("Add Admin");
        }
        jTextField1.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTextField2.setEditable(false);
        jTextField2.setText("Username :");
        jTextField2.setBackground(new java.awt.Color(219, 219, 219));
        jTextField2.setBorder(null);

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(204, 0, 0));
        jTextField4.setText("this username does not exist, please enter a valid one");
        jTextField4.setBorder(null);
        jTextField4.setVisible(false);


        jButton1.setText("add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        AccessTitle.setEditable(false);
        AccessTitle.setText("Access Level :");
        AccessTitle.setBorder(null);
        AccessTitle.setBackground(new java.awt.Color(219, 219, 219));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GUEST", "REPORTER", "DEVELOPER", "MAINTAINER", "OWNER" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        
        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Please insert a username and select an access level");
        jTextField5.setBackground(new java.awt.Color(219, 219, 219));
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AccessTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AccessTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>     
    
    /**
     * @param evt
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
		 switch (jComboBox1.getSelectedIndex()) {
		 case 0:
			 accessLevel=10;
			 accessLevelName="GUEST";
			 break;
		 case 1:
			 accessLevel=20;
			 accessLevelName="REPORTER";
			 break;
		 case 2:
			 accessLevel=30;
			 accessLevelName="DEVELOPER";
			 break;
		 case 3:
			 accessLevel=40;
			 accessLevelName="MAINTAINER";
			 break;
		 case 4:
			 accessLevel=50;
			 accessLevelName="OWNER";
			 break;
		 }
		
    }     

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {
    	// choix = 0 project / 1 group
    	if (choix==0) {
    	
	    	try {
				
				//Test
				// gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
	    		gitLabApi = Authentification.connectGitlabApi();
								
				List<User> users= gitLabApi.getUserApi().findUsers(jTextField3.getText());
				
				 
				// Add member to the project
				gitLabApi.getProjectApi().addMember(project.getProjectId(),users.get(0).getId(),accessLevel);
				// Access Level  INVALID(-1), NONE(0), MINIMAL_ACCESS(5), GUEST(10), REPORTER(20), DEVELOPER(30), @Deprecated MASTER(40), MAINTAINER(40), OWNER(50), ADMIN(60);
				Member newmember = new Member();
				newmember.setUsername(users.get(0).getUsername());
				newmember.setId(users.get(0).getId());
				newmember.setName(users.get(0).getName());
				newmember.setAccessLevel(AccessLevel.forValue(accessLevel));
				
				
				PageProjet.model2.addRow(new Object[]{false,users.get(0).getUsername(),accessLevelName});
				PageProjet.listMember_.add(newmember); 
				// Update nbr of member per project
				
				project.setNbr_collaborators(project.getNbr_collaborators()+1);
				SwingUtilities.updateComponentTreeUI(this);
				SwingUtilities.updateComponentTreeUI(PageProjet.framePageProjet);
				this.dispose();

			
			} catch (GitLabApiException e) {
				System.out.println("User already existing or Username is wrong name");
				e.printStackTrace();
			}
    	}
    	
    	if (choix==1) {
        	
	    	try {
				
				//Test
				// gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
	    		gitLabApi = Authentification.connectGitlabApi();
								
				List<User> users= gitLabApi.getUserApi().findUsers(jTextField3.getText());
				// Add member to the project
				gitLabApi.getGroupApi().addMember(group.getGroupId(),users.get(0).getId(),accessLevel);
				// Access Level  INVALID(-1), NONE(0), MINIMAL_ACCESS(5), GUEST(10), REPORTER(20), DEVELOPER(30), @Deprecated MASTER(40), MAINTAINER(40), OWNER(50), ADMIN(60);
				
				Member newmember = new Member();
				newmember.setUsername(users.get(0).getUsername());
				newmember.setId(users.get(0).getId());
				newmember.setName(users.get(0).getName());
				newmember.setAccessLevel(AccessLevel.forValue(accessLevel));
				
				PageGroupe.adminmodel.addRow(new Object[]{false,users.get(0).getUsername(),accessLevelName});
				group.getMembers().add(newmember);  
				// Update nbr of member per group
				PageGroupe.selectedGroup.setNbr_collaborators(PageGroupe.selectedGroup.getNbr_collaborators()+1);
				SwingUtilities.updateComponentTreeUI(this);
				SwingUtilities.updateComponentTreeUI(PageGroupe.framePageGroupe);
				this.dispose();
			
	    	} catch (GitLabApiException e) {
				System.out.println("Can't add this Administrator. You don't have the right or the user already exist");
				e.printStackTrace();
			}
    	}
    	
    }                 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AjouterMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjouterMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjouterMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjouterMembre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	if (choix==0) {
                    new AjouterMembre(project, null,choix).setVisible(true);
                	}
                	if (choix==1) {
                        new AjouterMembre(null, group,choix).setVisible(true);
                    	}
            }
        });
    }

    // Variables declaration - do not modify                     

    private javax.swing.JTextField AccessTitle;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox<String> jComboBox1;
	private static Project_ project;
	private static Group_ group;
	private Integer accessLevel=10;
	private String accessLevelName="GUEST";
	private static int choix;
    // End of variables declaration                   
}
