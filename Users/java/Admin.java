package Users.java;

public class Admin {
	private String id;
	private String password;
	public Admin(String iD, String pa) {
		id=iD;
		password=pa;
	}
	public String getAdminID()
	{
		return id;
				
	}
	public String getAdminPassword() {
		return password;
	}
}
