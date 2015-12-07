package ARSModel;

public class Reservation {

	int reservationID;
	Flight flight;
	Passenger passenger;
	Seat seat;
	Clerk clerk;
	
	public Reservation()
	{
		reservationID = -1;
		flight = null;
		passenger = null;
		seat = null;
		clerk = new Clerk();
	}
	
	public Reservation(int id, Flight flight, Passenger passenger, Seat seat, Clerk clerk)
	{
		reservationID = id;
		this.flight = flight;
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
	public String toString(){
		return "Reservation id: " +reservationID+ " flight no: " +flight.getFlightID()+ " passenger name: " +
					passenger.getName()+ " " + passenger.getSurname()+ " seat: " +seat.getSeatNo() +seat.getSeatChar()+ 
					" clerk name: " +clerk.getName()+" "+ clerk.getSurname();
	}
}
