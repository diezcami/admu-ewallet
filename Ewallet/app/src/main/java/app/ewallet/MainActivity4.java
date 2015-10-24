package app.ewallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity4 extends ActionBarActivity {
    public String url = "http://188.166.253.236/server.php";
    public String name = "0";

    TextView tvID;

    //getting the Intent



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

        LocalDBhandler db = new LocalDBhandler(this);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity2.Da_number);;

        //  Student student = db.getStudent(Integer.parseInt(message));


        TextView tvBal = (TextView) findViewById(R.id.tv_actualbalance);
        tvBal.setTextSize(40);
        tvBal.setText("100");
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
        }

        /**
         * These are the background tasks (ie. updating of the Database and shiz)
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity2.Da_number);


            try {
                //String link = "https://posttestserver.com/post.php";
                String link = "http://dogs.compsat.org/server.php";
                String data = URLEncoder.encode("idnum", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                OutputStreamWriter wr =  new OutputStreamWriter(conn.getOutputStream());
                Log.d("TESTING", data);
                wr.write(data);
                wr.flush();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "n-";

                while ((line = reader.readLine())  != null) {
                    sb.append(line);
                    break;
                }
                if (sb.toString() != null) {
                    name = line;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvID.setText(name);
                        }
                    });
                }
            } catch (IOException e) {
                name = "Error";
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
