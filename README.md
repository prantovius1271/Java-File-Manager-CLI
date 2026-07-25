# Java File Manager Console

A custom command-line tool I built to practice local file handling and system architecture using Java NIO. 

https://youtu.be/Dr6wKePX2O0 
provided above the video link on how it works...

## What It Does
This is a fully functional console app that lets you Create, Read, Update (append), Move, and Delete files directly from the terminal. 

## How I Built It (Architecture)

Instead of dumping 500 lines of code into a single `main` method, I wanted to practice separating concerns. I structured this using a basic Controller pattern:
* **The Router (`FileManager.java`):** Acts as the main menu and traffic cop. It takes the user's input and routes them to the right operation.
* **The Modules:** Each command (`CreateN`, `WriteN`, `ExtractN`, `TransferN`, `DeleteN`) has its own dedicated class. If the delete logic breaks, it doesn't affect the create logic.

## Key Features

* **Modern Java NIO:** Built using `java.nio.file.Files` and `Path` instead of the older `java.io` wrappers.
* **Crash Prevention (Defensive Programming):** I built a recursive try-catch loop for the `Scanner`. If a user types a letter when asked for a number, the program catches the error, clears the buffer, and asks again instead of throwing a stack trace and crashing.
* **Validation Checks:** The program checks if a file actually exists before attempting to read or delete it.

## Tech Stack

* **Language:** Java 
* **Core Libraries:** `java.nio.file`, `java.util.Scanner`,`java.io`
