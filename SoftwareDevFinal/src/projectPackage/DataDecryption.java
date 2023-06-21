// Leo Zobel
// 4/10/2023

package projectPackage;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

public class DataDecryption {
	
	List<byte[]> decryptedDataList = new ArrayList<byte[]>();

	public void decryptData(List<byte[]> decompressedDataList, List<KeyPair> keyPairList) throws Exception {
		
		// Initialize the cipher using RSA,ECB, and PKCS1Padding transformations
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		
		// Decrypt each set of data from the decompressed data list
		for (int i = 0; i < decompressedDataList.size(); i += 1) {
			// Retrieve the keyPair from storage and initialize it in the cipher
			KeyPair pair = keyPairList.get(i);
			cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
			
			// Get the decompressed data from the list and use it to update the cipher
			byte[] decompressedData = decompressedDataList.get(i);
			cipher.update(decompressedData);
			
			// Get the deciphered data from the cipher and add it to the decrypted data list
			byte[] decipheredText = cipher.doFinal();
			decryptedDataList.add(decipheredText);
		}
	}
	
	// Method to get the decrypted data list - 23 lines
	public List<byte[]> getDecrptedDataList() {return decryptedDataList;}
}
