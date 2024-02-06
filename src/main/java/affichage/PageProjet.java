package affichage;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Member;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;

import authentification.Authentification;
import project.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 * @author jeremy Castex / Athenna Roussin / Dianne Peres 
 * 
 * Cette classe s'occupe de l'affichage général des projets, et de leurs spécificités
 *
 */

public class PageProjet {

	public static int row;
	public static Tableau model;
	public static Tableau model2;
	public static List<Project_> listProject_;
	public static List<org.gitlab4j.api.models.Member> listMember_;
	public static Project_ selectedProject;
	public static int selectedRow;
	static JFrame framePageProjet;
	    public PageProjet() {
	    	super();
	    	model = new Tableau();
	    	model2 = new Tableau();
	    	listProject_ = null;
	    	listMember_ = null;
	    	framePageProjet=new JFrame();
	    	
	    	
	  
		    
			Object checkbox=new Object();
			Object name=new Object();
			Object role=new Object();
			Object nb_members=new Object();
			Object date_creation=new Object();
			Object date_modification=new Object();
			Object id=new Object();
			model.addColumn(checkbox="");
			model.addColumn(name="Name");
			model.addColumn(nb_members="Members");
			model.addColumn(date_creation="Created");
			model.addColumn(date_modification="Modified");
			model.addColumn(id ="id");

			listProject_ = ImportUserProjects.importProjects();

			for(Project_ proj: listProject_)
			   {
				model.addRow(new Object[]{false,proj.getName(),proj.getNbr_collaborators(),proj.getCreated_at(),proj.getLast_modif(),proj.getProjectId()});
			   }
			
	        framePageProjet.getContentPane().add(new JScrollPane(projectsTable), BorderLayout.CENTER);
	        
	        Icon icon = new ImageIcon("src/main/java/Affichage/binprojet.PNG");
	        importProjectButton = new javax.swing.JButton();
	        createProjectButton = new javax.swing.JButton();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        jPanel5 = new javax.swing.JPanel();
	        title = new javax.swing.JTextField();
	        jPanel3 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        directions = new javax.swing.JTextArea();
	        jPanel4 = new javax.swing.JPanel();
	        addMemberButton = new javax.swing.JButton();
	        jScrollPane4 = new javax.swing.JScrollPane();
	        jTextArea2 = new javax.swing.JTextArea();
	        membersTitle = new javax.swing.JTextField();
	        jTextField3 = new javax.swing.JTextField();
	        jScrollPane3 = new javax.swing.JScrollPane();
	        jList2 = new javax.swing.JList<String>();
	        descriptionTitle = new javax.swing.JTextField();
	        jScrollPane5 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        projectName = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        projectsTable = new javax.swing.JTable(model);
	        deleteButton = new javax.swing.JButton(/*icon*/);
	        deleteMemberButton = new javax.swing.JButton();
	        groupBelong = new javax.swing.JTextField();
	        groupBelongTitle = new javax.swing.JTextField();
	  
	        jScrollPane2.setViewportView(projectsTable);

	        framePageProjet.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jPanel4.setVisible(false);
	        membersTitle.setEditable(false);
	        membersTitle.setBackground(new java.awt.Color(240, 240, 240));
	        membersTitle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
	        membersTitle.setText("Members");
	        membersTitle.setBorder(null);

	        jTextField3.setEditable(false);
	        jTextField3.setBackground(new java.awt.Color(240, 240, 240));
	        jTextField3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
	        jTextField3.setText("Files");
	        jTextField3.setBorder(null);


	        descriptionTitle.setEditable(false);
	        descriptionTitle.setBackground(new java.awt.Color(240, 240, 240));
	        descriptionTitle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
	        descriptionTitle.setText("Description");
	        descriptionTitle.setBorder(null);
	        

	        jTextArea2.setEditable(false);
	        jTextArea2.setColumns(20);
	        jTextArea2.setRows(5);
	        jTextArea2.setLineWrap(true);
	        jScrollPane4.setViewportView(jTextArea2);
	        
	        model2.addColumn(checkbox="");
			model2.addColumn(name="Name");
			model2.addColumn("Access Level");
	        jTable1.setModel(model2);
	        jScrollPane5.setViewportView(jTable1);

	        projectName.setEditable(false);
	        projectName.setBackground(new java.awt.Color(0, 0, 51));
	        projectName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
	        projectName.setForeground(new java.awt.Color(255, 255, 255));
	        projectName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	        
	        
	        
	      	//set the width of columns of projectstable
	    	projectsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
	    	projectsTable.getColumnModel().getColumn(2).setPreferredWidth(15);
	    	projectsTable.getColumnModel().getColumn(5).setPreferredWidth(15);
	    	
	        //ouvrir infos du projet 
	        projectsTable.addMouseListener(new MouseListener() {
	        	
	        	@Override
	        	   public void mouseClicked(MouseEvent e) {
	        	      if (e.getClickCount() == 2) {
	        	         JTable target = (JTable) e.getSource();
	        	         int row = target.getSelectedRow();
	        	         selectedProject = listProject_.get(row);
	        	         
	        	         selectedRow=row;
	        	         jTextArea2.setText(selectedProject.getDescription());
	        	         projectName.setText(selectedProject.getName());
	        	         GitLabApi gitlabapi = Authentification.connectGitlabApi();
	        	         try {
	        	        	 String groupe[] = gitlabapi.getProjectApi().getProject(selectedProject.getProjectId()).getNameWithNamespace().split("/");
							groupBelong.setText(groupe[0]);
						} catch (GitLabApiException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	         int limit=model2.getRowCount();
	        	         for (int i=0;i<limit;i++) {
		        	         model2.removeRow(0);
	        	         }
	        	         listMember_=selectedProject.getMembers();
	        	         for (int i=0; i<listMember_.size();i++) {
	        	    			model2.addRow(new Object[] {false,listMember_.get(i).getUsername(),listMember_.get(i).getAccessLevel().name()});
	        	    		}
	        	         
	        	          List<String> files_=ImportFiles.importFilesOfProject(selectedProject.getProjectId());
	        	 	      DefaultListModel<String> filesmodel = new DefaultListModel<>();
	        	 	      if (files_!=null) {
	        	 	      for (String f: files_) {
	        	 	    	  filesmodel.addElement(f);
	        	 	    	  jList2.setModel(filesmodel);
	        	 	    	  jScrollPane3.setViewportView(jList2);
	        	 	      }  
	   
	        	         if(row!= -1){ 
	        	        	 jPanel4.setVisible(true);
	        	         }
	        	 	      }
	        	   }
	        	}


				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub					
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}

	        	});
	        
	        
	        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
	        deleteButton.setText("delete");
	        deleteButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                deleteButtonActionPerformed(evt,model);
	            }
	        });
	        
	       

	        importProjectButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
	        importProjectButton.setText("Import a project");
	        importProjectButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	        importProjectButton.setVisible(false);

	        createProjectButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        createProjectButton.setText("Create a project");
	        createProjectButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                createProjectButtonActionPerformed(evt);
	            }
	        });


	        jPanel5.setBackground(new java.awt.Color(0, 0, 51));
	        jPanel5.setPreferredSize(new java.awt.Dimension(650, 150));

	        title.setEditable(false);
	        title.setBackground(new java.awt.Color(0, 0, 51));
	        title.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
	        title.setForeground(new java.awt.Color(255, 255, 255));
	        title.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	        title.setText("Projects");
	        title.setPreferredSize(new java.awt.Dimension(150, 50));
	        
