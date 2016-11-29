package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * hier fidet das Spiel / das Zeichnen statt
 */
public class Spiel extends AppCompatActivity {

    // IV
    /** Info-Button */
    private Button b_info;
    /** Button zum Löschen der View */
    private Button b_loeschen;
    /** Menü Button */
    private Button b_menu;
    /** Button zum prüfen der gemalten Funktion */
    private Button b_pruefen;
    /** führt zum nächsten Level oder zur Endbewertung */
    private Button b_weiter;
    /** sagt, ob richtig oder falsch gezeichnet wurde */
    private TextView t_bewertung;
    /** zeigt die zu malende Funktion an */
    private TextView t_funktion;
    /** diese TextView zeigt das aktuelle Level an */
    private TextView t_level;
    private ImageView i_punkteanzeige;
    /** Zeichenfläche */
    private Zeichenfläche z;
    /** Liste */
    private Liste listX;
    private Liste listY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        // Variablen belegen
        b_info = (Button) findViewById(R.id.info);
        b_loeschen = (Button) findViewById(R.id.loeschen);
        b_menu = (Button) findViewById(R.id.menu);
        b_pruefen = (Button) findViewById(R.id.pruefen);
        b_weiter = (Button) findViewById(R.id.weiter);
        t_bewertung = (TextView) findViewById(R.id.bewertung);
        t_funktion = (TextView) findViewById(R.id.funktion);
        t_level = (TextView) findViewById(R.id.level);
        i_punkteanzeige = (ImageView) findViewById(R.id.punkteanzeige);
        z=(Zeichenfläche) findViewById(R.id.zeichenfläche);
        listX= z.getListX();
        listY= z.getListY();

        // Bewertungs TextView und Weiter Button sind erstmal unsichtbar
        b_weiter.setVisibility(View.INVISIBLE);
        t_bewertung.setVisibility(View.INVISIBLE);

        // TODO zeigen, in welchem Level wir sind
        // t_level.setText("Level " + (SELECT Level FROM Nutzer));

        // Kreise zur Anzeige, wie die Level absolviert wurden
        this.punkteanzeigeZeichnen();

        // TODO Name der Funktion anzeigen
        // indem die Funktion aus der Datenbank geholt wird
        // t_funktion.setText(SELECT funktion FROM Level WHERE Level = (SELECT Level FROM Nutzer));
        t_funktion.setText("f(x)=(0.5*x)+2");

        // Buttons mit Funktion belegen
        b_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

