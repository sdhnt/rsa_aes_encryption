import java.io.*;
import java.net.*;
import java.util.*;
import java.io.Serializable;

import javax.crypto.SecretKey;
import javax.crypto.SealedObject;

public class ReverseStringClient {

	public static void main(String[] args) throws Exception {
		// Bind the socket to the server with the appropriate port
		
		
		
		AESEncryption alice = new AESEncryption();
		SecretKey aliceKey = alice.getSecretEncryptionKey("alice&bob");
		Socket socket = new Socket("localhost", 3333);
		
		// Setup I/O streams
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

		System.out.print("Enter the string you want to reverse: ");
		Scanner scan = new Scanner(System.in);
		String s;
		s = scan.nextLine();
		SealedObject cipherObject = alice.encrypt(s, aliceKey);
		out.writeObject(cipherObject);
		out.flush();
		SealedObject a= (SealedObject) in.readObject();
		String decryptedText = (String) alice.decrypt(a, aliceKey);
		String result = decryptedText;
		
		System.out.println("The result is: " + result);
	}

}
