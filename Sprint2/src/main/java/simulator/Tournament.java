/**
 * 
 */
package simulator;

import java.util.ArrayList;

/**
 * 
 */
public class Tournament
{
	public Game game;
	public Bracket bracketType;
	public ArrayList<TourneyPlayer> scoreboard;
	public boolean registrationOpen; // sprint 3 implementation
	public boolean running; // sprint 3 implementation

	public String name;


	@Override
	public String toString()
	{
		return "Tournament: " + name;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the game
	 */
	public Game getGame()
	{
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game)
	{
		this.game = game;
	}

	/**
	 * @return the bracketType
	 */
	public Bracket getBracketType()
	{
		return bracketType;
	}

	/**
	 * @param bracketType the bracketType to set
	 */
	public void setBracketType(Bracket bracketType)
	{
		this.bracketType = bracketType;
	}

	/**
	 * @return the registrationOpen
	 */
	public boolean isRegistrationOpen()
	{
		return registrationOpen;
	}

	/**
	 * @param registrationOpen the registrationOpen to set
	 */
	public void setRegistrationOpen(boolean registrationOpen)
	{
		this.registrationOpen = registrationOpen;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning()
	{
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}

	/**
	 * @param scoreboard the scoreboard to set
	 */
	public void setScoreboard(ArrayList<TourneyPlayer> scoreboard)
	{
		this.scoreboard = scoreboard;
	}

	/**
	 * @return the scoreboard
	 */
	public ArrayList<TourneyPlayer> getScoreboard()
	{
		return scoreboard;
	}

	/**
	 * @param scoreboard
	 * @param game
	 * @param bracket
	 */
	public Tournament(ArrayList<TourneyPlayer> scoreboard, Game game, Bracket bracket)
	{
		this.scoreboard = scoreboard;
		this.game = game;
		this.bracketType = bracket;
		this.registrationOpen = false;
		this.running = false;
	}

	public void registerPlayer(Participant player)
	{
		if (player == null)
		{
			throw new IllegalArgumentException("Invalid player");
		}
		scoreboard.add(new TourneyPlayer(player));
	}

	public void playTournament()
	{
		Tuple pair = bracketType.nextPair(game.currentState);

		while (pair != null)
		{
			System.out.println("\nTournament: " + pair.p1.player.name + " VS " + pair.p2.player.name);

			game.play(pair.p1.player, pair.p2.player);

			State state = game.currentState;
			pair.p1.totalScore += state.p1Score;
			pair.p1.playersPlayed.add(pair.p2);
			pair.p2.totalScore += state.p2Score;
			pair.p2.playersPlayed.add(pair.p1);

			pair = bracketType.nextPair(game.currentState);

		}
	}
}
