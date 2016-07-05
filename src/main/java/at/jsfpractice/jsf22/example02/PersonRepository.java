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

/**
 * @author Michael Kurz
 */
@ManagedBean
@ApplicationScoped
public class PersonRepository implements Serializable 
{
	private Map<Integer, Person> persons = new TreeMap<Integer, Person>();
	
	@PostConstruct
	protected void init() 
	{
//		testdao = new testDAO();
    	
//    	try 
//    	{
//    		Connection con = testdao.getCon();
//			PreparedStatement pstmt = con.prepareStatement("select * from TEST");
//			ResultSet rs = pstmt.executeQuery();
//			
//			while (rs.next())
//			{
//				Person test = new Person(rs.getInt("ID"), rs.getString("NAME"), rs.getString("EMAIL"));
//				System.err.println("getPerson=="+test);
//			}
//			
//		} 
//    	catch (SQLException e) 
//    	{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
    	ArrayList personList = testDAO.getPersonData();
    	
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
