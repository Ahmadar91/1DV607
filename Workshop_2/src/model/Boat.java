package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Member")

public class Boat
{

	private int id;
	private double length;
	@XmlElement
	private Type typ;

	@XmlEnum
	public enum Type
	{
		Sailboat, Motorsailer, kayakOrCanoe, Others, Unknown
	}

	public Type getType()
	{
		return typ;
	}

	public void setType(Type type)
	{
		this.typ = type;
	}

	public void selectType(int x)
	{
		if (x == 1)
		{
			typ = Type.Sailboat;
		} else if (x == 2)
		{
			typ = Type.Motorsailer;
		} else if (x == 3)
		{
			typ = Type.kayakOrCanoe;
		} else if (x == 4)
		{
			typ = Type.Others;
		} else
		{
			typ = Type.Unknown;
		}
	}

	/*
	 * Constructor
	 */
	public Boat()
	{

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
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * get length
	 * 
	 * @return the length
	 */
	public double getLength()
	{
		return length;
	}

	/**
	 * set length
	 * 
	 * @param length
	 */
	public void setLength(double length)
	{
		this.length = length;
	}

}
