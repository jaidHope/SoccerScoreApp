import com.gradle.soccer.Game;
import com.gradle.soccer.GameParser;
import com.gradle.soccer.ScoreProcessor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SoccerAppTest
{

	/**
	 * This test checks that a new file object is created based on the file path passed in.
	 */
	@Test
	public void createFileObjectFromFilenameTest()
	{
		String sep = File.separator;
		String file_expected = new File("src" + sep + "main" + sep + "resources" + sep + "input.txt").getAbsolutePath();

		File file_actual = ScoreProcessor.createFileObjectFromFilename("src\\main\\resources\\input.txt");

		assertEquals(file_expected.toString(), file_actual.toString());
	}

	/**
	 * Test that the ScoreProcessor app is correctly able to determine whether the number of points passed in is exactly one.
	 */
	@Test
	public void checkOnePointTest_onePoint()
	{
		boolean expected = true;
		assertEquals(ScoreProcessor.checkOnePoint(1), expected);
	}

	/**
	 * Test that the ScoreProcessor app is correctly able to determine whether the number of points passed in is greater than one.
	 */
	@Test
	public void checkOnePointTest_multiplePoints()
	{
		boolean expected = false;
		assertEquals(ScoreProcessor.checkOnePoint(2), expected);
	}

	/**
	 * Test that the GameParser is correctly able to take in a HashMap of football results and sort them by points, and
	 * where the points are equal, alphabetically.
	 */
	@Test
	public void sortResultsTest_success()
	{
		HashMap<String, Integer> unsortedResults = new HashMap<>();
		unsortedResults.put("Arsenal", 4);
		unsortedResults.put("QPR", 1);
		unsortedResults.put("Liverpool", 1);
		unsortedResults.put("Man U", 2);

		HashMap<String, Integer> sortedResultsExpected = new HashMap<>();
		sortedResultsExpected.put("Arsenal", 4);
		sortedResultsExpected.put("Man U", 2);
		sortedResultsExpected.put("Liverpool", 1);
		sortedResultsExpected.put("QPR", 1);

		HashMap<String, Integer> sortedResultsActual =  ScoreProcessor.sortResults(unsortedResults);

		assertTrue(sortedResultsActual.equals(sortedResultsExpected));
	}

	/**
	 * Test that the ScoreProcessor app correctly orders teams alphabetically when all teams have the same number of points.
	 */
	@Test
	public void sortResultsTest_sameScores()
	{
		HashMap<String, Integer> unsortedResults = new HashMap<>();
		unsortedResults.put("Team D", 1);
		unsortedResults.put("Team B", 1);
		unsortedResults.put("Team A", 1);
		unsortedResults.put("Team C", 1);

		HashMap<String, Integer> sortedResultsExpected = new HashMap<>();
		sortedResultsExpected.put("Team A", 1);
		sortedResultsExpected.put("Team B", 1);
		sortedResultsExpected.put("Team C", 1);
		sortedResultsExpected.put("Team D", 1);

		HashMap<String, Integer> sortedResultsActual =  ScoreProcessor.sortResults(unsortedResults);

		assertTrue(sortedResultsActual.equals(sortedResultsExpected));
	}

	/**
	 * Test that the ScoreProcessor app correctly parses lines of data representing soccer matches.
	 */
	@Test
	public void parseLine_success()
	{
		GameParser gameParser = new GameParser();

		String data = "Lions 3, Snakes 3";
		Game game = new Game();
		ArrayList<Game> expectedList = new ArrayList<>();

		game.setTeamOne("Lions", 3);
		game.setTeamTwo("Snakes", 3);
		expectedList.add(game);

		gameParser.parseLine(data);

		ArrayList<Game> gamesListActual = gameParser.getGamesList();

		assertEquals(gamesListActual.get(0).getTeamOne().getName(),
				expectedList.get(0).getTeamOne().getName());
		assertEquals(gamesListActual.get(0).getTeamOne().getScore(),
				expectedList.get(0).getTeamOne().getScore());

		assertEquals(gamesListActual.get(0).getTeamTwo().getName(),
				expectedList.get(0).getTeamTwo().getName());
		assertEquals(gamesListActual.get(0).getTeamTwo().getScore(),
				expectedList.get(0).getTeamTwo().getScore());
	}

	/**
	 * Test that the ScoreProcessor app is able to calculate overall points for each time when multiple individual matches are passed in.
	 */
	@Test
	public void calcTableScores_success()
	{
		// set up what is to be tested

		ArrayList<Game> games = new ArrayList<>();

		Game game = new Game();
		game.setTeamOne("Lions", 6);
		game.setTeamTwo("FC Awesome", 0);
		games.add(game);

		Game game2 = new Game();
		game2.setTeamOne("Lions", 1);
		game2.setTeamTwo("FC Awesome", 1);
		games.add(game2);

		Game game3 = new Game();
		game3.setTeamOne("Snakes", 1);
		game3.setTeamTwo("Grouches", 4);
		games.add(game3);

		GameParser gameParser = new GameParser(games);

		//act
		HashMap<String, Integer> resultsTable = gameParser.calculateTableScores();

		assertEquals(resultsTable.get("Lions"), 4);
		assertEquals(resultsTable.get("FC Awesome"), 1);
		assertEquals(resultsTable.get("Grouches"), 3);
		assertEquals(resultsTable.get("Snakes"), 0);

	}
}
