package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Members")
public class Registry
{
	public Registry(){
		memberList = new ArrayList<>();
	}
	/** The file. */
	private File file = new File("src/Members.xml");

	/** The member list. */
	@XmlElement(name = "Member")
	private ArrayList<Member> memberList;

	/** The index. */
	@XmlAttribute(name = "member_index")
	private int index = 0;

	/** The boat id. */
	@XmlAttribute(name = "boat_index")
	private int boatId = 0;
	

	
	/**
	 * Adds the member.
	 *
	 * @param member the member
	 * @throws Exception the exception
	 */
	public void addMember(Member member) throws Exception
	{
		member.setId(++this.index);
		this.memberList.add(member);
		writeToFile();
	}

	/**
	 * Updata member.
	 *
	 * @param id        the id
	 * @param newMember the new member
	 */
	public void updataMember(int id, Member newMember)
	{
		Member member = new Member();
		int idx = this.findMemberById(id);
		Member member1 = new Member();
		member1 = this.memberList.get(idx);
		member1.setName(newMember.getName());
		member1.setPersonalNumber(newMember.getPersonalNumber());
		member1.setBoatsList(member.getBoatsList());
		writeToFile();

	}

	/**
	 * Gets the member by id.
	 *
	 * @param id the id
	 * @return the member by id
	 */
	public Member getMemberById(int id)
	{
		Member m = new Member();
		for (int i = 0; i < memberList.size(); i++)
		{
			if (memberList.get(i).getId() == id)
			{
				m = memberList.get(i);
				return m;
			}
		}
		return null;
	}

	/**
	 * Gets the member list.
	 *
	 * @return the member list
	 */
	public List<Member> getMemberList()
	{
		List<Member> newList = new ArrayList<Member>(memberList);
		return newList;
	}

	/**
	 * Sets the member list.
	 *
	 * @param memberList the new member list
	 */
	public void setMemberList(ArrayList<Member> memberList)
	{
		this.memberList = memberList;
	}

	/**
	 * Removes the member.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean removeMember(int id)
	{

		int idx = this.findMemberById(id);
		if (idx != -1)
		{
			memberList.remove(idx);
			writeToFile();
			return true;
		} else
		{
			return false;
		}

	}

	/**
	 * Adds the boat.
	 *
	 * @param memberId the member id
	 * @param boat     the boat
	 */
	public void addBoat(int memberId, Boat boat)
	{
		Member member = new Member();
		boat.setId(++boatId);
		member = getMemberById(memberId);
		member.getBoatsList().add(boat);

		writeToFile();
	}

	/**
	 * Update boat.
	 *
	 * @param memberId the member id
	 * @param boatId   the boat id
	 * @param newBoat  the new boat
	 * @return true, if successful
	 */
	public boolean updateBoat(int memberId, int boatId, Boat newBoat)
	{
		Member member = null;
		if (isMemberExisted(memberId))
		{
			member = getMemberById(memberId);
		} else
		{
			return false;
		}
		ArrayList<Boat> list = member.getBoatsList();
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getId() == boatId)
			{

				list.get(i).setLength(newBoat.getLength());
				list.get(i).setType(newBoat.getType());
				writeToFile();
				return true;
			}
		}

		writeToFile();
		return false;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex()
	{
		return this.index;
	}

	/**
	 * Removes the boat.
	 *
	 * @param memberId the member id
	 * @param boatId   the boat id
	 * @return true, if successful
	 */
	public boolean removeBoat(int memberId, int boatId)
	{
		Member member = null;
		if (isMemberExisted(memberId))
		{
			member = getMemberById(memberId);
		} else
		{
			return false;
		}
		int id = this.getIndexOfBoatById(boatId, member);
		if (id != -1)
		{
			member.getBoatsList().remove(id);
			writeToFile();
			return true;
		}
		return false;
	}

	/**
	 * Checks if is member existed.
	 *
	 * @param id the id
	 * @return true, if is member existed
	 */
	public boolean isMemberExisted(int id)
	{
		return findMemberById(id) != -1;
	}

	/**
	 * Gets the index of boat by id.
	 *
	 * @param id     the id
	 * @param member the member
	 * @return the index of boat by id
	 */
	private int getIndexOfBoatById(int id, Member member)
	{
		for (int i = 0; i < member.getBoatsList().size(); i++)
		{
			if (member.getBoatsList().get(i).getId() == id)
			{
				return i;
			}

		}
		return -1;
	}

	/**
	 * Find member by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	private int findMemberById(int id)
	{
		for (int i = 0; i < memberList.size(); i++)
		{
			if (memberList.get(i).getId() == id)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Write to file.
	 */
	private void writeToFile()
	{
		try
		{
			writeMemberToXml(this);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Load.
	 *
	 * @return the registry
	 * @throws Exception the exception
	 */
	public Registry load() throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(Member.class, Registry.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Registry) unmarshaller.unmarshal(file);
	}

	/**
	 * Write member to xml.
	 *
	 * @param manage Write a member in XML file
	 * @throws Exception the exception
	 */
	public void writeMemberToXml(Registry manage) throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(Member.class, Registry.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(manage, file);
	}

}
