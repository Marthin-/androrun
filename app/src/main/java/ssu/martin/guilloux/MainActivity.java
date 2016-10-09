package ssu.martin.guilloux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Le main de l'homme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMinValue(3);
        np.setMaxValue(60);
        np.setWrapSelectorWheel(false);

        //Parce que des noms de variables clairs et précis c'est trop facile. Lance/met à jour la fréquence du GPS
        Button b = (Button) findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stahp = new Intent(MainActivity.this, MyService.class);
                stopService(stahp);
                Intent stahrt = new Intent(MainActivity.this, MyService.class);
                stahrt.putExtra("timeValue", (int) np.getValue());
                startService(stahrt);
                Toast.makeText(MainActivity.this, "GPS update frequency : " + np.getValue() + "s", Toast.LENGTH_SHORT).show();
            }
        });
        //pour arrêter le GPS et fermer l'appli. Pls stahp !
        Button stahp = (Button) findViewById(R.id.stahp);
        stahp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "Stopped activity", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //self-explaining bouton de l'activité de liste des positions des familles.
        Button list = (Button) findViewById(R.id.view);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "Stopped activity", Toast.LENGTH_SHORT).show();
                Intent voirListe = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(voirListe);
            }
        });

        //On peut déduire le sens de codage des features en fonction de mes noms de variables.
        //Sert à vider le fichier des positions (loc.txt) histoire de faire le ménage.
        Button b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FILENAME = "loc.txt";
                File dir = getFilesDir();
                File file = new File(dir, FILENAME);
                if (file.delete()) {
                    Toast.makeText(MainActivity.this, "deleted loc.txt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error : could not delete loc.txt", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
