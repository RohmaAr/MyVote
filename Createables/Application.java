package Createables;

public abstract class Application {
  private String name;
  private String CNIC;
  private String assets;
  private String qualification;
  private boolean dualNationality; 
  Election election;
  public Application(String n,String c)
  {
	  name=n;
	  CNIC=c;
  }
  public void setName(String n)
  {
	  name=n;
  }
  public void setNationality(boolean n)
  {
	 dualNationality=n;
  }
  public void setAssets(String n)
  {
	  assets=n;
  }
  public void setQual(String n)
  {
	  qualification=n;
  }
  public abstract void saveApp();
  public abstract void displayApp();
 // public Application getApplication( Election e)
  {}
}
