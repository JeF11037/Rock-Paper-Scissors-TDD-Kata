package Participant;

import Game.Results;
import Gestures.Gestures;

public class Player implements Participant
{
	private Gestures gesture;
	
	@Override
	public void Chooses(Gestures gesture)
	{
		this.gesture = gesture;
	}
	
	@Override
	public Gestures Chose()
	{
		return this.gesture;
	}
	
	@Override
	public Results Beats(Participant opponent)
	{
		return this.gesture.Beats(opponent.Chose());
	}
}
