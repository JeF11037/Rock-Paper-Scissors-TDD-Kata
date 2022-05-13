package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Game.Results;
import Gestures.Gestures;
import Participant.Player;

public class RockTests 
{
	private final Gestures id = Gestures.Rock;
	private final Gestures advantageOver = Gestures.Scissors;
	
	Player player;
	Player opponent;
	
	@Before()
	public void SetUp() throws Exception
	{
		player = new Player();
		opponent = new Player();
	}
	
	@Test
	public void RockBeatsScissorsTest() 
	{
		player.Chooses(id);
		opponent.Chooses(advantageOver);
		assertEquals(
				"Given I have chosen "+this.id+"\r\n"
				+ "When the opponent chooses "+this.advantageOver+"\r\n"
				+ "Then I should win", 
				Results.Win, 
				player.Beats(opponent));
		
		player.Chooses(advantageOver);
		opponent.Chooses(id);
		assertEquals(
				"Given I have chosen "+this.advantageOver+"\r\n"
				+ "When the opponent chooses "+this.id+"\r\n"
				+ "Then they should win\r\n",
				Results.Lose, 
				player.Beats(opponent));
	}
}
