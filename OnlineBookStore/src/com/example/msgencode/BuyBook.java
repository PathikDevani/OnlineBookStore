package com.example.msgencode;

import java.util.Collection;
import java.util.List;

import org.java_websocket.WebSocket;
import org.json.JSONArray;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.database.Book;
import com.example.database.Transaction;
import com.example.onlinebook.MainActivity;
import com.example.service.AppService;

public class BuyBook {
	Book book;
	public Collection<WebSocket> c;

	public BuyBook(String count, String Bid, String name, int item, int price,
			WebSocket conn, Collection<WebSocket> collection) {

		book = AppService.db.getBook(Bid);
		this.c = collection;
		Transaction transaction = new Transaction();
		transaction.setBookId(book.getId());
		transaction.setItems(item+"");
		transaction.setMoney((item * Integer.parseInt(book.getPrice()))+"");
		transaction.setUser(name);

		book.setItem(Integer.parseInt(book.getItem()) - item + "");
		AppService.db.setBook(book);
		AppService.db.addTransaction(transaction);

		JSONArray json = new JSONArray();
		json.put(count);
		json.put(true);
		conn.send(json.toString());
		
		MainActivity.context.sendBroadcast(new Intent(keyList.brodCast.UPDATE_BOOK_LIST));
		MainActivity.context.sendBroadcast(new Intent(keyList.brodCast.UPDATE_TRANSCTION_LIST));
		
		AppService.showMessages("Book purches by "+name+"....");
		
		c.remove(conn);
		if (!c.isEmpty()) {
		}
	}
}
