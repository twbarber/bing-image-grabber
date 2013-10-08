/* 
	Bing Image Grabber - Version 0.1.1


 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class keyHandler {
	
	String encryptedKey;
	Scanner sc = new Scanner(System.in);
	
	public void getUserKey() {

		boolean goodKey = false;

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
			}
			else System.out.println("\nInvalid Key");
		}
	}

	public boolean verifyKey(String testKey) {

		try{
			URL url = new URL("https://api.datamarket.azure.com/Data.ashx/Bing/Search/Web?Query=%27test%27&$top=50&$format=json");

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
}
