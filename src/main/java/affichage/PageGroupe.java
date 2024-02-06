package affichage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.awt.*;
import javax.swing.table.*;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;

import authentification.Authentification;
import group.Group_;
import group.ImportUserGroups;
import project.ImportUserProjects;
import project.Project_;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author jeremy Castex / Athenna Roussin / Dianne Peres / Bihan Keven
 * 
 *         Cette classe s'occupe de l'affichage général des groupes, et de leurs
 *         spécificités
 *
 */

public class PageGroupe {
	static JFrame framePageGroupe;
	public static int row;
	public static Tableau model;
	public static Tableau adminmodel;
	public static Tableau projectmodel;
	public static List<Group_> listGroup_;
	public static List<Project_> listProject_;
	public static List<String> listProjectName_;
	public static Group_ selectedGroup;
	public static Project_ selectedProject;
	public static int nb_admin;
	public static int nb_projet;

	public PageGroupe() {
		super();
		framePageGroupe = new JFrame();
		model = new Tableau();
		adminmodel = new Tableau();
		projectmodel = new Tableau();
		listGroup_ = null;
		listProject_ = null;
		listProjectName_ = null;

		Object checkbox = new Object();
		Object name = new Object();
		Object nb_members = new Object();
		Object nb_admins = new Object();
		Object nb_project = new Object();
		Object date_creation = new Object();
		Object date_modification = new Object();
		Object id = new Object();

		model.addColumn(checkbox = "");
		model.addColumn(name = "Name");
		model.addColumn(nb_admins = "Administrators");
		model.addColumn(nb_project = "Projects");

		listGroup_ = ImportUserGroups.importGroups();

		for (Group_ group : listGroup_) {
			model.addRow(
					new Object[] { false, group.getName(), group.getNbr_collaborators(), group.getNbr_projects() });

		}

		int cols = model.getColumnCount();
		int row = model.getRowCount();

		framePageGroupe.getContentPane().add(new JScrollPane(getGroupsTable()), BorderLayout.CENTER);

		jSpinner1 = new javax.swing.JSpinner();
		jPanel2 = new javax.swing.JPanel();
		title = new javax.swing.JTextField();
		createGroupButton = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		directions = new javax.swing.JTextArea();
		Info1Group = new javax.swing.JPanel();
		jPaneladmin = new javax.swing.JPanel();
		jTextFieldAdmin = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		adminsTable = new javax.swing.JTable();
		addAdminButton = new javax.swing.JButton();
		importAdminButton = new javax.swing.JButton();
		deleteAdminButton = new javax.swing.JButton();
		jPanelprojects = new javax.swing.JPanel();
		jTextFieldProjects = new javax.swing.JTextField();
		jScrollPane2 = new javax.swing.JScrollPane();
		projectsTable = new javax.swing.JTable();
		importProjectButton = new javax.swing.JButton();
		addProjectButton = new javax.swing.JButton();
		deleteProjectButton = new javax.swing.JButton();
		jPanel6 = new javax.swing.JPanel();
		groupName = new javax.swing.JTextField();
		jScrollPane7 = new javax.swing.JScrollPane();
		setGroupsTable(new javax.swing.JTable(model));
		jLabel1 = new javax.swing.JLabel();
		deleteGroupButton = new javax.swing.JButton();
		importGroupButton = new javax.swing.JButton();
		importGroupProjectButton = new javax.swing.JButton();

		jScrollPane7.setViewportView(getGroupsTable());

		adminmodel.addColumn(checkbox = "");
		adminmodel.addColumn(name = "Username");
		adminmodel.addColumn("Acccess Level");
		adminsTable.setModel(adminmodel);
		jScrollPane1.setViewportView(adminsTable);

		projectmodel.addColumn(checkbox = "");
		projectmodel.addColumn(name = "Name");
		projectmodel.addColumn(nb_members = "Members");
		projectmodel.addColumn(date_creation = "Created");
		projectmodel.addColumn(date_modification = "Modified");
		projectmodel.addColumn(id = "id");
		projectsTable.setModel(projectmodel);
		jScrollPane2.setViewportView(projectsTable);

		Info1Group.setVisible(false);

		// set the width of columns of projectstable
		projectsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		projectsTable.getColumnModel().getColumn(2).setPreferredWidth(15);
		projectsTable.getColumnModel().getColumn(5).setPreferredWidth(15);

		// ouvrir Info1Group
		groupsTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					Group_ group = listGroup_.get(row);
					selectedGroup = group;
					groupName.setText(group.getName());
					int limit = adminmodel.getRowCount();
					for (int i = 0; i < limit; i++) {
						adminmodel.removeRow(0);

					}
					for (int i = 0; i < group.getMembers().size(); i++) {
						adminmodel.addRow(new Object[] { false, group.getMembers().get(i).getUsername(),
								group.getMembers().get(i).getAccessLevel().name() });
					}

					int limit1 = projectmodel.getRowCount();
					for (int i = 0; i < limit1; i++) {
						projectmodel.removeRow(0);
						// System.out.println(i);
					}
					listProject_ = ImportUserProjects.importProjectsOfGroup(group.getGroupId());
					for (Project_ proj : listProject_) {
						projectmodel.addRow(new Object[] { false, proj.getName(), proj.getNbr_collaborators(),
								proj.getCreated_at(), proj.getLast_modif(), proj.getProjectId() });
					}

					if (row != -1) {
						Info1Group.setVisible(true);
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

		// LINK infos d'un projet cliqué 2 fois à l'onglet Projet
		projectsTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					Project_ project = listProject_.get(row);
					selectedProject = project;

					for (Project_ p : PageProjet.listProject_) {
						SwingUtilities.updateComponentTreeUI(PageProjet.framePageProjet);
						PageProjet.selectedProject = p;
						if (project.getProjectId() == p.getProjectId()) {
							int j = PageProjet.listProject_.indexOf(p);
							System.out.println(j);
							PageProjet.projectsTable.setRowSelectionInterval(j, j);
							PageAccueil.onglets.setSelectedIndex(2);

							PageProjet.jTextArea2.setText(p.getDescription());
							PageProjet.projectName.setText(p.getName());
							GitLabApi gitlabapi = Authentification.connectGitlabApi();
							try {
								String groupe[] = gitlabapi.getProjectApi().getProject(selectedProject.getProjectId())
										.getNameWithNamespace().split("/");
								PageProjet.groupBelong.setText(groupe[0]);
							} catch (GitLabApiException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int limit = PageProjet.model2.getRowCount();
							for (int i = 0; i < limit; i++) {
								PageProjet.model2.removeRow(0);
							}
							for (int i = 0; i < p.getMembers().size(); i++) {
								PageProjet.model2.addRow(new Object[] { false, p.getMembers().get(i).getUsername(),
										p.getMembers().get(i).getAccessLevel().name() });
							}
							if (row != -1) {
								PageProjet.jPanel4.setVisible(true);
							}
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

		framePageGroupe.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		framePageGroupe.setTitle("Groups");
		framePageGroupe.setBackground(new java.awt.Color(255, 255, 255));
		framePageGroupe.setPreferredSize(new java.awt.Dimension(1200, 560));

		jPanel2.setBackground(new java.awt.Color(0, 0, 51));
		jPanel2.setPreferredSize(new java.awt.Dimension(650, 150));

		title.setEditable(false);
		title.setBackground(new java.awt.Color(0, 0, 51));
		title.setFont(new java.awt.Font("Times New Roman", 1, 37)); // NOI18N
		title.setForeground(new java.awt.Color(255, 255, 255));
		title.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		title.setText("Groups");
		title.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				titleActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(709, 709, 709)
						.addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(43, 43, 43)
						.addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(58, Short.MAX_VALUE)));

		createGroupButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		createGroupButton.setText("Create a group");
		createGroupButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createGroupButtonActionPerformed(evt);
			}
		});

