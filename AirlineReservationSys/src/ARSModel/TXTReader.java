package ARSModel;

import java.io.*;
import java.util.*;

public class TXTReader implements SeatPlanReaderStrategy
{
	private String fileName;
	private String spaceCh;
	private String emergencyStr;
	private String corridor;
	private String WC;
	private int economyRowBound;
	private String rightMostCol;
	private String door;
	private String wing;
	Scanner fileScanner;
	File plan;
	private int rowCountOfPlan;
	private int colCountOfPlan;
	private int rowCountSeat;
	private int colCountSeat;
	
	public TXTReader(String fileName) throws FileNotFoundException
	{
		wing="W";
		spaceCh="X";
		emergencyStr="E";
		corridor="CR,";
		door="DR";
		WC="WC";
		economyRowBound = 11;
		rightMostCol="RC,";
		this.fileName=fileName;
		plan = new File(this.fileName);
		fileScanner = new Scanner (plan);
		
		rowCountOfPlan = fileScanner.nextInt();

		rowCountSeat=rowCountOfPlan-1;
		colCountOfPlan = fileScanner.nextInt();
		colCountSeat=colCountOfPlan-1;
	}
	
	//Left most column indicating rows
	public boolean isLeftMostCol(String str)
	{
		return str.indexOf("|")!=-1 && str.indexOf("|")==str.length()-1;
	}
	
	//Right most column indicating rows
	public boolean isRightMostCol(String str)
	{
		return str.indexOf("|")!=-1 && str.indexOf("|")==0;
	}
	
	public boolean isFirstRow(String str)
	{
		return str.indexOf(",")!=-1;
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
		if((isRightMostCol(str) || isLeftMostCol(str)) && str.indexOf(wing)!=-1 )
			return true;
		else
			return false;
	}
	
	public boolean isEmergency(String str)
	{
		if((isRightMostCol(str) || isLeftMostCol(str)) && str.indexOf(emergencyStr)!=-1 )
			return true;
		else
			return false;
	}
	
	public boolean isCorridor(String str)
	{
		return str.equals(corridor);
	}
	
	public boolean isRightMostColHeader(String str)
	{
		return str.equals(rightMostCol);
	}

	public boolean isSeat(String str) throws NumberFormatException
	{
		if(str.equals("") || isRightMostCol(str) || isLeftMostCol(str) || isFirstRow(str) || isSpace(str) || isWC(str) || isDoor(str))
			return false;
		return true;
	}
	
	public String getSeat(String str)
	{
		if(isSeat(str))
			return str;
		else
			return "";
	}
	
	public String getColOfSeat(String str)
	{
		if(isSeat(str))
			return str.substring(0,1);
		else
			return "";
	}
	
	public int getRowOfSeat(String str)
	{
		if(isSeat(str))
			return Integer.parseInt(str.substring(1));
		else
			return -1;
	}
	
	public boolean isSpace(String str)
	{
		return str.equals(spaceCh);
	}
	
	public boolean isEconomy(String str)
	{
		return  economyRowBound < getRowOfSeat(str);
	}
	public void readShowSeatPlan()
	{
		String element ="";
		fileScanner.useDelimiter("\\t|\\n|\\r");

		while(fileScanner.hasNext())
		{
			element =fileScanner.next();
			//RC-A-B-CR-C-D-CR-E-F-RC
			if(isFirstRow(element))
			{
				if(isRightMostColHeader(element))
					System.out.println(element.substring(0, element.length()-1));
				else
					System.out.print(element.substring(0, element.length()-1));
			}
			else if(isLeftMostCol(element))
			{
				if(isWing(element))
					System.out.print(element.substring(0, element.length()-1));
				else if(isEmergency(element))
					System.out.print(element.substring(0, element.length()-1));
				else
					System.out.print(element.substring(0, element.length()-1));
			}
			else if(isRightMostCol(element))
			{
				if(isWing(element))
					System.out.println(element.substring(1));
				else if(isEmergency(element))
					System.out.println(element.substring(1));
				else
					System.out.println(element.substring(1));
			}
			else if(isSeat(element))
			{
				System.out.print(element);
			}
			else if(isSpace(element))
			{
				System.out.print(element);
			}
			else if(isWC(element))
			{
				System.out.print(element);
			}
			else if(isDoor(element))
			{
				System.out.print(element);
			}
		}
		fileScanner.close();
		
	}
	
}
