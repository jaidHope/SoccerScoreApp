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

	public String getName()
	{
		return teamName;
	}
	public int getScore()
	{
		return teamScore;
	}

	@Override
	public int compareTo(Object o)
	{
		return 0;
	}
}
