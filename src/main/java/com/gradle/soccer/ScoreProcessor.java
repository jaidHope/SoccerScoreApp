package com.gradle.soccer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScoreProcessor
{
	public static void main(String[] args)
	{
		System.out.println(" Specify file name with absolute or relative filepath: ");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine();

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
		scanner.close();

		HashMap results = parser.calculateTableScores();
		HashMap<String, Integer> sortedResults = sortResults(results);
		printRankings(sortedResults);
	}

	public static File createFileObjectFromFilename(String filename)
	{
		String file = new File(filename).getAbsolutePath();
		return new File(file);
	}

	private static void printRankings(HashMap sortedResults)
	{
		Set results = sortedResults.entrySet();

		// counter to store each team's placing
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
