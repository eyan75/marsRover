# Mars Rovers

Hello and thanks for looking at the code.

This project was built using gradle. If you do not have gradle, you can find insturctions to install it here:

https://gradle.org/install/

To run the program, run

`gradle run`

To run the test suite, run

`gradle test`

This project can be imported into most IDEs by importing the `build.gradle` file.

This application takes input from the file in src/main/resources/input.txt. For testing purposes, I have also allowed it to 
take a file as the first argument.

## Design decisions

The code is broken down into 3 main parts: the models, the service, and the main application

The models package contains all of the data objects required: the rover, plateau, directions, and instructions.
This package does not contain any business logic. This is so that the models on have 1 responsibility: to know about themselves.
For example, the rover knows only where it is and what direction it is facing, it is not responsible for moving or changing directions.
The goal of this is to keep the models simple and have the decoupled from business logic.

There is only 1 service in this project, the rover service. This is where all of the business logic of instructing the rovers happens.
The rover service is responsible for moving the rovers around. It also knows how big the plateau so it will prevent rovers from
being instructed off the plateau. Rovers are very expensive and we don't want them to fall off cliffs.
The rover service will throw exceptions in the case that something unusual happens such as the rover started somewhere not on
the plateau or the instructions contained an invalid instruction. It is up to the requester to decide how to handle these cases.
In my case, I have decided to print a informative message and move on.

The RoverApplication is the main class and handles the input and output. If at any point it encounters an error it will print an error and exit.

## Tests

There are 2 types of test included: unit tests for the rover service/command parser and an end to end test for the whole application.
The RoverServiceTest includes tests that issue various instructions to a rover. It also test for the unusual conditions mentioned above.
The RoverApplicationEndToEndTest covers the entire running of the application. It feeds a file to the application and checks that the expected output
is correct.