package Users.java;

import java.util.ArrayList;

import Createables.Election;

public class ElectionCommission {
	private String id;
	private String password;
	private ArrayList<Election> elections;
	public ElectionCommission(String i,String p,ArrayList<Election>e) {
		id=i;
		password=p;
		elections=new ArrayList<>();
	    elections=e;	
	}
	public String getAdminID()
	{
		return id;
				
	}
	public String getAdminPassword() {
		return password;
	}
}
