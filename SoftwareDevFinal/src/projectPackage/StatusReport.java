// Leo Zobel
// 4/10/2023

package projectPackage;

import java.util.List;

public class StatusReport {
	
	private String statusReport = "";
	private String generationReport = "";
	private String storageReport = "";
	private String encryptionReport = "";
	private String compressionReport = "";
	private String transmissionReport = "";
	
	public void generateStatusReport() {
		statusReport = "\nSatellite System Report: \n"
				+ "------------------------------\n"
				+ getGenerationReport()
				+ "\n"
				+ getEncryptionReport()
				+ "\n"
				+ getCompressionReport()
				+ "\n"
				+ getTransmissionReport()
				+ "\n"
				+ getStorageReport();
	}

	// Method to get status report
	public String getStatusReport() {return statusReport;}

	// Methods to get and set weather generation report
	public String getGenerationReport() {return generationReport;}
	public void setGenerationReport(String generationReport) {
		this.generationReport = "Weather Generation Report:\n" + generationReport + "\n";
	}

	// Methods to get and set storage report
	public String getStorageReport() {return storageReport;}
	public void setStorageReport(List<String> storageReport) {
		//this.storageReport = storageReport;
		//this.storageReport = String.format("Storage Report:\n%s\n%s\n%s\n%s\n", )
		this.storageReport = "Storage Report:\n";
		for (String report : storageReport) {
			this.storageReport += report + "\n";
		}
	}

	// Methods to get and set encryption report
	public String getEncryptionReport() {return encryptionReport;}
	public void setEncryptionReport(List<String> encryptionReport) {
		//this.encryptionReport = encryptionReport;
		this.encryptionReport = "Encryption Report:\n";
		for (String report : encryptionReport) {
			this.encryptionReport += report + "\n";
		}
	}

	// Methods to get and set compression report
	public String getCompressionReport() {return compressionReport;}
	public void setCompressionReport(List<String> compressionReport) {
		//this.compressionReport = compressionReport;
		this.compressionReport = "Compression Report:\n";
		for (String report : compressionReport) {
			this.compressionReport += report + "\n";
		}
	}

	// Methods to get and set transmission report - 45 lines
	public String getTransmissionReport() {return transmissionReport;}
	public void setTransmissionReport(List<String> transmissionReport) {
		//this.transmissionReport = transmissionReport;
		this.transmissionReport = "Transmission Report:\n";
		for (String report : transmissionReport) {
			this.transmissionReport += report + "\n";
		}
	}
}
