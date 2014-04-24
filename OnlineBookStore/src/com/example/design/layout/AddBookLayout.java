package com.example.design.layout;

import org.json.JSONException;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.database.Book;
import com.example.msgencode.keyList;
import com.example.onlinebook.MainActivity;
import com.example.service.AppService;
@SuppressWarnings("deprecation")
public class AddBookLayout extends abLyt {
	EditText booknameE, bookAuthorE, bookPageE, bookPriceE, bookItemE,bookSellerE,bookLangugageE,bookPublisherE;
	TextView booknameT, bookAuthorT, bookPageT, bookPriceT, bookItemT,bookSellerT,bookLangugageT,bookPublisherT;
	RelativeLayout r;
	Context context;
	ScrollView scroll;

	ABView cancle,ok;
	public AddBookLayout(final Context context, int width, int height, int x, int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.YELLOW);

		this.context = context;
		scroll = new ScrollView(context);
		scroll.setLayoutParams(new AbsoluteLayout.LayoutParams(width
				- MainActivity.cf.dpix[10], (height)
				- MainActivity.cf.dpix[10 + 50], MainActivity.cf.dpix[5],
				MainActivity.cf.dpix[5]));

		r = new RelativeLayout(context);
		r.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.FILL_PARENT,
				FrameLayout.LayoutParams.FILL_PARENT));
		scroll.addView(r);

		booknameT = new TextView(context);
		booknameT.setText("Book Name:");
		booknameT.setId(1);
		booknameT.setTextColor(Color.BLACK);
		booknameT.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT));
		r.addView(booknameT);

		booknameE = new EditText(context);
		setEditText(booknameE, 2, booknameT);
		r.addView(booknameE);

		bookAuthorT = new TextView(context);
		setTextView(bookAuthorT, 3, "Book Author:", booknameE);
		r.addView(bookAuthorT);

		bookAuthorE = new EditText(context);
		setEditText(bookAuthorE, 4, bookAuthorT);
		r.addView(bookAuthorE);

		bookPageT = new TextView(context);
		setTextView(bookPageT, 5, "Book Page:", bookAuthorE);
		r.addView(bookPageT);

		bookPageE = new EditText(context);
		setEditText(bookPageE, 6, bookPageT);
		r.addView(bookPageE);

		bookPriceT = new TextView(context);
		setTextView(bookPriceT, 7, "Book Price:", bookPageE);
		r.addView(bookPriceT);

		bookPriceE = new EditText(context);
		setEditText(bookPriceE, 8, bookPriceT);
		r.addView(bookPriceE);

		bookItemT = new TextView(context);
		setTextView(bookItemT, 9, "Book Item:", bookPriceE);
		r.addView(bookItemT);

		bookItemE = new EditText(context);
		setEditText(bookItemE, 10, bookItemT);
		r.addView(bookItemE);

		bookSellerT = new TextView(context);
		setTextView(bookSellerT, 11, "Book Seller:", bookItemE);
		r.addView(bookSellerT);

		bookSellerE = new EditText(context);
		setEditText(bookSellerE, 12, bookSellerT);
		r.addView(bookSellerE);
		
		bookLangugageT = new TextView(context);
		setTextView(bookLangugageT, 13, "Book Langugage:", bookSellerE);
		r.addView(bookLangugageT);

		bookLangugageE = new EditText(context);
		setEditText(bookLangugageE, 14, bookLangugageT);
		r.addView(bookLangugageE);
		
		bookPublisherT = new TextView(context);
		setTextView(bookPublisherT, 15, "Book Publisher:", bookLangugageE);
		r.addView(bookPublisherT);

		bookPublisherE = new EditText(context);
		setEditText(bookPublisherE, 16, bookPublisherT);
		r.addView(bookPublisherE);

		addView(scroll);
		
		cancle = new ABView(context, (width/2) - MainActivity.cf.dpix[10], MainActivity.cf.dpix[40], MainActivity.cf.dpix[5], height - MainActivity.cf.dpix[45]){
			{
				setBackgroundColor(Color.RED);
			}
			@Override
			public void onClick(MotionEvent event) {
				flushForm();
			}
		};
		addView(cancle);
		
		ok = new ABView(context, (width/2) - MainActivity.cf.dpix[10], MainActivity.cf.dpix[40], (width/2) + MainActivity.cf.dpix[5], height - MainActivity.cf.dpix[45]){
			{
				
				setBackgroundColor(Color.GREEN);
				
			}
			@Override
			public void onClick(MotionEvent event) {
				String bookname = booknameE.getText().toString();
				String bookauthor = bookAuthorE.getText().toString();
				String bookpage = bookPageE.getText().toString();
				String bookprice = bookPriceE.getText().toString();
				String bookitem = bookItemE.getText().toString();
				String bookseller = bookSellerE.getText().toString();
				String bookpublisher = bookPublisherE.getText().toString();
				String booklangugage = bookLangugageE.getText().toString();
				if(bookprice.equals("")||bookpublisher.equals("")||booklangugage.equals("")||bookname.equals("")||bookauthor.equals("")||bookpage.equals("")||bookitem.equals("")||bookseller.equals("")){
					Toast.makeText(context, "Enter currect information....",Toast.LENGTH_LONG).show();
				}
				else {
					Book book = new Book("temp");
					book.setName(bookname);
					book.setAuthor(bookauthor);
					book.setPage(bookpage);
					book.setPrice(bookprice);
					book.setItem(bookitem);
					book.setSeller(bookseller);
					book.setPublisher(bookpublisher);
					book.setLangugage(booklangugage);
					book.setStock("In Stock");
					book.setPaperback("Papaerback");
					book.setStar("888888");
					book.setReview("No review");
					Toast.makeText(context, "Book added to List.....",Toast.LENGTH_LONG).show();
					AppService.db.setBook(book);
					flushForm();
					MainActivity.context.sendBroadcast(new Intent(keyList.brodCast.UPDATE_BOOK_LIST));
					MainActivity.context.sendBroadcast(new Intent(keyList.brodCast.UPDATE_TRANSCTION_LIST));
				}
			}
		};
		addView(ok);
	}

	protected void flushForm() {
		booknameE.setText("");
		bookAuthorE.setText("");
		bookPageE.setText("");
		bookPriceE.setText("");
		bookItemE.setText("");
		bookSellerE.setText("");
		bookPublisherE.setText("");
		bookLangugageE.setText("");
	}

	public void setTextView(TextView texView, int id, String text, View view) {
		texView.setText(text);
		texView.setTextColor(Color.BLACK);
		RelativeLayout.LayoutParams r = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		r.addRule(RelativeLayout.BELOW, view.getId());
		r.topMargin = MainActivity.cf.dpix[5];
		texView.setId(id);
		texView.setLayoutParams(r);
	}

	public void setEditText(EditText editText, int id, TextView view) {
		editText.setTextColor(Color.BLACK);
		RelativeLayout.LayoutParams r = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		r.addRule(RelativeLayout.BELOW, view.getId());
		editText.setId(id);
		editText.setLayoutParams(r);
	}
}
