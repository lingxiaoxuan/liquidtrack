package com.ibm.bluemix.samples;

public class CommMethod {
	
	public static String changeStatus(String Status)
	{
		String Result= "";
		
		if ("1".equals(Status))
		{
			Result = "In Progress";
		}
		else
		{
			Result = "Complete";
		}
		
		return Result;
	}
	
	public static String changeTechnology(String Technology,String TechOther)
	{
		String Result= "";
		
		if ("".equals(Technology) || Technology == null)
		{
			Technology = "0";
		}
		
		switch (Integer.parseInt(Technology))
		{
			case 1:Result = "Web";break;
			case 2:Result = "MF";break;
			case 3:Result = "Mobile";break;
			case 4:Result = TechOther;break;
		}
		
		return Result;
	}
	
	public static String changeEvent (String EventType,String EventOther)
	{
		String Result= "";
		
		if ("".equals(EventType) || EventType == null)
		{
			EventType = "0";
		}
		
		switch (Integer.parseInt(EventType))
		{
			case 1:Result = "Basic Task";break;
			case 2:Result = "Component Design";break;
			case 3:Result = "Component Development";break;
			case 4:Result = "Test";break;
			case 5:Result = EventOther;break;
		}
		
		return Result;
	}
	
	public static String changeFlag (String Flag)
	{
		String Result= "";
		
		if ("1".equals(Flag))
		{
			Result = "Yes";
		}
		else
		{
			Result = "No";
		}
		
		
		return Result;
	}
	
	public static String changePem (String PeMID)
	{
		String Result= "";
		
		switch (Integer.parseInt(PeMID))
		{
			case 1:Result = "Sun Yan Feng";break;
			case 2:Result = "Gu Jiang";break;
			case 3:Result = "Wu Yang";break;
		}
		
		return Result;
	}
}
