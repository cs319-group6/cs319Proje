package ARSModel;

public class Admin extends User {
	public Admin(){
		super();
	}
	
	public Admin(long personID, String password, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, password, name, surname, email,socialSecurityNo,phoneNo);
		
	}
}