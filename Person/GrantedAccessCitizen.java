package Person;

import Enums.Province;

public abstract class GrantedAccessCitizen extends Person {
	protected String region;
	protected String PConstitut;
	protected String NConstitut;
	//protected Result result;
	protected Province prov;
	public String getRegion()
	{
		return region;
	}
	public String getPConstitut()
	{
		return PConstitut;
	}
	public String getNConstitut()
	{
		return NConstitut;
	}
	public Province getProv()
	{
		return prov;
	}
	public void setRegion(String r)
	{
		region=r;
	}
	public void setPConstitut(String p)
	{
		PConstitut=p;
	}
	public void setNConstitut(String n)
	{
		NConstitut=n;
	}
	public void  setProvince(Province p)
	{
		prov=p;
	}
	public void viewResult()
	{
		
	}
	public abstract void login();
}
