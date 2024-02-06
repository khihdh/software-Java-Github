package project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.TreeItem;

import affichage.FenetreAuth;
import authentification.Authentification;

public class ImportFiles {
	
	public static List<String> importFilesOfProject(int projectId) {

		List<String> listFiles = new ArrayList<String>();
		String n;
		
		try {
			GitLabApi gitLabApi;

			//Test
			//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
			//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "roussin.athena", "15022000");
			
			gitLabApi = Authentification.connectGitlabApi();
			
			Project project = gitLabApi.getProjectApi().getProject(projectId);
			System.out.println(project.getName());

			
			// Get the list of projects your account has access to
			//List<Project> projects = gitLabApi.getProjectApi().getProjects();
			String defaultbranch =project.getDefaultBranch();
			
			for(int i=0; i < gitLabApi.getRepositoryApi().getTree(projectId,"/",defaultbranch).size(); i++) { 
				  n =  gitLabApi.getRepositoryApi().getTree(projectId,"/",defaultbranch).get(i).getName();
					listFiles.add(n);
				}
			
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		if (listFiles.size()==0)
		{
			listFiles.add(" ");
		}
		return listFiles;
	}
//	public static void main(String[] args) { 
//		System.out.println(importFilesOfProject(1130));
//	}
	
}


