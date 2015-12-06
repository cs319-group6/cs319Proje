package ARSModel;

public abstract class User extends Person {
	private String password;
	public User(){
		super();
		password = "";
	}
	public User(int personID, String password, String name, String surname, String email, String socialSecurityNo,
			String phoneNo){
		
		super(personID, name, surname, email,socialSecurityNo,phoneNo);
		this.password = password;
		
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String pass ){
		password = pass;
	}
	 public String toString(){
		 return "ID: " + personID + " password: " + password + " name: " + name +" " + surname + " email: " + 
				 email + "SocialNo: " + socialSecurityNo + " phone no: " + phoneNo ;
	 }
	
}
