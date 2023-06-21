// Leo Zobel
// 4/10/2023

package projectPackage;

import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class DataDecompression {
	
	List<byte[]> decompressedDataList = new ArrayList<byte[]>();
	
	// Method to decompress weather data
	public void decompressData(List<byte[]> dataList, List<KeyPair> keyPairList) {
		
		// Use an inflater to decompress each set of data from the data list
		for (byte[] data : dataList) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Inflater inflater = new Inflater();
			// Inflate (decompress) the current byte[] of data in the list
			inflater.setInput(data);
			byte[] buffer = new byte[1024];
			while (!inflater.finished()) {
				try {
					int count = inflater.inflate(buffer);
					baos.write(buffer, 0, count);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			inflater.end();
			
			// Retrieve the decompressed data from the baos and add it to a new List
			byte[] decompressedData = baos.toByteArray();
			decompressedDataList.add(decompressedData);
		}
	}
	
	// Method to get the decompressed data list - 29 lines
	public List<byte[]> getDecompressedDataList() {return decompressedDataList;}
}
