package project;

import org.gitlab4j.api.models.Project;

import authentification.Authentification;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Group;

public class AddProject {
	
	public static void AddProject(Group grp, Project proj) {
		try {
			GitLabApi gitLabApi;
			
			//Test
			//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
			gitLabApi = Authentification.connectGitlabApi();			
			// Transfer project to the group
			gitLabApi.getGroupApi().transferProject(grp.getId(),proj.getId());
			
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
