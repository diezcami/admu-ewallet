package app.ewallet;

import android.app.IntentService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;

public class GetService extends IntentService
{
    public static final String MESSENGER = "Messenger";

    private String url = "http://posttestserver.com/post.php";

    public GetService()
    {
        super("SimpleGetService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Bundle extras = intent.getExtras();
        int result = Activity.RESULT_CANCELED;

        if (extras != null)
        {
            try
            {
                // Making HTTP request
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);

                // process response
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream is = httpEntity.getContent();
                InputStreamReader isr = new InputStreamReader(is);

                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                // server reply as a string
                String data = sb.toString();


                // pass result back to application
                Messenger messenger = (Messenger) extras.get(MESSENGER);
                Message msg = Message.obtain();

                // set result and reply data
                msg.arg1 = result;
                msg.obj = data;

                // send reply back
                messenger.send(msg);
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
