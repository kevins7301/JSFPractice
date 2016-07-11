package at.jsfpractice.jsf22.example02;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import at.jsfpractice.jsf22.testDB.testDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@ManagedBean
@ApplicationScoped
public class PersonRepository implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, Person> persons = new TreeMap<Integer, Person>();
	
	@PostConstruct
	public void init() 
	{
    	ArrayList personList = testDAO.getPersonData();
    	System.err.println("=dothis=" + personList);
    	for (int i = 0; i < personList.size(); i++)
    	{
    		HashMap personDataMap = (HashMap)personList.get(i);
    		
    		persons.put(new Integer(personDataMap.get("ID").toString()).intValue(), 
				new Person(new Integer(personDataMap.get("ID").toString()).intValue(), 
					personDataMap.get("NAME").toString(), personDataMap.get("EMAIL").toString()));
    	}
    	
//		persons.put(1, new Person(1, "Person 1", "person1@gamil.com"));
//		persons.put(2, new Person(2, "Person 2", "person2@gamil.com"));
//		persons.put(3, new Person(3, "Person 3", "person3@gamil.com"));
	}

	public Collection<Person> getPersons() {
		return this.persons.values();
	}

	public Person getPerson(int id) {
		return persons.get(id);
	}
}
