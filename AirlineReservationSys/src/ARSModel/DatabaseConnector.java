package ARSModel;
import java.sql.*;
import java.util.ArrayList;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class DatabaseConnector {
	private  final String ALGORITHM = "AES";
    private  final String KEY = "1Hbfh667adfDEJ78";
    private final Key aesKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
	private  String db_connect_string = "jdbc:mysql://localhost:3306/AirlineSys";
	private  String db_userid = "root";
	private  String db_password = "atakan1304";
	private  Connection conn;
	protected  String sqlCommand;
	protected  Statement statement;
	protected  ResultSet rs;
	protected  PreparedStatement preStt;
	
	
	/*
	 * 
	 * java.util.Date dt = new java.util.Date();
	 *
	 * java.text.SimpleDateFormat sdf = 
	 * new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 *	
	 *	String currentTime = sdf.format(dt);
	 *******
	 *	java.util.Date date = new Date();
	 *	Object param = new java.sql.Timestamp(date.getTime());    
	 *	preparedStatement.setObject(param);
	 * 
	 * 
	 * 
	 */

	private static DatabaseConnector dbInstance = null;
	
	private DatabaseConnector(){
		connect();
	}
	
	public static DatabaseConnector getInstance(){
		if(dbInstance == null){
			dbInstance= new DatabaseConnector();
		}
		return dbInstance;
	}
	
	public boolean connect(){
		 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 this.conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
			statement = conn.createStatement();
			if(conn != null)
				System.out.println("Connected");
			return true;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private boolean checkConnection(){
		try{
			if(conn.isClosed() || conn == null){
				return connect();		
			}
			else 
				return true;
		}catch(Exception e){
			return false;
		}
	}
	
	protected Connection getConnection(){
		try{
			checkConnection();
			return conn;
		}catch(Exception e){
			return null;
		}
	}
	
	public String encrypt(String str){
		
        // Create cipher
        Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
			// encrypt the text
	        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	        byte[] encrypted = cipher.doFinal(str.getBytes());
	        StringBuilder sb = new StringBuilder();
            for (byte b: encrypted) {
                sb.append((char)b);
            }
            // the encrypted String
            String enc = sb.toString();
	        return enc;
	        
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
        
	}
	
	public String decrypt(String encStr){
		Cipher cipher;
		
		try {
			cipher = Cipher.getInstance("AES");
			byte[] bb = new byte[encStr.length()];
	        for (int i=0; i<encStr.length(); i++) {
	            bb[i] = (byte) encStr.charAt(i);
	        }
	        // decrypt the text
	        cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        String decrypted = new String(cipher.doFinal(bb));
	        return decrypted;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public User verifyUser(int userID, String password){
		
		try{
			checkConnection();
			preStt = conn.prepareStatement("Select * from User where idUser = ? and password =  ?;");
			preStt.setInt(1, userID);
			preStt.setString(2, encrypt(password));
			rs = preStt.executeQuery();
			if(rs.next()){
				boolean isAdmin = rs.getBoolean(6);
				if(isAdmin){
					//return new Admin( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
					return new Admin( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
				}else {
					//return new Clerk( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
					return new Clerk( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
				}
				
			}else{
				return null;
			}
			
		}catch(SQLException e){
			//TODO error message
			e.printStackTrace();
			return null;
		}

	}
	
	public User addUser(String name, String surname, String email, String soNo, String phone,  boolean type){
		
		try{
			checkConnection();
			preStt = conn.prepareStatement("insert into User (userName, userSurname, userEmail, socialsecurityno, phone, type ) values (?,?,?,?,?,?);");
			preStt.setString(1, name);
			preStt.setString(2, surname);
			//preStt.setString(3, encrypt(email));
			preStt.setString(3, email);
			//preStt.setString(4, encrypt(soNo));
			preStt.setString(4, soNo);
			//preStt.setString(5, encrypt(phone));
			preStt.setString(5, phone);
			preStt.setBoolean(6, type);
			
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from User where userEmail = ?;");
				//preStt.setString(1, encrypt(email));
				preStt.setString(1, email);
				rs = preStt.executeQuery();
				
				if(rs.next()){
					int id = rs.getInt(1);
					preStt = conn.prepareStatement("Update User set password = ? where idUser  = ?;");
					//preStt.setString(1, encrypt(String.valueOf(id)));
					preStt.setString(1, String.valueOf(id));
					preStt.setInt(2, id);
					preStt.executeUpdate();
					boolean isAdmin = rs.getBoolean(8);
					if(isAdmin){
						return new Admin( rs.getInt(1),String.valueOf(id), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
						//return new Admin( rs.getInt(1),encrypt(String.valueOf(id)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
					}else {
						//return new Clerk( rs.getInt(1), encrypt(String.valueOf(id)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
						return new Clerk( rs.getInt(1), String.valueOf(id), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
					}
				}else
					return null;
			}
			else
				return null;
		}catch(SQLException e){
			//TODO Error message
			e.printStackTrace();
			return null;
		}
				
	}
	
	public boolean updateUser(int userID, String name, String surname, String password, String email, String soNo, String phone,  boolean type ){
		
		try {
			checkConnection();
			preStt = conn.prepareStatement("Update User set userName = ?, userSurname = ?, password = ?, userEmail= ?, socialsecurityno = ?, phone = ? where idUser  = ?;");
			preStt.setString(1, name);
			preStt.setString(2, surname);
			preStt.setString(3, password);
			preStt.setString(4, email);
			preStt.setString(5, soNo);
			preStt.setString(6, phone);
			preStt.setInt(7, userID);
			
			if(preStt.executeUpdate()== 1){
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public User getUserByID(int id){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Select * from User where idUser = ?;");
			preStt.setInt(1, id);
			rs = preStt.executeQuery();
			if(rs.next()){
				boolean isAdmin = rs.getBoolean(6);
				if(isAdmin){
					//return new Admin( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
					return new Admin( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				}else {
					//return new Clerk( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
					return new Clerk( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				}
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> users = new ArrayList<User>();
		try{
			checkConnection();
			preStt = conn.prepareStatement("Select * from User");
			rs = preStt.executeQuery();
			while (rs.next()){
				boolean isAdmin = rs.getBoolean(6);
				if(isAdmin){
					//users.add(new Admin( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) ));
					users.add(new Admin( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
				}else {
					users.add(new Clerk( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
				}
				
			}
			return users;
		}catch(SQLException e){
			//TODO error message
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<User> getUsersByType(boolean isAdmin){
		ArrayList<User> users = new ArrayList<User>();
		try{
			checkConnection();
			preStt = conn.prepareStatement("Select * from User where type = ?;");
			preStt.setBoolean(1, isAdmin);
			rs = preStt.executeQuery();
			while (rs.next()){
				if(isAdmin){
					users.add(new Admin( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
				}else {
					users.add(new Clerk( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
				}
				
			}
			return users;
		}catch(SQLException e){
			//TODO error message
			e.printStackTrace();
			return null;
		}
	}
	
	public Passenger addPassenger(String name, String surname, String email, String soNo, String phone){
		try{
			checkConnection();
			preStt = conn.prepareStatement("insert into Passenger (PassName, PassSurname, PassEmail, socialsecurityno, phone) values (?,?,?,?,?);");
			preStt.setString(1, name);
			preStt.setString(2, surname);
			preStt.setString(3, email);
			preStt.setString(4, soNo);
			preStt.setString(5, phone);
			
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from Passenger where PassEmail = ?;");
				//preStt.setString(1, encrypt(email));
				preStt.setString(1, email);
				rs = preStt.executeQuery();
				
				if(rs.next()){
					return new Passenger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				}else
					return null;
			}
			else
				return null;
		}catch(SQLException e){
			//TODO Error message
			e.printStackTrace();
			return null;
		}
	}

	public Passenger getPassenger(String email){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Select * from Passenger where PassEmail = ?;");
			preStt.setString(1, email);
			rs = preStt.executeQuery();
			if(rs.next()){
				return new Passenger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Passenger> getAllPassengers(){
		try {
			checkConnection();
			ArrayList<Passenger> passengers = new ArrayList<Passenger>();
			preStt = conn.prepareStatement("Select * from Passenger;");
			rs = preStt.executeQuery();
			while(rs.next()){
				passengers.add( new Passenger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			return passengers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	public Airport addAirport(String country, String city, String airportName){
		try{
			checkConnection();
			preStt = conn.prepareStatement("insert into Airport (AirportName, City, Country) values (?,?,?);");
			preStt.setString(1, airportName);
			preStt.setString(2, city);
			preStt.setString(3, country);
			
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from Airport where AirportName = ?;");
				//preStt.setString(1, encrypt(email));
				preStt.setString(1, airportName);
				rs = preStt.executeQuery();
				
				if(rs.next()){
					return new Airport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				}else
					return null;
			}
			else
				return null;
		}catch(SQLException e){
			//TODO Error message
			e.printStackTrace();
			return null;
		}
		
	}

	public ArrayList<Airport> getAirports(String country, String city){
		try {
			checkConnection();
			ArrayList<Airport> airports = new ArrayList<Airport>();
			preStt = conn.prepareStatement("Select * from Airport where country = ? and city = ?;");
			preStt.setString(1, country);
			preStt.setString(2, city);
			rs = preStt.executeQuery();
			while(rs.next()){
				 airports.add(new Airport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			return airports;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Airport> getAllAirports(){
		try {
			checkConnection();
			ArrayList<Airport> airports = new ArrayList<Airport>();
			preStt = conn.prepareStatement("Select * from Airport ;");
			rs = preStt.executeQuery();
			while(rs.next()){
				 airports.add(new Airport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			return airports;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Airport getAirport(String name){
		try {
			checkConnection();
			ArrayList<Airport> airports = new ArrayList<Airport>();
			preStt = conn.prepareStatement("Select * from Airport where AirportName = ?;");
			preStt.setString(1, name);
			rs = preStt.executeQuery();
			if(rs.next()){
				 return new Airport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Airport getAirport(int id){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Select * from Airport where idAirport = ?;");
			preStt.setInt(1, id);
			rs = preStt.executeQuery();
			if(rs.next()){
				 return new Airport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//TODO *****************
	public Flight addFlight(Airport from, Airport to, String planeType, long dateTime, int duration){
		try{
			checkConnection();
			preStt = conn.prepareStatement("insert into Flight (departure, destination, planeType, dateTime, duration ) values (?,?,?,?,?);");
			preStt.setInt(1, from.getAirportID());
			preStt.setInt(2, to.getAirportID());
			preStt.setString(3, planeType);
			preStt.setLong(4, dateTime);
			preStt.setInt(5, duration);
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from Flight where departure = ? and destination = ? and dateTime = ?;");
				preStt.setInt(1, from.getAirportID());
				preStt.setInt(2, to.getAirportID());
				preStt.setLong(3, dateTime);
				rs = preStt.executeQuery();
				if(rs.next()){
					return new Flight( rs.getInt(1),planeType, from, to,  new java.util.Date(dateTime), duration );
					
				}else
					return null;
				
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	//TODO getFlight()
	public ArrayList<Flight> getFlight(Airport from, Airport to, long date){
		try {
			checkConnection();
			ArrayList<Flight> flights = new ArrayList<Flight>();
			preStt = conn.prepareStatement("Select * from Flight where departure = ? and destination = ? and dateTime = ?;");
			preStt.setInt(1, from.getAirportID());
			preStt.setInt(2, to.airportID);    
			preStt.setLong(3, date);
			rs = preStt.executeQuery();
			while(rs.next()){
				 flights.add(new Flight(rs.getInt(1),rs.getString(2) ,from, to, new java.util.Date(date), rs.getInt(6)));
			}
			return flights;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public ArrayList<Flight> getAllFlights(){
		try {
			checkConnection();
			ArrayList<Flight> flights = new ArrayList<Flight>();
			preStt = conn.prepareStatement("Select * from Flight;");
			ResultSet rs = preStt.executeQuery();
			while(rs.next()){
				
				 flights.add(new Flight(rs.getInt(1),rs.getString(2) ,getAirport((int)rs.getInt(3)), getAirport((int)rs.getInt(4)), new java.util.Date(rs.getLong(5)), rs.getInt(6)));
			}
			return flights;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
}
