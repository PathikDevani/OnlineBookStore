package com.example.onlinebook;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.Utils;
import com.example.api.config;
import com.example.design.layout.MainLayout;
import com.example.msgencode.keyList;
import com.example.service.AppService;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	//public static DBhelper db;
	public WIFI wifiInterface;
	public WifiManager wifi;
	public BroadcastReceiver wificonnected;
	TextView wifiText, ipText;
	Button btn;
	public static int port;
	public static String SEVER_START = "com.exmple.onlinebook.START";
	public static String SEVER_STOP = "com.exmple.onlinebook.STOP";
	public boolean SEVER_STAT = false;
	public static Context context;
	public static config cf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final View V = new View(getBaseContext());
		setContentView(V);
		V.post(new Runnable() {

			@Override
			public void run() {
				// init config
				if (android.os.Build.VERSION.SDK_INT > 9) {
				    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				    StrictMode.setThreadPolicy(policy);
				}
				
				cf = new config(getBaseContext(), V.getWidth(), V.getHeight());
				init();
			}
		});

	}
	public static MainLayout main;
	protected void init() {
		context = this;
		main = new MainLayout(getBaseContext(), cf.w, cf.h, 0, 0);
		setContentView(main);
		getWindow().setSoftInputMode(
			    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		//db = new DBhelper(getBaseContext());
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		
		

		String[] s = getLocalIpAddress();
		for (int i = 0; i < s.length; i++) {
			Toast toast = Toast.makeText(getBaseContext(), s[i], Toast.LENGTH_SHORT);
	        toast.show();
		}
		
		//start service....
		startService(new Intent(getBaseContext(), AppService.class));
		//finish();
		/*btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (btn.getText().equals("Start")) {
					btn.setText("Stop");
					startService(new Intent(context, ForegroundService.class));
				} else {
					btn.setText("Start");
					stopService(new Intent(context, ForegroundService.class));
				}
			}
		});

		wifiInterface = new WIFI() {

			@Override
			public void wificonnect() {
				ipText.setText("Ip : " + getwifiIp(wifi) + ":8080");
				wifiText.setText("Wifi is on");
			}
		};

		wificonnected = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent intent) {
				if (intent.getAction().equals(SEVER_START)) {
					if (wifi.isWifiEnabled()) {
						Log.i("My", "i am conected " + getwifiIp(wifi));
						wifiText.setText("Wifi is On");
						ipText.setText("Ip : " + getwifiIp(wifi) + ":8080");
					} else {
						wifiText.setText("Wifi is Off");
						ipText.setText("no IP");
						wifi.setWifiEnabled(true);
					}
					SEVER_STAT = true;
				}
				if (intent.getAction().equals(SEVER_STOP)) {
					if (wifi.isWifiEnabled()) {
						Log.i("My", "i am conected " + getwifiIp(wifi));
						wifiText.setText("Wifi is On");
					} else {
						wifiText.setText("Wifi is Off");
					}
					ipText.setText("server is stop");
					SEVER_STAT = true;
				}
				if (SEVER_STAT) {
					if (intent.getAction().equals(
							WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
						boolean connected = intent.getBooleanExtra(
								WifiManager.EXTRA_SUPPLICANT_CONNECTED, false);
						if (!connected) {
							// Start service for disconnected state here
						}
					}

					else if (intent.getAction().equals(
							WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
						NetworkInfo netInfo = intent
								.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
						if (netInfo.isConnected()) {
							wifiInterface.wificonnect();
						}
					}

				}
			}
		};
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
		intentFilter.addAction(SEVER_START);
		intentFilter.addAction(SEVER_STOP);
		registerReceiver(wificonnected, intentFilter);
		// finish();*/
	}

	@Override
	protected void onDestroy() {
		
		sendBroadcast(new Intent(keyList.brodCast.DISTORY_APP));
		if (wificonnected != null)
			unregisterReceiver(wificonnected);
		/*if(db != null)
			db.close();*/
		super.onDestroy();
	}

	public String getwifiIp(WifiManager w) {
		int ip = w.getConnectionInfo().getIpAddress();
		String ipString = String.format("%d.%d.%d.%d", (ip & 0xff),
				(ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
		return ipString;
	}

	public interface WIFI {
		public void wificonnect();
		
	}
	
	public static String[] getLocalIpAddress()
	{          
	    ArrayList<String> addresses = new ArrayList<String>();
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    addresses.add(inetAddress.getHostAddress().toString());
	                }
	             }
	         }
	     } catch (SocketException ex) {
	         String LOG_TAG = null;
	         Log.e(LOG_TAG, ex.toString());
	     }
	     return addresses.toArray(new String[0]);
	}

}
