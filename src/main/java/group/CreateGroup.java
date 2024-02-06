/**
 * 
 */
package group;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import affichage.PageProjet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

import authentification.Authentification;
import project.CreateProject;
import projetFichierCsv.ReadCSV;

/**
 * @author Jérémy Castex
 *
 */
public class CreateGroup {
	Group group;
	GitLabApi gitLabApi;
	AccessLevel accessLevel;
	org.gitlab4j.api.models.Member member;
	
	public CreateGroup() {
		member= new org.gitlab4j.api.models.Member();
		
		gitLabApi = Authentification.connectGitlabApi();

		this.group=null;
	}

	public boolean createGroup(String name) {
		try {
			Group groupspec= new Group().withName(name).withPath(name);
			this.group = this.gitLabApi.getGroupApi().addGroup(groupspec);
			return true;
		}catch (GitLabApiException e){
			e.printStackTrace();
			return false;
		}
	}


	public boolean addAdminCSV(String file) {
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
				CreateProject.setAccessLevelToMember(member,test.get("accesslevel").get(i));
				this.gitLabApi.getGroupApi().addMember(this.group, member.getId(),member.getAccessLevel());
			}
			return true;	
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean addProjects(String file) {
		ReadCSV reader = new ReadCSV(new File(file));
		HashMap<String, ArrayList<String>> test = reader.readCSV();
		System.out.println(test.toString());
		Project projectSpec = null;
		List<Member> listMembers = null;
		String project="";
		try {
			listMembers = gitLabApi.getGroupApi().getMembers(group.getId());
		} catch (GitLabApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for (int i=0; i<test.get("Project").size();i++) {
				boolean isnotMember = true;
				System.out.println(test.get("Project").get(i));
				System.out.println(project);
				if (test.get("Project").get(i).compareTo(project) != 0) {
					System.out.println("pk");
					project=test.get("Project").get(i);
					projectSpec = new Project().withName(test.get("Project").get(i))
							.withIssuesEnabled(true).withMergeRequestsEnabled(true).withWikiEnabled(true)
							.withSnippetsEnabled(true).withPublic(true);
					projectSpec = this.gitLabApi.getProjectApi().createProject(group.getId(),projectSpec);
					PageProjet.addRow(projectSpec);
				}
				List<User> user = gitLabApi.getUserApi().findUsers(test.get("username/email").get(i));
				if (projectSpec != null) {
					for (int j=0; j<listMembers.size();j++) {
						if (user.size()==0) {
							return false;
						}
						if (user.get(0).getId().byteValue() == listMembers.get(j).getId().byteValue()) {
							System.out.println(listMembers.get(j).getId());
							System.out.println(user.get(0).getId());
							isnotMember=false;
						}
					}
					if (isnotMember) {
						member.setName(user.get(0).getName());
						member.setUsername(user.get(0).getUsername());
						member.setEmail(user.get(0).getEmail());
						member.setId(user.get(0).getId());
						CreateProject.setAccessLevelToMember(member,test.get("accesslevel").get(i));
						this.gitLabApi.getProjectApi().addMember(projectSpec, member.getId(),AccessLevel.DEVELOPER);
					}
				}
			}
			return true;	
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Group getGroup() {return group;}
}