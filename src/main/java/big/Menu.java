package big;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.util.Scanner;

public class Menu {

	private Scanner systemInput = new Scanner(System.in);

	public void firstRun() {

		String userHome = System.getProperty("user.home");
		String strDirectory = userHome + "/grabber";
		String imageDirectory = strDirectory + "/images";

		File bigHomeDirectoryFile = new File(strDirectory);
		File imageDirectoryFile = new File(imageDirectory);

		if (!bigHomeDirectoryFile.exists()) {
			if(bigHomeDirectoryFile.mkdir()) {
				if(imageDirectoryFile.mkdir())	
					System.out.println("BIG Directory Initialized\n");
			}	
		}
	}

	public String queryMenu() {

		boolean validChoice = false;

		System.out.println("Choose Search Term: \n" +
				"\n1) Random \n2) Custom");  

		while(!validChoice) {
			System.out.print("\nChoice: ");

			String choice = this.systemInput.nextLine();

			if(choice.equals("1") || choice.equalsIgnoreCase("random"))
				return "random";
			else if(choice.equals("2") || choice.equalsIgnoreCase("custom")) {
				System.out.print("\nEnter Search Term: ");
				return this.systemInput.nextLine().replaceAll(" ", "_");
			}	
			else 
				System.out.println("\nInvalid Option");
		}
		return "random";	// Returns random as default if somehow it gets here
	}

	public String filterMenu() {

		boolean validChoice = false;

		System.out.println("\nChoose Adult Content Settings: \n" +
				"\n1) Strict \n2) Moderate \n3) Off");

		while(!validChoice) {
			System.out.print("\nChoice: ");

			String choice = this.systemInput.nextLine();

			if(choice.equals("1") || choice.equalsIgnoreCase("strict"))
				return "Strict";
			else if(choice.equals("2") || choice.equalsIgnoreCase("moderate")) {
				return "Moderate";
			}	
			else if(choice.equals("3") || choice.equalsIgnoreCase("off")) {
				return "Off";
			}	
			else 
				System.out.println("\nInvalid Option");
		}
		return "Moderate";	// Returns Moderate as default if somehow it gets here

	}

	public int countMenu() {
		System.out.print("\nDesired Number of Images (Max 1000): ");
		boolean goodNumber = false;
		int numberImages = this.systemInput.nextInt();
		while(!goodNumber) {
			if(numberImages < 0 || numberImages > 1000) {
				System.out.print("\nPlease enter a valid number of images: ");
				numberImages = this.systemInput.nextInt();
			}
			else goodNumber = true;
		}
		return numberImages;
	}

	public String keyMenu() {
		System.out.print("\nEnter Bing AppID: ");
		String userAccountKey = systemInput.next();
		// Found this encryption on GitHub and StackOverflow... Required by MSoft
		byte[] byteKey = Base64.encodeBase64((userAccountKey + ":" + userAccountKey).getBytes());
		return new String(byteKey);
	}
}