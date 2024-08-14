package Createables;

public abstract class AppAssembly extends Application {
private String party;

	public AppAssembly(String n, String c) {
		super(n, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveApp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayApp() {
		// TODO Auto-generated method stub

	}
	public void setParty(String p)
	{
		party=p;
	}
	public String getParty()
	{
		return party;
	}
	
}
