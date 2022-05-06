/*
 * Author: Kyra Samuel
 * Date: 5/6/2022
 * Class: SDEV425
 * File: main.c 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Function prototypes
void fillPassword(size_t , char[]);
void showResults(char);
// should have void listed
void showMenu(void);

// Define a variable to hold a password
// and the copy - no longer copying password
char password[15];
//char cpassword[15];

int main(void)
{ 
  char input[5];  // user's input
	char cont = 'y'; 
	int cVar = 0; // process variable
  int ch;

	// Welcome the User
	printf("Welcome to the C Array Program!\n");

	// Display menu and Get Selection
	while (toupper(cont) != 'E') {
		// Diaply the Menu
		showMenu();
		
		// Get the user selection
    if (!fgets(input, sizeof(input), stdin)) {
      printf("Unable to read line\n");
      exit(1);
    } else {
      if (input[strlen(input) -1] == '\n') // if the last char is a newline
        input[strcspn(input, "\r\n")] = 0;
      else { // get the rest of the input
        while ((ch = getchar()) != '\n' && ch != EOF);

        if (ch == EOF) 
          printf("EOF Reached");
      }
      
      if (strlen(input) == 1)
        cont = input[0];
    }

    if (strlen(input) == 1)
      showResults(cont);
    else
      printf("Please enter a valid selection.\n");
	}

	// Call the Copy routine	
	fillPassword(sizeof(password),password);	
	
	// Display variable values
	printf("password is %s\n", password);
	printf("cVar is %d\n", cVar);

	// Copy password - do not copy password
  //memcpy_s(cpassword, password, sizeof (password));

	// Pause before exiting
	char confirm;
	printf("Confirm your exit!");
  
  confirm = getchar();

	return 0;
}

// Make a String of '1's
void fillPassword(size_t n, char dest[]) {
	// Should be n-1
	for (size_t j = 0; j < n - 1; ++j) {	
		dest[j] = '1';
	}
	// Add null terminator for string
	dest[n] = '\0';
}

/* Display the Results*/
void showResults(char value) {
	switch (toupper(value)) { // removes the need
	case 'F':
		printf("Welcome to the Football season!\n");		
    break; // missing break statement
	case 'S':		
		printf("Welcome to the Soccer season!\n");
		break;
	case 'B':		
		printf("Welcome to the Baseball season!\n");
		break;			
	case 'E':		
		printf("Exiting the Menu system!\n");
		break;
	default:
		printf("Please enter a valid selection\n");
	}
	
}

/* Display the Menu*/
void showMenu(void) {
	printf("Enter a selection from the following menu.\n");
	printf("B. Baseball season.\n");
	printf("F. Football season.\n");
	printf("S. Soccer season.\n");
	printf("E. Exit the system.\n");
}