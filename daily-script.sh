#!/bin/bash
# Daily script
# (comment line 10 and 31 to run script faster and skip compiling)

inputs="../inputs/Day1/"
merged="../files/trans.out"

cd Frontend/
# Compile the frontend 
#g++ *.cpp -o ticket-seller

# Used to name the temp daily trans.out
index=0
for i in $inputs*.inp
do
	# run the frontend
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