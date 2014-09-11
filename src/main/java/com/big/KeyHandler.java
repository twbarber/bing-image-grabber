package com.big;
/* 

 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class KeyHandler {

	private String encryptedKey;
	private File keyFile;
	private Scanner sc = new Scanner(System.in);

	public void getUserKey() throws IOException {

		String userHome = System.getProperty("user.home");
		boolean goodKey = false;

		this.keyFile = new File(userHome + "/big/keyList.txt");
		if(this.keyFile.exists()) {
			Scanner fileReader = new Scanner(this.keyFile);
			try {
				if(fileReader.hasNextLine())
					this.encryptedKey = fileReader.nextLine();
					goodKey = true;
			} finally {
				fileReader.close();
			}
		}
		
		while(!goodKey) {
			System.out.print("\nEnter Bing AppID: ");
			String userAccountKey = sc.next();
			// Found this encryption on GitHub and StackOverflow... Required by MSoft
			byte[] byteKey = Base64.encodeBase64((userAccountKey + ":" + userAccountKey).getBytes());
			String testKey = new String(byteKey);

			if(verifyKey(testKey)) {
				goodKey = true;
				sc.nextLine();
				System.out.println("\nKey Accepted");
				this.encryptedKey = testKey;
				writeKey();
			}
			else System.out.println("\nInvalid Key");
		}
	}

	public boolean verifyKey(String testKey) {

		try{
			URL url = new URL("https://api.datamarket.azure.com/Data.ashx/" +
					"Bing/Search/Web?Query=%27test%27&$top=50&$format=json");

			URLConnection urlConnection = url.openConnection();
			String s1 = "Basic " + testKey;
			urlConnection.setRequestProperty("Authorization", s1);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine);
			in.close();
		}catch (Exception e) {
			return false;
		}

		return true;
	}
	
	public boolean getKey(String testKey) throws IOException {

		String userHome = System.getProperty("user.home");

		this.keyFile = new File(userHome + "/big/keyList.txt");
		if(this.keyFile.exists()) {
			Scanner fileReader = new Scanner(this.keyFile);
			try {
				while(fileReader.hasNextLine())
					if(testKey.equals(fileReader.nextLine()))
						return true;
			} finally {
				fileReader.close();
			}
		}
		return false;
	}

	public void writeKey() throws IOException {

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
			output.append(this.encryptedKey);
			output.append("\n");
		} finally {
			output.close();
		}
	}

	public boolean isValidKey() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean keyExists() {
		// TODO Auto-generated method stub
		return false;
	}

	public void promptForKey() {
		// TODO Auto-generated method stub
		
	}
}