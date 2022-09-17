package com.gradle.soccer;

public class Team implements Comparable
{
	String teamName;
	int teamScore;

	public Team(String name, int score)
	{
		teamName = name;
		teamScore = score;
	}

	String getName()
	{
		return teamName;
	}
	int getScore()
	{
		return teamScore;
	}

	@Override
	public int compareTo(Object o)
	{
		return 0;
	}
}
