// Leo Zobel
// 4/10/2023

package projectPackage;

import java.util.HashMap;
import java.util.Scanner;

public class Preferences {
	
	private String[] options = {"temperature", "humidity", "pressure"};
	private HashMap<String, Boolean> preferenceMap = new HashMap<String, Boolean>();;
	private String preferenceReport = "No preferences set";
	
	// Constructor to set default preferenceMap values
	Preferences() {
		for (String option : options) {
			preferenceMap.put(option, false);
		}
	}
	
	// Ask user for what weather data they wish to collect
	public void setPreferences() {
		Scanner input = new Scanner(System.in);
		
		for (String option : options) {
			// Get User input
			System.out.printf("would you like to collect %s data? (y /n) ", option);
			String yesNo = input.nextLine();
			
			// Use user input to modify the preferenceMap
			if (yesNo.equals("y")) {
				preferenceMap.put(option, true);
			}
		}
		input.close();
		
		// Update preference report
		setPreferenceReport("Preference Selection: Successfully");
	}
	
	// Method to get preferences HashMap
	public HashMap<String, Boolean> getPreferences() {
		return preferenceMap;
	}
	
	// Method to get the preference report
	public String getPreferenceReport() {
		return preferenceReport;
	}
	// Method to set the preference report - 33 lines
	public void setPreferenceReport(String preferenceReport) {
		this.preferenceReport = preferenceReport;
	}
}
