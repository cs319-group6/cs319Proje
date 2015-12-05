package ARSModel;

public abstract class User extends Person {
	public User(){
		super();
	}
	public User(long personID, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, name, surname, email,socialSecurityNo,phoneNo);
		
	}
	
	
}
