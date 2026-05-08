/**
 * 
 */
package simulator;

import java.util.ArrayList;

public abstract class Participant
{
	String name;
	ArrayList<State> memory;

	public Participant(String name)
	{
		this.name = name;
		this.memory = new ArrayList<>();
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the memory
	 */
	public ArrayList<State> getMemory()
	{
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	public void setMemory(ArrayList<State> memory)
	{
		this.memory = memory;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	public void addMemory(State state)
	{
		memory.add(state);
	}

	public abstract int makeChoice(int actions);

	public void clearMemory()
	{
		memory.clear();
	}
}