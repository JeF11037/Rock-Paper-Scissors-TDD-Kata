package Gestures;

import Game.Results;

public enum Gestures
{
	Rock,
	Paper,
	Scissors;
	
	public Results Beats(Gestures gesture)
	{
		if (this == gesture)
			return Results.Draw;
		
		switch (this)
		{
			case Rock:
				return gesture == Gestures.Scissors ? Results.Win : Results.Lose;
			case Paper:
				return gesture == Gestures.Rock ? Results.Win : Results.Lose;
			case Scissors:
				return gesture == Gestures.Paper ? Results.Win : Results.Lose;
			default:
				return Results.Unknown;
		}
	}
}
