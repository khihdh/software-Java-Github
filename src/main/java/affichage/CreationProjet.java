package affichage;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.*;
import javax.swing.table.*;

import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import authentification.Authentification;
import group.Group_;
import project.Project_;
import projetFichierCsv.ReadCSV;
import project.CreateProject;

	
	public class CreationProjet extends javax.swing.JFrame {

		private CreateProject project;
		public static final String[] columns = {
	                "Name", "Surname", "Username/email","AccessLevel"
	            };
		public static final String[] columns2 = {
                "Name","AccessLevel"
            };
	    private DefaultTableModel model;
	    private DefaultTableModel model2;
	    private boolean addMember = false;
	    private static String pathCSV;
	    private static int choix;
	    // choix = 1 : Project
	    // choix = 0 : Groupe
		   
	    /**
		     * Creates new form Create
		     */
		    public CreationProjet(int choix) {
		    
		    	this.choix=choix;

		        jPanel1 = new javax.swing.JPanel();
		        jTextField1 = new javax.swing.JTextField();
		        jPanel2 = new javax.swing.JPanel();
		        step1 = new javax.swing.JPanel();
		        jTextProjectName = new javax.swing.JTextField();
		        jTextName = new javax.swing.JTextField();
		        jTextField4 = new javax.swing.JTextField();
		        jScrollPane2 = new javax.swing.JScrollPane();
		        jTextArea1 = new javax.swing.JTextArea();
		        jTextField7 = new javax.swing.JTextField();
		        nextButton1 = new javax.swing.JButton();
		        step2 = new javax.swing.JPanel();
		        jScrollPane1 = new javax.swing.JScrollPane();
		        jTable1 = new javax.swing.JTable();
		        jTextField5 = new javax.swing.JTextField();
		        jButton3 = new javax.swing.JButton();
		        nextButton2 = new javax.swing.JButton();
		        step3 = new javax.swing.JPanel();
		        jTextField8 = new javax.swing.JTextField();
		        jScrollPane3 = new javax.swing.JScrollPane();
		        jTable2 = new javax.swing.JTable();
		        jScrollPane4 = new javax.swing.JScrollPane();
		        description3 = new javax.swing.JTextArea();
		        jTextField6 = new javax.swing.JTextField();
		        jTextField9 = new javax.swing.JTextField();
		        jTextField10 = new javax.swing.JTextField();
		        jTextField11 = new javax.swing.JTextField();
		        createButton3 = new javax.swing.JButton();
		        model = new DefaultTableModel(columns, 0);
		        model2= new DefaultTableModel(columns2, 0);
		        jTextArea2 = new javax.swing.JTextArea();
		        jScrollPane5 = new javax.swing.JScrollPane();

		        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

		        jTextField1.setEditable(false);
		        jTextField1.setBackground(new java.awt.Color(0, 0, 51));
		        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
		        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
		        jTextField1.setText("New Project");
		        jTextField1.setBorder(null);

		        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		        jPanel1.setLayout(jPanel1Layout);
		        jPanel1Layout.setHorizontalGroup(
		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel1Layout.createSequentialGroup()
		                .addGap(242, 242, 242)
		                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(244, 244, 244))
		        );
		        jPanel1Layout.setVerticalGroup(
		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
		                .addContainerGap(38, Short.MAX_VALUE)
		                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(33, 33, 33))
		        );

		        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

		        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		        jPanel2.setLayout(jPanel2Layout);
		        jPanel2Layout.setHorizontalGroup(
		            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGap(0, 0, Short.MAX_VALUE)
		        );
		        jPanel2Layout.setVerticalGroup(
		            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGap(0, 10, Short.MAX_VALUE)
		        );

		        jTextProjectName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		        jTextProjectName.setText("Project1");
		        jTextProjectName.setBorder(null);

		        jTextName.setEditable(false);
		        jTextName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		        jTextName.setText("Name *:");
		        jTextName.setBackground(new java.awt.Color(219, 219, 219));
		        jTextProjectName.setBorder(null);

		        jTextField4.setEditable(false);
		        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		        jTextField4.setText("Description : ");
		        jTextField4.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField4.setBorder(null);

		        jTextArea1.setColumns(20);
		        jTextArea1.setRows(5);
		        jTextArea1.setLineWrap(true);
		        jScrollPane2.setViewportView(jTextArea1);

		        jTextField7.setEditable(false);
		        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		        jTextField7.setText("1/3 Please enter a name (*mandatory) and a description to your project");
		        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 12));
		        jTextField7.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField7.setBorder(null);

		        nextButton1.setText("next");
		        nextButton1.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		                nextButton1ActionPerformed(evt);
		            }
		        });

		        javax.swing.GroupLayout step1Layout = new javax.swing.GroupLayout(step1);
		        step1.setLayout(step1Layout);
		        step1Layout.setHorizontalGroup(
		            step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(step1Layout.createSequentialGroup()
		                .addGroup(step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                    .addGroup(step1Layout.createSequentialGroup()
		                        .addGap(0, 0, Short.MAX_VALUE)
		                        .addComponent(nextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(step1Layout.createSequentialGroup()
		                        .addGap(27, 27, 27)
		                        .addGroup(step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                            .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addGap(18, 18, 18)
		                        .addGroup(step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(step1Layout.createSequentialGroup()
		                                .addComponent(jTextProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(0, 0, Short.MAX_VALUE))
		                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))))
		                .addGap(41, 41, 41))
		            .addComponent(jTextField7)
		        );
		        step1Layout.setVerticalGroup(
		            step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(step1Layout.createSequentialGroup()
		                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(29, 29, 29)
		                .addGroup(step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jTextProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(50, 50, 50)
		                .addGroup(step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
		                .addComponent(nextButton1)
		                .addGap(38, 38, 38))
		        );

		        jTable1.setModel(model);
		        jTable1.setVisible(false);
		        jScrollPane1.setViewportView(jTable1);

		        jTextField5.setEditable(false);
		        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		        jTextField5.setText("2/3 You can now import your .csv file to add the members");
		        jTextField5.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField5.setBorder(null);

		        jButton3.setText("Import .csv");
		        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		        jButton3.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	jButton3ActionPerformed(evt);
		            }
		        });

		        nextButton2.setText("next");
		        nextButton2.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		                nextButton2ActionPerformed(evt);
		            }
		        });
		        
		        jTextArea2.setEditable(false);
		        jTextArea2.setBackground(new java.awt.Color(240, 240, 240));
		        jTextArea2.setColumns(20);
		        jTextArea2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		        jTextArea2.setRows(5);
		        jTextArea2.setLineWrap(true);
		        jTextArea2.setText("IMPORTANT BEFORE IMPORT !\n"
		        		+ "Please enter these exact titles in the boxes\n"
		        		+ "of your .csv : \n\n"
		        		+ "A1 : name\n"
		        		+ "B1 : surname\n"
		        		+ "C1 : mail/username\n"
		        		+ "D1 : acceesslevel\n"
		        		+ "columnD : enter the role in capital letters (example: GUEST,REPORTER,\n"
		        		+ "DEVELOPER, MAINTAINER, OWNER)");
		        jScrollPane5.setViewportView(jTextArea2);
		        
		        step2.setVisible(false);
		       
		        javax.swing.GroupLayout step2Layout = new javax.swing.GroupLayout(step2);
		        step2.setLayout(step2Layout);
		        step2Layout.setHorizontalGroup(
		            step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(step2Layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(jTextField5)
		                .addContainerGap())
		            .addGroup(step2Layout.createSequentialGroup()
		                .addGap(40, 40, 40)
		                .addGroup(step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addGroup(step2Layout.createSequentialGroup()
		                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(90, Short.MAX_VALUE))
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2Layout.createSequentialGroup()
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(nextButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(26, 26, 26))
		        );
		        step2Layout.setVerticalGroup(
		            step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2Layout.createSequentialGroup()
		                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(17, 17, 17)
		                .addGroup(step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jButton3)
		                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(18, 18, 18)
		                .addComponent(nextButton2)
		                .addContainerGap(42, Short.MAX_VALUE))
		        );


		        jTextField8.setEditable(false);
		        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		        jTextField8.setText("3/3 Here is a small recap of your new project");
		        jTextField8.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField8.setBorder(null);
		        
		        jTable2.setModel(model2);
		        jTable1.setVisible(false);	
		        jScrollPane3.setViewportView(jTable2);
		        
		        description3.setEditable(false);
		        description3.setBackground(new java.awt.Color(240, 240, 240));
		        description3.setColumns(20);
		        description3.setRows(5);
		        description3.setLineWrap(true);
		        jScrollPane4.setViewportView(description3);
		        
		        jTextField6.setEditable(false);
		        jTextField6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		        jTextField6.setText("Project1");
		        

		        createButton3.setText("done");
		        createButton3.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	createButton3ActionPerformed(evt);
		            }
		        });
		        
		        jTextField9.setEditable(false);
		        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		        jTextField9.setText("Name :");
		        jTextField9.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField9.setBorder(null);
		       

		        jTextField10.setEditable(false);
		        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		        jTextField10.setText("Description :");
		        jTextField10.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField10.setBorder(null);
		       
		        jTextField11.setEditable(false);
		        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		        jTextField11.setText("Members :");
		        jTextField11.setBackground(new java.awt.Color(219, 219, 219));
		        jTextField11.setBorder(null);
		       
		        
		        step3.setVisible(false);
		        
		        javax.swing.GroupLayout step3Layout = new javax.swing.GroupLayout(step3);
		        step3.setLayout(step3Layout);
		        step3Layout.setHorizontalGroup(
		            step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(step3Layout.createSequentialGroup()
		                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(0, 0, Short.MAX_VALUE))
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
		                        .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addGap(28, 28, 28)
		                        .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
		                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addGap(162, 162, 162))
		                                .addGroup(step3Layout.createSequentialGroup()
		                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addContainerGap()))
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
		                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(239, 239, 239))))
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
		                        .addComponent(createButton3)
		                        .addGap(28, 28, 28))))
		        );
		        step3Layout.setVerticalGroup(
		            step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(step3Layout.createSequentialGroup()
		                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(26, 26, 26)
		                .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
		                .addComponent(createButton3)
		                .addGap(45, 45, 45))
		        );


		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(20, 20, 20)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addComponent(step3, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
		                    .addComponent(step1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                .addGap(0, 0, Short.MAX_VALUE))
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(step2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap())
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(4, 4, 4)
		                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(40, 40, 40)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(step3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(step1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addContainerGap(34, Short.MAX_VALUE))
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(step2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addContainerGap())))
		        );

		        step2.getAccessibleContext().setAccessibleName("");
		        project = new CreateProject();

		        pack();
		    }

		    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {                                            
		        // TODO add your handling code here:
		    }                                           

		    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {   
		    	javax.swing.JFileChooser fileChose = new javax.swing.JFileChooser();
	                //javax.swing.filechooser.FileFilter fileCSV = new javax.swing.filechooser.FileNameExtensionFilter("name",".csv");
	                // pour avoir un fichier csv

	            int renvoie = fileChose.showOpenDialog(null); // 1 pour Ok, 0sinon

	            if (renvoie == javax.swing.JFileChooser.APPROVE_OPTION) {
	                 pathCSV= fileChose.getSelectedFile().getAbsolutePath(); //lien vers le fichier
	            }
	            System.out.println(pathCSV);
		    	ReadCSV reader = new ReadCSV(new File(pathCSV));
				HashMap<String, ArrayList<String>> test = reader.readCSV();
				System.out.println(test.toString());
		    	for(int i=0; i<test.get("name").size();i++)
				   {
					model.addRow(new Object[]{test.get("name").get(i),test.get("surname").get(i),test.get("mail/username").get(i),test.get("accesslevel").get(i)});
				   }
		    	jTable1.setModel(model);
		    	jTable1.setVisible(true);
		    	nextButton2.setText("next");
		    	addMember=true;
		    }       
		    
		    
		    private String accesslevelToString(AccessLevel accesslevel) {  	
		    	String accessLevelName = null;
		    	 switch (accesslevel.toValue()) {
				 case 10:
					 accessLevelName="GUEST";
					 break;
				 case 20:
					 accessLevelName="REPORTER";
					 break;
				 case 30:
					 accessLevelName="DEVELOPER";
					 break;
				 case 40:
					 accessLevelName="MAINTAINER";
					 break;
				 case 50:		 
					 accessLevelName="OWNER";
					 break;
				 }
		    	 return accessLevelName;
		    	
		    }

		    private void nextButton2ActionPerformed(java.awt.event.ActionEvent evt) {      
		    	if (addMember) {
		    		if(project.addMemberCSV(pathCSV)) {
			        	step2.setVisible(false);
			    		step3.setVisible(true);
			    		jTextField6.setText(project.getName());
			    		for (int i=0; i<project.getMembers().size();i++) {
			    			model2.addRow(new Object[] {project.getMembers().get(i).getName(),accesslevelToString(project.getMembers().get(i).getAccessLevel())});
			    		}
			    		jTable2.setModel(model2);
			    		jTable1.setVisible(true);
			    		description3.setText(project.getDescription());
			        }else {
			        	JOptionPane.showMessageDialog(null, " a username is already in the project");
			        }
		    	}else {
		    		step2.setVisible(false);
		    		step3.setVisible(true);
		    		jTextField6.setText(project.getName());
		    		for (int i=0; i<project.getMembers().size();i++) {
		    			model2.addRow(new Object[] {project.getMembers().get(i).getName(),accesslevelToString(project.getMembers().get(i).getAccessLevel())});
		    		}
		    		jTable2.setModel(model2);
		    		jTable1.setVisible(true);
		    		description3.setText(project.getDescription());
		    	}
		    
		    }                                           
               
		    private void createButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		    	if (choix==1) {
		    		PageProjet.addRow(project.getProject());
		    	}
		    	if (choix==2) {
		    		PageGroupe.addRow(project.getProject());
		    	}
		    	this.dispose();
		    }

		    private void nextButton1ActionPerformed(java.awt.event.ActionEvent evt) {   
		    	if (choix==1) {
		    	if (project.createProject( jTextProjectName.getText(),jTextArea1.getText(), Authentification.getUsername())) {
		    		step1.setVisible(false);
		    		step2.setVisible(true);
		    	}else {
		    		jTextField7.setText(" you already have a project with this name, please enter another name");
		    	}
		    	}
		    	else if (choix==2) {
		    		int row =PageGroupe.getGroupsTable().getSelectedRow();
		        	Group_ selectedGroup= PageGroupe.listGroup_.get(row);
		        	Group selectedGroup_git;
					try {
						GitLabApi gitLabApi = Authentification.connectGitlabApi();
						selectedGroup_git = gitLabApi.getGroupApi().getGroup(selectedGroup.getGroupId());
						System.out.println(choix);
			        	System.out.println(selectedGroup.getName());
				    	if (project.createProjectInGroup( jTextProjectName.getText(),jTextArea1.getText(), Authentification.getUsername(), selectedGroup_git)) {
				    		step1.setVisible(false);
				    		step2.setVisible(true);
				    	}else {
				    		jTextField7.setText(" you already have a project with this name, please enter another name");
				    	}
					} catch (GitLabApiException e) {
						// TODO Auto-generated catch block
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
		            java.util.logging.Logger.getLogger(CreationProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (InstantiationException ex) {
		            java.util.logging.Logger.getLogger(CreationProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (IllegalAccessException ex) {
		            java.util.logging.Logger.getLogger(CreationProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		            java.util.logging.Logger.getLogger(CreationProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        }
		        //</editor-fold>

		        /* Create and display the form */
		        java.awt.EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                new CreationProjet(choix).setVisible(true);
		            }
		        });
		    }

		    // Variables declaration - do not modify                     
		    private javax.swing.JButton createButton3;
		    private javax.swing.JTextArea description3;
		    private javax.swing.JButton jButton3;
		    private javax.swing.JPanel jPanel1;
		    private javax.swing.JPanel jPanel2;
		    private javax.swing.JScrollPane jScrollPane1;
		    private javax.swing.JScrollPane jScrollPane2;
		    private javax.swing.JScrollPane jScrollPane3;
		    private javax.swing.JScrollPane jScrollPane4;
		    private javax.swing.JScrollPane jScrollPane5;
		    private javax.swing.JTable jTable1;
		    private javax.swing.JTable jTable2;
		    private javax.swing.JTextArea jTextArea1;
		    private javax.swing.JTextArea jTextArea2;
		    private javax.swing.JTextField jTextField1;
		    private javax.swing.JTextField jTextProjectName;
		    private javax.swing.JTextField jTextName;
		    private javax.swing.JTextField jTextField4;
		    private javax.swing.JTextField jTextField5;
		    private javax.swing.JTextField jTextField6;
		    private javax.swing.JTextField jTextField7;
		    private javax.swing.JTextField jTextField8;
		    private javax.swing.JTextField jTextField9;
		    private javax.swing.JTextField jTextField10;
		    private javax.swing.JTextField jTextField11;
		    private javax.swing.JButton nextButton1;
		    private javax.swing.JButton nextButton2;
		    private javax.swing.JPanel step1;
		    private javax.swing.JPanel step2;
		    private javax.swing.JPanel step3;
		    
		    // End of variables declaration                   
		}

               


