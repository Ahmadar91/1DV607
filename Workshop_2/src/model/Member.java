package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Members")

public class Member
{

	private int id;
	private String name;
	private String personalNumber;
	private ArrayList<Boat> boatsList;

	/*
	 * Constructor
	 */
	public Member()
	{
		boatsList = new ArrayList<Boat>();
	}

	/*
	 * Constructor
	 */
	public Member(String name, String personalNumber)
	{
		this.name = name;
		this.personalNumber = personalNumber;

	}

	/**
	 * @return the id get id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id set id
	 */
	@XmlElement(name = "id")
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the name get name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name set name
	 */
	@XmlElement(name = "name")
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the personal number get personal number
	 */
	public String getPersonalNumber()
	{
		return personalNumber;
	}

	/**
	 * @param personalNumber set personalNumber
	 */
	@XmlElement(name = "personal_number")
	public void setPersonalNumber(String personalNumber)
	{
		this.personalNumber = personalNumber;
	}

	/**
	 * @return the boatsList get boatsList
	 */
	public ArrayList<Boat> getBoatsList()
	{
		return boatsList;
	}

	/**
	 * @param boatsList set BoatsList
	 */

	@XmlElement(name = "boats_list")
	public void setBoatsList(ArrayList<Boat> boatsList)
	{
		this.boatsList = boatsList;
	}

}
