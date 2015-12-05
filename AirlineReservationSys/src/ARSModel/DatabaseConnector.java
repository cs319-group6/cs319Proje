package ARSModel;
import java.sql.*;
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
			preStt = conn.prepareStatement("Select * from user where idUser = ? and password =  ?;");
			preStt.setInt(1, userID);
			preStt.setString(2, encrypt(password));
			rs = preStt.executeQuery();
			if(rs.next()){
				boolean isAdmin = rs.getBoolean(6);
				if(isAdmin){
					return new Admin( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
				}else {
					return new Clerk( rs.getInt(1), decrypt(rs.getString(2)), rs.getString(3), rs.getString(4), decrypt(rs.getString(5)), decrypt(rs.getString(6)), decrypt(rs.getString(7)) );
				}
				
			}else{
				return null;
			}
			
		}catch(SQLException e){
			//TODO error message
			return null;
		}

	}
	
	public User addUser(String name, String surname, String email, String soNo, String phone,  boolean type){
		
		try{
			checkConnection();
			conn = getConnection();
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
	
	//TODO
	public boolean updateUser(User user){
		
		try {
			preStt = conn.prepareStatement("Update User set userName = ?, userSurname = ?, password = ?, userEmail= ?, socialsecurityno = ?, phone = ? where idUser  = ?;");
			preStt.setString(1, user.getName());
			preStt.setString(2, user.getSurname());
			preStt.setString(3, user.getPassword());
			preStt.setString(4, user.getEmail());
			preStt.setString(5, user.getSocialSecurityNo());
			preStt.setString(6, user.getPhoneNo());
			preStt.setInt(7, user.getPersonID());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	//TODO
	public boolean addFlight(){
		return true;
	}
	
	//TODO getFlight()
	

	
}
