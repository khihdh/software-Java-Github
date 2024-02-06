package affichage;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectSharedGroup;

import authentification.Authentification;
import group.Group_;
import project.ImportUserProjects;
import project.Project_;

/**
 *
 * @author rouss
 */
public class AjouterProjet extends javax.swing.JFrame {

	public static Tableau model;
	static GitLabApi gitLabApi;
	public static List<Project_> listProject_ ;
	int row;
    /**
     * Creates new form AddProject
     */
    public AjouterProjet(Group_ grp) {
   
    	model = new Tableau();
    	listProject_ = ImportUserProjects.importProjects();
    	
    	group = grp;
    	
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
	//	model.addColumn(date_modification="Modified");
		model.addColumn("id");
		
		for(Project_ proj: listProject_)
		   {
			model.addRow(new Object[]{false,proj.getName(),proj.getNbr_collaborators(),proj.getCreated_at(),proj.getProjectId()});
		   }
	    row=model.getRowCount();

        jPanel1 = new javax.swing.JPanel();
        jTextTitle = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListProject = new javax.swing.JTable(model);
        jButtonAdd = new javax.swing.JButton();
        jTextInstruction = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jTextTitle.setEditable(false);
        jTextTitle.setBackground(new java.awt.Color(0, 0, 51));
        jTextTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jTextTitle.setForeground(new java.awt.Color(255, 255, 255));
        jTextTitle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextTitle.setText("Add Project ");
        jTextTitle.setBorder(null);
        jTextTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTitleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jTextTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jTextTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jScrollPane1.setViewportView(jTableListProject);

        jButtonAdd.setText("add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTButtonAddActionPerformed(evt,model);
            }
        });

        jTextInstruction.setEditable(false);
        jTextInstruction.setText("Please select the project you want to add, then click the button : ");
        jTextInstruction.setBackground(new java.awt.Color(219, 219, 219));
        jTextInstruction.setBorder(null);
        jTextInstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTButtonAddActionPerformed(evt,model);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextInstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jTextInstruction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAdd)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>                        

    private void jTextTitleActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTButtonAddActionPerformed(java.awt.event.ActionEvent evt, Tableau model) {
    	int row =PageGroupe.getGroupsTable().getSelectedRow();
    	Group_ selectedGroup= PageGroupe.listGroup_.get(row);
		System.out.println(selectedGroup.getGroupId());
		
    	if ( jTableListProject.getSelectedRow() != -1 && Tableau.aValue==true) {
    		System.out.println(jTableListProject.getSelectedRow());
    		Project_ selectedProject = listProject_.get(jTableListProject.getSelectedRow());
			int opt=JOptionPane.showConfirmDialog(null, "Are you sure to add this project","Add",JOptionPane.YES_NO_OPTION);
			if (opt==0) {
				try {
					gitLabApi = Authentification.connectGitlabApi();
					for(Project_ proj: listProject_)
					{
						if ( proj.getProjectId()==selectedProject.getProjectId()) {
							//System.out.println(model.getValueAt(jTableListProject.getSelectedRow(), 1));
							System.out.println(proj.getName());
							System.out.println(proj.getProjectId());
							//System.out.println(selectedProject.getProjectId());
							
							// Share Group to project
							Project projectGit = gitLabApi.getProjectApi().getProject(proj.getProjectId());
							List<ProjectSharedGroup> SharedGroups = projectGit.getSharedWithGroups();
							System.out.println(SharedGroups);
//							sharedWithGroup.setGroupId(selectedGroup.getGroupId());
//							sharedWithGroup.setGroupName(selectedGroup.getName());
//							SharedGroups.add(sharedWithGroup);
//							System.out.println(SharedGroups);
//							projectGit.setSharedWithGroups(SharedGroups);
							
							// Share Project to Group
							// Without shared
							Group groupGit = gitLabApi.getGroupApi().getGroup(selectedGroup.getGroupId());
							List<Project> projects = groupGit.getProjects();
//							projToAdd.setId(proj.getProjectId());
//							projToAdd.setName(proj.getName());
//							projects.add(projToAdd);
//							groupGit.setProjects(projects);
//							
							// With shared
							List<Project> sharedProjects = groupGit.getSharedProjects();
							sharedProjects.add(projectGit);
							groupGit.setSharedProjects(projects);
							
							//gitLabApi.getProjectApi().shareProject(proj.getProjectId(), group.getGroupId(), 40, date );
							// Remove group from the showing table 
							model.removeRow(jTableListProject.getSelectedRow());
							row-=1;
							JOptionPane.showMessageDialog(null, "Add succesfully");
							// Add project to the table of project's group page 
							PageGroupe.projectmodel.addRow(new Object[]{false,proj.getName(),proj.getMembers().size(), proj.getCreated_at(), proj.getLast_modif()});
							PageGroupe.listProject_.add(proj);
							// Update nbr of project per group
							//PageGroupe.model.removeRow(PageGroupe.getGroupsTable().getSelectedRow());
							//PageGroupe.model.addRow(new Object[]{false,group.getName(),group.getMembers().size(),group.getProjects().size()});

						}
					}
				} catch (GitLabApiException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Cannot Add this project, you don't have the permission");
					e.printStackTrace();
				}

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
            java.util.logging.Logger.getLogger(AjouterProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjouterProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjouterProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjouterProjet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjouterProjet(group).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListProject;
    private javax.swing.JTextField jTextTitle;
    private javax.swing.JTextField jTextInstruction;
    private static Group_ group;
    private static Date date;
    private ProjectSharedGroup sharedWithGroup = new ProjectSharedGroup();
    private Project projToAdd = new Project();
    // End of variables declaration                   
}
