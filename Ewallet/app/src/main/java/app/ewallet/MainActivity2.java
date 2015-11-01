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
    String item1String = "";
    String item2String = "";
    String item3String = "";
    String item4String = "";
    String qty1String = "";
    String qty2String = "";
    String qty3String = "";
    String qty4String = "";
    public final static String Da_number = "com.mycompany.demo.MESSAGE";
    public String url = "188.166.242.63";
    LocalDBhandler db = new LocalDBhandler(this);
    int idNumberint;
    Double total = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent incomingIntent = getIntent();
        TextView item1 = (TextView) findViewById(R.id.item_1);
        TextView item2 = (TextView) findViewById(R.id.item_2);
        TextView item3 = (TextView) findViewById(R.id.item_3);
        TextView item4 = (TextView) findViewById(R.id.item_4);
        TextView item1price = (TextView) findViewById(R.id.item_1price);
        TextView item2price = (TextView) findViewById(R.id.item_2price);
        TextView item3price = (TextView) findViewById(R.id.item_3price);
        TextView item4price = (TextView) findViewById(R.id.item_4price);
        TextView item1qty = (TextView) findViewById(R.id.item_1qty);
        TextView item2qty = (TextView) findViewById(R.id.item_2qty);
        TextView item3qty = (TextView) findViewById(R.id.item_3qty);
        TextView item4qty = (TextView) findViewById(R.id.item_4qty);
        TextView item1total = (TextView) findViewById(R.id.item_1total);
        TextView item2total = (TextView) findViewById(R.id.item_2total);
        TextView item3total = (TextView) findViewById(R.id.item_3total);
        TextView item4total = (TextView) findViewById(R.id.item_4total);

        if(!incomingIntent.getStringExtra("item1").equals(""))
        {
            String order1 = incomingIntent.getStringExtra("item1");
            String order1qty = incomingIntent.getStringExtra("qty1");
            String order1Price = incomingIntent.getStringExtra("item1Price");
            Double order1Total = Double.parseDouble(order1Price);
            order1Total = Integer.parseInt(order1qty) * order1Total;
            total += order1Total;
            item1.setText(order1);
            item1qty.setText(order1qty);
            item1price.setText(order1Price);
            item1total.setText(String.valueOf(order1Total));
            item1String = incomingIntent.getStringExtra("itemid1");
            qty1String = incomingIntent.getStringExtra("qty1");
        }
        else
        {
            item1.setText("");
            item1qty.setText("");
            item1price.setText("");
            item1total.setText("");
        }

        if(!incomingIntent.getStringExtra("item2").equals(""))
        {
            String order2 = incomingIntent.getStringExtra("item2");
            String order2qty = incomingIntent.getStringExtra("qty2");
            String order2Price = incomingIntent.getStringExtra("item2Price");
            Double order2Total = Double.parseDouble(order2Price);
            order2Total = Integer.parseInt(order2qty) * order2Total;
            total += order2Total;
            item2.setText(order2);
            item2qty.setText(order2qty);
            item2price.setText(order2Price);
            item2total.setText(String.valueOf(order2Total));
            item2String = incomingIntent.getStringExtra("item2id");
            qty2String = incomingIntent.getStringExtra("qty2");
        }
        else
        {
            item2.setText("");
            item2qty.setText("");
            item2price.setText("");
            item2total.setText("");
        }

        if(!incomingIntent.getStringExtra("item3").equals(""))
        {
            String order3 = incomingIntent.getStringExtra("item3");
            String order3qty = incomingIntent.getStringExtra("qty3");
            String order3Price = incomingIntent.getStringExtra("item3Price");
            Double order3Total = Double.parseDouble(order3Price);
            order3Total = Integer.parseInt(order3qty) * order3Total;
            total += order3Total;
            item3.setText(order3);
            item3qty.setText(order3qty);
            item3price.setText(order3Price);
            item3total.setText(String.valueOf(order3Total));
            item3String = incomingIntent.getStringExtra("itemid3");
            qty3String = incomingIntent.getStringExtra("qty3");
        }
        else
        {
            item3.setText("");
            item3qty.setText("");
            item3price.setText("");
            item3total.setText("");
        }

        if(!incomingIntent.getStringExtra("item4").equals(""))
        {
            String order4 = incomingIntent.getStringExtra("item4");
            String order4qty = incomingIntent.getStringExtra("qty4");
            String order4Price = incomingIntent.getStringExtra("item2Price");
            Double order4Total = Double.parseDouble(order4Price);
            order4Total = Integer.parseInt(order4qty) * order4Total;
            total += order4Total;
            item4.setText(order4);
            item4qty.setText(order4qty);
            item4price.setText(order4Price);
            item4total.setText(String.valueOf(order4Total));
            item4String = incomingIntent.getStringExtra("itemid4");
            qty4String = incomingIntent.getStringExtra("qty4");
        }
        else
        {
            item4.setText("");
            item4qty.setText("");
            item4price.setText("");
            item4total.setText("");
        }

        TextView totalTv = (TextView) findViewById(R.id.allitems_total);
        totalTv.setText(String.valueOf(total));
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

    public void login(View view) {
        LocalDBhandler db = new LocalDBhandler(this);
        Intent intent0 = (Intent) new Intent(this, MainActivity3.class);
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
                intent0.putExtra(Da_number, idNumber);
                intent0.putExtra("idnum", idNumber);
                intent0.putExtra("total", String.valueOf(total));
                intent0.putExtra("itemid1", item1String);
                intent0.putExtra("itemid2", item2String);
                intent0.putExtra("itemid3", item3String);
                intent0.putExtra("itemid4", item4String);
                intent0.putExtra("qty1", qty1String);
                intent0.putExtra("qty2", qty2String);
                intent0.putExtra("qty3", qty3String);
                intent0.putExtra("qty4", qty4String);
                startActivity(intent0);
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
