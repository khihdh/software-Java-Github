/**
 *
 */
package project;

import java.io.File;
import java.lang.reflect.Member;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Owner;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import affichage.CreationProjet;
import authentification.Authentification;
import member.Member_;
import project.Project_;
import projetFichierCsv.ReadCSV;


/**
 * Classe qui peremt de cr�er un projet, ajouter des membres et suprrimer un projet. Elle a comme
 * membre priv� un projet et la connexion gitlabapi
 *
 * @author jeremy Castex
 *
 */
public class CreateProject {

	Project project;
	GitLabApi gitLabApi;
	AccessLevel accessLevel;
	org.gitlab4j.api.models.Member member;

	public CreateProject( ) {
		member= new org.gitlab4j.api.models.Member();
		
		gitLabApi = Authentification.connectGitlabApi();
		this.project=null;
	}
	


	/**
	 * @param name
	 * @param description
	 * @param owner
	 * @return boolean
	 */
	public boolean createProject(String name, String description, String owner) {
		try {
			Project projectSpec = new Project().withName(name).withDescription(description)
					.withIssuesEnabled(true).withMergeRequestsEnabled(true).withWikiEnabled(true)
					.withSnippetsEnabled(true).withPublic(true);
			Owner projectOwner = new Owner().withUsername(owner);
			projectSpec.setOwner(projectOwner);		
			this.project = this.gitLabApi.getProjectApi().createProject(projectSpec);
			return true;
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean createProjectInGroup(String name, String description, String owner, Group group) {
		try {
			Project projectSpec = new Project().withName(name).withDescription(description)
					.withIssuesEnabled(true).withMergeRequestsEnabled(true).withWikiEnabled(true)
					.withSnippetsEnabled(true).withPublic(true);
			Owner projectOwner = new Owner().withUsername(owner);
			projectSpec.setOwner(projectOwner);		
			this.project = this.gitLabApi.getProjectApi().createProject(group.getId(),projectSpec);
			return true;
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	public static void setAccessLevelToMember(org.gitlab4j.api.models.Member member, String accessLevel_csv)  {
		 switch (accessLevel_csv) {
		 case "GUEST":
				 member.setAccessLevel(AccessLevel.forValue(10));
			 break;
		 case "REPORTER":
			 member.setAccessLevel(AccessLevel.forValue(20));
			 break;
		 case "DEVELOPER":
			 member.setAccessLevel(AccessLevel.forValue(30));
			 break;
		 case "MAINTAINER":
			 member.setAccessLevel(AccessLevel.forValue(40));
			 break;
		 case "OWNER":
			 member.setAccessLevel(AccessLevel.forValue(50));
			 break;
		 default:
			 member.setAccessLevel(AccessLevel.forValue(10));
		 }
		 }
	
	
	

	public boolean addMemberCSV(String file) {
		ReadCSV reader = new ReadCSV(new File(file));
		HashMap<String, ArrayList<String>> test = reader.readCSV();
		System.out.println(test.toString());
		
		try {
			for (int i=0; i<test.get("mail/username").size();i++) {
				List<User> user = gitLabApi.getUserApi().findUsers(test.get("mail/username").get(i));
				member.setName(user.get(0).getName());
				member.setUsername(user.get(0).getUsername());
				member.setEmail(user.get(0).getEmail());
				member.setId(user.get(0).getId());
				setAccessLevelToMember(member,test.get("accesslevel").get(i));
				this.gitLabApi.getProjectApi().addMember(this.project, member.getId(),member.getAccessLevel());
				System.out.println(member.getAccessLevel());
			}
			return true;	
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}
	
	public void addMember(String user) {
		try {
			List<User> user1=gitLabApi.getUserApi().findUsers(user);
			System.out.println("ok");
			this.gitLabApi.getProjectApi().addMember(this.project, user1.get(0).getId(),1);
			
			
			
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//deleteProject();
		}

	}
	
	public String getName() {
		return this.project.getName();
	}
	public String getDescription() {
		return this.project.getDescription();
	}
	public Project getProject() {
		return this.project;
	}
	public List<org.gitlab4j.api.models.Member> getMembers() {
		List<org.gitlab4j.api.models.Member> Memberfalse = new ArrayList<org.gitlab4j.api.models.Member>();
		try {
			return gitLabApi.getProjectApi().getMembers(this.project);
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Memberfalse;
		}
	}
	public void deleteProject() {
		try {
			this.gitLabApi.getProjectApi().deleteProject(this.project);
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteProject(int projectD) {
		try {
			this.gitLabApi.getProjectApi().deleteProject(projectD);
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) { 
		CreateProject project = new CreateProject(); 
	}

}
