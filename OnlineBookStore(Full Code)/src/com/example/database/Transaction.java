package com.example.database;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ClipData.Item;

public class Transaction {
	private String TRANSCTION_ID;
	private String BOOK_ID;
	private String USER;
	private String ITEMS;
	private String MONEY;
	private String TTIME;
	
	public void setTransctionId(String Transction_id){
		TRANSCTION_ID = Transction_id;
	}
	public void setTime(String time){
		TTIME = time;
	}
	public String getTransctionId() {
		return TRANSCTION_ID;
	}
	public String getTime() {
		return TTIME;
	}
	public void setBookId(String book_id){
		BOOK_ID = book_id;
	}
	public void setUser(String name) {
		USER = name;
	}
	public void setItems(String item) {
		ITEMS = item;
	}
	public void setMoney(String string) {
		MONEY = string;
	}
	public String getBookId() {
		return BOOK_ID;
	}
	public String getUser() {
		return USER;
	}
	public int getItem() {
		return Integer.parseInt(ITEMS);
	}
	public int getMoney() {
		return Integer.parseInt(MONEY);
	}
	public JSONObject Jobject() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("tid", TRANSCTION_ID);
		json.put("bid", BOOK_ID);
		json.put("user", USER);
		json.put("item", ITEMS);
		json.put("money",MONEY);
		json.put("time", TTIME);
		return json;
	}
}
