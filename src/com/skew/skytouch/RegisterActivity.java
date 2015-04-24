package com.skew.skytouch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity {
	EditText fname;
	EditText lname;
	EditText mobileno;
	EditText emailid;
	EditText address;
	String deviceID;
	Button registerbtn;
	String fname_val;
	String lname_val;
	String mobileno_val;
	String email_val;
	String add_val;
	String ifRegistered;
	InputStream is=null;
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
		Intent intent = new Intent(getApplicationContext(),MainActivity.class);
		startActivity(intent);
		finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	fname = (EditText) findViewById(R.id.fname);
	lname = (EditText) findViewById(R.id.lname);
	mobileno = (EditText) findViewById(R.id.mobileno);
	emailid = (EditText) findViewById(R.id.email);
	address = (EditText) findViewById(R.id.address);
	
	registerbtn = (Button) findViewById(R.id.registerbtn);
	// click button action		
	registerbtn.setOnClickListener(new OnClickListener() {
		@SuppressLint("NewApi")
		public void onClick(View arg0) {
	//		if(clientDetails()){
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				// COde to get phone IDs
//				TelephonyManager TM = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

				// Android Unique ID
				deviceID = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
				fname_val = fname.getText().toString();
				 lname_val = lname.getText().toString();
		         mobileno_val = mobileno.getText().toString();
		         email_val = emailid.getText().toString();
		         add_val = address.getText().toString();
//				Toast.makeText(getApplicationContext(), lastname+mobile+email, Toast.LENGTH_SHORT).show();
//				Toast.makeText(getApplicationContext(), deviceID, Toast.LENGTH_SHORT).show();
//				Log.d("androidID", deviceID);
//				finish();
				 insert();
				 finish();
				 Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				 startActivity(intent);
			/*}else{
				Toast.makeText(getApplicationContext(),"Please Register to Order" ,Toast.LENGTH_LONG).show();
				finish();
				Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
				startActivity(intent);
			}*/
			
		}
	});
//	Toast.makeText(getApplicationContext(), fname.toString()+lname.toString()+mobileno.toString()+email.toString()+address.toString(), Toast.LENGTH_LONG).show();	
	}
	public void insert()
    {
//		Toast.makeText(getApplicationContext(), fname_val+lname_val+mobileno_val+email_val+add_val+deviceID, Toast.LENGTH_LONG).show();
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("fname",fname_val));
    	nameValuePairs.add(new BasicNameValuePair("lname",lname_val));
    	nameValuePairs.add(new BasicNameValuePair("mobileno",mobileno_val));
    	nameValuePairs.add(new BasicNameValuePair("emailid",email_val));
    	nameValuePairs.add(new BasicNameValuePair("address",add_val));
    	nameValuePairs.add(new BasicNameValuePair("deviceid",deviceID));
    	try
    	{
    		HttpClient httpclient = new DefaultHttpClient();
//	        HttpPost httppost = new HttpPost("http://localhost/leadgen/submitlead.php");
//	        HttpPost httppost = new HttpPost("http://10.0.2.2/leadgen/submitlead.php");
	        HttpPost httppost = new HttpPost("http://meghae.com/resto/userreg.php");
	        // 192.186.194.131 
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	        
	}
        catch(Exception e)
	{
        	Log.e("Fail 1", e.toString());
//	    	Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        	Toast.makeText(getApplicationContext(),"No Internet Connection" ,Toast.LENGTH_LONG).show();
	}     
        
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	
/*	public boolean clientDetails(){
//			SetImage
			Properties prop = new Properties();
			InputStream input = null;
			Boolean ifRegisteredb = false;
		 	try {
		 		input = getBaseContext().getAssets().open("customerdetails.properties");
		 		// load a properties file
				prop.load(input);
				// get the values
				ifRegistered = prop.getProperty("registered");
				if(ifRegistered.equalsIgnoreCase("yes")){
					ifRegisteredb = true;
				}else{
					ifRegisteredb = false;
				}
			// All values entered in common
			}catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {	
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return ifRegisteredb;
	}*/
}
