package Createables;

import java.time.LocalDateTime;

public abstract class Election {
	protected LocalDateTime date;
	public Result result;
	public LocalDateTime geDate()
	{
		return date;
	}
	public void setDate(LocalDateTime d )
	{
		date=d;
	}
	public abstract Result getResult();
	public Election getElection()
	{
		return this;
	}
	public abstract void displayCandidates();
}
