package Game;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import Gestures.Gestures;
import Participant.Player;
import Tests.RPSTestSuite;
import java.util.Random;
import java.util.Scanner;

public class Driver 
{
	private static Scanner scanner;
	private final static int delay_pause = 5;
	private final static int delay = 10;
	private static boolean on = true;
	private static boolean initial = true;
	
	private static Player player;
	private static Player opponent;
	
	public static void main(String args[])
	{
		Result tests_result = JUnitCore.runClasses(RPSTestSuite.class);
		
		for (Failure failure : tests_result.getFailures())
		{
			System.out.println(failure.toString());
		}
		
		if (!tests_result.wasSuccessful())
			throw new java.lang.Error("Unit tests failed!");
		
		animatedOutput("-- Welcome to Rock, Paper, Scissors! --", true);
		while(on)
		{
			if (initial)
				animatedOutputWithBlanks("This is the rules:\n\nWhen round starts You have to choose one of the gesture: Rock, Paper or Scissors...\n\nRock beats Scissors.\nPaper beats Rock.\nScissors beats Paper.\n\nAfter You chose gesture I will answer who won.", 2, 2);
			String round_start_message = initial ? "Are You ready ? (y/n)" : "One more round ? (y/n)";
			animatedOutputWithBlanks(round_start_message, 1, 1);
			scanner = new Scanner(System.in);
			if (!scanner.hasNext("[YyNn]"))
				continue;
			if (scanner.hasNext("[Nn]"))
			{
				animatedOutputWithBlanks("See You later!", 1, 1);
				try {Thread.sleep(500);} catch (Exception e) {e.printStackTrace();}
				on = false;
			}
			if (scanner.hasNext("[Yy]"))
			{
				if (initial)
					initial = false;
				
				player = new Player();
				opponent = new Player();
				
				while(player.Chose() == Gestures.Unknown)
				{
					player.Chooses(StringToGesture(scanner.nextLine()));
					if (player.Chose() == Gestures.Unknown)
						animatedOutputWithBlanks("Player chooses his gesture. (Type \'Rock\'/\'r\', \'Paper\'/\'p\' or \'Scissors\'/\'s\')...", 1, 1);
				}
				animatedOutput("\nOpponent chooses his gesture", false);
				animatedDottedPause();
				opponent.Chooses(GetRandomGesture());
				animatedOutput(" "+opponent.Chose(), true);
							
				Results result = player.Beats(opponent);
				switch (result)
				{
					case Win:
						animatedOutputWithBlanks(player.Chose()+" beats "+opponent.Chose()+". Player won!", 2, 2);
						continue;
					case Lose:
						animatedOutputWithBlanks(opponent.Chose()+" beats "+player.Chose()+". Opponent won!", 2, 2);
						continue;
					case Draw:
						animatedOutputWithBlanks("Nothing happened. Draw!", 2, 2);
						continue;
					case Unknown:
						animatedOutputWithBlanks("We cannot calculate results.", 2, 2);
						continue;
					default:
						throw new java.lang.Error("Expected \'Results\' object, but got nothing.");
				}
			}
		}
	}
	
	private static Gestures GetRandomGesture ()
	{
		Gestures[] gestures = {Gestures.Rock, Gestures.Paper, Gestures.Scissors};
		Random generator = new Random();
		return gestures[generator.nextInt(gestures.length)];
	}
	
	private static Gestures StringToGesture(String output)
	{
		switch (output.toLowerCase())
		{
			case "rock":
			case "r":
				return Gestures.Rock;
			case "paper":
			case "p":
				return Gestures.Paper;
			case "scissors":
			case "s":
				return Gestures.Scissors;
			default:
				return Gestures.Unknown;
		}
	}
	
	private static void animatedDottedPause()
	{
		for (int i = 0; i < 3; i++)
		{
			try {Thread.sleep(500);} catch (Exception e) {e.printStackTrace();}
			System.out.print(".");
		}
	}
	
	private static void animatedOutput(String output, boolean end)
	{
        char[] outputChars = output.toCharArray();
        for (char c : outputChars) 
        {
            System.out.print(c);
            try 
            {
	            if (c == ',' || c == '.' || c == ':')
	            {
	                Thread.sleep(delay_pause);
	            } 
	            else 
	            {
	                Thread.sleep(delay);
	            }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        if (end)
            System.out.println();
    }

	private static void animatedOutputWithBlanks(String output, int beforeBlankCount, int afterBlankCount)
    {
        for (int i = 0; i < beforeBlankCount; i++) 
        {
            System.out.println();
        }
        char[] outputChars = output.toCharArray();
        for (char c : outputChars) 
        {
            System.out.print(c);
            try 
            {
                if (c == ',' || c == '.')
                {
                    Thread.sleep(delay_pause);
                } 
                else 
                {
                    Thread.sleep(delay);
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < afterBlankCount; i++) 
        {
            System.out.println();
        }
    }
}
