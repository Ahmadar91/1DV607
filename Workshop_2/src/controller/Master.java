package controller;

import model.Boat;
import model.Member;
import model.Registry;
import view.Console;
import view.ListView;

public class Master
{

	/**
	 * Run.
	 *
	 * @param manage  the manage
	 * @param console the console
	 * @throws Exception the exception
	 */
	public void run(Registry manage, Console console) throws Exception
	{
		ListView lView = new ListView();
		while (true)
		{
			String input = console.input();

			if (input.equals("1"))
			{
				addMember(manage, console);
			} else if (input.equals("2"))
			{
				console.printList(lView.compactList(manage));
			} else if (input.equals("3"))
			{
				console.printList(lView.verboseList(manage));
			} else if (input.equals("4"))
			{
				removeMember(manage, console);
			} else if (input.equals("5"))
			{
				updateMember(manage, console);
			} else if (input.equals("6"))
			{
				getMember(manage, console);
			} else if (input.equals("7"))
			{
				addBoat(manage, console);
			} else if (input.equals("8"))
			{
				removeBoat(manage, console);
			} else if (input.equals("9"))
			{
				updateBoat(manage, console);
			} else if (input.equals("0"))
			{
				console.sucMessage();
				System.exit(1);
			} else
			{
				console.input();
			}
		}
	}

	/**
	 * Adds the member.
	 *
	 * @param registry the registry
	 * @param console  the console
	 * @throws Exception the exception
	 */
	private void addMember(Registry registry, Console console) throws Exception
	{
		Validator validator = new Validator();

		Member member = new Member();
		boolean x = true;
		String arr[] = new String[2];
		while (x)
		{
			arr = console.addMember();
			if (!validator.checkPersonalNum(arr[1]))
			{
				console.failMessage();
			} else
			{
				x = false;
			}

		}
		member.setName(arr[0]);
		member.setPersonalNumber(arr[1]);
		registry.addMember(member);
	}

	/**
	 * Removes the member.
	 *
	 * @param manage  the manage
	 * @param console the console
	 */
	private void removeMember(Registry manage, Console console)
	{
		Validator validator = new Validator();
		int x = -1;
		String memberId = "";
		while (x == -1)
		{
			memberId = console.getId();
			x = validator.validate(memberId, "int");

			if (x == 0)
			{
				if (manage.removeMember(Integer.parseInt(memberId)))
				{
					console.sucMessage();
					continue;
				} else
				{
					x = -1;
					console.failMessage();
				}

			}
		}
	}

	/**
	 * Gets the member.
	 *
	 * @param manage  the manage
	 * @param console the console
	 * @return the member
	 */
	private void getMember(Registry manage, Console console)
	{
		Member member = new Member();
		Validator validator = new Validator();
		int x = -1;
		String memberId = "";
		while (x == -1)
		{
			memberId = console.getId();
			x = validator.validate(memberId, "int");

			if (x == 0)
			{
				member = manage.getMemberById(Integer.parseInt(memberId));
				console.printMember(member);
			}

		}
	}

	/**
	 * Update member.
	 *
	 * @param registry the registry
	 * @param console  the console
	 */
	private void updateMember(Registry registry, Console console)
	{
		Validator validator = new Validator();

		Member newMember = new Member();
		int x = -1;

		String memberId = "";
		while (x == -1)
		{
			memberId = console.getId();
			x = validator.validate(memberId, "int");

			if (x == 0)
			{
				if (registry.isMemberExisted(Integer.parseInt(memberId)))
				{
					continue;
				} else
				{
					x = -1;
					console.failMessage();
				}

			}
		}
		String arr[] = console.addMember();

		newMember.setPersonalNumber(arr[1]);
		while (!validator.checkPersonalNum(arr[1]))
		{
			arr = console.addMember();
			console.failMessage();
		}
		newMember.setName(arr[0]);
		registry.updataMember(Integer.parseInt(memberId), newMember);
		console.sucMessage();

	}

	/**
	 * Adds the boat.
	 *
	 * @param manage  the manage
	 * @param console the console
	 */
	private void addBoat(Registry manage, Console console)
	{
		Validator validator = new Validator();

		Boat boat = new Boat();
		int x = -1;
		String memberId = "";
		while (x == -1)
		{
			memberId = console.getId();
			x = validator.validate(memberId, "int");

			if (x == 0)
			{
				if (manage.isMemberExisted(Integer.parseInt(memberId)))
				{
					String arr[] = console.addBoat();
					x = validator.validate(arr[0], "int|in:1,2,3,4");
					int y = validator.validate(arr[1], "double");
					if (x != 0 || y != 0)
					{
						x = -1;
						console.failMessage();
					} else
					{
						int type = Integer.parseInt(arr[0]);
						boat.selectType(type);
						if (Double.parseDouble(arr[1]) <= 0)
						{
							console.failMessage();
							addBoat(manage, console);
							continue;
						}
						boat.setLength(Double.parseDouble(arr[1]));

						manage.addBoat(Integer.parseInt(memberId), boat);

					}
				} else
				{
					x = -1;
					console.failMessage();
				}

			}
		}
		console.sucMessage();
	}

	/**
	 * Update boat.
	 *
	 * @param manage  the manage
	 * @param console the console
	 */
	private void updateBoat(Registry manage, Console console)
	{
		Validator validator = new Validator();

		Boat newBoat = new Boat();
		int x = -1;
		String memberId = "";
		String boatId = "";

		while (x == -1)
		{
			memberId = console.getId();
			x = validator.validate(memberId, "int");
			if (x != -1)
			{
				boatId = console.getBoatId();
				x = validator.validate(boatId, "int");
				if (x != -1)
				{
					String arr[] = console.addBoat();
					x = validator.validate(arr[0], "int|in:1,2,3,4");
					if (x == 0)
					{
						int y = validator.validate(arr[1], "double");
						if (x != 0 || y != 0)
						{
							x = -1;
							console.failMessage();
						} else
						{
							int type = Integer.parseInt(arr[0]);
							newBoat.selectType(type);
							newBoat.setLength(Double.parseDouble(arr[1]));
							if (Double.parseDouble(arr[1]) <= 0)
							{
								console.failMessage();
								updateBoat(manage, console);
								continue;
							}
							if (manage.updateBoat(Integer.parseInt(memberId), Integer.parseInt(boatId), newBoat))
							{
								console.sucMessage();
								continue;
							} else
							{
								x = -1;
								console.failMessage();
							}

						}
					} else
					{
						x = -1;
						console.failMessage();
					}
				}
			}

		}
	}

	/**
	 * Removes the boat.
	 *
	 * @param manage  the manage
	 * @param console the console
	 */
	private void removeBoat(Registry manage, Console console)
	{
		Validator validator = new Validator();

		int x = -1;
		String memberId = "";
		String boatId = "";

		while (x == -1)
		{
			memberId = console.getId();
			x = validator.validate(memberId, "int");
			if (x != -1)
			{
				boatId = console.getBoatId();
				x = validator.validate(boatId, "int");
				if (x != -1)
				{

					if (manage.removeBoat(Integer.parseInt(memberId), Integer.parseInt(boatId)))
					{
						console.sucMessage();
						continue;
					} else
					{
						x = -1;
						console.failMessage();
					}

				}
			} else
			{
				x = -1;
				console.failMessage();
			}
		}
	}

}
