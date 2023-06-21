// Leo Zobel
// 4/10/2023

package projectPackage;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

public class WeatherData {
	
	private float temperature = 0;
	private float humidity = 0;
	private float pressure = 0;
	
	HashMap<String, Float> dataMap = new HashMap<String, Float>();
	private String generationReport = "No weather data generated";
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	// Generate random weather data for testing the system
	public void generateData(HashMap<String, Boolean> prefMap) {
		
		// Generated data is loosely based on the a random Florida day in April
		// Generate the temperature data
		if (prefMap.get("temperature")) {
			temperature = Float.parseFloat(df.format(randomFloat(60, 86)));
			dataMap.put("temperature", temperature);
		}
		// Generate the humidity data
		if (prefMap.get("humidity")) {
			humidity = Float.parseFloat(df.format(randomFloat(60, 92)));
			dataMap.put("humidity", humidity);
		}
		// Generate the pressure data
		if (prefMap.get("pressure")) {
			pressure = Float.parseFloat(df.format(randomFloat(30, 31)));
			dataMap.put("pressure", pressure);
		}
		
		// Generate the report
		setGenerationReport("Weather Data Generation: Successful");
	}
	
	// Generate a random float
	private float randomFloat(float min, float max) {
		Random r = new Random();
		return min + (max - min) * r.nextFloat();
	}
	
	// Method to get the weather data
	public HashMap<String, Float> getWeatherData() {
		return dataMap;
	}

	// Method to get the weather report
	public String getGenerationReport() {return generationReport;}
	// Method to set the weather report - 39 lines
	public void setGenerationReport(String generationReport) {this.generationReport = generationReport;}
}
