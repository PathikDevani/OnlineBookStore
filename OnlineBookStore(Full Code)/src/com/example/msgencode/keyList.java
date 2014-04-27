package com.example.msgencode;

public class keyList {
	public static final int BOOK_LIST = 1;
	public static final int BUY_BOOK = 2;
	
	public static class brodCast{
		public static final String UPDATE_BOOK_LIST = "com.example.onlinebook.updatebooklist";
		public static final String UPDATE_TRANSCTION_LIST = "com.example.onlinebook.updatetransctionlist";
		public static final String DISTORY_APP = "com.example.onlinebook.disroyapp";
		public static final String CONNECT_TO_NETWORK = "com.example.onlinebook.connect_to_network";
		public static final String DISCONNECT_TO_NETWORK = "com.example.onlinebook.connect_to_network";
		public static final String SEVER_START = "com.exmple.onlinebook.START";
		public static final String SEVER_STOP = "com.exmple.onlinebook.STOP";
		public static final String SEVER_STARTED = "com.exmple.onlinebook.STARTED";
		public static final String SEVER_STOPED = "com.exmple.onlinebook.STOPED";
		public static final String REFRESH_BOOK = "com.exmple.onlinebook.REFRESH_BOOK";
		public static boolean isServerStart = false;
	}
}
