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