package com.gradle.soccer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScoreProcessor
{
	public static void main(String[] args)
	{
			System.out.println(" Specify file name: ");
			Scanner scanner = new Scanner(System.in);
			String filename = scanner.nextLine();
			File file = new File(filename);
			GameParser parser = new GameParser();

			try
			{
				Scanner filereader = new Scanner(file);
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

			catch (FileNotFoundException e)
			{
				throw new RuntimeException(e);
			}
	}

	private static void printRankings(HashMap sortedResults)
	{
		Set results = sortedResults.entrySet();

		int i = 1;
		Iterator iterator = results.iterator();
		while (iterator.hasNext())
		{
			Map.Entry team = (Map.Entry) iterator.next();

			//check if there are consecutive identical scores
			if (team.getValue() == ((Map.Entry<?, ?>) iterator.next()).getValue())
			{
				// if the team only has one point, then a slightly different string needs to be output.
				if (checkOnePoint((int)team.getValue()))
				{
					System.out.println(i + ". " + team.getKey() + ", " + team.getValue() + " pt");
				}
				else
				{
					System.out.println(i + ". " + team.getKey() + ", " + team.getValue() + " pts");
				}
			}
			else
			{
				if (checkOnePoint((int) team.getValue()))
				{
					System.out.println(i + ". " + team.getKey() + ", " + team.getValue() + " pt");
				} else
				{
					System.out.println(i + ". " + team.getKey() + ", " + team.getValue() + " pts");
				}
				i++;
			}


		}
	}

	private static boolean checkOnePoint(int goals)
	{
		if(goals == 1)
		{
			return true;
		}
		return false;
	}

	private static HashMap<String, Integer> sortResults(HashMap results)
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

		//copying the sorted list in HashMap to preserve the iteration order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}

		return sortedHashMap;
	}
	private static void parseFileInput(Scanner scanner)
	{
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();
			System.out.println(data);
		}
	}
}
