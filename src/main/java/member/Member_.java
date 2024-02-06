package member;

/**
 * 
 * @author Diane
 *
 */

public class Member_ {
	
	int memberId;
	String name;

	public Member_(int memberId, String name) {
		super();
		this.memberId = memberId;
		this.name = name;
	}
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
