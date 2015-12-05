package ARSModel;

public class Reservation {

	Passenger[] passengers;
	Seat[] seats;
	Clerk clerk;
	
	public Reservation()
	{
		passengers = new Passenger[0];
		seats = new Seat[0];
		clerk = new Clerk();
	}
	
	public Reservation(Passenger[] passengers, Seat[] seats, Clerk clerk)
	{
		this.passengers = passengers;
		this.seats = seats;
		this.clerk = clerk;
	}
	
	
	//GET METHODS
	public Passenger[] getPassengers()
	{
		return passengers;
	}
	
	public Seat[] getSeats()
	{
		return seats;
	}
	
	public Clerk getClerk()
	{
		return clerk;
	}
	
	
	//SET METHODS
	public void setPassengers(Passenger[] a)
	{
		passengers = a;
	}
	
	public void setSeats(Seat[] a)
	{
		seats = a;
	}
	
	public void setClerk(Clerk a)
	{
		clerk = a;
	}
	
	
	//METHODS
	
}
