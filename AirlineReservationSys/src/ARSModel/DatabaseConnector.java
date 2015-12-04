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
	protected  PreparedStatement preparedStatement;
	
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
			Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
			statement = conn.createStatement();
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
			if(conn.isClosed()){
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
		// Create key and cipher
		String key = "Bar12345Bar12345"; // 128 bit key
        // Create key and cipher
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
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
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
	
	public boolean verifyUser(int userID, String password){
		sqlCommand = "Select * from user where idUser = "+ userID +" and password =  " + password;
		try{
			rs = statement.executeQuery(sqlCommand);
			if(rs.next()){
				//TODO create user
				
				return true;
			}else{
				return false;
			}
			
		}catch(SQLException e){
			return false;
		}

	}
	//TODO
	public boolean addUser(){
		return true;
	}
	
	//TODO
	public boolean updateUser(){
		return true;
	}
	
	//TODO
	public boolean addFlight(){
		return true;
	}
	
	//TODO getFlight()
	

	
}
