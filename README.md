# CSCI3060U_Backend (CSCI3060U Project Phase 4-6, Team noLabToday)

# Phase 4 - How to run:

- To compile the program use the make command to run the makefile like so:
```
make
```
- Then change directory back to the main directory (CSCI3060U_Backend) with the command:
```
cd ..
```
- The reason for this is because we are using a package to connect all the classes called "noLabToday"
- Then run the compiled program using:
```
java noLabToday.Main
```

# Phase 5 - How to run:

- To compile the program use the make command to run the makefile in the "noLabToday" folder like so:
```
make
```
- Then change directory back to the main directory (CSCI3060U_Backend) with the command:
```
cd ..
```
- The reason for this is because we are using a package to connect all the classes called "noLabToday"
- Then run the compiled program with the TestRunner using:
```
java noLabToday.TestRunner
```

Depending on how junit is installed you may need to add a -cp when compiling.


# Phase 6 - How to run:

- There is code in the script to compile, comment the lines that have g++ and make in them to skip the compilation
- Run the bash script for the daily script by running:
```
./daily-script 1
# Pass 1 as an argument to indicate testing in the ../inputs/Day1/ Folder
```
- And the weekly script by running:
```
./weekly-script
```

- Everything handed in with Phase 6 is in the PRINTOUTS folder, this excludes the scripts which are in the main directory.