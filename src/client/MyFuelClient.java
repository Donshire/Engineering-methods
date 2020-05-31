// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.MyFuelIF;
import enums.Commands;

import java.io.*;
import java.util.ArrayList;

import com.sun.xml.internal.stream.Entity;

import Entity.Message;
import Entity.MyFile;

public class MyFuelClient extends AbstractClient {

	MyFuelIF clientUI;
	public static Object ServerRetObj;
	public static boolean awaitResponse = false;

	public MyFuelClient(String host, int port, MyFuelIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
	}

	public void handleMessageFromServer(Object msg) {
		System.out.println("recive---");
		if (msg != null) {
			Message message = (Message) msg;
			//there is a serios bug here 
			System.out.println(message.getCmd());
			switch (message.getCmd()) {
				case defaultRes:// receive from server ArrayList<employee>
					ServerRetObj = message.getObj();
					
	//			case reciveFile:
	//				System.out.println("MyFuelClient");
	//				MyFile myFile=(MyFile) msg;
	//				saveFile(myFile);
	//				ServerRetObj=myFile.getFileName();
	//				break;
	
				case CustomerOrderListRes:
					ServerRetObj = message.getObj();
					break;
					
				case MaxPriceRes: // receive from server ArrayList<employee>
					ServerRetObj = message.getObj();
					break;
			}
		}
		
		awaitResponse = false;
	}

	public void handleMessageFromClientUI(Object str) {
		try {
			awaitResponse = true;
			sendToServer(str);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
					System.out.println("still waiting");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			clientUI.display("Could not send message to server.  Terminating client.");
			e.printStackTrace();
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
	
	private static void saveFile(MyFile file) {
		try {
			int fileSize = file.getSize();
			System.out.println("Message received: " + file.getFileName() + "length " + fileSize);
			FileOutputStream fos = new FileOutputStream("C:\\Users\\iamme\\Desktop\\server\\" + file.getFileName());
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(file.mybytearray, 0, fileSize);
			bos.flush();
			fos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//End of ChatClient class
