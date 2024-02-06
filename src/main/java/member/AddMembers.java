package member;

import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import authentification.Authentification;
import project.Project_;

public class AddMembers {
	
	static GitLabApi gitLabApi;

	public static void AddMemberToProject(String member, int projectID, int Level) {
		
		
		try {
			List<User> users= gitLabApi.getUserApi().findUsers(member);
			
			//Test
			// gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
			gitLabApi = Authentification.connectGitlabApi();
							
			// Add member to the project
			gitLabApi.getProjectApi().updateMember(projectID,users.get(0).getId(),Level);
			// Access Level  INVALID(-1), NONE(0), MINIMAL_ACCESS(5), GUEST(10), REPORTER(20), DEVELOPER(30), @Deprecated MASTER(40), MAINTAINER(40), OWNER(50), ADMIN(60);
			
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void AddMemberToGroup(String member, int grpID) {
		
		try {
			List<User> users= gitLabApi.getUserApi().findUsers(member);

			//Test
			// gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
			gitLabApi = Authentification.connectGitlabApi();
		
			// Add member to the group
			gitLabApi.getGroupApi().addMember(grpID, users.get(0).getId(),30);
	
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
