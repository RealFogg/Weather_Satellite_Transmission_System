// Leo Zobel
// 4/10/2023

package projectPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SatelliteSystem {

	public static void main(String[] args) throws Exception {
		
		// Open a ServerSocket to communicate with the weather station
		ServerSocket serverSocket = new ServerSocket(5050);
		System.out.println("Satellite is waiting for connections");
		
		// Accept the weather stations request to connect
		Socket socket = serverSocket.accept();
		System.out.println("Connection established");
			
		// Receive preference data from the weather station
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		HashMap<String, Boolean> prefMap = (HashMap<String, Boolean>) in.readObject();
		
		
		// Create dataStorage and statusReport objects
		StatusReport statReport = new StatusReport();
		DataStorage dataStorage = new DataStorage();
		
		// Generate random weather data
		WeatherData weatherData = new WeatherData();
		weatherData.generateData(prefMap);
		statReport.setGenerationReport(weatherData.getGenerationReport());
		HashMap<String, Float> weatherDataMap = weatherData.getWeatherData();
		System.out.println("Collected Data: \n" + weatherDataMap);
		
		// Encrypt the weather data
		DataEncryption dataEncryption = new DataEncryption();
		dataEncryption.encryptData(weatherDataMap);
		statReport.setEncryptionReport(dataEncryption.getEncryptionReport());
		
		// Store the encryption keys
		dataStorage.storeKeyPairs(dataEncryption.getKeyPair());
		
		// Compress the weather Data
		DataCompression dataCompression = new DataCompression();
		dataCompression.compressData(dataEncryption.getEncryptedData());
		statReport.setCompressionReport(dataCompression.getCompressionReport());
		
		//  Store the compressed data
		dataStorage.storeData(dataCompression.getCompressedData());
		
		// Generate final status report
		List<String> transmissionReport = new ArrayList<String>();
		transmissionReport.add("Transmission Started");
		statReport.setStorageReport(dataStorage.getStorageReport());
		statReport.generateStatusReport();
		
		// Send final weather data to the weather station
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeUTF(statReport.getStatusReport());
		out.writeObject(dataStorage.retrieveData());
		out.writeObject(dataStorage.retrieveKeyPairs());
		
		// Close the satellite connections and sockets - 53 lines
		socket.close();
		serverSocket.close();
	}

}
