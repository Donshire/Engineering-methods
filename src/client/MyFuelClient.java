
  
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import server.FileManagmentSys;
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

			switch (message.getCmd()) {
				case defaultRes:
					ServerRetObj = message.getObj();
					break;
				case NoElementFound:
					ServerRetObj = message.getObj();
					break;
				case reciveFile:
					MyFile myFile = (MyFile) message.getObj();
					ServerRetObj = saveFile(myFile);
					break;
			}
		}
		else System.out.println("NULL RECIEVED FROM THE SERVER !!");
		awaitResponse = false;
	}

	public void handleMessageFromClientUI(Object str) {
		try {
			awaitResponse = true;
			openConnection();
			sendToServer(str);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(250);
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
	
	private static File saveFile(MyFile file) {
		try {
			int fileSize = file.getSize();
			System.out.println("Message received: " + file.getFileName() + "length " + fileSize);
			FileManagmentSys.createSingleFolder("C:\\MyFuel_Client");
			FileOutputStream fos = new FileOutputStream("C:\\MyFuel_Client\\temp.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(file.mybytearray, 0, fileSize);
			bos.flush();
			fos.flush();
			return new File("C:\\MyFuel_Client\\temp.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