		jPanel1.setBackground(new java.awt.Color(102, 102, 102));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 16, Short.MAX_VALUE));

		jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		directions.setEditable(false);
		directions.setBackground(new java.awt.Color(240, 240, 240));
		directions.setColumns(20);
		directions.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		directions.setRows(5);
		directions.setText("double-click the row to see the\n" + "information of a group\n"
				+ "-you can add administrators using\n" + "their usernames\n " + "-you can add new projects\n"
				+ "  double-click the project row to see it's info\n\n" + "want to delete a group, administrator or \n"
				+ "project ?\n" + "select the instance you want to delete\n"
				+ "then click the delete button (you can only\n" + "delete one at a time)\n\n"
				+ "want to create a new group?\n" + "click the create button. 4 step way:\n"
				+ "1. insert a group name \n" + "2. import members with a .csv \n" + "3. import projects with a .csv\n"
				+ "4. a sum up of the created group\n");

		jScrollPane3.setViewportView(directions);

		Info1Group.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jTextFieldAdmin.setEditable(false);
		jTextFieldAdmin.setBackground(new java.awt.Color(240, 240, 240));
		jTextFieldAdmin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
		jTextFieldAdmin.setText("Administrators");
		jTextFieldAdmin.setBorder(null);

		addAdminButton.setText("Add admin");
		addAdminButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAdminButtonActionPerformed(evt);
			}
		});

		importAdminButton.setText("Import admin");
		importAdminButton.setVisible(false);

		deleteAdminButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
		deleteAdminButton.setText("delete");
		deleteAdminButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteAdminButtonActionPerformed(evt, adminmodel, selectedGroup);
			}
		});

		javax.swing.GroupLayout jPaneladminLayout = new javax.swing.GroupLayout(jPaneladmin);
		jPaneladmin.setLayout(jPaneladminLayout);
		jPaneladminLayout.setHorizontalGroup(jPaneladminLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPaneladminLayout.createSequentialGroup().addContainerGap()
						.addGroup(jPaneladminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPaneladminLayout.createSequentialGroup().addComponent(deleteAdminButton)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(importAdminButton).addGap(68, 68, 68)
												.addComponent(addAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE,
														102, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(jPaneladminLayout.createSequentialGroup()
										.addComponent(jTextFieldAdmin, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPaneladminLayout.setVerticalGroup(jPaneladminLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPaneladminLayout.createSequentialGroup().addGroup(jPaneladminLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPaneladminLayout.createSequentialGroup()
								.addComponent(jTextFieldAdmin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(deleteAdminButton).addGap(0, 0, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jPaneladminLayout.createSequentialGroup().addGap(0, 143, Short.MAX_VALUE)
										.addGroup(jPaneladminLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(importAdminButton).addComponent(addAdminButton))))
						.addContainerGap()));

		jTextFieldProjects.setEditable(false);
		jTextFieldProjects.setBackground(new java.awt.Color(240, 240, 240));
		jTextFieldProjects.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
		jTextFieldProjects.setText("Projects");
		jTextFieldProjects.setBorder(null);

		importProjectButton.setText("Import project");
		importProjectButton.setVisible(false);
		importProjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				importProjectButtonActionPerformed(evt);
			}
		});

		addProjectButton.setText("Add project");
		addProjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addProjectButtonActionPerformed(evt);
			}
		});

		deleteProjectButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
		deleteProjectButton.setText("delete");
		deleteProjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteProjectButtonActionPerformed(evt, projectmodel, selectedGroup);
			}
		});

		javax.swing.GroupLayout jPanelprojectsLayout = new javax.swing.GroupLayout(jPanelprojects);
		jPanelprojects.setLayout(jPanelprojectsLayout);
		jPanelprojectsLayout.setHorizontalGroup(jPanelprojectsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelprojectsLayout.createSequentialGroup().addContainerGap().addGroup(jPanelprojectsLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelprojectsLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(importProjectButton).addGap(60, 60, 60).addComponent(addProjectButton,
										javax.swing.GroupLayout.PREFERRED_SIZE, 103,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
						.addGroup(jPanelprojectsLayout.createSequentialGroup().addGroup(jPanelprojectsLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jTextFieldProjects, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(deleteProjectButton)).addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanelprojectsLayout
				.setVerticalGroup(jPanelprojectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelprojectsLayout.createSequentialGroup()
								.addComponent(jTextFieldProjects, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(deleteProjectButton).addGap(0, 34, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelprojectsLayout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(jPanelprojectsLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(importProjectButton).addComponent(addProjectButton))
										.addContainerGap()));

		jPanel6.setBackground(new java.awt.Color(0, 0, 51));

		groupName.setEditable(false);
		groupName.setBackground(new java.awt.Color(0, 0, 51));
		groupName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
		groupName.setForeground(new java.awt.Color(255, 255, 255));
		groupName.setBorder(null);
		groupName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		// groupName.setText("*Group name");

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(groupName,
								javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(50, 50, 50)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addGap(19, 19, 19)
						.addComponent(groupName, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE)));

		javax.swing.GroupLayout Info1GroupLayout = new javax.swing.GroupLayout(Info1Group);
		Info1Group.setLayout(Info1GroupLayout);
		Info1GroupLayout.setHorizontalGroup(Info1GroupLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(Info1GroupLayout.createSequentialGroup().addContainerGap()
						.addGroup(Info1GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanelprojects, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPaneladmin, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		Info1GroupLayout
				.setVerticalGroup(Info1GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(Info1GroupLayout.createSequentialGroup()
								.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(32, 32, 32)
								.addComponent(jPaneladmin, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(27, 27, 27)
								.addComponent(jPanelprojects, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(39, Short.MAX_VALUE)));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel1.setText("DIRECTIONS FOR THE USER :)");

		deleteGroupButton.setText("delete");
		deleteGroupButton.setFont(new java.awt.Font("Tahoma", 0, 10));
		deleteGroupButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteGroupButtonActionPerformed(evt, model);
			}
		});

		importGroupButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
		importGroupButton.setText("Import group");
		importGroupButton.setVisible(false);

		importGroupProjectButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
		importGroupProjectButton.setText("Import group + projects");
		importGroupProjectButton.setVisible(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(framePageGroupe.getContentPane());
		framePageGroupe.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(50, 50, 50)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(
										jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(80, 80, 80)
						.addGroup(layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 519,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(deleteGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(49, 49, 49)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														importGroupProjectButton,
														javax.swing.GroupLayout.PREFERRED_SIZE, 134,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup()
														.addComponent(importGroupButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 119,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(250, 250, 250).addComponent(createGroupButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 130,
																javax.swing.GroupLayout.PREFERRED_SIZE)))))
						.addGap(52, 52, 52)
						.addComponent(Info1Group, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(100, 100, 100))
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1542, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(19, 19, 19)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 394,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 422,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(deleteGroupButton))
										.addGroup(
												layout.createSequentialGroup().addGap(33, 33, 33).addComponent(
														createGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(importGroupButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 36,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(importGroupProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(Info1Group, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(45, Short.MAX_VALUE)));

		framePageGroupe.pack();
	}// </editor-fold>

	private void createGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {
		CreationGroupe frameText = new CreationGroupe();
		frameText.setVisible(true);
		frameText.pack();
		frameText.setLocationRelativeTo(null);
		frameText.setDefaultCloseOperation(AjouterProjet.DISPOSE_ON_CLOSE);
	}

	private void titleActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void importProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void addProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		CreationProjet frameText = new CreationProjet(2);
		frameText.setVisible(true);
		frameText.pack();
		frameText.setLocationRelativeTo(null);
		frameText.setDefaultCloseOperation(AjouterProjet.DISPOSE_ON_CLOSE);
	}

	private void addAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {
		AjouterMembre frameText = new AjouterMembre(null, selectedGroup, 1);
		// AjouterMembre.jTextFieldAdmin.setText("Add Administrator");
		frameText.setVisible(true);
		frameText.pack();
		frameText.setLocationRelativeTo(null);
		frameText.setDefaultCloseOperation(AjouterMembre.DISPOSE_ON_CLOSE);
	}

	private void deleteGroupButtonActionPerformed(java.awt.event.ActionEvent evt, Tableau model) {
		if (getGroupsTable().getSelectedRow() != -1 && Tableau.aValue == true) {
			int opt = JOptionPane.showConfirmDialog(null, "Are you sure to delete this group", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (opt == 0) {
				try {
					GitLabApi gitLabApi = Authentification.connectGitlabApi();
					for (Group_ group : listGroup_) {
						if (group.getName().compareTo(model.getValueAt(getGroupsTable().getSelectedRow(), 1).toString())==0) {
							List <Project_> projs = new ArrayList<Project_>();
							// enleve projets dans la page projet
							for (Project p : group.getProjects()) {
								for (Project_ proj : PageProjet.listProject_) { 
									if (proj.getProjectId() == p.getId()) {
										int j = PageProjet.listProject_.indexOf(proj);
										PageProjet.model.removeRow(j);
										PageStats.model.removeRow(j);
										projs.add(proj);
									}
								}
							}
							for (Project_ p:projs) {
								PageProjet.listProject_.remove(p);
								PageStats.listProject_.remove(p);
							}
							//on enleve le groupe
							gitLabApi.getGroupApi().deleteGroup(group.getGroupId());
							model.removeRow(getGroupsTable().getSelectedRow());
							listGroup_.remove(group);
							// SwingUtilities.updateComponentTreeUI(framePageGroupe);
							JOptionPane.showMessageDialog(null, "Deleted succesfully");
						}
					}
				} catch (GitLabApiException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Cannot delete this group, you don't have the permission");
					e.printStackTrace();
				}

			}
		}
	}

	private void deleteAdminButtonActionPerformed(java.awt.event.ActionEvent evt, Tableau model, Group_ group) {
		if (adminsTable.getSelectedRow() != -1 && Tableau.aValue == true) {
			int opt = JOptionPane.showConfirmDialog(null, "Are you sure to delete this administrator?", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (opt == 0) {
				try {
					GitLabApi gitLabApi = Authentification.connectGitlabApi();
					for (org.gitlab4j.api.models.Member member : group.getMembers()) {
						if (member.getUsername() == model.getValueAt(adminsTable.getSelectedRow(), 1)) {
							// System.out.println(model.getValueAt(adminsTable.getSelectedRow(), 1));
							System.out.println(member.getUsername());
							// System.out.println(member.getId());
							gitLabApi.getGroupApi().removeMember(group.getGroupId(), member.getId());
							group.getMembers().remove(group.getMembers().indexOf(member));
							adminmodel.removeRow(adminsTable.getSelectedRow());
							selectedGroup.setNbr_collaborators(selectedGroup.getNbr_collaborators() - 1);
							SwingUtilities.updateComponentTreeUI(framePageGroupe);
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

	private void deleteProjectButtonActionPerformed(java.awt.event.ActionEvent evt, Tableau model, Group_ group) {
		if (projectsTable.getSelectedRow() != -1 && Tableau.aValue == true) {
			int opt = JOptionPane.showConfirmDialog(null, "Are you sure to delete this project?", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (opt == 0) {
				try {
					GitLabApi gitLabApi = Authentification.connectGitlabApi();
					for (Project_ proj : listProject_) {
						if (proj.getName() == model.getValueAt(projectsTable.getSelectedRow(), 1)) {
							// System.out.println(model.getValueAt(projectsTable.getSelectedRow(), 1));
							System.out.println(proj.getName());
							// System.out.println(proj.getProjectId());
							gitLabApi.getProjectApi().deleteProject(proj.getProjectId());
							model.removeRow(projectsTable.getSelectedRow());
							selectedGroup.setNbr_projects(selectedGroup.getNbr_projects() - 1);
							// enleve le projet de la page projet
							for (Project_ p : PageProjet.listProject_) {
								if (proj.getProjectId() == p.getProjectId()) {
									int j = PageProjet.listProject_.indexOf(p);
									PageProjet.model.removeRow(j);
									PageProjet.listProject_.remove(p);
								}
							}
							PageProjet.listProject_.remove(proj);
							// fin
							SwingUtilities.updateComponentTreeUI(framePageGroupe);
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

	public static void addRow(Group grp) {

		int grpId;
		String name;
		List<Project> listProjects;
		List<Member> listMembers;
		int nbr_collaborators;
		int nbr_projects;

		GitLabApi gitLabApi;
		try {
			gitLabApi = Authentification.connectGitlabApi();
			name = grp.getName();
			grpId = grp.getId();

			listProjects = gitLabApi.getGroupApi().getProjects(grpId);
			listMembers = gitLabApi.getGroupApi().getMembers(grpId);

			nbr_collaborators = listMembers.size();
			nbr_projects = listProjects.size();

			Group_ group = new Group_(grpId, name, listProjects, listMembers, nbr_collaborators, nbr_projects);
			model.addRow(new Object[] { false, group.getName(), nbr_collaborators, nbr_projects });
			listGroup_.add(group);
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

			projectmodel.addRow(
					new Object[] { false, proj.getName(), nbr_collaborators, created_at, last_modif, proj.getId() });
			Project_ project = new Project_(proj.getId(), proj.getName(), created_at, last_modif, nbr_collaborators,
					nbr_branches, proj.getDescription(), collaborators);
			System.out.println(project.getName());
			listProject_.add(project);
			PageProjet.listProject_.add(project);
			PageProjet.model.addRow(new Object[] { false, proj.getName(), project.getNbr_collaborators(),
					project.getCreated_at(), project.getLast_modif(), project.getProjectId() });
			selectedGroup.getProjects().add(proj);

		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(PageGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(PageGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(PageGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(PageGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				framePageGroupe.setVisible(true);
			}
		});
	}

	public static javax.swing.JTable getGroupsTable() {
		return groupsTable;
	}

	public static void setGroupsTable(javax.swing.JTable groupsTable) {
		PageGroupe.groupsTable = groupsTable;
	}

	// Variables declaration - do not modify
	private javax.swing.JPanel Info1Group;
	private javax.swing.JButton addAdminButton;
	private javax.swing.JButton addProjectButton;
	private javax.swing.JTable adminsTable;
	private javax.swing.JButton createGroupButton;
	private javax.swing.JButton deleteAdminButton;
	private javax.swing.JButton deleteGroupButton;
	private javax.swing.JButton deleteProjectButton;
	private javax.swing.JTextArea directions;
	private javax.swing.JTextField groupName;
	private static javax.swing.JTable groupsTable;
	private javax.swing.JButton importAdminButton;
	private javax.swing.JButton importGroupButton;
	private javax.swing.JButton importGroupProjectButton;
	private javax.swing.JButton importProjectButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPaneladmin;
	private javax.swing.JPanel jPanelprojects;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JSpinner jSpinner1;
	private javax.swing.JTextField jTextFieldAdmin;
	private javax.swing.JTextField jTextFieldProjects;
	private javax.swing.JTable projectsTable;
	private javax.swing.JTextField title;
	// End of variables declaration
}