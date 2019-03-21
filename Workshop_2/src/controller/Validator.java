package controller;

import java.util.ArrayList;
import java.util.List;

public class Validator
{
	private int statusCode = 0;

	/**
	 * Constructor
	 */

	public Validator()
	{

	}

	/**
	 * Method that returns the statusCode
	 * 
	 * @param value
	 * @return statusCode
	 */
	public int validate(String value)
	{
		return statusCode;
	}

	/**
	 * @param value
	 * @return statusCode Method that validate integers
	 */
	public int validateInt(String value)
	{
		try
		{
			Integer.parseInt(value);
			statusCode = 0;
		} catch (NumberFormatException ex)
		{ // handle exception
			statusCode = -1;
			// statusMessage = "Invalid value";
		}
		return statusCode;
	}

	/**
	 * 
	 * @param value
	 * @return statusCode Method that validate doubles
	 */
	public int validateDouble(String value)
	{
		try
		{
			Double.parseDouble(value);
			statusCode = 0;
		} catch (NumberFormatException ex)
		{ // handle exception
			statusCode = -1;
		}
		return statusCode;
	}

	/**
	 * 
	 * @param value
	 * @param args
	 * @return statusCode Method that validate
	 */
	public int validateIn(String value, List<String> args)
	{
		statusCode = 0;
		for (String arg : args)
		{
			if (value.compareTo(arg) == 0)
				return statusCode;
		}
		statusCode = -2;
		return statusCode;
	}

	/**
	 * 
	 * @param value
	 * @param d
	 * @return statusCode Method that validate a the positivity of a double
	 */
	public int validateMin(String value, double d)
	{
		double v = Double.parseDouble(value);
		if (v < d)
		{
			statusCode = -5;
		}
		return statusCode;
	}

	/**
	 * 
	 * @param value
	 * @param operations
	 * @return statusCode Method that validate an integer, double, minimum value or
	 *         specific inputs
	 */
	public int validate(String value, String operations)
	{
		statusCode = 0;

		List<String> vals = explode(operations, "|");
		for (String s : vals)
		{
			if (s.equals("int"))
			{
				if (validateInt(value) != 0)
					return statusCode;
			} else if (s.equals("double"))
			{
				if (validateDouble(value) != 0)
					return statusCode;
			} else if (s.startsWith("in:"))
			{
				List<String> cValues = explode(s.substring(s.indexOf("in:") + 3), ",");
				if (validateIn(value, cValues) != 0)
					return statusCode;
			} else if (s.startsWith("min:"))
			{
				String t = s.substring(4);

				double d = Double.parseDouble(t);

				if (validateMin(value, d) != 0)
					return statusCode;
			}
		}
		return statusCode;
	}

	/**
	 * 
	 * @param value
	 * @param delimiter
	 * @return list Method that delimites a string
	 */
	private List<String> explode(String value, String delimiter)
	{
		List<String> values = new ArrayList<>();
		if (value.isEmpty())
			return values;
		String tmp = value;
		int x = tmp.indexOf(delimiter);

		while (x != -1)
		{
			values.add(tmp.substring(0, x));
			tmp = tmp.substring(x + 1);
			x = tmp.indexOf(delimiter);
		}
		values.add(tmp);
		return values;
	}

	/**
	 * Method that gets the statusCode
	 * 
	 * @return statusCode
	 */
	public int getStatusCode()
	{
		return statusCode;
	}

	// ######### VALEIDATE PERSONAL NUMBER ##########

	// check if the PersonAL number is a correct identity number
	public boolean checkPersonalNum(String ident)
	{
		if (ident.length() != 11)
			return false;
		int year = Integer.parseInt(ident.substring(0, 2));
		int month = Integer.parseInt(ident.substring(2, 4));
		int day = Integer.parseInt(ident.substring(4, 6));

		if (day == 0 || day > 31)
		{
			return false;
		}

		if (month == 0 || month > 12)
		{
			return false;
		}

		if (month == 2)
		{
			if (day > 29)
			{
				return false;
			}

			if (!isLeapYear(year))
			{
				if (day > 28)
				{
					return false;
				}
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11)
			if (day > 30)
			{
				return false;
			}

		// check the last digit
		if (!checkSum(ident))
		{
			return false;
		}

		return true;
	}

	// check if year PART OF PERSONAL NUMBER is Leap
	private boolean isLeapYear(int y)
	{

		// if year is divisible by 4, it is a leap year.
		if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0)))
			return true;
		else
			return false;
	}

	// check if last digit from ID (checksum of first 9 digit) is correct
	private boolean checkSum(String ident)
	{

		String netID = getFirstPart(ident) + getSecondPart(ident);// get ID without dash/hyphen

		int[] idArray = new int[10];
		int sum = 0;

		for (int i = 0; i < netID.length(); i++)
		{

			idArray[i] = Character.getNumericValue(netID.charAt(i));

			if (i % 2 == 0)
			{
				idArray[i] = idArray[i] * 2;
				if (idArray[i] >= 10)
					idArray[i] = idArray[i] / 10 + idArray[i] % 10;
			}

			if (i != 9)
				sum += idArray[i];// sum of the first 9 digits
		}

		if (idArray[9] == (10 - sum % 10) % 10) // Check the last digit
			return true;
		else
			return false;
	}

	// get first part of ID
	private String getFirstPart(String ident)
	{

		String firstPart = ident.substring(0, 6);
		return firstPart;
	}

	// get last part of ID
	private String getSecondPart(String ident)
	{

		String secondPart = ident.substring(7, 11);
		return secondPart;
	}

}
