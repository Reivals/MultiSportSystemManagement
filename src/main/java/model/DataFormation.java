package model;

import java.util.Calendar;
import java.util.Date;

public class DataFormation {
	
	public static String translateMonthToString()
	{
		
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int month = cal.get(Calendar.MONTH);
		switch(month)
    	{
    	case 0:
    		return "Styczen";
    	case 1:
    		return "Luty";
    	case 2:
    		return "Marzec";
    	case 3:
    		return "Kwiecien";
    	case 4:
    		return "Maj";
    	case 5:
    		return "Czerwiec";
    	case 6:
    		return "Lipiec";
    	case 7:
    		return "Sierpien";
    	case 8:
    		return "Wrzesien";
    	case 9:
    		return "Pazdziernik";
    	case 10:
    		return "Listopad";
    	case 11:
    		return "Grudzien";
    		
    	}
		return null;
	}

}
