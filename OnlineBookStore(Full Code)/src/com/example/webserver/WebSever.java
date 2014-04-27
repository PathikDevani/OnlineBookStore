package com.example.webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.R.string;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.onlinebook.MainActivity;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class WebSever extends NanoHTTPD {
	Context context;
	AssetManager am;
	public WebSever(Context context ,int port) {
		super(port);
		this.context = context;
		am = context.getAssets();
	}

	@Override
	public Response serve(IHTTPSession session) {
		//Log.i("My", session.getUri());
		fileInfo file = new fileInfo(session.getUri());
		try {
			//from assets ....
			return new NanoHTTPD.Response(Status.OK, file.MIME,am.open(file.PATH));
			//from memory card .....
			//return new NanoHTTPD.Response(Status.OK, file.MIME,new FileInputStream(new File("/mnt/sdcard/online/" + file.PATH)));
		} catch (IOException e) {
			return new NanoHTTPD.Response(Status.NOT_FOUND, NanoHTTPD.MIME_HTML,"404 file not found.....");
		}
	}

	class fileInfo {
		private final Map<String, String> MIME_TYPES = new HashMap<String, String>() {
			{
				put("css", "text/css");
				put("htm", "text/html");
				put("html", "text/html");
				put("htm", "text/html");
				put("xml", "text/xml");
				put("java", "text/x-java-source, text/java");
				put("md", "text/plain");
				put("txt", "text/plain");
				put("asc", "text/plain");
				put("gif", "image/gif");
				put("jpg", "image/jpeg");
				put("jpeg", "image/jpeg");
				put("png", "image/png");
				put("mp3", "audio/mpeg");
				put("m3u", "audio/mpeg-url");
				put("mp4", "video/mp4");
				put("ogv", "video/ogg");
				put("flv", "video/x-flv");
				put("mov", "video/quicktime");
				put("swf", "application/x-shockwave-flash");
				put("js", "application/javascript");
				put("pdf", "application/pdf");
				put("doc", "application/msword");
				put("ogg", "application/x-ogg");
				put("zip", "application/octet-stream");
				put("exe", "application/octet-stream");
				put("class", "application/octet-stream");
			}
		};
		public String PATH,MIME;
		private String mimeType;
		public fileInfo(String str) {
			PATH = str.substring(1);
			if(PATH.length() == 0)
			{
				PATH = "index.html";
			}
			int dot = PATH.lastIndexOf(".");
			mimeType = PATH.substring(dot+1).toLowerCase();
			MIME = MIME_TYPES.get(mimeType);
		}
	}
}
