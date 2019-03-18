package noLabToday;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.*;

/**
 * Class handles reading and writing to the various ticket/user/daily trans
 * files
 *
 * @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock (Team
 *         No Lab Today)
 * @version 0.1
 * @since 2019-03-05
 */

public class FileHandler {

	public String fileName;

	public List<String> userList = new ArrayList<String>();
	public List<String> ticketList = new ArrayList<String>();
	public List<String> transList = new ArrayList<String>();


	public boolean WriteTicketsFile() throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		try (Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./files/stock.at"), "utf-8")))
		{
			for (String line : this.ticketList) {
				if (line != null)
				{
					fileWriter.write(line + "\n");
				}

			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;

	}
	public boolean WriteUsersFile() throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		try (Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./files/users.ua"), "utf-8")))
		{
			for (String line : this.userList) {
				if (line != null)
				{
					fileWriter.write(line + "\n");
				}

			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;

	}
	public void initializeFiles(String userFile, String ticketsFile, String transFile) throws IOException
	{
		userList = readFile(userFile);
		ticketList = readFile(ticketsFile);
		transList = readFile(transFile);
	}

	/**
	 * reads the file and stores the contents in a list of strings
	 *
	 * @param fileName the name of the file in string format
	 * @return returns a list of strings (the file as a list of strings)
	 * @throws IOException when the file cannot be found or any issues with the i/o
	 *                     occur
	 */
	public static List<String> readFile(String fileName) throws IOException {
		InputStream is = new FileInputStream(fileName);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));

		List<String> fileAsString = new ArrayList<String>();
		String line = "";
		while (line != null) {
			line = buf.readLine();
			if (line != null) {
				fileAsString.add(line);
			}

		}
		return fileAsString;
	}

	/**
	 * Verifies the format of the user file
	 *
	 * @param fileName the name of the file in string format
	 * @return returns a list of strings (the file as a list of strings)
	 * @throws java.io.IOException when the file cannot be found or any issues with
	 *         the i/o occur
	 */
	public boolean verifyUserFileFormat() throws java.io.IOException {

		String username = "";
		String type = "";
		String credit = "";
		boolean check = true;
		for (String line : this.userList) {
			if (line != null) {
				username = line.substring(1, 15);
				type = line.substring(16, 18);
				credit = line.substring(19, 28);

				if (line.length() != 28) {
					check = false;
				} else if (!type.equals("AA") && !type.equals("FS") && !type.equals("BS") && !type.equals("SS")) {
					check = false;
				} else if (Double.parseDouble(credit) > 999999.99 || Double.parseDouble(credit) < 0) {
					check = false;
				}
			}

		}

		return check;
	}

	/**
	 * Verifies the format of the stock (tickets) file
	 *
	 * @param fileName the name of the file in string format
	 * @return returns a list of strings (the file as a list of strings)
	 * @throws java.io.IOException when the file cannot be found or any issues with
	 *         the i/o occur
	 */
	public boolean verifyTicketsFileFormat() throws java.io.IOException {
		String eventName = "";
		String username = "";
		String amountOfTickets = "";
		String ticketPrice = "";
		boolean check = true;
		for (String line : this.ticketList) {
			if (line != null) {
				eventName = line.substring(0, 26);
				username = line.substring(26, 42);
				amountOfTickets = line.substring(42, 46);
				ticketPrice = line.substring(46, 52);

				if (line.length() != 52) {
					check = false;
				} else if (Double.parseDouble(ticketPrice) > 999.99 || Double.parseDouble(ticketPrice) < 0) {
					check = false;
				} else if (Integer.parseInt(amountOfTickets.trim()) > 100
						|| Integer.parseInt(amountOfTickets.trim()) < 0) {
					check = false;
				}
			}
		}
		return check;
	}

	/**
	 * Verifies the format of the daily transaction file
	 *
	 * @param fileName
	 * @return
	 * @throws java.io.IOException
	 */
	public boolean verifyTransFileFormat() throws java.io.IOException {
		boolean check = true;
		String transCode = "";
		for (String line : this.transList) {
			if (line != null) {
				transCode = line.substring(0, 2);
				if (transCode.trim().equals("00")) {
					if (line.length() != 2) {
						check = false;
					}
				} else if (transCode.trim().equals("01")) {
					if (line.length() != 31) {
						check = false;
					}
				} else if (transCode.trim().equals("02")) {
					if (line.length() != 31) {
						check = false;
					}
				} else if (transCode.trim().equals("03")) {
					if (line.length() != 55) {
						check = false;
					}
				} else if (transCode.trim().equals("04")) {
					if (line.length() != 55) {
						check = false;
					}
				} else if (transCode.trim().equals("05")) {
					if (line.length() != 44) {
						check = false;
					}
				} else if (transCode.trim().equals("06")) {
					if (line.length() != 31) {
						check = false;
					}
				} else {
					check = false;
				}
			}
		}
		return check;
	}

	/**
	 * reads all files (users/tickets/trans) and stores the contents in a list of
	 * strings
	 *
	 * @param fileNames[] the name of the files in string format
	 * @return returns a list of strings (the file as a list of strings)
	 * @throws IOException when the file cannot be found or any issues with the i/o
	 *                     occur
	 */
	public List<String> readAllFiles(String fileNames[]) {
		List<String> fileContents = new ArrayList<String>();
		return fileContents;
	}
}
