package app.ewallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


public class MainActivity2 extends ActionBarActivity {

    public final static String Da_number = "com.mycompany.demo.MESSAGE";
    public String url = "188.166.242.63";
    LocalDBhandler db = new LocalDBhandler(this);
    int idNumberint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent incomingIntent = getIntent();
        TextView item1 = (TextView) findViewById(R.id.item_1);
        TextView item2 = (TextView) findViewById(R.id.item_2);
        TextView item3 = (TextView) findViewById(R.id.item_3);
        TextView item4 = (TextView) findViewById(R.id.item_4);
        TextView item1qty = (TextView) findViewById(R.id.item_1qty);
        TextView item2qty = (TextView) findViewById(R.id.item_2qty);
        TextView item3qty = (TextView) findViewById(R.id.item_3qty);
        TextView item4qty = (TextView) findViewById(R.id.item_4qty);

        String order1 = incomingIntent.getStringExtra("item1");
        String order1qty = incomingIntent.getStringExtra("qty1");
        String order2 = incomingIntent.getStringExtra("item2");
        String order2qty = incomingIntent.getStringExtra("qty2");
        String order3 = incomingIntent.getStringExtra("item3");
        String order3qty = incomingIntent.getStringExtra("qty3");
        String order4 = incomingIntent.getStringExtra("item4");
        String order4qty = incomingIntent.getStringExtra("qty4");

        item1.setText(order1);
        item1qty.setText(order1qty);
        item2.setText(order2);
        item2qty.setText(order2qty);
        item3.setText(order3);
        item3qty.setText(order3qty);
        item4.setText(order4);
        item4qty.setText(order4qty);

        updateDatabase1(db);
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

    public void login(View view) {
        LocalDBhandler db = new LocalDBhandler(this);
        Intent intent = (Intent) new Intent(this, MainActivity3.class);
        EditText ed = (EditText) findViewById(R.id.etidnumber);
        String idNumber = ed.getText().toString();


        if(idNumber.equals(""))
        {
            idNumberint = 0;
        }
        else
        {
            idNumberint = Integer.parseInt(idNumber);
        }
        try {
            Student student = db.getStudent(idNumberint);
            if (student.getID() > 0) {
                intent.putExtra(Da_number, idNumber);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this, "WRONG CREDENTIALS", Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (Exception e)
        {
            Toast toast = Toast.makeText(this, "WRONG CREDENTIALSS", Toast.LENGTH_SHORT);
            toast.show();
        }





    }

    /**
     * Should be the one that updates the database at the start of each session
     * @param db - The database handler that is also technically the database object itself
     */

    public void updateDatabase1(LocalDBhandler db) {
        Student stud1 = new Student(144107, "Legaspi, Seth Andrei L.", 1234, 100);
        Student stud2 = new Student(130488, "Begonia, Basil Miguel B.", 4321, 145);

        if (!db.checkExist(stud1.getID())) {
            db.addStud(stud1);
        } else { }
        if (!db.checkExist(stud2.getID())) {
            db.addStud(stud2);
        } else { }
    }

    /**
     * This makes that 'loading screen' you see in mobile online games and such lol, it also does some stuff in the background, thus not
     * 'crashing' the system
     */
    private class AsyncMethod extends AsyncTask<Void, Void, Void> {
        ProgressDialog pdL = new ProgressDialog(MainActivity2.this);

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
