package view;

import java.util.List;

import model.Boat;
import model.Member;
import model.Registry;

/**
 * The Class ListView.
 */
public class ListView
{

	/**
	 * Verbose list.
	 *
	 * @param list the list
	 * @return the string
	 */
	public String verboseList(List<Member> list)
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < list.size(); i++)
		{
			str.append("\nMId | MemName  |  PersonalNum  | BoatsNum | BootId | BoatType | BoatLength \n");
			str.append(list.get(i).getId() + "     ");
			str.append(list.get(i).getName() + "       ");
			str.append(list.get(i).getPersonalNumber() + "       ");
			str.append(list.get(i).getBoatsList().size() + "         ");
			List<Boat> boats = list.get(i).getBoatsList();
			for (Boat boat : boats)
			{
				str.append(boat.getId() + "        " + boat.getType() + "       " + boat.getLength() + "\n"
						+ "                                              ");
			}
		}
		return str.toString();
	}

	/**
	 * Verbose list.
	 *
	 * @param manage the manage
	 * @return the string
	 * @throws Exception the exception
	 */
	public String verboseList(Registry manage) throws Exception
	{
		return verboseList(manage.getMemberList());
	}

	/**
	 * Compact list.
	 *
	 * @param manage the manage
	 * @return the string
	 * @throws Exception the exception
	 */
	public String compactList(Registry manage) throws Exception
	{
		StringBuilder str = new StringBuilder();

		str.append("\nMID | MemName | BoatsNum \n");
		for (int i = 0; i < manage.getMemberList().size(); i++)
		{
			str.append(manage.getMemberList().get(i).getId() + "     ");
			str.append(manage.getMemberList().get(i).getName() + "       ");
			str.append(manage.getMemberList().get(i).getBoatsList().size() + "\n");

		}
		return str.toString();

	}
}
