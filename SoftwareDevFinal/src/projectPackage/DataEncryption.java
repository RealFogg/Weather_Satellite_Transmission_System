// Leo Zobel
// 4/10/2023

package projectPackage;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;

public class DataEncryption {
	
	private List<String> encryptionReport = new ArrayList<String>();
	private byte[] encryptedData = null;
	private KeyPair keyPair = null;
	
	// Method to set encryptedData
	public void setEncryptedData(byte[] encryptedData) {
		this.encryptedData = encryptedData;
	}
	// Method to get encryptedData
	public byte[] getEncryptedData() {
		return encryptedData;
	}
	
	// Method to set KeyPair
	public void setKeyPair(KeyPair pair) {
		this.keyPair = pair;
	}
	// Method to get KeyPair
	public KeyPair getKeyPair() {
		return keyPair;
	}
	
	// Method to get encryption report
	public List<String> getEncryptionReport() {
		return encryptionReport;
	}
	
	// Method to encrypt the weather data
	public void encryptData(HashMap<String, Float> weatherDataMap) {
		
		try {
			// Create and initialize KeyPair Generator using RSA algorithm
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(2048);
			
			// Generate the key pair
			KeyPair pair = keyPairGen.generateKeyPair();
			
			// Create and initialize the cipher using RSA,ECB, and PKCS1Padding transformations
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
			
			// Get weather data from the weather data HashMap
			String weatherData = "";
			if (weatherDataMap.get("temperature") != null) {
				weatherData += ("Temperature: " + String.valueOf(weatherDataMap.get("temperature")) + "\n");
			}
			if (weatherDataMap.get("humidity") != null) {
				weatherData += ("Humidity: " + String.valueOf(weatherDataMap.get("humidity")) + "\n");
			}
			if (weatherDataMap.get("pressure") != null) {
				weatherData += ("Pressure: " + String.valueOf(weatherDataMap.get("pressure")) + "\n");
			}
			
			// Add weather data to the cipher
			byte[] encryptWeatherData = weatherData.getBytes();
			cipher.update(encryptWeatherData);
			
			// Encrypt the data
			byte[] cipherText = cipher.doFinal();
			
			// Update encryptStatus
			encryptionReport.add("Data Encryption: Successful");
			
			setKeyPair(pair);
			setEncryptedData(cipherText);
			
		} catch (Exception e) {
			// Error encountered Update encryptStatus = 58 lines
			encryptionReport.add("Error occured while encrypting data - " + e.toString());
		}
	}
}
