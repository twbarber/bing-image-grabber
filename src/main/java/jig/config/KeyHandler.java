package jig.config;
/* 

 */

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class KeyHandler {

	private File keyFile;
	private Scanner sc = new Scanner(System.in);

	public KeyHandler() {

	}

	public String getExistingKey() throws IOException {

		String userHome = System.getProperty("user.home");

		try {
			this.keyFile = new File(userHome + "/grabber/keyList.txt");
			if(this.keyFile.exists()) {
				Scanner fileReader = new Scanner(this.keyFile);
				return fileReader.nextLine();
			}
		} catch(NullPointerException e) {
			System.err.println("Key file does not exist.");
		} catch(NoSuchElementException e) {
			System.err.println("Key file is empty.");
		}
		return null;
	}

	public void writeKey(String encryptedKey) throws IOException {

		if(!this.keyFile.exists()) {
			boolean success = false;
			try {
				success = this.keyFile.createNewFile();
			} catch (IOException e) {
			}
			if(success)	
				System.out.println("Key File Created");
		}

		Writer output = new BufferedWriter(new FileWriter(keyFile));
		try {
			output.append(encryptedKey);
			output.append("\n");
		} finally {
			output.close();
		}
	}

	public boolean isValidKey(String aBingKey) {
		try {
			URL bingTestUrl = new URL("https://api.datamarket.azure.com/Bing/Search/Image?"
					+ "$format=json&Query=%27test%27");

			URLConnection urlConnection = bingTestUrl.openConnection();
			String formattedKey = "Basic " + aBingKey;
			urlConnection.setRequestProperty("Authorization", formattedKey);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			if (in.readLine() != null)
				in.close();
		} catch (Exception e) {
			System.err.println("There was an error validating your key 111.");
			return false;
		}
		return true;
	}
}