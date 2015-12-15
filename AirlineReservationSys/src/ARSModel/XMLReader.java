package ARSModel;
import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLReader implements SeatPlanReaderStrategy
{
	private File fXmlFile;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private NodeList nList;
	private int economyRowBound;
	
	public XMLReader(String fileName) throws ParserConfigurationException, SAXException, IOException
	{
		fXmlFile = new File(fileName);
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(fXmlFile);
		nList = doc.getElementsByTagName("cell");
		economyRowBound=13;
	}
	
	public Element getCell(int index)
	{
		return (Element) nList.item(index);
	}
	
	public String getType(int index)
	{
		return getCell(index).getElementsByTagName("type").item(0).getTextContent();
	}
	public String getY(int index)
	{
		return getCell(index).getElementsByTagName("Y").item(0).getTextContent();
	}
	public String getX(int index)
	{
		return getCell(index).getElementsByTagName("X").item(0).getTextContent();
	}
	public String getRow(int index)
	{
		return getCell(index).getElementsByTagName("row").item(0).getTextContent();
	}
	public String getColChar(int index)
	{
		return getCell(index).getElementsByTagName("colChar").item(0).getTextContent();
	}
	
	public boolean isFirstRow(int index)
	{
		return getY(index).equals("0");
	}
	
	public boolean isSeat(int index)
	{
		return getType(index).equals("Seat");
	}
	
	public boolean isWC(int index)
	{
		return getType(index).equals("WC");
	}
	
	public boolean isCorridor(int index)
	{
		return getType(index).equals("CR");
	}
	
	public boolean isLeftMostColumn(int index)
	{
		return getType(index).substring(0,3).equals("LMC");
	}
	
	public boolean isRightMostColumn(int index)
	{
		return getType(index).substring(0,3).equals("RMC");
	}
	
	public boolean isDoor(int index)
	{
		return getType(index).equals("DR");
	}
	
	public boolean isEmergency(int index)
	{
		return (isLeftMostColumn(index) || isRightMostColumn(index)) && getType(index).substring(getType(index).length()-1, getType(index).length()).equals("E");
	}
	
	public boolean isWing(int index)
	{
		return (isLeftMostColumn(index) || isRightMostColumn(index)) && getType(index).substring(getType(index).length()-1, getType(index).length()).equals("W");
	}
	
	public boolean isSpace(int index)
	{
		return getType(index).equals("X");
	}
	
	public String getSeat(int index)
	{
		return getColChar(index)+getRow(index);
	}
		
	public boolean isEconomy(int index)
	{
		return isSeat(index) && economyRowBound<Integer.parseInt(getY(index));
	}

	@Override
	public void readShowSeatPlan() 
	{
		// TODO Auto-generated method stub
	}
}
