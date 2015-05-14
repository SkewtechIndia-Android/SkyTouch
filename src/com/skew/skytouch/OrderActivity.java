package com.skew.skytouch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends ActionBarActivity {
	Button button11;
	TableLayout orderLayoutTable;
	Context context;
	String tempDish;
	float tempRate;
	int id;
	int dishcount;
	String dishcountT;
//	String ifRegistered;

	String rateT;
	ArrayList<Object> selectedDishList = new ArrayList<>();
	Button clearOrderBtn ;
	Button Submit;
	String deviceid;
	String orderDesc;
	String billamt;
	InputStream is=null;
	String result=null;
	String line=null;
	String name;
//	TextView totalbill;
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(intent);
			finish();
	}
	// ArrayList<String> selectedDishList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		clearOrderBtn = (Button) findViewById(R.id.clearOrder);
		clearOrderBtn.setTypeface(null, Typeface.BOLD);
		clearOrderBtn.setTextColor(getResources().getColor(R.color.black));
		clearOrderBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// Clear Order
				Toast toast = new Toast(getApplicationContext());
			    ImageView view1  = new ImageView(getApplicationContext()); 	 
			    view1.setBackgroundResource(R.drawable.removed); 
			    toast.setDuration(Toast.LENGTH_SHORT);
			    toast.setGravity(Gravity.CENTER, 0, 0);
			    toast.setView(view1); 
			    toast.show();
				FoodMenuActivity.order.clear();
				FoodMenuActivity.Bill = (float) 0.0;
