package ARSModel;

public class Clerk extends User {

	public Clerk(){
		super();
	}
	
	public Clerk(int personID,String password, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID,password, name, surname, email,socialSecurityNo,phoneNo);
		
	}
}
