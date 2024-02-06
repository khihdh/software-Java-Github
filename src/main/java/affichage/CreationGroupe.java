
package affichage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteLayout;

import group.CreateGroup;
import projetFichierCsv.ReadCSV;
/**
 *
 * @author rouss
 */
public class CreationGroupe extends javax.swing.JFrame {
	
	private CreateGroup group;
	public static final String[] columns = {
            "Name", "Surname", "Username/email","AccessLevel"
        };
	public static final String[] columns2 = {
	        "Project","Member","AccessLevel"
	    };
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private static String pathCSV;
	private boolean addAdmin;
	private boolean addProject;

    /**
     * Creates new form CreateGroup
     */
    public CreationGroupe() {


        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        step1 = new javax.swing.JPanel();
        groupNameEnter = new javax.swing.JTextField();
        nameTitle = new javax.swing.JTextField();
        stepText1 = new javax.swing.JTextField();
        errorText1 = new javax.swing.JTextField();
        nextButton1 = new javax.swing.JButton();
        step2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminsTable2 = new javax.swing.JTable();
        stepText2 = new javax.swing.JTextField();
        importAdminButton = new javax.swing.JButton();
        nextButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        csvInfo2 = new javax.swing.JTextArea();
        step3 = new javax.swing.JPanel();
        importProjectButton = new javax.swing.JButton();
        nextButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        projectsTable3 = new javax.swing.JTable();
        stepText3 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        csvInfo3 = new javax.swing.JTextArea();
        step4 = new javax.swing.JPanel();
        stepText4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        adminsTable4 = new javax.swing.JTable();
        groupName4 = new javax.swing.JTextField();
        doneButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        projectsTable4 = new javax.swing.JTable();
        model = new DefaultTableModel(columns, 0);
        model2= new DefaultTableModel(columns2, 0);
        name = new javax.swing.JTextField();
        projects = new javax.swing.JTextField();
        members = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 0, 51));
        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("New Group");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
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

        groupNameEnter.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        groupNameEnter.setText("Group1");
   
        

        nameTitle.setEditable(false);
        nameTitle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nameTitle.setText("Name * :");
        nameTitle.setBorder(null);
        nameTitle.setBackground(new java.awt.Color(219, 219, 219));
       

        stepText1.setEditable(false);
        stepText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stepText1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stepText1.setText(" 1/3   Please enter the name of your group (*mandatory)");
        stepText1.setBorder(null);
        stepText1.setBackground(new java.awt.Color(219, 219, 219));
        
        
        errorText1.setEditable(false);
        errorText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errorText1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        errorText1.setBorder(null);
        errorText1.setBackground(new java.awt.Color(219, 219, 219));
        errorText1.setText("");
        
        
        
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
                .addGap(172, 172, 172)
                .addComponent(nameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(groupNameEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
            .addComponent(stepText1)
       
            .addComponent(errorText1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        step1Layout.setVerticalGroup(
            step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(step1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stepText1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(step1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupNameEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                	.addComponent(errorText1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(nextButton1)
                .addGap(33, 33, 33))
        );

        adminsTable2.setModel(model);
        adminsTable2.setVisible(false);
        jScrollPane1.setViewportView(adminsTable2);
        

        stepText2.setEditable(false);
        stepText2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stepText2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stepText2.setText("2/3     You can now import your .csv file to add the administrators");
        stepText2.setBorder(null);
        stepText2.setBackground(new java.awt.Color(219, 219, 219));
        

        importAdminButton.setText("Import .csv");
        importAdminButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        importAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importAdminButtonActionPerformed(evt);
            }
        });

        nextButton2.setText("later");
        nextButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButton2ActionPerformed(evt);
            }
        });

        csvInfo2.setEditable(false);
        csvInfo2.setBackground(new java.awt.Color(240, 240, 240));
        csvInfo2.setColumns(20);
        csvInfo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        csvInfo2.setRows(5);
        csvInfo2.setText("IMPORTANT BEFORE IMPORT !\n"
        		+ "Please enter these exact titles in the boxes\n"
        		+ "of your .csv : \n\n"
        		+ "A1 : name\n"
        		+ "B1 : surname\n"
        		+ "C1 : mail/username\n"
        		+ "D1 : acceesslevel\n"
        		+ "columnD : enter the role in capital letters (example: GUEST,REPORTER,\n"
        		+ "DEVELOPER, MAINTAINER, OWNER)");
        jScrollPane2.setViewportView(csvInfo2);

        javax.swing.GroupLayout step2Layout = new javax.swing.GroupLayout(step2);
        step2.setLayout(step2Layout);
        step2Layout.setHorizontalGroup(
            step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stepText2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        step2Layout.setVerticalGroup(
            step2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stepText2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importAdminButton)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton2)
                .addGap(62, 62, 62))
        );
        step2.setVisible(false);
        importProjectButton.setText("Import .csv");
        importProjectButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        importProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importProjectButtonActionPerformed(evt);
            }
        });

        nextButton3.setText("later");
        nextButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButton3ActionPerformed(evt);
            }
        });

        projectsTable3.setModel(model2);
        projectsTable3.setVisible(false);
        jScrollPane4.setViewportView(projectsTable3);

        stepText3.setEditable(false);
        stepText3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stepText3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stepText3.setText(" 3/3    You can now import your .csv file to add the projects ");
        stepText3.setBackground(new java.awt.Color(219, 219, 219));
        stepText3.setBorder(null);

        csvInfo3.setEditable(false);
        csvInfo3.setBackground(new java.awt.Color(240, 240, 240));
        csvInfo3.setColumns(20);
        csvInfo3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        csvInfo3.setRows(5);
        csvInfo3.setText("Important before import ! The exact name of columns in your .csv : \n\nA1 : Project\nB1 :username/email\nC1: accesslevel\n");
        csvInfo3.setLineWrap(true);
        jScrollPane6.setViewportView(csvInfo3);

        javax.swing.GroupLayout step3Layout = new javax.swing.GroupLayout(step3);
        step3.setLayout(step3Layout);
        step3Layout.setHorizontalGroup(
                step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
                    .addContainerGap(145, Short.MAX_VALUE)
                    .addGroup(step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(importProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(149, 149, 149))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepText3, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nextButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(60, 60, 60))
        );
        step3Layout.setVerticalGroup(
        		 step3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, step3Layout.createSequentialGroup()
                     .addContainerGap()
                     .addComponent(stepText3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                     .addComponent(importProjectButton)
                     .addGap(13, 13, 13)
                     .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addGap(18, 18, 18)
                     .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(nextButton3)
                     .addGap(62, 62, 62))
        );
        step3.setVisible(false);

        step4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stepText4.setEditable(false);
        stepText4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stepText4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stepText4.setText("Here is a small recap of your new group");
        stepText4.setBorder(null);
        stepText4.setBackground(new java.awt.Color(219, 219, 219));
        step4.add(stepText4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 60));

        adminsTable4.setModel(model);
        jScrollPane3.setViewportView(adminsTable4);

        step4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 410, 110));
        
        members.setEditable(false);
        members.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        members.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        members.setText("Members:");
        members.setBorder(null);
        members.setBackground(new java.awt.Color(219, 219, 219));
       
        step4.add(members, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 80, 30));

        groupName4.setEditable(false);
        groupName4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        groupName4.setText("Group1");
        step4.add(groupName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 126, 30));

        doneButton4.setText("done");
        doneButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButton4ActionPerformed(evt);
            }
        });
        step4.add(doneButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, -1, -1));

        projectsTable4.setModel(model2);
        jScrollPane5.setViewportView(projectsTable4);

        step4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 410, 120));

        name.setEditable(false);
        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        name.setText("Name :");
        name.setBorder(null);
        name.setBackground(new java.awt.Color(219, 219, 219));
      
        step4.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 80, 30));

        projects.setEditable(false);
        projects.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        projects.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        projects.setText("Projects:");
        projects.setBorder(null);
        projects.setBackground(new java.awt.Color(219, 219, 219));
        
        step4.add(projects, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 80, 30));


        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(step4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(step1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(step2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(47, Short.MAX_VALUE)
                    .addComponent(step3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
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
                            .addComponent(step4, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(step1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(step2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 155, Short.MAX_VALUE)
                    .addComponent(step3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        step4.setVisible(false);
        
        group= new CreateGroup();

        pack();
    }// </editor-fold>                            


    private void importAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
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
		if (test.get("name")==null) {JOptionPane.showMessageDialog(null, " the file does not respect the format ");};
		for(int i=0; i<test.get("name").size();i++)
		   {
			model.addRow(new Object[]{test.get("name").get(i),test.get("surname").get(i),test.get("mail/username").get(i),test.get("accesslevel").get(i)});
		   }
		adminsTable2.setModel(model);
		adminsTable2.setVisible(true);
		addAdmin=true;
		nextButton2.setText(" next ");
	    }                                                 
    	
    private void nextButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	if (addAdmin) {
    		if(group.addAdminCSV(pathCSV)) {
	        	step2.setVisible(false);
	    		step3.setVisible(true);
	        }else {
	        	JOptionPane.showMessageDialog(null, " a username is already admin of this group or this username does not exist");
	        }
    	}else {
    		step2.setVisible(false);
    		step3.setVisible(true);
    	}
    
    }  
                                                                                

    private void nextButton1ActionPerformed(java.awt.event.ActionEvent evt) { 
    	if(group.createGroup(groupNameEnter.getText())) {
	    	step1.setVisible(false);
	    	step2.setVisible(true);
	    	 groupName4.setText(groupNameEnter.getText());
    	}else {
    		errorText1.setText(" you already have this group, please enter another name or add a path (ex: groupParents/yourGroup) ");
    	}
    }                                           
                                     
    private void importProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	javax.swing.JFileChooser fileChose = new javax.swing.JFileChooser();
        //javax.swing.filechooser.FileFilter fileCSV = new javax.swing.filechooser.FileNameExtensionFilter("name",".csv");
        // pour avoir un fichier csv

	    int renvoie = fileChose.showOpenDialog(null); // 1 pour Ok, 0sinon
	
	    if (renvoie == javax.swing.JFileChooser.APPROVE_OPTION) {
	         pathCSV= fileChose.getSelectedFile().getAbsolutePath(); //lien vers le fichier
	    }
	    for (int i=0; i<model2.getRowCount();i++) {
	    	model2.removeRow(i);
	    }
	    System.out.println(pathCSV);
		ReadCSV reader = new ReadCSV(new File(pathCSV));
		HashMap<String, ArrayList<String>> test = reader.readCSV();
		System.out.println(test.toString());
		if (test.get("Project")==null) {JOptionPane.showMessageDialog(null, " the file does not respect the format ");};
		for(int i=0; i<test.get("Project").size();i++)
		   {
			model2.addRow(new Object[]{test.get("Project").get(i),test.get("username/email").get(i), test.get("accesslevel").get(i)});
		   }
		projectsTable3.setModel(model2);
		projectsTable3.setVisible(true);
		addProject=true;
		nextButton3.setText(" next ");
	    } 
                                                  

    private void nextButton3ActionPerformed(java.awt.event.ActionEvent evt) {   
    	if (addProject) {
    		if (group.addProjects(pathCSV)) {
        		step3.setVisible(false);
            	step4.setVisible(true);
        	}else {
        		JOptionPane.showMessageDialog(null, "a project is already in this group or a username is incorrect (does not exist)");
        	}
    	}else {
    		step3.setVisible(false);
        	step4.setVisible(true);
    	}
    	
    	
    }                                           

                               
    private void doneButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    	this.dispose();
    	PageGroupe.addRow(group.getGroup());
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
            java.util.logging.Logger.getLogger(CreationGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreationGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreationGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreationGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreationGroupe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTable adminsTable2;
    private javax.swing.JTable adminsTable4;
    private javax.swing.JTextArea csvInfo2;
    private javax.swing.JTextArea csvInfo3;
    private javax.swing.JButton doneButton4;
    private javax.swing.JTextField groupName4;
    private javax.swing.JTextField groupNameEnter;
    private javax.swing.JButton importAdminButton;
    private javax.swing.JButton importProjectButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nameTitle;
    private javax.swing.JButton nextButton1;
    private javax.swing.JButton nextButton2;
    private javax.swing.JButton nextButton3;
    private javax.swing.JTable projectsTable3;
    private javax.swing.JTable projectsTable4;
    private javax.swing.JPanel step1;
    private javax.swing.JPanel step2;
    private javax.swing.JPanel step3;
    private javax.swing.JPanel step4;
    private javax.swing.JTextField stepText1;
    private javax.swing.JTextField stepText2;
    private javax.swing.JTextField stepText3;
    private javax.swing.JTextField stepText4;
    private javax.swing.JTextField errorText1;
    private javax.swing.JTextField members;
    private javax.swing.JTextField name;
    private javax.swing.JTextField projects;
    // End of variables declaration                   
}
