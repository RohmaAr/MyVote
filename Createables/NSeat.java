package Createables;


public class NSeat extends Seat {
	private String NConstitut;
	private int Nnum;
	public NSeat(String c,int n)
	{
		Nnum=n;
		NConstitut=c;
	}
	
	@Override
	public Seat getSeat() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getNnm()
	{
		return Nnum;
	}
	
	@Override
	public String getConstitut() {
		// TODO Auto-generated method stub
		return NConstitut;
	}

}
