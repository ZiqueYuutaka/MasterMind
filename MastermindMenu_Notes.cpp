/*

FOR THE FINAL

YOU MAY MAKE UP YOUR OWN RULES !!!

Clean up the functions  (i.e. make them neat !!!)

add a function to display the random number if the player wants to bail !!!

allow for 2 players

keep track of the number of tries
(maybe limit the player to 10 guesses)

After an entered guess, if there is no winner show the past guesses

have a function to show the score

use packages
use FX
store info in a file???





*/
			#include<stdio.h>
			#include <iostream.h>
			#include<conio.h>
			#include<stdlib.h>
			#include<string.h>
		

			

			const int ESC = 27;
		
int number; //Random Number that the computer generated !!
int n[4];// The 4 random numbers !!!!
int guess[4];// the 4 numbers that you guessed !!!
int MyGuess;// the entered palayer's guess !!!
const int ROWS = 4;
int RIGHT = 0;// number of correct postions
int WRONG = 0;// number of incorrect positions



// prototypes for your functions !!!!

		 void errorwin();// if you mess up
		 void play();// generate a random number
		 void player_guess();// enter a guess and call function winner
		 void restore_the_screen();
		 void reload();
		 void winner();// do we have a winner?

		 void menu(); // the main menu 
		 

		 
		 
		 void main()
		 {

		
		 menu();
		
		 restore_the_screen();	//Need this to clear the colors
        
		 
		 }/* main */

		 void menu()
		 {
		 int selection;
		
		int nextline=3;
		 
		 window(1,1,80,25);
		 textbackground(WHITE);
		 textcolor(WHITE);
		 clrscr(); // Apply the changes !!!!

		 window(10,5,70,20);
		 textbackground(BLUE);
		 clrscr(); // Apply the changes !!!!

		 gotoxy(19,nextline); //applies to last window
		 cputs("MasterMind");	//Used with windows
		 
		 
		 nextline = nextline + 2;
		 gotoxy(23,nextline);
		 cputs("(1) Play");
		 
		 
		 nextline = nextline + 2;
		 gotoxy(23,nextline);
		 cputs("(2) Enter your guess");
		 
		 
		 nextline = nextline + 2;
		 gotoxy(23,nextline);
		 cputs("(3) ");
		 
		 
		 nextline = nextline + 2;
		 gotoxy(23,nextline);
		 cputs("(4) ");
		 
		 
		 nextline = nextline + 2;
		 gotoxy(23,nextline);
		 cputs("(5) ");
		 
		 
		 nextline = nextline + 2;
		 gotoxy(23,nextline);
         cputs("(9) Exit ");
		 
		 
		nextline = nextline + 2;
		gotoxy(25,nextline);
		cputs("Selection: ");
		selection = getche();	//Write out the echo
		
		if (selection==ESC) return;
		
		switch(selection){
		
		case '1':play();break;
		
		case '2':player_guess();break;
		
		case '3':;break;
		
		case '4':;break;
		
		case '5':;break;
		
		case '9':return;/* break; */
		
		default:errorwin();break;
		}/* switch */
		menu(); // return to the main menu !!!
		}/* menu */

		void errorwin()
		{
		window(10,10,30,7);
		textbackground(RED);
		textcolor(YELLOW|BLINK);
		clrscr();
		gotoxy(1,4);
		cputs("Illegal Selection !!! ");
		gotoxy(10,10);
		getch();                 /* used as a pause */
		}/* errorwin */

void play(){

window(1,1,80,25);
		 textbackground(BLACK);
		 textcolor(WHITE);
		 clrscr();
		 srand( time( NULL ) );
number = rand();
			//number = 5;// hardcode to debug !!!!
cout << number;
 clrscr();

n[0] = number / 1000;
if (n[0] > 9) n[0] = n[0] % 10; // to correct the random number !!!
cout << "\n n[0] = " << n[0] << endl; //guess the random number !!!

n[1] = number % 1000 /100;

cout << "\n n[1] = " << n[1] << endl;

n[2] = number % 1000 % 100 / 10;
cout << "\n n[2] = " << n[2] << endl;
n[3] = number % 1000  % 100 % 10;
cout << "\n n[3] = " << n[3] << endl;

window(10,5,25,7);
		textbackground(RED);
		textcolor(YELLOW|BLINK);	//does not blink anymore
		clrscr();
		gotoxy(1,4);
		cputs("I Have generated the random number !!! ");
		gotoxy(10,10);
		
		getch();  
		
		 
}// load



void restore_the_screen()
{
         window(1,1,80,25);
		 textbackground(BLACK);
		 textcolor(WHITE);
		 clrscr();
		 

}


void player_guess(){

         window(1,1,80,25);
		 textbackground(BLACK);
		 textcolor(WHITE);
		 clrscr();

cout << "\n Enter your Guess ";
cin >> MyGuess;

guess[0] = MyGuess / 1000;
cout << "\n guess[0] = " << guess[0] << endl;

guess[1] = MyGuess % 1000 /100;

cout << "\n guess[1] = " << guess[1] << endl;

guess[2] = MyGuess % 1000 % 100 / 10;
cout << "\n guess[2] = " << guess[2] << endl;
guess[3] = MyGuess % 1000  % 100 % 10;
cout << "\n guess[3] = " << guess[3] << endl;
		 
	
getch();

winner();

}



void winner()
{
for (int i = 0; i < ROWS;++i)

for (int j = 0; j < ROWS;++j)
{

if ((n[i] == guess[j] ) && (i == j) ) 
{


++RIGHT;
//if (n[i] == 0)  n[i] = 11;
//else n[i] = -n[i];

//if (guess[i] == 0)  guess[i] = 10;
	//else guess[i] = -guess[i];
n[i] = -1;
guess[i] = -2;

}//if


if (RIGHT == 4)
{
  cout << "\n\a You Win !!!!";
exit(0);
  }//if

}//for

for (int i = 0; i < ROWS;++i)

for (int j = 0; j < ROWS;++j)
{
if ((n[i] == guess[j] ) && (i != j) )
{

 ++WRONG;
 
 //if (n[i] == 0)  n[i] = 11;
//else n[i] = -n[i];

//if (guess[i] == 0)  guess[i] = 10;
	//else guess[i] = -guess[i];
	n[i] = -1;
	guess[j] = -2;
}//if




}// for j
cout <<"\n\n  Number in correct positions = " << RIGHT << endl;

cout <<"\n\n  Number in incorrect positions = " << WRONG << endl;

//if (RIGHT == 4)  cout << "\n\a You Win !!!!";


//for (int i = 0; i < ROWS;++i){
    // if (n[i] == 11)  n[i] = 0;
	//else if (n[i] < 0)  n[i] = -n[i];
	
	//if (guess[i] == 10)  guess[i] = 0;
	//else if (guess[i] < 0)  guess[i] = -guess[i];
	
//}//for





RIGHT = 0;
WRONG = 0;
getch();
reload();
	 }// winner









void reload(){

n[0] = number / 1000;
if (n[0] > 9) n[0] = n[0] % 10; // to correct the random number !!!
cout << "\n n[0] = " << n[0] << endl; //guess the random number !!!

n[1] = number % 1000 /100;

cout << "\n n[1] = " << n[1] << endl;

n[2] = number % 1000 % 100 / 10;
cout << "\n n[2] = " << n[2] << endl;
n[3] = number % 1000  % 100 % 10;
cout << "\n n[3] = " << n[3] << endl;

}

