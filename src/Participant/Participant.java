package Participant;

import Game.Results;
import Gestures.Gestures;

public interface Participant 
{
	public void Chooses(Gestures gesture);
	public Gestures Chose();
	public Results Beats(Participant opponent);
}
