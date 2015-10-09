package com.example.set.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String Da_number = "com.mycompany.demo.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Intent intent = (Intent) new Intent(this, demo2.class);
        EditText ed = (EditText) findViewById(R.id.editText);
        String idNumber = ed.getText().toString();
        Toast toast = Toast.makeText(this, idNumber, Toast.LENGTH_SHORT);
        if (idNumber.equals("131402")){
            intent.putExtra(Da_number, idNumber);
            startActivity(intent);
        } else if (idNumber.equals("132271")) {
            intent.putExtra(Da_number, idNumber);
            startActivity(intent);
        }
        else {
            Toast toast2 = Toast.makeText(this, "INVALID ID NUMBER", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }
}
