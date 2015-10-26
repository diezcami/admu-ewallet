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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        new AsyncMethod().execute();


        //setContentView(textView);

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

    public void onResume() {
        super.onResume();
        new AsyncMethod().execute();
    }
    public void checkOut(View view)
    {
        Intent intent = (Intent) new Intent(this, MainActivity5.class);
        startActivity(intent);
        this.finish();
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


            if (!getBalance) {
                    //String link = "https://posttestserver.com/post.php";
                    String link = url;

                    RequestParams params = new RequestParams();
                    params.put("id_num", idNumber);

                    SyncHttpClient client = new SyncHttpClient();
                    RequestHandle requestHandle = client.get(link, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            JSONObject jo = new JSONObject(new String(responseBody));

                            final String balance = jo.getJSONObject("Bal").getString("balance");
                            newBalTemp = balance;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //  Student student = db.getStudent(Integer.parseInt(message));
                                    tvID.setText(stud.getName());
                                    tvBal.setTextSize(40);
                                    tvBal.setText(balance);
                                }
                            });

                        } catch (JSONException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvID.setText("Error1");
                                }
                            });
                        }
                    }

                    // Happens when there's an error 4xx
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        final String potato = new String(responseBody);
                        final int status = statusCode;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvID.setText(String.valueOf(status) + " | " + potato);
                            }
                        });
                    }
                });


                getBalance = true;
            } else {
                try {
                    String link = url;
                    URL urlNew = new URL(link);
                    URLConnection conn = urlNew.openConnection();
                    conn.setDoOutput(true);

                    String newBal = String.valueOf((Double.parseDouble(newBalTemp)) - Double.parseDouble(total));
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("id_num", idNumber)
                            .appendQueryParameter("new_balance", newBal);

                    String query = builder.build().getEncodedQuery();

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    wr.write(query);
                    wr.flush();

                    wr.close();
                    os.close();

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