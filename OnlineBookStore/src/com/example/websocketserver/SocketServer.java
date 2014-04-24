package com.example.websocketserver;

import java.net.InetSocketAddress;
import java.util.Collection;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.example.msgencode.Encode;
import com.example.service.AppService;

import android.util.Log;
import android.widget.Toast;
	
public class SocketServer extends WebSocketServer{
	
	public SocketServer(int port) {
		super(new InetSocketAddress(port));
		
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		Log.i("My", "conected onOpen....." + handshake.getResourceDescriptor());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		Log.i("My", "conected onClose....." + reason );
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		Encode encode = new Encode(conn, message,connections());
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		//System.out.print(ex);
	}
	
	public void sendToAll( String text ) {
		Collection<WebSocket> con = connections();
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}
}
