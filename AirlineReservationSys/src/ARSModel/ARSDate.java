package ARSModel;

import java.util.Date;
import java.util.Calendar;

public class ARSDate {

	protected java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected int day;
	protected int month;
	protected int year;
	protected int hour;
	protected int minute;
	protected int seconds;
	protected long milies;
	protected Calendar c;
	
	public ARSDate(){
		c = Calendar.getInstance();
		milies = c.getTimeInMillis();
		set();
				
	}
	
	public ARSDate(String date,String time){
		year = Integer.parseInt(date.substring(0,4));
		month = Integer.parseInt(date.substring(5,7)) ;
		day = Integer.parseInt(date.substring(8));
		hour = Integer.parseInt(time.substring(0,2));
		minute = Integer.parseInt(time.substring(3,5));
		seconds = Integer.parseInt(time.substring(6));
		c = Calendar.getInstance();
		c.set(year, month-1, day, hour, minute, seconds);
		//c.set(Integer.parseInt(date.substring(0,4))-1900, Integer.parseInt(date.substring(5,7)), Integer.parseInt(date.substring(8,10)), Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(3,5)), Integer.parseInt(time.substring(6)));
	}
	
	public ARSDate(int year, int month, int day, int hours, int minutes, int seconds){
		
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hours;
		this.minute = minutes;
		this.seconds = seconds;
		c = Calendar.getInstance();
		c.set(this.year, this.month, this.day, this.hour, this.minute, this.seconds);
	}
	
	public ARSDate (long millis){
		c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		set();
		//System.out.println(toString());
	}
	
	public void setDay(int value){
		day = value;
	}
	
	public void setMonth(int value){
		month = value;
		
	}
	
	public void setYear(int value){
		year = value;
		
	}
	
	public void setHours(int value){
		hour = value;
		
	}
	
	public void setMinutes(int value){
		minute = value;
		
	}
	
	public void setSeconds(int value){
		seconds = value;
		
	}
	
	public void setMilies(long millis){
		c.setTimeInMillis(millis);
		set();
		
	}
	
	public void set(){
		String date = sdf.format(c.getTime());
		year = Integer.parseInt(date.substring(0,4));
		month = Integer.parseInt(date.substring(5,7)) ;
		day = Integer.parseInt(date.substring(8,10));
		hour = Integer.parseInt(date.substring(11,13));
		minute = Integer.parseInt(date.substring(14,16));
		seconds = Integer.parseInt(date.substring(17));
	}
	
	public int getDay(){
		return day;
	}
	
	public int getMonth(){
		return month;
		
	}
	
	public int getYear(){
		return year;
		
	}
	
	public int getHours(){
		return hour;
		
	}
	
	public int getMinutes(){
		return minute;
		
	}
	
	public int getSeconds(){
		return seconds;
		
	}
	
	public long getMilies(){
		return c.getTimeInMillis();
		
	}
	
	public String getTime(){
		String hours, minutes, seconds;
		if(hour <10)
			hours = "0" + hour;
		else
			hours = "" + hour;
		if(minute<10)
			minutes = "0" + minute;
		else
			minutes = "" + minute;
		if(this.seconds <10)
			seconds = "0" +this.seconds;
		else
			seconds = "" +this.seconds;
		return hours +":" + minutes + ":" + seconds;
	}

	public String getDate(){
		String year,month,day;
		if(this.year <10)
			year = "0" + this.year;
		else
			year = "" + this.year;
		if(this.month < 10 ){
			month  ="0" + this.month;
		}else
			month = "" + this.month;
		if(this.day <10)
			day = "0" + this.day;
		else
			day = "" + this.day;
		
		return year + "-" + month + "-" + day;
	}
	
	public String toString(){
		return getDate() + " " + getTime();
	}

	
}
