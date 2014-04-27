package com.example.service;

import java.io.IOException;

import org.json.JSONException;

import com.example.database.Book;
import com.example.database.DBhelper;
import com.example.msgencode.keyList;
import com.example.onlinebook.MainActivity;
import com.example.webserver.WebSever;
import com.example.websocketserver.SocketServer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AppService extends Service {
	public static DBhelper db;
	public static WebSever http;
	public static SocketServer webSocket;
	int http_port = 8080;
	int websocket_port = 5050;

	public static Context context;
	private static Handler handler;
	IntentFilter intentFilter;

	@Override
	public void onCreate() {
		super.onCreate();

		handler = new Handler();
		context = this;
		db = new DBhelper(context);
		intentFilter = new IntentFilter();
		intentFilter.addAction(keyList.brodCast.SEVER_START);
		intentFilter.addAction(keyList.brodCast.SEVER_STOP);
		intentFilter.addAction(keyList.brodCast.REFRESH_BOOK);
		intentFilter.addAction(keyList.brodCast.DELETE_BOOK);
		registerReceiver(receiver, intentFilter);
		http = new WebSever(this, http_port);
		webSocket = new SocketServer(websocket_port);
		keyList.brodCast.isServerStart = false;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		if (db != null)
			db.close();
		if (receiver != null)
			unregisterReceiver(receiver);
		super.onDestroy();
	}

	public static void showMessages(final String msg) {
		handler.post(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
				toast.show();
			}
		});
	}

	public void startServer() {
		keyList.brodCast.isServerStart = true;
		if (!webSocket.isAlive())
			webSocket.start();
		if (http.isAlive() == false) {
			do {
				try {
					http.start();
				} catch (IOException e) {
					http_port++;
					http = new WebSever(this, http_port);
				}
			} while (http.isAlive() == false);
			Log.i("My", "Http sever runing on port " + http_port);
		} else {

		}
	}

	public void stopServer() {
		keyList.brodCast.isServerStart = false;
		if (http.isAlive())
			http.stop();
		if (webSocket.isAlive()) {
			try {
				webSocket.stop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(keyList.brodCast.SEVER_START)) {
				startServer();
				showMessages("Server started.....");
				context.sendBroadcast(new Intent(keyList.brodCast.SEVER_STARTED));
			} else if (intent.getAction().equals(keyList.brodCast.SEVER_STOP)) {
				stopServer();
				showMessages("Server stoped.....");
				context.sendBroadcast(new Intent(keyList.brodCast.SEVER_STOPED));
			} else if (intent.getAction().equals(keyList.brodCast.REFRESH_BOOK)) {
				String bid = intent.getStringExtra("bid");
				Book book = db.getBook(bid);
				try {
					webSocket.sendToAll("[1,0,"+book.Jobject()+"]");
				} catch (JSONException e) {
				}
			}else if (intent.getAction().equals(keyList.brodCast.DELETE_BOOK)) {
				String bid = intent.getStringExtra("bid");
				webSocket.sendToAll("[1,1,"+"{'bid':'"+bid+"'}"+"]");
			}
		}
	};
}
