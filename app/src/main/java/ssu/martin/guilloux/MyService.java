package ssu.martin.guilloux;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by root on 27/09/16.
 */
public class MyService extends Service {
    public void onCreate(){}
    public void onDestroy(){
        Toast.makeText(MyService.this, "stopped service", Toast.LENGTH_SHORT).show();
    }
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(MyService.this, "started service", Toast.LENGTH_SHORT).show();
        String FILENAME = "loc.txt";
        String string = "0.000,0.000";
        FileOutputStream fos = null;
        try{
            fos = openFileOutput(FILENAME, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            fos.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }
    public IBinder onBind(Intent arg0){
        Toast.makeText(MyService.this, "bound service", Toast.LENGTH_SHORT).show();
        return null;
    }
}
