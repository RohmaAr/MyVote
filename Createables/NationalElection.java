package Createables;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NationalElection extends Assembly {

	@Override
	public void selectSeats() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPOs() {
	   

	}
	public Result getResult()
	{
		return result;
	}
	public NationalElection(LocalDateTime d)
	{
		seats= new ArrayList<>();
		date=d;
	}
}
