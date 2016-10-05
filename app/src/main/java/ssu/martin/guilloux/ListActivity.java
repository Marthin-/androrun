package ssu.martin.guilloux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        try {
            // open the file for reading
            InputStream instream = openFileInput("loc.txt");

            // if file the available for reading
            //     if (instream) {
            // prepare the file for reading
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            String line = buffreader.readLine();
            Toast.makeText(ListActivity.this, line, Toast.LENGTH_SHORT).show();











    /*    ListView list = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> tableau = new ArrayAdapter<>(list.getContext(),R.layout.activity_list);

        // try opening the myfilename.txt
        try {
            // open the file for reading
            InputStream instream = openFileInput("loc.txt");

            // if file the available for reading
       //     if (instream) {
                // prepare the file for reading
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);

                String line;
                // read every line of the file into the line-variable, on line at the time
                while ((line=buffreader.readLine())!=null) {
                    tableau.add(line);
                }
                instream.close();
        //    }
        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(ListActivity.this, "Could not read loc.txt !", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ListActivity.this, "oui donc ça fonctionne pas.", Toast.LENGTH_SHORT).show();
        }

        list.setAdapter(tableau);
*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
