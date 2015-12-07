package ARSModel;

public class ComboBItem {

	private Airport airport;
	
	public ComboBItem(Airport a){
		airport = a;
	}
	public String toString(){
		if(airport != null)
			return airport.getName();
		else
			return "";
	}
	public Airport getAirport(){
		return airport;
	}
}
