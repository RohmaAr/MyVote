package Person;

public class NoAccessCitizen extends Person{
	//private SignUp signUp; 
	public NoAccessCitizen() {}
	public NoAccessCitizen(String n,String cnic,String p)
	{
		password=p;
		CNIC=cnic;
		name=n;
	}
	public void signUp()
	{
		
	}
	
	public void login()
	{
		//some code to get the database from database if its access granted automatically create voter obj
	}
}
