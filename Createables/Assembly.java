package Createables;

import java.util.ArrayList;

import Enums.Type;

public abstract class Assembly extends Election {

	protected Type type;
	protected int noOfSeats;
	protected ArrayList<Seat> seats;
	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayCandidates() {
		for(int i=0;i<seats.size();i++)
		{
			seats.get(i).displaySeatCandidates();
		}

	}
	public abstract void selectSeats();
	public void setType(Type t) {
		type=t;
	}
	public void setSeatsNumber(int n)
	{
		noOfSeats=n;
	}
	public Type getType()
	{
		return type;
	}
	public int getNofSeats()
	{
		return noOfSeats;
	}
	public abstract void setPOs();
}
