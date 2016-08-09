**************************************************
		MASTER MIND GAME
**************************************************

OBJECTIVE:
Guess a randomly generated 4 digit number.

DESCRIPTION:
This game is a brain teaser.  The objective
of the game is to guess a 4 digit number with
unlimited attempts.  There is a save function
and a load function.
Hints will be provided in the center area as to
how many correct guessed numbers are present and/
or how many correctly guessed numbers in the 
incorrect position.
A list of previously guessed numbers as well
as their statistics is featured on the right in
a list view.  Clicking on a number will bring 
up its data.
There is an answer button to display the
correct number.  Once pressed, guess entries
will be disabled until a new game is started.

UI FUNCTIONALITY:
Buttons:
-NEW: 	Starts a new game
-LOAD: 	Loads an existing game if it is present
-SAVE: 	Saves the current game
-ANSWER:Displays the answer of the current game
-GUESS:	Submits your guess for verification

Upper Display:
Displays your guessed numbers

Center Display:
Displays your guess followed by helpful statistics
about your guess

Right Display:
A list of past guesses.  Clicking on one will
show its statistics in Center Display

Bottom Field:
Where your guess can be entered.  It only accepts
answer of length 4 and only numerical characters
**************************************************
		DEVELOPMENT TOOLS
**************************************************

SOURCE FILES:
Located in the src directory.

CLASS FILES:
Located in the class directory.  Packages are also
located here.

COMPILING:
Utilize the .bat files located in the src director.
Change path information located in each .bat file
to reflect the location of the \Final parent
directory.

cclass.bat	-for compiling the GuessData.java
		and Constants.java files.
cui.bat		-for compiling the XmlDAO.java,
		UIFunctions.java,
		AlertWindow.java, and
		MasterMindUI.java.
cmain.bat	-for compiling MasterMindApp.java
rmain.bat	-for launching MasterMindApp.class