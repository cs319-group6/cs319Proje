package ARSModel;

public class Admin extends User {
	public Admin(){
		super();
	}
	
	public Admin(long personID, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, name, surname, email,socialSecurityNo,phoneNo);
		
	}
}
