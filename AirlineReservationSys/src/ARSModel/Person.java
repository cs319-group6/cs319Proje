package ARSModel;

import java.util.Observable;

public abstract class Person extends Observable{
	
	int personID;
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
	
	public Person(int personID, String name, String surname, String email, String socialSecurityNo,
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
	public int getPersonID()
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
	public void setPersonID(int ID)
	{
		personID = ID;
		setChanged();
		notifyObservers();
	}
	
	public void setName(String str)
	{
		name = str;
		setChanged();
		notifyObservers();
	}
	
	public void setSurname(String str)
	{
		surname = str;
		setChanged();
		notifyObservers();
	}
	
	public void setEmail(String str)
	{
		email = str;
		setChanged();
		notifyObservers();
	}
	
	public void setSocialSecurityNo(String str)
	{
		socialSecurityNo = str;
		setChanged();
		notifyObservers();
	}
	
	public void setPhoneNo(String str)
	{
		phoneNo = str;
		setChanged();
		notifyObservers();
	}
	
	public String toString(){
		return "ID: " + personID + " name: " + name +" " + surname + " email: " + 
				 email + " social no: " + socialSecurityNo + " phone no: " + phoneNo ; 
	}
	
	

}
