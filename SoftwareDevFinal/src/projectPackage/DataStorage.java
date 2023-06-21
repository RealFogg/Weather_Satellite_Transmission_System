// Leo Zobel
// 4/10/2023

package projectPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
	
	private String dataFile = "storedData";
	private String keyPairFile = "keyPairData";
	private List<byte[]> dataList = new ArrayList<byte[]>();
	private List<KeyPair> keyPairList = new ArrayList<KeyPair>();
	private List<String> storageReport = new ArrayList<String>();
	
	// Method to store weather data in a file
	public void storeData(byte[] compressedData) {
		
		// If dataFile (weather data's file) is not empty then retrieve the data in the file
		File storeDataFile = new File(dataFile);
		if (storeDataFile.length() != 0) {
			// Override the current dataList with the data list from dataFile
			dataList = retrieveData();
		}
		
		// Add the compressed data to dataList
		dataList.add(compressedData);
		
		try {
			// Write the list of data to the storedData file
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(dataFile));
			out.writeObject(dataList);
			
			// Update storage report and close the output stream
			storageReport.add("Task - Store Data: Successful");
			out.close();
			
		} catch (Exception e) {
			// Error encountered return an error message
			storageReport.add("Error occured while storing data - " + e.toString());
		}
	}
	
	// Method to retrieve weather data from the storedData File
	public List<byte[]> retrieveData() {
		
		try {
			// Read the list of data from dataFile
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile));
			List<byte[]> dataBytes = (List<byte[]>) in.readObject();
			in.close();
			
			// Empty the dataFile file
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(dataFile));
			List<byte[]> emptyDataList = new ArrayList<byte[]>();
			out.writeObject(emptyDataList);
			out.close();
			
			// Update the report and return the contents of dataFile prior to it being emptied
			storageReport.add("Task - Retrieve Data: Successful");
			return dataBytes;
			
		} catch (Exception e) {
			// Error occurred update storage report
			storageReport.add("Error occurred while retieving data - " + e.toString());
			return null;
		}
	}
	
	// Method to store keyPairs in a file
	public void storeKeyPairs(KeyPair pair) {
		
		// If the keyPairFile is not empty retrieve the data from it before adding to the keyPairList
		File kpFile = new File(keyPairFile);
		if (kpFile.length() != 0) {
			// Retrieve the stored keyPairs (overrides existing keyPairList)
			keyPairList = retrieveKeyPairs();
		}
		
		// Add the given pair to the keyPairList
		keyPairList.add(pair);
		
		try {
			// Write the list of keyPairs to the keyPairData file
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(keyPairFile));
			out.writeObject(keyPairList);
			
			// Update storage report and close output stream
			storageReport.add("Task - Store KeyPair: Successful");
			out.close();
			
		} catch (Exception e) {
			// Error encountered return an error message
			storageReport.add("Error occured while storing KeyPair - " + e.toString()); // fix later**********************
		}
	}
	
	// Method to retrieve keyPairs from a file
	public List<KeyPair> retrieveKeyPairs() {
		
		try {
			// Read in the list of keyPairs from the keyPairFile
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(keyPairFile));
			List<KeyPair> keyPairs = (List<KeyPair>) in.readObject();
			in.close();
			
			// Empty the key Pair List file
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(keyPairFile));
			List<KeyPair> emptyKeyPairList = new ArrayList<KeyPair>();
			out.writeObject(emptyKeyPairList);
			out.close();
			
			// Update the report and return the contents of keyPairFile prior to it being emptied
			storageReport.add("Task - Retrieve KeyPairs: Successful");
			return keyPairs;
			
		} catch (Exception e) {
			// Error encountered return an error message
			storageReport.add("Error occured while retieving keyPairs - " + e.toString());
			return null;
		}
	}
	
	// Method to get the storage report - 94 lines
	public List<String> getStorageReport() {
		return storageReport;
	}
}
