package com.skew.skytouch;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FeedbackActivity extends ActionBarActivity {
	WebView webView;
	String deviceid;
	InputStream is=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("userid",deviceid));
		// creating connection detector class instance
		setContentView(R.layout.activity_feedback);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		deviceid = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
		
		Log.e("pass 1", "connection success ");
		 ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
	           // Check for network connections
	            if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
		                 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ) {
	                // if connected with internet
	            	String url = "http://meghawatersuppliers.com/Restro/admin/feedback.php"+"?"+"deviceid="+deviceid+"&restoid=rs";
	    			webView = (WebView) findViewById(R.id.webViewfeedback);
	    			webView.getSettings().setJavaScriptEnabled(true);
	    			webView.setWebViewClient(new WebViewClient());
	    			webView.loadUrl(url);
	    	    }else if ( 
	              connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
	              connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
	              Toast.makeText(this, " No Internet Connection", Toast.LENGTH_LONG).show();
	              Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				  startActivity(intent);
				  finish();
	            }
	}
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		finish();
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feedback, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
