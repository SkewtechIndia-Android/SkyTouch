package com.skew.skytouch;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WritePropertiesFile {
	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			properties.setProperty("fname", "vinod");
			properties.setProperty("lname", "kadam");
			properties.setProperty("mobileno", "988888");
			properties.setProperty("emailid", "a@b.com");
			properties.setProperty("DOB", "26/01/1986");
			properties.setProperty("address", "Auragabad");
			properties.setProperty("deviceid", "deviceid");
					
			File file = new File("customerdetails.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Registration Details");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}