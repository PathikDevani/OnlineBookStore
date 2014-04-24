package com.example.service;

import java.io.IOException;

import com.example.database.DBhelper;
import com.example.onlinebook.MainActivity;
import com.example.webserver.WebSever;
import com.example.websocketserver.SocketServer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AppService extends Service {
	public static DBhelper db;
	public static WebSever http;
	public static SocketServer webSocket;
	int http_port = 8080;
	int websocket_port = 4040;
	public static String SEVER_START = "com.exmple.onlinebook.START";
	public static String SEVER_STOP = "com.exmple.onlinebook.STOP";
	public static Context context;
	private static Handler handler;
	//public static DBhelper db;
	@Override
	public void onCreate() {
		super.onCreate();
	
		http = new WebSever(this, http_port);
		webSocket = new SocketServer(websocket_port);
		handler = new Handler();
		context = this;
		db = new DBhelper(context);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		//db = new DBhelper(context);
		//showMessages("service created....");
		if(!webSocket.isAlive())webSocket.start();
		if (http.isAlive() == false) {
			do {
				try {
					http.start();
				} catch (IOException e) {
					http_port++;
					http = new WebSever(this, http_port);
				}
			} while (http.isAlive() == false);
			Log.i("My", "Http sever runing on port "+http_port);
		}
		else {
			
		}
		Intent i = new Intent();
		i.setAction(SEVER_START);
		sendBroadcast(i);
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		if(db != null)
			db.close();
		if (http.isAlive())
			http.stop();
		if(webSocket.isAlive())
		{
			try {
				webSocket.stop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Intent i = new Intent();
		i.setAction(SEVER_STOP);
		sendBroadcast(i);
		super.onDestroy();
	}
	public static void showMessages(final String msg){
		handler.post(new Runnable() {
		    public void run() {
		        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		        toast.show();
		    }
		 });
	}
}
