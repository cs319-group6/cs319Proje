package ARSModel;

import java.io.*;
import java.util.Scanner;

public class SeatingPlanReaderTester {

	public static void main(String[] args) throws FileNotFoundException
	{
		SeatingPlanReader r1 = new SeatingPlanReader("C://Users//C.M.Yildiz//Desktop//seatPlan.txt");
		
		Scanner fileScanner = new Scanner (new File("C://Users//C.M.Yildiz//Desktop//seatPlan.txt"));
		
		fileScanner.useDelimiter(",");
		
		//�lk sat�rda plan boyutlar� vard�, bu nextler onu atlay�p direk plan'e gecmek icin
		System.out.println(fileScanner.next());
		System.out.println(fileScanner.next());
		System.out.println(fileScanner.next());

		String element="";
		
		while(fileScanner.hasNext())
		{
			element =fileScanner.next();
			//en sagdaki elementi al�nca yeni sat�ra gecti�i i�in i�lem yapmak istedi�im stringin sonuna "\n\r" da ekliyodu.
			//bu ondan kurtulmak i�in
			if(element.equals(("\r\n")))
				element =fileScanner.next();
			
			//RC-A-B-CR-C-D-CR-E-F-RC
			if(r1.isLeftMostCol(element)||r1.isRightMostCol(element) || r1.isSeatChar(element)|| r1.isCorridor(element))
			{
				if(!r1.isRightMostCol(element))
				{
					//put it
					System.out.print(element);
				}
				else
					System.out.println(element);
			}
			else
			{
				//LC A B CR C D CR E F RC sat�r�n� yazd�k art�k seatli sat�rlara ge�iyoruz
				// mesela ��yle bir sat�r 1,A1,B1,0,C1,D1,0,E1,F1,1
				//it is left row number. yani 1'i gorduk 1 i direk koy sonra da a1,b1.. leri koyacag�z
				System.out.print(element);

				while(fileScanner.hasNext())
				{
					element =fileScanner.next();
					
					if(element.equals(("\r\n")))
						element =fileScanner.next();
					
					if(r1.isDoor(element))
					{
						//put door representation
						System.out.print(element);
					}
					else if(r1.isEmergency(element))
					{
						//put emergency representation
						System.out.print(element);
					}
					else if(r1.isSpace(element))
					{
						//put space representation
						System.out.print(element);
					}
					else if(r1.isWC(element))
					{
						//put WC representation
						System.out.print(element);
					}
					else if(r1.isSeat(element))
					{
						//put seat representation
						System.out.print(element);
					}
					else
					{
						//last rowNumber put it
						//new line'a gec
						System.out.println(element);
					}
				}
			}
		}
		fileScanner.close();
	}
}
