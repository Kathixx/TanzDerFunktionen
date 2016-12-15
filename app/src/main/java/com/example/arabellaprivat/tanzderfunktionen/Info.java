package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * hier werden Tipps zum Lösen der Aufgaben gegeben
 */
public class Info extends AppCompatActivity {

    /** zeigt den Hilfetext an */
    private TextView t_help;
    /** schließt die Activity */
    private Button b_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Variablen belegen
        t_help = (TextView) findViewById(R.id.help);
        b_close = (Button) findViewById(R.id.close);

        // Intent, das diese Activity geöffnet hat holen
        Intent i = getIntent();
        Bundle b = i.getExtras();

        // TODO richtigen Text anzeigen
        // Text aus der Datenbank holen
        // passend zu dem Level, in dem wir gerade sind
        // sowas wie
        // t_help.setText(SELECT tipps FROM level WHERE Level = b.getInt("Level"));

        b_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // schließe diese Activity
                finish();
            }
        });
    }
}
