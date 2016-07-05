package at.jsfpractice.jsf22.example02;

import java.io.Serializable;

public class Person implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
    private int id;
    private String name;
    private String email;
    
    public Person(int id, String name, String email) 
    {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
