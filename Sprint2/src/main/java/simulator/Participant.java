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

	public void addMemory(State state)
	{
		memory.add(state);
	}

	abstract int makeChoice(int actions);

	public void clearMemory()
	{
		memory.clear();
	}
}