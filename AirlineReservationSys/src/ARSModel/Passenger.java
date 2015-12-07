package ARSModel;

public class Passenger extends Person {
	public Passenger(){
		super();
	}
	public Passenger(int personID, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, name, surname, email,socialSecurityNo,phoneNo);
		
	}
	public String toStrin(){
		return "Passenger ".concat(super.toString());
	}
}
