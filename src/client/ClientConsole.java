// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package client;
import java.io.*;
import common.MyFuelIF;


public class ClientConsole implements MyFuelIF 
{
   public static int DEFAULT_PORT ;
   MyFuelClient client;

  
  public ClientConsole(String host, int port) 
  {
    try 
    {
      client= new MyFuelClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"+ " Terminating client.");
      System.exit(1);
    }
  }

  
  public void accept(Object str) 
  {
	  client.handleMessageFromClientUI(str);
  }
  
 
  public void display(String message) 
  {
    System.out.println("> " + message);
  }
}
