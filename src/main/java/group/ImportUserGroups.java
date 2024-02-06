package group;

import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;

import authentification.Authentification;

/**
 * 
 * @author Diane
 *
 */

public class ImportUserGroups {

	
	public static List<Group_> importGroups() {
		
		List<Group_> listGroups = new ArrayList<Group_>();	
		
		try {
			GitLabApi gitLabApi ;
			//Test
			//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
			//gitLabApi = GitLabApi.oauth2Login("https://gitlab.com", "roussinathena", "15022000");
			gitLabApi = Authentification.connectGitlabApi();
			
			// Get the list of projects your account has access to
			//List<Project> projects = gitLabApi.getProjectApi().getProjects();
			
			List<Group> groups = gitLabApi.getGroupApi().getGroups();
			
			
			
		     // <p>Get a list of groups. (As user: my groups, as admin: all groups)</p>

			
			Group_ group;
	
			int grpId;
			String name;
			List<Project> listProjects;
			List<Member> listMembers;
			int nbr_collaborators;
			int nbr_projects;
	
			for(Group grp : groups) {
				name = grp.getName();
				grpId = grp.getId();
				
				listProjects = gitLabApi.getGroupApi().getProjects(grpId);
				listMembers = gitLabApi.getGroupApi().getMembers(grpId);
				
				nbr_collaborators=listMembers.size();
				nbr_projects=listProjects.size();

				group = new Group_(grpId, name, listProjects, listMembers, nbr_collaborators,nbr_projects);
				listGroups.add(group);
				
				}
			}
			catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	
		return listGroups;
	
		}
	
}
