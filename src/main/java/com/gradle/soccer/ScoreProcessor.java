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

				Map sortedResults = sortResults(results);

			}

			catch (FileNotFoundException e)
			{
				throw new RuntimeException(e);
			}
	}

	private static Map<String, Integer> sortResults(HashMap results)
	{
		Set set = results.entrySet();

		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			Map.Entry map = (Map.Entry) iterator.next();

		}
		Map<String, Integer> map = sortValues(results);
		return  map;
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
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
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
