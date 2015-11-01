package app.ewallet;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity4 extends ActionBarActivity {
    SharedPreferences sp;
    public String url = "http://188.166.253.236/index.php/User_Controller/balance";
    public String name = "0";
<<<<<<< HEAD
    String item1,item2,item3,item4;
    public boolean getBalance = false;

=======
    String item1,item2,item3,item4,qty1,qty2,qty3,qty4;
>>>>>>> 725a80a90fc505b1a749fe469e4d9f41d938d181
    public boolean getBalance = false;

    TextView tvID;
    TextView tvBal;
    TextView tvTotCost;
    TextView resultingBalance;
    String newBalTemp = "0";

    //LocalStudent database handler
    LocalDBhandler db = new LocalDBhandler(this);

    //BuyTransaction database handler
    LocalBuyTransHandler btdb = new LocalBuyTransHandler(this);

    //LocalStock Handler
    LocalStockHandler stdb = new LocalStockHandler(this);

    //Local itemOrder handler
    LocalitemOrder iodb  = new LocalitemOrder(this);

    //What we need to update in place of stocks
    LocalShopHandler shdb = new LocalShopHandler(this);


    //tv_actualbalance, tv_balance

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "INPUT SITE PHP";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    // products JSONArray
    JSONArray products = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        new AsyncMethod().execute();

<<<<<<< HEAD
<<<<<<< HEAD
=======
        Intent intent = getIntent();
        item1 = intent.getStringExtra("item1");
        item2 = intent.getStringExtra("item2");
        item3 = intent.getStringExtra("item3");
        item4 = intent.getStringExtra("item4");
>>>>>>> ffb08254eac62e21b8ba63eced46c81656a84186
=======
        Intent intent = getIntent();
        item1 = intent.getStringExtra("itemid1");
        item2 = intent.getStringExtra("itemid2");
        item3 = intent.getStringExtra("itemid3");
        item4 = intent.getStringExtra("itemid4");
        qty1 = intent.getStringExtra("qty1");
        qty2 = intent.getStringExtra("qty2");
        qty3 = intent.getStringExtra("qty3");
        qty4 = intent.getStringExtra("qty4");
>>>>>>> 725a80a90fc505b1a749fe469e4d9f41d938d181
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
<<<<<<< HEAD
    public void exit(View view)
    {

<<<<<<< HEAD
=======
        Date date = new Date();
        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = df6.format(date);
        Intent intent0 = getIntent();
        final String idNumber = intent0.getExtras().getString("idnum");
        Log.i("TESTTEST", String.valueOf(btdb.getLargestPrimKey()));
        //BuyTransaction bt = new BuyTransaction(btdb.getLargestPrimKey() + 1, timeStamp , Integer.parseInt(idNumber), 001);

        //currPrimaryKey is the primarykey of the buytransaction
        int currPrimaryKey = btdb.generatePrimaryKey();
        BuyTransaction bt = new BuyTransaction(currPrimaryKey, timeStamp, Integer.parseInt(idNumber),100);
        btdb.addBuyTrans(bt);

//im not sure if having multiple orders at one time will need more than one item order and stock...
        Stock stock1 = new Stock(currPrimaryKey, 100, itemnumberhere, timeStamp);
        //stdb is a localstock db
        stdb.addStock(stock1);
        Stock stock1 = new Stock(currPrimaryKey, 100, itemnumberhere, timeStamp);
        stdb.addStock(stock2);
        ItemOrder itemorder1 = new ItemOrder(currPrimaryKey, itemmumberhere, quantityfromAct1);
        ItemOrder itemorder2...
    }

>>>>>>> ffb08254eac62e21b8ba63eced46c81656a84186
=======
>>>>>>> 725a80a90fc505b1a749fe469e4d9f41d938d181
    public void checkOut(View view)
    {

        Intent intent0 = getIntent();
        final String idNumber = intent0.getExtras().getString("idnum");

        Date date = new Date();
        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = df6.format(date);

<<<<<<< HEAD
<<<<<<< HEAD
        btdb.drop();
=======
>>>>>>> ffb08254eac62e21b8ba63eced46c81656a84186
=======
        //check first if db is initially started with starting primary key of 10, or db already has contents

        sp = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sp.edit();
        String dbPrimaryKey = sp.getString("PRIMARYKEY", "initial");
        if(dbPrimaryKey.equals("initial"))
        {
            int currPrimaryKey = btdb.generatePrimaryKey();
            String primaryKey = String.valueOf(currPrimaryKey);
            sp = this.getPreferences(Context.MODE_PRIVATE);

            editor.putString("PRIMARYKEY", primaryKey);
            editor.commit();
            BuyTransaction bt = new BuyTransaction(currPrimaryKey, timeStamp, Integer.parseInt(idNumber),"001");
            btdb.addBuyTrans(bt);
            bt = btdb.getBuyTransaction(currPrimaryKey);
            Log.i("PrimaryKey", String.valueOf(bt.getTransID()));
            Log.i("Timestamp", bt.getTimeStamp());
            Log.i("idnumber", String.valueOf(bt.getIDNum()));
            Log.i("shopnumber", bt.getShopID());

            if(!item1.equals("")) {
                //This is the new way we update the stocks
                int itemID = Integer.parseInt(item1);
                Item item = shdb.getItem(itemID);
                shdb.updateItem(item.getID(), item.getQty()-Integer.parseInt(qty1));
                //

                Stock stock1 = new Stock(currPrimaryKey, "001", itemID, timeStamp, Integer.parseInt(qty1));
                stdb.addStock(stock1);
                stock1 = stdb.getStock(currPrimaryKey);
                Log.i("StockPrimaryKey", String.valueOf(stock1.getPrim()));
                Log.i("shopid", stock1.getShopID());
                Log.i("stockitemid", String.valueOf(stock1.getItemID()));
                Log.i("stocktimestamp", stock1.getTimeStamp());
                Log.i("stockqty", String.valueOf(stock1.getQty()));


                ItemOrder itemOrder1 = new ItemOrder(currPrimaryKey, Integer.parseInt(item1), Integer.parseInt(qty1));
                iodb.addItemOrder(itemOrder1);
                itemOrder1 = iodb.getItemOrder(currPrimaryKey);
                Log.i("IoPrimKey", String.valueOf(itemOrder1.getBuyTransID()));
                Log.i("ioitemId", String.valueOf(itemOrder1.getItemID()));
                Log.i("ioqty", String.valueOf(itemOrder1.getQty()));
            }


        }
        else
        {
            int currPrimaryKey = Integer.parseInt(dbPrimaryKey) + 1;
            btdb.setPrimaryKey(currPrimaryKey);
            BuyTransaction bt = new BuyTransaction(currPrimaryKey, timeStamp, Integer.parseInt(idNumber),"001");
            btdb.addBuyTrans(bt);
            bt = btdb.getBuyTransaction(currPrimaryKey);
            Log.i("PrimaryKey", String.valueOf(bt.getTransID()));
            Log.i("Timestamp", bt.getTimeStamp());
            Log.i("idnumber", String.valueOf(bt.getIDNum()));
            Log.i("shopnumber", bt.getShopID());
            Log.i("HEHE", "added to existing db");
            String primKey = String.valueOf(btdb.getPrimaryKey());
            editor.putString("PRIMARYKEY", primKey);
            editor.commit();

            if(!item1.equals("")) {
                //This is the new way we update the stocks
                int itemID = Integer.parseInt(item1);
                Item item = shdb.getItem(itemID);
                shdb.updateItem(item.getID(), item.getQty()-Integer.parseInt(qty1));
                //

                Stock stock1 = new Stock(currPrimaryKey, "001", Integer.parseInt(item1), timeStamp, Integer.parseInt(qty1));

                stock1 = stdb.getStock(currPrimaryKey);
                Log.i("StockPrimaryKey", String.valueOf(stock1.getPrim()));
                Log.i("shopid", stock1.getShopID());
                Log.i("stockitemid", String.valueOf(stock1.getItemID()));
                Log.i("stocktimestamp", stock1.getTimeStamp());
                Log.i("stockqty", String.valueOf(stock1.getQty()));
/*
                ItemOrder itemOrder1 = new ItemOrder(currPrimaryKey, Integer.parseInt(item1), Integer.parseInt(qty1));
                iodb.addItemOrder(itemOrder1);
                itemOrder1 = iodb.getItemOrder(currPrimaryKey);
                Log.i("IoPrimKey", String.valueOf(itemOrder1.getBuyTransID()));
                Log.i("ioitemId", String.valueOf(itemOrder1.getItemID()));
                Log.i("ioqty", String.valueOf(qty1));*/
            }

        }



        new AsyncMethod().execute();
        Intent intent = (Intent) new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    public void exit(View view)
    {
/*
        Intent intent0 = getIntent();
        final String idNumber = intent0.getExtras().getString("idnum");

        Date date = new Date();
        DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = df6.format(date);

        //check first if db is initially started with starting primary key of 10, or db already has contents

        sp = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = sp.edit();
        String dbPrimaryKey = sp.getString("PRIMARYKEY", "initial");
        if(dbPrimaryKey.equals("initial"))
        {
            int currPrimaryKey = btdb.generatePrimaryKey();
            String primaryKey = String.valueOf(currPrimaryKey);
            sp = this.getPreferences(Context.MODE_PRIVATE);

            editor.putString("PRIMARYKEY", primaryKey);
            editor.commit();
            BuyTransaction bt = new BuyTransaction(currPrimaryKey, timeStamp, Integer.parseInt(idNumber),"001");
            btdb.addBuyTrans(bt);
            bt = btdb.getBuyTransaction(currPrimaryKey);
            Log.i("PrimaryKey", String.valueOf(bt.getTransID()));
            Log.i("Timestamp", bt.getTimeStamp());
            Log.i("idnumber", String.valueOf(bt.getIDNum()));
            Log.i("shopnumber", bt.getShopID());
        }
        else
        {
            int currPrimaryKey = Integer.parseInt(dbPrimaryKey) + 1;
            btdb.setPrimaryKey(currPrimaryKey);
            BuyTransaction bt = new BuyTransaction(currPrimaryKey, timeStamp, Integer.parseInt(idNumber),"001");
            btdb.addBuyTrans(bt);
            bt = btdb.getBuyTransaction(currPrimaryKey);
            Log.i("PrimaryKey", String.valueOf(bt.getTransID()));
            Log.i("Timestamp", bt.getTimeStamp());
            Log.i("idnumber", String.valueOf(bt.getIDNum()));
            Log.i("shopnumber", bt.getShopID());
            Log.i("HEHE", "added to existing db");
            String primKey = String.valueOf(btdb.getPrimaryKey());
            editor.putString("PRIMARYKEY", primKey);
            editor.commit();
        }
>>>>>>> 725a80a90fc505b1a749fe469e4d9f41d938d181


        new AsyncMethod().execute();
        Intent intent = (Intent) new Intent(this, MainActivity5.class);
        startActivity(intent);
        //this.finish();*/
    }
    /**
     * This makes that 'loading screen' you see in mobile online games and such lol, it also does some stuff in the background, thus not
     * 'crashing' the system
     */
    private class AsyncMethod extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdL = new ProgressDialog(MainActivity4.this);
        /**
         * This is the UI loading screen
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdL.setMessage("\tLoading... Waiting...");
            pdL.show();

            tvID = (TextView) findViewById(R.id.tvidnumber);
            tvBal = (TextView) findViewById(R.id.tv_actualbalance);
            tvTotCost = (TextView) findViewById(R.id.tv_cost);
            resultingBalance = (TextView) findViewById(R.id.resulting_balance);
        }

        /**
         * These are the background tasks (ie. updating of the Database and shiz)
         * @param voids
         * @return
         */
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            final String idNumber = intent.getExtras().getString("idnum");
            final String total = intent.getExtras().getString("total");
            final Student stud = db.getStudent(Integer.parseInt(idNumber));
<<<<<<< HEAD


            if (getBalance == false) {
                    //String link = "https://posttestserver.com/post.php";
                try {
                    String link = url;

=======


            if (getBalance == false) {
                    //String link = "https://posttestserver.com/post.php";
                try {
                    String link = url;

>>>>>>> ffb08254eac62e21b8ba63eced46c81656a84186
                    RequestParams params = new RequestParams();
                    params.put("id_number", idNumber);
                    SyncHttpClient client = new SyncHttpClient();
                    RequestHandle requestHandle = client.post(link, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                           //is not called
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvID.setText("onSuccess");
                                }
                            });
                        }

                        // Happens when there's an error 4xx, and this is the thing that gets called somehow... and it works.
                        @Override
                        public void onFailure(int statusCode, Header[] headers, final byte[] responseBody, Throwable error) {
                            try {
                                final String potato = new String(responseBody);
                                JSONObject jo = new JSONObject(potato);
                                final String balance = jo.getString("balance");
                                newBalTemp = balance;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvID.setText(potato);
                                        tvID.setText(stud.getName());
                                        //tvBal.setTextSize(40);
                                        tvBal.setText(balance);
                                        tvTotCost.setText(String.valueOf(total));
                                        Double dNewBal = Double.parseDouble(newBalTemp) - Double.parseDouble(total);
                                        resultingBalance.setText(String.valueOf(dNewBal));
                                    }
                                });
                            } catch (JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvID.setText(new String(responseBody));
                                    }
                                });
                            }
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvID.setText("Dead");
                        }
                    });
                }

                getBalance = true;
            } else {
                try {
                    try {
                        String link = url;
                        Double dTotal = Double.parseDouble(total);
                        Double dNewBal = Double.parseDouble(newBalTemp) - dTotal;
                        String newBal = String.valueOf(dNewBal);

                        RequestParams params = new RequestParams();
                        params.put("id_number", idNumber);
                        params.put("new_balance",dNewBal);
                        SyncHttpClient client = new SyncHttpClient();
                        RequestHandle requestHandle = client.post(link, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                //Is not called
                            }

                            // Happens when there's an error 4xx, and this is the thing that gets called somehow... and it works.
                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                try {
                                    final String potato = new String(responseBody);
                                    JSONObject jo = new JSONObject(potato);
                                    final String balance = jo.getString("balance");
                                    newBalTemp = balance;
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvID.setText(potato);
                                            tvID.setText(stud.getName());
                                            //tvBal.setTextSize(40);
                                            tvBal.setText(balance);
                                        }
                                    });
                                } catch (JSONException e) {

                                }
                            }
                        });
                    } catch (Exception e) {

                    }



                } catch (Exception e) {

                }
            }

            return null;
        }

        /**
         * When everything is done; this gets rid of loading screen
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pdL.dismiss();
        }



    }
}