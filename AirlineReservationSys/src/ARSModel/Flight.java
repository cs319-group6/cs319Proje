package ARSModel;
import java.util.Date;

public class Flight {
	int tempNumOfRows = 35;
	String seatingPlanChars = "ABCDEF";
	int[] tempSeatArrengement = {3,3};
	int flightID;
	String planeType;
	Airport destination;
	Airport departure;
	Date dateTime;
	String status;
	int duration;
	Seat[][] seats;
	
	public Flight()
	{
		flightID = -1;
		planeType = "";
		destination = new Airport();
		departure = new Airport();
		dateTime = new Date();
		status = "";
		duration = -1;
		seats = new Seat[0][0];
	}
	
	public Flight(int flightID, String planeType, Airport destination, Airport departure,
					Date dateTime, int duration)
	{
		this.flightID = flightID;
		this.planeType = planeType;
		this.destination = destination;
		this.departure = departure;
		this.dateTime = dateTime;
		//this.status = status;
		this.duration = duration;
		generateDefaultSeats();
		//TODO generate seats according to planeType
	}
	
	
	//GET METHODS
	public int getFlightID()
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
	
	public Date getDateTime()
	{
		return dateTime;
	}
	//TODO calculate status according to current time and dateTime
	public String getStatus()
	{
		return status;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public Seat[][] getSeats()
	{
		return seats;
	}
	
	
	//SET METHODS
	public void setFlightID(int ID)
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
	
	public void setDateTime(Date c)
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
	
	public void setSeats(Seat[][] a)
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
	
	public void generateDefaultSeats(){
		seats = new Seat[tempNumOfRows][seatingPlanChars.length()];
		for(int i = 0; i< tempNumOfRows; i++){
			
			for(int j = 0; j < seatingPlanChars.length(); i++){
				seats[i][j] = new Seat(i,seatingPlanChars.substring(j, j+1), true);
			}
			
		}
	}
	
	public String toString(){
		return "Flight id: " +flightID+ " from: " +departure.getName()+ " to: " + destination.getName()+ " at: " +dateTime.toString()+ " duration: " + duration;
	}
	
}
