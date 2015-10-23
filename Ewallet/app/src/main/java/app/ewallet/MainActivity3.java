package app.ewallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void confirmPIN(View view)
    {
        LocalDBhandler db = new LocalDBhandler(this);
        Intent intent = (Intent) new Intent(this, MainActivity3.class);
        EditText ed = (EditText) findViewById(R.id.etidnumber);
        String idNumber = ed.getText().toString();
        Toast toast = Toast.makeText(this, idNumber, Toast.LENGTH_SHORT);

        //  try {
        // Student student = db.getStudent(Integer.parseInt(idNumber));
        //   if (student.getID() > 0){
        intent.putExtra(Da_number, idNumber);
        startActivity(intent);
    }


}
