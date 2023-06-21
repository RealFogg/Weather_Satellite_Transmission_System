// Leo Zobel
// 4/10/2023

package projectPackage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

public class DataCompression {
	
	private List<String> compressionReport = new ArrayList<String>();
	private byte[] compressedData = null;
	
	// Method to get compressedData
	public byte[] getCompressedData() {
		return compressedData;
	}
	// Method to set compressedData
	public void setCompressedData(byte[] compressedData) {
		this.compressedData = compressedData;
	}
	
	// Method to get the compression report
	public List<String> getCompressionReport() {
		return compressionReport;
	}
	
	// Method to compress the weather data
	public void compressData(byte[] encryptedData) {
		// Initialize ByteArrayOutputStream and Deflater and set the input for the deflater
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Deflater deflater = new Deflater();
		deflater.setInput(encryptedData);
		deflater.finish();
		
		// Use the baos and buffer to deflate (compress) the weather data
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			baos.write(buffer, 0, count);
		}
		deflater.end();
		// Retrieve the compressed data from the baos
		byte[] compressedData = baos.toByteArray();
		
		// Update the compression report and set the compressed data - 35 lines
		compressionReport.add("Data Compression: Successful");
		setCompressedData(compressedData);
	}
}
