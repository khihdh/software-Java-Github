package member;

import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Member;

import authentification.Authentification;

/**
 * 
 * @author Diane
 *
 */

public class ImportMembers {
	
	/*public static void main(String[] args) {
	ImportMembers list = new ImportMembers();
	List<Member_> listMembers_;
	listMembers_ = list.importMembersOfProject(1);
	for(Member_ elem: listMembers_)
	   {
	   	 System.out.println (elem.getName());
	   } 
	}*/

	public static List<Member_> importMembersOfProject(int projectId){
	
		List<Member_> listMembers = new ArrayList<Member_>(); 
	
		try {
			GitLabApi gitLabApi;

		//Test
		//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
		//gitLabApi = GitLabApi.oauth2Login("https://gitlab.com", "roussinathena", "15022000");
		
			gitLabApi = Authentification.connectGitlabApi();
		
		
		// Get the list of projects your account has access to
		//List<Project> projects = gitLabApi.getProjectApi().getProjects();
		
		List<Member> members = gitLabApi.getProjectApi().getMembers(projectId);

		Member_ member_;

		String name;
		int memberId;
		
		for(Member member : members) {
			name = member.getName();
			memberId = member.getId();
			
			member_ = new Member_(memberId,name);
			listMembers.add(member_);
		}

	} catch (GitLabApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return listMembers;

	}

public static List<Member_> importMembersOfGroup(int groupId) {
	
	List<Member_> listMembers = new ArrayList<Member_>(); 
	
	try {
		GitLabApi gitLabApi;

		//Test
		//gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "peres.diane", "Telecom 123");
		//gitLabApi = GitLabApi.oauth2Login("https://gitlab.com", "roussinathena", "15022000");
		
		gitLabApi = Authentification.connectGitlabApi();
		
		// Get the list of projects your account has access to
		//List<Project> projects = gitLabApi.getProjectApi().getProjects();
		
		List<Member> members = gitLabApi.getGroupApi().getMembers(groupId);

		Member_ member_;

		String name;
		int memberId;
		
		for(Member member : members) {
			name = member.getName();
			memberId = member.getId();
			
			member_ = new Member_(memberId,name);
			listMembers.add(member_);
		}

	} catch (GitLabApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return listMembers;
}
	
}
