// Leo Zobel
// 4/10/2023

package projectPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;

public class WeatherStation {

	public static void main(String[] args) throws Exception {
		
		// Create a preferences object and set the preferences based on user input
		Preferences prefs = new Preferences();
		prefs.setPreferences();
		HashMap<String, Boolean> prefMap = prefs.getPreferences();
		
		
		// Initializing a socket used to connect to the satellite
		Socket socket = new Socket("localhost", 5050);
		
		// Send preference data to the satellite
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(prefMap); // Writing a HashMap of preference data to the satellite
		
		// Receive weather data from the satellite
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		String statusReport = in.readUTF();                                    // Reads the status report from the 
		List<byte[]> compressedDataList = (List<byte[]>) in.readObject();      // Reads the compressed weather data
		List<KeyPair> keyPairList = (List<KeyPair>) in.readObject();           // Reads the KeyPairs used for decryption
		
		// Close the connection to the satellite
		socket.close();
		
		// Output the status report
		System.out.println(statusReport);
		
		// Decompress and Decrypt the weather data
		DataDecompression dataDecompression = new DataDecompression();
		dataDecompression.decompressData(compressedDataList, keyPairList);
		
		// Decrypt the weather data
		DataDecryption dataDecryption = new DataDecryption();
		dataDecryption.decryptData(dataDecompression.getDecompressedDataList(), keyPairList);
		
		// Output the weather data - 37 lines
		System.out.println("Collected Data: ");
		for(byte[] data : dataDecryption.getDecrptedDataList()) {
			System.out.println(new String(data, "UTF8"));
		}
	}
}
