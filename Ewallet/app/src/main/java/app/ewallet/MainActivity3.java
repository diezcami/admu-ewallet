package app.ewallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {
    String message;
    public final static String Da_number = "com.mycompany.demo.MESSAGE";
    public String url = "188.166.242.63";
    String item1,item2,item3,item4;
    int PIN, id;
    Student currStudent;
    //boolean studentExist;
    LocalDBhandler db = new LocalDBhandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity2.Da_number);
        item1 = intent.getStringExtra("item1");
        item2 = intent.getStringExtra("item2");
        item3 = intent.getStringExtra("item3");
        item4 = intent.getStringExtra("item4");
        id = Integer.parseInt(message);
        currStudent = db.getStudent(id);
        PIN = currStudent.getPIN();
        //studentExist = db.checkExist(id);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //studentExist = db.checkExist(id);
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

    public void confirmPIN(View view)
    {
        LocalDBhandler db = new LocalDBhandler(this);
        Intent intent = (Intent) new Intent(this, MainActivity4.class);
        EditText etPin = (EditText) findViewById(R.id.et_pin);
        String input = etPin.getText().toString();
        if(input.equals(""))
        {
            Toast toast = Toast.makeText(this, "Invalid PIN", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            int inputInt = Integer.parseInt(input);
            Intent getIntent = getIntent();
            String idnum = getIntent.getStringExtra("idnum");
            String total = getIntent.getStringExtra("total");

            if(inputInt == PIN)
            {
                intent.putExtra(Da_number, message);
                intent.putExtra("idnum", idnum);
                intent.putExtra("total",total);
                intent.putExtra("item1",item1);
                intent.putExtra("item2",item2);
                intent.putExtra("item3",item3);
                intent.putExtra("item4",item4);
                startActivity(intent);
            }
            else
            {
                Toast toast = Toast.makeText(this, "Invalid PIN", Toast.LENGTH_SHORT);
                toast.show();
            }
            Student localstudent = db.getStudent(id);
            int localPin = localstudent.getPIN();
            String strlocalPin = String.valueOf(localPin);
            //Toast toast = Toast.makeText(this, strlocalPin, Toast.LENGTH_SHORT);
            // toast.show();
        }


    }


    /**
     * Should be the one that updates the database at the start of each session
     * @param db - The database handler that is also technically the database object itself
     */


    /**
     * This makes that 'loading screen' you see in mobile online games and such lol, it also does some stuff in the background, thus not
     * 'crashing' the system
     */
    private class AsyncMethod extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdL = new ProgressDialog(MainActivity3.this);

        /**
         * This is the UI loading screen
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdL.setMessage("\tLoading...");
            pdL.show();
        }

        /**
         * These are the background tasks (ie. updating of the Database and shiz)
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient client = new DefaultHttpClient();


            JSONObject json = new JSONObject();

            try {
                //  HttpPost post = new HttpPost(url);
                //  json.put("","");

            } catch (Exception e) {

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


