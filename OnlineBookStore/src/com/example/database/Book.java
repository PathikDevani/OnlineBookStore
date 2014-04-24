package com.example.database;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Book {
	private String BOOK_INDEX;
	private String BOOK_ID;
	private String BOOK_NAME;
	private String BOOK_AUTHOR;
	private String BOOK_STAR;
	private String BOOK_REVIEW;
	private String BOOK_PAPERBACK;
	private String BOOK_LANGUGAGE;
	private String BOOK_PAGE;
	private String BOOK_PUBLISHER;
	private String BOOK_PRICE;
	private String BOOK_SELLER;
	private String BOOK_STOCK;
	private String BOOK_ITEM;

	public Book(String B_id) {
		BOOK_ID = B_id;
	}
	public void setIndex(String index) {
		BOOK_INDEX = index;
	}
	public String getIndex() {
		return BOOK_INDEX;
	}
	public void setName(String name) {
		BOOK_NAME = name;
	}
	public void setAuthor(String name) {
		BOOK_AUTHOR = name;
	}
	public void setStar(String name) {
		BOOK_STAR = name;
	}
	public void setReview(String name) {
		BOOK_REVIEW = name;
	}
	public void setPaperback(String name) {
		BOOK_PAPERBACK = name;
	}
	public void setLangugage(String name) {
		BOOK_LANGUGAGE = name;
	}
	public void setPage(String name) {
		BOOK_PAGE = name;
	}
	public void setPublisher(String name) {
		BOOK_PUBLISHER = name;
	}
	public void setPrice(String name) {
		BOOK_PRICE = name;
	}
	public void setSeller(String name) {
		BOOK_SELLER = name;
	}
	public void setStock(String name) {
		BOOK_STOCK = name;
	}
	public void setItem(String name) {
		BOOK_ITEM = name;
	}
	public String getId() {
		return BOOK_ID;
	}
	public String getName() {
		return BOOK_NAME;
	}
	public String getAuthor() {
		return BOOK_AUTHOR ;
	}
	public String getStar() {
		return BOOK_STAR;
	}
	public String getReview() {
		return BOOK_REVIEW;
	}
	public String getPaperback() {
		return BOOK_PAPERBACK;
	}
	public String getLangugage() {
		return BOOK_LANGUGAGE ;
	}
	public String getPage() {
		return BOOK_PAGE;
	}
	public String getPublisher() {
		return BOOK_PUBLISHER ;
	}
	public String getPrice() {
		return BOOK_PRICE ;
	}
	public String getSeller() {
		return BOOK_SELLER ;
	}
	public String getStock() {
		return BOOK_STOCK ;
	}
	public String getItem() {
		return BOOK_ITEM ;
	}
	public JSONObject Jobject() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("bid", BOOK_ID);
		json.put("bname", BOOK_NAME);
		json.put("author", BOOK_AUTHOR);
		json.put("star", BOOK_STAR);
		json.put("review", BOOK_REVIEW);
		json.put("paperback", BOOK_PAPERBACK);
		json.put("language", BOOK_LANGUGAGE);
		json.put("page", BOOK_PAGE);
		json.put("publisher", BOOK_PUBLISHER);
		json.put("price", BOOK_PRICE);
		json.put("seller", BOOK_SELLER);
		json.put("stock", BOOK_STOCK);
		json.put("item", BOOK_ITEM);
		return json;
	}
}
