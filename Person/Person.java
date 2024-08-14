package Person;

public abstract class Person {
	protected String name;
	protected String password;
	protected String CNIC;
	public String getName() {
		return name;
	}
	public String getCNIC() {
		return CNIC;
	}
	protected String getPassword()
	{
		return password;
	}
}
