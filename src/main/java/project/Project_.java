package project;

import java.util.ArrayList;
import java.util.List;

import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.User;

import member.Member_;

/**
 * 
 * @author Diane
 *
 */

// Classe Projet peremt de sélectionner les informations voulu depuis l'API et de les stocker.

public class Project_ {
	
	int projectId;
	String name;
	String created_at;
	String last_modif;
	String description;
	int nbr_collaborators;
	int nbr_branches;
	List<org.gitlab4j.api.models.Member> members;
	List<org.gitlab4j.api.models.User> users;
	List<String> listMembersName;
	List<Commit> listCommits;
	
	public Project_(int projectId, String name, String created_at, String last_modif, int nbr_collaborators, int nbr_branches, String description, List<org.gitlab4j.api.models.Member> members) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.created_at = created_at;
		this.last_modif = last_modif;
		this.nbr_collaborators = nbr_collaborators;
		this.nbr_branches = nbr_branches;
		this.description=description;
		this.members = members;
		//this.listCommits = listCommits;
		this.listMembersName = new ArrayList<String>();
		this.listCommits = new ArrayList<Commit>();
	}
	
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated_at() {
		return created_at;
	}
	public List<org.gitlab4j.api.models.Member> getMembers(){
		return this.members;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getLast_modif() {
		return last_modif;
	}
	public void setLast_modif(String last_modif) {
		this.last_modif = last_modif;
	}
	public int getNbr_collaborators() {
		return nbr_collaborators;
	}
	public void setNbr_collaborators(int nbr_collaborators) {
		this.nbr_collaborators = nbr_collaborators;
	}
	public int getNbr_branches() {
		return nbr_branches;
	}
	public void setNbr_branches(int nbr_branches) {
		this.nbr_branches = nbr_branches;
	}



	public List<String> getListMembersName() {
		return listMembersName;
	}


	public void setListMembersName(List<String> listMembersName) {
		this.listMembersName = listMembersName;
	}



	public List<Commit> getListCommits() {
		return listCommits;
	}

	public void setListCommits(List<Commit> listCommits) {
		this.listCommits = listCommits;
	}

	
	

}

