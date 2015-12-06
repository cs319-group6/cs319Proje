package ARSModel;

public class Reservation {

	Passenger passenger;
	Seat seat;
	Clerk clerk;
	
	public Reservation()
	{
		passenger = null;
		seat = null;
		clerk = new Clerk();
	}
	
	public Reservation(Passenger passenger, Seat seat, Clerk clerk)
	{
		this.passenger = passenger;
		this.seat = seat;
		this.clerk = clerk;
	}
	
	
	//GET METHODS
	public Passenger getPassengers()
	{
		return passenger;
	}
	
	public Seat getSeats()
	{
		return seat;
	}
	
	public Clerk getClerk()
	{
		return clerk;
	}
	
	
	//SET METHODS
	public void setPassengers(Passenger a)
	{
		passenger = a;
	}
	
	public void setSeats(Seat a)
	{
		seat = a;
	}
	
	public void setClerk(Clerk a)
	{
		clerk = a;
	}
	
	
	//METHODS
	
}
