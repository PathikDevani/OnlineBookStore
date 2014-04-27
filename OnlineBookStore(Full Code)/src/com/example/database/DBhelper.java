package com.example.database;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.AvoidXfermode;
import android.util.Log;




public class DBhelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	
	private static final String TABLE_TRANSACTION = "buy";
	private static final String TRANSACTION_ID= "id";
	private static final String TRANSACTION_USER= "user";
	private static final String TRANSACTION_ITEMS= "items";
	private static final String TRANSACTION_MONEY= "money";
	private static final String TRANSACTION_TIME = "time";
	
	private static final String TABLE_BOOK = "book";
	private static final String BOOK_INDEX = "book_index";
	private static final String BOOK_ID = "book_id";
	private static final String BOOK_NAME = "book_name";
	private static final String BOOK_AUTHOR = "author";
	private static final String BOOK_STAR = "star";
	private static final String BOOK_REVIEW = "REVIEW";
	private static final String BOOK_PAPERBACK = "paperback";
	private static final String BOOK_LANGUGAGE = "langugage";
	private static final String BOOK_PAGE = "page";
	private static final String BOOK_PUBLISHER = "publisher";
	private static final String BOOK_PRICE = "price";
	private static final String BOOK_SELLER = "seller";
	private static final String BOOK_STOCK = "stock";
	private static final String BOOK_ITEM = "iteam";
	
	SQLiteDatabase db;
	Context context;
	public DBhelper(Context context) {
		super(context, "onlinebook", null, DATABASE_VERSION);
		this.context = context;
		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_TRANSACTION
				+ "(" + TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
				BOOK_ID+ " TEXT,"+ 
				TRANSACTION_USER+ " TEXT,"+
				TRANSACTION_ITEMS+ " TEXT,"+
				TRANSACTION_MONEY+ " TEXT,"+
				TRANSACTION_TIME+ " DATETIME"+
				   ")";
		
		String CREATE_TABLE_BOOK_DETAIL = "CREATE TABLE " + TABLE_BOOK
				+ "(" + BOOK_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				BOOK_ID + " TEXT," + 
				BOOK_NAME+ " TEXT,"+ 
				BOOK_AUTHOR+ " TEXT,"+
				BOOK_STAR+ " TEXT,"+
				BOOK_REVIEW+ " TEXT,"+
				BOOK_PAPERBACK+ " TEXT,"+
				BOOK_LANGUGAGE+ " TEXT,"+
				BOOK_PAGE+ " TEXT,"+
				BOOK_PUBLISHER+ " TEXT,"+
				BOOK_PRICE+ " TEXT,"+
				BOOK_SELLER+ " TEXT,"+
				BOOK_STOCK+ " TEXT,"+
				BOOK_ITEM+ " TEXT"+
				   ")";
		db.execSQL(CREATE_TABLE_TRANSACTION);
		db.execSQL(CREATE_TABLE_BOOK_DETAIL);
		String book_detail = BOOK_ID+","+
				BOOK_NAME+","+
				BOOK_AUTHOR+","+
				BOOK_STAR+","+
				BOOK_REVIEW+","+
				BOOK_PAPERBACK+","+
				BOOK_LANGUGAGE+","+
				BOOK_PAGE+","+
				BOOK_PUBLISHER+","+
				BOOK_PRICE+","+
				BOOK_SELLER+","+
				BOOK_STOCK+","+
				BOOK_ITEM;
		String transaction = BOOK_ID+","+
				TRANSACTION_USER+","+
				TRANSACTION_ITEMS+","+
				TRANSACTION_MONEY+","+
				TRANSACTION_TIME;
		
		/*db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b1','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','315','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b2','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','8478','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b3','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','4578','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b4','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','315','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b5','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','3455','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b6','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','585','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b7','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','255','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b8','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','155','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values ('b9','Avenida del parque','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','156','Pathik Books','115','WS Retail','In Stock','15')");*/
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b1','Java Cookbook','Ian F. Darwin','988855','90 Ratings | 26 Reviews','Paperback','English','870','O Reilly Media','2280','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b2','API Design for C++','Martin Reddy','988855','90 Ratings | 26 Reviews','Paperback','English','472','Elsevier / Morgan Kaufmann','3600','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b3','Android Cookbook','Ian F. Darwin','988855','90 Ratings | 26 Reviews','Paperback','English','710','O Reilly Media','3600','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b4','Chanakya Neeti','Chankya','988855','90 Ratings | 26 Reviews','Paperback','Gujrati','152','Diamond Books','83','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b5','The Monk as Man: The Unknown Life of Swami Vivekananda','Sankar','988855','90 Ratings | 26 Reviews','Paperback','English','264','Penguin','83','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b6','The 3 Mistakes of My Life','CHETAN BHAGAT','988855','90 Ratings | 26 Reviews','Paperback','English','260','RUPA & CO.','140','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b7','Talk Like TED','Carmine Gallo','988855','90 Ratings | 26 Reviews','Paperback','English','300','General Non-Fiction','415','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b8','Life of Pi','Yann Martel','988855','90 Ratings | 26 Reviews','Paperback','English','352','RUPA & CO.','251','Canongate','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b9','The Spinning Heart','Donal Ryan','988855','90 Ratings | 26 Reviews','Paperback','English','160','Random House','1346','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b10','Killing Them Softly (Cogan s Trade Movie Tie-in Edition) ','George V. Higgins','988855','90 Ratings | 26 Reviews','Paperback','English','216','Knopf Doubleday Publishing Group','923','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b11','Ten Things I Love About You','Quinn, Julia, Quinn','988855','90 Ratings | 26 Reviews','Paperback','English','348','Little, Brown Book Group','399','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b12','Casino Royale','Ian Fleming','988855','90 Ratings | 26 Reviews','Paperback','English','256','Vintage Classics','838','WS Retail','In Stock','15')");
		db.execSQL("insert into "+TABLE_BOOK+"  ("+book_detail+") values('b13','Leave Me Alone(Finding and Losing Myself)','Maureen Corrigan','988855','90 Ratings | 26 Reviews','Paperback','English','260','Knopf Doubleday Publishing Group','745','WS Retail','In Stock','15')");

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
		onCreate(db);
	}
	public JSONArray getJArrayBooklist(){
		JSONArray Jarray = new JSONArray();
		
		String[] columns = new String[] {BOOK_INDEX,BOOK_ID,BOOK_NAME,BOOK_AUTHOR,BOOK_STAR,BOOK_REVIEW,BOOK_PAPERBACK,BOOK_LANGUGAGE,BOOK_PAGE,BOOK_PRICE,BOOK_SELLER,BOOK_STOCK,BOOK_ITEM ,BOOK_PUBLISHER};
        Cursor cursor = db.query(TABLE_BOOK, columns, null,
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            
        } 
        if(cursor.getCount() > 0){
        	do{
    			Book book = cursorTobook(cursor);
    			
    			try {
    				Jarray.put(book.Jobject());
    			} catch (JSONException e) {
    				Jarray.put("null");
    			}
    		}while (cursor.moveToNext());
        }
        cursor.close();
          return Jarray;
	}
	public Book getBook(String Bid){
		Book book = null;
		String[] columns = new String[] {BOOK_INDEX,BOOK_ID,BOOK_NAME,BOOK_AUTHOR,BOOK_STAR,BOOK_REVIEW,BOOK_PAPERBACK,BOOK_LANGUGAGE,BOOK_PAGE,BOOK_PRICE,BOOK_SELLER,BOOK_STOCK,BOOK_ITEM ,BOOK_PUBLISHER};
		Cursor cursor = db.query(TABLE_BOOK, columns, BOOK_ID + "=?",
				new String[] { Bid }, null, null, null, null);
		if(cursor != null){
            cursor.moveToFirst();
        }
		if(cursor.getCount() > 0){
			book = cursorTobook(cursor);
		}
		cursor.close();
		return book;
	}
	public void setBook(Book book){
		ContentValues values = new ContentValues();
		
		values.put(BOOK_NAME, book.getName());
		values.put(BOOK_AUTHOR, book.getAuthor());
		values.put(BOOK_STAR, book.getStar());
		values.put(BOOK_REVIEW, book.getReview());
		values.put(BOOK_PUBLISHER, book.getPublisher());
		values.put(BOOK_PAPERBACK, book.getPaperback());
		values.put(BOOK_LANGUGAGE, book.getLangugage());
		values.put(BOOK_PAGE, book.getPage());
		values.put(BOOK_PRICE, book.getPrice());
		values.put(BOOK_SELLER, book.getSeller());
		values.put(BOOK_STOCK, book.getStock());
		values.put(BOOK_ITEM, book.getItem());
		if(isBookExists(book.getId())){
			db.update(TABLE_BOOK, values, BOOK_ID + "=?", new String[]{book.getId()});
		}else {
			
			long id = db.insert(TABLE_BOOK, null, values);
			values.put(BOOK_ID, "b"+id);
			db.update(TABLE_BOOK, values, BOOK_INDEX + "=?", new String[]{String.valueOf(id)});
		}
	    
	}
	
	public void addTransaction(Transaction transaction) {
		  ContentValues values = new ContentValues();
		  values.put(BOOK_ID, transaction.getBookId());
		  values.put(TRANSACTION_ITEMS, transaction.getItem());
		  values.put(TRANSACTION_MONEY, transaction.getMoney());
		  values.put(TRANSACTION_USER, transaction.getUser());
		  values.put(TRANSACTION_TIME, getDateTime());
		  db.insert(TABLE_TRANSACTION, null, values);
	}
	public JSONArray getTransacionList(){
		JSONArray Jarray = new JSONArray();
		
		String[] columns = new String[] {TRANSACTION_ID,BOOK_ID,TRANSACTION_ITEMS,TRANSACTION_MONEY,TRANSACTION_USER,TRANSACTION_TIME};
		Cursor cursor = db.query(TABLE_TRANSACTION, columns, null,
                null, null, null, null);
		
		 if(cursor != null){
	            cursor.moveToLast();
	        } 
	        if(cursor.getCount() > 0){
	        	do{
	    			Transaction transaction  = cursorTotransaction(cursor);
	    			
	    			try {
	    				Jarray.put(transaction.Jobject());
	    			} catch (JSONException e) {
	    				Jarray.put("null");
	    			}
	    		}while (cursor.moveToPrevious());
	        }
	        cursor.close();
	    return Jarray;
	}
	
	public Book getLastBook(){
		String[] columns = new String[] {BOOK_INDEX,BOOK_ID,BOOK_NAME,BOOK_AUTHOR,BOOK_STAR,BOOK_REVIEW,BOOK_PAPERBACK,BOOK_LANGUGAGE,BOOK_PAGE,BOOK_PRICE,BOOK_SELLER,BOOK_STOCK,BOOK_ITEM,BOOK_PUBLISHER };
		Cursor cursor = db.query(TABLE_BOOK, columns,null, null, null, null, null);
		if(cursor !=null){
			cursor.moveToLast();
			Book book = cursorTobook(cursor) ;
			return book;
		}
		cursor.close();
		return null;
	}
	private Transaction cursorTotransaction(Cursor cursor) {
		Transaction transaction = new Transaction();
		transaction.setTransctionId(cursor.getString(0));
		transaction.setBookId(cursor.getString(1));
		transaction.setItems(cursor.getString(2));
		transaction.setMoney(cursor.getString(3));
		transaction.setUser(cursor.getString(4));
		transaction.setTime(cursor.getString(5));
		return transaction;
	}

	public void deletBook(String BookId){
		db.delete(TABLE_BOOK, BOOK_ID+"= ?", new String[] { BookId });
	}
	public int getACCBlance(){
		int bal = 0;
		String[] columns = new String[] {TRANSACTION_MONEY};
		Cursor cursor = db.query(TABLE_TRANSACTION, columns, null,
                null, null, null, null);
		if(cursor != null){
            cursor.moveToLast();
        } 
        if(cursor.getCount() > 0){
        	do{
    			bal = bal + Integer.parseInt(cursor.getString(0));
    			
    		}while (cursor.moveToPrevious());
        }
        cursor.close();
		return bal;
	}
	
	
	public void close(){
		if(db != null)
			db.close();
	}
	
	private Book cursorTobook(Cursor cursor) {
		Book book = new Book(cursor.getString(1));
		book.setIndex(cursor.getString(0));
		book.setName(cursor.getString(2));
		book.setAuthor(cursor.getString(3));
		book.setStar(cursor.getString(4));
		book.setReview(cursor.getString(5));
		book.setPaperback(cursor.getString(6));
		book.setLangugage(cursor.getString(7));
		book.setPage(cursor.getString(8));
		book.setPrice(cursor.getString(9));
		book.setSeller(cursor.getString(10));
		book.setStock(cursor.getString(11));
		book.setItem(cursor.getString(12));
		book.setPublisher(cursor.getString(13));
		return book;
	}
	
	private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	public  boolean isBookExists(String bookid) {
		Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_BOOK + " WHERE "+BOOK_ID+"=?", new String[]{bookid});
        if (mCursor != null) {
            if(mCursor.getCount()!=0)
            {
                return true;
            }
        }
        mCursor.close();
     return false;
	}
}