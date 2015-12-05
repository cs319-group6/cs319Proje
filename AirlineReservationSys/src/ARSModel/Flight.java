package ARSModel;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Flight {

	long flightID;
	String planeType;
	Airport destination;
	Airport departure;
	Calendar dateTime;
	String status;
	int duration;
	Seat[] seats;
	
	public Flight()
	{
		flightID = -1;
		planeType = "";
		destination = new Airport();
		departure = new Airport();
		dateTime = new GregorianCalendar();
		status = "";
		duration = -1;
		seats = new Seat[0];
	}
	
	public Flight(long flightID, String planeType, Airport destination, Airport departure,
					Calendar dateTime, String status, int duration, Seat[] seats)
	{
		this.flightID = flightID;
		this.planeType = planeType;
		this.destination = destination;
		this.departure = departure;
		this.dateTime = dateTime;
		this.status = status;
		this.duration = duration;
		this.seats = seats;
	}
	
	
	//GET METHODS
	public long getFlightID()
	{
		return flightID;
	}
	
	public String getPlaneType()
	{
		return planeType;
	}
	
	public Airport getDestination()
	{
		return destination;
	}
	
	public Airport getDeparture()
	{
		return departure;
	}
	
	public Calendar getDateTime()
	{
		return dateTime;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public Seat[] getSeats()
	{
		return seats;
	}
	
	
	//SET METHODS
	public void setFlightID(long ID)
	{
		flightID = ID;
	}
	
	public void setPlaneType(String str)
	{
		planeType = str;
	}
	
	public void setDestination(Airport des)
	{
		destination = des;
	}
	
	public void setDeparture(Airport dep)
	{
		departure = dep;
	}
	
	public void setDateTime(Calendar c)
	{
		dateTime = c;
	}
	
	public void setStatus(String str)
	{
		status = str;
	}
	
	public void setDuration(int a)
	{
		duration = a;
	}
	
	public void setSeats(Seat[] a)
	{
		seats = a;
	}
	
	//METHODS
	public void updateFlight(String seatNo)
	{
		
	}
	
	public boolean isFlightFull()
	{
		boolean check = false;
		
		return check;
	}
	
}
