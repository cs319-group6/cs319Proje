package ARSModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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
	protected java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
 //SINGLETON PATTERN
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
			preStt.setString(2, password);
			rs = preStt.executeQuery();
			if(rs.next()){
				boolean isAdmin = rs.getBoolean(8);
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
	
public boolean updateUser(int userID, String password, String email, String phone ){
		
		try {
			checkConnection();
			preStt = conn.prepareStatement("Update User set password = ?, userEmail= ?, phone = ? where idUser  = ?;");
			preStt.setString(1, password);
			preStt.setString(2, email);
			preStt.setString(3, phone);
			preStt.setInt(4, userID);
			
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
	
	public User getUserSoNo(String soNo){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Select * from User where socialsecurityno = ?;");
			preStt.setString(1,soNo);
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

	public Passenger getPassenger(String socialsecurityno){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Select * from Passenger where socialsecurityno = ?;");
			preStt.setString(1, socialsecurityno);
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
	public boolean updatePassenger(String name, String surname, String email, String SoNo, String phone){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Update  Passenger set PassName = ?, PassSurname = ?, PassEmail = ?, phone = ?  where socialsecurityno = ?  ;");
			preStt.setString(1, name);
			preStt.setString(2, surname);
			preStt.setString(3, email);
			preStt.setString(4, phone);
			preStt.setString(5, SoNo);
			//rs = preStt.executeQuery();
			if(preStt.executeUpdate() == 1){
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
	public boolean deleteAirport(String name){
		try {
			checkConnection();
			preStt = conn.prepareStatement("Delete from Airport where AirportName = ?;");
			preStt.setString(1, name);
			//rs = preStt.executeQuery();
			if(preStt.executeUpdate() == 1){
				 return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	//TODO *****************
	public Flight addFlight(Airport from, Airport to, int planeType, ARSDate dateTime, int duration){
		try{
			checkConnection();
			
			
			String plane = getPlane(planeType);
			if(plane == null)
				return null;
			preStt = conn.prepareStatement("insert into Flight (departure, destination,planeType, date, time , duration ) values (?,?,?,?,?,?);");
			preStt.setInt(1, from.getAirportID());
			preStt.setInt(2, to.getAirportID());
			preStt.setInt(3, planeType);
			preStt.setString(4, dateTime.getDate());
			preStt.setString(5, dateTime.getTime());
			preStt.setInt(6, duration);
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from Flight where departure = ? and destination = ? and date = ? and time = ? ;");
				preStt.setInt(1, from.getAirportID());
				preStt.setInt(2, to.getAirportID());
				preStt.setString(3, dateTime.getDate());
				preStt.setString(4, dateTime.getTime());
				rs = preStt.executeQuery();
				if(rs.next()){
					return new Flight( rs.getInt(1),plane, from, to,  dateTime, duration );
					
				}else
					return null;
				
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public Flight getFlight(Airport from, Airport to, ARSDate dateTime){
		try {
			checkConnection();
			Flight flight = null;
			preStt = conn.prepareStatement("Select * from Flight where departure = ? and destination = ? and date = ? and time =?;");
			preStt.setInt(1, from.getAirportID());
			preStt.setInt(2, to.airportID);    
			preStt.setString(3, dateTime.getDate());
			preStt.setString(4, dateTime.getTime() );
			rs = preStt.executeQuery();
			
			if(rs.next()){
				
				 flight = new Flight(rs.getInt(1),getPlane(rs.getInt(2)) ,from, to, dateTime, rs.getInt(7));
				 
			}
			
				
				flight.setSeatAvailablity(getReservedSeats(flight));

			return flight;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	//TODO getFlight()
	public ArrayList<Flight> getFlightAtDate(Airport from, Airport to, ARSDate dateTime){
		try {
			checkConnection();
			ArrayList<Flight> flights = new ArrayList<Flight>();
			
			//System.out.println(datestr.substring(0, 10));
			preStt = conn.prepareStatement("Select * from Flight where departure = ? and destination = ? and date = ?;");
			preStt.setInt(1, from.getAirportID());
			preStt.setInt(2, to.getAirportID());    
			preStt.setString(3, dateTime.getDate() );
			rs = preStt.executeQuery();
			
			while(rs.next()){
				
				 flights.add(new Flight(rs.getInt(1),getPlane(rs.getInt(2)) ,from, to, new ARSDate(rs.getString(5),rs.getString(6)), rs.getInt(7)));
				 
			}
			for(int i =0 ; i < flights.size(); i++){
				
				flights.get(i).setSeatAvailablity(getReservedSeats(flights.get(i)));
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
				
				 flights.add(new Flight(rs.getInt(1),getPlane(rs.getInt(2)) ,getAirport((int)rs.getInt(3)), getAirport((int)rs.getInt(4)), new ARSDate(rs.getString(5), rs.getString(6)), rs.getInt(7)));
			}
			for(int i =0 ; i < flights.size(); i++){
				
				flights.get(i).setSeatAvailablity(getReservedSeats(flights.get(i)));
			}
			return flights;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Flight getFlight(int id){
		try {
			checkConnection();
			Flight f = null ;
			preStt = conn.prepareStatement("Select * from Flight  where idFlight = ?;");
			preStt.setInt(1, id);
			ResultSet rs = preStt.executeQuery();
			if (rs.next()){
				 f = new Flight(rs.getInt(1),getPlane(rs.getInt(2)) ,getAirport((int)rs.getInt(3)), getAirport((int)rs.getInt(4)), new ARSDate(rs.getString(5), rs.getString(6)), rs.getInt(7));
			}
			if(f != null){
				
				f.setSeatAvailablity(getReservedSeats(f));
			}
			return f;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Reservation addReservation(Flight flight, Passenger pass, Clerk clerk, Seat seat){
		try{
			checkConnection();
			preStt = conn.prepareStatement("insert into Reservation (flight, passenger, clerk, seatNo, seatChar ) values (?,?,?,?,?);");
			preStt.setInt(1, flight.getFlightID());
			preStt.setInt(2, pass.getPersonID());
			preStt.setInt(3, clerk.getPersonID());
			preStt.setInt(4, seat.getSeatNo());
			preStt.setString(5, seat.getSeatChar());
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from Reservation where seatNo = ? and seatChar = ? and flight = ?;");
				preStt.setInt(1, seat.getSeatNo());
				preStt.setString(2, seat.getSeatChar());
				preStt.setInt(3, flight.getFlightID());
				rs = preStt.executeQuery();
				if(rs.next()){
					return new Reservation( rs.getInt(1),flight, pass,  seat, clerk );
					
				}else
					return null;
				
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Reservation addReservation(Flight flight, Clerk clerk, Seat seat){
		try{
			checkConnection();
			preStt = conn.prepareStatement("insert into Reservation (flight, clerk, seatNo, seatChar ) values (?,?,?,?);");
			preStt.setInt(1, flight.getFlightID());
			//preStt.setInt(2, pass.getPersonID());
			preStt.setInt(2, clerk.getPersonID());
			preStt.setInt(3, seat.getSeatNo());
			preStt.setString(4, seat.getSeatChar());
			
			if(preStt.executeUpdate() == 1){
				preStt = conn.prepareStatement("Select * from Reservation where seatNo = ? and seatChar = ? and flight = ?;");
				preStt.setInt(1, seat.getSeatNo());
				preStt.setString(2, seat.getSeatChar());
				preStt.setInt(3, flight.getFlightID());
				rs = preStt.executeQuery();
				if(rs.next()){
					return new Reservation( rs.getInt(1),flight, null,  seat, clerk );
					
				}else
					return null;
				
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Reservation getReservation(Flight flight, Clerk clerk, Seat seat){
		try{
			checkConnection();
			
			preStt = conn.prepareStatement("Select * from Reservation where seatNo = ? and clerk = ?and seatChar = ? and flight = ?;");
			preStt.setInt(1, seat.getSeatNo());
			preStt.setInt(2, clerk.getPersonID());
			preStt.setString(3, seat.getSeatChar());
			preStt.setInt(4, flight.getFlightID());
			rs = preStt.executeQuery();
			if(rs.next()){
				return new Reservation( rs.getInt(1),flight, null,  seat, clerk );
				
			}else
				return null;

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<Reservation> getReservations(Passenger pass){
		try{
			checkConnection();
			ArrayList<Reservation> reservations = new ArrayList<Reservation>();
			Flight f;
			Seat s;
			Clerk c;
			preStt = conn.prepareStatement("Select * from Reservation where passenger = ?;");
			preStt.setInt(1, pass.getPersonID());
			ResultSet rs = preStt.executeQuery();
			while(rs.next()){
				f = getFlight(rs.getInt(2));
				c = (Clerk)getUserByID(rs.getInt(4));
				s = new Seat(rs.getInt(5),rs.getString(6),false);
				reservations.add(new Reservation( rs.getInt(1),f, pass,  s, c ));
				
			}
			if(reservations.size()>0)
				return reservations;
			else
				return null;

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Reservation makeReservation(Reservation res, Passenger pass){
		try{
			checkConnection();
			preStt = conn.prepareStatement("Update  Reservation set passenger = ? where idReservation = ?;");
			preStt.setInt(1, pass.getPersonID());
			preStt.setInt(2, res.getID());
			if(preStt.executeUpdate() == 1){
				res.passenger = pass;
				return res;
			}
			return null;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	public boolean deleteReservation(int resID){
		try{
			checkConnection();
			preStt = conn.prepareStatement("delete from Reservation where idReservation = ?;");
			preStt.setInt(1, resID);
		
			if(preStt.executeUpdate() == 1){
				return true;
		
			}
			return false;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String getPlane(String planeType){
		try{
			checkConnection();
			
			preStt = conn.prepareStatement("Select * from planeType where plane = ?;");
			preStt.setString(1, planeType);
			ResultSet rs = preStt.executeQuery() ;
			if(!rs.next()){
				return null;
				
			}
			int planeID  = rs.getInt(1);
			int numOfRows = rs.getInt(3);
			String seatChars = rs.getString(4);
			String seatArrengement = rs.getString(5);
			//System.out.println(planeType.concat(",".concat(String.valueOf(numOfRows).concat(",".concat(seatChars.concat(",".concat(seatArrengement)))))));
			return planeType.concat(",".concat(String.valueOf(numOfRows).concat(",".concat(seatChars.concat(",".concat(seatArrengement))))));
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	public String getPlane(int planeID){
		try{
			checkConnection();
			
			preStt = conn.prepareStatement("Select * from planeType where idplaneType = ?;");
			preStt.setInt(1, planeID);
			ResultSet rs = preStt.executeQuery() ;
			if(!rs.next()){
				return null;
				
			}
			//int planeID  = rs.getInt(1);
			String plane = rs.getString(2);
			
			return getPlane(plane);
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Seat> getReservedSeats(Flight f){
		try{
			checkConnection();
			ArrayList<Seat> seats = new ArrayList<Seat>();
			preStt = conn.prepareStatement("Select * From Reservation where flight = ?;");
			preStt.setInt(1, f.getFlightID());
			rs = preStt.executeQuery();
			
			
			while(rs.next()){
				
				seats.add(new Seat(rs.getInt(5)-1, rs.getString(6),false));
			}
			return seats;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<Airport> getDestinationAirportsDepartsFrom(Airport airport){
		try {
			checkConnection();
			ArrayList<Airport> airports = new ArrayList<Airport>();
			preStt = conn.prepareStatement("Select distinct idAirport, AirportName, Country, City from Airport, Flight where Flight.departure = ?  and Flight.destination = Airport.idAirport;");
			preStt.setInt(1, airport.getAirportID());
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
	public ArrayList<Airport> getDepartureAirportsArrivesTo(Airport airport){
		try {
			checkConnection();
			ArrayList<Airport> airports = new ArrayList<Airport>();
			preStt = conn.prepareStatement("Select distinct idAirport, AirportName, Country, City from Airport, Flight where Flight.destination = ?  and Flight.departure = Airport.idAirport;");
			preStt.setInt(1, airport.getAirportID());
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
	 
	
	
	
}

