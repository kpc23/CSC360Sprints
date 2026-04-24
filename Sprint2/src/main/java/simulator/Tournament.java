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
	ArrayList<TourneyPlayer> scoreboard;
	Game game;
	Bracket bracketType;

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
