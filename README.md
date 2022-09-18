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

## How to clean and build project
1. Sync out this repository
2. Navigate to the root folder of this project (i.e. SoccerScoreApp)
3. Open a command line window and type `gradlew clean build`

![image](https://user-images.githubusercontent.com/31666184/190923313-e5a9e694-cacc-47ac-ae71-0a74e4fdc6a7.png)


## How to run application
1. Once repository synced out and built, run the following command:
`gradlew run --args="absolute/path/to/filename"` (Windows command line)

![image](https://user-images.githubusercontent.com/31666184/190923336-96adb935-62a7-4e6d-8817-a558759c6577.png)

## How to run tests
1. Once project synced out, use the command `gradlew test` (Windows command line)
2. Navigate to the `build\test-results` (XML) or `build\reports\tests\test\index.html` (report) folder in order to view test results.

![image](https://user-images.githubusercontent.com/31666184/190923159-37b2059b-65ea-4b1b-8815-8291b74c656b.png)