//	        jScrollPane3.setVisible(true);
//	        jList2.setVisible(true);
	        
	        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
	        jPanel5.setLayout(jPanel5Layout);
	        jPanel5Layout.setHorizontalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(704, 704, 704))
	        );
	        jPanel5Layout.setVerticalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel5Layout.createSequentialGroup()
	                .addGap(48, 48, 48)
	                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(52, Short.MAX_VALUE))
	        );

	        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

	        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	        jPanel3.setLayout(jPanel3Layout);
	        jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 17, Short.MAX_VALUE)
	        );

	        

	        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

	        directions.setEditable(false);
	        directions.setBackground(new java.awt.Color(240, 240, 240));
	        directions.setColumns(20);
	        directions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
	        directions.setRows(5);
	        directions.setText("");

	        jScrollPane1.setViewportView(directions);
	        
	        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

	        deleteMemberButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
	        deleteMemberButton.setText("delete");
	        deleteMemberButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                deleteMemberButtonActionPerformed(evt,model2,selectedProject);
	            }
	        });

	        addMemberButton.setText("Add Member");
	        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                addMemberButtonActionPerformed(evt);
	            }
	        });
	        
	        groupBelongTitle.setEditable(false);
	        groupBelongTitle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
	        groupBelongTitle.setText("Belongs to this following group");
	        groupBelongTitle.setBorder(null);
	        
	        groupBelong.setEditable(false);
	        groupBelong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

	        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
	        jPanel4.setLayout(jPanel4Layout);
	        jPanel4Layout.setHorizontalGroup(
	            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(projectName)
	            .addGroup(jPanel4Layout.createSequentialGroup()
	                .addGap(19, 19, 19)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(descriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(groupBelongTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(groupBelong, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(52, 52, 52)
	                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addComponent(membersTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addComponent(deleteMemberButton)
	                        .addGap(291, 291, 291)
	                        .addComponent(addMemberButton))
	                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(23, Short.MAX_VALUE))
	        );
	        jPanel4Layout.setVerticalGroup(
	            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel4Layout.createSequentialGroup()
	                .addComponent(projectName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(39, 39, 39)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(groupBelongTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addComponent(groupBelong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(27, 27, 27)
	                        .addComponent(descriptionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(membersTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(deleteMemberButton))
	                    .addGroup(jPanel4Layout.createSequentialGroup()
	                        .addGap(14, 14, 14)
	                        .addComponent(addMemberButton)))
	                .addGap(482, 482, 482))
	        );
	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	        jLabel1.setText("DIRECTIONS FOR THE USER :)");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(framePageProjet.getContentPane());
	        framePageProjet.getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1542, Short.MAX_VALUE)
	            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(50, 50, 50)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(importProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(200, 200, 200)
	                        .addComponent(createProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(23, 23, 23))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(75, 75, 75)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(45, 45, 45)
	                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(117, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(6, 6, 6)
	                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(19, 19, 19)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(deleteButton)
	                                .addGap(3, 3, 3)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(createProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(importProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(40, Short.MAX_VALUE))
	        );

	        framePageProjet.pack();
	    }// </editor-fold>   
	                        
	    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {
	    	
	    	 AjouterMembre frameText = new AjouterMembre(selectedProject,null,0);
	         frameText.setVisible(true);
	         frameText.pack();
	         frameText.setLocationRelativeTo(null);
	         frameText.setDefaultCloseOperation(AjouterMembre.DISPOSE_ON_CLOSE);
	    }                                           

	    private void createProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                                               
		        CreationProjet frameText = new CreationProjet(1);
		        frameText.setVisible(true);
		        frameText.pack();
		        frameText.setLocationRelativeTo(null);
		        frameText.setDefaultCloseOperation(CreationProjet.DISPOSE_ON_CLOSE);
	    }      
	    
	    private void deleteButtonActionPerformed(ActionEvent evt, Tableau model) {
			if ( projectsTable.getSelectedRow() != -1 && Tableau.aValue==true) {
				int opt=JOptionPane.showConfirmDialog(null, "Are you sure to delete "+ model.getValueAt(projectsTable.getSelectedRow(), 1)+" project","Delete",JOptionPane.YES_NO_OPTION);
				if (opt==0) {
					try {
						GitLabApi gitLabApi = Authentification.connectGitlabApi();
						SwingUtilities.updateComponentTreeUI(framePageProjet);
						for(Project_ proj: listProject_)
						{
							Integer id = proj.getProjectId();
							if ( id.toString().compareTo(model.getValueAt(projectsTable.getSelectedRow(), 5).toString())==0) {
								gitLabApi.getProjectApi().deleteProject(proj.getProjectId());
								model.removeRow(projectsTable.getSelectedRow());
								PageStats.model.removeRow(projectsTable.getSelectedRow());
								listProject_.remove(proj);
								PageStats.listProject_.remove(proj);
								//SwingUtilities.updateComponentTreeUI(framePageProjet);
								JOptionPane.showMessageDialog(null, "Deleted succesfully");
							}
						}
					} catch (GitLabApiException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Cannot delete this project, you don't have the permission");
						e.printStackTrace();
					}
	
				}
			}
			}
 
	    
	    
	    private void deleteMemberButtonActionPerformed(ActionEvent evt, Tableau model,Project_ project) {
	    	if ( jTable1.getSelectedRow() != -1 && Tableau.aValue==true) {
				int opt=JOptionPane.showConfirmDialog(null, "Are you sure to delete this member","Delete",JOptionPane.YES_NO_OPTION);
				if (opt==0) {
					try {
						GitLabApi gitLabApi = Authentification.connectGitlabApi();
						for(org.gitlab4j.api.models.Member member: project.getMembers())
						{
							if ( member.getUsername()==model.getValueAt(jTable1.getSelectedRow(), 1)) {
								System.out.println(member.getUsername());
								gitLabApi.getProjectApi().removeMember(project.getProjectId(),member.getId());
								project.getMembers().remove(project.getMembers().indexOf(member));
								model2.removeRow(jTable1.getSelectedRow());	
//								project.setNbr_collaborators((project.getNbr_collaborators())-1);
//								SwingUtilities.updateComponentTreeUI(framePageProjet);
								JOptionPane.showMessageDialog(null, "Deleted succesfully");				
							}
						
						}		
					} catch (GitLabApiException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Cannot remove this member, you don't have the permission");
						e.printStackTrace();
					}
				}
			}

		
			}


                                           

	    // function to add row
	    public static void AddRowToJTable(Object[] dataRow)
	    {
	        DefaultTableModel model = (DefaultTableModel)projectsTable.getModel();
	        model.addRow(dataRow);
	    }   
	    
	  
	    public static void addRow(Project proj) {
	    	String created_at;
	    	StringBuffer stringBuffer = new StringBuffer();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			simpleDateFormat.format(proj.getCreatedAt(), stringBuffer, new FieldPosition(0));
			created_at = stringBuffer.toString();
			String last_modif;
			StringBuffer stringBuffer2 = new StringBuffer();
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			simpleDateFormat2.format(proj.getLastActivityAt(), stringBuffer2, new FieldPosition(0));
			last_modif = stringBuffer2.toString();
			
			
			GitLabApi gitLabApi;
			try {
				gitLabApi = Authentification.connectGitlabApi();
				List<org.gitlab4j.api.models.Member> collaborators = gitLabApi.getProjectApi().getAllMembers(proj.getId());
				int nbr_collaborators = collaborators.size();
				List<Branch> branches = gitLabApi.getRepositoryApi().getBranches(proj.getId());
				int nbr_branches = branches.size();
				
		    	model.addRow(new Object[]{false,proj.getName(),nbr_collaborators,created_at,last_modif,proj.getId()});
		    	Project_ project = new Project_(proj.getId(), proj.getName(), created_at, last_modif, nbr_collaborators, nbr_branches,proj.getDescription(),collaborators);
				System.out.println(project.getName());
		    	listProject_.add(project);
			} catch (GitLabApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }

	    public static void main(String args[]) {

	        try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        //</editor-fold>

	        /* Create and display the form */
	 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(PageProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        //</editor-fold>

	        /* Create and display the form */
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	framePageProjet.setVisible(true);
	            }
	        });
	    }

	    public static javax.swing.JTable getProjetsTable() {
    		return projectsTable;
    	}

	    // Variables declaration - do not modify
	    private javax.swing.JButton createProjectButton;
	    private javax.swing.JButton deleteButton;
	    private javax.swing.JButton deleteMemberButton;
	    private javax.swing.JTextField descriptionTitle;
	    private javax.swing.JTextArea directions;
	    private javax.swing.JButton importProjectButton;
	    private javax.swing.JButton addMemberButton;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JList<String> jList2;
	    private javax.swing.JPanel jPanel3;
	    protected static javax.swing.JPanel jPanel4;
	    private javax.swing.JPanel jPanel5;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JScrollPane jScrollPane3;
	    private javax.swing.JScrollPane jScrollPane4;
	    private javax.swing.JScrollPane jScrollPane5;
	    private javax.swing.JTable jTable1;
	    protected static javax.swing.JTextArea jTextArea2;
	    private javax.swing.JTextField jTextField3;
	    private javax.swing.JTextField membersTitle;
	    protected static javax.swing.JTextField projectName;
	    private javax.swing.JTextField title;
	    public static javax.swing.JTable projectsTable;
	    public static javax.swing.JTextField groupBelong;
	    private javax.swing.JTextField groupBelongTitle;
	    
	    // End of variables declaration                   
	    
}

