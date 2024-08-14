package Createables;

import Enums.Province;

public class PSeat extends Seat {
    private String PConstitut;
    private Province province;
    private int Pnum;
    public PSeat(String PConst,int p,Province pr)
    {
    	PConstitut=PConst;
    	Pnum=p;
    	province=pr;
    }
	@Override
	public Seat getSeat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConstitut() {
		// TODO Auto-generated method stub
		return PConstitut;
	}
	public int getPnum()
	{
		return Pnum;
	}
	
}
