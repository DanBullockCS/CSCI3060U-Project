#!/bin/bash
# Weekly script

for (( j=1; j <= 5; j++ ))
do
	# Passing the argument of 1 to 5 to the daily-script
	day_index=$j
	./daily-script.sh $day_index
	# Print the summary (users.ua/stock.at) after each day
	cat output.txt
done

# Delete the file that holds the users.ua and stock.at output
rm output.txt