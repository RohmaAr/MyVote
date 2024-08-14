package Createables;

import org.apache.catalina.authenticator.SavedRequest;

public class Vote {
	private String CNIC;
	private String candidateName;
	//private Election election;
	private static Duration duration;
	//private SaveVote save;
	private String area;
	public void selectCandidate(String candiName)
	{
		candidateName=candiName;
	}
	public boolean ballotActive() {
		return false;
		
	}
	//is mai dekhlo k static rakhna hai isko k nahi.
	//public Vote(String CNIC, Election election , String area)
	{
		
	}
	public void saveVote() {
		
	}
	public boolean castedAlready(String CNIC)
	{
		return false;
	}
	public void displayCandidates()
	{
		
	}
}
