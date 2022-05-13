package Gestures;

import Game.Results;

public enum Gestures
{
	Rock,
	Paper,
	Scissors;
	
	public Results Beats(Gestures gesture)
	{
		switch (this)
		{
			case Rock:
				return gesture == Gestures.Scissors ? Results.Win : Results.Lose;
			case Scissors:
				return gesture == Gestures.Paper ? Results.Win : Results.Lose;
			default:
				return Results.Unknown;
		}
	}
}
