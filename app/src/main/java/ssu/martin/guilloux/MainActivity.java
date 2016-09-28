package ssu.martin.guilloux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "Started activity", Toast.LENGTH_SHORT).show();
            }
        });

        Button stahp = (Button) findViewById(R.id.stahp);
        stahp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "Stopped activity", Toast.LENGTH_SHORT).show();
            }
        });

        Button b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FILENAME = "loc.txt";
                File dir = getFilesDir();
                File file = new File(dir,FILENAME);
                if(file.delete()){
                    Toast.makeText(MainActivity.this, "deleted loc.txt", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Error : could not delete loc.txt", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
