#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>   // Definition for fork() and execve()
#include <errno.h>    // Definition for "error handling"
#include <sys/wait.h> // Definition for wait()

char *input = NULL;
size_t capline = 0; // Capacity
int i;
char *token;
char *array[512];

/* Execute a command */
void execute(){
    int pid = fork(); // Create a new process
    if (pid != 0) { // If not successfully completed
        int s;
        waitpid(-1, &s, 0); // Wait for process termination
    } else {
        if(execvp(array[0], array) == -1){ //returned -1 => something went wrong!*/
            perror("Wrong command"); // Display error message
            exit(errno);
        }
    }
}

void displayPrompt(){
    printf("> ");
}

/* Divide input line into tokens */
void makeTokens(char *input){
    i = 0;
    token = strtok(input, "\n ");
    while (token != NULL) {
        array[i++] = token;
        token = strtok(NULL, "\n ");
    }
    array[i] = NULL;
}

int main(){
    while(1){
        displayPrompt();
        getline(&input, &capline, stdin); // Read the user input

        if(strcmp(input,"\n")==0){
            perror("Please type in a command " );
            continue;
        }

        makeTokens(input); // Divide line into tokens


        if (strcmp(array[0], "q") == 0) { // 'q' = exit the Shell
            printf("Exited with status code 0\n");
            return 0;
        }
        execute(); // Call execvp()
    }
}