# SoccerScoreApp

This app takes in a file of footballl matches in a league and ouputs the overall ranking of each team on the command line.

In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points.
If two or more teams have the same number of points, they should have the same rank and be
printed in alphabetical order (as in the tie for 3rd place in the sample data).

## Sample input
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0

## Sample Output
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pt

## Technical Notes
- This app has been programmed in Java and is built using gradle. The gradle wrapper required is shipped with this project.
- 7 automated JUnit tests have been written using JUnit 5. All are passing.
