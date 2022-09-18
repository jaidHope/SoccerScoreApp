package com.gradle.soccer;

import java.util.ArrayList;
import java.util.HashMap;

public class GameParser
{
	HashMap<String, Integer> teamRankings = new HashMap<String, Integer>();
	ArrayList<Game> games = new ArrayList<>();

	void parseLine(String data)
	{
		String[] teamData  = data.split(",");

		String teamOneScore = teamData[0].replaceAll("[^0-9]","");
		String teamTwoScore = teamData[1].replaceAll("[^0-9]","");

		String teamOneName = teamData[0].replaceAll("[^a-zA-Z_ ]*", "").strip();
		String teamTwoName = teamData[1].replaceAll("[^a-zA-Z_ ]*", "").strip();

		Game game = new Game();
		game.setTeamOne(teamOneName, Integer.parseInt(teamOneScore));
		game.setTeamTwo(teamTwoName, Integer.parseInt(teamTwoScore));

		games.add(game);

	}

	HashMap<String, Integer> calculateTableScores()
	{
	     for ( Game game : games)
	     {
		     String teamOneName = game.getTeamOne().getName();
		     String teamTwoName = game.getTeamTwo().getName();
		     int teamOneScore = game.getTeamOne().getScore();
		     int teamTwoScore = game.getTeamTwo().getScore();

			 //add each team to overall hashmap if not already there, with a score of 0
			 if (!teamRankings.containsKey(teamOneName))
			 {
				 teamRankings.put(teamOneName, 0);
			 }

		     if (!teamRankings.containsKey(teamTwoName))
		     {
			     teamRankings.put(teamTwoName, 0);
		     }

			 //calculate points based on game
		     if(teamOneScore > teamTwoScore)
		     {
				 int currentScore = teamRankings.get(teamOneName);
				 teamRankings.put(teamOneName, currentScore+3);
		     }
			 else if (teamOneScore == teamTwoScore)
		     {
			     int currentScoreOne = teamRankings.get(teamOneName);
			     int currentScoreTwo = teamRankings.get(teamTwoName);
			     teamRankings.put(teamOneName, currentScoreOne+1);
			     teamRankings.put(teamTwoName, currentScoreTwo+1);
		     }
			 else
		     {
			     int currentScore = teamRankings.get(teamTwoName);
			     teamRankings.put(teamTwoName, currentScore+3);
		     }

	     }
		return teamRankings;
	}
}
