package com.example.msgencode;

import java.util.Collection;

import org.java_websocket.WebSocket;
import org.json.JSONArray;

import com.example.onlinebook.MainActivity;
import com.example.service.AppService;

import android.R.integer;
import android.R.string;
import android.util.Log;

	
public class Encode {
	String count,key;
	int Action;
	public Encode(WebSocket conn,String msg, Collection<WebSocket> collection){
		Log.i("My", msg);
		init(msg);
		
		switch (Action) {
		case keyList.BOOK_LIST:
				JSONArray a = new JSONArray();
				a.put(count);
				a.put(AppService.db.getJArrayBooklist());
				conn.send(a.toString());
			break;
			
		case keyList.BUY_BOOK:
				String ks[] = key.split("\\!");
				BuyBook b = new BuyBook(count,ks[0],ks[1],Integer.parseInt(ks[2]),Integer.parseInt(ks[3]),conn,collection);
			break;
		default:
			Log.i("My", "action not found : "+Action);
			break;
		}
	}
	private void init(String msg) {
		
		String[] k = msg.split(":");
		count = k[0];
		Log.i("My", "acton is : "+k[1]);
		String[] ktemp = k[1].split("\\?");
		Action = Integer.parseInt(ktemp[0]);
		key = ktemp[1];
	}
}