        b_loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // löscht die Zeichenfläche
                z.loescheView();
            }
        });

        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

        b_pruefen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ruft die check-Methode auf, sodass die Funktion geprüft wird
                // TODO trage das Ergebnis in die Datenbank ein
                check(listX, listY, z,t_bewertung, 1);

                // ändere die Anzeige des Buttons
                // Löschen und Prüfen Button verschwinden
                b_loeschen.setVisibility(View.INVISIBLE);
                b_pruefen.setVisibility(View.INVISIBLE);

                // RICHITG oder FALSCH soll angezeigt werden
                t_bewertung.setVisibility(View.VISIBLE);
                // TODO wenn richtig gezeichnet wurde (wenn in der DB eine 1 steht?)
                // wird schon bei der Check-Funktion richtig reingeschrieben, kein auslesen notwendig?!
                // t_bewertung.setText("Richtig!");
                // } else
                    // t_bewertung.setText("Falsch!");

                // Button, der zum nächsten Level führt wird sichtbar
                b_weiter.setVisibility(View.VISIBLE);

                // TODO Das Korrekturbild soll über die Zeichnung gelegt werden
                // wird schon in der Check-Funktion gemacht
            }
        });

        b_weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
    }

    /**
     * koordiniert das Zeichnen der Punkteanzeige
     * hier werden Zeichen-Eigenschaften gesetzt
     */
    private void punkteanzeigeZeichnen(){
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        // der Abstand vom linken Bildschirmrand wird um jew. 15 px erhöht
        int abstand_x = 0;
        // Style und Farbe hängen von der Bewertung der Level ab
        Paint paint = new Paint();
        // mit einer Schleife gehen wir durch die DB zu den verschiedenen Levels
        for (int i = 1; i <= 5; i++) {
            // grundsätzlich sind alle Kreise leer mit schwarzer Umrandung
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            // wenn das Level bestanden ist
            // TODO if((SELECT Bewertung FROM Level WHERE Level = i) == 1){
            // sei der Kreis grün ausgemalt
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            // }
            // wenn das 1. Level nicht bestanden ist
            // TODO if((SELECT Bewertung FROM Level WHERE Level = i) == 0){
            // sei der Kreis rot ausgemalt
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            // }
            // wenn das 1. Level noch nicht gespielt wurde
            // also wenn in der DB nichts oder null steht
            // bleiben die Einstellungen wie am Anfang

            // Abstand zum linken Nachbarkreis vergrößern
            abstand_x += 15;

            // mit diesen Einstellungen den Kreis malen
            this.einenKreisZeichnen(bitmap, paint, abstand_x);
        }
    }

    /**
     * zeichnet einen Punkt
     * @param bitmap        Hier wird gezeichnet
     * @param paint         Zeichen-Eigenschaften
     * @param abstand_x     Abstand zum linken Bildschirmrand, bzw. zum linken Nachbar-Kreis
     */
    private void einenKreisZeichnen(Bitmap bitmap, Paint paint, int abstand_x){
        Canvas canvas = new Canvas(bitmap);
        // Kreis zeichnen
        // mit dem sich mit jedem Kreis vergrößernden linken Abstand
        // Abstand nach oben 5 px
        // Radius 5 px
        // mit den "Stift"-Eigenschaften, die je nach Level verändert wurden
        canvas.drawCircle(abstand_x, 5, 5, paint);

        // in die ImageView einfügen
        i_punkteanzeige.setImageBitmap(bitmap);
    }

    /**
     * wird beim Click auf einen Button aufgerufen
     * und startet abhängig vom Button die passende Activity
     * @param view  Button, der geklickt wurde
     */
    public void sendMessage(View view){
        if(view.getId() == R.id.info) {
            Intent i = new Intent(this, Info.class);
            startActivity(i);
        } else if(view.getId() == R.id.menu) {
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        } else if(view.getId() == R.id.weiter) {
            // TODO wenn wir nicht im 5. Level sind, sondern davor
                // gehe ins nächste Level
                // TODO in der Datenbank das nächste Level eintragen
                // bleibe also im Spiel
                // Intent i = new Intent(this, Spiel.class);
                // startActivity(i);
            // TODO wenn wir in Level 5, also am Ende sind
                // gehe zur Endbewertung
                Intent i = new Intent(this, Bewertung.class);
                startActivity(i);
        }

    }


    /**Methode check
     * vergleicht den gezeichneten Pfad mit der jeweils vorgegebenen Funktion
     * @param listX Liste in der die x-Werte gespeichert werden
     * @param listY Liste in der die y-Werte gespeichert werden
     * @param z View, auf dem gezeichnet wird
     * @param t Textfeld, das Endergebnis anzeigt
     * @param x Level in dem man gerade ist @Vicky!
     * @return
     */


    void check(Liste listX, Liste listY, Zeichenfläche z, TextView t, int x) {
        double xWert;
        double yWert;
        // index i wird fortlaufend hochgezählt, bis zum Ende der Liste
        int index=0;
        int listLength=listX.size();
        String s="";
        // Zähler zählt richtige Vergleiche des gezeichneten und berechneten Wert
        int counter=0;
        while (index < listLength) {
            // x-Wert aus der Liste auslesen
            xWert=(listX.get(index));
            //s=s+" xP: "+xWert;
            // in x-Koordinaten-Werte umwandeln
            xWert=pixelToCoordinate(xWert,z,10);
            //s=s+" x-K: "+ xWert;
            // entsprechenden y-Wert mit der Funktion berechnen , Level x zeigt an welche Funktion aufgerufen werden soll
            // @VICKY + ARABELLA: wie liest man das Level aus??
            switch (x) {
                case 1:
                    yWert = linearFunction(xWert);
                    // s=s+" y-K: "+yWert;
                    break;
                case 2:
                    yWert = quadratFunction(xWert);
                    //s=s+" y-K: "+yWert;
                    break;
                default:
                    yWert=0;
            }
            // y-Koordinaten-Wert in Pixel umrechnen
            yWert=coordinateToPixel(yWert,z,6);
            //s=s+" y-P: "+yWert;
            // berechneten y-Wert mit y-Wert aus der Liste vergleichen
            boolean comparison=compare(yWert,listY,index);
            //s=s+" yV: "+ listY.get(index)+"\n";
            // counter hochzählen
            if (comparison) counter++;
            // index hochzählen
            index++;
        }

        // je nach dem wie viele Vergleiche richtig sind wird der gezeichnete Pfad akzeptie

        if (counter >8)
            // Ergebnis wird im TextView ausgegeben
            t.setText("Richtig"+s);
        else t.setText("Falsch"+s);
        z.changeBackground(x);

    }


    /**
     * lineare Funktion: f(x)=0,5x+2
     * hier könnte man eventuell vorgegeben Werte auch aus Datenbank auslesen?
     * @param x zu berechneter x-Wert in Koordinaten-Angaben
     * @return yWert berechneter y-Wert
     */
    double linearFunction (double x){
        // @VICKY auslesen aus der Liste
        // Daten evtl. aus Datenbank auslesen
        double yWert=(0.5*x)+2;
        return  round(yWert);
    }// Ende linearFunction()

    /**
     * quadratische Funktion: 0.25*x^2+1x-4
     * Todo Vicky Datenbank auslesen
     */
    double quadratFunction (double x){
        double yWert=(0.25*x*x)+x-4;
        return round(yWert);
    }


    /** Methode pixelToCoordinate
     *  rechnet den ausgelesen xWert in Koordinaten-Werte um
     *  abhängig von den maximalen x-WErten und y-WErten!
     *  Vorrausgesetzt dass der Koordinatenursprung in der Mitte des Views liegt
     *
     *  @param pixel  ausgelesener x-WErt in Pixelangaben
     *  @param xMax   maximaler Wert der x-Achse
     *  @return xWert dieser Wert wird in die Funktion eingesetzt
     */
    double pixelToCoordinate ( double pixel, Zeichenfläche z, int xMax){
        double xWert;
        // Liest die maximalen Pixelwerte des Views zurück
        //float maxXPixel = v. getRight-getLeft??
        float widthView=z.getRight()-z.getLeft();
        // Länge von x=0 bis x=xMax
        float halfView=widthView/2;
        // LängenMaß einer Koordinate
        float stepPixels= widthView/(2*xMax);
        xWert=(pixel-halfView)/stepPixels;
        return  round(xWert);
    }// Ende pixelToCoordinate

    /** Methode round
     * rundet einen Float-Wert auf 2 Dezimalstellen genau und gibt ihn als Double-Wert zurück
     * @param f zurundender Float-Wert
     * @return gerunderter Wert als Double
     */
    double round (float f){
        double rounded=Math.round(f*100);
        rounded=rounded/100;
        return rounded;
    }//Ende round

    /** Methode round
     * rundet einen Double-Wert auf 2 Dezimalstellen ab
     * @param d zu rundender Double-Wert
     * @return gerundeter Double-Wert
     */
    double round (double d){
        double rounded=Math.round(d*100);
        return rounded/100;
    }//Ende round

    /** Methode coordinateToPixel
     * rechnet einen Koordinaten-Wert in Pixelwert um
     * v.a. für y-Werte
     * abhängig von den maximalen y-Werten
     * Vorraussetzung: Koordinatensystem ist zentriert
     *
     * @param coordinate  berechnete y-Koordinaten
     * @param width     maxi
     * @param yMax
     * @return
     */
    double coordinateToPixel (double coordinate, Zeichenfläche z, float yMax){
        double yWert;
        float widthView=z.getBottom()-z.getTop();
        float stepPixels=widthView/(2*yMax);
        coordinate=yMax-coordinate;
        yWert=coordinate*stepPixels;
        return yWert;
    } // Ende coordinateToPixel

    /** Methode compare()
     * vergleicht den mitgegebenen Wert, mit dem Wert an ensprechender Stelle in der Liste
     * in Pixel-Werten
     * @param d  zu vergleichender Wert (berechnet)
     * @param l  Liste, in der Vergleichswerte gespeichert sind
     * @param index Stelle in der Liste, an der Vergleichswert steht
     * @return true falls Vergleich der zwei Werte übereinstimmt
     */
    boolean compare (double d, Liste l, int index){
        double tolerance=50;
        float compareValue=l.get(index);
        if (d>=compareValue-tolerance && d<=compareValue+tolerance ) return true;
        else return false;
    }//Ende compare

}