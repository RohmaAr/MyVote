package Createables;
import java.util.ArrayList;
import Person.Candidate;
import Person.PO;

public abstract class Seat {
	protected PO presOfficer;
	protected ArrayList<Candidate> candidates;
	public abstract Seat getSeat();
	public void addPo(PO po) {
        presOfficer=po;
	}
	public PO getPO()
	{
		return presOfficer;
	}
	public void displaySeatCandidates() {
		for(int i=0;i<candidates.size();i++)
		{
			candidates.get(i).getCandidateInfo();
		}
		
	}
	public Seat()
	{
		candidates=new ArrayList<>();
		presOfficer=new PO();
	}
	public abstract String getConstitut();
}
