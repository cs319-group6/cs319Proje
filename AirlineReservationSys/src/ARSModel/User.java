package ARSModel;

public abstract class User extends Person {
	private String password;
	public User(){
		super();
		password = "";
	}
	public User(long personID, String password, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, name, surname, email,socialSecurityNo,phoneNo);
		this.password = password;
		
	}
	
	
}
