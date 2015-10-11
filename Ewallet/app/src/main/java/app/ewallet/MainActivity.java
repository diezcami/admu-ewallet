package app.ewallet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public final static String Da_number = "com.mycompany.demo.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalDBhandler db = new LocalDBhandler(this);
        updateDatabase(db);
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
        Intent intent = (Intent) new Intent(this, WelcomeMenu.class);
        EditText ed = (EditText) findViewById(R.id.etidnumber);
        String idNumber = ed.getText().toString();
        Toast toast = Toast.makeText(this, idNumber, Toast.LENGTH_SHORT);

        try {
        Student student = db.getStudent(Integer.parseInt(idNumber));
        if (student.getID() > 0){
            intent.putExtra(Da_number, idNumber);
            startActivity(intent);
        } else {
            Toast toast2 = Toast.makeText(this, "INVALID ID NUMBER", Toast.LENGTH_SHORT);
            toast2.show();
        } } catch (Exception e) {
            Toast toast2 = Toast.makeText(this, "INVALID ID NUMBER", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    /**
     * Should be the one that updates the database at the start of each session
     * @param db - The database handler that is also technically the database object itself
     */
    public void updateDatabase(LocalDBhandler db) {
        Student stud1 = new Student(132271, "Legaspi, Seth Andrei L.", 1234, 100);
        Student stud2 = new Student(131402, "Domingo, Miguel Adrian", 4321, 145);

        if (!db.checkExist(stud1.getID())) {
            db.addStud(stud1);
        } else { }
        if (!db.checkExist(stud2.getID())) {
            db.addStud(stud2);
        } else { }
    }
}
