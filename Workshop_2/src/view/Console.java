package view;

import java.util.Scanner;
import model.Member;

/**
 * The Class Console.
 */
public class Console
{

	/** The scanner. */
	Scanner scanner = new Scanner(System.in);

	/*
	 * Method that outputs the options you have in the console.
	 */

	/**
	 * Input.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	public String input() throws Exception
	{
		String input = "";

		System.out.println("\nThese are a Member Registry functions."
				+ " \nPlease enter the corresponding  number or (0) to Exit\n");
		System.out.println("1 Create a new member ");
		System.out.println("2 List all members as a compact list ");
		System.out.println("3 List all members as a verbose list");
		System.out.println("4 Delete a member ");
		System.out.println("5 Change a member info");
		System.out.println("6 Look at specific member's information");
		System.out.println("7 Register a new boat ");
		System.out.println("8 Delete a boat ");
		System.out.println("9 Change a boat's information\n ");
		System.out.print("Type your choice number here:  ");

		input = scanner.next();

		return input;
	}

	/**
	 * Fail message.
	 */
	public void failMessage()
	{
		System.out.println("Invalid value");
	}

	/**
	 * Adds the member.
	 *
	 * @return the string[]
	 */
	public String[] addMember()
	{
		String[] arr = new String[2];

		System.out.print("Enter a member's name: ");
		String name = "";
		name = scanner.next();

		System.out.print("Enter the member's personal number (yymmdd-xxxx): ");
		String personalNumber = "";
		personalNumber = scanner.next();

		arr[0] = name;
		arr[1] = personalNumber;
		return arr;
	}

	/**
	 * Suc message.
	 */
	public void sucMessage()
	{
		System.out.println("operation accomplished successfully");
	}

	/**
	 * To print compact and verbose lists.
	 *
	 * @param str the str
	 */
	public void printList(String str)
	{
		System.out.println(str);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId()
	{
		System.out.print("Enter the wanted Member's ID: ");
		String id = scanner.next();
		return id;
	}

	/**
	 * Gets the boat id.
	 *
	 * @return the boat id
	 */
	public String getBoatId()
	{
		System.out.print("Enter the wanted boat ID: ");
		String id = scanner.next();
		return id;
	}

	/**
	 * Adds the boat.
	 *
	 * @return the string[]
	 */
	public String[] addBoat()
	{
		String[] arr = new String[2];
		System.out.println("Select boat type:\n1: Sailboat\n2: Motorsailer\n3: kayak/Canoe\n4: Others");
		arr[0] = scanner.next();
		System.out.print("Enter the length: ");
		arr[1] = scanner.next();
		return arr;
	}

	/**
	 * Prints the member.
	 *
	 * @param member the member
	 */
	public void printMember(Member member)
	{
		System.out.println("Name: " + member.getName() + "\nPersonal Number: " + member.getPersonalNumber()
				+ "\nNumber Of boats: " + member.getBoatsList().size());

	}
}
