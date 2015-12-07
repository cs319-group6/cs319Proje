package ARSModel;

public class Seat {

	int seatNo;
	String seatChar;
	boolean available;
	
	public Seat()
	{
		seatNo = -1;
		seatChar = "";
		available = false;
	}
	
	public Seat(int seatNo, String seatChar, boolean available)
	{
		this.seatNo = seatNo;
		this.seatChar = seatChar;
		this.available = available;
	}
	
	
	//GET METHODS
	public int getSeatNo()
	{
		return seatNo;
	}
	
	public String getSeatChar()
	{
		return seatChar;
	}
	
	public boolean getAvailable()
	{
		return available;
	}
	
	
	//SET METHODS
	public void setSeatNo(int str)
	{
		seatNo = str;
	}
	
	public void setAvailable(boolean a)
	{
		available = a;
	}
	
	public void setSeatChar( String c)
	{
		seatChar = c;
	}
	
	//METHODS
	public boolean allocate()
	{
		available = true;
		return true;
	}
	
	boolean equals(Seat s){
		return(s.getSeatChar().equalsIgnoreCase(this.seatChar) && s.getSeatNo() == this.getSeatNo());
	}
}

