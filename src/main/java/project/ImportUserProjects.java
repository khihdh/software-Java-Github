package project;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectFilter;
import org.gitlab4j.api.models.ProjectStatistics;

import authentification.Authentification;

/**
 * 
 * @author Diane
 *
 */

public class ImportUserProjects {
	
	GitLabApi gitLabApi = Authentification.connectGitlabApi();
	
	/*public static void main(String[] args) {
		ImportUserProjects list = new ImportUserProjects();
		List<Project_> listProjet_;
		listProjet_ = list.importProjects();
		for(Project_ elem: listProjet_)
		   {
		   	 System.out.println (elem.getName());
		   } 
	}*/
	
	public static List<Project_> importProjects() {
		
		List<Project_> listProjects = new ArrayList<Project_>();	
		
		try {
			GitLabApi gitLabApi;

			//Test
			//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
			//gitLabApi = GitLabApi.oauth2Login("https://gitlab.com", "roussinathena", "15022000")
			
			gitLabApi = Authentification.connectGitlabApi();
			
			// Get the list of projects your account has access to
			//List<Project> projects = gitLabApi.getProjectApi().getProjects();
			
			List<Project> projects = gitLabApi.getProjectApi().getMemberProjects();
			
			Project_ project;

			List<Branch> branches;
			List<Member> collaborators;
			int projectId;
			String name;
			String created_at;
			String last_modif;
			int nbr_collaborators;
			int nbr_branches;
			//List<CommitsApi> listCommits;

			for(Project proj : projects) {
				name = proj.getName();
				
				
				StringBuffer stringBuffer = new StringBuffer();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				simpleDateFormat.format(proj.getCreatedAt(), stringBuffer, new FieldPosition(0));
				created_at = stringBuffer.toString();
				
				StringBuffer stringBuffer2 = new StringBuffer();
				SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				simpleDateFormat2.format(proj.getLastActivityAt(), stringBuffer2, new FieldPosition(0));
				last_modif = stringBuffer2.toString();
				
				collaborators = gitLabApi.getProjectApi().getAllMembers(proj.getId());
				nbr_collaborators = collaborators.size();
				
				//branches = gitLabApi.getRepositoryApi().getBranches(proj.getId());
				nbr_branches = 0;
				
				//listCommits=proj.getClass();
				
				project = new Project_(proj.getId(), name, created_at, last_modif, nbr_collaborators, nbr_branches,proj.getDescription(),collaborators);
				System.out.println(project.getName());
				for (int i=0; i<project.getMembers().size();i++) {
					System.out.println(project.getMembers().get(i).getName());
				}
				listProjects.add(project);
				}
			}
			catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}

		return listProjects;

		}
	
public static List<Project_> importProjectsOfGroup(int groupId) {
		
		List<Project_> listProjectsOfGroup = new ArrayList<Project_>();	
		
		try {
			GitLabApi gitLabApi;

			//Test
			 //gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "roussin.athena", "15022000");
			//gitLabApi = GitLabApi.oauth2Login("https://gitlab.com", "roussinathena", "15022000")
			
			gitLabApi = Authentification.connectGitlabApi();
			
			// Get the list of projects your account has access to
			//List<Project> projects = gitLabApi.getProjectApi().getProjects();
			
			List<Project> projectsOfGroup = gitLabApi.getGroupApi().getProjects(groupId);
			
			
			Project_ project;

			List<Branch> branches;
			List<Member> collaborators;
			int projectId;
			String name;
			String created_at;
			String last_modif;
			int nbr_collaborators;
			int nbr_branches;
			String description;
			//List<TreeItem> files;
			
			//List<CommitsApi> listCommits;

			for(Project proj : projectsOfGroup) {
				name = proj.getName();
				
				projectId = proj.getId();
				
				StringBuffer stringBuffer = new StringBuffer();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				simpleDateFormat.format(proj.getCreatedAt(), stringBuffer, new FieldPosition(0));
				created_at = stringBuffer.toString();
				
				StringBuffer stringBuffer2 = new StringBuffer();
				SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				simpleDateFormat2.format(proj.getLastActivityAt(), stringBuffer2, new FieldPosition(0));
				last_modif = stringBuffer2.toString();
				
				collaborators = gitLabApi.getProjectApi().getAllMembers(proj.getId());
				nbr_collaborators = collaborators.size();
				
				branches = gitLabApi.getRepositoryApi().getBranches(proj.getId());
				nbr_branches = branches.size();

				description=proj.getDescription();
				
				project = new Project_(proj.getId(), name, created_at, last_modif, nbr_collaborators, nbr_branches,proj.getDescription(),collaborators);
				listProjectsOfGroup.add(project);
				
				}
			}
			catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}

		return listProjectsOfGroup;

		}

	public Map <String,Object> getStatistics(int id){
		Map<String, Object>map=new HashMap<String,Object>();
		ProjectStatistics p;
		List<Project>l;
		Project myproject=null;
		try {
			ProjectFilter filter = new ProjectFilter().withStatistics(true);
			
			l=gitLabApi.getProjectApi().getProjects(filter);
			
			for(Project project :l ) {
				if(project.getId()==id)
					myproject=project;
			}
			if(myproject != null) {
				p=myproject.getStatistics();
				map.put("nombredecommits", p.getCommitCount());
				map.put("branches", gitLabApi.getRepositoryApi().getBranches(id));
				map.put("dernière activité",myproject.getLastActivityAt());
				map.put("conflits résolus",myproject.getOpenIssuesCount() );
			}
			
		}  catch(GitLabApiException e) {
			e.printStackTrace();
		}
		return map;
	}
		
}


