#!/bin/bash
# Daily script
# (comment line 10 and 31 to run script faster and skip compiling)

inputs="../inputs/Day"$1"/" # Folders with .inp files
merged="../files/trans.out" # The merged trans.out file

cd Frontend/
# Compile the frontend 
#g++ *.cpp -o ticket-seller

# Used to name the temp daily trans.out
index=0
for i in $inputs*.inp
do
	# Run the frontend
	./ticket-seller ../files/users.ua ../files/stock.at ../files/$index.out < $i
	# Append to the merged trans.out
	cat ../files/$index.out >> $merged
	# Add a newline to the merged trans.out
	echo >> $merged
	
	# Delete the temp daily trans.out files
	rm ../files/$index.out
	((index++))
done
cd ..

# Compile the backend
cd noLabToday
#make
cd ..
# Run Backend
java noLabToday.Main

# Save the users.ua and stock.at at the end of the day to print at the end of the week
touch output.txt
# Save the output of the users.ua and stock.at files at the end of each day
echo "" >> output.txt # echo for a newline
echo "The current users file for day "$1": " >> output.txt
cat files/users.ua >> output.txt
echo "" >> output.txt # echo for a newline
echo "The available tickets file for day "$1": " >> output.txt 
cat files/stock.at >> output.txt
