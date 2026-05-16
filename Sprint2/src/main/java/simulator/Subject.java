package simulator;

import java.util.ArrayList;

/**
 * Abstract to hold AL of observers
 */
public abstract class Subject
{
	public ArrayList<Observer> listeners = new ArrayList<>();

	public void register(Observer observer)
	{
		listeners.add(observer);
	}

	public void deregister(Observer observer)
	{
		listeners.remove(observer);
	}

	public void notifyObservers(State s)
	{
		for (Observer observer : listeners)
		{
			try {
				observer.update(this, s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
