package ssu.martin.guilloux;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;


public class MyService extends Service {

    private LocationManager mlocManager;
    private LocationListener fonzie;

    public void onCreate() {
    }

    public void onDestroy() {
        //    Toast.makeText(MyService.this, "stopped service", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MyService.this, "GPS unauthorized...", Toast.LENGTH_SHORT).show();
        }
        this.mlocManager.removeUpdates(fonzie);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
    //    Toast.makeText(MyService.this, "started service", Toast.LENGTH_SHORT).show();
        this.mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MyService.this, "GPS unauthorized...", Toast.LENGTH_SHORT).show();
            return -1;
        }

        //get location at start

        Location location = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        String FILENAME = "loc.txt";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String string=new String(location.getLatitude()+":"+location.getLongitude());
        try {
            fos.write(string.getBytes());
            Toast.makeText(MyService.this, "new location "+ string, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MyService.this, "could not write !", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        //set listener

        this.mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, fonzie = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String FILENAME = "loc.txt";
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILENAME, Context.MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String string=new String(location.getLatitude()+":"+location.getLongitude());
                try {
                    fos.write(string.getBytes());
                    Toast.makeText(MyService.this, "new location "+ string, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MyService.this, "could not write !", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText(MyService.this, "Status Changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(MyService.this, "GPS enabled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(MyService.this, "GPS disabled", Toast.LENGTH_SHORT).show();
            }
        });
        return START_STICKY;
    }
    public IBinder onBind(Intent arg0){
        Toast.makeText(MyService.this, "bound service", Toast.LENGTH_SHORT).show();
        return null;
    }
}
