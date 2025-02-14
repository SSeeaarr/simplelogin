package login;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;


public class User {
    
	String username = "username";
	String password = "password";
	String mothername = "mom";
	String petname = "darwin";
	
	
	
	public String getMothername() {
		return mothername;
	}


	public String getPetname() {
		return petname;
	}


	public String getPassword() {
		return password;
	}
		
	
	public void setPassword(String password) { //set password to what we want
		this.password = password;
		write(); //call write to write the new password
		System.out.println("password is now: " + password); //for debug, to see new password
	}
	
	
	

	public void initialize() {
		
		File f = new File("information.txt");
	try {
		if (f.createNewFile()) { //if the file exists, read from it otherwise create it
		System.out.println("file created");
		write();
		} 
		else {
		System.out.println("file already exists, reading...");
		read();
		
		}
	}
	catch (IOException e){
		System.out.println("an error occured.");
	}
	}
	
	
	 /**
     * Writes the current user information to the file "information.txt".
     */
	public void write() {
		try {
	      FileWriter Writer = new FileWriter("information.txt"); //write current information when the function is called
	      Writer.write("Username:" + username 
	      		+ "\nPassword:" + password
	      		+ "\nMotherName:" + mothername
	      		+ "\nPetName:" + petname);
	      Writer.close();
		}
		catch (IOException e){
			System.out.println("an error occured");
			}
		}
	
	
		public void read() {
			File file = new File("information.txt");
		Scanner reader;
		try {
			
			//reading username from file
			
			reader = new Scanner(file);
			String temp = truncate(reader.nextLine());
			username = temp;
			
			//reading password from file
			
			temp = truncate(reader.nextLine());
			password = temp;
			
			//reading mother name from file
			
			temp = truncate(reader.nextLine());
			mothername = temp;
			
			//reading pet's name from file
			
			temp = truncate(reader.nextLine());
			petname = temp;
			
			
				
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		}
	    /**
	     * Formats text from text file to be properly read in program.
	     * @param line The input line from the file.
	     * @return The extracted value as a String.
	     */
		public String truncate(String line) {
			String read = line.substring(line.indexOf(":") + 1).trim();
			return read;
		}
	
	}


