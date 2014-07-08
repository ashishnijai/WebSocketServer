package com.gslab.starfish.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

 
public class WebSocketServer {
 
    public static void main(String[] args) {
        runServer();
    }
 
    public static void runServer() {
       Server server = new Server("localhost", 9090, "/websockets",null, CTIServerEndPoint.class);
       // Server server = new Server ();
 
        try {

        		server.start();
       		while(0==0){
//        			Thread.sleep(1000);
//        			if(CTIServerEndPoint.clientSession!=null){
//        			
//        				Message clientMessage = new Message();
//	        	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		        	    try{
//		        	    	System.out.println("Enter a message to send to client: " );
//		        	        String message  = reader.readLine();
//		        	        String[] messages = message.split("::");
//		        	        clientMessage.setMessage(messages[1]);
//		        	        clientMessage.setSender(messages[0]);
//		        	        clientMessage.setReceived(new Date());
//		        	    }catch(Exception ex){
//		        	    	System.out.println(ex.getMessage());	        	    	
//		        	    }	        	    
//	        	    	System.out.println("Sending a message to client: " + clientMessage);
//	        	    	CTIServerEndPoint.clientSession.getAsyncRemote().sendObject(clientMessage);
//	        	    	System.out.println("message sent");
//	        	    }
        		}

//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("Please press a key to stop the server.");
//            reader.readLine();
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
          //  server.stop();
        }
    }
}