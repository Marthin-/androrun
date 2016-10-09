package ssu.martin.guilloux;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            String line;
            //feed list with positions

            final ListView list = (ListView)findViewById(R.id.listView);
            ArrayAdapter<String> tableau = new ArrayAdapter<>(list.getContext(),R.layout.liste);
            while((line=buffreader.readLine())!=null) {
                tableau.add(line);
            }
            list.setAdapter(tableau);

            //make list item clickable so that it launches ggmaps at selected position
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(ListActivity.this, "List item was clicked at " + id, Toast.LENGTH_SHORT).show();
                    int monId = view.getId();
                    //Toast.makeText(ListActivity.this, "id clicked " + monId, Toast.LENGTH_SHORT).show();
                    TextView tv1=(TextView) findViewById(monId);
                    String ltlg = tv1.getText().toString();
                    //Toast.makeText(ListActivity.this, "clicked on location " + ltlg, Toast.LENGTH_SHORT).show();
                    String [] tab = ltlg.split(",");
                    Toast.makeText(ListActivity.this, "lat : "+tab[0]+",lon : "+tab[1], Toast.LENGTH_SHORT).show();

                    //creating uri for ggmaps
                    String mapsUri=new String("http://maps.google.com/maps?addr="+tab[0]+","+tab[1]);

                    //sending it with an intent
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(mapsUri));
                    startActivity(intent);


                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
