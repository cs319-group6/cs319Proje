package ARSModel;

public class Seat {

	String seatNo;
	char seatChar;
	boolean available;
	
	public Seat()
	{
		seatNo = "";
		seatChar = ' ';
		available = false;
	}
	
	public Seat(String seatNo, char seatChar, boolean available)
	{
		this.seatNo = seatNo;
		this.seatChar = seatChar;
		this.available = available;
	}
	
	
	//GET METHODS
	public String getSeatNo()
	{
		return seatNo;
	}
	
	public char getSeatChar()
	{
		return seatChar;
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
	
	public void setSeatChar( char c)
	{
		seatChar = c;
	}
	
	//METHODS
	public boolean allocate()
	{
		available = true;
		return true;
	}
	
}

