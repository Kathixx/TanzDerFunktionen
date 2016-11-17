package com.example.arabellaprivat.tanzderfunktionen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * hier werden Tipps zum Lösen der Aufgaben gegeben
 */
public class Info extends AppCompatActivity {

    /** zeigt den Hilfetext an */
    private TextView t_hilfetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Variablen belegen
        t_hilfetext = (TextView) findViewById(R.id.hilfetext);

        // TODO richtigen Text anzeigen
        // Text aus der Datenbank holen
        // passend zu dem Level, in dem wir gerade sind
        // sowas wie
        // t_hilfetext.setText(SELECT tipps FROM level WHERE Level = (SELECT Level FROM Nutzer));
    }
}
