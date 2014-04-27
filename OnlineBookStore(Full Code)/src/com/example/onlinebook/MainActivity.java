package com.example.onlinebook;

import java.net.Inet4Address;
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
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.api.config;
import com.example.design.layout.MainLayout;
import com.example.msgencode.keyList;
import com.example.service.AppService;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	public BroadcastReceiver receiver;
	Button btn;
	public static int port;
	public static Context context;
	public static config cf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final View V = new View(getBaseContext());
		setContentView(V);
		V.post(new Runnable() {

			@Override
			public void run() {
				// init config
				if (android.os.Build.VERSION.SDK_INT > 9) {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}

				cf = new config(getBaseContext(), V.getWidth(), V.getHeight());
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
				    public void run() {
				     init();
				    }
				}, 500);
			}
		});

	}

	public static MainLayout main;

	protected void init() {
		context = this;
		// Create layout
		
		main = new MainLayout(getBaseContext(), cf.w, cf.h, 0, 0);
		setContentView(main);
	}

	@Override
	protected void onStart() {
		super.onStart();

		// start background service....
		startService(new Intent(getBaseContext(), AppService.class));

		receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(final Context context, final Intent intent) {

				Log.d("My", "Network connectivity change");

				if (intent.getExtras() != null) {
					final ConnectivityManager connectivityManager = (ConnectivityManager) context
							.getSystemService(Context.CONNECTIVITY_SERVICE);
					final NetworkInfo ni = connectivityManager
							.getActiveNetworkInfo();

					if (ni != null && ni.isConnectedOrConnecting()) {
						sendBroadcast(new Intent(
								keyList.brodCast.CONNECT_TO_NETWORK));
					} else if (intent.getBooleanExtra(
							ConnectivityManager.EXTRA_NO_CONNECTIVITY,
							Boolean.FALSE)) {
						sendBroadcast(new Intent(
								keyList.brodCast.DISCONNECT_TO_NETWORK));
					}
				}
			}
		};
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
		registerReceiver(receiver, intentFilter);
	}

	@Override
	protected void onDestroy() {

		sendBroadcast(new Intent(keyList.brodCast.DISTORY_APP));
		if (receiver != null)
			unregisterReceiver(receiver);
		if (!keyList.brodCast.isServerStart)
			stopService(new Intent(getBaseContext(), AppService.class));
		super.onDestroy();
	}

	public static String[] getLocalIpAddress() {
		ArrayList<String> addresses = new ArrayList<String>();
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& inetAddress instanceof Inet4Address) {
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
