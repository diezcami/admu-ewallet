package app.ewallet;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity4 extends ActionBarActivity {
    public String url = "http://188.166.253.236/index.php/User_Controller/balance";
    public String name = "0";

    public boolean getBalance = false;

    TextView tvID;
    TextView tvBal;
    TextView tvTotCost;
    TextView resultingBalance;
    String newBalTemp = "0";

    //LocalStudent database handler
    LocalDBhandler db = new LocalDBhandler(this);


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

    public void checkOut(View view)
    {
        new AsyncMethod().execute();
        Intent intent = (Intent) new Intent(this, MainActivity5.class);
        startActivity(intent);
        //this.finish();
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


            if (getBalance == false) {
                    //String link = "https://posttestserver.com/post.php";
                try {
                    String link = url;

                    RequestParams params = new RequestParams();
                    params.put("id_number", idNumber);
                    SyncHttpClient client = new SyncHttpClient();
                    RequestHandle requestHandle = client.post(link, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                           //is not called
                            tvID.setText("onSuccess");
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
                                        tvTotCost.setText(String.valueOf(total));
                                        Double dNewBal = Double.parseDouble(newBalTemp) - Double.parseDouble(total);
                                        resultingBalance.setText(String.valueOf(dNewBal));
                                    }
                                });
                            } catch (JSONException e) {

                            }
                        }
                    });
                } catch (Exception e) {

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