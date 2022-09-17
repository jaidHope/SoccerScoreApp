import com.gradle.soccer.ScoreProcessor;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class SoccerAppTest
{
	/*
	 * This test ensures that each line of input is correctly parsed and that each game's characteristics
	 * is added to the arraylist
	 */
	/*@Test
	public void testParseGameInFile()
	{
		ArrayList game_expected = new ArrayList();
		game_expected.add("Lions");
		game_expected.add(3);
		game_expected.add("Snakes");
		game_expected.add(3);

		ScoreProcessor app = new ScoreProcessor();
		Game game = app.parseInput();

		assertArrayEquals(app.parseLine(), game_expected);
	}
*/
	/*
	 * This test ensures that each line of input entered on the command line is correctly parsed and that each game's characteristics
	 * is added to the arraylist
	 */
	@Test
	public void testParseGameOnCmdLine()
	{

	}
}
