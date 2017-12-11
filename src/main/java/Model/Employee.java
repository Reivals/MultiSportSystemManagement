package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	int id;
	@Column(name="Name")
	private String name;
	@Column(name="Surname")
	private String surname;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<Integer,Boolean> months;

	public Employee() {}


	public Employee( String name, String surname) {
		months = new HashMap<Integer,Boolean>();
		for(int i=0;i<12;i++)
		{
			months.put(new Integer(i), false);
		}

		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private Boolean getFromMap(Integer key)
	{
		
	    Set<Entry<Integer, Boolean>> s = months.entrySet();
	    Iterator<Entry<Integer, Boolean>> iter = s.iterator();
	    int index = 0;
	    while(iter.hasNext())
	    {
	        Entry<Integer, Boolean> e = iter.next();
	        if(index == key.intValue())
	        {
	            return e.getValue();
	        }
	        index++;
	    }
	    return null;
	}
	
	public Boolean getValueFromMap()
	{
		
		Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int month = cal.get(Calendar.MONTH);
		return getFromMap(month);
		
	}



	
	
}

