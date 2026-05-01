/**
 * 
 */
package simulator;

import java.util.ArrayList;

/**
 * Abstract to hold AL of observers
 */
public abstract class Subject
{
	ArrayList<Observer> listeners = new ArrayList<>();

	void register(Observer observer)
	{
		listeners.add(observer);
	}

	void deregister(Observer observer)
	{
		listeners.remove(observer);
	}

	void notifyObservers()
	{
		for (Observer observer : listeners)
		{
			observer.update(this);
		}
	}
}
