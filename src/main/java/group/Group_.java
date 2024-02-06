package group;

import java.util.List;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;

/**
 * 
 * @author Diane
 *
 */

public class Group_  extends Group{

	int groupId;
	String name;
	List<Project> projects;
	int nbr_collaborators;
	int nbr_projects;
	List<org.gitlab4j.api.models.Member> members;
	
	public Group_(int groupId, String name, List<Project> projects, List<org.gitlab4j.api.models.Member> members,int nbr_collaborators,int nbr_projects) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.members=members;
		this.nbr_collaborators = nbr_collaborators;
		this.projects=projects;
		this.nbr_projects = nbr_projects;
	}
	
	public int getNbr_projects() {
		return nbr_projects;
	}

	public void setNbr_projects(int nbr_projects) {
		this.nbr_projects = nbr_projects;
	}

	public int getGroupId() {
		return groupId;
	}
	public int getNbr_collaborators() {
		return nbr_collaborators;
	}

	public void setNbr_collaborators(int nbr_collaborators) {
		this.nbr_collaborators = nbr_collaborators;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<org.gitlab4j.api.models.Member> getMembers() {
		return members;
	}

	public void setMembers(List<org.gitlab4j.api.models.Member> members) {
		this.members = members;
	}

}
