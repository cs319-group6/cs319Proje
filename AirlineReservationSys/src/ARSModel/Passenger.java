package ARSModel;

public class Passenger extends Person {
	public Passenger(){
		super();
	}
	public Passenger(long personID, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, name, surname, email,socialSecurityNo,phoneNo);
		
	}
}
