package com.gradle.soccer;

public class Game
{
	Team teamOne;
	Team teamTwo;

	public Game() {}

	public void setTeamOne(String teamName, int score)
	{
		teamOne = new Team(teamName, score);
	}

	public void setTeamTwo(String teamName, int score)
	{
		teamTwo = new Team(teamName, score);
	}

	protected Team getTeamOne()
	{
		return teamOne;
	}

	protected Team getTeamTwo()
	{
		return teamTwo;
	}

}
