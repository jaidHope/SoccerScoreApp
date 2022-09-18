package com.gradle.soccer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScoreProcessor
{
	/**
	 * Entry point to the SoccerScoreApp. Run the application from the command line by running gradlew run --args="path/to/file" (platform independent)
	 *
	 * @param args Absolute path of the file containing the football matches.
	 */
	public static void main(String[] args)
	{
		String filename = args[0];

		GameParser parser = new GameParser();
		Scanner filereader = null;

		try
		{
			filereader = new Scanner(createFileObjectFromFilename(filename));
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}

		while (filereader.hasNextLine())
		{
			String data = filereader.nextLine();
			parser.parseLine(data);
		}

		filereader.close();

		HashMap results = parser.calculateTableScores();
		HashMap<String, Integer> sortedResults = sortResults(results);
		printRankings(sortedResults);
	}

	public static File createFileObjectFromFilename(String filename)
	{
		String file = new File(filename).getAbsolutePath();
		return new File(file);
	}

	/**
	 * This method is used to print the final rankings of each soccer team, by iterating through the HashMap of sorted
	 * results and performing string processing in order to output the results in the correct format.
	 *
	 * As part of this processing, the team ranking is printed in list form (i.e. 1. 2. ... n.), and where two teams
	 * have the same number of points, they are ordered alphabetically, with the same list item number.
	 * @param sortedResults
	 */
	private static void printRankings(HashMap sortedResults)
	{
		Set results = sortedResults.entrySet();

		// counter to store each team's placing (list item number)
		int i = 1;
		Map.Entry prevTeam = null;

		Iterator iterator = results.iterator();

		// jump ahead one and set `prev` to the first iterator value. This means that `current` becomes the second value,
		// hence, a look-behind comparison is done.
		if (iterator.hasNext())
		{
			prevTeam = (Map.Entry)iterator.next();
		}

		while (iterator.hasNext())
		{
			Map.Entry currentTeam = (Map.Entry) iterator.next();

			//check if there are consecutive identical scores
			if (prevTeam.getValue() == currentTeam.getValue())
			{
				// if the team only has one point, then a slightly different string needs to be output.
				if (checkOnePoint((int)prevTeam.getValue()))
				{
					System.out.println(i + ". " + prevTeam.getKey() + ", " + prevTeam.getValue() + " pt");
				}
				else
				{
					System.out.println(i + ". " + prevTeam.getKey() + ", " + prevTeam.getValue() + " pts");
				}
			}
			else
			{
				if (checkOnePoint((int) prevTeam.getValue()))
				{
					System.out.println(i + ". " + prevTeam.getKey() + ", " + prevTeam.getValue() + " pt");
				} else
				{
					System.out.println(i + ". " + prevTeam.getKey() + ", " + prevTeam.getValue() + " pts");
				}
				i++;
			}

			//The value of the previous team now becomes the current team's name
			prevTeam = currentTeam;
		}

		/* To cater for the last team being processed, we need to add this block of code. Because of how the iterator works, once the above
		 * has finished executing, the value of the `prev` will be the last element in the HashMap, which still needs to
		 * be processed (`currentTeam` will be NULL)
		 */
		if (checkOnePoint((int)prevTeam.getValue()))
		{
			System.out.println(i + ". " + prevTeam.getKey() + ", " + prevTeam.getValue() + " pt");
		}
		else
		{
			System.out.println(i + ". " + prevTeam.getKey() + ", " + prevTeam.getValue() + " pts");
		}
	}

	public static boolean checkOnePoint(int goals)
	{
		if(goals == 1)
		{
			return true;
		}
		return false;
	}

	/**
	 * This method takes in a HashMap of unordered football teams and their results, and iterates through each entry, and calls sortValues()
	 * on the entire HashMap, so it can compare the current iterator entry to the whole HashMap.
	 *
	 * @param results
	 * @return A HashMap containing the sorted team-score pairs.
	 */
	public static HashMap<String, Integer> sortResults(HashMap results)
	{
		Set set = results.entrySet();

		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			Map.Entry map = (Map.Entry) iterator.next();
		}
		HashMap<String, Integer> map = sortValues(results);
		return map;
	}

	/**
	 * This method takes in an unsorted HashMap and sorts it by converting the HashMap to a LinkedList, and passing that
	 * LinkedList through to a custom comparator which compares the teams score values.
	 *
	 * Once sorting has been completed, the LinkedList is converted back into a HashMap in order to preserve the new order,
	 * and for ease of processing when outputting final points.
	 *
	 * @param map
	 * @return HashMap containing the sorted team-score pairs.
	 */
	private static HashMap sortValues(HashMap map)
	{
		List list = new LinkedList(map.entrySet());

		//Custom Comparator
		Collections.sort(list, new Comparator()
		{
			@Override
			public int compare(Object o1, Object o2)
			{
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		//copying the sorted list into HashMap to preserve the iteration order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}

		return sortedHashMap;
	}
}