//				finish();
				Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				startActivity(intent);
			}
		});
		
		Iterator<Map.Entry<String, String>> entries = FoodMenuActivity.order
				.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			String key = entry.getKey();
			String value = entry.getValue();
			selectedDishList.add(key + "#" + value);
		}

		for (int i = 0; i < selectedDishList.size(); i++) {
			// get a reference for the TableLayout
			orderLayoutTable = (TableLayout) findViewById(R.id.odertable);
			TableRow row = new TableRow(this);
			ImageView icon = new ImageButton(this);
	//		icon.setBackgroundResource(R.drawable.veg);
			String[] temp1 = selectedDishList.get(i).toString().split("#");
			tempDish = temp1[0];
			rateT = temp1[1];
			dishcountT = temp1[2];

			if(tempDish.contains("Chi")){
		    	icon.setBackgroundResource(R.drawable.chickenleg);}
		    else{
		    	icon.setBackgroundResource(R.drawable.paneertikka);
		    }
			
			TextView dishtext = new TextView(this);
			dishtext.setText("\t" + tempDish + " ");
//			dishtext.setGravity(Gravity.CENTER_HORIZONTAL);
			dishtext.setTypeface(null, Typeface.BOLD);
			dishtext.setTextColor(getResources().getColor(R.color.White));
			
			TextView rate = new TextView(this);
			rate.setText(rateT+" ");
			rate.setTypeface(null, Typeface.BOLD);
			rate.setTextColor(getResources().getColor(R.color.White));
			 
			TextView qty = new TextView(this);
			qty.setText("("+dishcountT + ") ");
			qty.setTypeface(null, Typeface.BOLD);
			qty.setGravity(Gravity.CENTER_HORIZONTAL);
			qty.setTextColor(getResources().getColor(R.color.maroon));
//			qty.setBackgroundColor(getResources().getColor(R.color.green12));
			
			
			ImageButton ad = new ImageButton(this);
			ad.setBackgroundResource(R.drawable.removedish);

			ad.setId(i);
			row.addView(icon);
			row.addView(dishtext);
			row.addView(rate);
			row.addView(qty);
			row.addView(ad);
			row.setGravity(Gravity.CENTER_VERTICAL);
			
			if(i%2==0){
			    row.setBackgroundColor(getResources().getColor(R.color.evenrow));	
			    dishtext.setTextColor(getResources().getColor(R.color.White));
			    rate.setTextColor(getResources().getColor(R.color.White));
			    
			    }else{
			    	row.setBackgroundColor(getResources().getColor(R.color.White));	
			    	dishtext.setTextColor(getResources().getColor(R.color.black));
			    	rate.setTextColor(getResources().getColor(R.color.black));
				    
			    }

			// add the TableRow to the TableLayout
			orderLayoutTable.addView(row, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

			ad.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					
					Toast toast = new Toast(getApplicationContext());
				    ImageView view1  = new ImageView(getApplicationContext()); 	 
				    view1.setBackgroundResource(R.drawable.removed); 
				    toast.setDuration(Toast.LENGTH_SHORT);
				    toast.setGravity(Gravity.CENTER, 0, 0);
				    toast.setView(view1); 
				    toast.show();
					id = view.getId();
					String[] temp = selectedDishList.get(id).toString()
							.split("#");
										tempDish = temp[0];
					tempRate = Float.parseFloat(temp[1]);
					dishcount = Integer.parseInt(temp[2]) - 1;
					if (dishcount <= 0) {
						FoodMenuActivity.order.remove(tempDish);
					} else {
						
						FoodMenuActivity.order.put(tempDish, tempRate + "#"+ dishcount);
					}
//					finish();
//					Intent intent;
					Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
					//	intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
					
					startActivity(intent);
					FoodMenuActivity.Bill = FoodMenuActivity.Bill - tempRate;

					
				}
			});
		}
		
		if(FoodMenuActivity.Bill>0.00){
		TextView gap = new TextView(this);
		gap.setGravity(Gravity.RIGHT);
		orderLayoutTable.addView(gap, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		TextView totalbill = new TextView(this);
		totalbill.setText("Bill(Approx.): "+FoodMenuActivity.Bill+"\t\t");
		totalbill.setTextColor(getResources().getColor(R.color.maroon));
		totalbill.setBackgroundColor(getResources().getColor(R.color.White));
		totalbill.setTypeface(null, Typeface.BOLD);
		totalbill.setGravity(Gravity.RIGHT);
		orderLayoutTable.addView(totalbill, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		Button orderButton = new Button(this);
		Button addmoreButton = new Button(this);
		
		addmoreButton.setText("Add more dishes to Order");
		addmoreButton.setTypeface(null, Typeface.BOLD);
		addmoreButton.setTextColor(getResources().getColor(R.color.White));
		orderLayoutTable.addView(addmoreButton, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addmoreButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		orderButton.setText("Submit Order");
		orderButton.setTypeface(null, Typeface.BOLD);
		orderButton.setTextColor(getResources().getColor(R.color.black));
		orderLayoutTable.addView(orderButton, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		orderButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
	
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				deviceid = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
				orderDesc = FoodMenuActivity.order.toString();
		        billamt = String.valueOf(FoodMenuActivity.Bill);
				// check if client is registered
//		        InputStream input = null;
		        try{
					/*Properties prop = new Properties();
					input = getBaseContext().getAssets().open("customerdetails.properties");
				 		// load a properties file
				 		if(input!=null){
				 			prop.load(input);
							// get the values
				 			String deviceidtemp = prop.getProperty("deviceid").trim();
				 			Toast.makeText(getApplicationContext(),"Registered: "+deviceidtemp,Toast.LENGTH_LONG).show();
				 			
				 		}*/
				 			// Add code to check the user properties
				 		/*	if(deviceidtemp.isEmpty()){
				 				throw new FileNotFoundException();
				 			}else{
				 			String fnametemp = prop.getProperty("fname").trim();
							String lnametemp = prop.getProperty("lname").trim();
							String mobilenotemp = prop.getProperty("mobileno").trim();
							String addresstemp = prop.getProperty("address").trim();
							if(fnametemp.isEmpty()||lnametemp.isEmpty()||mobilenotemp.isEmpty()||addresstemp.isEmpty()){
									throw new FileNotFoundException();
							}
				 		}*/
	//			 		}
		        insert();
				FoodMenuActivity.order.clear();
				FoodMenuActivity.Bill = (float) 0.00;
				Intent intent = new Intent(getApplicationContext(),FoodMenuActivity.class);
				startActivity(intent);
				finish();
				}/*catch(FileNotFoundException fe){
		    		Log.e("Not Registered", fe.toString());
		        	Toast.makeText(getApplicationContext(),"Please Complete Registration" ,Toast.LENGTH_LONG).show();	
		        	finish();
					Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
					startActivity(intent);
		        }*/catch(Exception e){
		        	Log.e("oops Something went wrong", e.toString());
		        	Toast.makeText(getApplicationContext(),"oops Something went wrong!!!" ,Toast.LENGTH_LONG).show();
		        }/*finally {
					if (input != null) {
						try {	
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}*/
			}
		});
		
		}
	}
	public void insert()
    {
		Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
		// Check if the user is registered
		try{
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	// Add resturant name here 
//    	nameValuePairs.add(new BasicNameValuePair("resturant","skytouch"));
    	nameValuePairs.add(new BasicNameValuePair("userid",deviceid));
    	nameValuePairs.add(new BasicNameValuePair("orderdesc",orderDesc));
    	nameValuePairs.add(new BasicNameValuePair("billamount",billamt));
    	nameValuePairs.add(new BasicNameValuePair("restoid","rs"));
    	/*try
    	{*/
    		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://meghawatersuppliers.com/Restro/admin/order.php");
	        // 192.186.194.131 
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	//        Toast.makeText(getApplicationContext(),deviceid+orderDesc+billamt, Toast.LENGTH_LONG).show();
	        Toast.makeText(getApplicationContext(), "Order Submitted", Toast.LENGTH_LONG).show();
    	}
		catch(NullPointerException ne){
			Log.e("Null Pointer", ne.toString());
        	Toast.makeText(getApplicationContext(),"Please get Registered" ,Toast.LENGTH_LONG).show();	
        	finish();
		//	Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
			startActivity(intent);
		}
        catch(UnknownHostException ue){
        	Log.e("No Internet", ue.toString());
        	Toast.makeText(getApplicationContext(),"No Internet Connection" ,Toast.LENGTH_LONG).show();
        }   
    	catch(Exception e){
        	Log.e("oops Something went wrong", e.toString());
        	Toast.makeText(getApplicationContext(),"oops Something went wrong!!!" ,Toast.LENGTH_LONG).show();
        }
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
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
//		SetImage
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
