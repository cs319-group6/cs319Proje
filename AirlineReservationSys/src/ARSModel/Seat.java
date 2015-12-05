package ARSModel;

public class Seat {

	String seatNo;
	boolean available;
	
	public Seat()
	{
		seatNo = "";
		available = false;
	}
	
	public Seat(String seatNo, boolean available)
	{
		this.seatNo = seatNo;
		this.available = available;
	}
	
	
	//GET METHODS
	public String getSeatNo()
	{
		return seatNo;
	}
	
	public boolean getAvailable()
	{
		return available;
	}
	
	
	//SET METHODS
	public void setSeatNo(String str)
	{
		seatNo = str;
	}
	
	public void setAvailable(boolean a)
	{
		available = a;
	}
	
	
	//METHODS
	public boolean allocate()
	{
		available = true;
		return true;
	}
	
}

