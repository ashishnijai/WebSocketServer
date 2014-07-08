package com.gslab.starfish.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.Json;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import com.gslab.starfish.json.messages.ControlR;
import com.gslab.starfish.json.messages.Header;
import com.gslab.starfish.json.messages.Register_Received;
import com.gslab.starfish.json.messages.Register_Send;
import com.gslab.starfish.json.messages.encoders.*;
import com.gslab.starfish.json.messages.interfaces.Message;
import com.gslab.starfish.json.messages.decoders.*;
import com.gslab.starfish.json.*;

@ServerEndpoint(value = "/cti",  encoders={Register_Send_Encoder.class,Register_Received_Encoder.class}, decoders={Register_Send_Decoder.class,Register_Received_Decoder.class})
public class CTIServerEndPoint {
 
	Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	public static Session clientSession = null;
	
    private Logger logger = Logger.getLogger(this.getClass().getName());
 
    @OnOpen
    public void onOpen(Session session) {
    	logger.info("Client with session id:"+session.getId()+" connected.");
    	
    	
    	clientSession = session;
    	userSessions.add(session);
    	
    	
    }
 
    @OnMessage
    public void onMessage(Message message, Session session) {
    	
   
//        switch (message) {
//        case "quit":
//            try {
//            	
//                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Connection ended"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            break;
//           
//        }
    	Register_Received connectedMessage = null;
    	//if(message instanceof Register_Send){
    		//logger.info("Server got Register_Send message. "+((Register_Send)message).getPayload().get(0));
    		connectedMessage = new Register_Received();
    	try{
    		
    		
    		
    		Header header = new Header();
    		header.setInitiator("alice@example.com");
    		header.setTarget("alice@example.com");
    		header.setAction("connect-xyz");
    		
    		ControlR control = new ControlR();
    		control.setSession_id("SessionID1234");
    		control.setAck_sequence(1);
    		control.setPackage_type("register");
    		control.setSequence(1);
    		control.setMessage_state("final");
    		control.setSubsession_id("subsession00010-9989");
    		control.setType("responseFromStarfish");
    		control.setCorrelation_id("c1");
    		control.setVersion("1.0");
    			
                
    		
            connectedMessage.setControl(control);
            connectedMessage.setHeader(header);
    	session.getAsyncRemote().sendObject(connectedMessage);
    	}catch(Exception io){
    		System.out.println(io.getMessage());
    	}
    	//}
    	
    	
      
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
        userSessions.remove(session);
    }
}