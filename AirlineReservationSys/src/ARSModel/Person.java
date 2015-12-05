package ARSModel;

public abstract class Person {
	
	long personID;
	String name;
	String surname;
	String email;
	String socialSecurityNo;
	String phoneNo;
	
	public Person()
	{
		personID = -1;
		name = "";
		surname = "";
		email = "";
		socialSecurityNo = "";
		phoneNo = "";
	}
	
	public Person(long personID, String name, String surname, String email, String socialSecurityNo,
					String phoneNo)
	{
		this.personID = personID;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.socialSecurityNo = socialSecurityNo;
		this.phoneNo = phoneNo;
	}
	
	
	//GET METHODS
	public long getPersonID()
	{
		return personID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getSocialSecurityNo()
	{
		return socialSecurityNo;
	}
	
	public String getPhoneNo()
	{
		return phoneNo;
	}
	
	
	//SET METHODS
	public void setPersonID(long ID)
	{
		personID = ID;
	}
	
	public void setName(String str)
	{
		name = str;
	}
	
	public void setSurname(String str)
	{
		surname = str;
	}
	
	public void setEmail(String str)
	{
		email = str;
	}
	
	public void setSocialSecurityNo(String str)
	{
		socialSecurityNo = str;
	}
	
	public void setPhoneNo(String str)
	{
		email = str;
	}
	
	
	//METHODS
	
	

}
