import java.io.*;
import java.util.*;

public class SeatingPlanReader 
{
	private String seatingPlanFileName;
	private String seatColChars;
	private String spaceCh;
	private String emergencyStr;
	private String corridor;
	private String WC;
	private int economyRowBound;
	private String leftMostCol;
	private String rightMostCol;
	private String door;
	private String wing;
	Scanner fileScanner;
	File plan;
	private int rowCountOfSchema;
	private int colCountOfSchema;
	private int rowCountSeat;
	
	public SeatingPlanReader(String inputFileName) throws FileNotFoundException
	{
		wing="W";
		seatColChars="ABCDEF";
		spaceCh="0";
		emergencyStr="EE";
		corridor="CR";
		door="DR";
		WC="WC";
		economyRowBound = 11;
		leftMostCol="LC";
		rightMostCol="RC";
		seatingPlanFileName=inputFileName;
		plan = new File(seatingPlanFileName);
		fileScanner = new Scanner (plan);
		
		fileScanner.useDelimiter(",");
		rowCountOfSchema = fileScanner.nextInt();

		rowCountSeat=rowCountOfSchema-1;
		colCountOfSchema = fileScanner.nextInt();

		fileScanner.close();
	}

	public boolean isWC(String str)
	{
		return str.equals(WC);
	}
	
	public boolean isDoor(String str)
	{
		return str.equals(door);
	}
	
	public boolean isWing(String str)
	{
		if(!isWC(str)&& str.substring(0,1).equals(wing))
			return true;
		return false;
	}
	
	public boolean isEmergency(String str)
	{
		return str.length()>=emergencyStr.length() && str.substring(0,emergencyStr.length()).equals(emergencyStr);
	}
	
	public boolean isCorridor(String str)
	{
		return str.equals(corridor);
	}
	
	public boolean isRightMostCol(String str)
	{
		return str.equals(rightMostCol);
	}
	
	public boolean isLeftMostCol(String str)
	{
		return str.equals(leftMostCol);
	}
	
	public boolean isSeat(String str) throws NumberFormatException
	{
		if(seatColChars.indexOf(str.charAt(0))==-1 || str.length()<2)
			return false;
		else
		{
			return Integer.parseInt(str.substring(1))<=rowCountSeat && Integer.parseInt(str.substring(1))>=1;	
		}
	}
	
	public boolean isSeatChar(String str)
	{
		if(seatColChars.indexOf(str)==-1)
			return false;
		return true;
	}
	
	public int rowOfSeat(String str) throws NumberFormatException
	{
		return Integer.parseInt(str.substring(1));
	}
	
	public String colOfSeat(String str)
	{
		return str.substring(0,1);
	}
	
	public boolean isSpace(String str)
	{
		return str.equals(spaceCh);
	}
	
	public boolean isEconomy(String str)
	{
		return  economyRowBound < rowOfSeat(str);
	}
	
	public boolean isRowNumber(String str) throws NumberFormatException
	{		
		return isEmergency(str) || isWing(str) || (Integer.parseInt(str.substring(0))>=1 && Integer.parseInt(str.substring(0))<=36);
	}
}
