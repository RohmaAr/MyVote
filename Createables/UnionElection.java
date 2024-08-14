package Createables;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Person.Candidate;
import Person.PO;

public class UnionElection extends Election {
	private String region;
	private PO presOfficer;
	private ArrayList<Candidate> candidates;
	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	public UnionElection(LocalDateTime d,String r)
	{
		date=d;
		candidates=new ArrayList<>();
		region=r;
	}
	@Override
	public void displayCandidates() {
		for(int i=0;i<candidates.size();i++)
		{
			candidates.get(i).getCandidateInfo();
		}
	}
	public void setPO(PO p)
	{
		presOfficer=p;
	}
	public String getRegion()
	{
		return region;
	}
	public void setRegion(String r)
	{
		region=r;
	}
}
