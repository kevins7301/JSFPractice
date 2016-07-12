package at.jsfpractice.jsf22.testDB;

import java.util.ArrayList;


public class DecimalValueConstant 
{
	public static String[] decimalStrList = {"PRICE", "QUANTITY"};
	
	
	public static boolean IsDecimal(String decimalStr)
	{
		for (String str : decimalStrList)
		{
			if (str == decimalStr)
			{
				return true;
			}
		}
		
		return false;
	}
}
