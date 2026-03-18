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
	ArrayList<Observer> observers = new ArrayList<>();

	void register(Observer observer)
	{
		observers.add(observer);
	}

	void deregister(Observer observer)
	{
		observers.remove(observer);
	}

	void notifyObservers()
	{
		for (Observer observer : observers)
		{
			observer.update(this);
		}
	}
}
