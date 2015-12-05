package ARSModel;

public class Airport {

	long airportID;
	String country;
	String city;
	String name;
	
	public Airport()
	{
		airportID = -1;
		country = "";
		city = "";
		name = "";
	}
	
	public Airport(long airportID, String country, String city, String name)
	{
		this.airportID = airportID;
		this.country = country;
		this.city = city;
		this.name = name;
	}
	
	//GET METHODS
	public long getAirportID()
	{
		return airportID;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getName()
	{
		return name;
	}
	
	//SET METHODS
	public void setAirportID(long ID)
	{
		airportID = ID; 
	}
	
	public void setCountry(String str)
	{
		country = str;
	}
	
	public void setCity(String str)
	{
		city = str;
	}
	
	public void setName(String str)
	{
		name = str;
	}
	
	
}