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

	public Team getTeamOne()
	{
		return teamOne;
	}

	public Team getTeamTwo()
	{
		return teamTwo;
	}

}
