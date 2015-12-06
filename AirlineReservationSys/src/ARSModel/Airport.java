package ARSModel;

public class Airport {

	int airportID;
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
	
	public Airport(int airportID, String name, String country, String city)
	{
		this.airportID = airportID;
		this.country = country;
		this.city = city;
		this.name = name;
	}
	
	//GET METHODS
	public int getAirportID()
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
	public void setAirportID(int ID)
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
	
	public String toString(){
		return "Airport id: " +airportID+ " name: " +name+ " country: " +country+ " city: " + city;
	}
	
}
