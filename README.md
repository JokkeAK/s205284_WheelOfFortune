# WheelOfFortune

This is the app made for the individual project for course 62550, User experience and mobile application development Fall 22. It is a modified game of Wheel of Fortune.

Created by student: s205284, Joakim Anker Kruse.

## Requirements for assignment:
1. The game rules listed should be implemented.
2. The game should be able to be played again when finished.
3. The application must be implemented in Kotlin
4. The application must be implemented using Jetpack Compose.
5. Modern Android architecture guidelines should be followed.
6. Modern Android state management should be used (as covered in the course). (LiveData, not covered in the course, is not considered as modern state mangement)
7. Version control (GitHub or GitLab) should be used. (Access should be given to Ian with Github username: “GitHubBruger”)
8. The app name must start with the student number.
(Edit the values/strings.xml e.g. <string name="app_name">s123456 Lykkehjulet</string>)
9. The minSdkVersion should be 28

## Game rules to implement for assignment:
1. The game is for one player.
2. When the game starts, a word is randomly chosen from predefined categories and
displayed along with the category.
3. The word is displayed with the letters hidden.
4. The player “spins the wheel”. (A graphically spinning wheel is not required to be
implemented this could be done simply by tapping a button and showing the result.)
5. The possible results of the “spinning the wheel” are: a number of points e.g 1000 or
“bankrupt”.
6. In the event of a value being shown, a letter (consonant or vowel) is chosen by the user
(from a keyboard or otherwise). If the letter is present, the user’s points total is
incremented by the value shown times the number of occurrences of the letter. The
occurrences of the letter are revealed in the word. If the letter is not present the user loses
a “life”.
7. In the event of “bankrupt” being shown, the user loses all their points.
8. The “wheel is spun” until the game is won or lost.
9. The game is won when all the letters have been found and the user still has a life.
10. The game is lost when the user has no lives left and the word has not been found.
11. A user starts with 5 “lives”.

## Which rules has been implemented?
All of the mentioned rules has been implemented and been thoroughly tested (although manually). Everything should work as described.

## Inspiration
The initial setup of the game logic and architecture has been inspired by the following codelab from Android which has been supplied to us in a lecture in the course:
https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-4-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-viewmodel-and-state#0

## Further improvement
Barring any bugs or glitches, all of the text is hard coded and could be fetched from a xml file in the resource folder. This would improve the localization capabilites of the app, however this was not a listed 
requirement so this was not a priority at all for the assignment. I could've gone further and added a spinning wheel animation instead of just having a button to "spin", but
this was also not a requirement as stated in the rules to implement, but could however be implemented with more time. 
